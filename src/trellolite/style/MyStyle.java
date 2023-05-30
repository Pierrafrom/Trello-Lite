package trellolite.style;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.awt.Color;
import java.awt.Font;

/**
 * This interface contains all the style constants used in the application.
 * It contains Colors, Fonts, and Font Sizes.
 *
 * @author Pierre Fromont Boissel
 */
public interface MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // COLORS
    // -----------------------------------------------------------------------------------------------------------------
    Color BACKGROUND_COLOR = new Color(29, 30, 39);
    Color TEXT_COLOR = new Color(199, 200, 216);
    Color BORDER_COLOR = new Color(68, 71, 90);
    Color BUTTON_COLOR = new Color(79, 84, 105);
    Color BUTTON_BORDER_COLOR = new Color(96, 102, 126);
    Color BUTTON_HOVER = new Color(121, 128, 157);
    Color TEXT_ACCENTUATION = new Color(174, 129, 255);
    Color COMPONENT_ACCENTUATION = new Color(0, 174, 239);
    Color SUCCESS_COLOR = new Color(76, 175, 80);
    Color ERROR_COLOR = new Color(244, 67, 54);
    Color WARNING_COLOR = new Color(255, 193, 7);
    Color NEUTRAL_COLOR = new Color(200, 200, 200);

    // -----------------------------------------------------------------------------------------------------------------
    // FONTS
    // -----------------------------------------------------------------------------------------------------------------
    String TEXT_FONT = "Segoe UI";
    String TITLE_FONT = "Century Gothic";
    int TITLE_FONT_SIZE = 22;
    int SUBTITLE_FONT_SIZE = 18;
    int TEXT_FONT_SIZE = 14;
    int LONG_TEXT_FONT_SIZE = 12;
    int SMALL_TEXT_FONT_SIZE = 10;
    int TITLE_FONT_STYLE = Font.BOLD;
    int SUBTITLE_FONT_STYLE = Font.BOLD;
    int TEXT_FONT_STYLE = Font.PLAIN;
    int LONG_TEXT_FONT_STYLE = Font.PLAIN;
    int SMALL_TEXT_FONT_STYLE = Font.PLAIN;
}
