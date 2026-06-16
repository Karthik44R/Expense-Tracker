import DataBase.UserAuthentication;
import DataBase.DBConnection;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class ExpenseTracker {
    private static int loggedInUserId = -1;
//Register    
public static class Register extends JFrame{
    public Register(){
        JFrame frame=new JFrame();
        setTitle("Register");
        setSize(900, 500);
        setResizable(false);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0, 100,120));
        leftPanel.setBounds(0, 0, 420, 500);
        
        ImageIcon imageorg = new ImageIcon("src/images/login.png");
        Image scaled = imageorg.getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaled);

        JLabel image = new JLabel();
        image.setIcon(icon1);
        image.setBounds(70, 60, 300, 220);
        leftPanel.add(image);

        JLabel logotext = new JLabel("Expense Tracker", JLabel.CENTER);
        logotext.setForeground(Color.WHITE);
        logotext.setFont(new Font("Serif", Font.PLAIN, 26));
        logotext.setBounds(65, 300, 300, 40);
        leftPanel.add(logotext);
        add(leftPanel);

        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(420, 0, 480, 500);
        add(rightPanel);
        
        JLabel logintitle = new JLabel("Register", SwingConstants.CENTER);
        logintitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logintitle.setForeground(new Color(0, 100, 120));
        logintitle.setBounds(150, 60, 180, 40);
        rightPanel.add(logintitle);

        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(Color.RED);
        messageLabel.setBounds(110, 120, 350, 30);
        rightPanel.add(messageLabel);

        JLabel logint = new JLabel("Register", SwingConstants.CENTER);
        logint.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logint.setForeground(new Color(0, 100,120));
        logint.setBounds(150, 60, 180, 40);
        rightPanel.add(logint);

        JLabel ulabel = new JLabel("User Name");
        ulabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ulabel.setBounds(60, 150, 120, 30);
        rightPanel.add(ulabel);

        JTextField usertext = new JTextField();
        usertext.setBounds(180, 150, 220, 30);
        rightPanel.add(usertext);

        JLabel plabel = new JLabel("Password");
        plabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        plabel.setBounds(60, 210, 120, 30);
        rightPanel.add(plabel);

        JPasswordField ptext = new JPasswordField();
        ptext.setBounds(180, 210, 220, 30);
        rightPanel.add(ptext);

        JButton register = new JButton("Register");
        register.setBackground(new Color(0, 100,120));
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        register.setBounds(180, 260, 100, 30);
        rightPanel.add(register);

        JLabel noac = new JLabel("I Have an Account");
        noac.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        noac.setBounds(100, 340, 200, 30);
        rightPanel.add(noac);

        JButton loginb = new JButton("Login");
        loginb.setBackground(new Color(0, 100,120));
        loginb.setForeground(Color.WHITE);
        loginb.setFocusPainted(false);
        loginb.setBounds(270, 340, 100, 30);
        rightPanel.add(loginb);
       
        register.addActionListener((ActionEvent e) -> {
        String username = usertext.getText().trim();
        String password = new String(ptext.getPassword()).trim();
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please Enter Username and Password");
            return;
        }
        boolean success = UserAuthentication.registerUser(username, password);
        if (success) {
                messageLabel.setText("Register Successful");
                dispose();
                new Login().setVisible(true);
        }
        else {
            messageLabel.setForeground(new Color(0,180,80));
            messageLabel.setText("Username already Exists Please Login");
        }
        });
            loginb.addActionListener((ActionEvent e) -> {
                dispose();
                SwingUtilities.invokeLater(() -> new Login().setVisible(true));
        });
    }
