import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentLoginSceneController {

    @FXML
    private Button logIn;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup;

    @FXML
    private TextField userId;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    void loginUser(ActionEvent event) {
        String a = userId.getText();
        String b = password.getText();
        if (a.equals("") && b.equals("")) {
            JOptionPane.showMessageDialog(null, "Username or Password Blank", "Ooooops!!!", 0);
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan", "eventour2020");
                pst = con.prepareStatement("select * from students where username=? and password=?");
                pst.setString(1, a);
                pst.setString(2, b);
                rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Success", "Login Success", 1);
                    Parent root = FXMLLoader.load(getClass().getResource("StudentOptionScreen.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Check the credentials", "Login Failure", 0);
                    userId.setText("");
                    password.setText("");
                    userId.requestFocus();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    @FXML
    void signupUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentSignUpScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}