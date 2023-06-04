package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import trellolite.TrelloMain;
import trellolite.model.Participant;
import trellolite.model.Role;
import trellolite.model.Workspace;
import trellolite.model.WorkspaceManager;
import trellolite.style.OptionPaneStyle;
import trellolite.view.LoginView;

/**
 * LoginController class
 * This class is the controller of the login view.
 * It is used to manage the login and signup actions.
 * <br>The methods of this class are:
 * <ul>
 *     <li>actionPerformed</li>
 *     <li>login</li>
 *     <li>signup</li>
 * </ul>
 * @author Augustin Lecomte
 * @see ActionListener
 * @see LoginView
 * @see WorkspaceManager
 * @see Participant
 * @see Role
 * @see Workspace
 * @see OptionPaneStyle
 */
public class LoginController implements ActionListener{

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private WorkspaceManager workspaceManager;
    private LoginView loginView;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------
    /**
     * This constructor creates a login controller with the given login view and workspace manager.
     * The login button and the signup button are added to the action listener.
     * 
     * @param loginView ,LoginView, this is the login view.
     * @param workspaceManager ,WorkspaceManager, this is the workspace manager.
     * @author Augustin Lecomte
     * @see ActionListener
     * @see LoginView
     * @see WorkspaceManager
     */
    public LoginController(LoginView loginView, WorkspaceManager workspaceManager) {
        
        this.loginView = loginView;
        this.workspaceManager = workspaceManager;

        this.loginView.getLoginButton().addActionListener(this);
        this.loginView.getSignupButton().addActionListener(this);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method is used to manage the login and signup actions.
     * If the login button is clicked, the login method is called.
     * If the signup button is clicked, the signup method is called.
     * 
     * @param e ,ActionEvent, this is the event of the action performed by the user.
     * @author Augsutin Lecomte
     * @see ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginView.getLoginButton()) {
           login();
        } else if(e.getSource() == loginView.getSignupButton()) {
            signup();
        }
    }

    /**
     * This method is used to login the user.
     * The method checks if the mail and password are correct.
     * If the mail and password are correct, the user is logged in.
     * If the mail and password are not correct, an error message is displayed.
     * 
     * @author Augustin Lecomte
     * @see OptionPaneStyle
     * @see WorkspaceManager
     * @see Participant
     * @see Workspace
     * @see Role
     * @see ArrayList
     * @see JOptionPane
     */
    private void login(){
        ArrayList<Participant> participants;

        String mail = loginView.getLoginTextFields()[0].getText();
        String password = loginView.getLoginTextFields()[1].getText();

        for(Workspace workspace : workspaceManager.getWorkspaces()) {
            participants = workspace.getMembers();
            
            for(Participant participant : participants) {

                if(participant.getMail().equals(mail) && participant.getPassword().equals(password)){
                    // Changing the current participant and the selected workspace of the main class
                    // to display the correct workspace, and being able to manage the permissions
                    TrelloMain.currentParticipant = participant;
                    TrelloMain.selectedWorkspaceIndex = workspaceManager.getWorkspaces().indexOf(workspace);
                    loginView.dispose();
                    return;
                }
            }
        }
        OptionPaneStyle.showMessageDialog(null, "Wrong mail or password.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    /**
     * This method is used to signup the user.
     * The method checks if the mail is already used.
     * If the mail is already used, an error message is displayed.
     * If the mail is not already used, the user is signed up.
     * 
     * @author Augustin Lecomte
     * @see OptionPaneStyle
     * @see WorkspaceManager
     * @see Participant
     * @see Workspace
     * @see Role
     * @see ArrayList
     * @see JOptionPane
     */
    private void signup(){

        String firstName = loginView.getSignupTextFields()[0].getText();
        String lastName = loginView.getSignupTextFields()[1].getText();
        String mail = loginView.getSignupTextFields()[2].getText();
        String password = loginView.getSignupTextFields()[3].getText();

        for(Workspace workspace : workspaceManager.getWorkspaces()) {
            ArrayList<Participant> participants = workspace.getMembers();
            
            for(Participant participant : participants) {
                if (participant.getMail().equals(mail)) {
                    OptionPaneStyle.showMessageDialog(null, "This mail is already used.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        if(firstName.isEmpty() || lastName.isEmpty() || mail.isEmpty() || password.isEmpty()) {
            OptionPaneStyle.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int lastWorkspace;

        Participant newParticipant = new Participant(firstName, lastName, mail, password);
        TrelloMain.currentParticipant = newParticipant;
        workspaceManager.addWorkspace(new Workspace(lastName +"'s workspace", newParticipant));
        
        lastWorkspace = workspaceManager.getWorkspaces().size() - 1;
        TrelloMain.selectedWorkspaceIndex = lastWorkspace;
        
        loginView.dispose();
        return;
    }
}
