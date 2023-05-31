package trellolite.controller;

import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.CardListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
                    if(newName.isEmpty()) {
                        optionPaneStyle.showMessageDialog(null, "Please, enter a valid name", "empty name", JOptionPane.ERROR_MESSAGE);
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
                    
                    createNewCard();
                    

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

        private void createNewCard() {
            // create the frame for the new card creation
            JFrame frame = new JFrame("New card");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            


        }
    }
}
