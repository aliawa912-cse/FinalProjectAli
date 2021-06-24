package aliawad.finalprojectali.Data;

public class Thing{
    public String title;
    public String catg;
    public String link;
    public String image;
    private String key;
    private String owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "Title='" + title + '\'' +
                ", Category='" + catg + '\'' +
                ", Link='" + link + '\'' +
                ", Image='" + image + '\'' +
                ", key='" + key + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}



