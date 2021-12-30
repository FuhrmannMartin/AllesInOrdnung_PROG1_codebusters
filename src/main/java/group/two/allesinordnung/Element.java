package group.two.allesinordnung;

public class Element {

    public String type;
    public String title;
    public String author;
    public int stars;
    public int hash;

    public Element(String title, String type, String author) {
        this.title = title;
        this.type = type;
        this.author = author;
        this.hash = (title + type + author).hashCode();
    }

    @Override
    public String toString() {
        return type + "; " + title + "; " + author + "; " + hash;
    }

    public int updateHash() {
        return (this.title + this.type + this.author).hashCode();
    }

}
