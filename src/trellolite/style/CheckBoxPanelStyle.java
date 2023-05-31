package trellolite.style;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CheckBoxPanelStyle extends PanelStyle {

    public CheckBoxPanelStyle(ArrayList<String> fields) {
        super(new FlowLayout(FlowLayout.CENTER));
        for (String item : fields) {
            JCheckBox checkBox = new JCheckBox(item);
            checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(checkBox);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ArrayList<String> fields = new ArrayList<String>();
                fields.add("Field 1");
                fields.add("Field 2");
                fields.add("Field 3");
                fields.add("Field 4");
                fields.add("Field 5");
                CheckBoxPanelStyle panel = new CheckBoxPanelStyle(fields);
                JFrame frame = new JFrame("CheckBoxPanelStyle");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }
}
