package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serial;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Board class
 * This class represents a board in the Trello-Lite application.
 * A board is a collection of lists.
 * A board has a name and a unique id.
 * A board can be serialized and deserialized.
 * A board can be created with a name or without a name.
 * If a board is created without a name, a default name is given.
 * The default name is "Board" + id.
 * The id is automatically generated.
 *
 * @author Glen Denoual
 * @see CardList
 * @see Card
 * @see Serializable
 * @see java.util.ArrayList
 */
public class Board implements Serializable {

    //------------------------------------------------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    //------------------------------------------------------------------------------------------------------------------
    @Serial
    private static final long serialVersionUID = 4518357025796495586L;
    private static int nextId = 0;

    //------------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    //------------------------------------------------------------------------------------------------------------------
    private final int id;
    private String name;
    private ArrayList<CardList> lists;

    //------------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Constructor with name
     * Creates a new board with the given name.
     * The id is automatically generated.
     * The lists are initialized to an empty ArrayList.
     * The nextId is incremented.
     * The name is set to the given name.
     *
     * @param name, String, the name of the board
     * @author Glen Denoual
     * @see ArrayList
     * @see CardList
     */
    public Board(String name) {
        id = nextId;
        nextId++;
        this.name = name;
        lists = new ArrayList<>();
    }

    /**
     * Constructor without name
     * Creates a new board with a default name.
     * The id is automatically generated.
     * The lists are initialized to an empty ArrayList.
     * The nextId is incremented.
     * The name is set to "Board" + id.
     *
     * @author Glen Denoual
     * @see ArrayList
     * @see CardList
     */
    public Board() {
        id = nextId;
        nextId++;
        name = "Board " + id;
        lists = new ArrayList<>();
    }

    //------------------------------------------------------------------------------------------------------------------
    // GETTERS & SETTERS
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Getter for the id
     * Returns the id of the board.
     *
     * @return int, the id of the board
     * @author Glen Denoual
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name
     * Returns the name of the board.
     *
     * @return String, the name of the board
     * @author Glen Denoual
     * @see String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the lists
     * Returns the lists of the board.
     *
     * @return ArrayList<CardList>, the lists of the board
     * @author Glen Denoual
     * @see ArrayList
     * @see CardList
     * @see Card
     */
    public ArrayList<CardList> getLists() {
        return lists;
    }

    /**
     * Setter for the name
     * Sets the name of the board to the given name.
     *
     * @param name, String, the new name of the board
     * @author Glen Denoual
     * @see String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the lists
     * Sets the lists of the board to the given lists.
     *
     * @param lists, ArrayList<CardList>, the new lists of the board
     * @author Glen Denoual
     * @see ArrayList
     * @see CardList
     * @see Card
     */
    public void setLists(ArrayList<CardList> lists) {
        this.lists = lists;
    }

    //------------------------------------------------------------------------------------------------------------------
    // METHODS
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Adds the given list to the board.
     * The list is added at the end of the lists.
     *
     * @param list, CardList, the list to add to the board
     * @author Glen Denoual
     * @see CardList
     * @see Card
     * @see ArrayList
     */
    public void addList(CardList list) {
        lists.add(list);
    }

    /**
     * Removes the given list from the board.
     * The list is removed from the lists.
     *
     * @param list, CardList, the list to remove from the board
     * @author Glen Denoual
     * @see CardList
     * @see Card
     * @see ArrayList
     */
    public void removeList(CardList list) {
        lists.remove(list);
    }

    /**
     * Returns the list at the given index.
     *
     * @param i, int, the index of the list to return
     * @author Augustin Lecomte
     * @see CardList
     * @see Card
     * @see ArrayList
     */
    public CardList getList(int i) {
        return lists.get(i);
    }
}