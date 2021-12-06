package group.two.allesinordnung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddElementController {

    private String type;
    private String title;
    private String author;

    @FXML
    private Button addElementCancelID;

    @FXML
    private Button addElementOkID;

    @FXML
    private TextField authorID;

    @FXML
    private TextField titleID;

    @FXML
    private TextField typeID;

    @FXML
    void author() {
        author = authorID.getText();
    }

    @FXML
    void title() {
        title = titleID.getText();
    }

    @FXML
    void type() { type = typeID.getText(); }

    @FXML
    void addElementCancel(ActionEvent event) {
        Stage stage = (Stage) addElementCancelID.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addElementOk(ActionEvent event) {
        author();
        title();
        type();
        if (isNotNullEmpty(type) && isNotNullEmpty(title) && isNotNullEmpty(author)) {
            if (type.equalsIgnoreCase("cd") || type.equalsIgnoreCase("dvd") || type.equalsIgnoreCase("book")) {
                Element element = new Element(title, type, author, 0);
                ElementList.addElementToElementList(element);
                Stage stage = (Stage) addElementOkID.getScene().getWindow();
                stage.close();
            } else {
                typeID.clear();
                typeID.appendText("Invalid type! (CD/DVD/Book)");
            }
        }
    }

    public static boolean isNotNullEmpty(String str) {
        // check if string is null
        if (str == null) {
            return false;
        }
        // check if string is empty
        else return !str.isEmpty();
    }

}
