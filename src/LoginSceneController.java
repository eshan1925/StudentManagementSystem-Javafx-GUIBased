import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginSceneController {

    @FXML
    private Button logIn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userId;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    void loginUser(ActionEvent event) {
        String a = userId.getText();
        String b = password.getText();
        if(a.equals("") && b.equals("")){
            JOptionPane.showMessageDialog(null, "Username or Password Blank", "Ooooops!!!", 0);
        }else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan","eventour2020");
                pst = con.prepareStatement("select * from users where username_=? and password_=?");
                pst.setString(1, a);
                pst.setString(2, b);
                rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Success","Login Success",1);
                }else{
                    JOptionPane.showMessageDialog(null,"Check the credentials","Login Failure",0);
                    userId.setText("");
                    password.setText("");
                    userId.requestFocus();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        // try {
        //     Class.forName("com.mysql.cj.jdbc.Driver");
        //     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan",
        //             "eventour2020");
        //     Statement s = con.createStatement();
        //     String s1 = "insert into users(username_,password_) values('" + a + "','" + b + "');";
        //     System.out.println("Success");
        //     s.executeUpdate(s1);
        // } catch (Exception e) {
        //     System.out.println(e);
        // }
    }

}