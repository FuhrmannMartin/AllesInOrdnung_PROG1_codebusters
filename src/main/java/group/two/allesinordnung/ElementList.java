package group.two.allesinordnung;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
in this list all elements of currently active database are stored
 */
public class ElementList {

    public static List<Element> elementList = new ArrayList<>();
    public static List<Element> elementsFiltered = new ArrayList<>();

    // clearing element list e.g. when importing new database from json
    public static void clearElementList() {
        elementList.clear();
    }

    // adding new element defined by user to currently active database
    public static void addElementToElementList(Element element) {
        boolean elementAlreadyExisting = false;
        int n = elementList.size();
        int i = 0;

        // checking whether element already exists based on hash code
        while (i < n) {
            if (elementList.get(i).hash == element.hash) {
                elementAlreadyExisting = true;
                break;
            }
            i++;
        }

        if (!elementAlreadyExisting) {
            elementList.add(element);
        } else {
            System.out.println("Element already exists!");
        }
    }

    // removing element from currently active database based on hash code
    public static void deleteElementFromElementList(int hash) {
        int n = elementList.size();
        int i = 0;

        // looping through list until correct element is found
        while (i < n) {
            if (elementList.get(i).hash == hash) {
                elementList.remove(i);
                break;
            }
            i++;
        }
    }

    // editing element from currently active database based on hash code
    // elementProperty defines which object property is to be edited
    // string is the new value of this property
    public static void editElementInElementList(int hash, String elementProperty, String string) {
        int n = elementList.size();
        int i = 0;

        while (i < n) {
            if (elementList.get(i).hash == hash) {
                switch (elementProperty) {
                    case "type" -> elementList.get(i).type = string;
                    case "title" -> elementList.get(i).title = string;
                    case "author" -> elementList.get(i).author = string;
                    case "stars" -> elementList.get(i).stars = Integer.parseInt(string);
                }
                break;
            }
            i++;
        }
    }

    // exporting currently active database to json file using gson library
    public static void exportElementList(Window stage) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Dialog");
        fc.setInitialFileName("database");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("json file", "*.json"));
        try {
            File file = fc.showSaveDialog(stage);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(elementList); // converts to json
            WriteStringToFile.main(file, json);
        } catch (Exception ex) {
            System.out.println("Error while exporting!");
        }
    }

    // importing new database using gson library
    public static void importElementList(Window stage) {
        // https://simplesolution.dev/java-convert-json-file-to-java-object-using-gson/
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Dialog");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("json file", "*.json"));
        try {
            clearElementList();
            File file = fc.showOpenDialog(stage);
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(file);
            Type type = new TypeToken<List<Element>>(){}.getType();
            elementList = gson.fromJson(fileReader, type);
        } catch (Exception ex) {
            System.out.println("Error while importing!");
        }
    }

    // function returning titles of all currently filtered elements
    // currently filtered elements are filtered in second list 'elementsFiltered'
    public static String[] getElementList(String filter, String regexp) {
        elementsFiltered.clear();
        boolean searchActive = false;
        boolean matchFound;

        if (regexp.length() > 0) {
            searchActive = true;
        }

        // looping through all elements
        for (Element element : elementList) {
            // check for matches with current search filter set by user
            if (searchActive) {
                Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(element.title);
                matchFound = matcher.find();
            } else {matchFound = true;}
            if(matchFound) {
                // check for filter options set by user
                if (filter.equals("all")) {
                    elementsFiltered.add(element);
                } else {
                    if (element.type.equals(filter)) {
                        elementsFiltered.add(element);
                    }
                }
            }
        }

        // return titles of currently filtered elements
        elementsFiltered.sort(Comparator.comparing(Element::toString));
        int l = elementsFiltered.size();
        String[] titles = new String[l];

        for (int i = 0; i < l; i++) {
            titles[i] = i+1 + ": " + elementsFiltered.get(i).title;
        }

        return titles;
    }

    // updating hash code after editing element
    public static void updateHash(int hash) {
        int n = elementList.size();
        int i = 0;

        while (i < n) {
            if (elementList.get(i).hash == hash) {
                elementList.get(i).hash = elementList.get(i).updateHash();
                break;
            }
            i++;
        }
    }
}
