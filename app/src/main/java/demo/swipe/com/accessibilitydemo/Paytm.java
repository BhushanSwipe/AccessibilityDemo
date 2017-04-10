package demo.swipe.com.accessibilitydemo;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by USer on 26-10-2016.
 */

public class Paytm {


    public static String getProductNameL2(AccessibilityNodeInfo source) {
        String productName = null;
        int childCount = source.getChildCount();
        int i = 0;
        while (i < childCount) {
//            Log.e("L1", " txt="+source.getChild(i).getText()+" ,des="+source.getChild(i).getContentDescription()+" ,id="+source.getChild(i).getViewIdResourceName());
            if (source.getChild(i) != null && source.getChild(i).getText() != null) {
                Log.e("L1", " txt=" + source.getChild(i).getText());
            }
            if (source.getChild(i) == null || source.getChild(i).getChildCount() <= 0) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/product_name");
                if (findAccessibilityNodeInfosByViewId.size() > 0) {
                    productName = ((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(0)).getText().toString();
//                    Log.e("L2", productName);
                }
                i++;
            } else {
                productName = getProductNameL2(source.getChild(i));
//                Log.e("L2", productName +" "+BuildConfig.VERSION_NAME);
                return productName;
            }
        }
        return productName;
    }

    public static boolean getPaytm(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        List findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/product_name");
        if (findAccessibilityNodeInfosByViewId.size() > 0) {
            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(0);
            if (!(accessibilityNodeInfo2 == null || accessibilityNodeInfo2.getText() == null)) {
                String str;
                String charSequence = accessibilityNodeInfo2.getText().toString();
                charSequence.replace(" ", BuildConfig.FLAVOR);
                findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/actual_price");
                String charSequence2 = findAccessibilityNodeInfosByViewId.size() > 0 ? ((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(0)).getText().toString() : null;
                findAccessibilityNodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/discounted_price");
                String charSequence3 = findAccessibilityNodeInfosByViewId.size() > 0 ? ((AccessibilityNodeInfo) findAccessibilityNodeInfosByViewId.get(0)).getText().toString() : null;

                if (charSequence != null && charSequence3 != null) {
                    MyApplication.getNameString = charSequence;
                    MyApplication.getPrice = Integer.parseInt(charSequence3.replace("₹ ", "").replace(",", "").replace(" ", "").trim());

                    Log.e("Chari1", "" + charSequence + "-->" + charSequence2 + "-->" + charSequence3);

                }
                Log.e("Chari2", "" + charSequence + "-->" + charSequence2 + "-->" + charSequence3);

                if (charSequence2 == null) {
                    str = charSequence3;
                } else if (charSequence3 == null) {
                    charSequence3 = charSequence2;
                    str = charSequence2;
                } else {
                    str = charSequence2;
                }
                String str2 = BuildConfig.FLAVOR;
                try {
                    str2 = ((AccessibilityNodeInfo) accessibilityNodeInfo.findAccessibilityNodeInfosByText("Product Code").get(0)).getParent().getChild(2).getText().toString();
                    Log.e("Chari2", "" + str2);
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    public static boolean getPaytmVerify(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent != null && ("net.one97.paytm.AJRProductDetail".equals(accessibilityEvent.getClassName()) || "android.support.v4.view.ViewPager".equals(accessibilityEvent.getClassName()))) {
            AccessibilityNodeInfo source = accessibilityEvent.getSource();
            if (source != null) {
                List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/product_detail_scroll");
                if (findAccessibilityNodeInfosByViewId != null) {
                    for (AccessibilityNodeInfo source2 : findAccessibilityNodeInfosByViewId) {
                        if (source2 != null && source2.isVisibleToUser()) {
                            return getPaytm(source2);
                        }
                    }
                }
            }
        }
        return false;
    }
}
