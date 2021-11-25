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


public class MarksScreenController {

    @FXML
    private Button delete;

    @FXML
    private Button exit;

    @FXML
    private TextField highest;

    @FXML
    private TextField m1;

    @FXML
    private TextField m2;

    @FXML
    private TextField m3;

    @FXML
    private TextField m4;

    @FXML
    private TextField m5;

    @FXML
    private Button newrecord;

    @FXML
    private TextField percentage;

    @FXML
    private Button record;

    @FXML
    private TextField registrationnumber;

    @FXML
    private TextField studentname;

    @FXML
    void recordentered(ActionEvent event) throws ClassNotFoundException, SQLException {
        String name = studentname.getText();
        String reg_no = registrationnumber.getText();
        int mark1 = Integer.valueOf(m1.getText());
        int mark2 = Integer.valueOf(m2.getText());
        int mark3 = Integer.valueOf(m3.getText());
        int mark4 = Integer.valueOf(m4.getText());
        int mark5 = Integer.valueOf(m5.getText());
        int[] marks = new int[5];
        marks[0] = mark1;marks[1] = mark2;marks[2] = mark3;marks[3] = mark4;marks[4] = mark5;
        int max_mark=0;
        for(int i=0;i<marks.length;i++){
            if(marks[i]>max_mark){
                max_mark=marks[i];
            }
        }
        int percentage_marks = (mark1+mark2+mark3+mark4+mark5)/5;
        // highest.setText(String.valueOf(max_mark));
        // percentage.setText(String.valueOf(percentage_marks));
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan","eventour2020");
        Statement s = con.createStatement();
        String s1 = "insert into student_details(name,registration_number,Sub1,Sub2,Sub3,Sub4,Sub5,HighestMarks,Percentage) values('" + name + "','" + reg_no + "','" + mark1 + "','" + mark2 +"','"+ mark3 +"','"+ mark4 +"','"+ mark5 +"','"+ max_mark +"','"+ percentage_marks +"');";
        highest.setText(String.valueOf(max_mark));
        percentage.setText(String.valueOf(percentage_marks+"%"));
        JOptionPane.showMessageDialog(null, "Entered all records!!!", "Record Entry Success!!!", 1);
        s.executeUpdate(s1);
    }

    @FXML
    void enternewrecord(ActionEvent event) {
        studentname.setText("");
        registrationnumber.setText("");
        m1.setText("");m2.setText("");m3.setText("");m4.setText("");m5.setText("");highest.setText("");percentage.setText("");
    }

    
    @FXML
    void quit(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Thankyou", "Student Management System", 1);
        System.exit(0);
    }

    @FXML
    void deleterecord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteRecord.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
