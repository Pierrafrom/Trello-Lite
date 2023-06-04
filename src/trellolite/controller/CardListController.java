package trellolite.controller;

import trellolite.TrelloMain;
import trellolite.model.*;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.CardCreatorView;
import trellolite.view.CardListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class is the controller of the Board class.
 *
 * @author Augustin Lecomte
 * @author Pierre Fromont Boissel
 * @see trellolite.model.Board
 * @see trellolite.view.BoardView
 * @see trellolite.controller.CardListController
 */
public class CardListController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------

    Board board;
    CardList cardList;
    BoardView boardView;
    CardListView cardListView;

    ComboBoxStyle actionComboBox;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the BoardController class.
     *
     * @param board        ,Board, The board
     * @param cardList     CardList, The card list
     * @param boardView    BoardView, The board view
     * @param cardListView CardListView, The card list view
     * @see trellolite.model.Board
     * @see trellolite.model.CardList
     * @see trellolite.view.BoardView
     * @see trellolite.view.CardListView
     */
    public CardListController(Board board, CardList cardList, BoardView boardView, CardListView cardListView) {

        this.board = board;
        this.cardList = cardList;
        this.boardView = boardView;
        this.cardListView = cardListView;

        actionComboBox = cardListView.getActionsComboBox();
        actionComboBox.addActionListener(new ActionComboBoxListener());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // LISTENERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Listener for the actionComboBox
     *
     * @author Pierre Fromont Boissel
     * @author Augustin Lecomte
     * @see trellolite.view.CardListView
     * @see trellolite.controller.CardListController
     * @see trellolite.model.CardList
     */
    private class ActionComboBoxListener implements ActionListener {

        // ---------------------------------------------------------------------------------------------------------
        // CONSTANTS
        // ---------------------------------------------------------------------------------------------------------

        final int CHANGE_NAME = 0;
        final int ADD_CARD = 1;
        final int DELETE_LIST = 2;

        private CardCreatorView cardCreatorView;

        /**
         * Action performed when the user selects an action in the actionComboBox
         *
         * @param e ,ActionEvent, The event
         * @author Pierre Fromont Boissel
         * @author Augustin Lecomte
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            Role role = TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getRole
                    (TrelloMain.currentParticipant);
            if (role == Role.OBSERVER) {
                String message = "Sorry, observers can not modify the workspace in any way.";
                OptionPaneStyle.showMessageDialog(null, message, "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Switch between the different actions of the actionComboBox
            switch (actionComboBox.getSelectedIndex()) {
                case CHANGE_NAME -> {
                    // Instantiate the variables
                    boolean empty;
                    boolean used;
                    String alert;
                    String titleAlert = "Invalid name";

                    String newName = OptionPaneStyle.showInputDialog("Enter the new name of the list");

                    empty = newName.isEmpty();
                    used = isNameUsedList(newName);

                    // Rename the CardList model only if the name is not empty and unique in the
                    // board
                    // Else, it displays an error message
                    if (empty || used) {
                        if (empty)
                            alert = "The name cannot be empty";
                        else
                            alert = "The name is already used in this board";

                        OptionPaneStyle.showMessageDialog(null, alert, titleAlert,
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    cardList.setName(newName);
                    cardListView.update(cardList);

                    // Update the actionComboBox to match the new one created during the update
                    actionComboBox = cardListView.getActionsComboBox();
                    actionComboBox.addActionListener(new ActionComboBoxListener());
                }
                case ADD_CARD -> createCardCreator();
                case DELETE_LIST -> {
                    // Ask for confirmation
                    ConfirmationDialog dialogController = new ConfirmationDialog();
                    String message = "Are you sure you want to delete the list : " + cardList.getName() + " ?";
                    String title = "Confirmation";
                    boolean result = dialogController.showConfirmationDialog(message, title);

                    // If the user selects "Yes"
                    if (result) {
                        board.removeList(cardList);
                        boardView.update(board);
                    }
                }
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        // METHODS
        // -------------------------------------------------------------------------------------------------------------

        /**
         * Check if the name is already used in the board
         *
         * @param name String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         * @see trellolite.model.Board
         * @see trellolite.model.CardList
         * @see trellolite.view.BoardView
         * @see trellolite.view.CardListView
         */
        private boolean isNameUsedList(String name) {
            for (CardList list : board.getLists()) {
                if (list.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Check if the name is already used in the card list
         *
         * @param name String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         */
        private boolean isNameUsedCard(String name) {
            for (Card card : cardList.getCards()) {
                if (card.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Create a CardCreatorView to create a new card in the card list
         *
         * @author Pierre Fromont Boissel
         * @see trellolite.view.CardCreatorView
         * @see trellolite.model.Card
         * @see trellolite.model.CardList
         */
        private void createCardCreator() {
            // dispose the previous cardCreatorView if it exists
            if (cardCreatorView != null) {
                cardCreatorView.dispose();
            }
            // create a new cardCreatorView
            cardCreatorView = new CardCreatorView();

            // add a listener to the cardCreatorView
            cardCreatorView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // check if the user canceled the creation
                    if (!cardCreatorView.isCancelled()) {

                        // check if the name is already used
                        if (isNameUsedCard(cardCreatorView.getNameData())) {
                            OptionPaneStyle.showMessageDialog(null, "Please, enter a unique" +
                                    " name", "name already used", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the name is empty
                        if (cardCreatorView.getNameData().isEmpty()) {
                            OptionPaneStyle.showMessageDialog(null, "Please, enter a name",
                                    "empty name", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the date is valid
                        else if (!isValidDateFormat(cardCreatorView.getDueDateData())
                                && !cardCreatorView.getDueDateData().isEmpty()) {
                            OptionPaneStyle.showMessageDialog(null,
                                    "Please, enter a valid date (yyyy-MM-dd) in the future",
                                    "invalid date", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // create a new card
                        String name = cardCreatorView.getNameData();
                        String description = cardCreatorView.getDescriptionData();
                        LocalDate dueDate = null;
                        if (!cardCreatorView.getDueDateData().isEmpty()) {
                            dueDate = LocalDate.parse(cardCreatorView.getDueDateData());
                        }
                        Card card = new Card(name, description, dueDate);
                        // add Participants
                        ArrayList<Object> participants = cardCreatorView.getParticipantsData();
                        for (Object participant : participants) {
                            card.addMember((Participant) participant);
                        }
                        // add cards
                        ArrayList<Object> cards = cardCreatorView.getCardsData();
                        for (Object cardObject : cards) {
                            card.addLinkedCard((Card) cardObject);
                        }

                        // add the card to the list
                        cardList.addCard(card);
                        cardListView.update(cardList);
                    }
                }
            });
        }

        /**
         * Check if the date is valid
         *
         * @param dateString, String, the date to check
         * @return boolean, true if the date is valid, false otherwise
         * @throws DateTimeParseException, if the date is not in the correct format
         * @author Pierre Fromont Boissel
         * @see LocalDate
         */
        public static boolean isValidDateFormat(String dateString) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                // check if the date is in the future
                return !LocalDate.parse(dateString, formatter).isBefore(LocalDate.now());
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }
}
