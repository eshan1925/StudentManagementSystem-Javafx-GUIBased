import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class DeleteScreenController {

    @FXML
    private Button delete;

    @FXML
    private Button exit;

    @FXML
    private Button newrecord;

    @FXML
    private Button newrecord1;

    @FXML
    private TextField registrationnumber;

    @FXML
    private TextField studentname;

    @FXML
    void deleterecord(ActionEvent event) throws ClassNotFoundException, SQLException {
        String name = studentname.getText();
        String regno = registrationnumber.getText();
        System.out.println(name+" "+registrationnumber);
        if (name != "" || regno != "") {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan",
                    "eventour2020");
            Statement s = con.createStatement();
            regno='"'+regno+'"';
            String s1 = "delete from student_details where registration_number=" + regno + ';';
            studentname.setText("");
            registrationnumber.setText("");
            JOptionPane.showMessageDialog(null, "Deleted Record", "Success!!!", 1);
            s.executeUpdate(s1);
        }else{
            JOptionPane.showMessageDialog(null, "Field Empty", "Error!!!", 0);
        }
    }

    @FXML
    void enternewrecord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MarksDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void quit(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Thankyou", "Student Management System", 1);
        System.exit(0);
    }

}
