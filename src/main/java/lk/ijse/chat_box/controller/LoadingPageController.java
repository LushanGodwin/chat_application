package lk.ijse.chat_box.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingPageController implements Initializable {
    @FXML
    private ProgressBar progressbar;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Void> task=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    updateProgress(i,50);
                    Thread.sleep(75);

                }
                return null;
            }
        };
        progressbar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event ->{
            try {
                Parent loginPage= FXMLLoader.load(getClass().getResource("/view/loginPageForm.fxml"));
                Scene scene=new Scene(loginPage);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

                ((Stage) progressbar.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        new Thread(task).start();
    }
}