//Login
public static class Login extends JFrame {
    public Login() {
        JFrame frame=new JFrame();
        setTitle("Login");
        setSize(900, 500);
        setResizable(false);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0, 100,120));
        leftPanel.setBounds(0, 0, 420, 500);
        
        ImageIcon imageorg = new ImageIcon("src/images/login.png");
        Image scaled = imageorg.getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaled);
        
        JLabel image = new JLabel();
        image.setIcon(icon1);
        image.setBounds(70, 60, 300, 240);
        leftPanel.add(image);

        JLabel logotext = new JLabel("Expense Tracker", JLabel.CENTER);
        logotext.setForeground(Color.WHITE);
        logotext.setFont(new Font("Serif", Font.PLAIN, 26));
        logotext.setBounds(65, 300, 300, 40);
        leftPanel.add(logotext);
        add(leftPanel);

        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(420, 0, 480, 500);
        add(rightPanel);

        JLabel logintitle = new JLabel("Login", SwingConstants.CENTER);
        logintitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logintitle.setForeground(new Color(0, 100,120));
        logintitle.setBounds(150, 60, 180, 40);
        rightPanel.add(logintitle);
        
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(Color.RED);
        messageLabel.setBounds(110,115, 350, 30);
        rightPanel.add(messageLabel);

        JLabel ulabel = new JLabel("User Name");
        ulabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ulabel.setBounds(60, 150, 120, 30);
        rightPanel.add(ulabel);

        JTextField usertext = new JTextField();
        usertext.setBounds(180, 150, 220, 30);
        rightPanel.add(usertext);

        JLabel plabel = new JLabel("Password");
        plabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        plabel.setBounds(60, 210, 120, 30);
        rightPanel.add(plabel);

        JPasswordField ptext = new JPasswordField();
        ptext.setBounds(180, 210, 220, 30);
        rightPanel.add(ptext);

        JButton loginb = new JButton("Login");
        loginb.setBackground(new Color(0, 100,120));
        loginb.setForeground(Color.WHITE);
        loginb.setFocusPainted(false);
        loginb.setBounds(180, 260, 100, 30);
        rightPanel.add(loginb);

        JLabel noac = new JLabel("I Don’t Have an Account");
        noac.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        noac.setBounds(60, 340, 200, 30);
        rightPanel.add(noac);

        JButton register = new JButton("Register");
        register.setBackground(new Color(0, 100,120));
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        register.setBounds(270, 340, 100, 30);
        rightPanel.add(register);
    
        loginb.addActionListener((ActionEvent e) -> {
        String username = usertext.getText().trim();
        String password = new String(ptext.getPassword()).trim();
        if (username.isEmpty() || password.isEmpty()) {
        messageLabel.setForeground(Color.RED);
        messageLabel.setText("Please Enter Username and Password");
        return;
        }
        boolean success = UserAuthentication.loginUser(username, password);
        if (success) {
            dispose();
            SwingUtilities.invokeLater(() -> new dashboard(usertext).setVisible(true));
        }
        else {
            messageLabel.setText("Invalid Username or Password");
        }
        });
        register.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Register().setVisible(true));
        });
    }
}
//DashBoard
public static class dashboard extends JFrame {
    public dashboard(JTextField usertext) {
        setTitle("Expense Tracker - Dashboard");
        setSize(900, 500);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel dashboardPanel = new JPanel(null);
        dashboardPanel.setBackground(new Color(20,20,130));
        dashboardPanel.setBounds(0, 0, 900, 500);
        add(dashboardPanel);

        Color orange = new Color(255, 140, 0);
        Font bfont = new Font("Segoe UI", Font.BOLD, 16);

        JButton logoutb = new JButton("Logout");
        logoutb.setBackground(orange);
        logoutb.setForeground(Color.WHITE);
        logoutb.setFont(bfont);
        logoutb.setFocusable(false);
        logoutb.setBounds(770, 20, 100, 35);
        dashboardPanel.add(logoutb);

        JLabel userLabel = new JLabel(usertext.getText(), SwingConstants.CENTER);
        userLabel.setOpaque(true);
        userLabel.setBackground(orange);
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userLabel.setBounds(50, 100, 150, 35);
        dashboardPanel.add(userLabel);

        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(Color.LIGHT_GRAY);
        balancePanel.setBounds(300, 80, 350, 120);

        JLabel balmsg = new JLabel("", SwingConstants.CENTER);
        balmsg.setFont(new Font("Segoe UI", Font.BOLD, 18));
        balmsg.setForeground(Color.BLACK);
        balancePanel.add(balmsg, BorderLayout.CENTER);
        dashboardPanel.add(balancePanel);

        JButton incomeb = new JButton("Income");
        incomeb.setBackground(orange);
        incomeb.setForeground(Color.WHITE);
        incomeb.setFont(bfont);
        incomeb.setFocusPainted(false);
        incomeb.setBounds(100, 275, 150, 40);
        dashboardPanel.add(incomeb);
        
        JButton expenseb = new JButton("Expense");
        expenseb.setBackground(orange);
        expenseb.setForeground(Color.WHITE);
        expenseb.setFont(bfont);
        expenseb.setFocusPainted(false);
        expenseb.setBounds(350, 275, 150, 40);
        dashboardPanel.add(expenseb);
        
        JButton balanceb = new JButton("Balance");
        balanceb.setBackground(orange);
        balanceb.setForeground(Color.WHITE);
        balanceb.setFont(bfont);
        balanceb.setFocusPainted(false);
        balanceb.setBounds(600, 275, 150, 40);
        dashboardPanel.add(balanceb);
        logoutb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    });
        incomeb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_income(usertext).setVisible(true));
    });
        expenseb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_expense(usertext).setVisible(true));
    });
        balanceb.addActionListener((ActionEvent e) -> {
        try (Connection con = DBConnection.getConnection()) {
//total income
            String sqlincome = "SELECT SUM(amount) FROM income WHERE user_id = ?";
            PreparedStatement psincome = con.prepareStatement(sqlincome);
            psincome.setInt(1, loggedInUserId);
            ResultSet rsincome = psincome.executeQuery();
            int tincome = 0;
            if (rsincome.next()) {
                tincome = rsincome.getInt(1);
                if (rsincome.wasNull())
                    tincome = 0;
            }
//total expense
            String sqlexpense = "SELECT SUM(amount) FROM expenses WHERE user_id = ?";
            PreparedStatement psexpense = con.prepareStatement(sqlexpense);
            psexpense.setInt(1, loggedInUserId);
            ResultSet rsexpense = psexpense.executeQuery();
            int texpense = 0;
            if (rsexpense.next()) {
                texpense = rsexpense.getInt(1);
                if (rsexpense.wasNull()) texpense = 0;
            }
            int balance = tincome - texpense;
            balmsg.setText("Total Balance : ₹ "+balance);
        }catch(Exception ex){
            balmsg.setText("Error in Calculating Balance");
        }
        });
    }   
}
//Income Dashboard
public static class dash_income extends JFrame {
    public dash_income(JTextField usertext) {
        setTitle("Expense Tracker - Income Dashboard");
        setSize(900, 500);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel dashboardPanel = new JPanel(null);
        dashboardPanel.setBackground(new Color(20,20,130));
        dashboardPanel.setBounds(0, 0, 900, 500);
        add(dashboardPanel);

        Color orange = new Color(255, 140, 0);
        Font bfont = new Font("Segoe UI", Font.BOLD, 16);

        JButton backb=new JButton("Back");
        backb.setBackground(orange);
        backb.setForeground(Color.WHITE);
        backb.setFont(bfont);
        backb.setFocusable(false);
        backb.setBounds(670,20,75,35);
        dashboardPanel.add(backb);

        JButton logoutb = new JButton("Logout");
        logoutb.setBackground(orange);
        logoutb.setForeground(Color.WHITE);
        logoutb.setFont(bfont);
        logoutb.setFocusable(false);
        logoutb.setBounds(770, 20, 88, 35);
        dashboardPanel.add(logoutb);

        JLabel userLabel = new JLabel(usertext.getText(), SwingConstants.CENTER);
        userLabel.setOpaque(true);
        userLabel.setBackground(orange);
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userLabel.setBounds(60, 145, 150, 35);
        dashboardPanel.add(userLabel);

        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(Color.lightGray);
        //balancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        balancePanel.setBounds(300, 100, 350, 120);

        JLabel balmsg = new JLabel("", SwingConstants.CENTER);
        balmsg.setFont(new Font("Segoe UI", Font.BOLD, 14));
        balmsg.setForeground(Color.BLACK);
        balancePanel.add(balmsg, BorderLayout.CENTER);
        dashboardPanel.add(balancePanel);
        
        JButton addb = new JButton("Add");
        addb.setBackground(orange);
        addb.setForeground(Color.WHITE);
        addb.setFont(bfont);
        addb.setFocusable(false);
        addb.setBounds(110, 300, 150, 40);
        dashboardPanel.add(addb);

        JButton detailsb = new JButton("Details");
        detailsb.setBackground(orange);
        detailsb.setForeground(Color.WHITE);
        detailsb.setFont(bfont);
        detailsb.setFocusable(false);
        detailsb.setBounds(360, 300, 150, 40);
        dashboardPanel.add(detailsb);

        JButton deleteb = new JButton("Delete");
        deleteb.setBackground(orange);
        deleteb.setForeground(Color.WHITE);
        deleteb.setFont(bfont);
        deleteb.setFocusable(false);
        deleteb.setBounds(610, 300, 150, 40);
        dashboardPanel.add(deleteb);
        
        try (Connection con = DBConnection.getConnection()) {
            String sqlincome = "SELECT SUM(amount) FROM income WHERE user_id = ?";
            PreparedStatement psincome = con.prepareStatement(sqlincome);
            psincome.setInt(1, loggedInUserId);
            ResultSet rsincome = psincome.executeQuery();
            int tincome = 0;
            if (rsincome.next()) {
                tincome = rsincome.getInt(1);
                if (rsincome.wasNull())
                    tincome = 0;
            }
            balmsg.setText("Total Income : ₹ "+tincome);
        }
        catch(Exception ex){
            balmsg.setText("Error in Calculating Total Income");
        }
        addb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new add_income(usertext).setVisible(true));
    });            
        deleteb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new del_income(usertext).setVisible(true));
    });
        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dashboard(usertext).setVisible(true));
    });
        logoutb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    });
        detailsb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new bal_income(usertext).setVisible(true));
    });
    }
}
//Expense Dashboard
public static class dash_expense extends JFrame {
    public dash_expense(JTextField usertext) {
        setTitle("Expense Tracker - Expense Dashboard");
        setSize(900, 500);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        JPanel dashboardPanel = new JPanel(null);
        dashboardPanel.setBackground(new Color(20,20,130));
        dashboardPanel.setBounds(0, 0, 900, 500);
        add(dashboardPanel);
        Color orange = new Color(255, 140, 0);
        Font bfont = new Font("Segoe UI", Font.BOLD, 16);
        JButton backb=new JButton("Back");
        backb.setBackground(orange);
        backb.setForeground(Color.WHITE);
        backb.setFont(bfont);
        backb.setFocusable(false);
        backb.setBounds(670,20,75,35);
        dashboardPanel.add(backb);
        JButton logoutb = new JButton("Logout");
        logoutb.setBackground(orange);
        logoutb.setForeground(Color.WHITE);
        logoutb.setFont(bfont);
        logoutb.setFocusable(false);
        logoutb.setBounds(770, 20, 88, 35);
        dashboardPanel.add(logoutb);
        
        JLabel userLabel = new JLabel(usertext.getText(), SwingConstants.CENTER);
        userLabel.setOpaque(true);
        userLabel.setBackground(orange);
        userLabel.setForeground(Color.BLACK);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userLabel.setBounds(60, 145, 150, 35);
        dashboardPanel.add(userLabel);
        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(Color.lightGray);
        //balancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        balancePanel.setBounds(300, 100, 350, 120);
        JLabel balmsg = new JLabel("", SwingConstants.CENTER);
        balmsg.setFont(new Font("Segoe UI", Font.BOLD, 14));
        balmsg.setForeground(Color.BLACK);
        balancePanel.add(balmsg, BorderLayout.CENTER);
        dashboardPanel.add(balancePanel);
        JButton addb = new JButton("Add");
        addb.setBackground(orange);
        addb.setForeground(Color.WHITE);
        addb.setFont(bfont);
        addb.setFocusPainted(false);
        addb.setBounds(110, 300, 150, 40);
        dashboardPanel.add(addb);
        JButton detailsb = new JButton("Details");
        detailsb.setBackground(orange);
        detailsb.setForeground(Color.WHITE);
        detailsb.setFont(bfont);
        detailsb.setFocusPainted(false);
        detailsb.setBounds(360, 300, 150, 40);
        dashboardPanel.add(detailsb);
        JButton deleteb = new JButton("Delete");
        deleteb.setBackground(orange);
        deleteb.setForeground(Color.WHITE);
        deleteb.setFont(bfont);
        deleteb.setFocusPainted(false);
        deleteb.setBounds(610, 300, 150, 40);
        dashboardPanel.add(deleteb);
    
        try (Connection con = DBConnection.getConnection()) {    
            String sqlexpense = "SELECT SUM(amount) FROM expenses WHERE user_id = ?";
            PreparedStatement psexpense = con.prepareStatement(sqlexpense);
            psexpense.setInt(1, loggedInUserId);
            ResultSet rsexpense = psexpense.executeQuery();
            int texpense = 0;
            if (rsexpense.next()) {
                texpense = rsexpense.getInt(1);
                if (rsexpense.wasNull()) texpense = 0;
            }
            balmsg.setText("Total Expense : ₹ "+texpense);
        }catch(Exception ex){
            balmsg.setText("Error in Calculating Total Expense");
        }
        addb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new add_expense(usertext).setVisible(true));
    });                   
        deleteb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new del_expense(usertext).setVisible(true));
    });
        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dashboard(usertext).setVisible(true));
    });
        logoutb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    });
        detailsb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new bal_expense(usertext).setVisible(true));
    });
    }
}    
    public static class add_income extends JFrame {
    public add_income(JTextField usertext) {
        setTitle("Add Income");
        setSize(900,500);
        setResizable(false);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0,100,120));
        leftPanel.setBounds(0,0,420,500);
        
        ImageIcon imageorg = new ImageIcon("src/images/income.png");
        Image scaled = imageorg.getImage().getScaledInstance(240,240,Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaled);
        JLabel image = new JLabel();
        image.setIcon(icon1);
        image.setBounds(93,60,300,220);
        leftPanel.add(image);
        
        JLabel logotext = new JLabel("Expense Tracker",JLabel.CENTER);
        logotext.setForeground(Color.WHITE);
        logotext.setFont(new Font("Serif",Font.PLAIN,26));
        logotext.setBounds(65,300,300,40);
        leftPanel.add(logotext);
        add(leftPanel);
        
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(420,0,480,500);
        add(rightPanel);
        
        JButton backb=new JButton("Back");
        backb.setFont(new Font("Segoe UI",Font.BOLD,18));
        backb.setBackground(new Color(0,100,120));
        backb.setForeground(Color.WHITE);
        backb.setBounds(15,15,80,30);
        backb.setFocusable(false);
        rightPanel.add(backb);
