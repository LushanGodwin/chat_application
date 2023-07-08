package lk.ijse.chat_box.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.chat_box.dto.Server;

import java.io.IOException;

public class LoginPageFormController {
    @FXML
    private JFXButton btnLogin;

    @FXML
    private AnchorPane root;


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        try {
            Server server = Server.getServerSocket();
            Thread thread = new Thread(server);
            thread.start();

            root.getChildren().clear();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/clientJoinForm.fxml"))));
            stage.setTitle("Chit Chat");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(e->{
                System.exit(0);
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
