package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------
import trellolite.style.OptionPaneStyle;

import javax.swing.*;

/**
 * This class represents the confirmation dialog controller of the application.
 * It is used to manage  any confirmation dialog.
 *
 * @author Pierre Fromont Boissel
 * @see trellolite.style.OptionPaneStyle
 */
public class ConfirmationDialog extends OptionPaneStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // METHOD
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Displays a confirmation dialog with the specified message and title,
     * and returns true if the user selects "Yes" and false if the user selects "Cancel".
     *
     * @param message : String, the message to display.
     * @param title   : String, the title of the dialog.
     * @return boolean, true if "Yes" is selected, false if "Cancel" is selected.
     * @author Pierre Fromont Boissel
     * @example <pre>{@code
     * ConfirmationDialogController dialogController = new ConfirmationDialogController();
     *
     * String message = "Are you sure you want to proceed?";
     * String title = "Confirmation";
     *
     * boolean result = dialogController.showConfirmationDialog(message, title);
     *
     * if (result) {
     *     System.out.println("User selected Yes.");
     *     // Perform some action if "Yes" is selected
     * } else {
     *     System.out.println("User selected Cancel.");
     *     // Perform some action if "Cancel" is selected
     * }
     * </pre>}
     * @see JOptionPane
     * @see OptionPaneStyle
     * @see trellolite.style.PanelStyle
     */
    public boolean showConfirmationDialog(String message, String title) {
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "Cancel");

        int option = showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        return option == JOptionPane.YES_OPTION;
    }
}