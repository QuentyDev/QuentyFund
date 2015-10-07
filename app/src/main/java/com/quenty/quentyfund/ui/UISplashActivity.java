package com.quenty.quentyfund.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quenty.quentyfund.R;

public class UISplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uisplash);
        SplashThreadLoadConfiguration splashThreadLoadConfiguration = new SplashThreadLoadConfiguration();
        splashThreadLoadConfiguration.execute();
    }

    /**
     * This class allow to start a new activity with app Intro
     */
    class SplashThreadLoadConfiguration extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... f_url) {
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 2000) {

            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String file_url) {
            startActivity(new Intent(UISplashActivity.this, UIAppIntroActivity.class));
            UISplashActivity.this.finish();
        }
    }

}
