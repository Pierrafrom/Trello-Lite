package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.JLabel;

/**
 * This class represents the style of the labels.
 * This class implements the MyStyle interface.
 * This class extends the JLabel class.
 *
 * @author Pierre Fromont Boissel
 * @see MyStyle
 * @see JLabel
 */
public class LabelStyle extends JLabel implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a label with a specific style.
     * Constructor with default layout.
     *
     * @param text String, this is the text of the label.
     * @param type TextType, this is the type of the text.
     * @author Pierre Fromont Boissel
     * @see TextType
     * @see JLabel
     * @see MyStyle
     */
    public LabelStyle(String text, TextType type) {
        super(text);
        setForeground(TEXT_COLOR);
        setFont(new java.awt.Font(type.getFontName(), type.getFontStyle(), type.getFontSize()));
    }

    /**
     * This constructor creates a label with a specific style.
     * Constructor with default layout.
     *
     * @param text String, this is the text of the label.
     * @param type TextType, this is the type of the text.
     * @param alignment int, this is the alignment of the text.
     * @see TextType
     * @see JLabel
     * @see MyStyle
     */
    public LabelStyle(String text, TextType type, int alignment) {
        super(text);
        setForeground(TEXT_COLOR);
        setFont(new java.awt.Font(type.getFontName(), type.getFontStyle(), type.getFontSize()));
        setHorizontalAlignment(alignment);
    }
}
