package demo.swipe.com.accessibilitydemo;

import java.util.ArrayList;

/**
 * Created by USer on 21-02-2017.
 */

public class ResultItems {

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductBrowsedPrice() {
        return productBrowsedPrice;
    }

    public void setProductBrowsedPrice(String productBrowsedPrice) {
        this.productBrowsedPrice = productBrowsedPrice;
    }

    public ArrayList<StoreDetails> getStoreDetailsArray() {
        return storeDetailsArray;
    }

    public void setStoreDetailsArray(ArrayList<StoreDetails> storeDetailsArray) {
        this.storeDetailsArray = storeDetailsArray;
    }


    public String getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(String bestPrice) {
        this.bestPrice = bestPrice;
    }



    private String productTitle;
    private String productId;
    private String productBrowsedPrice;
    private String bestPrice;
    ArrayList<StoreDetails> storeDetailsArray;

    @Override
    public String toString() {
        return "ResultItems{" +
                "productTitle='" + productTitle + '\'' +
                ", productId='" + productId + '\'' +
                ", productBrowsedPrice='" + productBrowsedPrice + '\'' +
                ", bestPrice='" + bestPrice + '\'' +
                ", storeDetailsArray=" + storeDetailsArray +
                '}';
    }
}
