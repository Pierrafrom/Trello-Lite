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
 * This class is the controller of the card preview.
 * <p>
 * It opens the card when the user clicks on the button.
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
    private Card card;
    private CardListView cardListView;
    private CardList cardList;
    private ButtonStyle openButton;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates an OpenCardController.
     * <p>
     * It creates a button to open the card.
     * It adds an ActionListener to the button.
     * When the user clicks on the button, it opens the card.
     * It creates a FullCardView and a FullCardController.
     * It passes the card, the cardListView and the cardList to the FullCardController.
     * </p>
     *
     * @param card,         Card, the card to open
     * @param cardPreView,  CardPreView, the card preview
     * @param cardListView, CardListView, the card list view
     * @param cardList,     CardList, the card list
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
        this.card = card;
        this.cardListView = cardListView;
        this.cardList = cardList;

        openButton = cardPreView.getOpenCard();
        openButton.addActionListener(new OpenCardListener());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // LISTENER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the button to open the card.
     * <p>
     * When the user clicks on the button, it opens the card.
     * It creates a FullCardView and a FullCardController.
     * It passes the card, the cardListView and the cardList to the FullCardController.
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
            // open the card
            FullCardView fullCardView = new FullCardView(card);
            // add the controller
            FullCardController fullCardController = new FullCardController(card, fullCardView, cardListView, cardList);
        }
    }
}
