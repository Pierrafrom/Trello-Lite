package trellolite.style;

/**
 * This enum contains the different types of text.
 * Each type has a font name, a font style and a font size.
 *
 * @author Pierre Fromont Boissel
 */
public enum TextType implements MyStyle {

    // -----------------------------------------------------------------------------------------------------------------
    // ENUMS
    // -----------------------------------------------------------------------------------------------------------------
    TITLE(TITLE_FONT, TITLE_FONT_STYLE, TITLE_FONT_SIZE),
    SUBTITLE(TITLE_FONT, SUBTITLE_FONT_STYLE, SUBTITLE_FONT_SIZE),
    TEXT(TEXT_FONT, TEXT_FONT_STYLE, TEXT_FONT_SIZE);

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final String fontName;
    private final int fontSize;
    private final int fontStyle;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------
    TextType(String fontName, int fontStyle, int fontSize) {
        this.fontName = fontName;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS
    // -----------------------------------------------------------------------------------------------------------------
    public String getFontName() {
        return fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }
}