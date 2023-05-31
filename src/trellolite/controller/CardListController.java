package trellolite.controller;

import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.CardListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.html.Option;

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
                    System.out.println("Change the name of the list");
                    // TODO: Change the name of the list
                    // change the name of the list model
                    // update the view
                    OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                    String newName = optionPaneStyle.showInputDialog("Enter the new name of the list", "New name");
                    cardList.setName(newName);
                    cardListView.update(cardList);
                    break;
                case 1:
                    // Add a new card
                    System.out.println("Add a new card");
                    // TODO: Add a new card
                    break;
                case 2:
                    // Delete the list
                    // Ask for confirmation
                    ConfirmationDialog dialogController = new ConfirmationDialog();
                    String message = "Are you sure you want to delete the list : " + cardList.getName() + " ?";
                    String title = "Confirmation";
                    boolean result = dialogController.showConfirmationDialog(message, title);

                    // If the user selected "Yes"
                    if (result) {
                        // TODO: Delete the List
                        // Delete the List model from the Board model
                        // update the Board view
                        System.out.println("Delete the List");
                    }
                    break;
            }
        }
    }
}
