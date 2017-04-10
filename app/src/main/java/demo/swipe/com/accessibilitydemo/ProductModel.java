package demo.swipe.com.accessibilitydemo;

import java.util.ArrayList;

/**
 * Created by USer on 24-02-2017.
 */

public class ProductModel {

    @Override
    public String toString() {
        return "ProductModel{" +
                "id='" + id + '\'' +
                ", best_price='" + best_price + '\'' +
                ", storeArray=" + storeArray +
                '}';
    }

    private String id;
    private String best_price;
    private ArrayList<StoreDetails> storeArray;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBest_price() {
        return best_price;
    }

    public void setBest_price(String best_price) {
        this.best_price = best_price;
    }

    public ArrayList<StoreDetails> getStoreArray() {
        return storeArray;
    }

    public void setStoreArray(ArrayList<StoreDetails> storeArray) {
        this.storeArray = storeArray;
    }
}
