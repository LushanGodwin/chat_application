package lk.ijse.chat_box.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.chat_box.dto.Client;

import java.io.IOException;

public class ClientJoinFormController {
    @FXML
    private Button btnJoin;

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnJoinOnAction(ActionEvent event) throws IOException {
        Client client = new Client(txtUserName.getText());
        Thread thread = new Thread(client);
        thread.start();
    }
}
