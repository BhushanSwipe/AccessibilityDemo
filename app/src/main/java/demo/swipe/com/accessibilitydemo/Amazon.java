package demo.swipe.com.accessibilitydemo;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by USer on 25-10-2016.
 */

public class Amazon {
    public static boolean checkAmazonFlag;
    public static ArrayList<Integer> arr= new ArrayList<>();

    public static String getProductPriceAmazon(AccessibilityNodeInfo source, int level, String type) {
        String productPrice = null;
        if (source != null) {
            int childCount = source.getChildCount();
            int i = 0;
            while (i < childCount) {
//                Log.i("PricerFirst", "" + source.getChild(i).getContentDescription() + " " + level + " " +i+" "+source.getChild(i).getViewIdResourceName());
                String priceret=null;
                if(source.getChild(i)!=null && source.getChild(i).getContentDescription()!=null) {
                    //Log.e("APriceInClass", source.getChild(i).getContentDescription() + "-->" + source.getChild(i).getClassName() + "-->" + source.getChild(i).getViewIdResourceName() + "-->" + source.getChild(i).isVisibleToUser()+",L="+level+",i="+i);

                    //Previous Code
                    if(source.getChild(i).getContentDescription().equals("Price:")){

                        if(source.getChild(i+1)!=null && source.getChild(i+1).getContentDescription()!=null){
                            Log.i("Price","Detected " +source.getChild(i+1).getContentDescription());
                            productPrice=source.getChild(i+1).getContentDescription().toString();
                            if(source.getChild(i+1).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim().length()>0){
                                Log.i("Added","Price");
                                arr.add(Integer.parseInt(source.getChild(i+1).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim()));
                            }
                        }

                        for(int j=0;j<source.getChildCount();j++){
                                if(j==13){
                                    if(source.getChild(j)!=null && source.getChild(j).getContentDescription()!=null){
                                        Log.i("SalePrice","Detected " +source.getChild(j).getContentDescription());

                                    }
                                }else{
                                    if(source.getChild(j)!=null && source.getChild(j).getContentDescription()!=null){
                                        Log.i("BhushanPrice", level+",J="+j+" "+source.getChild(j).getContentDescription()+"-->"+source.getChild(j).getClassName()+"--"+source.getChild(j).getParent().getClassName()+"--"+source.getChild(j).getParent().getParent().getClassName()+"-->"+source.getViewIdResourceName()+"-+>"+source.getChild(j).getViewIdResourceName()+"--"+source.getChild(j).getParent().getViewIdResourceName()+"--"+source.getChild(j).getParent().getParent().getViewIdResourceName());

                                    }

                                }

                        }
                    } else if(source.getChild(i).getContentDescription().equals("Sale:")) {
                        if(source.getChild(i+1)!=null && source.getChild(i+1).getContentDescription()!=null) {
                            Log.e("SalePrice", "Detected 1 " + source.getChild(i + 1).getContentDescription());
                            productPrice=source.getChild(i + 1).getContentDescription().toString();
                            if(source.getChild(i+1).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim().length()>0){
                                Log.e("Added","SalePrice");

                                arr.add(Integer.parseInt(source.getChild(i+1).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim()));
                            }

                        }

                        for(int j=0;j<source.getChildCount();j++){
                            if(j==13){
                                if(source.getChild(j)!=null && source.getChild(j).getContentDescription()!=null) {
                                    Log.e("SalePrice","Detected " +source.getChild(j).getContentDescription());
                                }
                            }else{
//                                Log.i("BhushanPrice", level+",J="+j+" "+source.getChild(j).getContentDescription()+"-->"+source.getChild(j).getClassName()+"--"+source.getChild(j).getParent().getClassName()+"--"+source.getChild(j).getParent().getParent().getClassName()+"-->"+source.getViewIdResourceName()+"-+>"+source.getChild(j).getViewIdResourceName()+"--"+source.getChild(j).getParent().getViewIdResourceName()+"--"+source.getChild(j).getParent().getParent().getViewIdResourceName());
                            }

                        }

                    }else if (source.getChild(i).getContentDescription().toString().startsWith("Lightning Deal")) {
                        Log.e("Deal", "Detected " + source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").trim() + " " + level + " " + i + " " + source.getChild(i).getViewIdResourceName());
                        productPrice = source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").trim();
                        if(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim().length()>0){
                            Log.e("Added","Deal");
                            arr.add(Integer.parseInt(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim()));
                        }
                    }else if(source.getChild(i)!=null && source.getChild(i).getViewIdResourceName()!=null && source.getChild(i).getViewIdResourceName().equals("priceblock_dealprice")){
                        Log.e("DP","--"+source.getChild(i).getContentDescription());
                        if(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim().length()>0){
                            Log.e("Added","DP");
                            arr.add(Integer.parseInt(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim()));
                        }
                    }else if(source.getChild(i)!=null && source.getChild(i).getContentDescription()!=null && source.getChild(i).getClassName().equals("android.widget.EditText") && level==3 && i==0){
                        Log.e("DiffP","--"+source.getChild(i).getContentDescription());
                        if(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim().length()>0){
                            Log.e("Added","DiffP");
                            arr.add(Integer.parseInt(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim()));
                        }
                    }
                }else{

                    Log.i("NonPrice","Detected");
                }
                // Deal of the day

                if(source.getChild(i) != null){
                    if(source.getChild(i).getClassName()!=null && source.getChild(i).getClassName().equals("android.view.View") && source.getChild(i).isVisibleToUser() && source.getChild(i).getContentDescription()!=null && source.getChild(i).getContentDescription().length()>0){
                        if(level==2 && i==1){
                            Log.e("DealDay","Detected "+source.getChild(i).getContentDescription()+" L: "+level+" i:"+i);
                            if(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim().length()>0){
                                Log.e("Added","DealDay");
                                arr.add(Integer.parseInt(source.getChild(i).getContentDescription().toString().replace("Lightning Deal", "").replaceAll("\\(.*\\)", "").replaceAll("\\s+","").replaceAll("[^0-9]", "").trim()));
                            }
                        }
                    }
                }
                if(level==3 && i>7) {
                    if (source.getChild(i)!=null){
                        if(level==3 && i==9) {
                            Log.e("PricerOuter", "" + source.getChild(i).getContentDescription() + " " + level + " " + i);
                        }
                    }
                }
                if(level==3 && i==7 && source.getChild(i)!=null && source.getChild(i).getClassName()!=null && source.getChild(i).getClassName().equals("android.view.View")&& source.getChild(i).getParent()!=null && source.getChild(i).getParent().getClassName()!=null && source.getChild(i).getParent().getClassName().equals("android.view.View")&& source.getChild(i).getParent().getParent().getClassName()!=null && source.getChild(i).getParent().getParent().getClassName().equals("android.webkit.WebView") && source.getChild(i).getParent().getParent().getParent().getClassName()!=null && source.getChild(i).getParent().getParent().getParent().getClassName().equals("android.webkit.WebView")) {
                    Log.e("PricerG", "" + source.getChild(i).getContentDescription() + " " + level + " " + i + " " + source.getChild(i).getParent().getClassName()+"-->"+source.getChild(i).getParent().getViewIdResourceName()+" ,"+ source.getChild(i).getParent().getParent().getClassName()+"-->"+source.getChild(i).getParent().getParent().getViewIdResourceName() +" ,"+ source.getChild(i).getParent().getParent().getParent().getClassName()+" ,"+ source.getChild(i).getParent().getParent().getParent().getViewIdResourceName());
                }

                //Normal Pricer Getter


                if(source.getChild(i)!=null && source.getChild(i).getContentDescription()!=null &&  source.getChild(i).getParent()!=null && source.getChild(i).getParent().getClassName()!=null &&source.getChild(i).getParent().getClassName().equals("android.view.View")&& source.getChild(i).getParent().getParent().getClassName()!=null &&source.getChild(i).getParent().getParent().getClassName().equals("android.view.View") && source.getChild(i).getParent().getParent().getParent()!=null && source.getChild(i).getParent().getParent().getParent().getClassName()!=null && source.getChild(i).getParent().getParent().getParent().getClassName().equals("android.webkit.WebView") && source.getChild(i).getClassName().equals("android.widget.EditText")&& source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent()!=null && source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getClassName().equals("android.support.v4.widget.DrawerLayout")){
                    if(level==4 && i==0){
                        Log.e("Pricer 1", "Detected"+source.getChild(i).getContentDescription()+" " + level + " " + i +" "+source.getClassName()+"-->"+source.getViewIdResourceName()+" ,"+source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getClassName()+"-->"+source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getViewIdResourceName());
                        if(source.getChild(i).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim().length()>0){
                            Log.e("Added","Pricer 1");
                            arr.add(Integer.parseInt(source.getChild(i).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim()));
                        }
                        productPrice=source.getChild(i).getContentDescription().toString();
                    }else if(level==6 && i==0){
                        if(source.getChild(i).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim().length()>0){
                            Log.e("Added","Pricer 2");
                            arr.add(Integer.parseInt(source.getChild(i).getContentDescription().toString().replaceAll("[^0-9]", "").replace("LightningDeal","").replace(",","").replace(" ","").replace("rupees","").replace("₹","").replace("s","").replaceAll("\\s+","").trim()));
                        }
                        productPrice=source.getChild(i).getContentDescription().toString();
                        Log.e("Pricer 2", "Detected"+source.getChild(i).getContentDescription()+" " + level + " " + i +" "+source.getClassName()+"-->"+source.getViewIdResourceName()+" ,"+source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getClassName()+"-->"+source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getViewIdResourceName());
                    }
                }

                if (!(source.getChild(i) == null || source.getChild(i).getContentDescription() == null)) {
                    if (level == 2 && i == 0 && source.getChild(i).getClassName().equals("android.widget.EditText") && source.getChild(i).getParent() != null && source.getChild(i).getParent().getClassName().equals("android.view.View") && source.getChild(i).getParent().getParent() != null && source.getChild(i).getParent().getParent().getClassName().equals("android.view.View") && source.getChild(i).getParent().getParent().getParent() != null && source.getChild(i).getParent().getParent().getParent().getClassName().equals("android.webkit.WebView") && source.getChild(i).getParent().getParent().getParent().getParent() != null && source.getChild(i).getParent().getParent().getParent().getParent().getClassName().equals("android.webkit.WebView") && source.getChild(i).getParent().getParent().getParent().getParent().getParent() != null && source.getChild(i).getParent().getParent().getParent().getParent().getParent().getClassName().equals("android.widget.LinearLayout") && source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent() != null && source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getClassName().equals("android.support.v4.widget.DrawerLayout") && source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent() != null && source.getChild(i).getParent().getParent().getParent().getParent().getParent().getParent().getParent().getClassName().equals("android.widget.FrameLayout")) {
                        return source.getChild(i).getContentDescription().toString();
                    } else if (source.getChild(i).getChildCount() > 0) {
                        productPrice = getProductPriceAmazon(source.getChild(i), level + 1, "Level One Extraction");
                        if (productPrice != null) {
                            return productPrice;
                        }
                    }
                }
                i++;
            }
        }
        if(arr.size()!=0){
            Log.e("MinPrice",""+ Arrays.asList(arr)+"->"+Collections.min(arr));
            productPrice= Collections.min(arr).toString();
        }
        Log.e("ProductPrice",""+productPrice);
        return productPrice;
    }


    public static String getProductNameAmazon(AccessibilityNodeInfo source, int level) {
        if (source != null) {
            int childCount = source.getChildCount();
            int i = 0;

            while (i < childCount) {
//                Log.i("GetName",""+source.getChild(i).getContentDescription()+" "+level+" "+i+" "+source.getChild(i).getViewIdResourceName());
                if(source.getChild(i) != null) {
                    if (source.getChild(i).getViewIdResourceName() != null) {
                        if (source.getChild(i).getViewIdResourceName().equals("title_feature_div") || source.getChild(i).getViewIdResourceName().equals("title")) {
                            Log.i("PDName1", "" + source.getChild(i).getContentDescription() + " " + level + " " + i + " " + source.getChild(i).getViewIdResourceName());
                            return source.getChild(i).getContentDescription().toString();
                        }
                    }
                }
                else if (!(source.getChild(i) == null || source.getChild(i).getContentDescription() == null)) {
                    String name = source.getChild(i).getContentDescription().toString();
                    if (level == 3 && i == 2) {
                        //Getting Ri8 Response
                        Log.i("PDName2", BuildConfig.VERSION_NAME + name + " " + level + " " + i +" "+source.getChild(i).getViewIdResourceName());
                    }
                    name = name.trim();
                    if (level == 1 && !name.isEmpty()) {
                        return name;
                    }
                    if (i == 0 && source.getChild(i).getChildCount() > 0 && level < 1) {
                        name = getProductNameAmazon(source.getChild(i), level + 1);
                        if (name != null) {
                            Log.i("PDName3", BuildConfig.VERSION_NAME + name + " " + level + " " + i);
                            return name;
                        }
                    }
                }
                i++;
            }
        }
        return null;
    }


    public static void checkProductDetailsPage(AccessibilityEvent event){
        if (event.getClassName() != null) {
            if (event.getClassName().toString().contains("Activity")) {
                if (event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.splashscreen.StartupActivity")) {
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    Log.e("Activity", "False");

                    checkAmazonFlag =false;
                } else if (event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.home.web.MShopWebGatewayActivity")) {
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    Log.e("Activity", "False");

                    checkAmazonFlag =false;
                } else if (event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.categoryBrowse.CategoryBrowseActivity")) {
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    Log.e("Activity", "False");

                    checkAmazonFlag =false;
                } else if (event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.search.SearchActivity")) {
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    Log.e("Activity", "False");

                    checkAmazonFlag =false;
                } else if(event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.search.RetailSearchActivity")){
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    Log.e("Activity", "False");

                    checkAmazonFlag =false;
                }else if(event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.goldbox.WebGoldboxActivity")){
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    Log.e("Activity", "False");

                    checkAmazonFlag =false;

                }else if (event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.details.web.WebProductDetailsActivity")) {

                    MyApplication.checkPrcAmazon = null;
                    MyApplication.checkstr = null;

                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;
                    checkAmazonFlag =true;
                }
/* else {
                    MyApplication.getNameString = null;
                    MyApplication.getPrice = 0;

                    checkAmazonFlag=false;
                    //return false;
                }*/

            } else {

                if(event.getSource().getViewIdResourceName()!=null){
                    if(event.getSource().getViewIdResourceName().equalsIgnoreCase("in.amazon.mShop.android.shopping:id/mash_fragment_stack_container")){
                        MyApplication.getNameString = null;
                        MyApplication.getPrice = 0;
                        Log.e("Activity", "False");
                        checkAmazonFlag =false;
                    }
                }
            }
        }
    }
}
