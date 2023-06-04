package trellolite.controller;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.TrelloMain;
import trellolite.model.Board;
import trellolite.model.Participant;
import trellolite.model.Role;
import trellolite.model.Workspace;
import trellolite.style.ButtonStyle;
import trellolite.style.ComboBoxStyle;
import trellolite.style.OptionPaneStyle;
import trellolite.view.ManagerView;
import trellolite.view.WorkspaceInfoView;
import trellolite.view.WorkspaceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * This class is the controller of the ManagerView class.
 * <br>
 * <br>
 * It contains the listeners of the ManagerView class.
 * There is one listener for each button and comboBox of the ManagerView class.
 * <br>
 * <br>
 *
 * @author Pierre Fromont Boissel
 * @see ManagerView
 * @see WorkspaceView
 * @see WorkspaceInfoView
 * @see trellolite.model
 * @see trellolite.style
 * @see javax.swing.JComboBox
 * @see javax.swing.JButton
 * @see java.awt.event.ActionListener
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.WindowAdapter
 * @see java.awt.event.WindowEvent
 * @see java.util.ArrayList
 */
public class ManagerController {

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------
    private ManagerView managerView;
    private WorkspaceView workspaceView;
    private WorkspaceInfoView workspaceInfoView;

    private ComboBoxStyle workspaceComboBox;
    private ButtonStyle NewButton;
    private ButtonStyle DeleteButton;
    private ComboBoxStyle actionComboBox;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates the listeners of the ManagerView class.
     * <br>
     * <br>
     * It creates a listener for each button and comboBox of the ManagerView class.
     * <br>
     * <br>
     *
     * @param managerView       the ManagerView object of the program
     * @param workspaceView     the WorkspaceView object of the program
     * @param workspaceInfoView the WorkspaceInfoView object of the program
     * @author Pierre Fromont Boissel
     * @see ManagerView
     * @see WorkspaceView
     * @see WorkspaceInfoView
     * @see trellolite.model
     * @see trellolite.style
     * @see javax.swing.JComboBox
     * @see javax.swing.JButton
     * @see java.awt.event.ActionListener
     */
    public ManagerController(ManagerView managerView, WorkspaceView
            workspaceView, WorkspaceInfoView workspaceInfoView) {

        this.managerView = managerView;
        this.workspaceView = workspaceView;
        this.workspaceInfoView = workspaceInfoView;

        this.workspaceComboBox = managerView.getWorkspaceComboBox();
        this.NewButton = managerView.getNewWorkspaceButton();
        this.DeleteButton = managerView.getDeleteWorkspaceButton();
        this.actionComboBox = managerView.getActionsComboBox();

        this.NewButton.addActionListener(new NewButtonListener());
        this.DeleteButton.addActionListener(new DeleteButtonListener());
        this.workspaceComboBox.addActionListener(new WorkspaceComboBoxListener());
        this.actionComboBox.addActionListener(new ActionComboBoxListener());

    }

