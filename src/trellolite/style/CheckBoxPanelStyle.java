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
 * This class is a panel with a list of check boxes.
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
    private ArrayList<Object> fields;
    private PanelStyle panel;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the CheckBoxPanelStyle class.
     * 
     * <br>
     * This constructor creates a panel with a size, a layout and a background color.
     * <br>
     * It displays a list of check boxes.
     * 
     * @param fields, ArrayList<Object>, the fields to display in the check boxes.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Object  
     * @see PanelStyle
     */
    public CheckBoxPanelStyle(ArrayList<Object> fields) {
        super(new BorderLayout());
        this.fields = fields;
        setPreferredSize(new Dimension(200, 200));
        panel = new PanelStyle(200, 30 * fields.size(), new GridLayout(fields.size(), 1));
        for (Object item : fields) {
            JCheckBox checkBox = new JCheckBox(item.toString());
            checkBox.setOpaque(false);
            checkBox.setForeground(TEXT_COLOR);
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            checkBox.setFont(new Font(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE));
            panel.add(checkBox);
        }

        ScrollPaneStyle scrollPane = new ScrollPaneStyle(panel,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the selected fields.
     * 
     * @return ArrayList<Object>, the selected fields.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Object
     */
    public ArrayList<Object> getSelected() {
        ArrayList<Object> selected = new ArrayList<Object>();
        for (int i = 0; i < fields.size(); i++) {
            JCheckBox checkBox = (JCheckBox) panel.getComponent(i);
            if (checkBox.isSelected()) {
                selected.add(fields.get(i));
            }
        }
        return selected;
    }

    /* test main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ArrayList<Object> fields = new ArrayList<Object>();
                fields.add(new Participant("Pierre", "Fromont Boissel", "pierrefromont@outlook.fr", Role.ADMIN));
                fields.add(new Participant("John", "Doe", "pierrefromont@outlook.fr", Role.MEMBER));
                fields.add(new Participant("Jane", "Smith", "pierrefromont@outlook.fr", Role.MEMBER));
                fields.add(new Participant("Alice", "Johnson", "pierrefromont@outlook.fr", Role.MEMBER));
                fields.add(new Participant("Bob", "Anderson", "pierrefromont@outlook.fr", Role.MEMBER));
                fields.add(new Participant("Pierre", "Fromont Boissel", "pierrefromont@outlook.fr", Role.ADMIN));
                fields.add(new Participant("John", "Doe", "pierrefromont@outlook.fr", Role.MEMBER));
                fields.add(new Participant("Jane", "Smith", "jane@", Role.MEMBER));
                fields.add(new Participant("Alice", "Johnson", "alice@", Role.MEMBER));
                fields.add(new Participant("Bob", "Anderson", "bob@", Role.MEMBER));

                CheckBoxPanelStyle panel = new CheckBoxPanelStyle(fields);
                JFrame frame = new JFrame("CheckBoxPanelStyle");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                // recuperer les participants selectionnes a la fermeture de la fenetre
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        ArrayList<Object> selected = panel.getSelected();
                        for (Object item : selected) {
                            System.out.println(item);
                        }
                    }
                });
            }
        });
    } */
}
