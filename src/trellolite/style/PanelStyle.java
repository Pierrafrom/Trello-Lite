package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 * This class represents the style of the panels.
 * This class implements the MyStyle interface.
 * This class extends the JPanel class.
 *
 * @author Pierre Fromont Boissel
 * @see MyStyle
 * @see JPanel
 * @see LayoutManager
 */
public class PanelStyle extends JPanel implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a panel with a specific style.
     * Constructor with default layout.
     *
     * @param width  int, this is the width of the panel.
     * @param height int, this is the height of the panel.
     * @param layout LayoutManager, this is the layout of the panel.
     * @author Pierre Fromont Boissel
     * @see LayoutManager
     * @see JPanel
     * @see MyStyle
     */
    public PanelStyle(int width, int height, LayoutManager layout) {
        super();
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new java.awt.Dimension(width, height));
        setLayout(layout);
    }

    /**
     * This constructor creates a panel with a specific style.
     * Constructor with default layout.
     *
     * @param layout LayoutManager, this is the layout of the panel.
     * @see LayoutManager
     * @see JPanel
     * @see MyStyle
     */
    public PanelStyle(LayoutManager layout) {
        super();
        setBackground(BACKGROUND_COLOR);
        setLayout(layout);
    }
}
