package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * This class can create MessageBox, can be useful to display errors or information to user
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public final class MessageBoxUtil {

    /**
     *  Displays a confirm contextual menu
     *
     * @param title is the title of the contextual menu
     * @param content is the content of the contextual menu
     * @return ButtonType
     */
    public static ButtonType showConfirm(String title, String content) {
        return createAlert(title, content, Alert.AlertType.CONFIRMATION)
                .showAndWait()
                .get();
    }

    /**
     *  Displays an info contextual menu
     *
     * @param title is the title of the contextual menu
     * @param content is the content of the contextual menu
     * @return ButtonType
     */
    public static ButtonType showInfo(String title, String content) {
        return createAlert(title, content, Alert.AlertType.INFORMATION)
                .showAndWait()
                .get();
    }

    /**
     *  Displays a warning contextual menu
     *
     * @param title is the title of the contextual menu
     * @param content is the content of the contextual menu
     * @return ButtonType
     */
    public static ButtonType showWarning(String title, String content) {
        return createAlert(title, content, Alert.AlertType.WARNING)
                .showAndWait()
                .get();
    }

    /**
     *  Displays an error contextual menu
     *
     * @param title is the title of the contextual menu
     * @param content is the content of the contextual menu
     * @return ButtonType
     */
    public static ButtonType showError(String title, String content) {
        return createAlert(title, content, Alert.AlertType.ERROR)
                .showAndWait()
                .get();
    }

    /**
     *  Creates a dialog
     *
     * @param title is the title of the dialog menu
     * @param content is the content of the contextual menu
     * @param type is the type of the dialog
     * @return Alert
     *
     * @see Alert.AlertType
     */
    private static Alert createAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);

        alert.setTitle("Alert");
        alert.setHeaderText(title);
        alert.setContentText(content);

        return alert;
    }
}
