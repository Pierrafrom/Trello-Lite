package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serial;
import java.io.Serializable;

/**
 * Participant class
 * This class represents a participant in the Trello-Lite application.
 * A participant has a first name, a last name, mail, a password and a role.
 * A participant can be serialized and deserialized.
 * A participant can be created with a first name, a last name, mail and a role.
 * A participant can be created with a first name, a last name, mail, a password and a role.
 * If a participant is created with a password, the password is set to "0000".
 * The ID is automatically generated.
 * The nextId is incremented whenever a new participant is created.
 * <br>The methods of this class are:
 * <ul>
 *     <li>setPassword</li>
 *     <li>toString</li>
 * </ul>
 *
 * @author Pierre Fromont Boissel
 * @see Board
 * @see CardList
 * @see Card
 * @see Serializable
 * @see Board
 * @see Participant
 */
public class Participant implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    @Serial
    private static final long serialVersionUID = 7895314517453172782L;
    private static int nextId = 0;

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final int ID;
    private final String FIRSTNAME;
    private final String LASTNAME;
    private String mail;
    private String password;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Constructor of the class Participant.
     * The participant is created with a first name, a last name, mail and a role.
     * The password is set to "0000".
     *
     * @param firstName String, the first name of the participant.
     * @param lastName  String, the last name of the participant.
     * @param mail      String, the mail of the participant.
     * @author Pierre Fromont Boissel
     * @see Role
     */
    public Participant(String firstName, String lastName, String mail) {
        ID = nextId;
        nextId++;
        this.FIRSTNAME = firstName;
        this.LASTNAME = lastName;
        this.mail = mail;
        this.password = "0000";
    }

    /**
     * Constructor of the class Participant.
     * The participant is created with a first name, a last name, mail, a password and a role.
     *
     * @param firstName String, the first name of the participant.
     * @param lastName  String, the last name of the participant.
     * @param mail      String, the mail of the participant.
     * @param password  String, the password of the participant.
     * @author Pierre Fromont Boissel
     */
    public Participant(String firstName, String lastName, String mail, String password) {
        ID = nextId;
        nextId++;
        this.FIRSTNAME = firstName;
        this.LASTNAME = lastName;
        this.mail = mail;
        this.password = password;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Getter of the mail of the participant.
     *
     * @return mail, String, the mail of the participant.
     * @author Pierre Fromont Boissel
     */
    public String getMail() {
        return mail;
    }

    /**
     * Getter of the password of the participant.
     *
     * @return password, String, the password of the participant.
     * @author Pierre Fromont Boissel
     */
    public String getPassword() {
        return password;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method transforms the participant into a String.
     * It returns the mail of the participant.
     * 
     * @return mail, String, the mail of the participant.
     * @author Pierre Fromont Boissel
     * @see String
     * @see Participant
     * @see trellolite.model.Participant#getMail()
     */
    public String toString() {
        return getMail();
    }
}