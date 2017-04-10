package demo.swipe.com.accessibilitydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    int PERMISSION_ALL = 1;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    private static final int REQUEST_OVERLAY=33;
    private Button buttonAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAccess=(Button)findViewById(R.id.buttonAccess);


        buttonAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent();
                intent.setClassName("com.android.settings",
                        "com.android.settings.Settings");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT,
                        "the fragment which you want show");
                //intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT_ARGUMENTS,
                //        extras);
                startActivity(intent);*/
                startActivity(
                        new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            }
        });
        if (checkDrawOverlayPermission()) {
            startService(new Intent(MainActivity.this, MyAccessibilityService.class));
        }
        
        
        

    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Settings.canDrawOverlays(this)) {
                startService(new Intent(MainActivity.this, MyAccessibilityService.class));
            }
        }
    }
}
