package group.two.allesinordnung;

public class Element {

    public static int count;
    public int id;
    public String title;
    public String type;
    public String author;

    public Element(String title, String type, String author) {
        this.title = title;
        this.type = type;
        this.author = author;
        this.id = count;
        count++;
    }

    public Element() {
        //this.id = count;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Element.count = count;
    }

    @Override
    public String toString() {
        return title;
    }



}
