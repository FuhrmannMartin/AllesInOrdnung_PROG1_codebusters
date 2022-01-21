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

/*
controller for GUI when adding new element to active database
 */
public class AddElementController {

    // variables needed for initializing new element object
    private String type;
    private String title;
    private String author;
    private static int stars;

    // colors for star feature
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


    @FXML // getting entered author by user in GUI
    void author() {
        author = authorID.getText();
    }

    @FXML // getting entered title by user in GUI
    void title() {
        title = titleID.getText();
    }

    @FXML  // getting entered type by user in GUI
    void type() { type = typeID.getText(); }

    @FXML // in case adding new element is canceled
    void addElementCancel(ActionEvent event) {
        Stage stage = (Stage) addElementCancelID.getScene().getWindow();
        stage.close(); // closes stage for adding new element to current database
    }

    @FXML  // creating new element with user input after OK button pressed
    void addElementOk(ActionEvent event) {
        author(); // getting author
        title();  // getting title
        type();   // getting type
        // checking input data -> accepted types are cd/dvd/book
        if (isNotNullEmpty(type) && isNotNullEmpty(title) && isNotNullEmpty(author)) {
            if (type.equalsIgnoreCase("cd") || type.equalsIgnoreCase("dvd") || type.equalsIgnoreCase("book")) {

                if (type.equalsIgnoreCase("cd") || type.equalsIgnoreCase("dvd")) {
                    type = type.toUpperCase();
                }
                if (type.equalsIgnoreCase("book")) {
                    type = capitalizeFirst(type);
                }

                Element element = new Element(title, type, author, stars); // instantiating new element object
                ElementList.addElementToElementList(element); // adding new element to list
                stars = 0; // setting star variable to 0 again
                Stage stage = (Stage) addElementOkID.getScene().getWindow();
                stage.close(); // closing GUI for adding new element
            } else {
                typeID.clear();
                typeID.appendText("Invalid type! (CD/DVD/Book)");
            }
        } else {
            if (!isNotNullEmpty(type)) {
                typeID.appendText("Please provide a valid type! (CD/DVD/Book)");
            }
            if (!isNotNullEmpty(title)) {
                titleID.appendText("Please provide title!");
            }
            if (!isNotNullEmpty(author)) {
                authorID.appendText("Please provide author!");
            }
        }
    }

    @FXML // 1 star appears gold
    void star1AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(b);
        star3AddID.setFill(b);
        star4AddID.setFill(b);
        star5AddID.setFill(b);
        stars = 1;
    }

    @FXML // 2 stars appear gold
    void star2AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(b);
        star4AddID.setFill(b);
        star5AddID.setFill(b);
        stars = 2;
    }

    @FXML // 3 stars appear gold
    void star3AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(y);
        star4AddID.setFill(b);
        star5AddID.setFill(b);
        stars = 3;
    }

    @FXML // 4 stars appear gold
    void star4AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(y);
        star4AddID.setFill(y);
        star5AddID.setFill(b);
        stars = 4;
    }

    @FXML // 5 stars appear gold
    void star5AddClicked(MouseEvent event) {
        star1AddID.setFill(y);
        star2AddID.setFill(y);
        star3AddID.setFill(y);
        star4AddID.setFill(y);
        star5AddID.setFill(y);
        stars = 5;
    }

    // function used to check user input in GUI
    private static boolean isNotNullEmpty(String str) {
        // check if string is null
        if (str == null) {
            return false;
        }
        // check if string is empty
        else return !str.isEmpty();
    }

    // function to capitalize the first character of each word in a string
    private static String capitalizeFirst(String str) {
        String firstLetter = str.substring(0, 1);
        String remainingLetters = str.substring(1);
        firstLetter = firstLetter.toUpperCase();
        return firstLetter + remainingLetters.toLowerCase();
    }

}
