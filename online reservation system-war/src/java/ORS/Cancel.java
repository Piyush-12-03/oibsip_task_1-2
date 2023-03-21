package ORS;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Cancel {

    JFrame cancelFrame;
    JLabel titleLabel, showLabel, enterPnrText;
    JTextField pnrInput;
    JButton submiButton, backButton;

    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/pkdb1?useSSL=false";
    String user = "root";
    String password = "root";

    public Cancel() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
            String sql = "use pkdb1";
            stmt.executeUpdate(sql);
        } catch (Exception e1) {
            System.out.println(e1);
        }

        cancelFrame = new JFrame("Registration System By-Piyush kag");
        cancelFrame.setSize(4000, 4000);
        cancelFrame.setLayout(null);
        cancelFrame.setBackground(Color.yellow);

        titleLabel = new JLabel("Online Reservation System");
        titleLabel.setBounds(500, 5, 500, 200);
        titleLabel.setForeground(Color.BLACK);
        Font font1 = new Font("Poppins", Font.BOLD, 35);
        titleLabel.setFont(font1);
        cancelFrame.add(titleLabel);

        showLabel = new JLabel("CANCEL A TICKET");
        showLabel.setBounds(560, 150, 400, 200);
        showLabel.setForeground(Color.BLACK);
        Font font2 = new Font("Poppins", Font.BOLD, 30);
        showLabel.setFont(font2);
        cancelFrame.add(showLabel);

        enterPnrText = new JLabel("Enter PNR");
        enterPnrText.setBounds(400, 300, 100, 40);
        enterPnrText.setForeground(Color.BLACK);
        Font font3 = new Font("Poppins", Font.BOLD, 20);
        enterPnrText.setFont(font3);
        cancelFrame.add(enterPnrText);

        pnrInput = new JTextField();
        pnrInput.setBounds(550, 300, 300, 40);
        pnrInput.requestFocus();
        cancelFrame.add(pnrInput);

        submiButton = new JButton("Submit");
        submiButton.setBounds(550, 400, 140, 40);
        cancelFrame.add(submiButton);

        backButton = new JButton("Back");
        backButton.setBounds(700, 400, 150, 40);
        cancelFrame.add(backButton);

        submiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletedata();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelFrame.dispose();
            }
        });

        cancelFrame.setVisible(true);
        cancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void deletedata() {
        try {
            int pnrNumber = Integer.parseInt(pnrInput.getText());
            String q = "Select * from ticketinfo where pnr = ?";
            PreparedStatement pst1 = conn.prepareStatement(q);
            pst1.setInt(1, pnrNumber);
            ResultSet rSet = pst1.executeQuery();
            if (rSet.next()) {
                try {
                    int response = JOptionPane.showConfirmDialog(cancelFrame,
                            "Do you really want to cancel the ticket?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        q = "Delete from ticketinfo where pnr = ?";
                        pst1 = conn.prepareStatement(q);
                        pst1.setInt(1, pnrNumber);
                        pst1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully");
                    }
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "PNR does not exist");
            }
            pnrInput.setText("");
            pnrInput.requestFocus();
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }
}