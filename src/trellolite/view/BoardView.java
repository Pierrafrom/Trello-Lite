package trellolite.view;

import trellolite.TrelloMain;
import trellolite.controller.BoardController;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.controller.CardListController;
import trellolite.model.Board;
import trellolite.style.*;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a JPanel that displays a board.
 * <p>
 * It is used in the MainView class.
 * <br>
 * This class extends the PanelStyle class.
 * <br>
 * </p>
 *
 * @author Pierre Fromont Boissel
 * @see trellolite.model.Board
 * @see trellolite.style.PanelStyle
 * @see javax.swing.JPanel
 */
public class BoardView extends PanelStyle {

    private final String[] actions = { "Change the name of the board", "Add a list", "Delete the board" };
    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private Board board;
    private ComboBoxStyle actionsComboBox;
    private final PanelStyle topPanel;
    private WorkspaceView workspaceView;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the actions combo box.
     *
     * @return ComboBoxStyle, the actions combo box.
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     * @see trellolite.model.Board
     * @see trellolite.view.BoardView
     */
    public ComboBoxStyle getActionsComboBox() {
        return actionsComboBox;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor is used to create a BoardView.
     * 
     * @param board         , the board to display.
     * @param workspaceView , the workspace view that contains the board view.
     *                      It is used to create the controller.
     * 
     * @see trellolite.model.Board
     * @see trellolite.view.WorkspaceView
     * @see trellolite.view.BoardView
     * @see trellolite.style.PanelStyle
     * @see trellolite.style.ComboBoxStyle
     * @see trellolite.style.OptionPaneStyle
     * @see trellolite.controller.BoardController
     */
    public BoardView(Board board, WorkspaceView workspaceView) {
        super(new BorderLayout());
        this.board = board;
        this.workspaceView = workspaceView;

        // Create the top Panel
        topPanel = new PanelStyle(1200, 30, new BorderLayout());
        // Create the title Panel
        topPanel.add(createTitlePanel(), BorderLayout.CENTER);
        // Create the actions Panel
        topPanel.add(createActionsPanel(), BorderLayout.EAST);
        // Add the top Panel to the content Panel
        add(topPanel, BorderLayout.NORTH);
        // create the content Panel
        createContentPanel();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method is used to update the board.
     * <br>
     * It will be used to update the board when a list is added or removed.
     * <br>
     *
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Board
     */
    public void update(Board board) {
        this.board = board;
        removeAll(); // Supprime tous les composants de BoardView

        // Recrée les composants
        topPanel.removeAll();
        topPanel.add(createTitlePanel(), BorderLayout.CENTER);
        topPanel.add(createActionsPanel(), BorderLayout.EAST);
        createContentPanel();

        // Ajoute les composants mis à jour à BoardView
        add(topPanel, BorderLayout.NORTH);
        revalidate(); // Revalide le panneau pour mettre à jour la mise en page
        repaint(); // Redessine le panneau pour refléter les changements
    }

    /**
     * This method is used to create the title panel.
     *
     * @return PanelStyle, the title panel of the board.
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Board
     * @see trellolite.view.BoardView
     * @see trellolite.style.PanelStyle
     */
    public PanelStyle createTitlePanel() {
        PanelStyle titlePanel = new PanelStyle(950, 30, new BorderLayout());
        titlePanel.add(new LabelStyle(board.getName(), TextType.TITLE, SwingConstants.CENTER), BorderLayout.CENTER);
        return titlePanel;
    }

    /**
     * This method is used to create the actions panel.
     *
     * @return PanelStyle, the actions panel of the board.
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     * @see trellolite.model.Board
     * @see trellolite.view.BoardView
     * @see trellolite.style.PanelStyle
     */
    public PanelStyle createActionsPanel() {
        PanelStyle actionsPanel = new PanelStyle(250, 30, new BorderLayout());
        actionsComboBox = new ComboBoxStyle(actions, 240, 30, 14);
        actionsPanel.add(actionsComboBox, BorderLayout.CENTER);
        new BoardController(TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex),
                board, workspaceView, this);
        return actionsPanel;
    }

    /**
     * This method is used to create the content panel.
     *
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Board
     * @see trellolite.view.BoardView
     * @see trellolite.style.PanelStyle
     * @see trellolite.view.CardListView
     * @see javax.swing.JScrollPane
     * @see trellolite.style.ScrollPaneStyle
     * @see javax.swing.ScrollPaneConstants
     * @see java.awt.FlowLayout
     * @see java.awt.BorderLayout
     * @see java.awt.Dimension
     */
    public void createContentPanel() {
        // get the width of the content panel
        int width = 0;
        for (int i = 0; i < board.getLists().size(); i++) {
            width += CardListView.WIDTH + 50;
        }
        // Create the content panel
        PanelStyle contentPanel = new PanelStyle(width, 600, new FlowLayout(FlowLayout.LEFT, 50, 50));
        // Add the lists to the content panel
        for (int i = 0; i < board.getLists().size(); i++) {
            // Create the list view
            CardListView listView = new CardListView(board.getList(i));
            // Add the controller to the list view
            new CardListController(board, board.getList(i), this, listView);
            // Add the list view to the content panel
            contentPanel.add(listView);
        }
        // Create the scroll pane and add the content panel to it
        ScrollPaneStyle scrollPane = new ScrollPaneStyle(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
