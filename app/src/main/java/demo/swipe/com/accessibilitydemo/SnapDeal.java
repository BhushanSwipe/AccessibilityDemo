package demo.swipe.com.accessibilitydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by USer on 25-10-2016.
 */

public class SnapDeal {
    static Context con;
    static String storetitle1;
    static int counter = 0;


    public SnapDeal(Context context) {

        con = context;
    }

    public static String getSnapDealName(AccessibilityNodeInfo source, Context context) {
        try {
            if (source.getPackageName().equals("com.snapdeal.main")) {
                List findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/ptitleView");
                if (findAccessibilityNodeInfosByViewId.size() > 0) {
                    for (int i = 0; i < findAccessibilityNodeInfosByViewId.size(); i++) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(i);
                        MyApplication.getNameString=accessibilityNodeInfo2.getText().toString();
                        Log.e("Snapname",""+accessibilityNodeInfo2.getText().toString());

                    }
                }
                if (source.getViewIdResourceName() != null) {
                    con = context;
                    //Log.e("SnapText", "" + source.getText() + "-->" + source.getViewIdResourceName() + "-->" + source.getClassName());


                    if (source.getViewIdResourceName().equals("com.snapdeal.main:id/ptitleView")) {
                        //Log.e("Found", "" + source.getText());
                        //Log.e("Tag dispaly icon", "display");
                        MyApplication.getNameString = source.getText().toString();
                        Log.e("Snapname2",""+source.getText().toString());

                        //Log.e("oldpro", "oldpro" + MyApplication.getNameString);



                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Log.e("snapdeal1", "snapdeal1" + e);
        }
        return MyApplication.getNameString;
    }

    public static Integer getSnapDealPrice(AccessibilityNodeInfo source, Context context) {
        try {
            if (source.getPackageName().equals("com.snapdeal.main")) {
                List findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/finalPriceTextView");
                if (findAccessibilityNodeInfosByViewId.size() > 0) {
                    for (int i = 0; i < findAccessibilityNodeInfosByViewId.size(); i++) {
                        AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(i);
                        MyApplication.getPrice = Integer.parseInt(accessibilityNodeInfo2.getText().toString().replace(",", "").replace("Rs. ", "").trim());

                        Log.e("Snapprice",""+accessibilityNodeInfo2.getText().toString());
                    }
                }

                if (source.getViewIdResourceName() != null) {
                    if (source.getViewIdResourceName().equals("com.snapdeal.main:id/finalPriceTextView")) {
                        con = context;
                        Log.e("Snapprice", "" + source.getText());
                        MyApplication.getPrice = Integer.parseInt(source.getText().toString().replace(",", "").replace("Rs. ", "").trim());
                        if (MyApplication.getNameString != null && MyApplication.getPrice != null) {
                            Log.e("name" + MyApplication.getNameString, "price" + MyApplication.getPrice);
                            }
                        }
                    }
                }

            //  return MyApplication.getPrice;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("snapdeal2", "snapdeal2" + e);
        }
        return MyApplication.getPrice;
    }

    private static boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) con.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}
