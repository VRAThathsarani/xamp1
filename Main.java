import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){

        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,500);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel signLabel = new JLabel("Sign Up");
        signLabel.setBounds(150,10,200,30);
        signLabel.setForeground(Color.BLUE);
        frame.setResizable(false);
        panel.add(signLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50,60,100,25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField();
        nameText.setBounds(150,60,100,25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50,120,100,25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField();
        emailText.setBounds(150,120,100,25);
        panel.add(emailText);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50,180,100,25);
        panel.add(genderLabel);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setBounds(150,180,100,25);
        panel.add(maleButton);

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(250,180,100,25);
        panel.add(femaleButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        String gender = maleButton.isSelected() ? "Male" : "Female";

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50,240,100,25);
        panel.add(passwordLabel);

        JTextField passwordText = new JTextField();
        passwordText.setBounds(150,240,100,25);
        panel.add(passwordText);

        JCheckBox checkBox = new JCheckBox("I am not a robbot.");
        checkBox.setBounds(50,300,300,25);
        panel.add(checkBox);

        JButton submitButton = new JButton("Sign Up");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(50,360,300,25);
        panel.add(submitButton);

        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");

                    PreparedStatement pstm = connection.prepareStatement("INSERT INTO registration(name,email,gender,password) VALUES(?,?,?,?)");

                    pstm.setObject(1, nameText.getText());
                    pstm.setObject(2, emailText.getText());
                    pstm.setObject(3, gender);
                    pstm.setObject(4, passwordText.getText());

                    pstm.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Sign Up successful", "Success",JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}