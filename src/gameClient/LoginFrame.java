package gameClient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginFrame extends JFrame implements ActionListener {

    Container c = getContentPane();
    JLabel userName = new JLabel("User Name");
    JLabel levelnumber = new JLabel("Level Number");
    JTextField UF = new JTextField();
    JTextField LF = new JTextField();
    JButton login = new JButton("Login");
    JButton clear = new JButton("Clear");
    JFrame F = new JFrame();
    int level_number;
    int id;


    public LoginFrame() {
        JPanel p = new JPanel();
        F.add(p);
        p.setLayout(null);
        F.setSize(500, 500);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Sizes();
        addVariablesToFrame();
        c.setLayout(null);
        login.setVisible(true);
    }

        public void Sizes () {
            userName.setBounds(100, 150, 100, 30);
            levelnumber.setBounds(100, 220, 100, 30);
            UF.setBounds(250, 150, 150, 30);
            LF.setBounds(250, 220, 150, 30);
            login.setBounds(150, 300, 100, 30);
            clear.setBounds(300, 300, 100, 30);
        }

        public void addVariablesToFrame () {
            c.add(userName);
            c.add(UF);
            c.add(levelnumber);
            c.add(LF);
            c.add(login);
            c.add(clear);
        }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == LF) {
                String l = LF.getText();
                int level = Integer.parseInt(l);
                this.level_number = level;
                System.out.println(level);
            }
            if (e.getSource() == login) {
                String u = UF.getText();
                int user_id = Integer.parseInt(u);
                this.id = user_id;
                System.out.println(user_id);
            }
        }

        public void actionPerformed (MouseEvent e){
            if (e.getSource() == LF) {
                String l = LF.getText();
                int level = Integer.parseInt(l);
                this.level_number = level;
            }
            if (e.getSource() == login) {
                String u = UF.getText();
                int user_id = Integer.parseInt(u);
                this.id = user_id;
            }
        }

        public static void main (String[]args){
            LoginFrame frame = new LoginFrame();
            frame.setTitle("Login Page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
        }

    }


