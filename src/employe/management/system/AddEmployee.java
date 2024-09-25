package employe.management.system;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener{
    
    Random ran = new Random();
    int number = ran.nextInt(999999);
    
    JTextField tfname, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfsalary, tfdesignation;
    JDateChooser dcdob;
    JComboBox<String> cbeducation;
    JLabel lblempId;
    JButton add, back;
    
    AddEmployee() {
        // Frame properties
        setTitle("Add Employee");
        getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue background
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Heading
        JLabel heading = new JLabel("Add Employee Detail");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(heading, gbc);
        
        // Name
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name"), gbc);
        
        tfname = new JTextField(15);
        gbc.gridx = 1;
        add(tfname, gbc);
        
        // Father's Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Father's Name"), gbc);
        
        tffname = new JTextField(15);
        gbc.gridx = 1;
        add(tffname, gbc);
        
        // Date of Birth
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Date of Birth"), gbc);
        
        dcdob = new JDateChooser();
        gbc.gridx = 1;
        add(dcdob, gbc);
        
        // Salary
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Salary"), gbc);
        
        tfsalary = new JTextField(15);
        gbc.gridx = 1;
        add(tfsalary, gbc);
        
        // Address
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Address"), gbc);
        
        tfaddress = new JTextField(15);
        gbc.gridx = 1;
        add(tfaddress, gbc);
        
        // Phone
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Phone"), gbc);
        
        tfphone = new JTextField(15);
        gbc.gridx = 1;
        add(tfphone, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Email"), gbc);
        
        tfemail = new JTextField(15);
        gbc.gridx = 1;
        add(tfemail, gbc);
        
        // Highest Education
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Highest Education"), gbc);
        
        String courses[] = {"BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        cbeducation = new JComboBox<>(courses);
        gbc.gridx = 1;
        add(cbeducation, gbc);
        
        // Designation
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("Designation"), gbc);
        
        tfdesignation = new JTextField(15);
        gbc.gridx = 1;
        add(tfdesignation, gbc);
        
        // ATM Number
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("ATM Number"), gbc);
        
        tfaadhar = new JTextField(15);
        gbc.gridx = 1;
        add(tfaadhar, gbc);
        
        // Employee ID
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("Employee ID"), gbc);
        
        lblempId = new JLabel("" + number);
        gbc.gridx = 1;
        add(lblempId, gbc);
        
        // Buttons
        add = createButton("Add Details", new Color(34, 139, 34)); // Forest Green
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(add, gbc);
        
        back = createButton("Back", new Color(255, 69, 0)); // Red Orange
        gbc.gridx = 1;
        add(back, gbc);
        
        
        

        // Frame settings
        setSize(600, 600);
        setLocation(300, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
     private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(this);
        return button;
    }
     
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) cbeducation.getSelectedItem();
            String designation = tfdesignation.getText();
            String aadhar = tfaadhar.getText();
            String empId = lblempId.getText();
            
            try {
                Conn conn = new Conn();
                String query = "insert into employee values('"+name+"', '"+fname+"', '"+dob+"', '"+salary+"', '"+address+"', '"+phone+"', '"+email+"', '"+education+"', '"+designation+"', '"+aadhar+"', '"+empId+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home().setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error adding details: " + e.getMessage());
            }
        } else {
            setVisible(false);
            new Home().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
