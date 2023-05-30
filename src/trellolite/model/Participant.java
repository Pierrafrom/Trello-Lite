package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serializable;

/**
 * Participant class
 * This class represents a participant in the Trello-Lite application.
 * A participant has a first name, a last name, a mail, a password and a role.
 * A participant can be serialized and deserialized.
 * A participant can be created with a first name, a last name, a mail and a role.
 * A participant can be created with a first name, a last name, a mail, a password and a role.
 * If a participant is created with a password, the password is set to "0000".
 * The id is automatically generated.
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
    private static int nextId = 0;

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final int id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private Role role;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * constructor of the class Participant.
     * The participant is created with a first name, a last name, a mail and a role.
     * The password is set to "0000".
     *
     * @param firstName ,String, the first name of the participant.
     * @param lastName  ,String, the last name of the participant.
     * @param mail      ,String, the mail of the participant.
     * @param role      ,Role, the role of the participant.
     * @author Pierre Fromont Boissel
     * @see Role
     */
    public Participant(String firstName, String lastName, String mail, Role role) {
        id = nextId;
        nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = "0000";
        this.role = role;
    }

    /**
     * Constructor of the class Participant.
     * The participant is created with a first name, a last name, a mail, a password and a role.
     *
     * @param firstName ,String, the first name of the participant.
     * @param lastName  ,String, the last name of the participant.
     * @param mail      ,String, the mail of the participant.
     * @param password  ,String, the password of the participant.
     * @param role      ,Role, the role of the participant.
     * @author Pierre Fromont Boissel
     */
    public Participant(String firstName, String lastName, String mail, String password, Role role) {
        id = nextId;
        nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Getter of the id of the participant.
     *
     * @return id, int, the id of the participant.
     * @author Pierre Fromont Boissel
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of the first name of the participant.
     *
     * @return firstName, String, the first name of the participant.
     * @author Pierre Fromont Boissel
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter of the last name of the participant.
     *
     * @return lastName, String, the last name of the participant.
     * @author Pierre Fromont Boissel
     */
    public String getLastName() {
        return lastName;
    }

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

    /**
     * Getter of the role of the participant.
     *
     * @return role, Role, the role of the participant.
     * @author Pierre Fomont Boissel
     * @see Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter of the first name of the participant.
     *
     * @param firstName, String, the first name of the participant.
     * @author Pierre Fromont Boissel
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter of the last name of the participant.
     *
     * @param lastName, String, the last name of the participant.
     * @author Pierre Fromont Boissel
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter of the mail of the participant.
     *
     * @param mail, String, the mail of the participant.
     * @author Pierre Fromont Boissel
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Setter of the password of the participant.
     *
     * @param password, String, the password of the participant.
     * @author Pierre Fromont Boissel
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter of the role of the participant.
     *
     * @param role, Role, the role of the participant.
     * @author Pierre Fromont Boissel
     * @see Role
     */
    public void setRole(Role role) {
        this.role = role;
    }
}