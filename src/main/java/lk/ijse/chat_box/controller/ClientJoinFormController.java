package lk.ijse.chat_box.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.chat_box.bo.custom.UserBO;
import lk.ijse.chat_box.bo.custom.impl.UserBOImpl;
import lk.ijse.chat_box.client.Client;
import lk.ijse.chat_box.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientJoinFormController {

    List<String> userList = new ArrayList<>();

    UserBO userBO = new UserBOImpl();


    @FXML
    private Button btnJoin;
    @FXML
    private Button btnRegister;

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private JFXTextField txtRegisterUserName;

    @FXML
    private JFXTextField txtRegisterUserPassword;

    @FXML
    void btnJoinOnAction(ActionEvent event) throws IOException {


        try {
            if (userBO.isValidUser(new UserDTO(txtUserName.getText(), txtPassword.getText()))) {

                if (!checkDuplicate(txtUserName.getText())) {
                    userList.add(txtUserName.getText());
                    txtUserName.setText("");
                    txtPassword.setText("");
                    Client client = new Client(txtUserName.getText());
                    Thread thread = new Thread(client);
                    thread.start();
                } else {
                    new Alert(Alert.AlertType.ERROR, "User is already join this chat !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid User name or password !").show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkDuplicate(String user_name) {


        for (String name : userList) {
            if (name.equals(user_name)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (!(txtRegisterUserName.getText().isEmpty() || txtRegisterUserPassword.getText().isEmpty())) {
            try {
                if (userBO.isExistUser(txtRegisterUserName.getText())) {
                    new Alert(Alert.AlertType.ERROR, "User name is Already Added !").show();
                } else {
                    boolean isAdded = userBO.addUser(new UserDTO(txtRegisterUserName.getText(), txtRegisterUserPassword.getText()));
                    if (isAdded) {
                        txtRegisterUserName.setText("");
                        txtRegisterUserPassword.setText("");
                        new Alert(Alert.AlertType.INFORMATION, "User is Added !").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "User is not Added !").show();
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fill user name and password !").show();
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

        btnJoin.fire();
    }

    @FXML
    void txtRegisterUserNameOnAction(ActionEvent event) {
        txtRegisterUserPassword.requestFocus();
    }

    @FXML
    void txtRegisterUserPasswordOnAction(ActionEvent event) {
        btnRegister.fire();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

}
