package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

/**
 * This class is a JComboBox with a custom style.
 * <p>
 * It is used in the ManagerView class.
 * This class extends the JComboBox class.
 * </p>
 *
 * @author Pierre Fromont Boissel
 * @see JComboBox
 */
public class ComboBoxStyle extends JComboBox<String> implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a new ComboBoxStyle object.
     *
     * @param items,  String[], the items of the combo box.
     * @param width,  int, the width of the combo box.
     * @param height, int, the height of the combo box.
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     */
    public ComboBoxStyle(String[] items, int width, int height) {
        super(items);
        setModel(new DefaultComboBoxModel<>(items));
        setRenderer(new CustomComboBoxRenderer());
        setEditor(new CustomComboBoxEditor());
        setUI(new CustomComboBoxUI());
        setBackground(BACKGROUND_COLOR);
        setForeground(TEXT_COLOR);
        setFont(new Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * This constructor creates a new ComboBoxStyle object.
     *
     * @param items,    String[], the items of the combo box.
     * @param width,    int, the width of the combo box.
     * @param height,   int, the height of the combo box.
     * @param fontSize, int, the font size of the combo box items.
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     */
    public ComboBoxStyle(String[] items, int width, int height, int fontSize) {
        super(items);
        setModel(new DefaultComboBoxModel<>(items));
        setRenderer(new CustomComboBoxRenderer());
        setEditor(new CustomComboBoxEditor());
        setUI(new CustomComboBoxUI());
        setBackground(BACKGROUND_COLOR);
        setForeground(TEXT_COLOR);
        setFont(new Font(TEXT_FONT, TEXT_FONT_STYLE, fontSize));
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        setPreferredSize(new Dimension(width, height));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // PRIVATE CLASSES
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is a custom renderer for the JComboBox.
     * <p>
     * This class is a custom renderer for the JComboBox. It is used in the ComboBoxStyle class.
     * This class extends the DefaultListCellRenderer class.
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see DefaultListCellRenderer
     * @see ComboBoxStyle
     * @see trellolite.style.MyStyle
     */
    private static class CustomComboBoxRenderer extends DefaultListCellRenderer {
        /**
         * {@inheritDoc}
         */
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setBackground(isSelected ? COMPONENT_ACCENTUATION : BACKGROUND_COLOR);
            setForeground(TEXT_COLOR);
            setFont(new Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
            return this;
        }
    }

    /**
     * This class is a custom editor for the JComboBox.
     * <p>
     * This class is a custom editor for the JComboBox. It is used in the ComboBoxStyle class.
     * This class extends the BasicComboBoxEditor class.
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see BasicComboBoxEditor
     * @see ComboBoxStyle
     * @see trellolite.style.MyStyle
     */
    private static class CustomComboBoxEditor extends BasicComboBoxEditor {
        /**
         * {@inheritDoc}
         */
        @Override
        public Component getEditorComponent() {
            JTextField editor = (JTextField) super.getEditorComponent();
            editor.setBackground(BACKGROUND_COLOR);
            editor.setForeground(TEXT_COLOR);
            editor.setFont(new Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
            editor.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
            return editor;
        }
    }

    /**
     * This class is a custom UI for the JComboBox.
     * <p>
     * This class is a custom UI for the JComboBox. It is used in the ComboBoxStyle class.
     * This class extends the BasicComboBoxUI class.
     * </p>
     * <p>
     * It is used to change the style of the arrow button.
     * The arrow button is the button that is used to open the list of the JComboBox.
     * It is the button with the arrow on the right of the JComboBox.
     * The arrow button is a JButton.
     * The arrow button is a private class in the BasicComboBoxUI class.
     * The arrow button is created in the createArrowButton() method of the BasicComboBoxUI class.
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see BasicComboBoxUI
     * @see ComboBoxStyle
     * @see trellolite.style.MyStyle
     * @see javax.swing.JButton
     * @see javax.swing.plaf.basic.BasicComboBoxUI
     * @see javax.swing.plaf.basic.BasicComboBoxUI#createArrowButton()
     */
    private static class CustomComboBoxUI extends BasicComboBoxUI {
        /**
         * {@inheritDoc}
         */
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = super.createArrowButton();
            arrowButton.setBackground(BUTTON_COLOR);
            arrowButton.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER_COLOR));
            arrowButton.setContentAreaFilled(false);
            arrowButton.setFocusPainted(false);
            arrowButton.setOpaque(true);
            arrowButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    arrowButton.setBackground(BUTTON_HOVER);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    arrowButton.setBackground(BUTTON_COLOR);
                }
            });
            return arrowButton;
        }
    }
}