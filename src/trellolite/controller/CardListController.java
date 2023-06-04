package trellolite.controller;

import trellolite.TrelloMain;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.model.Role;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.CardListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
     * @param board ,Board, The board
     * @param cardList ,CardList, The card list
     * @param boardView ,BoardView, The board view
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

            //--------------------------------------------------------------------------------------------------------------
            // CONSTANTS
            //--------------------------------------------------------------------------------------------------------------

            final int CHANGE_NAME = 0;
            final int ADD_CARD = 1;
            final int DELETE_LIST = 2;

            Role role = TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getRole(TrelloMain.currentParticipant);
            if (role == Role.OBSERVER){
                String message = "Sorry, observers can not modify the workspace in any way.";
                OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                optionPaneStyle.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

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
                    used = isNameUsed(newName);

                    // Rename the CardList model only if the name is not empty and unique in the board
                    // Else, it displays an error message
                    if (empty || used) {
                        if(empty) alert = "The name cannot be empty";
                        else alert = "The name is already used in this board";
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
                    createNewCard();
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

        /**
         * Check if the name is already used in the board
         *
         * @param name ,String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         */
        private boolean isNameUsed(String name) {
            for (CardList list : board.getLists()) {
                if (list.getName().equals(name)) {
                    return true;
                }
            }
            return false;
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
