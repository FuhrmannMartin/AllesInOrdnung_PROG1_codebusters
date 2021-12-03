package group.two.allesinordnung;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class AllesInOrdnungController {

    private String filter = "all";

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
    private MenuItem editElementID;

    @FXML
    private MenuItem searchElementID;

    @FXML
    private MenuItem showAllID;

    @FXML
    private MenuItem showBooksID;

    @FXML
    private MenuItem showCDsID;

    @FXML
    private MenuItem showDVDsID;

    @FXML
    private ImageView showImageID;

    @FXML
    private TextFlow showTextID;

    @FXML
    void addElement(ActionEvent event) {
        Element element1 = new Element("Rattatatam, mein Herz", "book", "Franziska Seyboldt");
        ElementList.addElement(element1);
        Element element2 = new Element("Kid A", "CD", "Radiohead");
        ElementList.addElement(element2);
        updateListView();
    }

    @FXML
    void deleteElement(ActionEvent event) {
        ObservableList<Integer> selection;
        selection = listViewID.getSelectionModel().getSelectedIndices();
        Element selectedElement = ElementList.elementsFiltered.get(selection.get(0));

        ElementList.deleteElement(selectedElement.id);
        updateListView();
        showTextID.getChildren().clear();
        Element.count--;
    }

    @FXML
    void editElement(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AllesInOrdnung.class.getResource("EditElement.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350.0, 180.0);
        Stage stage = new Stage();
        stage.setTitle("Alles in Ordnung - Edit!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchElement(ActionEvent event) {

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
        showTextID.getChildren().clear();
        filter = "all";
        updateListView();
    }

    @FXML
    void showBooks(ActionEvent event) {
        showTextID.getChildren().clear();
        filter = "book";
        updateListView();
    }

    @FXML
    void showCDs(ActionEvent event) {
        showTextID.getChildren().clear();
        filter = "CD";
        updateListView();
    }

    @FXML
    void showDVDs(ActionEvent event) {
        showTextID.getChildren().clear();
        filter = "DVD";
        updateListView();
    }

    @FXML
    void listViewMouseClicked(MouseEvent event) {
        ObservableList<Integer> selection;
        selection = listViewID.getSelectionModel().getSelectedIndices();
        Element selectedElement = ElementList.elementsFiltered.get(selection.get(0));

        Text t1 = ElementList.getElementText(selectedElement.id);

        showTextID.getChildren().clear();
        showTextID.getChildren().add(t1);
    }

    public void updateListView() {
        listViewID.getItems().clear();
        String[] titles = ElementList.getElementList(filter);
        listViewID.getItems().addAll(titles);
    }

}
