package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Board;
import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.model.Participant;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.CardListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * This class is the controller of the Board class.
 *
 * @author Augustin Lecomte
 * @author Pierre Fromont Boissel
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
     * @param cardList     ,CardList, The card list
     * @param boardView    ,BoardView, The board view
     * @param cardListView ,CardListView, The card list view
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
     */
    private class ActionComboBoxListener implements ActionListener {
        /**
         * Action performed when the user selects an action in the actionComboBox
         *
         * @param e ,ActionEvent, The event
         * @author Pierre Fromont Boissel
         * @author Augustin Lecomte
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // --------------------------------------------------------------------------------------------------------------
            // CONSTANTS
            // --------------------------------------------------------------------------------------------------------------

            final int CHANGE_NAME = 0;
            final int ADD_CARD = 1;
            final int DELETE_LIST = 2;

            // Switch between the different actions of the actionComboBox
            switch (actionComboBox.getSelectedIndex()) {
                case CHANGE_NAME -> {
                    // Changing the name of the cardList

                    // Instantiate the variables
                    boolean empty = false;
                    boolean used = false;
                    String alert;
                    String titleAlert = "Invalid name";

                    OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                    String newName = optionPaneStyle.showInputDialog("Enter the new name of the list");

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
                        String message = "Please, enter a valid name";
                        String title = "Empty name";

                        optionPaneStyle.showMessageDialog(null, alert, titleAlert,
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    cardList.setName(newName);
                    cardListView.update(cardList);

                    // Update the actionComboBox to match the new one created during the update
                    actionComboBox = cardListView.getActionsComboBox();
                    actionComboBox.addActionListener(new ActionComboBoxListener());
                }
                case ADD_CARD -> {
                    // Add a new card
                    createCardCreator();
                }
                case DELETE_LIST -> {
                    // Delete the list
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

        // -----------------------------------------------------------------------------------------------------------------
        // METHODS
        // -----------------------------------------------------------------------------------------------------------------

        /**
         * Check if the name is already used in the board
         *
         * @param name ,String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
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
         * Check if the name is already used in the cardlist
         *
         * @param name ,String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         */
        private boolean isNameUsedCard(String name) {
            for (Card card: cardList.getCards()) {
                if (card.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        private void createCardCreator() {
            CardCreatorController constructor = new CardCreatorController();

            constructor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // check if the user cancelled the creation
                    if (!constructor.isCancelled()) {

                        // check if the name is already used
                        if (isNameUsedCard(constructor.getNameData())) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null, "Please, enter a unique name", "name already used",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the name is empty
                        if (constructor.getNameData().isEmpty()) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null, "Please, enter a name", "empty name",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the date is valid
                        else if (!isValidDateFormat(constructor.getDueDateData())
                                && !constructor.getDueDateData().isEmpty()) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null,
                                    "Please, enter a valid date (yyyy-MM-dd) in the future", "invalid date",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // create a new card
                        String name = constructor.getNameData();
                        String description = constructor.getDescriptionData();
                        LocalDate dueDate = null;
                        if (!constructor.getDueDateData().isEmpty()) {
                        dueDate = LocalDate.parse(constructor.getDueDateData());
                        }
                        Card card = new Card(name, description, dueDate);
                        // add Participants
                        ArrayList<Object> participants = constructor.getParticipantsData();
                        for (Object participant : participants) {
                            card.addMember((Participant) participant);
                        }

                        // add the card to the list
                        cardList.addCard(card);
                        cardListView.update(cardList);
                    }
                }
            });
        }

        public static boolean isValidDateFormat(String dateString) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                // check if the date is in the future
                if (LocalDate.parse(dateString, formatter).isBefore(LocalDate.now())) {
                    return false;
                }
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }

}
