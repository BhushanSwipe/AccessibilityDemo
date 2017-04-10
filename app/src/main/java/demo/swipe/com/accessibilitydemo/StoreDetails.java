package demo.swipe.com.accessibilitydemo;


/**
 * Created by USer on 20-02-2017.
 */

public class StoreDetails {

    private String color;
    private String category;
    private Integer price;
    private String store;
    private String store_url;

    @Override
    public String toString() {
        return "StoreDetails{" +
                "color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", store='" + store + '\'' +
                ", store_url='" + store_url + '\'' +
                '}';
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStore_url() {
        return store_url;
    }

    public void setStore_url(String store_url) {
        this.store_url = store_url;
    }
}
