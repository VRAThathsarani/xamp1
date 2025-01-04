import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Registration form");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel signUp = new JLabel("Sign Up");
        signUp.setBounds(225,20,100,25);
        signUp.setFont(new Font("Arial",Font.BOLD,20));
        signUp.setForeground(Color.BLUE);
        panel.add(signUp);

        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setBounds(20,70,100,25);
        nameLabel.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(nameLabel);

        JTextField nameText  = new JTextField();
        nameText.setBounds(200,70,200,25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email :");
        emailLabel.setBounds(20,120,100,25);
        emailLabel.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(emailLabel);

        JTextField emailText = new JTextField();
        emailText.setBounds(200,120,200,25);
        panel.add(emailText);

        JLabel genderLabel = new JLabel("Gender :");
        genderLabel.setBounds(20,170,100,25);
        genderLabel.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(genderLabel);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setBounds(200,170,100,25);
        maleButton.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(maleButton);

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(300,170,100,25);
        femaleButton.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(femaleButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);



        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(20,220,200,25);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(passwordLabel);

        JTextField passwordText = new JTextField();
        passwordText.setBounds(200,220,200,25);
        panel.add(passwordText);

        JCheckBox checkBox = new JCheckBox("I am not a robot.");
        checkBox.setBounds(20,270,300,25);
        panel.add(checkBox);

        String[] dates = {"1","2,","3","4","5"};
        String[] months = {"Jan","Feb","March","April"};
        String[] years = {"2001","2002","2003","2004"};

        JLabel dobLabel = new JLabel("DOB :");
        dobLabel.setBounds(20,320,100,25);
        dobLabel.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(dobLabel);

        JComboBox<String> date = new JComboBox<>(dates);
        date.setBounds(200,320,50,25);
        panel.add(date);

        JComboBox<String> month = new JComboBox<>(months);
        month.setBounds(250,320,50,25);
        panel.add(month);

        JComboBox<String> year = new JComboBox<>(years);
        year.setBounds(300,320,100,25);
        panel.add(year);


        JButton submitButton = new JButton("Sign Up");
        submitButton.setBounds(200,370,100,25);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLUE);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = maleButton.isSelected() ? "Male" : "Female";
                String d = (String) date.getSelectedItem() + month.getSelectedItem() + year.getSelectedItem();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root","");

                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user1(name,email,gender,password,DOB) VALUES(?,?,?,?,?)");

                    ps.setObject(1,nameText.getText());
                    ps.setObject(2,emailText.getText());
                    ps.setObject(3,gender);
                    ps.setObject(4,passwordText.getText());
                    ps.setObject(5,d);
                    ps.executeUpdate();

                    System.out.println("Registration Successfully.");

                    JOptionPane.showMessageDialog(null,"Registration Successfully." ,"Sucess" ,JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }

            }
        });



        frame.setVisible(true);
    }
}