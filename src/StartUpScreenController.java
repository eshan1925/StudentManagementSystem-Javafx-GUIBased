import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.stage.Stage;
public class StartUpScreenController {

    @FXML
    private Button admin;

    @FXML
    private Button student;

    @FXML
    void adminPageacess(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void studentpageAcess(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MarksDetails.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
