package trellolite.view;

import trellolite.controller.OpenCardController;
import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.style.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import trellolite.model.*;
import trellolite.style.*;

public class CardArchivedView extends JFrame {

    private static final int MARGIN = 5;
    private static final Color BORDER_COLOR = Color.BLACK; // Set a valid border color
    private CardList cardList;

    public CardArchivedView(CardList cardList) {
        super("Card Archived List"); // Set the frame title
        this.cardList = cardList;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(650, 680));
        setLayout(new BorderLayout());

        // Create top panel and add it to the frame
        PanelStyle topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        // Create content panel and add it to a scroll pane
        ScrollPaneStyle contentScrollPane = createContentPanel();
        add(contentScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private PanelStyle createTopPanel() {
        PanelStyle topPanel = new PanelStyle(getWidth(), 100, new GridLayout(2, 1));
        LabelStyle listName = new LabelStyle(cardList.getName()+ "(archived)", TextType.SUBTITLE, SwingConstants.CENTER);
        topPanel.add(listName);
        return topPanel;
    }

    private ScrollPaneStyle createContentPanel() {
        int contentHeight = 0;
        for (int i = 0; i < cardList.getCardsArchived().size(); i++) {
            contentHeight += CardPreView.HEIGHT + MARGIN;
        }

        PanelStyle contentPanel = new PanelStyle(getWidth(), contentHeight,
                new FlowLayout(FlowLayout.CENTER, MARGIN, MARGIN));

        for (int i = 0; i < cardList.getCardsArchived().size(); i++) {
            Card card = cardList.getCardArchived(i); // Use getCardArchived instead of getCard
            CardPreView cardPreView = new CardPreView(card);
            contentPanel.add(cardPreView);
        }

        return new ScrollPaneStyle(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**public static void main(String[] args) {
        CardList cl = new CardList("test");
        Card c = new Card("card1");
        c.setArchived(true);
        cl.addCardArchived(c);
        System.out.println(cl.getCardsArchived().size());
        CardArchivedView v = new CardArchivedView(cl);
    }*/
}
