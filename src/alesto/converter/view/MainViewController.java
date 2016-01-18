package alesto.converter.view;

import alesto.converter.MainApp;
import alesto.converter.util.Base64Converter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * This controller controls all of the features of this application.
 */
public class MainViewController {
    @FXML
    private Text fileURI;

    @FXML
    private TextField rawCode;

    @FXML
    private TextField codeForHTML;

    //reference to the main app.
    private MainApp mainApp;

    //This variable should contain result of converting.
    private String code;

    //This sign is written instead of potentially convertable file URI by default.
    private static final String URI_PLACEHOLDER = "Choose image to convert...";

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        fileURI.setText(URI_PLACEHOLDER);
    }

    /**
     * Chooses file using operational system's explorer.
     */
    @FXML
    public void browseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your image to convert");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.jpg","*.jpeg","*.png","*.bmp","*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileURI.setText(selectedFile.getAbsolutePath());
        }
        else {
            fileURI.setText(URI_PLACEHOLDER);
        }

    }

    /**
     * Converts given URI to Base64 code and writes it down text fields.
     */
    @FXML
    public void convert() {
        String uri = fileURI.getText();
        if(uri == null || uri.isEmpty() || uri.equals(URI_PLACEHOLDER)) {
            nothingToConvertAlert();
        } else {
            code = Base64Converter.encodeImage(fileURI.getText());
            rawCode.setText(code);
            codeForHTML.setText("data:image/jpg;base64," + code);
        }
    }

    /**
     * Copies code from text fields to clipboard for further use. Sorry for that mess, have no idea how to handle it yet.
     */
    @FXML
    public void copyToClipboardRaw() {
        copyToClipboard(rawCode);
    }

    @FXML
    public void copyToClipboardHTML() {
        copyToClipboard(codeForHTML);
    }

    public void copyToClipboard(TextField source) {
        if(code == null || code.isEmpty()) {
            nothingToCopyAlert();
        } else {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            clipboard.setContent(content);
        }
    }
    /**********************************/
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Alert block.
     */


    private void nothingToCopyAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Error");
        alert.setHeaderText("Nothing to Copy!");
        alert.setContentText("You should convert file before something appear in the text field.");

        alert.showAndWait();
    }

    private void nothingToConvertAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Error");
        alert.setHeaderText("Nothing to Convert!");
        alert.setContentText("Please, click on the \"Browse\" button after closing this window. Then choose your file to convert.");

        alert.showAndWait();
    }

}
