package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------
import trellolite.model.Workspace;
import trellolite.style.TabbedPaneStyle;

/**
 * This class is used to display the boards of a workspace.
 *
 * @author Pierre Fromont Boissel
 * @see trellolite.model.Board
 * @see trellolite.model.Workspace
 * @see trellolite.view.BoardView
 * @see trellolite.style.TabbedPaneStyle
 * @see javax.swing.JTabbedPane
 */
public class WorkspaceView extends TabbedPaneStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private Workspace workspace;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor is used to display the boards of a workspace.
     *
     * @param workspace, Workspace, the workspace to display.
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Board
     * @see trellolite.model.Workspace
     * @see trellolite.view.BoardView
     * @see trellolite.style.TabbedPaneStyle
     * @see javax.swing.JTabbedPane
     */
    public WorkspaceView(Workspace workspace) {
        super();
        this.workspace = workspace;
        // Add the boards to the tabbed pane
        for (int i = 0; i < workspace.getBoards().size(); i++) {
            // create a board view
            BoardView boardView = new BoardView(workspace.getBoards().get(i), this);
            // add the board to the tabbed pane
            addTab(workspace.getBoards().get(i).getName(), boardView);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method is used to update the tabbed pane.
     * <br>
     * It will be used to update the tabbed pane when a board is added or removed.
     * <br>
     *
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Board
     * @see trellolite.model.Workspace
     * @see trellolite.view.BoardView
     * @see trellolite.style.TabbedPaneStyle
     * @see javax.swing.JTabbedPane
     */
    public void update() {
        // Remove all the tabs
        removeAll();
        // Add the boards to the tabbed pane
        for (int i = 0; i < workspace.getBoards().size(); i++) {
            // create a board view
            BoardView boardView = new BoardView(workspace.getBoards().get(i), this);
            // add the board to the tabbed pane
            addTab(workspace.getBoards().get(i).getName(), boardView);
        }
    }

    /**
     * This method is used to change the workspace.
     * <br>
     * It will be used to change the workspace when the user changes the workspace.
     * <br>
     *
     * @param workspace, Workspace, the workspace to display.
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Board
     * @see trellolite.model.Workspace
     * @see trellolite.view.BoardView
     * @see trellolite.style.TabbedPaneStyle
     * @see javax.swing.JTabbedPane
     */
    public void changeWorkspace(Workspace workspace) {
        this.workspace = workspace;
        update();
    }
}

