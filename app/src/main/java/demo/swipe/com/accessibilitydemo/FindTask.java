package demo.swipe.com.accessibilitydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by USer on 24-02-2017.
 */

public class FindTask extends AsyncTask<String,String,String> {

    Context context;
    String responseString = "";
    private String url = "";
    private Boolean is_mspid_found;
    String id= null;
    private String responseStringForId;
    private ArrayList<StoreDetails> storeArray;
    private ProductModel productModel;


    public FindTask(Context context){
        this.context=context;
//        this.sharedPreferences=sharedPreferences;
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.e("OnCancelled","FindTask");
        MyApplication.isFindTaskRunning=false;
        Log.e("isFindTaskRunning","false");


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("onPreExecute","FindTask");
        MyApplication.isFindTaskRunning=true;
        Log.e("isFindTaskRunning","true");

    }


    @Override
    protected String doInBackground(String... strings) {
        Log.e("doInBackground","FindTask");

        try{
            if(!isCancelled()){
                url = strings[0];
                Log.e("Link",""+url);
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder().url(strings[0]).build();

//            Request request = new Request.Builder().url(strings[0]).addHeader("Content-Type","application/x-www-form-urlencoded").build();
                Response response = httpClient.newCall(request).execute();
                responseString = response.body().string();

                if(responseString!=null) {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(responseString);

                    JSONObject jObjResponse=null;
                    if(jsonObject.has("response")){
                        jObjResponse= jsonObject.getJSONObject("response");
                        if (jObjResponse.has("is_mspid_found")) {
                            is_mspid_found = Boolean.parseBoolean(jObjResponse.getString("is_mspid_found"));
                        }
                    }


                    if (is_mspid_found) {
                        JSONObject jObjLink = null;
                        productModel = new ProductModel();

                        if (jObjResponse.has("link")) {

                            jObjLink = new JSONObject(jObjResponse.getString("link"));
                            if (jObjLink.has("id")) {

                                id = jObjLink.getString("id");
                                productModel.setId(id);

                                //Second web service calling
                                Request requester = new Request.Builder().url(MyApplication.second_api + id).build();
                                Response responser = httpClient.newCall(requester).execute();
                                responseStringForId = responser.body().string();

                                JSONObject jObjforId = null;
                                jObjforId = new JSONObject(responseStringForId);
                                if (jObjforId.has("best_price")) {
                                    String bestPrice = jObjforId.getString("best_price");
                                    if (bestPrice.equalsIgnoreCase("null"))
                                        productModel.setBest_price("Out of Stock");
                                    else
                                        productModel.setBest_price(bestPrice);
                                }

                                JSONObject linesjObj = jObjforId.getJSONObject("lines");
                                storeArray = new ArrayList<>();
                                Iterator<?> keys = linesjObj.keys();
                                String skey = "";
                                boolean listing_not_available = true;

                                if (!productModel.getBest_price().equalsIgnoreCase("Out of stock")) {
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
                                                    JSONObject storeListjObj = listingjObj.getJSONObject(lineKey);

                                                    StoreDetails storeDetails = new StoreDetails();
                                                    if (storeListjObj.has("category")) {
                                                        storeDetails.setCategory(storeListjObj.getString("category"));
                                                    }
                                                    if (storeListjObj.has("store")) {
                                                        storeDetails.setStore(storeListjObj.getString("store"));
                                                    }
                                                    if (storeListjObj.has("price")) {
                                                        storeDetails.setPrice(Integer.parseInt(storeListjObj.getString("price")));
                                                    }

                                                    if (storeListjObj.has("color")) {
                                                        storeDetails.setColor(storeListjObj.getString("color"));
                                                    }

                                                    if (storeListjObj.has("store_url")) {
                                                        storeDetails.setStore_url(storeListjObj.getString("store_url"));
                                                        if (storeDetails.getStore_url().contains("sendtostore.php")) {
                                                            storeDetails.setStore_url(storeDetails.getStore_url().replace("sendtostore.php", "justswipe.php"));
                                                        }

                                                    }

                                                    if (storeDetails.getPrice() != null) {
                                                        storeArray.add(storeDetails);
                                                        Log.e("Added", "StoreDetails:"+storeDetails.getStore()+":-"+storeDetails.getPrice());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        productModel.setStoreArray(storeArray);
                        Collections.sort(storeArray,
                                new Comparator<StoreDetails>() {
                                    @Override
                                    public int compare(StoreDetails lhs, StoreDetails rhs) {
                                        return lhs.getPrice().compareTo(
                                                rhs.getPrice());
                                    }
                                });
                        Log.e("Min", "" + storeArray.get(0).getPrice());

                    } else {
                        //Not found mspid
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }



        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("onPostExecute","FindTask");

        if(storeArray!=null){
            Log.e("StoreArraySize",""+storeArray.size());

            if(storeArray.size()>0){
                Log.e("StoreArraySize1",""+storeArray.size());

                MyApplication.store_URL = storeArray.get(0).getStore_url();
                Log.e("Saving Price","P="+MyApplication.getPrice+",Min="+storeArray.get(0).getPrice());
                MyApplication.saving_price=MyApplication.getPrice-storeArray.get(0).getPrice();
                if(MyApplication.saving_price>0){
                    if (!isMyServiceRunning(ChatHeadService.class)) {
                        context.startService(new Intent(context, ChatHeadService.class));
                        Log.e("ChatHeadService","Started from FindTask");

                    }

                } else if(MyApplication.saving_price==0){
                    Toast.makeText(context.getApplicationContext(), "This is Lowest price", Toast.LENGTH_SHORT).show();
                }else{
                    MyApplication.saving_price=0;
                }

                if(storeArray.get(0).getPrice()>MyApplication.getPrice){
                    Toast.makeText(context, "This is Lowest Price", Toast.LENGTH_SHORT).show();
                }

            }
            storeArray.clear();


            if(productModel!=null){
                productModel=null;
            }

        }else{
            Log.e("StoreArray","Null");
            Toast.makeText(context, "Not found on server", Toast.LENGTH_SHORT).show();
        }

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
}
