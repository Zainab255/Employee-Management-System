package employe.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    JTextField tfusername;
    JPasswordField tfpassword;  // For hiding password input
    
    Login() {
        
        getContentPane().setBackground(Color.decode("#f0f0f0")); // Light gray background
        setLayout(null);
        
        // Panel for form elements
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(50, 50, 400, 200);
        loginPanel.setBackground(Color.WHITE);  // White panel for input fields
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Border
        
        // Username label
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30, 30, 100, 30);
        lblusername.setFont(new Font("Arial", Font.BOLD, 14));
        lblusername.setForeground(Color.DARK_GRAY);
        loginPanel.add(lblusername);
        
        // Username input
        tfusername = new JTextField();
        tfusername.setBounds(150, 30, 200, 30);
        tfusername.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        loginPanel.add(tfusername);
        
        // Password label
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(30, 80, 100, 30);
        lblpassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblpassword.setForeground(Color.DARK_GRAY);
        loginPanel.add(lblpassword);
        
        // Password input (hidden characters)
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 80, 200, 30);
        tfpassword.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        loginPanel.add(tfpassword);
        
        // Login button
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 130, 200, 40);
        login.setBackground(Color.decode("#007BFF")); // Blue color for button
        login.setForeground(Color.WHITE); 
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setFocusPainted(false);
        login.setBorder(null); // Remove button border
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(this);
        loginPanel.add(login);
        
        add(loginPanel);
        
        // Image on the side
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(480, 75, 150, 150);
        add(image);
        
        // Frame settings
        setSize(650, 350);
        setLocation(450, 200);
        setTitle("Employee Management System - Login");
        setResizable(false);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword()); // Getting password as a string
            
            Conn c = new Conn();
            String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
            
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                setVisible(false);
                new Home().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                setVisible(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
