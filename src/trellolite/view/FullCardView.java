package trellolite.view;

import trellolite.controller.FullCardController;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.style.*;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the view of a CARD.
 * <br>
 * This class extends JFrame.
 * <br>
 * It shows the name, the description, the due date and the labels of a CARD.
 *
 * @author Glen Denoual
 * @see Card
 * @see PanelStyle
 * @see LabelStyle
 * @see ButtonStyle
 * @see TextType
 * @see MyStyle
 * @see java.awt.BorderLayout
 * @see java.awt.GridLayout
 * @see javax.swing.JFrame
 */
public class FullCardView extends JFrame {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private ButtonStyle modifyButton;
    private ButtonStyle deleteButton;
    private final Card CARD;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    public ButtonStyle getModifyButton() {
        return modifyButton;
    }

    public ButtonStyle getDeleteButton() {
        return deleteButton;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the FullCardView class.
     *
     * <br>
     * This constructor creates a JFrame with a title, a size, a default close operation, a visibility, a resizable
     * status and a location.
     * <br>
     * It displays the CARD in a JFrame.
     *
     * @param card, Card, the CARD to display
     * @author Glen Denoual
     * @see Card
     * @see JFrame
     * @see java.awt.BorderLayout
     * @see java.awt.GridLayout
     * @see javax.swing.JFrame
     * @see javax.swing.JLabel
     * @see javax.swing.SwingConstants
     * @see trellolite.style.PanelStyle
     * @see trellolite.style.LabelStyle
     * @see trellolite.style.TextType
     * @see trellolite.style.MyStyle
     * @see trellolite.style.ButtonStyle
     * @see trellolite.style.ScrollPaneStyle
     */
    public FullCardView(Card card, CardListView cardListView, CardList cardList) {
        super(card.getName());
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        this.CARD = card;

        // main panel
        PanelStyle mainPanel = new PanelStyle(500, 500, new BorderLayout());
        add(mainPanel);

        // Title panel
        mainPanel.add(creatTitlePanel(), BorderLayout.NORTH);

        // add the content panel to the main panel
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);

        // bottom panel
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        new FullCardController(card, this, cardListView, cardList);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method creates the title panel.
     *
     * @return PanelStyle, the title panel
     * @author Glen Denoual
     * @see PanelStyle
     * @see LabelStyle
     * @see TextType
     * @see MyStyle
     * @see BorderLayout
     */
    private PanelStyle creatTitlePanel() {
        PanelStyle titlePanel = new PanelStyle(500, 50, new BorderLayout());
        LabelStyle titleLabel = new LabelStyle(CARD.getName(), TextType.TITLE, SwingConstants.CENTER);
        titlePanel.setBorder(BorderFactory.createLineBorder(MyStyle.BORDER_COLOR, 2));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        return titlePanel;
    }

    /**
     * This method creates the content panel.
     *
     * @return PanelStyle, the content panel
     * @author Glen Denoual
     * @see PanelStyle
     * @see LabelStyle
     * @see TextType
     * @see MyStyle
     * @see GridLayout
     * @see javax.swing.JTextArea
     * @see javax.swing.JScrollPane
     * @see javax.swing.ScrollPaneConstants
     * @see java.awt.Font
     */
    private PanelStyle createContentPanel() {
        // content panel
        PanelStyle contentPanel = new PanelStyle(500, 500, new GridLayout(3, 2));
        //description
        LabelStyle descriptionLabel = new LabelStyle("Description :", TextType.TEXT);
        descriptionLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
                MyStyle.BORDER_COLOR));
        contentPanel.add(descriptionLabel);
        // description content
        JTextArea descriptionContentTextArea = new JTextArea(CARD.getDescription());
        descriptionContentTextArea.setEditable(false); // set textArea non-editable
        descriptionContentTextArea.setLineWrap(true); // set word wrap
        descriptionContentTextArea.setWrapStyleWord(true); // wrap line at word boundary
        // set the font
        descriptionContentTextArea.setFont(new Font(MyStyle.TEXT_FONT, Font.PLAIN, TextType.LONG_TEXT_FONT_SIZE));
        descriptionContentTextArea.setOpaque(true); // set textArea opaque
        descriptionContentTextArea.setForeground(MyStyle.BACKGROUND_COLOR); // set text color
        descriptionContentTextArea.setBackground(MyStyle.NEUTRAL_COLOR); // set text background color
        // set border
        descriptionContentTextArea.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2,
                MyStyle.BORDER_COLOR));
        // add it to a scrollPane
        ScrollPaneStyle descriptionContentScrollPane = new ScrollPaneStyle(descriptionContentTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // add the scrollPane to the content panel
        contentPanel.add(descriptionContentScrollPane);
        // Participants
        LabelStyle participantsLabel = new LabelStyle("Participants :", TextType.TEXT);
        participantsLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
                MyStyle.BORDER_COLOR));
        contentPanel.add(participantsLabel);
        // Participants content
        LabelStyle participantsContentLabel = new LabelStyle(CARD.showParticipants(), TextType.TEXT);
        participantsContentLabel.setOpaque(true);
        participantsContentLabel.setForeground(MyStyle.BACKGROUND_COLOR);
        participantsContentLabel.setBackground(MyStyle.NEUTRAL_COLOR);
        participantsContentLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2,
                MyStyle.BORDER_COLOR));
        // add it to a scrollPane
        ScrollPaneStyle participantContentScrollPane = new ScrollPaneStyle(participantsContentLabel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPanel.add(participantContentScrollPane);
        // link cards
        LabelStyle linkCardsLabel = new LabelStyle("Linked cards :", TextType.TEXT);
        linkCardsLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
                MyStyle.BORDER_COLOR));
        contentPanel.add(linkCardsLabel);
        // link cards content
        LabelStyle linkCardsContentLabel = new LabelStyle(CARD.showLinkedCards(), TextType.TEXT);
        linkCardsContentLabel.setOpaque(true);
        linkCardsContentLabel.setForeground(MyStyle.BACKGROUND_COLOR);
        linkCardsContentLabel.setBackground(MyStyle.NEUTRAL_COLOR);
        linkCardsContentLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2,
                MyStyle.BORDER_COLOR));
        // add it to a scrollPane
        ScrollPaneStyle linkCardsContentScrollPane = new ScrollPaneStyle(linkCardsContentLabel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPanel.add(linkCardsContentScrollPane);

        return contentPanel;
    }

    /**
     * This method creates the bottom panel.
     *
     * @return PanelStyle, the bottom panel
     * @author Glen Denoual
     * @see PanelStyle
     * @see LabelStyle
     * @see TextType
     * @see MyStyle
     * @see GridLayout
     * @see ButtonStyle
     */
    private PanelStyle createBottomPanel() {
        PanelStyle bottomPanel = new PanelStyle(500, 50, new GridLayout(1, 2));
        // due date
        String dueDate = CARD.getDueDate() == null ? "No due date" : "Due date : " + CARD.getDueDate().toString();
        LabelStyle dueDateLabel = new LabelStyle(dueDate, TextType.TEXT);
        dueDateLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
                MyStyle.BORDER_COLOR));
        bottomPanel.add(dueDateLabel);
        PanelStyle buttonPanel = new PanelStyle(250, 50, new GridLayout(1, 2));
        // Modify button
        modifyButton = new ButtonStyle("Modify", 125, 50);
        buttonPanel.add(modifyButton);
        // delete button
        deleteButton = new ButtonStyle("End", 125, 50);
        buttonPanel.add(deleteButton);
        bottomPanel.add(buttonPanel);
        return bottomPanel;
    }
}