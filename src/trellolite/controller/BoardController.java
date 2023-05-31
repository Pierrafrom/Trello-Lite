package trellolite.controller;

import trellolite.TrelloMain;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.model.Workspace;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import trellolite.view.WorkspaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

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

    private class ActionComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (actionComboBox.getSelectedIndex()) {
                case 0 ->
                    // Add a new list
                        createNewList();
                case 1 -> {
                    // Delete the Board
                    // Ask for confirmation
                    ConfirmationDialog dialogController = new ConfirmationDialog();
                    String message = "Are you sure you want to delete the board : " + board.getName() + " ?";
                    String title = "Confirmation";
                    boolean result = dialogController.showConfirmationDialog(message, title);
                    int id=board.getId();

                    // If the user selected "Yes"
                    if (result) {
                        // TODO: Delete the Board
                        // Delete the Board model from the Workspace model
                        // update the Workspace view
                        workspace.getBoards().remove(board);
                        boardView.update(board);
                        System.out.println("Delete the Board");
                    }
                    
                }
                case 2 ->{
                    // Rename the Board
                        System.out.println("Rename the Board");
                // TODO: Rename the Board
                OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                Object ChangeBoardNameObj = optionPaneStyle.showInputDialog(null,
                    "Enter the new name of the board:", "Board name modification",
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
                String newName = ChangeBoardNameObj.toString().trim();
                board.setName(newName);
                // Rename the Board model
                System.out.println(board.getName());
                // update the Board view
                boardView.update(board);
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
        private void createNewList(){
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
    }
}
