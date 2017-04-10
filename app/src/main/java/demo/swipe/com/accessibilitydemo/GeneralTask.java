package demo.swipe.com.accessibilitydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by USer on 06-02-2017.
 */

public class GeneralTask extends AsyncTask<String,String,String> {
    Context context;
    String responseString = "";
    private String url = "";
    ArrayList<ResultItems> resultItemsArray=new ArrayList<>();
    String store_name1;
    String newb1, newb2;
    Integer price;


    String productName,newProdName;
    private String countofProducts;
    private String searchedString;
    private String responseStringForId;
    private ArrayList<StoreDetails> storeDetailsArray;
    private BrowsedProduct browsedProduct;

    public  GeneralTask(Context context,Integer getPrice){
        this.context=context;
        price = getPrice;
        browsedProduct= new BrowsedProduct();
    }

    public String isContainExactWord(CharSequence charSequence) {
        final StringBuilder sb = new StringBuilder(charSequence.length());
        sb.append(charSequence);
        return sb.toString();
    }


    public String queryLogic(String productnameString){

        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            url = strings[0];
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();
            Response response = httpClient.newCall(request).execute();
            responseString = response.body().string();

            if(responseString!=null) {
                JSONObject jsonObject = null;
                jsonObject = new JSONObject(responseString);

                if (resultItemsArray != null) {
                    resultItemsArray.clear();
                }

                if (jsonObject.has("count")) {
                    countofProducts = jsonObject.getString("count");
                }

                if (Integer.parseInt(countofProducts) != 1373) {
                    if (jsonObject.has("items")) {
                        JSONArray searchItemJArray = jsonObject.getJSONArray("items");
                        for (int i = 0; i < searchItemJArray.length(); i++) {
                            JSONObject itemJObj = searchItemJArray.getJSONObject(i);
                            ResultItems resultItem= new ResultItems();
                            if (itemJObj.has("id")) {
                                //searchItem.setId(itemJObj.getString("id"));
                                resultItem.setProductId(itemJObj.getString("id"));
                            }

                            if (itemJObj.has("price")) {
                                //searchItem.setPrice(itemJObj.getString("price"));
                                resultItem.setProductBrowsedPrice(itemJObj.getString("price"));
                            }

                            if (itemJObj.has("title")) {
                                //searchItem.setTitle(itemJObj.getString("title"));
                                resultItem.setProductTitle(itemJObj.getString("title"));
                            }


                            resultItemsArray.add(resultItem);
                        }
                        browsedProduct.setProductCrawledName(MyApplication.getNameString.toString());
                        browsedProduct.setProductCrawledPrice(MyApplication.getPrice);

                        //New code for string formation according to category
                        productName = MyApplication.getNameString.toString().toLowerCase().replace(",", "").replaceAll("\\(.*?\\) ?", "");

                        //Set Crawled Price of Object

                        Log.e("ProductName", "" + productName);
                        String[] productNameArray = productName.split("\\s+");

                        for (int i = 0; i < resultItemsArray.size(); i++) {

                            ResultItems resultItem = resultItemsArray.get(i);
                            searchedString = resultItem.getProductTitle().toLowerCase().toString();
                            Log.e("Searchedstring", "" + searchedString);

                            String[] searchedStringArray = searchedString.split("\\s+");
                            searchedStringArray = new HashSet<>(Arrays.asList(searchedStringArray)).toArray(new String[0]);


                            ArrayList<String> ar = new ArrayList<>();

                            //Fetched String divided into parts
                            for (int j = 0; j < searchedStringArray.length; j++) {
                                if (Arrays.asList(productNameArray).contains(searchedStringArray[j]))
                                    ar.add(searchedStringArray[j]);
                            }

                            Log.e("CountofArray", " " + ar.size());

                        }


                        //Price Matching Logic
                        int index=0;
                        for(int a=0;a< resultItemsArray.size();a++){
                            if(MyApplication.getPrice==Integer.parseInt(resultItemsArray.get(a).getProductBrowsedPrice())){
                                    index=a;
                            }
                        }


                        //Second Request for id to get proper response
                        Request requester = new Request.Builder().url(MyApplication.second_api + resultItemsArray.get(index).getProductId()).build();
                        Response responser = httpClient.newCall(requester).execute();
                        responseStringForId = responser.body().string();
                        Log.e("Id sent ",""+resultItemsArray.get(index).getProductId());
                        Log.e("Resss", responseStringForId);

/*
                        item = new Item();
*/

                        if (responseStringForId != null) {
                            JSONObject jObjforId = new JSONObject(responseStringForId);
                            //browsedProduct= new BrowsedProduct();
                            if (jObjforId.has("best_price")) {
                                String bestPrice = jObjforId.getString("best_price");
                                if (bestPrice.equalsIgnoreCase("null"))
                                    resultItemsArray.get(index).setBestPrice("Out of Stock");
                                else
                                    resultItemsArray.get(index).setBestPrice(bestPrice);
                            }



                            storeDetailsArray= new ArrayList<>();
                            JSONObject linesjObj = jObjforId.getJSONObject("lines");
                            Iterator<?> keys = linesjObj.keys();
                            String skey = "";
                            boolean listing_not_available = true;
                            if (!resultItemsArray.get(index).getBestPrice().equalsIgnoreCase("Out of stock")) {
                                while (keys.hasNext()) {
                                    skey = (String) keys.next();
                                    MyApplication.store_name.add(skey);
                                    JSONObject storejObj = linesjObj.getJSONObject(skey);
                                    if (storejObj.has("listings")) {
                                        //Listing listing = new Listing();
                                        try {
                                            JSONArray listingJArray = storejObj.getJSONArray("listings");
                                            listing_not_available = true;
                                        } catch (Exception e) {
                                            listing_not_available = false;
                                        }
                                        if (listing_not_available == false) {
                                            JSONObject listingjObj = storejObj.getJSONObject("listings");
                                            Iterator<?> lineKeys = listingjObj.keys();
                                            String lineKey = "";
                                            while (lineKeys.hasNext()) {
                                                lineKey = (String) lineKeys.next();
                                                //JSONObject storeListingjObj = listingjObj.getJSONObject(lineKey);
                                                JSONObject storeListjObj=listingjObj.getJSONObject(lineKey);

                                                StoreDetails storeDetails= new StoreDetails();
                                                if(storeListjObj.has("category")){
                                                    storeDetails.setCategory(storeListjObj.getString("category"));
                                                }
                                                if(storeListjObj.has("store")){
                                                    storeDetails.setStore(storeListjObj.getString("store"));
                                                }
                                                if(storeListjObj.has("price")){
                                                    storeDetails.setPrice(Integer.parseInt(storeListjObj.getString("price")));
                                                }

                                                if(storeListjObj.has("color")){
                                                    storeDetails.setColor(storeListjObj.getString("color"));
                                                }

                                                if (storeListjObj.has("store_url")) {
                                                    storeDetails.setStore_url(storeListjObj.getString("store_url"));
                                                    if (storeDetails.getStore_url().contains("sendtostore.php")) {
                                                        storeDetails.setStore_url(storeDetails.getStore_url().replace("sendtostore.php", "justswipe.php"));
                                                    }

                                                }

                                                if (storeDetails.getPrice() != null) {
                                                    storeDetailsArray.add(storeDetails);

                                                    Log.e("Added","StoreDetails");
                                                }
                                            }
                                        }

                                    } else {
                                        /*if (item.getLines_available().equalsIgnoreCase("false")) {
                                            JSONObject listingSummaryjObj = storejObj.getJSONObject("listing_summary");
                                            Listing listing = new Listing();
                                            listing.setStore_url(listingSummaryjObj.getString("store_base_url"));
                                            if (listing.getStore_url().contains("sendtostore.php")) {
                                                listing.setStore_url(listing.getStore_url().replace("sendtostore.php", "justswipe.php"));
                                            }
                                            listing.setPrice(item.getBest_price());
                                            listing.setStoreImage(listingSummaryjObj.getString("store_image_url"));
                                            listingItemArray.add(listing);

                                        }*/

                                    }

                                    if(storeDetailsArray.size()>0){
                                        resultItemsArray.get(index).setStoreDetailsArray(storeDetailsArray);
                                    }

                                    //Log.e("Store",""+MyApplication.store_URL);

                                    for (int i = 0; i < resultItemsArray.get(index).getStoreDetailsArray().size(); i++) {
                                        MyApplication.price_name.add(Integer.valueOf(resultItemsArray.get(index).getStoreDetailsArray().get(i).getPrice()));
                                        MyApplication.cat_name.add(resultItemsArray.get(index).getStoreDetailsArray().get(i).getStore_url());
                                        Log.e("TAG", "Actual_price " + MyApplication.price_name.get(i));
                                    }
                                }
                                //MyApplication.store_URL = listingItemArray.get(index).getStore_url();


                            }
                        }
                    }
                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return responseString;
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        Log.e("Inside","OnPOstExecute");

/*
        for (int i = 0; i < MyApplication.price_name.size(); i++) {

            Log.e("Inside","price name");
            int constant_price = Integer.parseInt(MyApplication.price_name.get(i).toString());
            //Log.e("TAG", "store_title" + MyApplication.store_name.get(i));

            if(storeDetailsArray!=null){
                if(storeDetailsArray.size()>0){
                    Log.e("Inside","Store Details");

                    if (Collections.min(MyApplication.price_name) == constant_price) {
                        //MyApplication.store_title = MyApplication.store_name.get(i).toString();
                        MyApplication.store_URL = storeDetailsArray.get(i).getStore_url();


                        MyApplication.saving_price=MyApplication.getPrice-Collections.min(MyApplication.price_name);
                        if (!isMyServiceRunning(ChatHeadService.class)) {
                            context.startService(new Intent(context, ChatHeadService.class));
                        }
                        Log.e("name", "name " + MyApplication.store_URL);
                        break;
                    }

            }



            }
            MyApplication.store_name.clear();
            MyApplication.price_name.clear();
            MyApplication.cat_name.clear();

            resultItemsArray.clear();
            browsedProduct.getResultItemsArray().clear();

        }
*/


        for (int i = 0; i < MyApplication.price_name.size(); i++) {

            Log.e("Inside","price name");
            int constant_price = Integer.parseInt(MyApplication.price_name.get(i).toString());
            //Log.e("TAG", "store_title" + MyApplication.store_name.get(i));

            if(storeDetailsArray!=null){
                if(storeDetailsArray.size()>0){
                    Log.e("Inside","Store Details");

                    if (Collections.min(MyApplication.price_name) == constant_price) {
                        Log.e("Constant Price",""+constant_price);

                        //if (Collections.min(MyApplication.price_name) == constant_price) {

                            //MyApplication.store_title = MyApplication.store_name.get(i).toString();
                        MyApplication.store_URL = storeDetailsArray.get(i).getStore_url();

                        MyApplication.saving_price=MyApplication.getPrice-Collections.min(MyApplication.price_name);
                        if (!isMyServiceRunning(ChatHeadService.class)) {
                            context.startService(new Intent(context, ChatHeadService.class));
                        }
                        Log.e("name", "name " + MyApplication.store_URL);
                        break;
                    }

                }



            }
            MyApplication.store_name.clear();
            MyApplication.price_name.clear();
            MyApplication.cat_name.clear();

            resultItemsArray.clear();
            if(browsedProduct!=null){
                if(browsedProduct.getResultItemsArray()!=null){
                    browsedProduct.getResultItemsArray().clear();
                }
            }

        }

    }
}
