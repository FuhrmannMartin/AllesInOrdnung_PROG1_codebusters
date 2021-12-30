package group.two.allesinordnung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class AddElementController {

    private String type;
    private String title;
    private String author;

    Paint y = new Color(1,1,0,1.0);
    Paint b = new Color(0,0,0,1.0);

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
    private SVGPath star1AddID;

    @FXML
    private SVGPath star2AddID;

    @FXML
    private SVGPath star3AddID;

    @FXML
    private SVGPath star4AddID;

    @FXML
    private SVGPath star5AddID;


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
                Element element = new Element(title, type, author);
                ElementList.addElementToElementList(element);
                Stage stage = (Stage) addElementOkID.getScene().getWindow();
                stage.close();
            } else {
                typeID.clear();
                typeID.appendText("Invalid type! (CD/DVD/Book)");
            }
        }
    }

    @FXML
    void star1AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(b);
        star3AddID.setFill(b);
        star4AddID.setFill(b);
        star5AddID.setFill(b);
    }

    @FXML
    void star2AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(b);
        star4AddID.setFill(b);
        star5AddID.setFill(b);
    }

    @FXML
    void star3AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(y);
        star4AddID.setFill(b);
        star5AddID.setFill(b);
    }

    @FXML
    void star4AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(y);
        star4AddID.setFill(y);
        star5AddID.setFill(b);
    }

    @FXML
    void star5AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(y);
        star4AddID.setFill(y);
        star5AddID.setFill(y);
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
