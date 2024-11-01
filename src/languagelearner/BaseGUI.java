package languagelearner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author mamar
 */

//Abstract base class for consitant GUI layout throughout the classes (Abstraction)
public abstract class BaseGUI extends JPanel {

    
    public static MainFrame mainFrame;
    //Encapsulated fields with protected access for all subclasses
    protected boolean lang = true;
    private Data data = new Data();
    protected int score = 0;
    protected Model model = new Model();

    //Constructor
    public BaseGUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        
        //Create NavPanel and ContentPanel
        JPanel navPanel = createNavPanel();
        JPanel contentPanel = createContentPanel();

        //SplitPane to divid between navPanel and contentPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, contentPanel, navPanel);
        splitPane.setRightComponent(navPanel);
        splitPane.setLeftComponent(createContentPanel());
        splitPane.setDividerSize(9);//Divider size for split pane      
        splitPane.setResizeWeight(0.75);//Resize weight for split pane

        add(splitPane, BorderLayout.CENTER);//adds split pane to main layout.
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    //Abstract  method to create navigation panel
    //Sublclasses implement this method and provide their own navigation
    protected abstract JPanel createNavPanel();

    //Can be over ridden and is default implementaiton of createContentPanel
    protected JPanel createContentPanel() {
        return createContentPanel(lang); //calls abstract method with lang
    }

    //Overloaded method with boolean parameter. Abstract method that mustr be implemented by subclasses.
    protected abstract JPanel createContentPanel(boolean lang);

    //Encapsulation but isnt private since sbuclassesses will modify the buttons. 
    protected JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(30, 30));
        return button;
    }

    //Creates quit button to exit the program
    protected JButton quitButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(e -> {
            //Display the final score in a pop-up
            JOptionPane.showMessageDialog(null, "Your final score is: " + model.menuScore, "Final Score", JOptionPane.INFORMATION_MESSAGE);

            //Exit the application
            System.exit(0);
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
    //Navigation button to switch panels
    protected JButton createNavButton(String text, String card) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(30, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setActionCommand(card);

        return button;
    }
    //Method to create Label with specified text
    protected JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        return label;
    }
    //Method to create TextArea so all subclasses can use
    protected JScrollPane createTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false); //Makes text non-editable
        textArea.setLineWrap(true); //Adds line wrapping
        textArea.setWrapStyleWord(true); //Wraps lines at word bounderies //GPT was used for this line
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 310)); //Adjust size as needed
        
        return scrollPane;
    }
    
    //Creates a Combobox for subclasses
    protected JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(150, 30)); // Adjust size as needed
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        return comboBox;
    }

}
