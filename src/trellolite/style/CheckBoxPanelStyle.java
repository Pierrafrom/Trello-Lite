package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

/**
 * This class is a PANEL with a list of check boxes.
 * <br>
 * This class extends PanelStyle.
 * <br>
 * It shows a list of check boxes.
 * 
 * @author Pierre Fromont Boissel
 * @see PanelStyle
 * @see ArrayList
 * @see JCheckBox 
 */
public class CheckBoxPanelStyle extends PanelStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final ArrayList<Object> FIELDS;
    private final PanelStyle PANEL;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the CheckBoxPanelStyle class.
     * 
     * <br>
     * This constructor creates a PANEL with a size, a layout and a background color.
     * <br>
     * It displays a list of check boxes.
     * 
     * @param fields, ArrayList<Object>, the FIELDS to display in the checkboxes.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Object  
     * @see PanelStyle
     */
    public CheckBoxPanelStyle(ArrayList<Object> fields) {
        super(new BorderLayout());
        this.FIELDS = fields;
        setPreferredSize(new Dimension(200, 200));
        PANEL = new PanelStyle(200, 30 * fields.size(), new GridLayout(fields.size(), 1));
        for (Object item : fields) {
            JCheckBox checkBox = new JCheckBox(item.toString());
            checkBox.setOpaque(false);
            checkBox.setForeground(TEXT_COLOR);
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            checkBox.setFont(new Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
            PANEL.add(checkBox);
        }

        ScrollPaneStyle scrollPane = new ScrollPaneStyle(PANEL,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the selected FIELDS.
     * 
     * @return ArrayList<Object>, the selected FIELDS.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Object
     */
    public ArrayList<Object> getSelected() {
        ArrayList<Object> selected = new ArrayList<>();
        for (int i = 0; i < FIELDS.size(); i++) {
            JCheckBox checkBox = (JCheckBox) PANEL.getComponent(i);
            if (checkBox.isSelected()) {
                selected.add(FIELDS.get(i));
            }
        }
        return selected;
    }
}
