package employe.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;

    ViewEmployee() {
        // Set frame properties
        setTitle("View Employee");
        getContentPane().setBackground(new Color(240, 240, 240)); // Light grey background
        setLayout(null);
        
        // Add heading label
        JLabel heading = new JLabel("Employee Details", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(30, 144, 255)); // Blue color
        heading.setBounds(0, 10, 900, 30);
        add(heading);
        
        // Search label
        JLabel searchlbl = new JLabel("Search by Employee Id:");
        searchlbl.setBounds(20, 50, 180, 20);
        searchlbl.setFont(new Font("Arial", Font.PLAIN, 16));
        add(searchlbl);
        
        // Choice for employee IDs
        cemployeeId = new Choice();
        cemployeeId.setBounds(220, 50, 150, 20);
        add(cemployeeId);
        
        // Populate choice with employee IDs
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while (rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Table for displaying employee data
        table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25); // Set row height
        
        // Populate table with employee data
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Scroll pane for the table
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 500);
        add(jsp);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // Left-aligned layout
        buttonPanel.setOpaque(false); // Transparent background

        // Search button
        search = createButton("Search");
        buttonPanel.add(search);
        
        // Print button
        print = createButton("Print");
        buttonPanel.add(print);
        
        // Update button
        update = createButton("Update");
        buttonPanel.add(update);
        
        // Back button
        back = createButton("Back");
        buttonPanel.add(back);

        // Add button panel to the frame
        buttonPanel.setBounds(50, 70, 900, 30);
        add(buttonPanel);
        
        // Frame settings
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Method to create buttons with common styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(30, 144, 255)); // A nice blue color
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Adding hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255)); // Original color
            }
        });
        
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from employee where empId = '" + cemployeeId.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
