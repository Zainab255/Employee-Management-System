package employe.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployee extends JFrame implements ActionListener {
    
    Choice cEmpId;
    JButton delete, back;
    
    RemoveEmployee() {
        // Set frame properties
        getContentPane().setBackground(new Color(240, 248, 255)); // Light background
        setLayout(null);
        
        // Label for Employee ID
        JLabel labelempId = new JLabel("Employee Id:");
        labelempId.setBounds(50, 50, 100, 30);
        labelempId.setFont(new Font("Arial", Font.BOLD, 14));
        labelempId.setForeground(new Color(25, 25, 112)); // Dark blue
        add(labelempId);
        
        // Dropdown for employee IDs
        cEmpId = new Choice();
        cEmpId.setBounds(200, 50, 150, 30);
        add(cEmpId);
        
        // Fetch employee IDs from database
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Labels for employee details
        JLabel labelname = new JLabel("Name:");
        labelname.setBounds(50, 100, 100, 30);
        labelname.setFont(new Font("Arial", Font.BOLD, 14));
        labelname.setForeground(new Color(25, 25, 112));
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 200, 30);
        lblname.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblname);
        
        JLabel labelphone = new JLabel("Phone:");
        labelphone.setBounds(50, 150, 100, 30);
        labelphone.setFont(new Font("Arial", Font.BOLD, 14));
        labelphone.setForeground(new Color(25, 25, 112));
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 150, 200, 30);
        lblphone.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblphone);
        
        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(50, 200, 100, 30);
        labelemail.setFont(new Font("Arial", Font.BOLD, 14));
        labelemail.setForeground(new Color(25, 25, 112));
        add(labelemail);
        
        JLabel lblemail = new JLabel();
        lblemail.setBounds(200, 200, 200, 30);
        lblemail.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblemail);
        
        // Fetch selected employee details
        updateEmployeeDetails(lblname, lblphone, lblemail);
        
        // Add listener to dropdown to fetch details
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                updateEmployeeDetails(lblname, lblphone, lblemail);
            }
        });
        
        // Delete button
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        styleButton(delete);
        delete.addActionListener(this);
        add(delete);
        
        // Back button
        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        styleButton(back);
        back.addActionListener(this);
        add(back);
        
        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 600, 400);
        add(image);
        
        // Frame properties
        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    private void updateEmployeeDetails(JLabel lblname, JLabel lblphone, JLabel lblemail) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee WHERE empId = '" + cEmpId.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                lblname.setText(rs.getString("name"));
                lblphone.setText(rs.getString("phone"));
                lblemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM employee WHERE empId = '" + cEmpId.getSelectedItem() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
