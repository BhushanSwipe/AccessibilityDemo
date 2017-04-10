package demo.swipe.com.accessibilitydemo;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by USer on 25-10-2016.
 */

public class PlayStore {
    public static String getAppName(AccessibilityNodeInfo source){
        if (source.getPackageName().equals("com.android.vending")) {
            //com.android.vending:id/title_title
            if (source.getViewIdResourceName() != null) {
                if (source.getViewIdResourceName().equals("com.android.vending:id/title_title")) {
                    //   if(MyApplication.checkPlay==false) {
                    Log.e("Found", "" + source.getText());
                    MyApplication.checkstr = source.getText().toString();
                    MyApplication.checkPlay = true;
                    //   }
                }
            }
        }
        return MyApplication.checkstr;
    }
}
