package br.com.datarey.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import br.com.datarey.util.MessageType;
import br.com.datarey.util.MessageUtil;

public class MessageUtilImpl implements MessageUtil {

    @Override
    public void showMessage(String message, MessageType type) {
        Alert alert = null;
        if (type.equals(MessageType.ERROR)) {
            alert = new Alert(AlertType.ERROR);
        } else {
            return;
        }
        alert.setHeaderText(message);
        alert.show();
    }

}
