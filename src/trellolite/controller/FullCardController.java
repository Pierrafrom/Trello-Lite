package trellolite.controller;

import trellolite.model.Card;
import trellolite.model.CardList;
import trellolite.view.CardListView;
import trellolite.view.FullCardView;
import trellolite.style.ButtonStyle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import trellolite.model.Board;
import trellolite.model.Participant;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.BoardView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FullCardController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private Card card;
    private FullCardView fullCardView;
    private CardListView cardListView;
    private CardList cardList;

    private ButtonStyle modifyButton;
    private ButtonStyle deleteButton;

    public FullCardController(Card card, FullCardView fullCardView, CardListView cardListView, CardList cardList) {
        this.card = card;
        this.fullCardView = fullCardView;
        this.cardListView = cardListView;
        this.cardList = cardList;

        modifyButton = fullCardView.getModifyButton();
        modifyButton.addActionListener(new ModifyCardListener());
        deleteButton = fullCardView.getDeleteButton();
        deleteButton.addActionListener(new DeleteCardListener());
    }

    private class ModifyCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // modify the card
            createCardModifier();
        }

        private void createCardModifier() {
            CardCreatorController constructor = new CardCreatorController();

            constructor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // check if the user cancelled the creation
                    if (!constructor.isCancelled()) {

                        // check if the name is already used
                        if (isNameUsedCard(constructor.getNameData())) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null, "Please, enter a unique name", "name already used",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the name is empty
                        if (constructor.getNameData().isEmpty()) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null, "Please, enter a name", "empty name",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // check if the date is valid
                        else if (!isValidDateFormat(constructor.getDueDateData())
                                && !constructor.getDueDateData().isEmpty()) {
                            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                            optionPaneStyle.showMessageDialog(null,
                                    "Please, enter a valid date (yyyy-MM-dd) in the future", "invalid date",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // create a new card
                        String name = constructor.getNameData();
                        String description = constructor.getDescriptionData();
                        LocalDate dueDate = null;
                        if (!constructor.getDueDateData().isEmpty()) {
                            dueDate = LocalDate.parse(constructor.getDueDateData());
                        }
                        // add Participants
                        ArrayList<Object> participants = constructor.getParticipantsData();
                        for (Object participant : participants) {
                            card.addMember((Participant) participant);
                        }

                        card.setName(name);
                        card.setDescription(description);
                        card.setDueDate(dueDate);
                        for (Object participant : participants) {
                            card.addMember((Participant) participant);
                        }

                        cardListView.update(cardList);
                        fullCardView.update(card);
                    }
                }
            });
        }

        public static boolean isValidDateFormat(String dateString) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                // check if the date is in the future
                if (LocalDate.parse(dateString, formatter).isBefore(LocalDate.now())) {
                    return false;
                }
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        /**
         * Check if the name is already used in the cardlist
         *
         * @param name ,String, The name to check
         * @return boolean, True if the name is already used, false otherwise
         * @author Augustin Lecomte
         */
        private boolean isNameUsedCard(String name) {
            for (Card card: cardList.getCards()) {
                if (card.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    private class DeleteCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Ask for confirmation
            ConfirmationDialog dialogController = new ConfirmationDialog();
            String message = "Are you sure you want to delete the card : " + card.getName() + " ?";
            String title = "Confirmation";
            boolean result = dialogController.showConfirmationDialog(message, title);

            if (result) {
                cardList.removeCard(card);
                cardListView.update(cardList);
                fullCardView.dispose();
            }
        }
    }
}
