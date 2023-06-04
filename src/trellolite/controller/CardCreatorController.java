package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------
import trellolite.style.PanelStyle;
import trellolite.style.ScrollPaneStyle;
import trellolite.style.TextType;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import trellolite.TrelloMain;
import trellolite.style.ButtonStyle;
import trellolite.style.CheckBoxPanelStyle;
import trellolite.style.LabelStyle;
import trellolite.style.MyStyle;

/**
 * This class is the controller of the card creator view. It allows the user to
 * create a new card by filling the fields of the view. The user can also cancel
 * the creation of the card.
 * 
 * @author Pierre Fromont Boissel
 * @see CardCreatorView
 * @see CardController
 * @see CardView
 */
public class CardCreatorController extends JFrame {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private boolean cancelled = true;
    private ButtonStyle createButton;
    private ButtonStyle cancelButton;

    private CheckBoxPanelStyle participantsPanel;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JTextField dueDateField;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns a boolean to know if the user cancelled the creation of
     * the card or not.
     * 
     * @return boolean, true if the user cancelled the creation of the card, false
     *         otherwise.
     * @author Pierre Fromont Boissel
     * @see CardCreatorController
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * This method returns the create button.
     * 
     * @return ButtonStyle, the create button of the card creator controller.
     * @author Pierre Fromont Boissel
     * @see CardCreatorController
     * @see ButtonStyle
     */
    public ButtonStyle getCreateButton() {
        return createButton;
    }

    /**
     * This method returns the cancel button.
     * 
     * @return ButtonStyle, the cancel button of the card creator controller.
     * @author Pierre Fromont Boissel
     * @see CardCreatorController
     * @see ButtonStyle
     */
    public ButtonStyle getCancelButton() {
        return cancelButton;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a new card creator controller. It creates the view
     * and adds the different components to it.
     * 
     * 
     */
    public CardCreatorController() {
        super("Create a new card");
        setLayout(new BorderLayout());
        setSize(450, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        // top panel
        createTopPanel();

        // center panel
        createCenterPanel();

        // bottom panel
        createBottomPanel();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    private void createTopPanel() {
        PanelStyle topPanel = new PanelStyle(450, 50, new GridLayout(1, 2));
        LabelStyle nameLabel = new LabelStyle("Name :", TextType.TEXT, SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(MyStyle.BORDER_COLOR, 2));
        topPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, MyStyle.BORDER_COLOR));
        topPanel.add(nameField);
        add(topPanel, BorderLayout.NORTH);
    }

    private void createCenterPanel() {
        PanelStyle centerPanel = new PanelStyle(new GridLayout(2, 2));
        // description label
        LabelStyle descriptionLabel = new LabelStyle("Description :", TextType.TEXT, SwingConstants.CENTER);
        descriptionLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, MyStyle.BORDER_COLOR));
        centerPanel.add(descriptionLabel);
        // description text area
        descriptionField = new JTextArea();
        descriptionField.setLineWrap(true); // set word wrap
        descriptionField.setWrapStyleWord(true); // wrap line at word boundary
        descriptionField.setFont(new Font(MyStyle.TEXT_FONT, Font.PLAIN, TextType.LONG_TEXT_FONT_SIZE));
        descriptionField.setOpaque(true); // set textArea opaque
        descriptionField.setForeground(MyStyle.BACKGROUND_COLOR); // set text color
        descriptionField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2,
                MyStyle.BORDER_COLOR));
        // add it to a scrollPane
        ScrollPaneStyle descriptionContentScrollPane = new ScrollPaneStyle(descriptionField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.add(descriptionContentScrollPane);

        // participants label
        LabelStyle participantsLabel = new LabelStyle("Participants :", TextType.TEXT, SwingConstants.CENTER);
        participantsLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, MyStyle.BORDER_COLOR));
        centerPanel.add(participantsLabel);
        // participants panel
        ArrayList<Object> fields = new ArrayList<Object>();
        if (TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getMembers().size() == 0) {
            participantsLabel.setText("No participants to add");
        } else {

            for (Object obj : TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex)
                    .getMembers()) {
                fields.add(obj);
            }
        }

        participantsPanel = new CheckBoxPanelStyle(fields);
        participantsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, MyStyle.BORDER_COLOR));
        centerPanel.add(participantsPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        PanelStyle bottomPanel = new PanelStyle(450, 100, new GridLayout(2, 2));
        LabelStyle dueDateLabel = new LabelStyle("Due date (yyyy-MM-dd):", TextType.TEXT, SwingConstants.CENTER);
        dueDateLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, MyStyle.BORDER_COLOR));
        bottomPanel.add(dueDateLabel);
        dueDateField = new JTextField();
        dueDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, MyStyle.BORDER_COLOR));
        bottomPanel.add(dueDateField);
        // create button
        createButton = new ButtonStyle("Confirm", 0, 0);
        createButton.addActionListener(e -> {
            cancelled = false;
            dispose();
        });
        cancelButton = new ButtonStyle("Cancel", 0, 0);
        cancelButton.addActionListener(e -> {
            dispose();
        });
        bottomPanel.add(createButton);
        bottomPanel.add(cancelButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public String getNameData() {
        return nameField.getText();
    }

    public String getDescriptionData() {
        return descriptionField.getText();
    }

    public ArrayList<Object> getParticipantsData() {
        return participantsPanel.getSelected();
    }

    public String getDueDateData() {
        return dueDateField.getText();
    }
}
