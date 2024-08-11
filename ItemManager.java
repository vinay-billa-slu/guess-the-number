import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager {
    private List<Item> items;
    private int currentItemIndex;
    private int currentPrice;

    public ItemManager() {
        initializeItems();
    }

    private void initializeItems() {
        items = new ArrayList<>();
        items.add(new Item("image_1.jpg", 12));
        items.add(new Item("image_2.jpg", 75));
        items.add(new Item("image_3.jpg", 34));
        items.add(new Item("image_4.jpg", 90));
        items.add(new Item("image_5.jpg", 36));
        items.add(new Item("image_6.jpg", 75));
        items.add(new Item("image_7.jpg", 78));
        items.add(new Item("image_8.jpg", 29));
        items.add(new Item("image_9.jpg", 67));
    }

    public void startNewRound() {
        currentItemIndex = new Random().nextInt(items.size());
        currentPrice = items.get(currentItemIndex).getPrice();
    }

    public String getCurrentItemImage() {
        return items.get(currentItemIndex).getImagePath();
    }

    public int getCurrentPrice() {
        return currentPrice;
    }
}