//back button at right        
//        backb.setForeground(new Color(0, 100,120));
//        backb.setBounds(650,40,180,40);
//        rightPanel.add(backb);
        
        JLabel logintitle = new JLabel("Income",SwingConstants.CENTER);
        logintitle.setFont(new Font("Segoe UI",Font.BOLD,28));
        logintitle.setForeground(new Color(0,100,120));
        logintitle.setBounds(150,50,180,40);
        rightPanel.add(logintitle);
        JLabel dtlb = new JLabel("Date");
        dtlb.setFont(new Font("Segoe UI",Font.PLAIN,16));
        dtlb.setBounds(60,135,120,30);
        rightPanel.add(dtlb);
        
        JTextField datetext = new JTextField();
        datetext.setBounds(180,136,220,30);
        rightPanel.add(datetext);
        
        JLabel deslb = new JLabel("Description");
        deslb.setFont(new Font("Segoe UI",Font.PLAIN,16));
        deslb.setBounds(60,185,120,30);
        rightPanel.add(deslb);
        
        JTextField destext = new JTextField();
        destext.setBounds(180,186,220,30);
        rightPanel.add(destext);
        
        JLabel amtlb = new JLabel("Amount");
        amtlb.setFont(new Font("Segoe UI",Font.PLAIN,16));
        amtlb.setBounds(60,245,120,30);
        rightPanel.add(amtlb);
        
        JTextField amttext = new JTextField();
        amttext.setBounds(180,246,220,30);
        rightPanel.add(amttext);
        
        JLabel msglb = new JLabel("", SwingConstants.CENTER);
        msglb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        msglb.setForeground(Color.green);
        msglb.setBounds(115,85,250,30);
        rightPanel.add(msglb);
        
        JButton addb = new JButton("ADD");
        addb.setBackground(new Color(0,100,120));
        addb.setForeground(Color.WHITE);
        addb.setFocusPainted(false);
        addb.setBounds(182,320,100,30);
        addb.setFocusable(false);
        rightPanel.add(addb);
        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_income(usertext).setVisible(true));
    }); 
        addb.addActionListener((ActionEvent e) -> {
            try (Connection con = DBConnection.getConnection()) {
                String date = datetext.getText().trim().replace("/", "-");
                String desc = destext.getText().trim();
                String amt=amttext.getText().trim();
                if (date.isEmpty() || desc.isEmpty() || amt.isEmpty()) {
                    msglb.setForeground(Color.red);
                    msglb.setText("Please Enter All Details");
                    addb.setEnabled(true);
                    return;
                }
                int amount = Integer.parseInt(amt);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate ldate = LocalDate.parse(date, format);
                java.sql.Date Date = java.sql.Date.valueOf(ldate);
                String sql = "INSERT INTO income (inc_date, description, amount, user_id) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, Date);
                ps.setString(2, desc);
                ps.setInt(3, amount);
                ps.setInt(4, loggedInUserId);
                ps.executeUpdate();
                msglb.setForeground(Color.green);
                msglb.setText("Income Added Successfully");
                datetext.setText("");
                destext.setText("");
                amttext.setText("");
            }
                catch (Exception ex) {
                    msglb.setForeground(Color.red);
                    msglb.setText("Invalid Details");
            }finally {
                    addb.setEnabled(true);
                }
        });
    }
}
    public static class add_expense extends JFrame {
    public add_expense(JTextField usertext) {
        setTitle("Add Expense");
        setSize(900,500);
        setResizable(false);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0,100,120));
        leftPanel.setBounds(0,0,420,500);
     
        ImageIcon imageorg = new ImageIcon("src/images/expense.png");
        Image scaled = imageorg.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaled);
        JLabel image = new JLabel();
        image.setIcon(icon1);
        image.setBounds(120,60,300,220);
        leftPanel.add(image);
        
        JLabel logotext = new JLabel("Expense Tracker",JLabel.CENTER);
        logotext.setForeground(Color.WHITE);
        logotext.setFont(new Font("Serif",Font.PLAIN,26));
        logotext.setBounds(65,300,300,40);
        leftPanel.add(logotext);
        add(leftPanel);
        
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(420,0,480,500);
        add(rightPanel);
        
        JLabel msglb = new JLabel("", SwingConstants.CENTER);
        msglb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        msglb.setForeground(Color.green);
        msglb.setBounds(115,85,250,30);
        rightPanel.add(msglb);
        
        JButton backb=new JButton("Back");
        backb.setFont(new Font("Segoe UI",Font.BOLD,18));
        backb.setBackground(new Color(0,100,120));
        backb.setForeground(Color.WHITE);
        backb.setBounds(15,15,80,30);
        backb.setFocusable(false);
        rightPanel.add(backb);
