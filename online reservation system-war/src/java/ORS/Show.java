package ORS;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Show {

    JFrame showFrame;
    JLabel titleLabel, showLabel, enterPnrText;
    JLabel pnr, name, email, locationinfo, datentime;
    JTextField pnrInput;
    JButton submitButton, backButton;

    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/pkdb1?useSSL=false";
    String user = "root";
    String password = "root";

    public Show () {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
            String sql = "use pkdb1";
            stmt.executeUpdate(sql);
        } catch (Exception e1) {
            System.out.println(e1);
        }

        showFrame = new JFrame("Registration System By-Piyush kag");
        showFrame.setSize(4000, 4000);
        showFrame.setLayout(null);
        showFrame.setBackground(Color.yellow);

        titleLabel = new JLabel("Online Reservation System");
        titleLabel.setBounds(500, 5, 500, 200);
        titleLabel.setForeground(Color.BLACK);
        Font font1 = new Font("Poppins", Font.BOLD, 35);
        titleLabel.setFont(font1);
        showFrame.add(titleLabel);

        showLabel = new JLabel("SHOW A TICKET");
        showLabel.setBounds(580, 150, 400, 200);
        showLabel.setForeground(Color.BLACK);
        Font font2 = new Font("Poppins", Font.BOLD, 30);
        showLabel.setFont(font2);
        showFrame.add(showLabel);

        enterPnrText = new JLabel("Enter PNR");
        enterPnrText.setBounds(400, 300, 100, 40);
        enterPnrText.setForeground(Color.BLACK);
        Font font3 = new Font("Poppins", Font.BOLD, 20);
        enterPnrText.setFont(font3);
        showFrame.add(enterPnrText);

        pnrInput = new JTextField();
        pnrInput.setBounds(550, 300, 300, 40);
        pnrInput.requestFocus();
        showFrame.add(pnrInput);

        submitButton = new JButton("Submit");
        submitButton.setBounds(550, 400, 140, 40);
        showFrame.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(700, 400, 150, 40);
        showFrame.add(backButton);
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displaydata();  
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFrame.dispose();
            }
        });

        pnr = new JLabel("PNR: ");
        pnr.setBounds(550, 480, 300, 30);
        pnr.setForeground(Color.BLACK);
        Font font4 = new Font("Poppins", Font.BOLD, 20);
        pnr.setFont(font4);
        showFrame.add(pnr);

        name = new JLabel("Name: ");
        name.setBounds(550, 520, 300, 30);
        name.setForeground(Color.BLACK);
        name.setFont(font4);
        showFrame.add(name);

        email = new JLabel("Email Id: ");
        email.setBounds(550, 560, 300, 30);
        email.setForeground(Color.BLACK);
        email.setFont(font4);
        showFrame.add(email);

        locationinfo = new JLabel("From: ");
        locationinfo.setBounds(550, 600, 300, 30);
        locationinfo.setForeground(Color.BLACK);
        locationinfo.setFont(font4);
        showFrame.add(locationinfo);

        datentime = new JLabel("Date: ");
        datentime.setBounds(550, 640, 300, 30);
        datentime.setForeground(Color.BLACK);
        datentime.setFont(font4);
        showFrame.add(datentime);

        showFrame.setVisible(true);
        showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displaydata() {
        try {
            int pnrNumber = Integer.parseInt(pnrInput.getText());
            String q = "Select * from ticketinfo where pnr = ?";
            PreparedStatement pst1 = conn.prepareStatement(q);
            pst1.setInt(1, pnrNumber);
            ResultSet rSet = pst1.executeQuery();
            if (rSet.next()) {
                pnr.setText("PNR: " + rSet.getString(1));
                name.setText("Name: " + rSet.getString(2) + " " + rSet.getString(3));
                email.setText("Email Id: " + rSet.getString(4));
                locationinfo.setText("From: " + rSet.getString(5) + " To: " + rSet.getString(6));
                datentime.setText("Date: " + rSet.getString(7) + "    Time: " + rSet.getString(8));
            } else {
                JOptionPane.showMessageDialog(null, "PNR does not exist");
                pnrInput.setText("");
                pnrInput.requestFocus();
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }
}