package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is used to create a button with a custom style.
 * <p>
 * It extends the JButton class.
 * It implements the MyStyle interface.
 * We add an event listener to change the background color of the button when the mouse is over the button.
 * </p>
 * @author Pierre Fromont Boissel
 * @see java.awt.event.MouseListener
 * @see java.awt.event.MouseAdapter
 * @see JButton
 * @see MyStyle
 */
public class ButtonStyle extends JButton implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a button with a custom style.
     * <p>
     * It sets the text of the button.
     * It sets the width and the height of the button.
     * It sets the background color of the button.
     * It sets the foreground color of the button.
     * It sets the border of the button.
     * It sets the font of the button.
     * It adds an event listener to change the background color of the button when the mouse is over the button.
     * </p>
     *
     * @param text,   String,  the text of the button
     * @param width,  int,  the width of the button
     * @param height, int, the height of the button
     * @author Pierre Fromont Boissel
     * @see ButtonStyle
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseAdapter
     * @see JButton
     * @see MyStyle
     */
    public ButtonStyle(String text, int width, int height) {
        super(text);
        setBackground(BUTTON_COLOR);
        setForeground(TEXT_COLOR);
        setPreferredSize(new java.awt.Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        setFocusPainted(false);
        setFont(new Font(TEXT_FONT, Font.PLAIN, TEXT_FONT_SIZE));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(BACKGROUND_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(BUTTON_COLOR);
            }
        });
    }

    /**
     * This constructor creates a button with a custom style.
     * <p>
     * It sets the text of the button.
     * It sets the width and the height of the button.
     * It sets the background color of the button.
     * It sets the foreground color of the button.
     * It sets the border of the button.
     * It sets the font of the button.
     * It adds an event listener to change the background color of the button when the mouse is over the button.
     * </p>
     *
     * @param text,     String,  the text of the button
     * @param width,    int,  the width of the button
     * @param height,   int, the height of the button
     * @param fontSize, int, the font size of the button text
     * @see ButtonStyle
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseAdapter
     * @see JButton
     * @see MyStyle
     */
    public ButtonStyle(String text, int width, int height, int fontSize) {
        super(text);
        setBackground(BUTTON_COLOR);
        setForeground(TEXT_COLOR);
        setPreferredSize(new java.awt.Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        setFocusPainted(false);
        setFont(new Font(TEXT_FONT, Font.PLAIN, fontSize));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(BACKGROUND_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                setBackground(BUTTON_COLOR);
            }
        });
    }
}
