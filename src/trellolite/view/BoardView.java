package trellolite.view;

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

    private final String[] actions = {"Add a list", "Delete the board", "Change the name of the board"};
    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private Board board;
    private ComboBoxStyle actionsComboBox;
    private final PanelStyle topPanel;

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
    public BoardView(Board board) {
        super(new BorderLayout());
        this.board = board;

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
        // remove the title Panel
        topPanel.remove(0);
        // Create the title Panel
        topPanel.add(createTitlePanel(), BorderLayout.CENTER);
        // Remove the content Panel
        remove(1);
        // Create the content Panel
        createContentPanel();
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
            CardListController controller = new CardListController(board, board.getList(i),this, listView);
            // Add the list view to the content panel
            contentPanel.add(listView);
        }
        // Create the scroll pane and add the content panel to it
        ScrollPaneStyle scrollPane = new ScrollPaneStyle(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
