package aliawad.finalprojectali.Data;

public class Thing{
    public String Title;
    public String Category;
    public String Link;
    public String Image;
    private String key;
    private String owner;

    public static boolean isSuccessful() {
        return isSuccessful();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "Title='" + Title + '\'' +
                ", Category='" + Category + '\'' +
                ", Link='" + Link + '\'' +
                ", Image='" + Image + '\'' +
                ", key='" + key + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}



