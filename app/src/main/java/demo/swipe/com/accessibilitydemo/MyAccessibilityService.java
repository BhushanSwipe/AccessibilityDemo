package demo.swipe.com.accessibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import java.util.List;

import static demo.swipe.com.accessibilitydemo.Amazon.*;


/**
 * Created by USer on 06-09-2016.
 */
public class MyAccessibilityService extends AccessibilityService {

    Context context;
    public FindTask findTask=null;
    //public FindTask findTask=new FindTask(MyAccessibilityService.this);

    public MyAccessibilityService() {
        this.context = this;
    }




    //Previous
    WindowManager windowManager;
    WindowManager.LayoutParams params;
    private NewView chatImage;



    @SuppressLint("RtlHardcoded")
    @Override
    public void onCreate() {
        super.onCreate();



        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatImage = new NewView(this);

        params = new WindowManager.LayoutParams(
                160, 160, WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 10;
        params.y = 200;


        //Adding Text

        windowManager.addView(chatImage, params);

        chatImage.setVisibility(View.GONE);


        chatImage.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        //return true;
//                    case MotionEvent.ACTION_UP:
//                        return false;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatImage, params);
                        //return true;
                }
                return false;
            }
        });
        chatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        //if(MyApplication.getNameString!=null && MyApplication.getPrice!=0) {
                        Toast.makeText(MyAccessibilityService.this, "" + MyApplication.getNameString + " , " + MyApplication.getPrice, Toast.LENGTH_SHORT).show();
                        //}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");

    }



    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        AccessibilityNodeInfo source = event.getSource();
        if (source == null) {
            return;
        }
        if (source.getClassName() == null) {
            return;
        }

      Log.e("EventClass", source.getText()+"-->"+event.getClassName() + "-->" + source.getViewIdResourceName());

