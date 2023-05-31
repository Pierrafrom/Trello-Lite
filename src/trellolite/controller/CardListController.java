package trellolite.controller;

import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.CardListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            //--------------------------------------------------------------------------------------------------------------
            // CONSTANTS
            //--------------------------------------------------------------------------------------------------------------

            final int CHANGE_NAME = 0;
            final int ADD_CARD = 1;
            final int DELETE_LIST = 2;

            // Switch between the different actions of the actionComboBox
            switch (actionComboBox.getSelectedIndex()) {
                case CHANGE_NAME -> {
                    // Changing the name of the cardList
                    OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                    String newName = optionPaneStyle.showInputDialog("Enter the new name of the list");
                    if (newName.isEmpty()) {
                        String message = "Please, enter a valid name";
                        String title = "Empty name";
                        optionPaneStyle.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    cardList.setName(newName);
                    cardListView.update(cardList);

                    // Update the actionComboBox to match the new one created during the update
                    actionComboBox = cardListView.getActionsComboBox();
                    actionComboBox.addActionListener(new ActionComboBoxListener());
                }
                case ADD_CARD ->
                    // Add a new card
                    System.out.println("Add a new card");
                    // TODO: Add a new card
                case DELETE_LIST -> {
                    // Deleting the cardList
                    // Asking for confirmation
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
    }
}
