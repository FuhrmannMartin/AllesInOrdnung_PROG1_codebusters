package group.two.allesinordnung;

public class Element {

    public String type;
    public String title;
    public String author;
    public int hash;

    public Element(String title, String type, String author, int hash) {
        this.title = title;
        if (type.equals("CD") || type.equals("DVD") || type.equals("book")) {
            this.type = type;
        } else {
            this.type = "undefined";
        }
        this.author = author;
        if (hash != 0) {
            this.hash = hash;
        } else {
            this.hash = (title + type + author).hashCode();
        }
    }

    @Override
    public String toString() {
        return type + "; " + title + "; " + author + "; " + hash;
    }

    public int updateHash() {
        return (this.title + this.type + this.author).hashCode();
    }

}
