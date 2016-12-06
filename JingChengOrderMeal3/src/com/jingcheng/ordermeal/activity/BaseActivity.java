package com.jingcheng.ordermeal.activity;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		 window = getWindow();  
//	        WindowManager.LayoutParams params = window.getAttributes();  
//	        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;  
//	        window.setAttributes(params);  
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//		              | View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		hideBottomUIMenu();
//		setWindowStatusBarColor(this,getResources().getColor(R.color.barcolor));
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
	 /**
     * Òþ²ØÐéÄâ°´¼ü£¬²¢ÇÒÈ«ÆÁ
     */
    protected void hideBottomUIMenu() {
        //Òþ²ØÐéÄâ°´¼ü£¬²¢ÇÒÈ«ÆÁ
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    
//    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Window window = activity.getWindow();
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(activity.getResources().getColor(colorResId));
//
//                //µ×²¿µ¼º½À¸
//                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
