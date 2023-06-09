package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.TrelloMain;
import trellolite.model.Participant;
import trellolite.model.Workspace;
import trellolite.style.ButtonStyle;
import trellolite.style.ComboBoxStyle;
import trellolite.style.PanelStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is a JPanel that displays the workspace manager.
 * <p>
 * It is used in the MainView class.
 * <br>
 * This class extends the PanelStyle class.
 * <br>
 * This class contains the following methods:
 * <ul>
 *     <li>ManagerView(int width, int height, LayoutManager layout)</li>
 *     <li>ComboBoxStyle createWorkspaceComboBox()</li>
 *     <li>ButtonStyle createNewWorkspaceButton()</li>
 *     <li>ButtonStyle createDeleteWorkspaceButton()</li>
 *     <li>ComboBoxStyle createActionsComboBox()</li>
 *     <li>void updateWorkspaceComboBox()</li>
 *     </ul>
 * <br>
 *     This class contains the following attributes:
 * <ul>
 *     <li>ComboBoxStyle workspaceComboBox</li>
 *     <li>ButtonStyle newWorkspaceButton</li>
 *     <li>ButtonStyle deleteWorkspaceButton</li>
 *     <li>ComboBoxStyle actionsComboBox</li>
 * </ul>
 * <br>
 *
 * @author Pierre Fromont Boissel
 * @see PanelStyle
 * @see trellolite.TrelloMain
 * @see trellolite.model.Workspace
 * @see trellolite.model.WorkspaceManager
 * @see ComboBoxStyle
 * @see ButtonStyle
 */
public class ManagerView extends PanelStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------

    private ComboBoxStyle workspaceComboBox;
    private ButtonStyle newWorkspaceButton;
    private ButtonStyle deleteWorkspaceButton;
    private ComboBoxStyle actionsComboBox;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the workspace combo box.
     *
     * @return ComboBoxStyle, the workspace combo box.
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     */
    public ComboBoxStyle getWorkspaceComboBox() {
        return workspaceComboBox;
    }

    /**
     * This method returns the new workspace button.
     *
     * @return ButtonStyle, the new workspace button.
     * @author Pierre Fromont Boissel
     * @see ButtonStyle
     */
    public ButtonStyle getNewWorkspaceButton() {
        return newWorkspaceButton;
    }

    /**
     * This method returns the deleted workspace button.
     *
     * @return ButtonStyle, the delete workspace button.
     * @author Pierre Fromont Boissel
     * @see ButtonStyle
     */
    public ButtonStyle getDeleteWorkspaceButton() {
        return deleteWorkspaceButton;
    }

    /**
     * This method returns the action combo box.
     *
     * @return ComboBoxStyle, the action combo box.
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     */
    public ComboBoxStyle getActionsComboBox() {
        return actionsComboBox;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    public ManagerView() {
        super(240, 150, new GridLayout(3, 1, 0, 10));

        // create the workspace combo box
        createWorkspaceComboBox();
        add(workspaceComboBox);
        // create the buttons
        PanelStyle buttonsPanel = new PanelStyle(240, 40,
                new GridLayout(1, 2, 10, 0));
        createNewWorkspaceButton();
        buttonsPanel.add(newWorkspaceButton);
        createDeleteWorkspaceButton();
        buttonsPanel.add(deleteWorkspaceButton);
        add(buttonsPanel);
        // create the action combo box
        createActionsComboBox();
        add(actionsComboBox);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method creates the workspace combo box.
     *
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     * @see JComboBox
     */
    private void createWorkspaceComboBox() {
        ArrayList<String> workspaceNames = new ArrayList<>();

        for (Workspace workspace : TrelloMain.workspaceManager.getWorkspaces()) {
            for (Participant participant : workspace.getMembers()) {
                if (participant.equals(TrelloMain.currentParticipant)) {
                    workspaceNames.add(workspace.getName());
                }
            }
        }
        //workspaceComboBox.setModel(new DefaultComboBoxModel<String>((String[]) workspaceNames.toArray()));

        int comboBoxWidth = 240;
        int comboBoxHeight = 40;
        int comboBoxFontSize = 16;
        workspaceComboBox = new ComboBoxStyle(workspaceNames.toArray(new String[0]),
                comboBoxWidth, comboBoxHeight, comboBoxFontSize);
    }

    /**
     * This method updates the workspace combo box.
     *
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     * @see JComboBox
     */
    public void updateWorkspaceComboBox() {
        System.out.println("updateWorkspaceComboBox");
        workspaceComboBox.removeAllItems();
        for (Workspace workspace : TrelloMain.workspaceManager.getWorkspaces()) {
            for (Participant participant : workspace.getMembers()) {
                if (participant.equals(TrelloMain.currentParticipant)) {
                    workspaceComboBox.addItem(workspace.getName());
                }
            }
        }
    }

    /**
     * This method creates the new workspace button.
     *
     * @author Pierre Fromont Boissel
     * @see ButtonStyle
     * @see JButton
     */
    private void createNewWorkspaceButton() {
        int buttonWidth = 115;
        int buttonHeight = 40;
        int buttonFontSize = 16;
        newWorkspaceButton = new ButtonStyle("New", buttonWidth, buttonHeight, buttonFontSize);
    }

    /**
     * This method creates the deleted workspace button.
     *
     * @author Pierre Fromont Boissel
     * @see ButtonStyle
     * @see JButton
     */
    private void createDeleteWorkspaceButton() {
        int buttonWidth = 115;
        int buttonHeight = 40;
        int buttonFontSize = 16;
        deleteWorkspaceButton = new ButtonStyle("Delete", buttonWidth, buttonHeight, buttonFontSize);
    }

    /**
     * This method creates the action combo box.
     *
     * @author Pierre Fromont Boissel
     * @see ComboBoxStyle
     * @see JComboBox
     */
    private void createActionsComboBox() {
        String[] actions = {"Rename the workspace", "Add a Member", "Remove a Member", "Add a Board"};
        int comboBoxWidth = 240;
        int comboBoxHeight = 40;
        int comboBoxFontSize = 16;
        actionsComboBox = new ComboBoxStyle(actions, comboBoxWidth, comboBoxHeight, comboBoxFontSize);
    }
}
