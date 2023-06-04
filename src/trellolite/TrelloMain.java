package trellolite;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import trellolite.controller.*;
import trellolite.model.*;
import trellolite.style.*;
import trellolite.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * This class is the main class of the program.
 * <br>
 * <br>
 * It contains the main method, which creates the main frame of the program, and
 * the saveData and loadData methods,
 * which save and load the data of the program.
 * <br>
 * <br>
 * It also contains the workspaceManager object, which contains all the data of
 * the program, and the
 * selectedWorkspaceIndex variable, which contains the index of the selected
 * workspace.
 * <br>
 * <br>
 * It assembles View and controller classes to create the main frame of the
 * program.
 *
 * @author Pierre Fromont Boissel
 * @see WorkspaceManager
 * @see trellolite.model
 * @see trellolite.view
 * @see trellolite.controller
 * @see javax.swing.JFrame
 * @see javax.swing.SwingUtilities
 */
public class TrelloMain {

    // -----------------------------------------------------------------------------------------------------------------
    // PUBLIC STATIC VARIABLES
    // -----------------------------------------------------------------------------------------------------------------
    public static WorkspaceManager workspaceManager = new WorkspaceManager();
    public static int selectedWorkspaceIndex;
    public static Participant currentParticipant;

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method saves the data of the workspaceManager object in the
     * data/datas.ser file.
     * <br>
     * <br>
     * <b>WARNING:</b> This method will overwrite the data/datas.ser file.
     * <br>
     * <br>
     *
     * @param workspaceManager, WorkspaceManager, the workspaceManager object to
     *                          save.
     * @throws IOException,           if the file is not found.
     * @throws FileNotFoundException, if the file is not found.
     * @author Pierre Fromont Boissel
     * @see WorkspaceManager
     * @see java.io.FileOutputStream
     * @see java.io.ObjectOutputStream
     * @see java.io.IOException
     * @see java.io.FileNotFoundException
     */
    public static void saveData(WorkspaceManager workspaceManager) {
        try {
            FileOutputStream fileOut = new FileOutputStream("data/datas.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(workspaceManager);
            objectOut.close();
            fileOut.close();
            System.out.println("The data has been successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method loads the data of the data/datas.ser file in a WorkspaceManager
     * object.
     *
     * @return WorkspaceManager, the WorkspaceManager object loaded from the
     *         data/datas.ser file.
     * @throws IOException,            if the file is not found.
     * @throws ClassNotFoundException, if the class is not found.
     * @author Pierre Fromont Boissel
     * @see WorkspaceManager
     * @see java.io.FileInputStream
     * @see java.io.ObjectInputStream
     * @see java.io.IOException
     * @see java.io.FileNotFoundException
     * @see java.lang.ClassNotFoundException
     */
    public static WorkspaceManager loadData() {
        WorkspaceManager workspaceManager = null;
        try {
            FileInputStream fileIn = new FileInputStream("data/datas.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            workspaceManager = (WorkspaceManager) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Data has been successfully loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Data has not been found.");
            e.printStackTrace();
            System.out.println("No data found.");
            workspaceManager = new WorkspaceManager();
        }
        return workspaceManager;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // MAIN METHOD
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This is the main method of the program.
     * <br>
     * <br>
     * It loads the data from the data/datas.ser file, creates the main frame, and
     * saves the data when the program is
     * closed.
     * <br>
     * <br>
     *
     * @param args, String[], the arguments of the main method.
     * @author Pierre Fromont Boissel
     * @see WorkspaceManager
     * @see javax.swing.JFrame
     * @see javax.swing.SwingUtilities
     * @see java.awt.BorderLayout
     * @see java.awt.FlowLayout
     * @see java.awt.event.WindowAdapter
     * @see java.awt.event.WindowEvent
     */
    public static void main(String[] args) {

        // -------------------------------------------------------------------------------------------------------------
        // LOAD DATA
        // -------------------------------------------------------------------------------------------------------------

        workspaceManager = loadData();

        LoginView loginView = new LoginView();
        new LoginController(loginView,
                workspaceManager);

        // -------------------------------------------------------------------------------------------------------------
        // CREATE THE MAIN FRAME AFTER LOGING IN
        // -------------------------------------------------------------------------------------------------------------
        loginView.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {

                    // Create a new JFrame, set its title to "Trello-Lite", set its size to
                    // 1200x700, set its default close
                    // operation to JFrame.EXIT_ON_CLOSE, set its location to the center of the
                    // screen, and make it visible.
                    JFrame mainFrame = new JFrame("Trello-Lite");
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setSize(1200, 700);
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);
                    mainFrame.setMinimumSize(new Dimension(650, 680));
                    mainFrame.setLayout(new BorderLayout());

                    // ---------------------------------------------------------------------------------------------------------
                    // LEFT PANEL
                    // ---------------------------------------------------------------------------------------------------------

                    // Create the left panel, set its size to 250x700, and set its layout to
                    // FlowLayout.
                    PanelStyle leftPanel = new PanelStyle(250, 700, new FlowLayout());
                    ((FlowLayout) leftPanel.getLayout()).setHgap(5);
                    ((FlowLayout) leftPanel.getLayout()).setVgap(5);
                    leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, MyStyle.BORDER_COLOR));

                    // Add the workspaceInfoView and the managerView to the left panel.
                    WorkspaceInfoView workspaceInfoView = new WorkspaceInfoView();
                    leftPanel.add(workspaceInfoView);
                    ManagerView managerView = new ManagerView();
                    leftPanel.add(managerView);

                    // Add the left panel to the main panel.
                    mainFrame.add(leftPanel, BorderLayout.WEST);

                    // ---------------------------------------------------------------------------------------------------------
                    // RIGHT PANEL
                    // ---------------------------------------------------------------------------------------------------------

                    // create the workspaceView
                    WorkspaceView workspaceView = new WorkspaceView(
                            workspaceManager.getWorkspace(selectedWorkspaceIndex));

                    // add the workspaceView to the main panel
                    mainFrame.add(workspaceView, BorderLayout.CENTER);

                    // ---------------------------------------------------------------------------------------------------------
                    // ADD CONTROLLERS
                    // ---------------------------------------------------------------------------------------------------------
                    new ManagerController(managerView, workspaceView, workspaceInfoView);

                    /*
                     * To simplify the code we add the other controllers in the constructor of the
                     * View.
                     * This avoids to create a new controller for each view.
                     */

                    // ---------------------------------------------------------------------------------------------------------
                    // SAVE DATA
                    // ---------------------------------------------------------------------------------------------------------
                    mainFrame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            // Call the saveData function to serialize and save the data
                            saveData(workspaceManager);
                        }
                    });
                });
            }
        });
    }
}
