package demo.swipe.com.accessibilitydemo;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by USer on 14-09-2016.
 */
public class MyApplication extends Application  {
    public static boolean check=false;
    public static String oldNameString = null;
    public static Integer price=0;
    public static Integer flipPrice=0;
    public static int counter;
    public static ArrayList<Integer> char_counter = new ArrayList<>();
    public static int saving_price;
    public static Integer product_price = 0;
    public static boolean scrolling_flag = false;
    public static boolean scrolling_check_false = false;
    public static String store_URL;
    public static boolean checkFlip=false;
    public static boolean checkPaytm=false;
    public static String Api = "http://api.mysmartprice.com/staging/search.php?query=";
    public static String second_api = "http://api.mysmartprice.com/service/single/item.php?id=";
    public static int countApi =0;
    public static ArrayList<String> store_name = new ArrayList<>();
    public static ArrayList<Integer> price_name = new ArrayList<>();
    public static ArrayList<String> cat_name = new ArrayList<>();

    public static boolean checkPlay =false;
    public static String str =null;
    public static String checkstr =null;
    public static String checkPrcAmazon =null;
    public static Boolean isFindTaskRunning =false;

    public static boolean counter_check=false;

    public static String getNameString=null;
    public static Integer getPrice=0;

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
