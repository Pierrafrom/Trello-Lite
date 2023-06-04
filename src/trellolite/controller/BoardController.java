package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.TrelloMain;
import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.model.Role;
import trellolite.model.Workspace;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.ManagerView;
import trellolite.view.WorkspaceInfoView;
import trellolite.view.WorkspaceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is the controller of the Board class.
 *
 * @author Pierre Fromont Boissel
 * @author Roxane Zaharia
 * @author Augustin Lecomte
 * @see Board
 * @see BoardView
 * @see Workspace
 * @see WorkspaceView
 * @see WorkspaceInfoView
 */
public class BoardController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------

    private final Workspace WORKSPACE;
    private final Board BOARD;
    private final WorkspaceView WORKSPACEVIEW;
    private final BoardView BOARDVIEW;
    private final WorkspaceInfoView WORKSPACEINFOVIEW;

    private final ComboBoxStyle ACTIONCOMBOBOX;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the BoardController class
     *
     * @param workspace,         the workspace that contains the board
     * @param board,             the board to control
     * @param workspaceView,     the workspace view that contains the board view
     * @param boardView,         the board view to control
     * @param workspaceInfoView, the workspace info view that is used to display the board info
     */
    public BoardController(Workspace workspace, Board board, WorkspaceView workspaceView, BoardView boardView,
                           WorkspaceInfoView workspaceInfoView) {
        WORKSPACE = workspace;
        BOARD = board;
        WORKSPACEVIEW = workspaceView;
        BOARDVIEW = boardView;
        WORKSPACEINFOVIEW = workspaceInfoView;

        ACTIONCOMBOBOX = boardView.getActionsComboBox();
        ACTIONCOMBOBOX.addActionListener(new ActionComboBoxListener());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // LISTENERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Listener for the ACTIONCOMBOBOX
     *
     * @author Pierre Fromont Boissel
     * @author Augustin Lecomte
     * @see BoardController
     * @see BoardView
     * @see Workspace
     * @see WorkspaceView
     * @see WorkspaceInfoView
     */
    private class ActionComboBoxListener implements ActionListener {

        // ---------------------------------------------------------------------------------------------------------
        // CONSTANTS
        // ---------------------------------------------------------------------------------------------------------
        final int RENAME_BOARD = 0;
        final int ADD_NEW_LIST = 1;
        final int DELETE_BOARD = 2;

        /**
         * Action performed when the user selects an action in the ACTIONCOMBOBOX
         *
         * @param e ,ActionEvent, the event
         * @author Pierre Fromont Boissel
         * @author Augustin Lecomte
         * @see BoardController
         * @see BoardView
         * @see Workspace
         * @see WorkspaceView
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // Check if the user is allowed to modify the BOARD
            Role role = TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getRole
                    (TrelloMain.currentParticipant);
            if (role == Role.OBSERVER) {
                String message = "Sorry, observers can not modify the WORKSPACE in any way.";
                OptionPaneStyle.showMessageDialog(null, message, "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Switch between the different actions of the ACTIONCOMBOBOX
            switch (ACTIONCOMBOBOX.getSelectedIndex()) {
                case RENAME_BOARD -> {
                    // Instantiate the variables
                    boolean empty;
                    boolean used;
                    String alert;
                    String titleAlert = "Invalid name";

                    // Prompt the user to enter the new name of the BOARD
                    String message = "Enter the new name of the BOARD:";
                    String title = "Board name modification";
                    Object ChangeBoardNameObj = OptionPaneStyle.showInputDialog(null, message, title,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    String newName = ChangeBoardNameObj.toString().trim();

                    // Rename the Board model only if the name is not empty and unique in the
                    // WORKSPACE
                    // Else, it displays an error message
                    empty = newName.isEmpty();
                    used = isNameUsed(newName);
                    if (empty || used) {
                        if (empty)
                            alert = "The name cannot be empty";
                        else
                            alert = "The name is already used";

                        OptionPaneStyle.showMessageDialog(null, alert, titleAlert,
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    BOARD.setName(newName);

                    // update the Board view
                    WORKSPACEVIEW.update();
                    WORKSPACEINFOVIEW.update();

                }
                case ADD_NEW_LIST -> createNewList();
                case DELETE_BOARD -> {
                    // Asking for confirmation before deleting the BOARD
                    ConfirmationDialog dialogController = new ConfirmationDialog();
                    String message = "Are you sure you want to delete the BOARD : " + BOARD.getName() + " ?";
                    String title = "Confirmation";
                    boolean result = dialogController.showConfirmationDialog(message, title);

                    // If the user selected "Yes"
                    if (result) {
                        // Delete the Board model from the Workspace model
                        WORKSPACE.removeBoard(BOARD);
                        WORKSPACEVIEW.update();
                        WORKSPACEINFOVIEW.update();
                    }
                }
            }
        }

        /**
         * This method creates a new list and adds it to the selected WORKSPACE.
         * <p>
         * It prompts the user to enter the name of the new list.
         * Then it creates a new list and adds it to the selected BOARD.
         * </p>
         *
         * @author Pierre Fromont Boissel
         * @see ManagerView
         * @see trellolite.model
         * @see trellolite.style
         * @see javax.swing.JOptionPane
         */
        private void createNewList() {
            // Prompt the user to enter the name of the new list
            Object listNameObj = OptionPaneStyle.showInputDialog(null,
                    "Enter the name of the new List:", "New List creation",
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);

            // Check if the user entered a list name
            if (listNameObj != null) {
                // Convert the object to a string and trim leading and trailing spaces
                String listName = listNameObj.toString().trim();

                // While the list name is empty, ask the user to enter a new one
                while (listName.isEmpty()) {
                    // Display an error message indicating that the list name cannot be empty
                    OptionPaneStyle.showMessageDialog(null, "List name cannot be empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the list name again
                    listNameObj = OptionPaneStyle.showInputDialog(null,
                            "Enter the name of the new list:", "New list creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    listName = listNameObj.toString().trim();
                }

                // Get the names of the existing lists
                ArrayList<String> names = new ArrayList<>();
                for (CardList list : BOARD.getLists()) {
                    names.add(list.getName());
                }

                // While the list name already exists, ask the user to enter a new one
                while (names.contains(listName)) {
                    // Display an error message indicating that the list name already exists
                    OptionPaneStyle.showMessageDialog(null, "list name already exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the list name again
                    listNameObj = OptionPaneStyle.showInputDialog(null,
                            "Enter the name of the new list:", "New list creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    listName = listNameObj.toString().trim();
                }

                // Create the new list with the user-provided name
                CardList list = new CardList(listName);
                BOARD.addList(list);
                BOARDVIEW.update(BOARD);
            }
        }

        /**
         * Check if the name is already used in the BOARD
         *
         * @param name String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         */
        private boolean isNameUsed(String name) {
            for (Board board : WORKSPACE.getBoards()) {
                if (board.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }
}
