package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.TrelloMain;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.model.Role;
import trellolite.model.Workspace;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.ManagerView;
import trellolite.view.WorkspaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

/**
 * This class is the controller of the Board class.
 *
 * @author Pierre Fromont Boissel
 * @author Roxane Zaharia
 * @author Augustin Lecomte
 */
public class BoardController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------

    Workspace workspace;
    Board board;
    WorkspaceView workspaceView;
    BoardView boardView;

    ComboBoxStyle actionComboBox;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------
    public BoardController(Workspace workspace, Board board, WorkspaceView workspaceView, BoardView boardView) {
        this.workspace = workspace;
        this.board = board;
        this.workspaceView = workspaceView;
        this.boardView = boardView;

        actionComboBox = boardView.getActionsComboBox();
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
         * @param e ,ActionEvent, the event
         * @author Pierre Fromont Boissel
         * @author Augustin Lecomte
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // ---------------------------------------------------------------------------------------------------------
            // CONSTANTS
            // ---------------------------------------------------------------------------------------------------------

            final int RENAME_BOARD = 0;
            final int ADD_NEW_LIST = 1;
            final int DELETE_BOARD = 2;

            Role role = TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getRole(TrelloMain.currentParticipant);
            if (role == Role.OBSERVER){
                String message = "Sorry, observers can not modify the workspace in any way.";
                OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                optionPaneStyle.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
                }

            // Switch between the different actions of the actionComboBox
            switch (actionComboBox.getSelectedIndex()) {
                case RENAME_BOARD -> {
                    // Rename the Board

                    // Instantiate the variables
                    boolean empty = false;
                    boolean used = false;
                    String alert;
                    String titleAlert = "Invalid name";

                    // Prompt the user to enter the new name of the board
                    OptionPaneStyle optionPaneStyle = new OptionPaneStyle();

                    String message = "Enter the new name of the board:";
                    String title = "Board name modification";
                    Object ChangeBoardNameObj = optionPaneStyle.showInputDialog(null, message,title,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    String newName = ChangeBoardNameObj.toString().trim();

                    // Rename the Board model only if the name is not empty and unique in the workspace
                    // Else, it displays an error message
                    empty = newName.isEmpty();
                    used = isNameUsed(newName);
                    if(empty || used){
                        if(empty) alert = "The name cannot be empty";
                        else alert = "The name is already used";

                        optionPaneStyle.showMessageDialog(null, alert, titleAlert,
                                                          JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    board.setName(newName);

                    // update the Board view
                    boardView.update(board);
                }
                case ADD_NEW_LIST -> {
                    // Add a new list
                    createNewList();
                }
                case DELETE_BOARD -> {
                    // Delete the Board
                    // Asking for confirmation before deleting the board
                    ConfirmationDialog dialogController = new ConfirmationDialog();
                    String message = "Are you sure you want to delete the board : " + board.getName() + " ?";
                    String title = "Confirmation";
                    boolean result = dialogController.showConfirmationDialog(message, title);

                    // If the user selected "Yes"
                    if (result) {
                        // Delete the Board model from the Workspace model
                        workspace.removeBoard(board);
                        workspaceView.update();
                        // update the Workspace view
                    }
                }
            }
        }

        /**
         * This method creates a new list and adds it to the selected workspace.
         * <p>
         * It prompts the user to enter the name of the new list.
         * Then it creates a new list and adds it to the selected board.
         * </p>
         * 
         * @author Pierre Fromont Boissel
         * @see ManagerView
         * @see trellolite.model
         * @see trellolite.style
         * @see javax.swing.JOptionPane
         */
        private void createNewList() {
            // Create a new OptionPaneStyle object to display dialogs with the same style
            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();

            // Prompt the user to enter the name of the new list
            Object listNameObj = optionPaneStyle.showInputDialog(null,
                    "Enter the name of the new List:", "New List creation",
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);

            // Check if the user entered a list name
            if (listNameObj != null) {
                // Convert the object to a string and trim leading and trailing spaces
                String listName = listNameObj.toString().trim();

                // While the list name is empty, ask the user to enter a new one
                while (listName.isEmpty()) {
                    // Display an error message indicating that the list name cannot be empty
                    optionPaneStyle.showMessageDialog(null, "List name cannot be empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the list name again
                    listNameObj = optionPaneStyle.showInputDialog(null,
                            "Enter the name of the new list:", "New list creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    listName = listNameObj.toString().trim();
                }

                // Get the names of the existing lists
                ArrayList<String> names = new ArrayList<String>();
                for (CardList list : board.getLists()) {
                    names.add(list.getName());
                }

                // While the list name already exists, ask the user to enter a new one
                while (names.contains(listName)) {
                    // Display an error message indicating that the list name already exists
                    optionPaneStyle.showMessageDialog(null, "list name already exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the list name again
                    listNameObj = optionPaneStyle.showInputDialog(null,
                            "Enter the name of the new list:", "New list creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    listName = listNameObj.toString().trim();
                }

                // Create the new list with the user-provided name
                CardList list = new CardList(listName);
                board.addList(list);
                boardView.update(board);
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
            for (Board board : workspace.getBoards()) {
                if (board.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }
}
