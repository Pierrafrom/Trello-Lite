package trellolite.view;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import javax.swing.*;

import java.awt.*;

import trellolite.style.*;

/**
 * LoginView class
 * This class represents the login view of the Trello-Lite application.
 * Extends JFrame.
 * The login view is composed of:
 * <ul>
 *     <li>the title</li>
 *     <li>the login form</li>
 *     <li>the signup form</li>
 * </ul>
 * The login view is composed of a main panel with a border layout.
 * The north panel contains the title.
 * The center panel contains the login and signup forms.
 * <br>The methods of this class are:
 * <ul>
 *     <li>createNorthPanel</li>
 *     <li>createCenterPanel</li>
 *     <li>createLoginForm</li>
 *     <li>createSeparators</li>
 *     <li>createSignupForm</li>
 * </ul>
 * <br>This class extends JFrame.
 * @author Augustin Lecomte
 * @see JFrame
 * @see PanelStyle
 * @see LabelStyle
 * @see TextField
 * @see ButtonStyle
 * @see FlowLayout
 * @see GridBagLayout
 * @see GridBagConstraints
 * @see JSeparator
 * @see BorderLayout
 * @see Dimension
 * @see MyStyle
 * @see TextType
 * @see SwingConstants
 * @see BorderFactory
 */
public class LoginView extends JFrame{

    // -----------------------------------------------------------------------------------------------------------------
    // ATTRIBUTES
    // -----------------------------------------------------------------------------------------------------------------

    private final int WIDTH = 500;
    private final int HEIGHT = 470;

    ButtonStyle loginButton;
    ButtonStyle signupButton;

    TextField mailLoginTextField;
    TextField passwordLoginTextField;

    TextField firstNameTextField;
    TextField lastNameTextField;
    TextField mailSignupTextField;
    TextField passwordSignupTextField;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This constructor creates a login view.
     * The login view is composed of:
     * <ul>
     *     <li>the title</li>
     *     <li>the login form</li>
     *     <li>the signup form</li>
     * </ul>
     * The login view is composed of a main panel with a border layout.
     * The north panel contains the title.
     * The center panel contains the login and signup forms.
     * 
     * @author Augustin Lecomte
     */
    public LoginView() {
        // Create the main frame and set its properties.
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create the main panel that will contain all the other panels.
        PanelStyle mainPanel = new PanelStyle(WIDTH, HEIGHT, new BorderLayout());
        mainPanel.setBackground(MyStyle.BACKGROUND_COLOR);
        add(mainPanel);

        mainPanel.add(createNorthPanel(), BorderLayout.NORTH);

        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);

