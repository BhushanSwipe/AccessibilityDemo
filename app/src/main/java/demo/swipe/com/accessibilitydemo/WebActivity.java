package demo.swipe.com.accessibilitydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import im.delight.android.webview.AdvancedWebView;


public class WebActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    AdvancedWebView mWebView;

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("OnResume","WebActivity");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_web);

        Log.e("OnCreate","WebActivity");

        if (isMyServiceRunning(ChatHeadService.class)) {
            // MyApplication.oldNameString = null;
            this.getApplicationContext().stopService(new Intent(WebActivity.this, ChatHeadService.class));
        }
        ActionBar actionBar=this.getSupportActionBar();
      /*  if (MyApplication.store_URL != null) {
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(MyApplication.store_URL));

// Starts Implicit Activity
            startActivity(i);
        }*/

        actionBar.setDisplayHomeAsUpEnabled(true);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(WebActivity.this, WebActivity.this);
        mWebView.loadUrl(MyApplication.store_URL);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        if (!isMyServiceRunning(ChatHeadService.class)) {
            this.startService(new Intent(WebActivity.this, ChatHeadService.class));
            Log.e("ChatHeadService","Started from WebActivity");

        }

    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                if (!isMyServiceRunning(ChatHeadService.class)) {
                    this.startService(new Intent(WebActivity.this, ChatHeadService.class));
                    Log.e("ChatHeadService","Started from WebActivity");

                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
