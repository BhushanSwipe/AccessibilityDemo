package demo.swipe.com.accessibilitydemo;


/**
 * Created by USer on 06-09-2016.
 */
public class DetectorLaunch implements Runnable {
    @Override
    public void run() {
       /* String[] activePackages;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            activePackages = getActivePackages();
        } else {
            activePackages = getActivePackagesCompat();
        }
        if (activePackages != null) {
            for (String activePackage : activePackages) {
                if (activePackage.equals("com.google.android.calendar")) {
                    //Calendar app is launched, do something
                }
            }
        }*/
        //mHandler.postDelayed(this, 1000);
    }

   /* String[] getActivePackagesCompat() {
        final List<ActivityManager.RunningTaskInfo> taskInfo = mActivityManager.getRunningTasks(1);
        final ComponentName componentName = taskInfo.get(0).topActivity;
        final String[] activePackages = new String[1];
        activePackages[0] = componentName.getPackageName();
        return activePackages;
    }

    String[] getActivePackages() {
        final Set<String> activePackages = new HashSet<String>();
        final List<ActivityManager.RunningAppProcessInfo> processInfos = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                activePackages.addAll(Arrays.asList(processInfo.pkgList));
            }
        }
        return activePackages.toArray(new String[activePackages.size()]);
    }*/
}
