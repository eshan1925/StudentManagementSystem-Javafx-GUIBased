import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SignUpSceneController {

    @FXML
    private Button logIn;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button quit;

    @FXML
    private TextField userId;

    @FXML
    private Button register;

    @FXML
    void loginUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void register(ActionEvent event) {
        String a = userId.getText();
        String b = password.getText();
        String c = name.getText();
        if (a.equals("") || b.equals("") || c.equals("")) {
            JOptionPane.showMessageDialog(null, "One or more fields Blank", "Ooooops!!!", 0);
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan",
                        "eventour2020");
                Statement s = con.createStatement();
                String s1 = "insert into users(username_,password_,name) values('" + a + "','" + b + "','" + c + "');";
                JOptionPane.showMessageDialog(null, "Registered new user Successfully", "Login Success", 1);
                System.out.println("Success");
                s.executeUpdate(s1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
