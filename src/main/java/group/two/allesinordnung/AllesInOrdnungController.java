package group.two.allesinordnung;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/*
controller for main GUI to manage active database
 */
public class AllesInOrdnungController {

    private String filter = "all"; // current filter settings (CD/DVD/books)
    String regexp = ""; // current search filter settings
    // colors for star feature
    Paint y = new Color(1,1,0,1.0);
    Paint b = new Color(0,0,0,1.0);
    private static int stars; // star voting for currently selected element

    public Element selectedElement; // variable reflecting currently selected element by user

    @FXML
    private Button EditOkID;

    @FXML
    private TextField searchID; // GUI search window

    @FXML
    private MenuItem addElementID;

    @FXML
    private MenuItem deleteElementID;

    @FXML
    private MenuItem changeDatabaseID;

    @FXML
    private MenuItem exportDatabaseID;  // GUI export database option

    @FXML
    private ListView<String> listViewID; // GUI list showing all currently filtered elements

    @FXML
    private MenuItem showAllID;

    @FXML
    private MenuItem showBooksID;

    @FXML
    private MenuItem showCDsID;

    @FXML
    private MenuItem showDVDsID;

    @FXML
    private TextField authorID; // GUI author field for currently selected element

    @FXML
    private TextField titleID; // GUI title field for currently selected element

    @FXML
    private TextField typeID; // GUI type field for currently selected element

    @FXML
    private SVGPath star1ID;  // GUI first star for currently selected element

    @FXML
    private SVGPath star2ID;  // GUI second star for currently selected element

    @FXML
    private SVGPath star3ID;  // GUI third star for currently selected element

    @FXML
    private SVGPath star4ID;  // GUI fourth star for currently selected element

    @FXML
    private SVGPath star5ID;  // GUI fifth star for currently selected element

    @FXML // second GUI for adding new element to current database
    void addElement(ActionEvent event) {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddElement.fxml"));
            Parent root = loader.load();

            // Get controller
            AddElementController addElementController = loader.getController();

            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Neues Element hinzufuegen!");
            stage.showAndWait();
            updateListView();
        } catch (IOException ex) {
            System.err.println("Error when loading AddElementController!");
        }
    }

    @FXML // delete currently selected element from current database
    void deleteElement(ActionEvent event) {
        ElementList.deleteElementFromElementList(selectedElement.hash);
        deleteVisibleElementInfo(); // remove information of deleted element from info section (lower right corner)
        updateListView(); // update GUI list showing all currently filtered elements
    }

    @FXML // change to other database
    void changeDatabase(ActionEvent event) {
        Window stage = exportDatabaseID.getParentPopup().getOwnerWindow();
        ElementList.importElementList(stage); // import from json file
        filter = "all"; // set filter to show all types of elements
        updateListView(); // update GUI list showing all elements
    }

    @FXML
    void exportDatabase(ActionEvent event) {
        Window stage = exportDatabaseID.getParentPopup().getOwnerWindow();
        ElementList.exportElementList(stage); // export to json file
    }

    @FXML // GUI button to set filter to show all types of elements
    void showAll(ActionEvent event) {
        deleteVisibleElementInfo(); // remove element information from info section
        filter = "all";
        updateListView(); // update GUI list showing all currently filtered elements
    }

    @FXML // GUI button to set filter to show only elements of type book
    void showBooks(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "book";
        updateListView();
    }

    @FXML // GUI button to set filter to show only elements of type CD
    void showCDs(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "CD";
        updateListView();
    }

    @FXML // GUI button to set filter to show only elements of type DVD
    void showDVDs(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "DVD";
        updateListView();
    }

    @FXML // function handling user request to select specific element from list
    void listViewMouseClicked(MouseEvent event) {
        ObservableList<Integer> selection;
        selection = listViewID.getSelectionModel().getSelectedIndices();
        try {
            selectedElement = ElementList.elementsFiltered.get(selection.get(0));
            updateVisibleElementInfo(selectedElement); // add element information from selected element to info section
        } catch (Exception exception) {
            System.out.println("Invalid selection!");
        }
    }

    @FXML // function handling user search request
    public void search(ActionEvent event) {
        deleteVisibleElementInfo(); // remove element information from info section
        regexp = searchID.getText(); // get user search request
        updateListView(); // update visible element list
    }

    @FXML // function handling author edit
    public void author() {
        String string = authorID.getText();
        ElementList.editElementInElementList(selectedElement.hash, "author", string); // element searched by hash code in element list
    }

    @FXML // function handling title edit
    public void title() {
        String string = titleID.getText();
        ElementList.editElementInElementList(selectedElement.hash, "title", string);
    }

    @FXML  // function handling type edit
    public void type() {
        String string = typeID.getText();
        if (string.equals("CD") || string.equals("DVD") || string.equals("book")) {
            ElementList.editElementInElementList(selectedElement.hash, "type", string);
        }
    }

    // function handling stars edit
    public void stars() {
        ElementList.editElementInElementList(selectedElement.hash, "stars", Integer.toString(stars));
    }

    @FXML // function handling user element edit request
    void EditOk(ActionEvent event) {
        if (selectedElement != null) {
            // update properties according to user input
            author();
            title();
            type();
            stars();
            ElementList.updateHash(selectedElement.hash); // update has code after edit
            System.out.println(selectedElement);
            updateListView(); // update list of visible elements
        }
    }

    @FXML // 1 star appears gold
    void star1Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(b);
        star3ID.setFill(b);
        star4ID.setFill(b);
        star5ID.setFill(b);
        stars = 1;
    }

    @FXML // 2 stars appear gold
    void star2Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(b);
        star4ID.setFill(b);
        star5ID.setFill(b);
        stars = 2;
    }

    @FXML // 3 stars appear gold
    void star3Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(y);
        star4ID.setFill(b);
        star5ID.setFill(b);
        stars = 3;
    }

    @FXML // 4 stars appear gold
    void star4Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(y);
        star4ID.setFill(y);
        star5ID.setFill(b);
        stars = 4;
    }

    @FXML // 5 stars appear gold
    void star5Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(y);
        star4ID.setFill(y);
        star5ID.setFill(y);
        stars = 5;
    }

    // no star appears gold
    void noStars() {
        star1ID.setFill(b);
        star2ID.setFill(b);
        star3ID.setFill(b);
        star4ID.setFill(b);
        star5ID.setFill(b);
    }

    // function handling list update
    public void updateListView() {
        listViewID.getItems().clear(); // clearing list
        String[] titles = ElementList.getElementList(filter, regexp); // get title of all currently filtered elements
        listViewID.getItems().addAll(titles); // show elements
    }

    // function handling the visualization of currently selected element
    public void updateVisibleElementInfo(Element selectedElement) {
        typeID.clear();
        typeID.appendText(selectedElement.type);
        authorID.clear();
        authorID.appendText(selectedElement.author);
        titleID.clear();
        titleID.appendText(selectedElement.title);
        System.out.println(selectedElement);
        switch (selectedElement.stars) {
            case 0 -> noStars();
            case 1 -> star1Clicked(null);
            case 2 -> star2Clicked(null);
            case 3 -> star3Clicked(null);
            case 4 -> star4Clicked(null);
            case 5 -> star5Clicked(null);
        }
    }

    // function removing the visualization of any element information
    public void deleteVisibleElementInfo() {
        typeID.clear();
        authorID.clear();
        titleID.clear();
        noStars();
    }

}
