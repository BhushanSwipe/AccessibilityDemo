package demo.swipe.com.accessibilitydemo;

/**
 * Created by USer on 20-02-2017.
 */

public class QueryProducts {
    private String productTitle;
    private String productId;
    private String productPrice;
    private String productCategory;

    @Override
    public String toString() {
        return "QueryProducts{" +
                "productTitle='" + productTitle + '\'' +
                ", productId='" + productId + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
