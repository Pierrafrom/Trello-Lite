package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.TrelloMain;
import trellolite.model.Board;
import trellolite.model.CardList;
import trellolite.style.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is the controller of the card creator view. It allows the user to
 * create a new card by filling the fields with the view. The user can also cancel
 * the creation of the card.
 *
 * @author Pierre Fromont Boissel
 * @see CardCreatorView
 */
public class CardCreatorView extends JFrame {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private boolean cancelled = true;

    private CheckBoxPanelStyle participantsPanel;
    private CheckBoxPanelStyle cardsPanel;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JTextField dueDateField;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns a boolean to know if the user canceled the creation of
     * the card or not.
     *
     * @return boolean, true if the user canceled the creation of the card, false
     * otherwise.
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     */
    public boolean isCancelled() {
        return cancelled;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a new card creator controller. It creates the view
     * and adds the different components to it.
     */
    public CardCreatorView() {
        super("Create a new card");
        setLayout(new BorderLayout());
        setSize(450, 650);
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

    /**
     * This method creates the top panel of the view. It contains the name field of
     * the card.
     *
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     */
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

    /**
     * This method creates the center panel of the view. It contains the description
     * field of the card. It also contains the participants panel and the cards
     * panel.
     *
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     * @see CheckBoxPanelStyle
     * @see CardList
     * @see Board
     */
    private void createCenterPanel() {
        PanelStyle centerPanel = new PanelStyle(new GridLayout(3, 2));
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
        ArrayList<Object> fields = new ArrayList<>();
        if (TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getMembers().size() == 0) {
            participantsLabel.setText("No participants to add");
        } else {

            fields.addAll(TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex)
                    .getMembers());
        }

        participantsPanel = new CheckBoxPanelStyle(fields);
        participantsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, MyStyle.BORDER_COLOR));
        centerPanel.add(participantsPanel);

        // cards panel
        // get all the cards in the curent Workspace
        ArrayList<Object> cards = new ArrayList<>();
        for (Board board : TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getBoards()) {
            for (CardList cardList : board.getLists()) {
                cards.addAll(cardList.getCards());
            }
        }
        cardsPanel = new CheckBoxPanelStyle(cards);
        cardsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, MyStyle.BORDER_COLOR));
        // cards label
        LabelStyle cardsLabel = new LabelStyle("Link cards :", TextType.TEXT, SwingConstants.CENTER);
        if (cards.size() == 0) {
            cardsLabel.setText("No cards to link");
        }
        cardsLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, MyStyle.BORDER_COLOR));
        centerPanel.add(cardsLabel);
        // add label and panel to the center panel

        centerPanel.add(cardsPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * This method creates the bottom panel of the view. It contains the due date
     * field of the card. It also contains the create and cancel buttons.
     *
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     * @see ButtonStyle
     * @see PanelStyle
     * @see LabelStyle
     */
    private void createBottomPanel() {
        PanelStyle bottomPanel = new PanelStyle(450, 100, new GridLayout(2, 2));
        LabelStyle dueDateLabel = new LabelStyle("Due date (yyyy-MM-dd):", TextType.TEXT, SwingConstants.CENTER);
        dueDateLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, MyStyle.BORDER_COLOR));
        bottomPanel.add(dueDateLabel);
        dueDateField = new JTextField();
        dueDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, MyStyle.BORDER_COLOR));
        bottomPanel.add(dueDateField);
        // create button
        ButtonStyle createButton = new ButtonStyle("Confirm", 0, 0);
        createButton.addActionListener(e -> {
            cancelled = false;
            dispose();
        });
        ButtonStyle cancelButton = new ButtonStyle("Cancel", 0, 0);
        cancelButton.addActionListener(e -> dispose());
        bottomPanel.add(createButton);
        bottomPanel.add(cancelButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * This method returns the name of the card.
     *
     * @return the name of the card
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     */
    public String getNameData() {
        return nameField.getText();
    }

    /**
     * This method returns the description of the card.
     *
     * @return the description of the card
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     */
    public String getDescriptionData() {
        return descriptionField.getText();
    }

    /**
     * This method returns the participants of the card.
     *
     * @return the participants of the card
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     * @see CheckBoxPanelStyle
     */
    public ArrayList<Object> getParticipantsData() {
        return participantsPanel.getSelected();
    }

    /**
     * This method returns the due date of the card.
     *
     * @return the due date of the card in the format yyyy-MM-dd
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     */
    public String getDueDateData() {
        return dueDateField.getText();
    }

    /**
     * This method returns the cards linked to the card.
     *
     * @return the cards linked to the card
     * @author Pierre Fromont Boissel
     * @see CardCreatorView
     * @see CheckBoxPanelStyle
     */
    public ArrayList<Object> getCardsData() {
        return cardsPanel.getSelected();
    }
}
