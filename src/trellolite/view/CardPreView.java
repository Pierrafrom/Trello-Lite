package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Card;
import trellolite.style.ButtonStyle;
import trellolite.style.LabelStyle;
import trellolite.style.PanelStyle;
import trellolite.style.TextType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This class is a JPanel that displays a preview of a card.
 * <p>
 * It shows the name and the due date of the card.
 * We also have a button to open the card.
 * </p>
 *
 * @author Augustin Lecomte
 * @see trellolite.model.Card
 * @see trellolite.style.PanelStyle
 * @see javax.swing.JPanel
 * @see trellolite.style.ButtonStyle
 * @see trellolite.style.LabelStyle
 */
public class CardPreView extends PanelStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTANTS
    // -----------------------------------------------------------------------------------------------------------------
    private static final int WIDTH = 240;
    public static final int HEIGHT = 70;

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------

    ButtonStyle openCard;

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------

    public ButtonStyle getOpenCard() {
        return openCard;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a CardPreView.
     *
     * @param card, Card, the card to display.
     * @author Augustin Lecomte
     * @see trellolite.model.Card
     * @see trellolite.view.CardPreView
     * @see trellolite.style.PanelStyle
     * @see javax.swing.JPanel
     */
    public CardPreView(Card card) {
        super(WIDTH, HEIGHT, new BorderLayout());
        // create border
        Border lineBorder = BorderFactory.createLineBorder(BORDER_COLOR, 1);
        setBorder(lineBorder);
        // create info panel
        PanelStyle infoPanel = new PanelStyle(WIDTH - 70, HEIGHT, new BorderLayout());
        LabelStyle cardName = new LabelStyle(card.getName(), TextType.SUBTITLE, SwingConstants.LEFT);
        infoPanel.add(cardName, BorderLayout.NORTH);
        LabelStyle CardDueDate;
        if (card.getDueDate() != null) {
            CardDueDate = new LabelStyle(card.getDueDate().toString(), TextType.TEXT, SwingConstants.LEFT);
        } else {
            CardDueDate = new LabelStyle("No due date", TextType.TEXT, SwingConstants.LEFT);
        }
        infoPanel.add(CardDueDate, BorderLayout.SOUTH);
        add(infoPanel, BorderLayout.WEST);
        openCard = new ButtonStyle("Open", 70, 70);
        add(openCard, BorderLayout.CENTER);
    }
}
