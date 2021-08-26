package com.nisaefendioglu.google_maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity{
	private final int splashTime = 450;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
           Intent i = new Intent(SplashScreen.this,
           MapsActivity.class);
           startActivity(i);
           finish();
    },
                this.splashTime);
             
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}
