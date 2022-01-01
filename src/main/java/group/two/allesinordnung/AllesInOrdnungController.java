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


public class AllesInOrdnungController {

    private String filter = "all";
    String regexp = "";
    Paint y = new Color(1,1,0,1.0);
    Paint b = new Color(0,0,0,1.0);
    private static int stars;

    public Element selectedElement;

    @FXML
    private Button EditOkID;

    @FXML
    private TextField searchID;

    @FXML
    private MenuItem addElementID;

    @FXML
    private MenuItem deleteElementID;

    @FXML
    private MenuItem changeDatabaseID;

    @FXML
    private MenuItem exportDatabaseID;

    @FXML
    private ListView<String> listViewID;

    @FXML
    private MenuItem showAllID;

    @FXML
    private MenuItem showBooksID;

    @FXML
    private MenuItem showCDsID;

    @FXML
    private MenuItem showDVDsID;

    @FXML
    private TextField authorID;

    @FXML
    private TextField titleID;

    @FXML
    private TextField typeID;

    @FXML
    private SVGPath star1ID;

    @FXML
    private SVGPath star2ID;

    @FXML
    private SVGPath star3ID;

    @FXML
    private SVGPath star4ID;

    @FXML
    private SVGPath star5ID;

    @FXML
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

    @FXML
    void deleteElement(ActionEvent event) {
        ElementList.deleteElementFromElementList(selectedElement.hash);
        deleteVisibleElementInfo();
        updateListView();
    }

    @FXML
    void changeDatabase(ActionEvent event) {
        Window stage = exportDatabaseID.getParentPopup().getOwnerWindow();
        ElementList.importElementList(stage);
        updateListView();
    }

    @FXML
    void exportDatabase(ActionEvent event) {
        Window stage = exportDatabaseID.getParentPopup().getOwnerWindow();
        ElementList.exportElementList(stage);
    }

    @FXML
    void showAll(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "all";
        updateListView();
    }

    @FXML
    void showBooks(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "book";
        updateListView();
    }

    @FXML
    void showCDs(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "CD";
        updateListView();
    }

    @FXML
    void showDVDs(ActionEvent event) {
        deleteVisibleElementInfo();
        filter = "DVD";
        updateListView();
    }

    @FXML
    void listViewMouseClicked(MouseEvent event) {
        ObservableList<Integer> selection;
        selection = listViewID.getSelectionModel().getSelectedIndices();
        try {
            selectedElement = ElementList.elementsFiltered.get(selection.get(0));
            updateVisibleElementInfo(selectedElement);
        } catch (Exception exception) {
            System.out.println("Invalid selection!");
        }
    }

    @FXML
    public void search(ActionEvent event) {
        deleteVisibleElementInfo();
        regexp = searchID.getText();
        updateListView();
    }

    @FXML
    public void author() {
        String string = authorID.getText();
        ElementList.editElementInElementList(selectedElement.hash, "author", string);
    }

    @FXML
    public void title() {
        String string = titleID.getText();
        ElementList.editElementInElementList(selectedElement.hash, "title", string);
    }

    @FXML
    public void type() {
        String string = typeID.getText();
        if (string.equals("CD") || string.equals("DVD") || string.equals("book")) {
            ElementList.editElementInElementList(selectedElement.hash, "type", string);
        }
    }

    public void stars() {
        ElementList.editElementInElementList(selectedElement.hash, "stars", Integer.toString(stars));
    }

    @FXML
    void EditOk(ActionEvent event) {
        if (selectedElement != null) {
            author();
            title();
            type();
            stars();
            ElementList.updateHash(selectedElement.hash);
            System.out.println(selectedElement);
            updateListView();
        }
    }

    @FXML
    void star1Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(b);
        star3ID.setFill(b);
        star4ID.setFill(b);
        star5ID.setFill(b);
        stars = 1;
    }

    @FXML
    void star2Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(b);
        star4ID.setFill(b);
        star5ID.setFill(b);
        stars = 2;
    }

    @FXML
    void star3Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(y);
        star4ID.setFill(b);
        star5ID.setFill(b);
        stars = 3;
    }

    @FXML
    void star4Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(y);
        star4ID.setFill(y);
        star5ID.setFill(b);
        stars = 4;
    }

    @FXML
    void star5Clicked(MouseEvent event) {
        star1ID.setFill(y);
        star2ID.setFill(y);
        star3ID.setFill(y);
        star4ID.setFill(y);
        star5ID.setFill(y);
        stars = 5;
    }

    void noStars() {
        star1ID.setFill(b);
        star2ID.setFill(b);
        star3ID.setFill(b);
        star4ID.setFill(b);
        star5ID.setFill(b);
    }

    public void updateListView() {
        listViewID.getItems().clear();
        String[] titles = ElementList.getElementList(filter, regexp);
        listViewID.getItems().addAll(titles);
    }

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

    public void deleteVisibleElementInfo() {
        typeID.clear();
        authorID.clear();
        titleID.clear();
        noStars();
    }

}
