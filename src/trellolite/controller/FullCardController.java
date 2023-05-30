package trellolite.controller;

import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.view.CardListView;
import trellolite.view.FullCardView;
import trellolite.style.ButtonStyle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullCardController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private Card card;
    private FullCardView fullCardView;
    private CardListView cardListView;
    private CardList cardList;

    private ButtonStyle modifyButton;

    public FullCardController(Card card, FullCardView fullCardView, CardListView cardListView, CardList cardList) {
        this.card = card;
        this.fullCardView = fullCardView;
        this.cardListView = cardListView;
        this.cardList = cardList;

        modifyButton = fullCardView.getModifyButton();
        modifyButton.addActionListener(new ModifyCardListener());
    }

    private class ModifyCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Modify button clicked");
            // TODO: Modify the card
        }
    }
}
