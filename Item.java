public class Item {
    private String imagePath;
    private int price;

    public Item(String imagePath, int price) {
        this.imagePath = imagePath;
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getPrice() {
        return price;
    }
}