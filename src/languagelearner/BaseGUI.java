/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author mamar
 */
public abstract class BaseGUI extends JPanel {

    public static MainFrame mainFrame;
    protected boolean lang = true;

    public BaseGUI(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel navPanel = createNavPanel();

        JPanel contentPanel = createContentPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, contentPanel, navPanel);
        splitPane.setRightComponent(navPanel);
        splitPane.setLeftComponent(createContentPanel());
        splitPane.setDividerSize(9);// divider lin        
        splitPane.setResizeWeight(0.75);

        add(splitPane, BorderLayout.CENTER);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    protected abstract JPanel createNavPanel();

    // Default createContentPanel method, can be overridden
    protected JPanel createContentPanel() {
        return createContentPanel(lang); // Default to true or appropriate default value
    }

    // Overloaded method with boolean parameter
    protected abstract JPanel createContentPanel(boolean lang);

    //should these be private instead?
    protected JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(30, 30));
        return button;
    }

    protected JButton quitButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(e -> System.exit(0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    protected JButton createNavButton(String text, String card) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(30, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setActionCommand(card);

        return button;
    }

    protected JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        return label;
    }

    protected JScrollPane createTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 310)); //adjust size as needed
        return scrollPane;
    }

    protected JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(150, 30)); // Adjust size as needed
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        return comboBox;
    }

}