    // -----------------------------------------------------------------------------------------------------------------
    // LISTENERS
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // NEW BUTTON LISTENER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the NewButton of the ManagerView class.
     * <br>
     * <br>
     * It creates a new workspace when the NewButton is clicked.
     * <br>
     * <br>
     * It also updates the workspace combobox of the ManagerView class.
     * <br>
     *
     * @author Pierre Fromont Boissel
     * @see ManagerView
     * @see WorkspaceView
     * @see WorkspaceInfoView
     * @see trellolite.model
     * @see trellolite.style
     * @see javax.swing.JComboBox
     * @see javax.swing.JButton
     * @see java.awt.event.ActionListener
     * @see java.awt.event.ActionEvent
     */
    private class NewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new workspace
            addWorkspace();
        }

        /**
         * This method creates a new workspace.
         * <p>
         * It asks the user to enter the name of the new workspace.
         * It also checks if the name of the new workspace is not empty and if it does not already exist.
         * If the name of the new workspace is valid, it creates a new workspace and adds it to the workspace manager.
         * It also updates the workspace combobox of the ManagerView class.
         * If the name of the new workspace is not valid, it asks the user to enter a new name.
         * If the user cancels the creation of the new workspace, it does nothing.
         * If the user closes the dialog, it does nothing.
         * </p>
         *
         * @author Pierre Fromont Boissel
         * @see trellolite.model.WorkspaceManager
         * @see trellolite.model.Workspace
         * @see trellolite.style.OptionPaneStyle
         * @see javax.swing.JOptionPane
         */
        private void addWorkspace() {
            // Create a new OptionPaneStyle object to display dialogs with the same style
            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();

            // Prompt the user to enter the name of the new workspace
            Object workspaceNameObj = optionPaneStyle.showInputDialog(null,
                    "Enter the name of the new workspace:", "New workspace creation",
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);

            // Check if the user entered a workspace name
            if (workspaceNameObj != null) {
                // Convert the object to a string and trim leading and trailing spaces
                String workspaceName = workspaceNameObj.toString().trim();

                // While the workspace name is empty, ask the user to enter a new one
                while (workspaceName.isEmpty()) {
                    // Display an error message indicating that the workspace name cannot be empty
                    optionPaneStyle.showMessageDialog(null, "Workspace name cannot be empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the workspace name again
                    workspaceNameObj = optionPaneStyle.showInputDialog(null,
                            "Enter the name of the new workspace:", "New workspace creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    workspaceName = workspaceNameObj.toString().trim();
                }

                // Get the names of the existing workspaces
                ArrayList<String> names = new ArrayList<String>();
                for (Workspace workspace : TrelloMain.workspaceManager.getWorkspaces()) {
                    names.add(workspace.getName());
                }

                // While the workspace name already exists, ask the user to enter a new one
                while (names.contains(workspaceName)) {
                    // Display an error message indicating that the workspace name already exists
                    optionPaneStyle.showMessageDialog(null, "Workspace name already exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the workspace name again
                    workspaceNameObj = optionPaneStyle.showInputDialog(null,
                            "Enter the name of the new workspace:", "New workspace creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    workspaceName = workspaceNameObj.toString().trim();
                }

                // Create the new workspace with the user-provided name
                Workspace workspace = new Workspace(workspaceName, TrelloMain.currentParticipant);
                TrelloMain.workspaceManager.addWorkspace(workspace);

                // Update the workspace combobox
                managerView.updateWorkspaceComboBox();

                // Select the new workspace in the combobox
                TrelloMain.selectedWorkspaceIndex = TrelloMain.workspaceManager.getWorkspaces().size() - 1;
                managerView.getWorkspaceComboBox().setSelectedIndex(TrelloMain.selectedWorkspaceIndex);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // DELETE BUTTON LISTENER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the DeleteButton of the ManagerView class.
     * <p>
     * It deletes the selected workspace when the DeleteButton is clicked.
     * It also updates the workspace combobox of the ManagerView class.
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see ManagerView
     * @see WorkspaceView
     * @see WorkspaceInfoView
     * @see trellolite.model
     * @see trellolite.style
     * @see javax.swing.JComboBox
     * @see javax.swing.JButton
     */
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getRole(TrelloMain.currentParticipant) != Role.ADMIN){
                String message = "To delete a workspace, you must be an admin!";
                OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                optionPaneStyle.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Delete the Workspace
            // Ask for confirmation
            ConfirmationDialog dialogController = new ConfirmationDialog();
            String message = "Are you sure you want to delete the workspace : " +
                    TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getName() + " ?";
            String title = "Confirmation";
            boolean result = dialogController.showConfirmationDialog(message, title);

            // If the user selected "Yes"
            if (result) {
                // Delete the Workspace
                TrelloMain.workspaceManager.removeWorkspace(
                        TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex));
                // Update the workspace combobox
                managerView.updateWorkspaceComboBox();
                TrelloMain.selectedWorkspaceIndex -= 1;
                managerView.getWorkspaceComboBox().setSelectedIndex(TrelloMain.selectedWorkspaceIndex);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // WORKSPACE COMBOBOX LISTENER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the WorkspaceComboBox of the ManagerView class.
     * <p>
     * It changes the selected workspace when the WorkspaceComboBox is changed.
     * It also updates the workspace view and the workspace info view.
     * It also updates the selected workspace index.
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see ManagerView
     * @see WorkspaceView
     * @see WorkspaceInfoView
     * @see trellolite.model
     * @see javax.swing.JComboBox
     * @see java.awt.event.ActionListener
     * @see java.awt.event.ActionEvent
     */
    private class WorkspaceComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TrelloMain.selectedWorkspaceIndex = workspaceComboBox.getSelectedIndex();
            workspaceView.changeWorkspace(TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex));
            workspaceInfoView.update();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ACTION COMBOBOX LISTENER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This class is the listener of the ActionComboBox of the WorkspaceInfoView class.
     * <p>
     * It handles the actions of the ActionComboBox.
     * It also updates the corresponding view.
     * </p>
     * <p>
     * The actions are :
     * <ul>
     *     <li>Change the name of the workspace</li>
     *     <li>Add a member</li>
     *     <li>Remove a member</li>
     *     <li>Change the role of a member</li>
     * </ul>
     * </p>
     *
     * @author Pierre Fromont Boissel
     * @see WorkspaceInfoView
     * @see trellolite.model
     * @see trellolite.style
     * @see javax.swing.JComboBox
     * @see java.awt.event.ActionListener
     * @see java.awt.event.ActionEvent
     * @see java.util.ArrayList
     * @see java.lang.String
     */
    private class ActionComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getRole(TrelloMain.currentParticipant) != Role.ADMIN){
                String message = "To modify the name, delete or add a new Board and add or remove a member, you must be an admin!";
                OptionPaneStyle optionPaneStyle = new OptionPaneStyle();
                optionPaneStyle.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            switch (actionComboBox.getSelectedIndex()) {
                case 0 -> {
                    // Change the name of the workspace
                    changeName();
                }
                case 1 -> {
                    // Add a Member
                    // fields
                    ArrayList<String> fields = new ArrayList<>();
                    fields.add("email :");
                    fields.add("first name :");
                    fields.add("last name :");
                    fields.add("password :");
                    // Combo boxes
                    ArrayList<ComboBoxStyle> comboBoxes = new ArrayList<>();
                    String[] Roles = {"Member", "Admin", "Observer"};
                    comboBoxes.add(new ComboBoxStyle(Roles, 0, 0, 14));

                    // combo boxes labels
                    ArrayList<String> comboBoxesLabels = new ArrayList<>();
                    comboBoxesLabels.add("Role :");
                    createNewParticipantController(fields, comboBoxes, comboBoxesLabels);
                }
                case 2 -> {
                    // Remove a Member
                    removeMember();
                }
                case 3 -> {
                    // Add a Board
                    createNewBoard();
                }
            }
        }

        // -----------------------------------------------------------------------------------------------------------------
        // METHODS
        // -----------------------------------------------------------------------------------------------------------------

        /**
         * This method creates a new board and adds it to the selected workspace.
         * <p>
         * It prompts the user to enter the name of the new board.
         * Then it creates a new board and adds it to the selected workspace.
         * </p>
         * 
         * @author Pierre Fromont Boissel
         * @see ManagerView
         * @see trellolite.model
         * @see trellolite.style
         * @see javax.swing.JOptionPane
         */
        private void createNewBoard(){
            // Create a new OptionPaneStyle object to display dialogs with the same style
            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();

            // Prompt the user to enter the name of the new board
            Object boardNameObj = optionPaneStyle.showInputDialog(null,
                    "Enter the name of the new Board:", "New Board creation",
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);

            // Check if the user entered a board name
            if (boardNameObj != null) {
                // Convert the object to a string and trim leading and trailing spaces
                String boardName = boardNameObj.toString().trim();

                // While the board name is empty, ask the user to enter a new one
                while (boardName.isEmpty()) {
                    // Display an error message indicating that the board name cannot be empty
                    optionPaneStyle.showMessageDialog(null, "Board name cannot be empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the board name again
                    boardNameObj = optionPaneStyle.showInputDialog(null,
                            "Enter the name of the new board:", "New board creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    boardName = boardNameObj.toString().trim();
                }

                // Get the names of the existing boards
                ArrayList<String> names = new ArrayList<String>();
                for (Board board : TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).getBoards()) {
                    names.add(board.getName());
                }

                // While the board name already exists, ask the user to enter a new one
                while (names.contains(boardName)) {
                    // Display an error message indicating that the board name already exists
                    optionPaneStyle.showMessageDialog(null, "Board name already exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the board name again
                    boardNameObj = optionPaneStyle.showInputDialog(null,
                            "Enter the name of the new board:", "New board creation",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    // Convert the object to a string and trim leading and trailing spaces
                    boardName = boardNameObj.toString().trim();
                }

                // Create the new board with the user-provided name
                Board board = new Board(boardName);
                TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).addBoard(board);

                // Update the workspace info view and the workspace view
                workspaceInfoView.update();
                workspaceView.update();

            }
        }

        /**
         * This method creates a new ParticipantConstructor object for the participant controller window.
         * <p>
         * It allows to create a new participant.
         * </p>
         *
         * @param fields           the fields of the controller window
         * @param comboBoxes       the combo boxes of the controller window
         * @param comboBoxesLabels the labels of the combo boxes of the controller window
         * @author Pierre Fromont Boissel
         * @see ParticipantConstructor
         * @see trellolite.style
         * @see java.awt.event.WindowAdapter
         * @see java.awt.event.WindowEvent
         * @see java.util.ArrayList
         * @see java.lang.String
         */
        public void createNewParticipantController(ArrayList<String> fields, ArrayList<ComboBoxStyle> comboBoxes,
                                                   ArrayList<String> comboBoxesLabels) {
            // Create a new ParticipantConstructor object for the participant constructor
            ParticipantConstructor constructor = new ParticipantConstructor("Add a participant",
                    400, 300, fields, comboBoxes, comboBoxesLabels);

            // Add a WindowListener to the constructor window
            constructor.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Check if the constructor was not cancelled by the user
                    if (!constructor.isCancelled()) {
                        // Get the emails of the existing participants in the selected workspace
                        ArrayList<String> mailsOfWorkspace = new ArrayList<>();
                        for (Participant participant : TrelloMain.workspaceManager
                                .getWorkspace(TrelloMain.selectedWorkspaceIndex).getMembers()) {
                            mailsOfWorkspace.add(participant.getMail());
                        }

                        // Get the main of the existing particpant in the whole app
                        ArrayList<String> mailsOfApp = new ArrayList<>();
                        for (Workspace workspace : TrelloMain.workspaceManager.getWorkspaces()) {
                            for (Participant participant : workspace.getMembers()) {
                                mailsOfApp.add(participant.getMail());
                            }
                        }

                        // Check if any of the fields is empty, display an error message and create a new constructor
                        if (constructor.getFieldsContent().get(0).equals("") ||
                                constructor.getFieldsContent().get(1).equals("") ||
                                constructor.getFieldsContent().get(2).equals("")) {
                            OptionPaneStyle errorMessage = new OptionPaneStyle();
                            errorMessage.showMessageDialog(null, "Please, can you fill all " +
                                            "the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                            // Create a new constructor if any of the fields is empty
                            createNewParticipantController(fields, comboBoxes, comboBoxesLabels);
                        }
                        // Check if the email already exists, display an error message and create a new constructor
                        else if (mailsOfWorkspace.contains(constructor.getFieldsContent().get(0))) {
                            OptionPaneStyle errorMessage = new OptionPaneStyle();
                            errorMessage.showMessageDialog(null, "This email is already in this workspace!",
                                                     "Error", JOptionPane.ERROR_MESSAGE);
                            // Create a new constructor if the email already exists
                            createNewParticipantController(fields, comboBoxes, comboBoxesLabels);
                        }
                        // Add the already existing participant to the workspace by the email
                        else if (mailsOfApp.contains(constructor.getFieldsContent().get(0))){
                            for (Workspace workspace : TrelloMain.workspaceManager.getWorkspaces()) {
                                for (Participant participant : workspace.getMembers()) {
                                    if (participant.getMail().equals(constructor.getFieldsContent().get(0))) {
                                        System.out.println("Add the already existing participant to the workspace by the email");
                                        Role role = null;
                                        if (constructor.getComboBoxesContent().get(0).equals("Admin")) {
                                            role = Role.ADMIN;
                                        } else if (constructor.getComboBoxesContent().get(0).equals("Member")) {
                                            role = Role.MEMBER;
                                        } else if (constructor.getComboBoxesContent().get(0).equals("Observer")) {
                                            role = Role.OBSERVER;
                                        }
                                        TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex).addMember(participant, role);
                                        workspaceInfoView.update();
                                        return;
                                    }
                                }
                            }
                        }
                        // Create a new participant with the provided information
                        else {
                            Role role = null;
                            if (constructor.getComboBoxesContent().get(0).equals("Admin")) {
                                role = Role.ADMIN;
                            } else if (constructor.getComboBoxesContent().get(0).equals("Member")) {
                                role = Role.MEMBER;
                            } else if (constructor.getComboBoxesContent().get(0).equals("Observer")) {
                                role = Role.OBSERVER;
                            }
                            
                            // print data
                            System.out.println("firstname: " + constructor.getFieldsContent().get(1));
                            System.out.println("lastname: " + constructor.getFieldsContent().get(2));
                            System.out.println("Mail: " + constructor.getFieldsContent().get(0));
                            System.out.println("Role: " + role);
                            System.out.println("password: " + constructor.getFieldsContent().get(3));

                            Participant newParticipant = new Participant(constructor.getFieldsContent().get(1),
                                    constructor.getFieldsContent().get(2),
                                    constructor.getFieldsContent().get(0),
                                    constructor.getFieldsContent().get(3));

                            // Add the new participant to the workspace
                            TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex)
                                    .addMember(newParticipant, role);
                            // Update the info panel
                            workspaceInfoView.update();
                        }
                    }
                }
            });
        }

        private void removeMember(){
            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();

             // Prompt the user to enter the name of the new member
            Object memberMailObject = optionPaneStyle.showInputDialog(null,
                "Enter the mail of the member", "Remove a Member",
                JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if (memberMailObject != null) {
                // Convert the object to a string and trim leading and trailing spaces
                String memberMail = memberMailObject.toString().trim();
                
                // While the member mail is empty, ask the user to enter a new one
                while (memberMail.isEmpty()) {
                    // Display an error message indicating that the member mail already exists
                    optionPaneStyle.showMessageDialog(null, "Enter mail ",
                            "Error", JOptionPane.ERROR_MESSAGE);

                     // Prompt the user to enter the name of the member to be deleted
                    memberMailObject = optionPaneStyle.showInputDialog(null, "Enter the mail of the member",
                            "Remove a Member",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    memberMail = memberMailObject.toString().trim();
                }

                ArrayList<String> mails = new ArrayList<String>();
                for (Participant member : TrelloMain.workspaceManager
                        .getWorkspace(TrelloMain.selectedWorkspaceIndex).getMembers()) {
                    mails.add(member.getMail());
                }
                // While the member  doesn't exists, ask the user to enter a new one
                while (!mails.contains(memberMail)) {
                    optionPaneStyle.showMessageDialog(null, "Member mail doesn't exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    // Prompt the user to enter the name of the new board
                    memberMailObject = optionPaneStyle.showInputDialog(null, "Enter the mail of the member",
                            "Remove a Member",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    memberMail = memberMailObject.toString().trim();
                }

                // Remove the member
                Participant memberToRemove = null;
                for (Participant member : TrelloMain.workspaceManager
                        .getWorkspace(TrelloMain.selectedWorkspaceIndex).getMembers()) {
                    if (member.getMail().equals(memberMail)) {
                        memberToRemove = member;
                    }
                }

                TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex)
                        .removeMember(memberToRemove);
                
                // Update the workspace info view
                workspaceInfoView.update();
            }
        }
        
        private void changeName(){
            OptionPaneStyle optionPaneStyle = new OptionPaneStyle();

            // Prompt the user to enter the new name of the workspace
            Object workspaceNameObject = optionPaneStyle.showInputDialog(null,
                    "Enter the new name of the workspace", "Change the name of the workspace",
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if (workspaceNameObject != null) {
                // Convert the object to a string and trim leading and trailing spaces
                String workspaceName = workspaceNameObject.toString().trim();

                // While the workspace name is empty, ask the user to enter a new one
                while (workspaceName.isEmpty()) {
                    // Display an error message indicating that the workspace name already exists
                    optionPaneStyle.showMessageDialog(null, "Enter the new name of the workspace",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the new name of the workspace
                    workspaceNameObject = optionPaneStyle.showInputDialog(null,
                            "Enter the new name of the workspace", "Change the name of the workspace",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    workspaceName = workspaceNameObject.toString().trim();
                }

                // get workspaces names
                ArrayList<String> workspacesNames = new ArrayList<String>();
                for (Workspace workspace : TrelloMain.workspaceManager.getWorkspaces()) {
                    workspacesNames.add(workspace.getName());
                }

                // While the workspace name already exists, ask the user to enter a new one
                while (workspacesNames.contains(workspaceName)) {
                    // Display an error message indicating that the workspace name already exists
                    optionPaneStyle.showMessageDialog(null, "This workspace name already exists!",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    // Prompt the user to enter the new name of the workspace
                    workspaceNameObject = optionPaneStyle.showInputDialog(null,
                            "Enter the new name of the workspace", "Change the name of the workspace",
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    workspaceName = workspaceNameObject.toString().trim();
                }

                // Change the name of the workspace
                TrelloMain.workspaceManager.getWorkspace(TrelloMain.selectedWorkspaceIndex)
                        .setName(workspaceName);

                // Update the workspace info view
                workspaceInfoView.update();
                // update the combo box
                managerView.updateWorkspaceComboBox();
            }
        }
    }
}