        setSize(new Dimension(WIDTH, HEIGHT));
    }    

    // -----------------------------------------------------------------------------------------------------------------
    // METHODS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * This method creates the north panel of the login view.
     * The north panel contains the title.
     * 
     * @return PanelStyle, the north panel of the login view.
     * @author Augustin Lecomte
     * @see PanelStyle
     * @see BorderLayout
     * @see MyStyle
     * @see LabelStyle
     * @see TextType
     * @see SwingConstants
     */
    private PanelStyle createNorthPanel() {
        final String title = "Welcome to Trello-Lite";

        PanelStyle northPanel = new PanelStyle(WIDTH, 50, new BorderLayout());
        northPanel.setBackground(MyStyle.BACKGROUND_COLOR);
        LabelStyle loginLabel = new LabelStyle(title, TextType.TITLE, SwingConstants.CENTER);
        northPanel.add(loginLabel, BorderLayout.CENTER);
        return northPanel;
    }

    /**
     * This method creates the center panel of the login view.
     * The center panel contains the login and signup forms.
     * 
     * @return PanelStyle, the center panel of the login view.
     * @author Augustin Lecomte
     * @see PanelStyle
     * @see GridBagLayout
     * @see GridBagConstraints
     * @see LabelStyle
     */
    private PanelStyle createCenterPanel() {
        
        // Create the center panel that will contain the login and signup forms.
        GridBagLayout gbLayout = new GridBagLayout(); // Create the grid bag layout.
        gbLayout.columnWidths = new int[] { 80, 150, 40, 80, 150 }; // Set the column widths.
        gbLayout.rowHeights = new int[] { 50, 50, 50, 20, 50, 50, 50, 50}; // Set the row heights.

        PanelStyle centerPanel = new PanelStyle(WIDTH, 100, gbLayout);
        centerPanel.setBackground(MyStyle.BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        createLoginForm(gbc, centerPanel);
        createSeparators(gbLayout, gbc, centerPanel);
        createSignupForm(gbc, centerPanel);
        return centerPanel;
    }

    /**
     * This method creates the login form and adds it to the center panel.
     * The login form is composed of:
     * <ul>
     *    <li>the mail label and text field</li>
     *   <li>the password label and text field</li>
     *  <li>the login button</li>
     * </ul>
     * 
     * @param gbc , GridBagConstraints, the grid bag constraints.
     * @param centerPanel , PanelStyle, the center panel of the login view.
     * @author Augustin Lecomte
     * @see PanelStyle
     * @see GridBagLayout
     * @see GridBagConstraints
     * @see LabelStyle
     * @see TextField
     * @see ButtonStyle
     * @see FlowLayout
     * @see MyStyle
     * @see TextType
     * @see SwingConstants
     */
    private void createLoginForm(GridBagConstraints gbc, PanelStyle centerPanel){
        // Create the mail label and text field and add them to the center panel.
        LabelStyle mailLoginLabel = new LabelStyle("Mail", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(mailLoginLabel, gbc);

        mailLoginTextField = new TextField();
        mailLoginTextField.setPreferredSize(new Dimension(415, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(mailLoginTextField, gbc);

        // Create the password label and text field and add them to the center panel.
        LabelStyle passwordLoginLabel = new LabelStyle("Password", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(passwordLoginLabel, gbc);

        passwordLoginTextField = new TextField();
        passwordLoginTextField.setPreferredSize(new Dimension(415, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(passwordLoginTextField, gbc);

        // Create the login button through a panel and add it to the center panel.
        PanelStyle loginPanel = new PanelStyle(WIDTH, 50, new FlowLayout());
        loginPanel.setBackground(MyStyle.BACKGROUND_COLOR);

        loginButton = new ButtonStyle("Login", 100, 30);
        loginButton.setBackground(MyStyle.BUTTON_COLOR);
        loginButton.setBorder(BorderFactory.createLineBorder(MyStyle.BUTTON_BORDER_COLOR));
        loginButton.setForeground(MyStyle.TEXT_COLOR);
        loginPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        centerPanel.add(loginPanel, gbc);
    }

    /**
     * This method creates the separators and adds them to the center panel.
     * The separtion between the login and signup forms will look like this:
     * <br>
     * <br>----------------------------- OR -----------------------------
     * 
     * @param gbLayout , GridBagLayout, the grid bag layout.
     * @param gbc , GridBagConstraints, the grid bag constraints.
     * @param centerPanel , PanelStyle, the center panel of the login view.
     * @author Augustin Lecomte
     * @see PanelStyle
     * @see GridBagLayout
     * @see GridBagConstraints
     * @see JSeparator
     * @see LabelStyle
     * @see MyStyle
     * @see TextType
     * @see SwingConstants
     */
    private void createSeparators(GridBagLayout gbLayout, GridBagConstraints gbc, PanelStyle centerPanel){
        // Create the separators and add them to the center panel.
        JSeparator separator1 = new JSeparator();
        separator1.setPreferredSize(new Dimension(230, 10));
        separator1.setForeground(MyStyle.BORDER_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(separator1, gbc);

        JSeparator separator2 = new JSeparator();
        separator2.setPreferredSize(new Dimension(230, 10));
        separator2.setForeground(MyStyle.BORDER_COLOR);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        centerPanel.add(separator2, gbc);

        LabelStyle orLabel = new LabelStyle("OR", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        centerPanel.add(orLabel, gbc);
    }

    /**
     * This method creates the signup form and adds it to the center panel.
     * The signup form is composed of:
     * <ul>
     *      <li>the first name label and text field</li>
     *      <li>the last name label and text field</li>
     *      <li>the mail label and text field</li>
     *      <li>the password label and text field</li>
     *      <li>the signup button</li>
     * </ul>
     * 
     * @param gbc , GridBagConstraints, the grid bag constraints.
     * @param centerPanel , PanelStyle, the center panel of the login view.
     * @author Augustin Lecomte
     * @see PanelStyle
     * @see GridBagLayout
     * @see GridBagConstraints
     * @see LabelStyle
     * @see TextField
     * @see ButtonStyle
     * @see FlowLayout
     * @see MyStyle
     * @see TextType
     * @see SwingConstants
     */
    private void createSignupForm(GridBagConstraints gbc, PanelStyle centerPanel){
        // Create the first name label and text field and add them to the center panel.
        LabelStyle firstNameLabel = new LabelStyle("First name", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(firstNameLabel, gbc);

        firstNameTextField = new TextField();
        firstNameTextField.setPreferredSize(new Dimension(145, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(firstNameTextField, gbc);

        // Create the last name label and text field and add them to the center panel.
        LabelStyle lastNameLabel = new LabelStyle("Last name", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(lastNameLabel, gbc);

        lastNameTextField = new TextField();
        lastNameTextField.setPreferredSize(new Dimension(145, 30));
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(lastNameTextField, gbc);

        // Create the mail label and text field and add them to the center panel.
        LabelStyle mailSignupLabel = new LabelStyle("Mail", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(mailSignupLabel, gbc);

        mailSignupTextField = new TextField();
        mailSignupTextField.setPreferredSize(new Dimension(415, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(mailSignupTextField, gbc);

        // Create the password label and text field and add them to the center panel.
        LabelStyle passwordSignupLabel = new LabelStyle("Password", TextType.TEXT, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(passwordSignupLabel, gbc);

        passwordSignupTextField = new TextField();
        passwordSignupTextField.setPreferredSize(new Dimension(415, 30));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(passwordSignupTextField, gbc);

        // Create the signup button through a panel and add it to the center panel.
        PanelStyle signupPanel = new PanelStyle(WIDTH, 50, new FlowLayout());
        signupPanel.setBackground(MyStyle.BACKGROUND_COLOR);

        signupButton = new ButtonStyle("Signup", 100, 30);
        signupButton.setBackground(MyStyle.BUTTON_COLOR);
        signupButton.setBorder(BorderFactory.createLineBorder(MyStyle.BUTTON_BORDER_COLOR));
        signupButton.setForeground(MyStyle.TEXT_COLOR);
        signupPanel.add(signupButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 5;
        centerPanel.add(signupPanel, gbc);
    }

    public ButtonStyle getLoginButton() {
        return loginButton;
    }

    public ButtonStyle getSignupButton() {
        return signupButton;
    }

    public TextField[] getLoginTextFields() {
        TextField[] loginTextFields = new TextField[2];
        loginTextFields[0] = mailLoginTextField;
        loginTextFields[1] = passwordLoginTextField;
        return loginTextFields;
    }

    public TextField[] getSignupTextFields() {
        TextField[] signupTextFields = new TextField[4];
        signupTextFields[0] = firstNameTextField;
        signupTextFields[1] = lastNameTextField;
        signupTextFields[2] = mailSignupTextField;
        signupTextFields[3] = passwordSignupTextField;
        return signupTextFields;
    }
}
