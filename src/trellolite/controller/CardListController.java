package trellolite.controller;

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

    private class ActionComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionComboBox.getSelectedIndex()) {
                case 0:
                    // Change the name of the list
                    OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                    String newName = optionPaneStyle.showInputDialog("Enter the new name of the list", "New name");
                    if (newName.isEmpty()) {
                        optionPaneStyle.showMessageDialog(null, "Please, enter a valid name", "empty name",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    cardList.setName(newName);
                    cardListView.update(cardList);

                    // Update the actionComboBox to match the new one create in the update
                    actionComboBox = cardListView.getActionsComboBox();
                    actionComboBox.addActionListener(new ActionComboBoxListener());

                    break;
                case 1:
                    // Add a new card
                    createCardCreator();
                    break;
                case 2:
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
                    break;
            }
        }

        // -----------------------------------------------------------------------------------------------------------------
        // METHODS
        // -----------------------------------------------------------------------------------------------------------------

        private void createCardCreator() {
            CardCreatorController constructor = new CardCreatorController();

            constructor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // check if the user cancelled the creation
                    if (!constructor.isCancelled()) {

                        // check if the name is empty
                        if (constructor.getNameData().isEmpty()) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null, "Please, enter a name", "empty name",
                                    JOptionPane.ERROR_MESSAGE);
                            // create a new constructor
                            createCardCreator();
                        }

                        // check if the date is valid
                        else if (!isValidDateFormat(constructor.getDueDateData()) && !constructor.getDueDateData().isEmpty()) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null, "Please, enter a valid date (yyyy-MM-dd) in the future", "invalid date",
                                    JOptionPane.ERROR_MESSAGE);
                            // create a new constructor
                            createCardCreator();
                        }

                        // create a new card
                        String name = constructor.getNameData();
                        String description = constructor.getDescriptionData();
                        LocalDate dueDate = LocalDate.parse(constructor.getDueDateData());
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
