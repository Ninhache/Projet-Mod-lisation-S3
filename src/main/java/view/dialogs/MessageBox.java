package view.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * This class can create MessageBox, can be usefull for error and else
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public final class MessageBox {

    public static ButtonType showConfirm(String title, String content) {
        return createAlert(title, content, Alert.AlertType.CONFIRMATION)
                .showAndWait()
                .get();
    }

    public static ButtonType showInfo(String title, String content) {
        return createAlert(title, content, Alert.AlertType.INFORMATION)
                .showAndWait()
                .get();
    }

    public static ButtonType showWarning(String title, String content) {
        return createAlert(title, content, Alert.AlertType.WARNING)
                .showAndWait()
                .get();
    }

    public static ButtonType showError(String title, String content) {
        return createAlert(title, content, Alert.AlertType.ERROR)
                .showAndWait()
                .get();
    }

    private static Alert createAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);

        alert.setTitle("");
        alert.setHeaderText(title);
        alert.setContentText(content);

        return alert;
    }
}
