package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.style.ButtonStyle;
import trellolite.view.CardListView;
import trellolite.view.CardPreView;
import trellolite.view.FullCardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the controller of the CARD preview.
 * <p>
 * It opens the CARD when the user clicks on the button.
 * It creates a FullCardView and a FullCardController.
 * </p>
 *
 * @author Pierre Fromont Boissel
 * @see trellolite.model.Card
 * @see trellolite.view.CardPreView
 * @see trellolite.view.FullCardView
 * @see trellolite.controller.FullCardController
 * @see trellolite.model.CardList
 * @see trellolite.view.CardListView
 */
public class OpenCardController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final Card CARD;
    private final CardListView CARDLISTVIEW;
    private final CardList CARDLIST;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates an OpenCardController.
     * <p>
     * It creates a button to open the CARD.
     * It adds an ActionListener to the button.
     * When the user clicks on the button, it opens the CARD.
     * It creates a FullCardView and a FullCardController.
     * It passes the CARD, the CARDLISTVIEW and the CARDLIST to the FullCardController.
     * </p>
     *
     * @param card,         Card, the CARD to open
     * @param cardPreView,  CardPreView, the CARD preview
     * @param cardListView, CardListView, the CARD list view
     * @param cardList,     CardList, the CARD list
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Card
     * @see trellolite.view.CardPreView
     * @see trellolite.view.FullCardView
     * @see trellolite.controller.FullCardController
     * @see trellolite.model.CardList
     * @see trellolite.view.CardListView
     * @see trellolite.style.ButtonStyle     *
     */
    public OpenCardController(Card card, CardPreView cardPreView, CardListView cardListView, CardList cardList) {
        this.CARD = card;
        this.CARDLISTVIEW = cardListView;
        this.CARDLIST = cardList;

        ButtonStyle openButton = cardPreView.getOpenCard();
        openButton.addActionListener(new OpenCardListener());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // LISTENER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the button to open the CARD.
     * <p>
     * When the user clicks on the button, it opens the CARD.
     * It creates a FullCardView and a FullCardController.
     * It passes the CARD, the CARDLISTVIEW and the CARDLIST to the FullCardController.
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see trellolite.model.Card
     * @see trellolite.view.CardPreView
     * @see trellolite.view.FullCardView
     * @see trellolite.controller.FullCardController
     * @see trellolite.model.CardList
     * @see trellolite.view.CardListView
     */
    private class OpenCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // open the CARD
            new FullCardView(CARD, CARDLISTVIEW, CARDLIST);
        }
    }
}
