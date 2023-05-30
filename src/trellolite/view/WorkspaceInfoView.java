package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.TrelloMain;
import trellolite.style.LabelStyle;
import trellolite.style.PanelStyle;
import trellolite.style.TextType;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a JPanel that displays information about the currently selected workspace.
 * <p>
 * This class is a JPanel that displays information about the currently selected workspace. It is used in the
 * WorkspaceView class.
 * <br>
 * This class extends the PanelStyle class.
 * <br>
 * This class contains the following methods:
 * <ul>
 *     <li>WorkspaceInfoView(int width, int height, LayoutManager layout)</li>
 *     <li>PanelStyle createTitlePanel()</li>
 *     <li>PanelStyle createInfoPanel()</li>
 *     <li>void update()</li>
 * </ul>
 * <br>
 *
 * @author Pierre Fromont Boisel
 * @see PanelStyle
 * @see trellolite.TrelloMain
 * @see trellolite.model.Workspace
 * @see trellolite.model.WorkspaceManager
 */
public class WorkspaceInfoView extends PanelStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a new WorkspaceInfoView object.
     *
     * @author Pierre Fromont Boisel
     * @see PanelStyle
     * @see trellolite.TrelloMain
     * @see trellolite.model.Workspace
     * @see trellolite.model.WorkspaceManager
     * @see java.awt.FlowLayout
     * @see java.awt.BorderLayout
     * @see javax.swing.JLabel
     * @see javax.swing.SwingConstants
     */
    public WorkspaceInfoView() {
        super(240, 320, new FlowLayout());
        ((FlowLayout) getLayout()).setVgap(5);

        // Add the title panel
        add(createTitlePanel());
        // Add the info panel
        add(createInfoPanel());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method creates a panel that displays the title of the workspace.
     *
     * @return PanelStyle, the panel that displays the title of the workspace.
     * @author Pierre Fromont Boissel
     * @see PanelStyle
     * @see trellolite.TrelloMain
     * @see trellolite.model.Workspace
     * @see trellolite.model.WorkspaceManager
     * @see java.awt.FlowLayout
     * @see java.awt.BorderLayout
     * @see javax.swing.JLabel
     * @see javax.swing.SwingConstants
     */
    private PanelStyle createTitlePanel() {
        PanelStyle titlePanel = new PanelStyle(240, 60, new BorderLayout());
        LabelStyle titleLabel = new LabelStyle("Trello-Lite", TextType.TITLE, SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        return titlePanel;
    }

    /**
     * This method creates a panel that displays information about the workspace.
     *
     * @return PanelStyle, the panel that displays information about the workspace.
     * @author Pierre Fromont Boissel
     * @see PanelStyle
     * @see trellolite.TrelloMain
     * @see trellolite.model.Workspace
     * @see trellolite.model.WorkspaceManager
     * @see java.awt.FlowLayout
     * @see java.awt.BorderLayout
     * @see javax.swing.JLabel
     * @see javax.swing.SwingConstants
     */
    private PanelStyle createInfoPanel() {
        PanelStyle infoPanel = new PanelStyle(240, 250, new BorderLayout());
        LabelStyle infoLabel = new LabelStyle(
                TrelloMain.workspaceManager.getWorkspaces().get(TrelloMain.selectedWorkspaceIndex).toStringHTML(), TextType.TEXT,
                SwingConstants.CENTER);
        infoPanel.add(infoLabel, BorderLayout.CENTER);
        return infoPanel;
    }

    /**
     * This method updates the information displayed in the panel.
     *
     * @author Pierre Fromont Boissel
     * @see PanelStyle
     * @see trellolite.TrelloMain
     * @see trellolite.model.Workspace
     * @see trellolite.model.WorkspaceManager
     * @see java.awt.FlowLayout
     * @see java.awt.BorderLayout
     * @see javax.swing.JLabel
     * @see javax.swing.SwingConstants
     */
    public void update() {
        PanelStyle infoPanel = new PanelStyle(240, 250, new BorderLayout());
        LabelStyle infoLabel = new LabelStyle(
                TrelloMain.workspaceManager.getWorkspaces().get(TrelloMain.selectedWorkspaceIndex).toStringHTML(), TextType.TEXT,
                SwingConstants.CENTER);
        infoPanel.add(infoLabel, BorderLayout.CENTER);
        remove(1);
        add(infoPanel);
        revalidate();
        repaint();
    }
}
