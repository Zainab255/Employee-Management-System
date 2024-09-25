package employe.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton view, add, update, remove;

    Home() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("Employee Management System", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        image.add(heading, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); // Centered layout with gaps
        buttonPanel.setOpaque(false); // Make the panel transparent

        add = createButton("Add Employee");
        buttonPanel.add(add);
        
        view = createButton("View Employees");
        buttonPanel.add(view);
        
        update = createButton("Update Employee");
        buttonPanel.add(update);
        
        remove = createButton("Remove Employee");
        buttonPanel.add(remove);

        // Add button panel to the bottom of the image label
        image.add(buttonPanel, BorderLayout.SOUTH);
        
        add(image, BorderLayout.CENTER);
        
        // Frame settings
        setSize(1120, 630);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(30, 144, 255)); // A nice blue color
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(200, 40)); // Set preferred size
        button.setFocusPainted(false);
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
        setVisible(false);
        if (ae.getSource() == add) {
            new AddEmployee();
        } else if (ae.getSource() == view) {
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            new UpdateEmployee();
        } else {
            new RemoveEmployee();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
