import java.sql.*;
import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class viewRecordScreenController {

    @FXML
    private Button exit;

    @FXML
    private TextField registrationnumber;

    @FXML
    private TextField studentname;

    // @FXML
    // private TableColumn<?, ?> highest;

    // @FXML
    // private TableColumn<?, ?> percentage;

    // @FXML
    // private TableColumn<?, ?> sub1;

    // @FXML
    // private TableColumn<?, ?> sub2;

    // @FXML
    // private TableColumn<?, ?> sub3;

    // @FXML
    // private TableColumn<?, ?> sub4;

    // @FXML
    // private TableColumn<?, ?> sub5;

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
        String a = '"' + studentname.getText() + '"';
        String s1 = "Select sub1,sub2,sub3,sub4,sub5,HighestMarks,Percentage from student_details where name=" + a;
        ResultSet rsSet = con.createStatement().executeQuery(s1);
        ObservableList data = FXCollections.observableArrayList();
        for(int i=0 ; i<rsSet.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;                
            TableColumn col = new TableColumn(rsSet.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                    return new SimpleStringProperty(param.getValue().get(j).toString());                        
                }                    
            });
            table.getColumns().addAll(col); 
            System.out.println("Column ["+i+"] ");
        }

        while(rsSet.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=rsSet.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(rsSet.getString(i));
            }
            System.out.println("Row [1] added "+row );
            data.add(row);
        }
        table.setItems(data);
    }

}
