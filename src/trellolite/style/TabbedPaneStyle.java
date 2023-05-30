package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to style the JTabbedPane.
 *
 * @author Pierre Fromont Boissel
 * @see javax.swing.UIManager
 * @see javax.swing.JTabbedPane
 * @see trellolite.style.MyStyle
 * @see trellolite.style.PanelStyle
 * @see trellolite.view.WorkspaceView
 */
public class TabbedPaneStyle extends JTabbedPane implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor is used to style the JTabbedPane.
     * <br>
     * <b>WARNING:</b> This constructor will overwrite the default style of the JTabbedPane.
     * <br>
     *
     * @author Pierre Fromont Boissel
     * @see javax.swing.UIManager
     * @see javax.swing.JTabbedPane
     * @see trellolite.style.MyStyle
     * @see trellolite.style.PanelStyle
     * @see trellolite.view.WorkspaceView
     */
    public TabbedPaneStyle() {
        super();
        setBackground(BUTTON_HOVER);
        setForeground(TEXT_COLOR);
        setBorder(null);
        // Change the color of the tab
        UIManager.put("TabbedPane.selected", BACKGROUND_COLOR);
        UIManager.put("TabbedPane.borderHighlightColor", BORDER_COLOR);
        UIManager.put("TabbedPane.darkShadow", BORDER_COLOR);
        // Change the color of the tab area
        UIManager.put("TabbedPane.contentAreaColor", BACKGROUND_COLOR);
        setFont(new java.awt.Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
    }
}