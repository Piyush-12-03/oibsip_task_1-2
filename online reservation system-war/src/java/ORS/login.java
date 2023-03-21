package ORS;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login {

    JFrame loginFrame;
    JLabel titlelabel, usernametext, passwordtext, logintext;
    JTextField usernameinput;
    JPasswordField passwordinput;
    JButton loginButton, registerButton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/pkdb1?useSSL=false";
    String user = "root";
    String password = "root";

    public login() {

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

        loginFrame = new JFrame("Registration System By-Piyush kag");
        loginFrame.setSize(4000, 4000);
        loginFrame.setLayout(null);
        loginFrame.setBackground(Color.yellow);

        titlelabel = new JLabel("Online Reservation System");
        titlelabel.setBounds(500, 5, 500, 200);
        titlelabel.setForeground(Color.BLACK);
        Font font1 = new Font("Poppins", Font.BOLD, 35);
        titlelabel.setFont(font1);
        loginFrame.add(titlelabel);

        logintext = new JLabel("LOGIN");
        logintext.setBounds(650, 150, 400, 200);
        logintext.setForeground(Color.BLACK);
        Font font2 = new Font("Poppins", Font.BOLD, 32);
        logintext.setFont(font2);
        loginFrame.add(logintext);

        usernametext = new JLabel("Username");
        usernametext.setBounds(400, 300, 100, 40);
        usernametext.setForeground(Color.BLACK);
        Font font3 = new Font("Poppins", Font.BOLD, 20);
        usernametext.setFont(font3);
        loginFrame.add(usernametext);

        passwordtext = new JLabel("Password");
        passwordtext.setBounds(400, 350, 100, 40);
        passwordtext.setForeground(Color.BLACK);
        Font font4 = new Font("Poppins", Font.BOLD, 20);
        passwordtext.setFont(font4);
        loginFrame.add(passwordtext);

        usernameinput = new JTextField();
        usernameinput.setBounds(550, 300, 300, 40);
        usernameinput.requestFocus();
        loginFrame.add(usernameinput);

        passwordinput = new JPasswordField();
        passwordinput.setBounds(550, 350, 300, 40);
        loginFrame.add(passwordinput);

        loginButton = new JButton("Login");
        loginButton.setBounds(550, 400, 140, 40);
        loginFrame.add(loginButton);

        registerButton = new JButton("Sign Up");
        registerButton.setBounds(700, 400, 150, 40);
        loginFrame.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (login()) {
                    loginFrame.dispose();
                    Menu menu = new Menu();   
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup signup = new Signup();
            }
        });

        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean login() {
        try {
            String user = usernameinput.getText();
            int pwd = Integer.parseInt(passwordinput.getText());
            String q = "select * from orslogin where Name = ? and Pass = ? ";
            PreparedStatement pst1 = conn.prepareStatement(q);
            pst1.setString(1, user);
            pst1.setInt(2, pwd);
            ResultSet rSet = pst1.executeQuery();
            if (rSet.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password");
                usernameinput.setText("");
                passwordinput.setText("");
                usernameinput.requestFocus();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}