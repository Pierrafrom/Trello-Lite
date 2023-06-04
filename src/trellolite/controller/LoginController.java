package trellolite.controller;

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

public class LoginController implements ActionListener{

    private WorkspaceManager workspaceManager;
    private LoginView loginView;

    public LoginController(LoginView loginView, WorkspaceManager workspaceManager) {
        this.loginView = loginView;
        this.workspaceManager = workspaceManager;

        this.loginView.getLoginButton().addActionListener(this);
        this.loginView.getSignupButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginView.getLoginButton()) {
           login();
        } else if(e.getSource() == loginView.getSignupButton()) {
            signup();
        }
    }

    private void login(){
        ArrayList<Participant> participants;

        String mail = loginView.getLoginTextFields()[0].getText();
        String password = loginView.getLoginTextFields()[1].getText();

        for(Workspace workspace : workspaceManager.getWorkspaces()) {
            participants = workspace.getMembers();
            
            for(Participant participant : participants) {

                if(participant.getMail().equals(mail) && participant.getPassword().equals(password)){
                    TrelloMain.currentParticipant = participant;
                    loginView.dispose();
                    return;
                }
            }
        }
        OptionPaneStyle.showMessageDialog(null, "Wrong mail or password.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void signup(){
        ArrayList<Participant> participants;

            String firstName = loginView.getSignupTextFields()[0].getText();
            String lastName = loginView.getSignupTextFields()[1].getText();
            String mail = loginView.getSignupTextFields()[2].getText();
            String password = loginView.getSignupTextFields()[3].getText();

            for(Workspace workspace : workspaceManager.getWorkspaces()) {
                participants = workspace.getMembers();
                
                for(Participant participant : participants) {
                    if (participant.getMail().equals(mail)) {
                        OptionPaneStyle.showMessageDialog(null, "This mail is already used.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if(firstName.isEmpty() || lastName.isEmpty() || mail.isEmpty() || password.isEmpty()) {
                    OptionPaneStyle.showMessageDialog(null, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Participant newParticipant = new Participant(firstName, lastName, mail, password, Role.MEMBER);
                workspace.addMember(newParticipant);
                TrelloMain.currentParticipant = newParticipant;
                workspaceManager.addWorkspace(new Workspace(lastName +"'s workspace", newParticipant));
                loginView.dispose();
                return;
            }
    }
}
