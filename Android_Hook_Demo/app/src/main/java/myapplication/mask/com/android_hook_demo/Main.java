package myapplication.mask.com.android_hook_demo;
import android.util.Log;import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by mask on 2017/3/4.
 */

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam)throws Throwable{

        Log.i("XposedDemo", "Call handleLoadPackage");
        if (loadPackageParam.packageName.equals("myapplication.mask.com.android_hook_demo")){
            Log.i("XposedDemo", "Enter " + loadPackageParam.packageName);
           Class clazz = loadPackageParam.classLoader.loadClass("myapplication.mask.com.android_hook_demo.MainActivity");
            XposedHelpers.findAndHookMethod(clazz, "toastMessage", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);

                }
                @Override
                protected void afterHookedMethod(MethodHookParam param)throws Throwable{

                    param.setResult("Hooked");

                }
            });
        }
    }
}



