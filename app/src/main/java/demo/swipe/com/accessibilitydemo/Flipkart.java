package demo.swipe.com.accessibilitydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by USer on 30-09-2016.
 */

public class Flipkart {

    static Context context;

    public Flipkart(Context context){
        this.context=context;
    }


    public static void getFlipPrice(AccessibilityNodeInfo source) {
        List findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByText("₹");
        if (findAccessibilityNodeInfosByViewId.size() > 0) {
            for (int i = 0; i < findAccessibilityNodeInfosByViewId.size(); i++) {
                AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(i);
                if (accessibilityNodeInfo2.getText() != null && accessibilityNodeInfo2.getParent() != null) {
                    if (accessibilityNodeInfo2.getParent().getViewIdResourceName() != null && accessibilityNodeInfo2.getParent().getViewIdResourceName().equals("com.flipkart.android:id/price_layout")) {
                        int childCount = accessibilityNodeInfo2.getParent().getChildCount();
                        Log.e("Count", "" + childCount);
                        for (int k = 0; k < accessibilityNodeInfo2.getParent().getChildCount(); k++) {
                            if (accessibilityNodeInfo2.getParent().getChild(k).getText() != null) {
                                if (accessibilityNodeInfo2.getParent().getParent() != null && accessibilityNodeInfo2.getParent().getParent().getViewIdResourceName() != null && accessibilityNodeInfo2.getParent().getParent().getViewIdResourceName().equals("com.flipkart.android:id/product_summary_v2_1")) {
                                    if (accessibilityNodeInfo2.getParent().getParent().getParent() != null && accessibilityNodeInfo2.getParent().getParent().getParent().getViewIdResourceName() != null && accessibilityNodeInfo2.getParent().getParent().getParent().getViewIdResourceName().equals("com.flipkart.android:id/product_lifestyle_summary_holder_1")) {
                                        Log.e("Counter3", "" + accessibilityNodeInfo2.getParent().getChild(k).getText().toString() + ",Id= " + accessibilityNodeInfo2.getParent().getParent().getParent().getViewIdResourceName());
                                        if (accessibilityNodeInfo2.getParent().getChildCount() > 0 && accessibilityNodeInfo2.getParent().getChild(0) != null && accessibilityNodeInfo2.getParent().getChild(1) != null && accessibilityNodeInfo2.getParent().getChild(1).getText() != null) {
                                            MyApplication.getPrice = Integer.parseInt(accessibilityNodeInfo2.getParent().getChild(1).getText().toString().replace(",", ""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }




    public static void getProductName(AccessibilityNodeInfo source, int level) {
        if (source != null) {
            if(source.getClassName()!=null && source.getClassName().equals("android.widget.TextView") && source.getViewIdResourceName()==null && source.getParent()!=null && source.getParent().getClassName()!=null && source.getParent().getClassName().equals("android.widget.FrameLayout") && source.getParent().getViewIdResourceName()!=null && source.getParent().getViewIdResourceName().equals("com.flipkart.android:id/title_layout")){
                if(source.isVisibleToUser() && source.getText()!=null){
                    Log.e("FlipSourceName", "Vis "+source.getText() +" "+ BuildConfig.VERSION_NAME+" "+source.getViewIdResourceName()+" "+source.getParent().getViewIdResourceName());
                    MyApplication.getNameString=source.getText().toString();
                }
            }

            int childCount = source.getChildCount();
            for (int i = 0; i < childCount; i++) {
                getProductName(source.getChild(i), level + 1);
            }
        }

    }
}
