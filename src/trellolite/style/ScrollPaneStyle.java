package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * This class is a JScrollPane with a custom style.
 * <p>
 * This class implements the MyStyle interface.
 * <br>
 * This class extends the JScrollPane class.
 * <br>
 * This class is used to display a JScrollPane with a custom style.
 * </p>
 *
 * @author Pierre Fromont Boissel
 * @see MyStyle
 * @see JScrollPane
 */
public class ScrollPaneStyle extends JScrollPane implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor is used to create a JScrollPane with a custom style.
     *
     * @param view, Component, the component to display in the scroll pane.
     * @author Pierre Fromont Boissel
     * @see MyStyle
     * @see JScrollPane
     * @see Component
     */
    public ScrollPaneStyle(Component view, int verticalScrollBarPolicy, int horizontalScrollBarPolicy) {
        super(view, verticalScrollBarPolicy, horizontalScrollBarPolicy);
        initializeStyle();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method is used to initialize the style of the scroll pane.
     * <br>
     * This method is called by the constructor.
     * <br>
     *
     * @author Pierre Fromont Boissel
     * @see MyStyle
     * @see JScrollPane
     * @see Component
     */
    private void initializeStyle() {
        // Define the style of the scroll pane
        setBackground(BACKGROUND_COLOR);
        getViewport().setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder());
        setForeground(TEXT_COLOR);

        // Personalize the vertical scroll bar
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setBackground(BACKGROUND_COLOR);
        verticalScrollBar.setForeground(TEXT_COLOR);
        verticalScrollBar.setUI(new MyScrollBarUI());
        verticalScrollBar.setPreferredSize(new Dimension(12, verticalScrollBar.getPreferredSize().height));

        // Personalize the horizontal scroll bar
        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setBackground(BACKGROUND_COLOR);
        horizontalScrollBar.setForeground(TEXT_COLOR);
        horizontalScrollBar.setUI(new MyScrollBarUI());
        horizontalScrollBar.setPreferredSize(new Dimension(horizontalScrollBar.getPreferredSize().width, 12));
    }

    /**
     * This class is used to customize the scroll bar.
     * <br>
     * This class extends the BasicScrollBarUI class.
     * <br>
     *
     * @author Pierre Fromont Boissel
     * @see BasicScrollBarUI
     * @see ScrollPaneStyle
     * @see MyStyle
     * @see JScrollPane
     * @see Component
     * @see JScrollBar
     */
    private static class MyScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            thumbColor = BUTTON_COLOR;
            thumbDarkShadowColor = BUTTON_COLOR;
            thumbHighlightColor = BUTTON_HOVER;
            thumbLightShadowColor = BUTTON_COLOR;
            trackColor = BACKGROUND_COLOR;
            trackHighlightColor = BUTTON_HOVER;
        }
    }
}

