package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.model.Participant;
import trellolite.style.ButtonStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.CardCreatorView;
import trellolite.view.CardListView;
import trellolite.view.FullCardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FullCardController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final Card CARD;
    private final FullCardView FULLCARDVIEW;
    private final CardListView CARDLISTVIEW;
    private final CardList CARDLIST;

    /**
     * This constructor creates a new FullCardController. It allows the user to
     * modify or delete a card.
     *
     * @param card,         Card, the card to modify or delete
     * @param fullCardView, FullCardView, the view of the card to modify or delete
     * @param cardListView, CardListView, the view of the list of cards to modify or delete
     * @param cardList,     CardList, the list of cards to modify or delete
     * @author Glen Denoual
     * @see Card
     * @see FullCardView
     * @see CardListView
     * @see CardList
     * @see ModifyCardListener
     */
    public FullCardController(Card card, FullCardView fullCardView, CardListView cardListView, CardList cardList) {
        CARD = card;
        FULLCARDVIEW = fullCardView;
        CARDLISTVIEW = cardListView;
        CARDLIST = cardList;

        ButtonStyle modifyButton = fullCardView.getModifyButton();
        modifyButton.addActionListener(new ModifyCardListener());
        ButtonStyle deleteButton = fullCardView.getDeleteButton();
        deleteButton.addActionListener(new DeleteCardListener());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // LISTENERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the deleted button. It allows the user to
     * modify a card.
     *
     * @author Pierre Fromont Boissel
     * @see FullCardController
     * @see FullCardView
     * @see CardListView
     * @see CardList
     * @see Card
     * @see DeleteCardListener
     */
    private class ModifyCardListener implements ActionListener {
        // -------------------------------------------------------------------------------------------------------------
        // ATTRIBUTES
        // -------------------------------------------------------------------------------------------------------------
        private CardCreatorView constructor;

        // -------------------------------------------------------------------------------------------------------------
        // METHODS
        // -------------------------------------------------------------------------------------------------------------

        /**
         * This method is called when the user clicks on the modified button. It
         * allows the user to modify a card.
         *
         * @param e the event to be processed
         * @author Pierre Fromont Boissel
         * @see ModifyCardListener
         * @see FullCardController
         * @see FullCardView
         * @see CardListView
         * @see CardList
         * @see Card
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            createCardModifier();
        }

        /**
         * This method creates a new CardCreatorView. It allows the user to
         * modify a card.
         *
         * @author Pierre Fromont Boissel
         * @see ModifyCardListener
         * @see FullCardController
         * @see FullCardView
         * @see CardListView
         * @see CardList
         * @see Card
         * @see CardCreatorView
         */
        private void createCardModifier() {
            // dispose the previous window
            if (constructor != null) {
                constructor.dispose();
            }
            // create a new window
            constructor = new CardCreatorView();
            // set the data of the card to modify
            constructor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // check if the user canceled the creation
                    if (!constructor.isCancelled()) {

                        // check if the name is already used
                        if (isNameUsedCard(constructor.getNameData())) {
                            OptionPaneStyle.showMessageDialog(null, "Please, enter a unique" +
                                    " name", "name already used", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the name is empty
                        if (constructor.getNameData().isEmpty()) {
                            OptionPaneStyle.showMessageDialog(null, "Please, enter a " +
                                    "name", "empty name", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the date is valid
                        else if (!isValidDateFormat(constructor.getDueDateData())
                                && !constructor.getDueDateData().isEmpty()) {
                            OptionPaneStyle.showMessageDialog(null,
                                    "Please, enter a valid date (yyyy-MM-dd) in the future", "invalid " +
                                            "date", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Modify the card
                        String name = constructor.getNameData();
                        String description = constructor.getDescriptionData();
                        LocalDate dueDate = null;
                        CARD.setName(name);
                        CARD.setDescription(description);
                        if (!constructor.getDueDateData().isEmpty()) {
                            dueDate = LocalDate.parse(constructor.getDueDateData());
                        }
                        CARD.setDueDate(dueDate);
                        // add Participants
                        ArrayList<Object> participants = constructor.getParticipantsData();
                        // remove all the participants
                        CARD.getParticipants().clear();
                        for (Object participant : participants) {
                            CARD.addMember((Participant) participant);
                        }
                        // cards
                        // remove all the cards
                        CARD.getLinkedCards().clear();
                        ArrayList<Object> cards = constructor.getCardsData();
                        for (Object card : cards) {
                            CARD.addLinkedCard((Card) card);
                        }
                        // update the card list
                        CARDLISTVIEW.update(CARDLIST);
                        // dispose the window
                        FULLCARDVIEW.dispose();
                    }
                }
            });
        }

        /**
         * Check if the date is valid and in the future
         *
         * @param dateString, String, the date to check
         * @return boolean, True if the date is valid and in the future, false otherwise
         * @throws DateTimeParseException if the date is not valid
         * @author Augustin Lecomte
         * @see LocalDate
         */
        public static boolean isValidDateFormat(String dateString) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                // check if the date is in the future
                return !LocalDate.parse(dateString, formatter).isBefore(LocalDate.now());
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        /**
         * Check if the name is already used in the card list
         *
         * @param name String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         */
        private boolean isNameUsedCard(String name) {
            for (Card card : CARDLIST.getCards()) {
                if (card.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * This class is the listener of the deleted button. It allows the user to
     * delete a card.
     *
     * @author Pierre Fromont Boissel
     * @see FullCardController
     * @see FullCardView
     * @see CardListView
     */
    private class DeleteCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Ask for confirmation
            ConfirmationDialog dialogController = new ConfirmationDialog();
            String message = "Are you sure you want to end the card (task) : " + CARD.getName() + " ?";
            String title = "Confirmation";
            boolean result = dialogController.showConfirmationDialog(message, title);

            if (result) {
                CARDLIST.removeCard(CARD);
                CARDLISTVIEW.update(CARDLIST);
                FULLCARDVIEW.dispose();
            }
        }
    }
}
