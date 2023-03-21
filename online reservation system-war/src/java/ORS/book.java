package ORS;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class book {

    JFrame bookFrame;
    JLabel titleLabel, bookingFormText, firstNameLabel, emailLabel, fromLabel, toLabel, dateLabel, timeLabel;
    JTextField firstNameInput, lastNameInput, emailInput, dateInput, timeInput;
    JComboBox fromlistCities, tolistCities;
    JButton submitButton, backButton;

    String cities[] = { "Banglore", "Chennai", "Delhi", "Indore" ,"Kolkata", "Mumbai", "Nagpur", "Odissa" , "Pune", "Ratlam", "Satna"};
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/pkdb1?useSSL=false";
    String user = "root";
    String password = "root";

    public book() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
            String sql = "use pkdb1";
            stmt.executeUpdate(sql);
            sql = "create table if not exists ticketinfo (pnr int unique not null,firstname varchar(100) not null, lastname varchar(100) not null, emailid varchar(100) not null, fromcity varchar(100) not null, tocity varchar(100) not null, datecal DATE not null, time varchar(100) not null)";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }

        bookFrame = new JFrame("Registration System By-Piyush kag");
        bookFrame.setSize(4000, 4000);
        bookFrame.setLayout(null);
        bookFrame.setBackground(Color.yellow);

        titleLabel = new JLabel("Online Reservation System");
        titleLabel.setBounds(500, 5, 500, 200);
        titleLabel.setForeground(Color.BLACK);
        Font font1 = new Font("Poppins", Font.BOLD, 35);
        titleLabel.setFont(font1);
        bookFrame.add(titleLabel);

        bookingFormText = new JLabel("BOOKING FORM");
        bookingFormText.setBounds(580, 60, 400, 200);
        bookingFormText.setForeground(Color.BLACK);
        Font font2 = new Font("Poppins", Font.BOLD, 30);
        bookingFormText.setFont(font2);
        bookFrame.add(bookingFormText);

        firstNameInput = new JTextField();
        firstNameInput.setBounds(360, 210, 300, 40);
        bookFrame.add(firstNameInput);
        firstNameInput.requestFocus();

        lastNameInput = new JTextField();
        lastNameInput.setBounds(700, 210, 300, 40);
        bookFrame.add(lastNameInput);

        emailInput = new JTextField();
        emailInput.setBounds(360, 280, 640, 40);
        bookFrame.add(emailInput);

        firstNameLabel = new JLabel("First & Last Name");
        firstNameLabel.setBounds(150, 210, 200, 40);
        firstNameLabel.setForeground(Color.BLACK);
        Font font3 = new Font("Poppins", Font.BOLD, 20);
        firstNameLabel.setFont(font3);
        bookFrame.add(firstNameLabel);

        emailLabel = new JLabel("Email Id");
        emailLabel.setBounds(150, 280, 200, 40);
        emailLabel.setForeground(Color.BLACK);
        Font font4 = new Font("Poppins", Font.BOLD, 20);
        emailLabel.setFont(font4);
        bookFrame.add(emailLabel);

        fromLabel = new JLabel("From & To");
        fromLabel.setBounds(150, 350, 200, 40);
        fromLabel.setForeground(Color.BLACK);
        Font font5 = new Font("Poppins", Font.BOLD, 20);
        fromLabel.setFont(font5);
        bookFrame.add(fromLabel);

        fromlistCities = new JComboBox<String>(cities);
        fromlistCities.setBounds(360, 360, 300, 40);
        bookFrame.add(fromlistCities);
        fromlistCities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == fromlistCities) {
                    fromlistCities.getSelectedItem();
                }
            }
        });

        tolistCities = new JComboBox<String>(cities);
        tolistCities.setBounds(700, 360, 300, 40);
        bookFrame.add(tolistCities);
        tolistCities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tolistCities) {
                    tolistCities.getSelectedItem();
                }
            }
        });

        dateLabel = new JLabel("Date (yyyy-mm-dd) &");
        dateLabel.setBounds(150, 420, 200, 40);
        dateLabel.setForeground(Color.BLACK);
        Font font6 = new Font("Poppins", Font.BOLD, 20);
        dateLabel.setFont(font6);
        bookFrame.add(dateLabel);

        dateInput = new JTextField();
        dateInput.setBounds(360, 440, 300, 40);
        bookFrame.add(dateInput);

        timeLabel = new JLabel("Time (HH:MM) AM/PM");
        timeLabel.setBounds(150, 450, 250, 40);
        timeLabel.setForeground(Color.BLACK);
        Font font7 = new Font("Poppins", Font.BOLD, 18);
        timeLabel.setFont(font7);
        bookFrame.add(timeLabel);

        timeInput = new JTextField();
        timeInput.setBounds(700, 440, 300, 40);
        bookFrame.add(timeInput);

        submitButton = new JButton("Submit");
        submitButton.setBounds(520, 520, 140, 40);
        bookFrame.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(700, 520, 140, 40);
        bookFrame.add(backButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String q1, q2;
                    Random randomNumber = new Random();
                    int pnr = randomNumber.nextInt(2000);
                    String firstname = firstNameInput.getText();
                    String lastname = lastNameInput.getText();
                    String email = emailInput.getText();
                    String fromcity = fromlistCities.getSelectedItem().toString();
                    String tocity = tolistCities.getSelectedItem().toString();
                    String datee = dateInput.getText().toString();
                    String timee = timeInput.getText();

                    q2 = "insert into ticketinfo(pnr,firstname, lastname, emailid, fromcity, tocity, datecal, time) values (?,?,?,?,?,?,?,?) ";
                    PreparedStatement pst2 = conn.prepareStatement(q2);
                    pst2.setInt(1, pnr);
                    pst2.setString(2, firstname);
                    pst2.setString(3, lastname);
                    pst2.setString(4, email);
                    pst2.setString(5, fromcity);
                    pst2.setString(6, tocity);
                    pst2.setString(7, datee);
                    pst2.setString(8, timee);
                    try {
                        pst2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Ticket Generated Successfully \n Remember PNR is: " + pnr);
                        firstNameInput.setText("");
                        lastNameInput.setText("");
                        emailInput.setText("");
                        dateInput.setText("");
                        timeInput.setText("");
                    } catch (Exception e1) {
                        System.out.println(e1);
                        JOptionPane.showMessageDialog(null, "Something Went Wrong");
                    }
                    firstNameInput.requestFocus();
                } catch (Exception e2) {
                    System.out.println(e2);
                    JOptionPane.showMessageDialog(null, "Something Went Wrong");

                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookFrame.dispose();
            }
        });

        bookFrame.setVisible(true);
        bookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}