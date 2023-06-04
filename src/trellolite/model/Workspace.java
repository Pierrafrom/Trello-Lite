package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
 * <br>The methods of this class are:
 * <ul>
 *     <li>addList</li>
 *     <li>removeList</li>
 *     <li>getList</li>
 *     <li>getLists</li>
 * </ul>
 *
 * @author Pierre Fromont Boissel
 * @author Augustin
 * @see CardList
 * @see Card
 * @see Serializable
 * @see java.util.ArrayList
 */
public class Workspace implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    // STATIC ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    @Serial
    private static final long serialVersionUID = 6016683277505522200L;
    private static int nextId = 0;

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private final int id;
    private String name;
    private Map<Participant, Role> members;
    private ArrayList<Board> boards;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a workspace with the given name.
     * The workspace is created with the given creator as a member and with one
     * board.
     *
     * @param name    String, this is the name of the workspace.
     * @param creator Participant, this is the creator of the workspace.
     * @author Pierre Fromont Boissel
     * @author Augustin Lecomte
     * @see Participant
     * @see Board
     * @see ArrayList
     * @see Role
     * @see CardList
     * @see Card
     * @see Serializable
     */
    public Workspace(String name, Participant creator) {
        id = nextId++;
        this.name = name;
        members = new HashMap<>();
        members.put(creator, Role.ADMIN);
        boards = new ArrayList<>();
        boards.add(new Board("new board"));
    }

    /**
     * This constructor creates a workspace with the given name.
     *
     * @param name String, this is the name of the workspace.
     * @author Pierre Fromont Boissel
     * @author Augustin Lecomte
     * @see Participant
     * @see Board
     * @see ArrayList
     * @see Role
     * @see CardList
     * @see Card
     * @see Serializable
     */
    public Workspace(String name) {
        id = nextId++;
        this.name = name;
        members = new HashMap<>();
        boards = new ArrayList<>();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the id of the workspace.
     *
     * @return int, this is the id of the workspace.
     * @author Pierre Fromont Boissel
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns the name of the workspace.
     *
     * @return String, this is the name of the workspace.
     * @author Pierre Fromont Boissel
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the members of the workspace.
     *
     * @return ArrayList<Participant>, this is the list of the members of the workspace.
	 * @author Augustin Lecomte
     * @see Participant
     */
    public ArrayList<Participant> getMembers() {
        return new ArrayList<>(members.keySet());
    }

    /**
     * This method returns the role of the given participant in the workspace.
     *
     * @param participant Participant, this is the participant to get the role of.
     * @return Role, this is the role of the given participant in the workspace.
     * @author Augustin Lecomte
     * @see Participant
     * @see Role
     */
    public Role getRole(Participant participant) {
        return members.get(participant);
    }

    /**
     * This method returns the boards of the workspace.
     *
     * @return ArrayList<Board>, this is the list of the boards of the workspace.
     * @author Pierre Fromont Boissel
     * @see Board
     */
    public ArrayList<Board> getBoards() {
        return boards;
    }

    /**
     * This method sets the name of the workspace.
     *
     * @param name String, this is the name of the workspace.
     * @author Pierre Fromont Boissel
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the boards of the workspace.
     *
     * @param boards ,ArrayList<Board>, this is the list of the boards of the workspace.
     * @author Pierre Fromont Boissel
     * @see Board
     */
    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * toString method of the class Workspace.
     * This method is used to display information about the workspace in HTML format
     *
     * @return String, this is the string representation of the workspace in HTML format.
     * @author Pierre Fromon Boissel
     * @author Augustin Lecomte
     * @see StringBuilder
     * @see Participant
     * @see ArrayList
     * @see Board
     */
    public String toStringHTML() {
        StringBuilder str = new StringBuilder("<html><strong>Workspace</strong> : " + name + "<br>");
        str.append("<strong>Members</strong> : <br>");

        ArrayList<Participant> participants = new ArrayList<>(this.members.keySet());

        for (Participant member : participants) {
            str.append("- ").append(member.getMail()).append("<br>");
        }
        str.append("<strong>Boards</strong> : <br>");
        for (Board board : boards) {
            str.append("- ").append(board.getName()).append("<br>");
        }
        str.append("</html>");
        return str.toString();
    }

    /**
     * This method adds a member to the workspace.
     *
     * @param member Participant, this is the member to add to the workspace.
     * @author Pierre Fromont Boissel
     * @see Participant
     */
    public void addMember(Participant member, Role role) {
        members.put(member, role);
    }

    /**
     * This method removes a member from the workspace.
     *
     * @param member Participant, this is the member to remove from the workspace.
     * @author Pierre Fromont Boissel
     * @see Participant
     */
    public void removeMember(Participant member) {
        members.remove(member);
    }

    /**
     * This method adds a board to the workspace.
     *
     * @param board : arg1 : Board, this is the board to add to the workspace.
     * @author Pierre Fromont Boissel
     * @see Board
     */
    public void addBoard(Board board) {
        boards.add(board);
    }

    /**
     * This method removes a board from the workspace.
     *
     * @param board Board, this is the board to remove from the workspace.
     * @author Pierre Fromont Boissel
     * @see Board
     */
    public void removeBoard(Board board) {
        boards.remove(board);
    }
}