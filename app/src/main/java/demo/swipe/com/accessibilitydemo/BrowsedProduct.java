package demo.swipe.com.accessibilitydemo;

import java.util.ArrayList;

/**
 * Created by USer on 20-02-2017.
 */

public class BrowsedProduct {


    public String getProductCrawledName() {
        return productCrawledName;
    }

    public void setProductCrawledName(String productCrawledName) {
        this.productCrawledName = productCrawledName;
    }

    public Integer getProductCrawledPrice() {
        return productCrawledPrice;
    }

    public void setProductCrawledPrice(Integer productCrawledPrice) {
        this.productCrawledPrice = productCrawledPrice;
    }


    public ArrayList<ResultItems> getResultItemsArray() {
        return resultItemsArray;
    }

    public void setResultItemsArray(ArrayList<ResultItems> resultItemsArray) {
        this.resultItemsArray = resultItemsArray;
    }


    @Override
    public String toString() {
        return "BrowsedProduct{" +
                "productCrawledName='" + productCrawledName + '\'' +
                ", productCrawledPrice=" + productCrawledPrice +
                ", resultItemsArray=" + resultItemsArray +
                '}';
    }

    private String productCrawledName;
    private Integer productCrawledPrice;
    private ArrayList<ResultItems> resultItemsArray;


}
