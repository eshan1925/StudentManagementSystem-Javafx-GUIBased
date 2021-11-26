import java.sql.*;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class viewRecordScreenController {

    @FXML
    private Button exit;

    @FXML
    private TextField registrationnumber;

    @FXML
    private TextField studentname;

    @FXML
    private TableColumn<?, ?> highest;

    @FXML
    private TableColumn<?, ?> percentage;

    @FXML
    private TableColumn<?, ?> sub1;

    @FXML
    private TableColumn<?, ?> sub2;

    @FXML
    private TableColumn<?, ?> sub3;

    @FXML
    private TableColumn<?, ?> sub4;

    @FXML
    private TableColumn<?, ?> sub5;

    @FXML
    private TableView<?> table;
    @FXML
    private Button viewrec;

    @FXML
    void quit(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Thankyou", "Student Management System", 1);
        System.exit(0);
    }

    @FXML
    void viewrecord(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "eshan", "eventour2020");
        Statement s = con.createStatement();
        String a = '"' + studentname.getText() + '"';
        String s1 = "Select sub1,sub2,sub3,sub4,sub5,HighestMarks,Percentage from student_details where name=" + a;
        ResultSet rsSet = s.executeQuery(s1);
        ObservableList dbData = FXCollections.observableArrayList(rsSet);
        for (int i = 0; i < rsSet.getMetaData().getColumnCount(); i++) {
            TableColumn column = new TableColumn<>();
            switch (rsSet.getMetaData().getColumnName(i+1)) {
                case "id":
                    column.setText("ID #");
                    break;
                case "name":
                    column.setText("Person Name");
                    break;
                case "married":
                    column.setText("Marital Status");
                    break;
                default: column.setText(rsSet.getMetaData().getColumnName(i+1)); //if column name in SQL Database is not found, then TableView column receive SQL Database current column name (not readable)
                    break;
            }
            column.setCellValueFactory(new PropertyValueFactory<>(rsSet.getMetaData().getColumnName(i+1))); //Setting cell property value to correct variable from Person class.
            table.getColumns().add(column);
        }
        table.setItems(dbData);
    }

}
