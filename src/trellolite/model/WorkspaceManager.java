package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serializable;
import java.util.ArrayList;

/**
 * WorkspaceManager class
 * This class represents the workspace manager of the Trello-Lite application.
 * A workspace manager is a collection of workspaces.
 * A workspace manager can be serialized and deserialized.
 * A workspace manager can be created.
 * A workspace manager can be created with no workspaces.
 * The workspaces are initialized to an empty ArrayList.
 * <br>The methods of this class are:
 * <ul>
 *     <li>addWorkspace</li>
 *     <li>removeWorkspace</li>
 * </ul>
 *
 * @author Pierre Fromont Boissel
 * @see Workspace
 * @see Serializable
 * @see java.util.ArrayList
 */
public class WorkspaceManager implements Serializable {

    // Attributes
    private static final long serialVersionUID = -1723741337874081155L;
    private ArrayList<Workspace> workspaces;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a workspace manager with no workspaces.
     * The workspaces are initialized to an empty ArrayList.
     *
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Workspace
     */
    public WorkspaceManager() {
        workspaces = new ArrayList<>();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method returns the workspaces of the workspace manager.
     *
     * @return ArrayList<Workspace>, this is the workspaces of the workspace manager.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Workspace
     */
    public ArrayList<Workspace> getWorkspaces() {
        return workspaces;
    }

    /**
     * This method returns the workspace of the workspace manager at the given index.
     *
     * @param index ,int, this is the index of the workspace to return.
     * @return Workspace, this is the workspace of the workspace manager at the given index.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Workspace
     */
    public Workspace getWorkspace(int index) {
        return workspaces.get(index);
    }

    /**
     * This method returns the names of the workspaces of the workspace manager.
     *
     * @return String[], this is the names of the workspaces of the workspace manager.
     * @author Pierre Fromont Boissel
     * @see ArrayList
     * @see Workspace
     */
    public String[] getWorkspacesNames() {
        String[] names = new String[workspaces.size()];
        for (int i = 0; i < workspaces.size(); i++) {
            names[i] = workspaces.get(i).getName();
        }
        return names;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method adds a workspace to the workspace manager.
     *
     * @param workspace ,Workspace, this is the workspace to add.
     * @author Pierre Fromont Boissel
     * @see Workspace
     */
    public void addWorkspace(Workspace workspace) {
        workspaces.add(workspace);
    }

    /**
     * This method removes a workspace from the workspace manager.
     *
     * @param workspace ,Workspace, this is the workspace to remove.
     * @author Pierre Fromont Boissel
     * @see Workspace
     */
    public void removeWorkspace(Workspace workspace) {
        workspaces.remove(workspace);
    }
}