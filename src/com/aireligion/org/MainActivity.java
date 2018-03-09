package com.aireligion.org;

import android.os.Bundle;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.aireligion.org.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webView);
        //webView.setVerticalScrollBarEnabled(false);        // отключили прокрутку
        //webView.setHorizontalScrollBarEnabled(false);      // отключили прокрутку
        webView.getSettings().setJavaScriptEnabled(true);  // включили JavaScript
        //webView.getSettings().setDomStorageEnabled(true);  // включили localStorage и т.п.
        //webView.getSettings().setSupportZoom(false);       // отключили зум, т.к. нормальные приложения подобным функционалом не обладают
        //webView.getSettings().setSupportMultipleWindows(false);   // отключили поддержку вкладок.  
                                                             // Т.к. пользователь должен сидеть в SPA приложении
        //webView.addJavascriptInterface(new WebAppInterface(this), "API");   // прокидываем объект в JavaScript. 
        webView.getSettings().setLoadsImagesAutomatically(true);
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getScheme().equals("market")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        Activity host = (Activity) view.getContext();
                        host.startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException e) {
                        // Google Play app is not installed, you may want to open the app store link
                        Uri uri = Uri.parse(url);
                        view.loadUrl("");
                        return false;
                    }

                }
                return false;
            }
        });
        //webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://aireligion.org"); 
     
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
