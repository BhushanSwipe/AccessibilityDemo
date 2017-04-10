package demo.swipe.com.accessibilitydemo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by USer on 19-08-2016.
 */
public class Item {
    private String review_available;
    private String spec_available;
    private String category;
    private String subcategory;
    private String model;
    private String title;
    private String canonical;
    private String best_price;
    private String best_store;
    private String best_line_id;
    private String lines_available;
    private String mrp;
    private String discount;
    private String id;
    private String brand;
    private String reviews;
    private String ratings;
    private String avg_rating;
    private String store_image_url;

    public ArrayList<HashMap<String, String>> getColor_options() {
        return color_options;
    }

    public void setColor_options(ArrayList<HashMap<String, String>> color_options) {
        this.color_options = color_options;
    }

    private ArrayList<HashMap<String, String>> color_options;


    private ArrayList<String> keyFeatures;

    public ArrayList<String> getKeyFeatures() {
        return keyFeatures;
    }

    public void setKeyFeatures(ArrayList<String> keyFeatures) {
        this.keyFeatures = keyFeatures;
    }

    public String getStore_image_url() {
        return store_image_url;
    }

    public void setStore_image_url(String store_image_url) {
        this.store_image_url = store_image_url;
    }

    public ArrayList<String> getImagesUrlFull() {
        return imagesUrlFull;
    }

    public void setImagesUrlFull(ArrayList<String> imagesUrlFull) {
        this.imagesUrlFull = imagesUrlFull;
    }

    public ArrayList<String> getImagesUrlThumb() {
        return imagesUrlThumb;
    }

    public void setImagesUrlThumb(ArrayList<String> imagesUrlThumb) {
        this.imagesUrlThumb = imagesUrlThumb;
    }

    public ArrayList<String> getImagesUrlSmall() {
        return imagesUrlSmall;
    }

    public void setImagesUrlSmall(ArrayList<String> imagesUrlSmall) {
        this.imagesUrlSmall = imagesUrlSmall;
    }

    private ArrayList<String> imagesUrlFull;
    private ArrayList<String> imagesUrlSmall;
    private ArrayList<String> imagesUrlThumb;
    private ArrayList<Listing> listings;

    public String getReview_available() {
        return review_available;
    }

    public void setReview_available(String review_available) {
        this.review_available = review_available;
    }

    public String getSpec_available() {
        return spec_available;
    }

    public void setSpec_available(String spec_available) {
        this.spec_available = spec_available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCanonical() {
        return canonical;
    }

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    public String getBest_price() {
        return best_price;
    }

    public void setBest_price(String best_price) {
        this.best_price = best_price;
    }

    public String getBest_store() {
        return best_store;
    }

    public void setBest_store(String best_store) {
        this.best_store = best_store;
    }

    public String getBest_line_id() {
        return best_line_id;
    }

    public void setBest_line_id(String best_line_id) {
        this.best_line_id = best_line_id;
    }

    public String getLines_available() {
        return lines_available;
    }

    public void setLines_available(String lines_available) {
        this.lines_available = lines_available;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(String avg_rating) {
        this.avg_rating = avg_rating;
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }

    public void setListings(ArrayList<Listing> listings) {
        this.listings = listings;
    }
}
