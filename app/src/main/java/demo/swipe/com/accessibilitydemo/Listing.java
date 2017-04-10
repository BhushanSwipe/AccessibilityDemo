package demo.swipe.com.accessibilitydemo;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by USer on 19-08-2016.
 */
public class Listing {
    private String storeName;
    private String storeImage;
    private String line_id;
    private String category;
    private String store;
    private String store_url;
    private String shipping_cost;
    private String mrp;
    private String price;
    private String delivery;
    private String color;
    private String emi;
    private String cod;
    private String offers;
    private String relevance;
    private String coupons_available;
    private String coupon_id;
    private String special_coupons_available;
    private String special_coupon_id;
    private String price_before_coupon;
    private String price_after_coupon;
    private String coupon_id_new;
    private String msp_coins;
    public static  ArrayList<String> arrayList = new ArrayList<>();

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        Log.e("u","u"+this.store_url);
    }

    public String getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(String shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEmi() {
        return emi;
    }

    public void setEmi(String emi) {
        this.emi = emi;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getCoupons_available() {
        return coupons_available;
    }

    public void setCoupons_available(String coupons_available) {
        this.coupons_available = coupons_available;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getSpecial_coupons_available() {
        return special_coupons_available;
    }

    public void setSpecial_coupons_available(String special_coupons_available) {
        this.special_coupons_available = special_coupons_available;
    }

    public String getSpecial_coupon_id() {
        return special_coupon_id;
    }

    public void setSpecial_coupon_id(String special_coupon_id) {
        this.special_coupon_id = special_coupon_id;
    }

    public String getPrice_before_coupon() {
        return price_before_coupon;
    }

    public void setPrice_before_coupon(String price_before_coupon) {
        this.price_before_coupon = price_before_coupon;
    }

    public String getPrice_after_coupon() {
        return price_after_coupon;
    }

    public void setPrice_after_coupon(String price_after_coupon) {
        this.price_after_coupon = price_after_coupon;
    }

    public String getCoupon_id_new() {
        return coupon_id_new;
    }

    public void setCoupon_id_new(String coupon_id_new) {
        this.coupon_id_new = coupon_id_new;
    }

    public String getMsp_coins() {
        return msp_coins;
    }

    public void setMsp_coins(String msp_coins) {
        this.msp_coins = msp_coins;
    }
}
