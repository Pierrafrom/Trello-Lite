package trellolite.model;

// --------------------------------------------------------------------------------------------------------------------
// IMPORTS
// --------------------------------------------------------------------------------------------------------------------

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Card class
 * This class represents a card in the Trello-Lite application.
 * A card is a task to do.
 * A card has a name, a description, a due date, a list of participants and an archived status.
 * A card can be serialized and deserialized.
 * A card can be created with a name, a description and a due date.
 * A card can be created with no name, no description, no due date and no participants.
 * If a card is created with no name, a default name is given.
 * The default name is "Card " + id.
 * The id is automatically generated.
 * The card is not archived by default.
 * The card is created with no participants.
 * The id is automatically generated.
 * The nextId is incremented whenever a new card is created.
 * <br>The methods of this class are:
 * <ul>
 *     <li>addMember</li>
 *     <li>removeMember</li>
 * </ul>
 *
 * @author Glen Denoual
 * @see Participant
 * @see Serializable
 * @see ArrayList
 * @see LocalDate
 * @see CardList
 * @see Board
 */
public class Card implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private static final long serialVersionUID = -5546002422160290154L;
    private static int nextId = 0;

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final int id;
    private String name;
    private String description;
    private boolean archived;
    private LocalDate dueDate;
    private ArrayList<Participant> participants;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the class Card.
     * The card is created with the given name, description and due date.
     * The card is created with no participants.
     * The card is not archived by default.
     *
     * @param name        ,arg1, String, the name of the card.
     * @param description ,arg2, String, the description of the card.
     * @param dueDate     ,arg3, String, the due date of the card.
     * @author Glen Denoual
     * @see Participant
     * @see ArrayList
     * @see LocalDate
     */
    public Card(String name, String description, LocalDate dueDate) {
        id = nextId;
        nextId++;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        participants = new ArrayList<>();
    }

    /**
     * Constructor of the class Card.
     * The card is created with no name, no description, no archived status and no due date.
     *
     * @author Pierre Fromont Boissel
     * @see Participant
     * @see ArrayList
     */
    public Card() {
        id = nextId;
        nextId++;
        name = "";
        description = "";
        archived = false;
        dueDate = null;
        participants = new ArrayList<>();
    }

    /**
     * Constructor of the class Card.
     * The card is created with the given name.
     * The card is created with no description, no archived, no
     * status and no due date.
     *
     * @param name, String, the name of the card.
     * @author Pierre Fromont Boissel
     */
    public Card(String name) {
        id = nextId;
        nextId++;
        this.name = name;
        description = "";
        archived = false;
        dueDate = null;
        participants = new ArrayList<>();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Getter of the id of the card.
     *
     * @return int, the id of the card.
     * @author Glen Denoual
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of the nextId of the card.
     *
     * @return int, the nextId of the card.
     * @author Glen Denoual
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the name of the card.
     *
     * @return String, the name of the card.
     * @author Glen Denoual
     * @see String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of the archived status of the card.
     *
     * @return boolean, the archived status of the card.
     * @author Glen Denoual
     * @see boolean
     */
    public boolean getArchived() {
        return archived;
    }

    /**
     * Getter of the due date of the card.
     *
     * @return LocalDate, the due date of the card.
     * @author Glen Denoual
     * @see LocalDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Getter of the participants of the card.
     *
     * @return ArrayList<Participant>, the participants of the card.
     * @author Glen Denoual
     * @see ArrayList
     * @see Participant
     */
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    /**
     * Getter of the participant at the given index of the card.
     *
     * @param index, int, the index of the participant to return.
     * @return Participant, the participant at the given index of the card.
     * @author Augustin Lecomte
     * @see Participant
     * @see ArrayList
     */
    public Participant getParticipant(int index) {
        return participants.get(index);
    }

    /**
     * Setter for the name
     * Sets the name of the card to the given name.
     *
     * @param name, String, the new name of the board
     * @author Glen Denoual
     * @see String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the description
     * Sets the description of the card to the given description.
     *
     * @param description, String, the new description of the board
     * @author Glen Denoual
     * @see String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for the archived status
     * Sets the archived status of the card to the given archived status.
     *
     * @param archived, boolean, the new archived status of the board
     * @author Glen Denoual
     * @see boolean
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     * Setter for the due date
     * Sets the due date of the card to the given due date.
     *
     * @param dueDate, LocalDate, the new due date of the board
     * @author Glen Denoual
     * @see LocalDate
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Setter for the participants
     * Sets the participants of the card to the given participants.
     *
     * @param participants, ArrayList<Participant>, the new participants of the board
     * @author Glen Denoual
     * @see ArrayList
     * @see Participant
     */
    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method adds a participant to the card at the end of the participants list.
     *
     * @param member ,Participant, the participant to add to the card.
	 * @author Glen Denoual
     * @see Participant
     */
    public void addMember(Participant member) {
        participants.add(member);
    }

    /**
     * This method removes a participant from the card.
     *
     * @param member ,Participant, the participant to remove from the card.
     * @author Glen Denoual
     * @see Participant
     */
    public void removeMember(Participant member) {
        participants.remove(member);
    }

	/**
	 * This method returns a string containing the list of the participants of the card.
	 *
	 * @return String, the list of the participants of the card.
	 * @author Pierre Fromont Boissel
	 * @see String
	 * @see Participant
	 * @see ArrayList
	 * @see StringBuilder
	 */
	public String showParticipants() {
		StringBuilder string = new StringBuilder("<html><ul>");
		for (Participant p : participants) {
			string.append("<li>").append(p.getMail()).append("</li>");
		}
		string.append("</ul></html>");
		return string.toString();
	}
}