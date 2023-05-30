package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.controller.OpenCardController;
import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.style.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This class is a JPanel that displays a list of cards.
 * <p>
 * It is used in the MainView class.
 * <br>
 * This class extends the PanelStyle class.
 * <br>
 * </p>
 *
 * @author Augustin Lecomte
 * @see trellolite.model.CardList
 * @see trellolite.style.PanelStyle
 * @see javax.swing.JPanel
 */
public class CardListView extends PanelStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTANTS
    // -----------------------------------------------------------------------------------------------------------------
    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;
    public static final int MARGIN = 10;
    private final String[] actions = {"Change the name", "Add a Card", "Delete this list"};

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private CardList cardList;
    private ComboBoxStyle actionsComboBox;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the actions combo box.
     *
     * @return ComboBoxStyle, the actions combo box.
     * @author Augustin Lecomte
     * @see ComboBoxStyle
     * @see trellolite.model.CardList
     * @see trellolite.view.CardListView
     */
    public ComboBoxStyle getActionsComboBox() {
        return actionsComboBox;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a CardListView.
     *
     * @param cardList, CardList, the card list to display.
     * @author Augustin Lecomte
     * @see trellolite.model.CardList
     * @see trellolite.view.CardListView
     * @see trellolite.style.PanelStyle
     * @see javax.swing.JPanel
     */
    public CardListView(CardList cardList) {
        super(WIDTH, HEIGHT, new BorderLayout());
        this.cardList = cardList;
        Border lineBorder = BorderFactory.createLineBorder(BORDER_COLOR, 3);
        setBorder(lineBorder);
        // create top panel
        add(createTopPanel(), BorderLayout.NORTH);
        // create content panel
        add(createContentPanel(), BorderLayout.CENTER);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method updates the CardListView.
     * <p>
     * It updates the name of the CardList and the content of the CardListView.
     * <br>
     * It is used when a card is added or removed from the CardList.
     * <br>
     * It is also used when the name of the CardList is changed.
     * </p>
     *
     * @param cardList, CardList, the card list to display.
     * @author Augustin Lecomte
     * @see trellolite.view.CardPreView
     * @see trellolite.style.PanelStyle
     * @see trellolite.style.ScrollPaneStyle
     * @see javax.swing.JPanel
     * @see javax.swing.JScrollPane
     */
    public void update(CardList cardList) {
        this.cardList = cardList;
        // update top panel
        LabelStyle listName = new LabelStyle(cardList.getName(), TextType.SUBTITLE, SwingConstants.CENTER);
        remove(0);
        add(listName, BorderLayout.CENTER, 0);
        // update content panel
        remove(1);
        add(createContentPanel(), BorderLayout.CENTER, 1);
        revalidate();
        repaint();
    }

    /**
     * This method creates the top panel of the CardListView.
     * <p>
     * It creates a PanelStyle that contains a LabelStyle and a ComboBoxStyle.
     * <br>
     * The LabelStyle contains the name of the CardList.
     * <br>
     * The ComboBoxStyle contains the actions that can be done on the CardList.
     * <br>
     * The actions are: change the name, add a card and delete the list.
     * </p>
     *
     * @return PanelStyle, the top panel of the CardListView.
     * @author Augustin Lecomte
     * @see trellolite.style.PanelStyle
     * @see trellolite.style.LabelStyle
     * @see javax.swing.JPanel
     * @see javax.swing.JLabel
     * @see trellolite.style.TextType
     * @see javax.swing.SwingConstants
     * @see trellolite.style.ComboBoxStyle
     * @see javax.swing.JComboBox
     */
    private PanelStyle createTopPanel() {
        // create top panel
        PanelStyle topPanel = new PanelStyle(WIDTH, 100, new GridLayout(2, 1));
        // add list name
        LabelStyle listName = new LabelStyle(cardList.getName(), TextType.SUBTITLE, SwingConstants.CENTER);
        topPanel.add(listName);
        // add actions combo box
        PanelStyle actionPanel = new PanelStyle(WIDTH, 30, new FlowLayout(FlowLayout.CENTER, MARGIN, MARGIN));
        actionsComboBox = new ComboBoxStyle(actions, 240, 30);
        actionPanel.add(actionsComboBox);
        topPanel.add(actionPanel);
        return topPanel;
    }

    /**
     * This method creates the content panel of the CardListView.
     * <p>
     * It creates a ScrollPaneStyle that contains a PanelStyle.
     * <br>
     * The PanelStyle contains all the CardPreView of the CardList.
     * </p>
     *
     * @return ScrollPaneStyle, the content panel of the CardListView.
     * @author Augustin Lecomte
     * @see trellolite.view.CardPreView
     * @see trellolite.style.PanelStyle
     * @see trellolite.style.ScrollPaneStyle
     * @see javax.swing.JScrollPane
     * @see javax.swing.JPanel
     */
    private ScrollPaneStyle createContentPanel() {
        // get the height of the  content panel
        int contentHeight = 0;
        for (int i = 0; i < cardList.getCards().size(); i++) {
            contentHeight += CardPreView.HEIGHT + MARGIN;
        }

        // create content panel
        PanelStyle contentPanel = new PanelStyle(WIDTH, contentHeight, new FlowLayout(FlowLayout.CENTER, MARGIN,
                MARGIN));

        // add Cards to the content panel
        for (int i = 0; i < cardList.getCards().size(); i++) {
            // create a card
            Card card = cardList.getCard(i);
            // create card preview
            CardPreView cardPreView = new CardPreView(card);
            // add the controller to the card preview
            OpenCardController openCardController = new OpenCardController(card, cardPreView,
                    this, cardList);
            // add card preview to content panel
            contentPanel.add(cardPreView);
        }
        // add content panel to scroll pane
        return new ScrollPaneStyle(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
