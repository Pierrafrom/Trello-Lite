package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the OptionPane style of the application.
 *
 * @author Pierre Fromont Boissel
 * @see trellolite.style.MyStyle
 * @see JOptionPane
 * @see UIManager
 * @see trellolite.style
 */
public class OptionPaneStyle extends JOptionPane implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the OptionPane.
     *
     * @author Pierre Fromont Boissel
     * @see UIManager
     * @see trellolite.style.MyStyle
     * @see trellolite.style
     * @see JOptionPane
     */
    public OptionPaneStyle() {
        super();
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
        UIManager.put("OptionPane.messageFont", new java.awt.Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
        UIManager.put("Button.background", BUTTON_COLOR);
        UIManager.put("Button.foreground", TEXT_COLOR);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
        UIManager.put("OptionPane.messageFont", new java.awt.Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
        UIManager.put("Button.background", BUTTON_COLOR);
        UIManager.put("Button.foreground", TEXT_COLOR);

        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }
}
