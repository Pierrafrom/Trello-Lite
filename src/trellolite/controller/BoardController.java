package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Board;
import trellolite.model.Workspace;
import trellolite.style.ComboBoxStyle;
import trellolite.view.BoardView;
import trellolite.view.WorkspaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                        System.out.println("Add a new list");

                // TODO: Add a new list
                case 1 -> {
                    // Delete the Board
                    // Ask for confirmation
                    ConfirmationDialog dialogController = new ConfirmationDialog();
                    String message = "Are you sure you want to delete the board : " + board.getName() + " ?";
                    String title = "Confirmation";
                    boolean result = dialogController.showConfirmationDialog(message, title);

                    // If the user selected "Yes"
                    if (result) {
                        // TODO: Delete the Board
                        // Delete the Board model from the Workspace model
                        // update the Workspace view
                        System.out.println("Delete the Board");
                    }
                }
                case 2 ->
                    // Rename the Board
                        System.out.println("Rename the Board");

                // TODO: Rename the Board
                // Rename the Board model
                // update the Board view
            }
        }
    }
}
