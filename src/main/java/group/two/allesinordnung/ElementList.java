package group.two.allesinordnung;

import com.google.gson.Gson;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ElementList {

    public static List<Element> elementList = new ArrayList<>();
    public static List<Element> elementsFiltered = new ArrayList<>();

    public static void newElementList() {
        elementList.clear();
    }

    public static void addElement(Element element) {
        elementList.add(element);
    }

    public static void deleteElement(int id) {
        int n = elementList.size();
        int i = 0;

        while (i < n) {
            if (elementList.get(i).id == id) {
                elementList.remove(i);
                break;
            }
            i++;
        }

    }

    public static void exportElementList(Window stage) {
        StringBuilder jsonAllElements = new StringBuilder();

        //https://www.youtube.com/watch?v=vL8RahFv8NY
        //https://attacomsian.com/blog/gson-read-write-json
        Gson gson = new Gson();

        for (Element element : elementList) {
            String json = gson.toJson(element) + System.lineSeparator();
            jsonAllElements.append(json);
        }

        //https://www.youtube.com/watch?v=7lnVelyHxrc
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Dialog");
        //fc.setInitialFileName("jsonDatabase");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("json file", "*.json"));
        try {
            File file = fc.showSaveDialog(stage);
            WriteStringToFile.main(file, jsonAllElements.toString());
        } catch (Exception ex) {
            System.out.println("Error while exporting!");
        }
    }

    public static void importElementList(Window stage) {

        //https://www.youtube.com/watch?v=7lnVelyHxrc
        //https://attacomsian.com/blog/gson-read-write-json
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Dialog");
        //fc.setInitialFileName("jsonDatabase");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("json file", "*.json"));
        try {
            ElementList.newElementList();
            Element.setCount(0);
            File file = fc.showOpenDialog(stage);
            Gson gson = new Gson();
            String json = Files.readString(file.toPath());
            Pattern pattern = Pattern.compile("\\{.*}");
            Matcher matcher = pattern.matcher(json);
            while (matcher.find()) {
                //Element element = new Element();
                Element element = gson.fromJson(matcher.group(), Element.class);
                ElementList.addElement(element);
            }
        } catch (Exception ex) {
            System.out.println("Error while importing!");
        }
    }

    public static String[] getElementList(String filter) {
        elementsFiltered.clear();

        for (Element element : elementList) {
            if (filter.equals("all")) {
                elementsFiltered.add(element);
            } else {
                if (element.type.equals(filter)) {
                    elementsFiltered.add(element);
                }
            }
        }

        elementsFiltered.sort(Comparator.comparing(Element::toString));
        int l = elementsFiltered.size();
        String[] titles = new String[l];

        for (int i = 0; i < l; i++) {
            titles[i] = i+1 + ": " + elementsFiltered.get(i).title;
        }

        return titles;
    }

    public static Text getElementText(int id) {
        Element selectedElement = getElementByID(id);
        String displayText = selectedElement.type + System.lineSeparator() + "author: " + selectedElement.author + System.lineSeparator() + "title: " + selectedElement.title;

        return new Text(displayText);
    }

    private static Element getElementByID(int id) {
        int n = elementList.size();
        int i = 0;
        Element selectedElement = null;

        while (i < n) {
            if (elementList.get(i).id == id) {
                selectedElement = elementList.get(i);
                break;
            }
            i++;
        }

        return selectedElement;
    }

}
