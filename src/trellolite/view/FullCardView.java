package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Card;
import trellolite.style.*;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the view of a card.
 * <br>
 * This class extends JFrame.
 * <br>
 * It shows the name, the description, the due date and the labels of a card.
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
    private Card card;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    public ButtonStyle getModifyButton() {
        return modifyButton;
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
     * It displays the card in a JFrame.
     *
     * @param card, Card, the card to display
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
    public FullCardView(Card card) {
        super(card.getName());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        this.card = card;

        // main panel
        PanelStyle mainPanel = new PanelStyle(500, 500, new BorderLayout());
        add(mainPanel);

        // Title panel
        mainPanel.add(creatTitlePanel(), BorderLayout.NORTH);

        // add the content panel to the main panel
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);

        // bottom panel
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method updates the card.
     *
     * @param card, Card, the card to update
     * @author Glen Denoual
     * @see Card
     * @see FullCardView
     */
    public void update(Card card) {
        this.dispose();
        new FullCardView(card);
    }

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
        LabelStyle titleLabel = new LabelStyle(card.getName(), TextType.TITLE, SwingConstants.CENTER);
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
        PanelStyle contentPanel = new PanelStyle(500, 400, new GridLayout(2, 2));
        //description
        LabelStyle descriptionLabel = new LabelStyle("Description :", TextType.TEXT);
        descriptionLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
                MyStyle.BORDER_COLOR));
        contentPanel.add(descriptionLabel);
        // description content
        JTextArea descriptionContentTextArea = new JTextArea(card.getDescription());
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
        LabelStyle participantsContentLabel = new LabelStyle(card.showParticipants(), TextType.TEXT);
        participantsContentLabel.setOpaque(true);
        participantsContentLabel.setForeground(MyStyle.BACKGROUND_COLOR);
        participantsContentLabel.setBackground(MyStyle.NEUTRAL_COLOR);
        participantsContentLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2,
                MyStyle.BORDER_COLOR));
        // add it to a scrollPane
        contentPanel.add(participantsContentLabel);
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
        String dueDate = card.getDueDate() == null ? "No due date" : "Due date : " + card.getDueDate().toString();
        LabelStyle dueDateLabel = new LabelStyle(dueDate, TextType.TEXT);
        dueDateLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2,
                MyStyle.BORDER_COLOR));
        bottomPanel.add(dueDateLabel);
        // Modify button
        modifyButton = new ButtonStyle("Modify", 250, 50);
        bottomPanel.add(modifyButton);
        return bottomPanel;
    }

    /* test for the card view
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Card card = new Card("test");
            String descr = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend tristique " +
                    "libero, ac finibus nisi porttitor vitae. Suspendisse consectetur eleifend risus, id mattis " +
                    "nisi hendrerit vitae. Sed sagittis interdum ultricies. Nullam condimentum nisl a ex aliquam, " +
                    "sit amet maximus nunc consequat. Nullam quis augue vestibulum, consequat urna sed, consequat " +
                    "purus. Sed efficitur, enim ut fringilla fringilla, ligula libero faucibus velit, nec blandit " +
                    "lacus enim vel quam. Nullam id arcu tincidunt, mollis enim eget, suscipit odio. Curabitur sed" +
                    " posuere ligula.";
            card.setDescription(descr);
            card.addMember(new Participant("test", "test", "test@gmail.com", Role.MEMBER));
            card.addMember(new Participant("test1", "test1", "test1@gmail.com", Role.MEMBER));
            card.addMember(new Participant("test2", "test2", "test2@gmail.com", Role.MEMBER));
            FullCardView fullCardView = new FullCardView(card);
        });
    }*/
}