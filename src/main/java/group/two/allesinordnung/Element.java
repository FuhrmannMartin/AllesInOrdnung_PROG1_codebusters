package group.two.allesinordnung;

// class defining elements of type (CD/DVD/book)
public class Element {

    public String type;
    public String title;
    public String author;
    public int stars;
    public int hash;

    public Element(String title, String type, String author, int stars) {
        this.title = title;
        this.type = type;
        this.author = author;
        this.hash = (title + type + author).hashCode();
        this.stars = stars;
    }

    @Override
    public String toString() {
        return type + "; " + title + "; " + author + "; " + stars + "; " + hash;
    }

    // has code is created out of title, type and author
    // used to find elements in element list
    // and to ensure every element is unique
    public int updateHash() {
        return (this.title + this.type + this.author).hashCode();
    }

}