/*        Log.e("EventChecker", "" + event.toString());
        Log.e("ContentChecker", "" + event.getContentDescription());
        Log.e("Textchecker", source.getText() + "-->" + source.toString() + "-->" + source.getParent());
        Log.e("Sourcechecker", source.toString() + "");
        Log.e("ClassChecker", "" + event.getClassName() + "-->" + source.getViewIdResourceName());
        Log.e("EventClass", source.getText() + "-->" + event.getClassName() + "-->" + source.getViewIdResourceName() + "-->" + source.getContentDescription());*/

        final int eventType = event.getEventType();
        String eventText = null;
        switch(eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "Clicked: ";
                MyApplication.isFindTaskRunning=false;
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "Focused: ";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventText = "TextChanged: ";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                eventText = "Selected: ";
                break;

        }

        eventText = eventText +" "+ event.toString();

        Log.i("EventText",""+eventText);

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED: {
                if (MyApplication.check == true) {
                    MyApplication.check = false;
                }

            }
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED: {
                if (MyApplication.checkPlay == true) {
                    MyApplication.checkPlay = false;
                }
                if (MyApplication.check == true) {
                    MyApplication.check = false;
                }

            }

            case AccessibilityEvent.TYPE_VIEW_CLICKED: {
                //Log.e("Viewtype",""+source.getViewIdResourceName());
            }
        }
        //For Flipkart
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED: {
                if (MyApplication.check == true) {
                    MyApplication.check = false;
                }
            }
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED: {
                if (MyApplication.checkPlay == true) {
                    MyApplication.checkPlay = false;
                }
                if (MyApplication.check == true) {
                    MyApplication.check = false;
                }
            }

        }
        if (source != null) {
            //For Flipkart
            if (source.getPackageName().equals("com.flipkart.android")) {
                if (source != null) {

                    Flipkart.getFlipPrice(source);
                    Flipkart.getProductName(source, 1);


                    if (source.getViewIdResourceName() != null) {

                        if(source.getViewIdResourceName().equals("com.flipkart.android:id/pmu_product_parent_layout")){
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","pmu_parent layout");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                            }
                        }
                        if (source.getViewIdResourceName().equals("com.flipkart.android:id/product_page_recyclerview")) {
//                            chatImage.setVisibility(View.VISIBLE);


                        } else {

                            /*if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","PDP else");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                            }*/

                            //Log.e("Service","Stopped");
//                            chatImage.setVisibility(View.GONE);
                            /*if (isMyServiceRunning(ChatHeadService.class)) {
                                // MyApplication.oldNameString = null;
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));

                            }*/
                        }
                    }


                        List findAccessibilityNodeInfosByViewIdReact = source.findAccessibilityNodeInfosByViewId("com.flipkart.android:id/react_frame_layout");
                        if (findAccessibilityNodeInfosByViewIdReact.size() > 0) {
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","React_Flipkart");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                if(findTask!=null){
                                    findTask.cancel(true);
                                }

                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));

                            }
                        }

                        List findAccessibilityNodeInfosByViewIdFooter = source.findAccessibilityNodeInfosByViewId("com.flipkart.android:id/footer_container");
                        if (findAccessibilityNodeInfosByViewIdFooter.size() > 0) {

                            //Type 1
                            if(MyApplication.getNameString!=null && MyApplication.getPrice!=0){
                                Log.e("Detected","Footer_Flipkart "+MyApplication.isFindTaskRunning);
                                /*if(!MyApplication.isFindTaskRunning){
                                    context.startService(new Intent(MyAccessibilityService.this, ChatHeadService.class));

                                }*/

                                //Type 2

                                if(!MyApplication.isFindTaskRunning){
                                    Log.e("started","Findtask_Flipkart");
                                    if(isConnected(context)){
                                        findTask=new FindTask(MyAccessibilityService.this);
                                        findTask.execute(Constants.findUrl + MyApplication.getPrice+Constants.productTitle+MyApplication.getNameString.replace("...more","")+Constants.storeTitle+"flipkart");
                                    }
                                }

                            }

                        }


                        /*List findAccessibilityNodeInfosByViewIdContent = source.findAccessibilityNodeInfosByViewId("com.flipkart.android:id/contentFragment");
                        if (findAccessibilityNodeInfosByViewIdContent.size() > 0) {
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","Content_Flipkart");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                            }

                        }*/

                        List findAccessibilityNodeInfosByViewIdHomeRecy = source.findAccessibilityNodeInfosByViewId("com.flipkart.android:id/home_recycler_view");
                        if (findAccessibilityNodeInfosByViewIdHomeRecy.size() > 0) {
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","HomeRecycler_Flipkart");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                                Log.e("Detected","HomeRecycler");
                            }
                        }


                        //Code for Elite Plus 5.0 Accessibility

                    //com.flipkart.android:id/title_action_bar
                    //com.flipkart.android:id/contentFragment
                    //com.flipkart.android:id/home_recycler_view
                    //com.flipkart.android:id/react_frame_layout

                    if(source.getViewIdResourceName()!=null){
                        if(source.getViewIdResourceName().equals("com.flipkart.android:id/title_action_bar")){
                            if (source.getText() != null) {

                                if (isMyServiceRunning(ChatHeadService.class)) {
                                    Log.e("Detected","title_action_bar");
                                    MyApplication.getNameString=null;
                                    MyApplication.getPrice=0;
                                    MyApplication.isFindTaskRunning=false;
                                    Log.e("isFindTaskRunning","false");

                                    if(findTask!=null){
                                        findTask.cancel(true);
                                    }
                                    context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                                    Log.e("Detected","title_action_bar");
                                }
                            }

                        }

                        /*if(source.getViewIdResourceName().equals("com.flipkart.android:id/contentFragment")){
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","HomeRecycler_Flipkart");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                                Log.e("Detected","HomeRecycler");
                            }
                        }*/

                        if(source.getViewIdResourceName().equals("com.flipkart.android:id/home_recycler_view")){
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","home_recycler_view");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                                Log.e("Detected","home_recycler_view");
                            }
                        }

                        if(source.getViewIdResourceName().equals("com.flipkart.android:id/react_frame_layout")){
                            if (isMyServiceRunning(ChatHeadService.class)) {
                                Log.e("Detected","react_frame_layout");
                                MyApplication.getNameString=null;
                                MyApplication.getPrice=0;
                                MyApplication.isFindTaskRunning=false;
                                Log.e("isFindTaskRunning","false");

                                if(findTask!=null){
                                    findTask.cancel(true);
                                }
                                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                                Log.e("Detected","react_frame_layout");
                            }
                        }

                        if(source.getViewIdResourceName().equals("com.flipkart.android:id/footer_container")){
                            if(MyApplication.getNameString!=null && MyApplication.getPrice!=0){
                                Log.e("Detected","footer_container "+MyApplication.isFindTaskRunning);
                                if(!MyApplication.isFindTaskRunning){
                                    Log.e("started","Findtask_Flipkart");
                                    if(isConnected(context)){
                                        findTask=new FindTask(MyAccessibilityService.this);
                                        findTask.execute(Constants.findUrl + MyApplication.getPrice+Constants.productTitle+MyApplication.getNameString.replace("...more","")+Constants.storeTitle+"flipkart");
                                    }
                                }

                            }
                        }
                    }

                    if (source.getClassName().equals("android.widget.TextView")) {
                        if (source.getViewIdResourceName() != null) {
                            if (source.getViewIdResourceName().equals("com.flipkart.android:id/product_addtocart_1")) {

//                                chatImage.setVisibility(View.VISIBLE);
                                //context.startService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                            }
                            if (source.getViewIdResourceName().equals("com.flipkart.android:id/product_buynow_1")) {
//                                chatImage.setVisibility(View.VISIBLE);
                            }
                            if (source.getViewIdResourceName().equals("com.flipkart.android:id/search_widget_textbox")) {
//                                chatImage.setVisibility(View.GONE);
                            }
                        }
                    }
                }


                if (source.getParent() != null && source.getParent().getViewIdResourceName() != null) {
                    if (source.getParent().getViewIdResourceName().equals("com.flipkart.android:id/price_layout")) {
                        if (source.getParent().isVisibleToUser()) {
                            MyApplication.flipPrice = Integer.parseInt(source.getParent().getChild(1).getText().toString().replace(",", ""));
                            Log.e("FlipPrice", "" + MyApplication.flipPrice);
                        }
                    }
                }


            }

        }
        //For Snapdeal
        if (source.getPackageName().equals("com.snapdeal.main")) {

            SnapDeal.getSnapDealName(source,context);
            SnapDeal.getSnapDealPrice(source,context);

            List findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/finalPriceTextView");
            if (findAccessibilityNodeInfosByViewId.size() > 0) {
                Log.e("SnapDeal","Enabled");
//                chatImage.setVisibility(View.VISIBLE);
                //Snapdeal PDP

                //Type 1
                if(MyApplication.getNameString!=null && MyApplication.getPrice!=0){

                    Log.e("Inside","NullCheck_Snapdeal");
//                    context.startService(new Intent(MyAccessibilityService.this, ChatHeadService.class));

                    //Type 2
                    if(!MyApplication.isFindTaskRunning){
                        if(isConnected(context)){
                            findTask=new FindTask(MyAccessibilityService.this);
                            findTask.execute(Constants.findUrl + MyApplication.getPrice+Constants.productTitle+MyApplication.getNameString.replace("...more","")+Constants.storeTitle+"snapdeal");
                        }
                    }
                }
            }

            /*List findAccessibilityNodeInfosByViewId2 = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/search_text_view");
            if (findAccessibilityNodeInfosByViewId2.size() > 0) {
                Log.e("SnapIcon","Disabled from search_text_view");
//                chatImage.setVisibility(View.GONE);
                MyApplication.getNameString=null;
                MyApplication.getPrice=0;
                if(findTask!=null){
                    findTask.cancel(true);
                }
                if (sharedpreferences.contains("FLAG")) {
                    SharedPreferences.Editor e = sharedpreferences.edit();
                    e.putString("FLAG", "FALSE");
                    e.apply();
                }


                    context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));



            }*/
            List findAccessibilityNodeInfosByViewId3 = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/filter_by_text_view");
            if (findAccessibilityNodeInfosByViewId3.size() > 0) {
                Log.e("SnapIcon","Disabled from filter_by_text_view Snapdeal");
//                chatImage.setVisibility(View.GONE);
                if (isMyServiceRunning(ChatHeadService.class)) {
                    Log.e("Detected","Content_Snapdeal");
                    MyApplication.getNameString=null;
                    MyApplication.getPrice=0;

                    MyApplication.isFindTaskRunning=false;
                    Log.e("isFindTaskRunning","false");


                    if(findTask!=null){
                        findTask.cancel(true);
                    }
                    context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                }




            }
            //com.snapdeal.main:id/plp_recyclerView

            List findAccessibilityNodeInfosByViewIdPlp = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/topOpenFilterContainer");
            if (findAccessibilityNodeInfosByViewIdPlp.size() > 0) {
                Log.e("SnapIcon","Disabled from topOpenFilterContainer Snapdeal");
//                chatImage.setVisibility(View.GONE);
                MyApplication.getNameString=null;
                MyApplication.getPrice=0;
                if(findTask!=null){
                    findTask.cancel(true);
                }

                MyApplication.isFindTaskRunning=false;
                Log.e("isFindTaskRunning","false");

                context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));

            }


            /*if (source.getViewIdResourceName() != null) {
                if (source.getViewIdResourceName().equals("com.snapdeal.main:id/search_text_view")) {
                    //chatImage.setVisibility(View.GONE);
                }

                if (source.getViewIdResourceName().equals("com.snapdeal.main:id/filter_by_text_view")) {
                    //chatImage.setVisibility(View.GONE);
                }

                if (source.getViewIdResourceName().equals("com.snapdeal.main:id/pdpBottomBtnsContainer")) {
                    //chatImage.setVisibility(View.VISIBLE);
                }
                if (source.getViewIdResourceName().equals("com.snapdeal.main:id/homeShopRecycleView") || source.getViewIdResourceName().equals("com.snapdeal.main:id/plp_recyclerView") || source.getViewIdResourceName().equals("com.snapdeal.main:id/csfSubcategoryRecycleViewNew")) {
                    //chatImage.setVisibility(View.GONE);
                }
            }*/
        }

        //For Paytm
        //Recent Task Handler for Paytm
        if (event.getPackageName() != null && event.getPackageName().equals("com.android.systemui")) {
            if (event.getClassName().equals("com.android.systemui.recents.RecentsActivity") || event.getClassName().equals("com.android.systemui.recents.views.TaskStackView")) {
                if (isMyServiceRunning(ChatHeadService.class)) {
                    context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                    if(findTask!=null){
                        findTask.cancel(true);
                    }

                }


//                chatImage.setVisibility(View.GONE);
                Log.e("icon", "Disabled from recent");
            }
        }
        //Home Handler for Paytm
        if (event.getPackageName() != null && event.getPackageName().equals("home.solo.launcher.free")) {
            if (event.getClassName().equals("home.solo.launcher.free.Launcher")) {
                //chatImage.setVisibility(View.GONE);

                if (isMyServiceRunning(ChatHeadService.class)) {
                    // MyApplication.oldNameString = null;
                    findTask.cancel(true);

                    context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));

                }

                Log.e("icon", "Disabled from launcher");
            }
        }



        //net.one97.paytm:id/product_name
            //Paytm Start
            if (source != null) {
                //Paytm.getProductNameL2(source);
                //Paytm.getPaytmVerify(event);
            }



            //Paytm Product Page Detection
        if (source.getPackageName().equals("net.one97.paytm")) {
            //Log.e("EventClass", "-->" + event.getClassName() + "-->" + source.getViewIdResourceName());

/*
            List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/product_detail_scroll");
            if (findAccessibilityNodeInfosByViewId.size()>0) {
                for (AccessibilityNodeInfo source2 : findAccessibilityNodeInfosByViewId) {
                    if (source2 != null && source2.isVisibleToUser()) {
                        List names=source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/product_name");

                        if(names.size()>0){
                            if(((AccessibilityNodeInfo) names.get(0)).isVisibleToUser()){
                                Log.e("PaytmName",""+((AccessibilityNodeInfo) names.get(0)).getText().toString()+" "+((AccessibilityNodeInfo) names.get(0)).isVisibleToUser());
                                MyApplication.getNameString=((AccessibilityNodeInfo) names.get(0)).getText().toString();

                            }
                        }

                        List price=source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/pdp_no_offer_price");

                        if(price.size()>0){
                            if(((AccessibilityNodeInfo) names.get(0)).isVisibleToUser()){
                                Log.e("PaytmPrice",""+((AccessibilityNodeInfo) price.get(0)).getText().toString()+" "+((AccessibilityNodeInfo) price.get(0)).isVisibleToUser());
                                MyApplication.getPrice=Integer.parseInt(((AccessibilityNodeInfo) price.get(0)).getText().toString().replace("Buy for ₹ ","").replace(",",""));
                            }

                        }
                    }
                }
            }
*/
            List names=source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/product_name");

            if(names.size()>0){
                if(((AccessibilityNodeInfo) names.get(0)).isVisibleToUser()){
                    Log.e("PaytmName",""+((AccessibilityNodeInfo) names.get(0)).getText().toString()+" "+((AccessibilityNodeInfo) names.get(0)).isVisibleToUser());
                    MyApplication.getNameString=((AccessibilityNodeInfo) names.get(0)).getText().toString();

                }
            }

            List price=source.findAccessibilityNodeInfosByViewId("net.one97.paytm:id/pdp_no_offer_price");

            if(price.size()>0){
                if(((AccessibilityNodeInfo) price.get(0)).isVisibleToUser()){
                    Log.e("PaytmPrice",""+((AccessibilityNodeInfo) price.get(0)).getText().toString()+" "+((AccessibilityNodeInfo) price.get(0)).isVisibleToUser());
                    MyApplication.getPrice=Integer.parseInt(((AccessibilityNodeInfo) price.get(0)).getText().toString().replace("Buy for ₹ ","").replace(",",""));

                    if(MyApplication.getNameString!=null && MyApplication.getPrice!=0){
                        if(!MyApplication.isFindTaskRunning){
                            findTask=new FindTask(MyAccessibilityService.this);
                            findTask.execute(Constants.findUrl + MyApplication.getPrice+Constants.productTitle+MyApplication.getNameString.replace("...more","")+Constants.storeTitle+"paytm");

                        }
                    }
                }

            }



            if (event.getClassName() != null) {
                if (event.getClassName().toString().contains("AJR")) {
                    if (event.getClassName().toString().equalsIgnoreCase("net.one97.paytm.AJRSecondaryHome")) {
                        //chatImage.setVisibility(View.GONE);
                        if (isMyServiceRunning(ChatHeadService.class)) {
                            Log.e("Detected","Content");

                            MyApplication.isFindTaskRunning=false;
                            Log.e("isFindTaskRunning","false");

                            MyApplication.getNameString=null;
                            MyApplication.getPrice=0;

                            if(findTask!=null){
                                findTask.cancel(true);
                            }
                            context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                        }

                    } else if (event.getClassName().toString().equalsIgnoreCase("net.one97.paytm.AJRGridPageContainer")) {
                        //chatImage.setVisibility(View.GONE);

                        if (isMyServiceRunning(ChatHeadService.class)) {
                            Log.e("Detected","AJRGridPageContainer");
                            MyApplication.getNameString=null;
                            MyApplication.getPrice=0;
                            MyApplication.isFindTaskRunning=false;
                            Log.e("isFindTaskRunning","false");

                            if(findTask!=null){
                                findTask.cancel(true);
                            }
                            context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                        }

                    } else if (event.getClassName().toString().equalsIgnoreCase("net.one97.paytm.landingpage.activity.AJRMainActivity")) {
                        //chatImage.setVisibility(View.GONE);

                        if (isMyServiceRunning(ChatHeadService.class)) {
                            Log.e("Detected","AJRMainActivity");
                            MyApplication.getNameString=null;
                            MyApplication.getPrice=0;
                            MyApplication.isFindTaskRunning=false;
                            Log.e("isFindTaskRunning","false");

                            if(findTask!=null){
                                findTask.cancel(true);
                            }
                            context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                        }

                    } else if (event.getClassName().toString().equalsIgnoreCase("net.one97.paytm.AJRProductDetail")) {
                         //Paytm PDP
//                        context.startService(new Intent(MyAccessibilityService.this, ChatHeadService.class));



                        /*if(MyApplication.getNameString!=null && MyApplication.getPrice!=0){
                            //Not Done
                            Log.e("NamePrice","Name="+MyApplication.getNameString+",Prc="+MyApplication.getPrice);


                            Log.e("Inside","NullCheck_PAYTM");
                            if(!MyApplication.isFindTaskRunning){
                                findTask=new FindTask(MyAccessibilityService.this);
                                findTask.execute(Constants.findUrl + MyApplication.getPrice+Constants.productTitle+MyApplication.getNameString.replace("...more","")+Constants.storeTitle+"paytm");

                            }

                        }*/

                    } else {
                        //chatImage.setVisibility(View.GONE);
                        if (isMyServiceRunning(ChatHeadService.class)) {
                            Log.e("Detected","Content");
                            MyApplication.getNameString=null;
                            MyApplication.getPrice=0;
                            MyApplication.isFindTaskRunning=false;
                            Log.e("isFindTaskRunning","false");

                            if(findTask!=null){
                                findTask.cancel(true);
                            }
                            context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                        }
                    }
                } else {
                }
            }

        }


        //Play Store
        PlayStore.getAppName(source);

        //Amazon
        if (source.getPackageName().equals("in.amazon.mShop.android.shopping")) {


            Amazon.checkProductDetailsPage(event);

            if (getProductNameAmazon(source, 3) != null) {
                MyApplication.getNameString = null;
                MyApplication.getNameString = getProductNameAmazon(source, 3);
            }


            if (event.getSource().getContentDescription() != null) {
                if (event.getSource().getContentDescription().equals("Product Details")) {
                    Log.e("Activity", "True");
                    checkAmazonFlag = true;
                    Log.e("AmazonDetails", MyApplication.getNameString + "-->" + MyApplication.getPrice);

                    if (getProductPriceAmazon(source, 1, "Pricer") != null && getProductPriceAmazon(source, 1, "Pricer") != "") {
                        MyApplication.getPrice = 0;
                        MyApplication.getPrice = Integer.parseInt(getProductPriceAmazon(source, 1, "Pricer").replace(",", "").replace(" ", "").replace("rupees", "").replace("₹", "").replace("s", "").replaceAll("\\s+", "").trim());
                    }
                }
            }
            if (checkAmazonFlag) {

                //Amazon PDP VISIBILITY ON

                //Type 1
                if(MyApplication.getNameString!=null && MyApplication.getPrice!=0){
                    //context.startService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                    /*                    if(MyApplication.countApi==0) {

                    }*/

                    if(!MyApplication.isFindTaskRunning){

                        if(isConnected(context)){
                            findTask=new FindTask(MyAccessibilityService.this);
                            findTask.execute(Constants.findUrl + MyApplication.getPrice+Constants.productTitle+MyApplication.getNameString.replace("...more","")+Constants.storeTitle+"amazon");
                        }

                    }


                }

            } else {
                //VISIBILITY OFF
                Amazon.arr.clear();

                MyApplication.isFindTaskRunning=false;
                Log.e("isFindTaskRunning","false");


                if (isMyServiceRunning(ChatHeadService.class)) {
                    findTask.cancel(true);
                    context.getApplicationContext().stopService(new Intent(MyAccessibilityService.this, ChatHeadService.class));
                }

            }
        }

    }


    @Override
    public void onInterrupt() {
        Log.e("onInterrupt", "Called");
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e("TAG", "onServiceConnected");
    }
}
