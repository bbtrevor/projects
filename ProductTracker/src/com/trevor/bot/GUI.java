package com.trevor.bot;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
/**
 * A GUI that allows users to add products for sites supported and track or stop tracking 
 * those products.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public class GUI implements ActionListener {
    
    private static final int FRAME_WIDTH = 16 * 64;
    private static final int FRAME_HEIGHT = 9 * 64;
    
    private JFrame frame;
    private JPanel panel;
    
    private JTextField siteNameTextField;
    private JTextField URLErrorTextField;
    private JTextField targetLowTextField;
    private JTextField targetHighTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    
    private JLabel siteNameError;
    private JLabel URLError;
    private JLabel targetLowError;
    private JLabel targetHighError;
    private JLabel emailError;
    private JLabel passwordError;
    
    private String siteNameErrorText;
    private String URLErrorText;
    private String targetLowErrorText;
    private String targetHighErrorText;
    private String emailErrorText;
    private String passwordErrorText;
    
    private JLabel productsAddedLabel;
    private int productsAdded;
    
    private JButton addProductButton;
    private JButton startButton;
    private JButton stopButton;
    
    /**
     * Creates a new GUI.
     */
    public GUI() {
        init();
        initErrorTexts();
        addTextFields();
        addButtons();
        addLabels();
        addErrorLabels();
        
        frame.setVisible(true);
    }
    
    /**
     * Initializes frame and panel fields and their attributes.
     */
    private void init() {
        frame = new JFrame("Product Tracking GUI");
        panel = new JPanel();
        frame.setResizable(false);
        panel.setBackground(Color.GRAY);
        frame.getContentPane();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        productsAdded = 0;
    }
    
    /**
     * Initializes error texts.
     */
    private void initErrorTexts() {
        Sites[] sites = Sites.values();
        StringBuilder builder = new StringBuilder();
        builder.append("* Invalid site name. Try ");
        for (int i = 0; i < sites.length; i++) {
            if (i == sites.length - 1) {
                builder.append("or ");
            }
            builder.append(sites[i].toString());
            if (i != sites.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(".");
        
        siteNameErrorText = builder.toString();
        
        URLErrorText = "* Invalid URL. Please enter a valid URL.";
        
        targetLowErrorText = "* Invalid entry. Enter a value for the target low price.";
        
        targetHighErrorText = "* Invalid entry. Enter a value for the target high price.";
        
        emailErrorText = "* Enter your email address for this site.";
        
        passwordErrorText = "* Enter your password for this site.";
    
    }
    
    /**
     * Adds text fields to the GUI for user input.
     */
    private void addTextFields() {
        siteNameTextField = new JTextField(28);
        Dimension textFieldSize = siteNameTextField.getPreferredSize();
        siteNameTextField.setBounds(35, 80, textFieldSize.width, textFieldSize.height);
        panel.add(siteNameTextField);
        
        URLErrorTextField = new JTextField(40);
        Dimension textFieldSize2 = URLErrorTextField.getPreferredSize();
        URLErrorTextField.setBounds(FRAME_WIDTH / 2 - (textFieldSize2.width / 2), 
            150, textFieldSize2.width, textFieldSize2.height);
        panel.add(URLErrorTextField);
        
        targetLowTextField = new JTextField(40);
        Dimension textFieldSize3 = targetLowTextField.getPreferredSize();
        targetLowTextField.setBounds(FRAME_WIDTH / 2 - (textFieldSize3.width / 2), 
            220, textFieldSize3.width, textFieldSize3.height);
        panel.add(targetLowTextField);
    
        targetHighTextField = new JTextField(40);
        Dimension textFieldSize4 = targetHighTextField.getPreferredSize();
        targetHighTextField.setBounds(FRAME_WIDTH / 2 - (textFieldSize4.width / 2), 
            290, textFieldSize4.width, textFieldSize4.height);
        panel.add(targetHighTextField);
    
        emailTextField = new JTextField(28);
        Dimension textFieldSize5 = emailTextField.getPreferredSize();
        emailTextField.setBounds(siteNameTextField.getX() + textFieldSize.width + 5, 
            80, textFieldSize5.width, textFieldSize5.height);
        panel.add(emailTextField);
        
        passwordTextField = new JTextField(28);
        Dimension textFieldSize6 = passwordTextField.getPreferredSize();
        passwordTextField.setBounds(emailTextField.getX() + textFieldSize5.width + 5, 
            80, textFieldSize6.width, textFieldSize6.height);
        panel.add(passwordTextField);
        
        
    }
    
    /**
     * Adds labels to the GUI.
     */
    private void addLabels() {
        JLabel label = new JLabel("Product Info");
        Dimension labelSize = label.getPreferredSize();
        label.setBounds(FRAME_WIDTH / 2 - (labelSize.width / 2),
            20, labelSize.width, labelSize.height);
        panel.add(label);
        
        JLabel label2 = new JLabel("Enter Site Name for this Product");
        Dimension labelSize2 = label2.getPreferredSize();
        label2.setBounds(siteNameTextField.getX() + siteNameTextField.getWidth() - 
            (siteNameTextField.getWidth() / 2) - (labelSize2.width / 2), 60, 
            labelSize2.width, labelSize2.height);
        panel.add(label2);
        
        JLabel label3 = new JLabel("Enter URL for this Product");
        Dimension labelSize3 = label3.getPreferredSize();
        label3.setBounds(FRAME_WIDTH / 2 - (labelSize3.width / 2),
            130, labelSize3.width, labelSize3.height);
        panel.add(label3);
        
        JLabel label4 = new JLabel("Enter Target Low Price for this Product");
        Dimension labelSize4 = label4.getPreferredSize();
        label4.setBounds(FRAME_WIDTH / 2 - (labelSize4.width / 2),
            200, labelSize4.width, labelSize4.height);
        panel.add(label4);
        
        JLabel label5 = new JLabel("Enter Target High Price for this Product");
        Dimension labelSize5 = label5.getPreferredSize();
        label5.setBounds(FRAME_WIDTH / 2 - (labelSize5.width / 2),
            270, labelSize5.width, labelSize5.height);
        panel.add(label5);
        
        JLabel label6 = new JLabel("Enter Email for this Site");
        Dimension labelSize6 = label6.getPreferredSize();
        label6.setBounds(emailTextField.getX() + emailTextField.getWidth() - 
            (emailTextField.getWidth() / 2) - (labelSize6.width / 2), 60, 
            labelSize6.width, labelSize6.height);
        panel.add(label6);
        
        JLabel label7 = new JLabel("Enter Password for this Site");
        Dimension labelSize7 = label7.getPreferredSize();
        label7.setBounds(passwordTextField.getX() + passwordTextField.getWidth() - 
            (passwordTextField.getWidth() / 2) - (labelSize7.width / 2), 60, 
            labelSize7.width, labelSize7.height);
        panel.add(label7);
        
        productsAddedLabel = new JLabel("9999 Products added.");
        Dimension productsAddedLabelSize = productsAddedLabel.getPreferredSize();
        productsAddedLabel.setBounds(addProductButton.getX() + 
            addProductButton.getWidth() + 10, 
            addProductButton.getY() + ((addProductButton.getHeight() - 
                productsAddedLabelSize.height) / 2) , productsAddedLabelSize.width, 
            productsAddedLabelSize.height);
        productsAddedLabel.setText("");
        panel.add(productsAddedLabel);
    }
    
    /**
     * Adds error labels to the GUI.
     */
    private void addErrorLabels() {
        siteNameError = new JLabel(siteNameErrorText);
        siteNameError.setForeground(new Color(150, 0, 0));
        Dimension labelSize = siteNameError.getPreferredSize();
        siteNameError.setBounds(siteNameTextField.getX(), 
            siteNameTextField.getY() + siteNameTextField.getHeight() + 5, 
            labelSize.width, labelSize.height);
        siteNameError.setText("");
        panel.add(siteNameError);
        
        URLError = new JLabel(URLErrorText);
        URLError.setForeground(new Color(150, 0, 0));
        URLError.setBackground(Color.RED);
        Dimension labelSize2 = URLError.getPreferredSize();
        URLError.setBounds(URLErrorTextField.getX(), 
            URLErrorTextField.getY() + URLErrorTextField.getHeight() + 5, 
            labelSize2.width, labelSize2.height);
        URLError.setText("");
        panel.add(URLError);
        
        targetLowError = new JLabel(targetLowErrorText);
        targetLowError.setForeground(new Color(150, 0, 0));
        targetLowError.setBackground(Color.RED);
        Dimension labelSize3 = targetLowError.getPreferredSize();
        targetLowError.setBounds(targetLowTextField.getX(), 
            targetLowTextField.getY() + targetLowTextField.getHeight() + 5, 
            labelSize3.width, labelSize3.height);
        targetLowError.setText("");
        panel.add(targetLowError);
        
        targetHighError = new JLabel(targetHighErrorText);
        targetHighError.setForeground(new Color(150, 0, 0));
        targetHighError.setBackground(Color.RED);
        Dimension labelSize4 = targetHighError.getPreferredSize();
        targetHighError.setBounds(targetHighTextField.getX(), 
            targetHighTextField.getY() + targetHighTextField.getHeight() + 5, 
            labelSize4.width, labelSize4.height);
        targetHighError.setText("");
        panel.add(targetHighError);
    
        emailError = new JLabel(emailErrorText);
        emailError.setForeground(new Color(150, 0, 0));
        Dimension labelSize5 = emailError.getPreferredSize();
        emailError.setBounds(emailTextField.getX(), 
            emailTextField.getY() + emailTextField.getHeight() + 5, 
            labelSize5.width, labelSize5.height);
        emailError.setText("");
        panel.add(emailError);
        
        passwordError = new JLabel(passwordErrorText);
        passwordError.setForeground(new Color(150, 0, 0));
        Dimension labelSize6 = passwordError.getPreferredSize();
        passwordError.setBounds(passwordTextField.getX(), 
            passwordTextField.getY() + passwordTextField.getHeight() + 5, 
            labelSize6.width, labelSize6.height);
        passwordError.setText("");
        panel.add(passwordError);
    
    }
    
    /**
     * Adds buttons to the GUI.
     */
    private void addButtons() {
        addProductButton = new JButton("Add Product");
        Dimension addProductButtonSize = addProductButton.getPreferredSize();
        addProductButton.setBounds(FRAME_WIDTH / 2 - (addProductButtonSize.width / 2), 
            360, addProductButtonSize.width, addProductButtonSize.height);
        addProductButton.addActionListener(this);
        panel.add(addProductButton);
        
        startButton = new JButton("Start Tracking!");
        Dimension buttonSize2 = startButton.getPreferredSize();
        startButton.setBounds(FRAME_WIDTH / 2 - (buttonSize2.width / 2), 
            400, buttonSize2.width, buttonSize2.height);
        startButton.setEnabled(false);
        startButton.addActionListener(this);
        panel.add(startButton);
        
        stopButton = new JButton("Stop Tracking");
        Dimension stopButtonSize = stopButton.getPreferredSize();
        stopButton.setBounds(FRAME_WIDTH / 2 - (stopButtonSize.width / 2), 
            440, stopButtonSize.width, stopButtonSize.height);
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);
        panel.add(stopButton);
    }
    
    /**
     * Checks the texts fields to see if all input is valid.
     * @return true if all of the text fields have valid input, false otherwise.
     */
    private boolean checkTextFieldInfo() {
        boolean isValid = true;
        if (!checkSiteName()) {
            isValid = false;
        }
        if (!checkURL()) {
            isValid = false;
        }
        if (!checkTargetLow()) {
            isValid = false;
        }
        if (!checkTargetHigh()) {
            isValid = false;
        }
        if (!checkEmail()) {
            isValid = false;
        }
        if (!checkEmail()) {
            isValid = false;
        }
        if (!checkPassword()) {
            isValid = false;
        }
        return isValid;
    }
    
    /**
     * Checks the site name text field for valid input.
     * @return true if the site name has valid input, false otherwise.
     */
    private boolean checkSiteName() {
        String siteName = siteNameTextField.getText().toLowerCase();
        Sites[] sites = Sites.values();
        for (Sites site : sites) {
            if (site.toString().toLowerCase().equals(siteName)) {
                siteNameError.setText("");
                return true;
            }
        }
        siteNameError.setText(siteNameErrorText);
        return false;
    }
    
    /**
     * Checks the email text field for valid input.
     * @return true if the email text field has valid input, false otherwise.
     */
    private boolean checkEmail() {
        if (emailTextField.getText().equals("")) {
            emailError.setText(emailErrorText);
            return false;
        }
        emailError.setText("");
        return true;
    }
    
    /**
     * Checks the password text field for valid input.
     * @return true if the password text field has valid input, false otherwise.
     */
    private boolean checkPassword() {
        if (passwordTextField.getText().equals("")) {
            passwordError.setText(passwordErrorText);
            return false;
        }
        passwordError.setText("");
        return true;
    }
    
    /**
     * Checks the URL text field for valid input.
     * @return true if the URL text field has valid input, false otherwise.
     */
    private boolean checkURL() {
        try {
            new URL(URLErrorTextField.getText()).toURI();
            URLError.setText("");
            return true;
        }
        catch (Exception e) {
            URLError.setText(URLErrorText);
            return false;
        }
    }
    
    /**
     * Checks the target low text field for valid input.
     * @return true if the target low text field has valid input, false otherwise.
     */
    private boolean checkTargetLow() {
        try {
            Double.valueOf(targetLowTextField.getText());
            targetLowError.setText("");
            return true;
        }
        catch (NumberFormatException e) {
            targetLowError.setText(targetLowErrorText);
            return false;
        }
    }
    
    /**
     * Checks the target high text field for valid input.
     * @return true if the target high text field has valid input, false otherwise.
     */
    private boolean checkTargetHigh() {
        try {
            Double.valueOf(targetHighTextField.getText());
            targetHighError.setText("");
            return true;
        }
        catch (NumberFormatException e) {
            targetHighError.setText(targetHighErrorText);
            return false;
        }
    }
    
    /**
     * Called when a button is pressed, implements button functionality.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Sites[] sites = Sites.values();
        switch (e.getActionCommand()) {
            case "Add Product":
                if (checkTextFieldInfo()) {
                    productsAdded++;
                    String productsAddedText = productsAdded == 1 ? 
                        productsAdded + " Product added." : 
                        productsAdded + " Products added.";
                    productsAddedLabel.setText(productsAddedText);
                    
                    for (Sites site : sites) {
                        if (site.toString().toLowerCase().equals(
                            siteNameTextField.getText().toLowerCase())) {
                            site.getBot().addProduct(new Product(URLErrorTextField.getText(), 
                                Double.valueOf(targetLowTextField.getText()), 
                                Double.valueOf(targetHighTextField.getText())));
                            site.getBot().setCredentials(emailTextField.getText(), 
                                passwordTextField.getText());
                        }
                    }
                    if (productsAdded == 1) {
                        startButton.setEnabled(true);
                    }
                    URLErrorTextField.setText("");
                    targetLowTextField.setText("");
                    targetHighTextField.setText("");
                    
                }
                break;
            case "Start Tracking!":
                stopButton.setEnabled(true);
                addProductButton.setEnabled(false);
                startButton.setEnabled(false);
                for (Sites site: sites) {
                    if (!site.getBot().hasNoProducts()) {
                        site.getBot().track();
                    }
                }
                break;
            case "Stop Tracking":
                stopButton.setEnabled(false);
                addProductButton.setEnabled(true);
                startButton.setEnabled(true);
                for (Sites site : sites) {
                    if (!site.getBot().hasNoProducts()) {
                        site.getBot().stopTracking();
                    }
                }
                break;
        }
    }
}
