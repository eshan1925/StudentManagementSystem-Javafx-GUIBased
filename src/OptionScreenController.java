import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OptionScreenController {

    @FXML
    private Button delete1;

    @FXML
    private Button exit;

    @FXML
    private Button newrecord;

    @FXML
    private Button viewrec;

    @FXML
    void deleterecord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteRecord.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void enterrecord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MarksDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void viewrecord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("recordScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
