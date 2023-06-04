package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CardList class
 * This class represents a list of cards in the Trello-Lite application.
 * A list of cards is a collection of cards.
 * A list of cards has a name, an archived status and a list of cards.
 * A list of cards can be serialized and deserialized.
 * A list of cards can be created with a name, an archived status and a list of cards.
 * A list of cards can be created with a name.
 * A list of cards can be created with no name, no archived status and no cards.
 * If a list of cards is created with no name, a default name is given.
 * The default name is "List " + id.
 * The id is automatically generated.
 * The list of cards is created with no cards.
 * The list of cards is created with no archived status.
 * The id is automatically generated.
 * The nextId is incremented whenever a new list of cards is created.
 * <br>The methods of this class are:
 * <ul>
 *     <li>addCard</li>
 *     <li>removeCard</li>
 * </ul>
 *
 * @author Roxane Zaharia
 * @see Card
 * @see Serializable
 * @see ArrayList
 * @see Board
 * @see Participant
 * @see CardList
 * @see Card
 * @see Serializable
 * @see ArrayList
 * @see Board
 * @see Participant
 */
public class CardList implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private static final long serialVersionUID = 6903435592741052994L;
    private static int nextId = 0;

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final int id;
    private String name;
    private boolean archived;
    private ArrayList<Card> cards;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the class List.
     * The list is created with the given name, archived status and cards.
     *
     * @param name     ,String, the name of the list.
     * @param archived ,boolean, the archived status of the list.
     * @param cards    ,ArrayList<Card>, the cards of the list.
     * @author Roxane Zaharia
     * @see ArrayList
     * @see Card
     */
    public CardList(String name, boolean archived, ArrayList<Card> cards) {
        id = nextId;
        nextId++;
        this.name = name;
        this.archived = archived;
        this.cards = cards;
    }

    /**
     * Constructor of the class List.
     * The list is created with the given name and archived status.
     * The list is created with no cards.
     *
     * @param name ,String, the name of the list.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Card
     */
    public CardList(String name) {
        id = nextId;
        nextId++;
        this.name = name;
        this.archived = false;
        cards = new ArrayList<>();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Getter for the id.
     *
     * @return int, the id of the list.
     * @author Roxanne Zaharia
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name.
     *
     * @return String, the name of the list.
     * @author Roxanne Zaharia
     * @see String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the archived status.
     *
     * @return boolean, the archived status of the list.
     * @author Roxanne Zaharia
     */
    public boolean getArchived() {
        return archived;
    }

    /**
     * Getter for the cards.
     *
     * @return ArrayList<Card>, the cards of the list.
     * @author Roxanne Zaharia
     * @see ArrayList
     * @see Card
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Getter for the card at the given index.
     *
     * @param index ,int, the index of the card.
     * @return Card, the card at the given index of the list of cards of the list.
     * @author Augustin Lecomte
     * @see Card
     */
    public Card getCard(int index) {
        return cards.get(index);
    }

    /**
     * Setter for the name.
     * Sets the name of the list with the given name.
     *
     * @param name ,String, the name of the list.
     * @author Roxanne Zaharia
     * @see String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the archived status.
     * Sets the archived status of the list with the given archived status.
     *
     * @param archived ,boolean, the archived status of the list.
     * @author Roxanne Zaharia
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     * Setter for the cards.
     * Sets the cards of the list with the given cards.
     *
     * @param cards ,ArrayList<Card>, the cards of the list.
     * @author Roxanne Zaharia
     * @see ArrayList
     * @see Card
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method adds a card to the list at the end of the list.
     *
     * @param card ,Card, the card to add to the list.
     * @author Roxane Zaharia
     * @see Card
     * @see ArrayList
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * This method removes a card from the list.
     *
     * @param card ,Card, the card to remove from the list.
     * @author Roxane Zaharia
     * @see Card
     * @see ArrayList
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }
}