//back button at right        
//        backb.setForeground(new Color(0, 100,120));
//        backb.setBounds(650,40,180,40);
//        rightPanel.add(backb);
        JLabel logintitle = new JLabel("Expense", SwingConstants.CENTER);
        logintitle.setFont(new Font("Segoe UI",Font.BOLD,28));
        logintitle.setForeground(new Color(0,100,120));
        logintitle.setBounds(150,40,180,40);
        rightPanel.add(logintitle);
        
        JLabel dtlb = new JLabel("Date");
        dtlb.setFont(new Font("Segoe UI",Font.PLAIN,16));
        dtlb.setBounds(60,125,120,30);
        rightPanel.add(dtlb);
        
        JTextField datetext = new JTextField();
        datetext.setBounds(180,126,220,30);
        rightPanel.add(datetext);
        
        JLabel deslb = new JLabel("Description");
        deslb.setFont(new Font("Segoe UI",Font.PLAIN,16));
        deslb.setBounds(60,185,120,30);
        rightPanel.add(deslb);
        
        JTextField destext = new JTextField();
        destext.setBounds(180,186,220,30);
        rightPanel.add(destext);
        
        JLabel amtlb = new JLabel("Amount");
        amtlb.setFont(new Font("Segoe UI",Font.PLAIN,16));
        amtlb.setBounds(60,245,120,30);
        rightPanel.add(amtlb);
        
        JTextField amttext = new JTextField();
        amttext.setBounds(180,246,220,30);
        rightPanel.add(amttext);
        
        JButton addb = new JButton("ADD");
        addb.setBackground(new Color(0,100,120));
        addb.setForeground(Color.WHITE);
        addb.setBounds(182,320,100,30);
        addb.setFocusable(false);
        rightPanel.add(addb);
        
        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_expense(usertext).setVisible(true));
    });
        addb.addActionListener((ActionEvent e) -> {
            try (Connection con = DBConnection.getConnection()) {
                String date = datetext.getText().trim().replace("/","-");
                String desc = destext.getText().trim();
                String amt=amttext.getText().trim();
                if (date.isEmpty() || desc.isEmpty() || amt.isEmpty()) {
                    msglb.setForeground(Color.red);
                    msglb.setText("Please Enter All Details");
                    addb.setEnabled(true);
                    return;
                }
                int amount = Integer.parseInt(amt);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate ldate = LocalDate.parse(date, format);
                java.sql.Date Date = java.sql.Date.valueOf(ldate);
                String sql = "INSERT INTO expenses (exp_date, description, amount, user_id) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, Date);
                ps.setString(2, desc);
                ps.setInt(3, amount);
                ps.setInt(4, loggedInUserId);
                ps.executeUpdate();
                msglb.setForeground(Color.green);
                msglb.setText("Expense Added Successfully");
                datetext.setText("");
                destext.setText("");
                amttext.setText("");
            }
                catch (Exception ex) {
                    msglb.setForeground(Color.red);
                    msglb.setText("Invalid Details");
            }finally {
                    addb.setEnabled(true);
                }
        });
    }
}
    public static class del_income extends JFrame {
    public del_income(JTextField usertext) {
        setTitle("Delete Income");
        setSize(900,500);
        setResizable(false);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0,100,120));
        leftPanel.setBounds(0,0,420,500);
        
        ImageIcon imageorg = new ImageIcon("src/images/income.png");
        Image scaled = imageorg.getImage().getScaledInstance(240,240,Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaled);
        JLabel image = new JLabel();
        image.setIcon(icon1);
        image.setBounds(93,60,300,220);
        leftPanel.add(image);
        
        JLabel logotext = new JLabel("Expense Tracker",JLabel.CENTER);
        logotext.setForeground(Color.WHITE);
        logotext.setFont(new Font("Serif",Font.PLAIN,26));
        logotext.setBounds(65,300,300,40);
        leftPanel.add(logotext);
        add(leftPanel);
        
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(420,0,480,500);
        add(rightPanel);
        
        JLabel msglb = new JLabel("", SwingConstants.CENTER);
        msglb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        msglb.setForeground(Color.green);
        msglb.setBounds(115,110,250,30);
        rightPanel.add(msglb);
        
        JButton backb=new JButton("Back");
        backb.setFont(new Font("Segoe UI",Font.BOLD,18));
        backb.setBackground(new Color(0,100,120));
        backb.setForeground(Color.WHITE);
        backb.setBounds(15,15,80,30);
        backb.setFocusable(false);
        rightPanel.add(backb);
        
        JLabel logintitle = new JLabel("Income",SwingConstants.CENTER);
        logintitle.setFont(new Font("Segoe UI",Font.BOLD,28));
        logintitle.setForeground(new Color(0,100,120));
        logintitle.setBounds(150,60,180,40);
        rightPanel.add(logintitle);
        
        JLabel idlb = new JLabel("ID");
        idlb.setFont(new Font("Segoe UI",Font.PLAIN,20));
        idlb.setBounds(80,175,120,30);
        rightPanel.add(idlb);
        
        JTextField Idtext = new JTextField();
        Idtext.setBounds(160,177,220,30);
        rightPanel.add(Idtext);
        
        JButton delb = new JButton("DELETE");
        delb.setBackground(new Color(0,100,120));
        delb.setForeground(Color.WHITE);
        delb.setBounds(182,270,100,40);
        delb.setFocusable(false);
        rightPanel.add(delb);
        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_income(usertext).setVisible(true));
    });
        delb.addActionListener((ActionEvent e) -> {
            try (Connection con = DBConnection.getConnection()) {
            String idtext = Idtext.getText().trim();
            if (idtext.isEmpty()) {
                msglb.setForeground(Color.red);
                msglb.setText("Please Enter a valid ID");
                delb.setEnabled(true);
                return;
            }
            int Id = Integer.parseInt(idtext);
            String sql = "DELETE FROM income WHERE id = ? AND user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            ps.setInt(2, loggedInUserId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                Idtext.setText("");
                msglb.setForeground(Color.green);
                msglb.setText("Income deleted successfully");
            } else {
                msglb.setForeground(Color.red);
                msglb.setText("No Income found with that ID");
            }
        } catch (Exception ex) {
            msglb.setForeground(Color.red);
            msglb.setText("Error in Deleting");
        }finally {
                delb.setEnabled(true);
            }
    });
    }
}
//Delete Expense
    public static class del_expense extends JFrame {
    public del_expense(JTextField usertext) {
        setTitle("Delete Expense");
        setSize(900,500);
        setResizable(false);
        ImageIcon icon=new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0,100,120));
        leftPanel.setBounds(0,0,420,500);
        
        ImageIcon imageorg = new ImageIcon("src/images/expense.png");
        Image scaled = imageorg.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(scaled);
        JLabel image = new JLabel();
        image.setIcon(icon1);
        image.setBounds(120,60,300,220);
        leftPanel.add(image);
        
        JLabel logotext = new JLabel("Expense Tracker",JLabel.CENTER);
        logotext.setForeground(Color.WHITE);
        logotext.setFont(new Font("Serif",Font.PLAIN,26));
        logotext.setBounds(65,300,300,40);
        leftPanel.add(logotext);
        add(leftPanel);
        
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(420,0,480,500);
        add(rightPanel);
        
        JLabel msglb = new JLabel("", SwingConstants.CENTER);
        msglb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        msglb.setForeground(Color.green);
        msglb.setBounds(115,110,250,30);
        rightPanel.add(msglb);
        
        JButton backb=new JButton("Back");
        backb.setFont(new Font("Segoe UI",Font.BOLD,18));
        backb.setBackground(new Color(0,100,120));
        backb.setForeground(Color.WHITE);
        backb.setBounds(15,15,80,30);
        backb.setFocusable(false);
        rightPanel.add(backb);
        
        JLabel logintitle = new JLabel("Expense",SwingConstants.CENTER);
        logintitle.setFont(new Font("Segoe UI",Font.BOLD,28));
        logintitle.setForeground(new Color(0,100,120));
        logintitle.setBounds(150,60,180,40);
        rightPanel.add(logintitle);
        
        JLabel idlb = new JLabel("ID");
        idlb.setFont(new Font("Segoe UI",Font.PLAIN,20));
        idlb.setBounds(80,175,120,30);
        rightPanel.add(idlb);
        JTextField Idtext = new JTextField();
        Idtext.setBounds(160,177,220,30);
        rightPanel.add(Idtext);
        
        JButton delb = new JButton("DELETE");
        delb.setBackground(new Color(0,100,120));
        delb.setForeground(Color.WHITE);
        delb.setBounds(182,270,100,40);
        delb.setFocusable(false);
        rightPanel.add(delb);
        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_expense(usertext).setVisible(true));
    });
        delb.addActionListener((ActionEvent e) -> {
            try (Connection con = DBConnection.getConnection()) {
            String idtext = Idtext.getText().trim();
            if (idtext.isEmpty()) {
                msglb.setForeground(Color.red);
                msglb.setText("Please Enter a valid ID");
                delb.setEnabled(true);
                return;
            }
            int Id = Integer.parseInt(idtext);
            String sql = "DELETE FROM expenses WHERE id = ? AND user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            ps.setInt(2, loggedInUserId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                Idtext.setText("");
                msglb.setForeground(Color.green);
                msglb.setText("Expense deleted successfully");
            } else {
                msglb.setForeground(Color.red);
                msglb.setText("No Expense found with that ID");
            }
        } catch (Exception ex) {
            msglb.setForeground(Color.red);
            msglb.setText("Error in Deleting");
        }finally {
                delb.setEnabled(true);
            }
    });
    }
}
//balance income
public static class bal_income extends JFrame {
    public bal_income(JTextField usertext) {
        setTitle("Expense Tracker");
        setSize(800, 500);
        ImageIcon icon = new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Top panel
        JPanel top = new JPanel(null);
        top.setBackground(new Color(0, 90, 100));
        top.setBounds(0, 0, 800, 60);
        add(top);

        JButton backb = new JButton("Back");
        backb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        backb.setBackground(new Color(0, 100, 120));
        backb.setForeground(Color.WHITE);
        backb.setBounds(690, 15, 80, 30);
        backb.setFocusable(false);
        top.add(backb);

        JLabel title1 = new JLabel("INCOME");
        title1.setFont(new Font("Serif", Font.BOLD, 28));
        title1.setForeground(Color.WHITE);
        title1.setBounds(310, 10, 150, 40);
        top.add(title1);

        // Column headers
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(30, 70, 80, 25);
        add(idLabel);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(140, 70, 100, 25);
        add(dateLabel);

        JLabel descLabel = new JLabel("Description");
        descLabel.setBounds(300, 70, 200, 25);
        add(descLabel);

        JLabel amtLabel = new JLabel("Amount");
        amtLabel.setBounds(560, 70, 100, 25);
        add(amtLabel);

        // Scrollable panel for rows
        JPanel rowsPanel = new JPanel(null);
        JScrollPane scrollPane = new JScrollPane(rowsPanel);
        scrollPane.setBounds(0, 100, 780, 350);
        add(scrollPane);

        try (Connection con = DBConnection.getConnection()) {
            // Fetch expenses ordered by date descending (latest first)
            String sql = "SELECT id, inc_date, description, amount FROM income " +
                         "WHERE user_id = ? ORDER BY inc_date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, loggedInUserId);
            ResultSet rs = ps.executeQuery();

            int y = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            while (rs.next()) {
                int id = rs.getInt("id");
                java.sql.Date sqlDate = rs.getDate("inc_date");
                String dateStr = sqlDate.toLocalDate().format(formatter);
                String desc = rs.getString("description");
                int amt = rs.getInt("amount");

                // Row panel
                JPanel row = new JPanel(null);
                row.setBackground((y / 40) % 2 == 0 ? Color.WHITE : new Color(230, 230, 230));
                row.setBounds(0, y, 780, 40);

                // Row labels
                JLabel idLbl = new JLabel(String.valueOf(id));
                idLbl.setBounds(30, 5, 80, 30);
                row.add(idLbl);

                JLabel dateLbl = new JLabel(dateStr);
                dateLbl.setBounds(140, 5, 100, 30);
                row.add(dateLbl);

                JLabel descLbl = new JLabel(desc);
                descLbl.setBounds(300, 5, 200, 30);
                row.add(descLbl);

                JLabel amtLbl = new JLabel("₹ " + amt);
                amtLbl.setBounds(560, 5, 100, 30);
                row.add(amtLbl);

                rowsPanel.add(row);
                y += 40;
            }

            // Set preferred height for scroll
            rowsPanel.setPreferredSize(new Dimension(780, y));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_income(usertext).setVisible(true));
        });
    }
}
//balance expense
public static class bal_expense extends JFrame {
    public bal_expense(JTextField usertext) {
        setTitle("Expense Tracker");
        setSize(800, 500);
        ImageIcon icon = new ImageIcon("src/images/budget.png");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Top panel
        JPanel top = new JPanel(null);
        top.setBackground(new Color(0, 90, 100));
        top.setBounds(0, 0, 800, 60);
        add(top);

        JButton backb = new JButton("Back");
        backb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        backb.setBackground(new Color(0, 100, 120));
        backb.setForeground(Color.WHITE);
        backb.setBounds(690, 15, 80, 30);
        backb.setFocusable(false);
        top.add(backb);

        JLabel title1 = new JLabel("EXPENSES");
        title1.setFont(new Font("Serif", Font.BOLD, 28));
        title1.setForeground(Color.WHITE);
        title1.setBounds(310, 10, 150, 40);
        top.add(title1);

        // Column headers
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(30, 70, 80, 25);
        add(idLabel);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(140, 70, 100, 25);
        add(dateLabel);

        JLabel descLabel = new JLabel("Description");
        descLabel.setBounds(300, 70, 200, 25);
        add(descLabel);

        JLabel amtLabel = new JLabel("Amount");
        amtLabel.setBounds(560, 70, 100, 25);
        add(amtLabel);

        // Scrollable panel for rows
        JPanel rowsPanel = new JPanel(null);
        JScrollPane scrollPane = new JScrollPane(rowsPanel);
        scrollPane.setBounds(0, 100, 780, 350);
        add(scrollPane);

        try (Connection con = DBConnection.getConnection()) {
            // Fetch expenses ordered by date descending (latest first)
            String sql = "SELECT id, exp_date, description, amount FROM expenses " +
                         "WHERE user_id = ? ORDER BY exp_date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, loggedInUserId);
            ResultSet rs = ps.executeQuery();

            int y = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            while (rs.next()) {
                int id = rs.getInt("id");
                java.sql.Date sqlDate = rs.getDate("exp_date");
                String dateStr = sqlDate.toLocalDate().format(formatter);
                String desc = rs.getString("description");
                int amt = rs.getInt("amount");

                // Row panel
                JPanel row = new JPanel(null);
                row.setBackground((y / 40) % 2 == 0 ? Color.WHITE : new Color(230, 230, 230));
                row.setBounds(0, y, 780, 40);

                // Row labels
                JLabel idLbl = new JLabel(String.valueOf(id));
                idLbl.setBounds(30, 5, 80, 30);
                row.add(idLbl);

                JLabel dateLbl = new JLabel(dateStr);
                dateLbl.setBounds(140, 5, 100, 30);
                row.add(dateLbl);

                JLabel descLbl = new JLabel(desc);
                descLbl.setBounds(300, 5, 200, 30);
                row.add(descLbl);

                JLabel amtLbl = new JLabel("₹ " + amt);
                amtLbl.setBounds(560, 5, 100, 30);
                row.add(amtLbl);

                rowsPanel.add(row);
                y += 40;
            }

            // Set preferred height for scroll
            rowsPanel.setPreferredSize(new Dimension(780, y));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        backb.addActionListener((ActionEvent e) -> {
            dispose();
            SwingUtilities.invokeLater(() -> new dash_expense(usertext).setVisible(true));
        });
    }
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
}