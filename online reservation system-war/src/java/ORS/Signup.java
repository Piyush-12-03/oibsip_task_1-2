package ORS;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup {
    JFrame signupFrame;
    JLabel titlelabel, usernametext, passwordtext, signuptext;
    JTextField usernameinput;
    JPasswordField passwordinput;
    JButton backButton, registerButton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/pkdb1?useSSL=false";
    String user = "root";
    String password = "root";

    public Signup() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
            String sql = "create database if not exists pkdb1";
            stmt.executeUpdate(sql);
            sql = "use pkdb1";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }

        signupFrame = new JFrame("Registration System By-Piyush kag");
        signupFrame.setSize(4000, 4000);
        signupFrame.setLayout(null);
        signupFrame.setBackground(Color.yellow);

        titlelabel = new JLabel("Online Reservation System");
        titlelabel.setBounds(500, 5, 500, 200);
        titlelabel.setForeground(Color.BLACK);
        Font font1 = new Font("Poppins", Font.BOLD, 35);
        titlelabel.setFont(font1);
        signupFrame.add(titlelabel);

        signuptext = new JLabel("SIGNUP");
        signuptext.setBounds(640, 150, 400, 200);
        signuptext.setForeground(Color.BLACK);
        Font font2 = new Font("Poppins", Font.BOLD, 32);
        signuptext.setFont(font2);
        signupFrame.add(signuptext);

        usernametext = new JLabel("Username");
        usernametext.setBounds(400, 300, 100, 40);
        usernametext.setForeground(Color.BLACK);
        Font font3 = new Font("Poppins", Font.BOLD, 20);
        usernametext.setFont(font3);
        signupFrame.add(usernametext);

        passwordtext = new JLabel("Password");
        passwordtext.setBounds(400, 350, 100, 40);
        passwordtext.setForeground(Color.BLACK);
        Font font4 = new Font("Poppins", Font.BOLD, 20);
        passwordtext.setFont(font4);
        signupFrame.add(passwordtext);

        usernameinput = new JTextField();
        usernameinput.setBounds(550, 300, 300, 40);
        usernameinput.requestFocus();
        signupFrame.add(usernameinput);

        passwordinput = new JPasswordField();
        passwordinput.setBounds(550, 350, 300, 40);
        signupFrame.add(passwordinput);

        registerButton = new JButton("Register");
        registerButton.setBounds(550, 400, 140, 40);
        signupFrame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertintodatabase();
            }
        });

        backButton = new JButton("Back to Login");
        backButton.setBounds(700, 400, 150, 40);
        signupFrame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });

        signupFrame.setVisible(true);
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void insertintodatabase() {
        try {
            String q1, q2;
            String user = usernameinput.getText();
            int pwd = Integer.parseInt(passwordinput.getText());

            q1 = "create table if not exists orslogin (Name varchar(100) unique not null, Pass int not null)";
            PreparedStatement pst1 = conn.prepareStatement(q1);
            pst1.execute();

            q2 = "insert into orslogin(Name,Pass) values (?,?) ";
            PreparedStatement pst2 = conn.prepareStatement(q2);
            pst2.setString(1, user);
            pst2.setInt(2, pwd);
            try {
                pst2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Account Created");
                passwordinput.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Username must be unique");
            }
            usernameinput.setText("");
            usernameinput.requestFocus();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void back() {
        signupFrame.dispose();
    }
}