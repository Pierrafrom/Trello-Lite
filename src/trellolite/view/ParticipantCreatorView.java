package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.style.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the style for a constructor window.
 * It is a pop-up window that allows the user to create a new object.
 *
 * @author Pierre Fromont Boissel
 * @see trellolite.style.MyStyle
 * @see javax.swing.JFrame
 * @see javax.swing.SwingUtilities
 */
public class ParticipantCreatorView extends JFrame {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final PanelStyle mainPanel;
    protected boolean cancelled = true;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------
    public boolean isCancelled() {
        return cancelled;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the ParticipantConstructor.
     * It is a pop-up window that allows the user to create a new object.
     *
     * @param title,            String, the title of the window.
     * @param width,            int, the width of the window.
     * @param height,           int, the height of the window.
     * @param fields,           ArrayList<String>, the fields to display.
     * @param comboBoxes,       ArrayList<ComboBoxStyle>, the comboBoxes to
     *                          ,display.
     * @param comboBoxesLabels, ArrayList<String>, the labels of the
     *                          comboBoxes.
     * @author Pierre Fromont Boissel
     * @example <pre>{@code
     * SwingUtilities.invokeLater(new Runnable() {
     *      public void run() {
     *
     *          // fields
     *          ArrayList<String> fields = new ArrayList<>();
     *          fields.add("Field 1:");
     *          fields.add("Field 2:");
     *          fields.add("Field 3:");
     *
     *          // comboBoxes
     *          ArrayList<ComboBoxStyle> comboBoxes = new
     *          ArrayList<ComboBoxStyle>();
     *          comboBoxes.add(new ComboBoxStyle(new String[] { "Choice 1", "Choice
     *          2", "Choice 3" }, 0, 0));
     *          comboBoxes.add(new ComboBoxStyle(new String[] { "Choice 1", "Choice
     *          2", "Choice 3" }, 0, 0));
     *          ArrayList<String> comboBoxesLabels = new ArrayList<String>();
     *          comboBoxesLabels.add("ComboBox 1:");
     *          comboBoxesLabels.add("ComboBox 2:");
     *
     *          new ParticipantConstructor("object constructor", 400, 300, fields, comboBoxes, comboBoxesLabels);
     *      }
     *  });
     * }</pre>
     * @see javax.swing.SwingUtilities
     * @see javax.swing.JFrame
     * @see trellolite.style.PanelStyle
     * @see trellolite.style.LabelStyle
     * @see trellolite.style.TextType
     * @see trellolite.style.ButtonStyle
     * @see trellolite.style.ComboBoxStyle
     * @see java.awt.GridLayout
     * @see java.util.ArrayList
     * @see java.lang.String
     */
    public ParticipantCreatorView(String title, int width, int height, ArrayList<String> fields,
                                  ArrayList<ComboBoxStyle> comboBoxes, ArrayList<String> comboBoxesLabels) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // main panel
        mainPanel = new PanelStyle(new GridLayout(fields.size() + comboBoxes.size() + 1, 2));
        for (String field : fields) {
            mainPanel.add(new LabelStyle(field, TextType.TEXT));
            mainPanel.add(new JTextField());
        }
        for (int i = 0; i < comboBoxes.size(); i++) {
            mainPanel.add(new LabelStyle(comboBoxesLabels.get(i), TextType.TEXT));
            mainPanel.add(comboBoxes.get(i));
        }
        ButtonStyle confirmButton = new ButtonStyle("Confirm", 0, 0);
        mainPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            cancelled = false;
            dispose();
        });
        ButtonStyle cancelButton = new ButtonStyle("Cancel", 0, 0);
        cancelButton.addActionListener(e -> dispose());
        mainPanel.add(cancelButton);
        add(mainPanel);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the content of the fields.
     *
     * @return fieldValues: ArrayList<String>, the content of the fields.
     * @author Pierre Fromont Boissel
     * @see java.awt.Component
     * @see javax.swing.JTextField
     * @see java.util.ArrayList
     * @see java.lang.String
     */
    public ArrayList<String> getFieldsContent() {
        ArrayList<String> fieldValues = new ArrayList<>();

        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JTextField textField) {
                String fieldValue = textField.getText();
                fieldValues.add(fieldValue);
            }
        }

        return fieldValues;
    }

    /**
     * This method returns the content of the comboBoxes.
     *
     * @return selectedValues: ArrayList<Object>, the content of the comboBoxes.
     * @author Pierre Fromont Boissel
     * @see java.awt.Component
     * @see trellolite.style.ComboBoxStyle
     * @see java.util.ArrayList
     * @see java.lang.Object
     * @see java.lang.String
     */
    public ArrayList<Object> getComboBoxesContent() {
        ArrayList<Object> selectedValues = new ArrayList<>();

        for (Component component : mainPanel.getComponents()) {
            if (component instanceof ComboBoxStyle comboBox) {
                Object selectedValue = comboBox.getSelectedItem();
                selectedValues.add(selectedValue);
            }
        }

        return selectedValues;
    }
}
