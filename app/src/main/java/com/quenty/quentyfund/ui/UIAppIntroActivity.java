package com.quenty.quentyfund.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.quenty.quentyfund.R;

public class UIAppIntroActivity extends AppIntro {


    @Override
    public void init(Bundle bundle) {
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.step_title_one), getResources().getString(R.string.step_message_one), R.drawable.ic_registration, getResources().getColor(R.color.primary)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.step_title_two), getResources().getString(R.string.step_message_two), R.drawable.ic_be_conect, getResources().getColor(R.color.primary)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.step_title_three), getResources().getString(R.string.step_message_three), R.drawable.ic_donate, getResources().getColor(R.color.primary)));
        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.step_title_four), getResources().getString(R.string.step_message_four), R.drawable.ic_donate_push, getResources().getColor(R.color.primary)));
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        showSkipButton(true);
        showDoneButton(true);

        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        startActivity(new Intent(UIAppIntroActivity.this, UILoginActivity.class));
        UIAppIntroActivity.this.finish();
    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(UIAppIntroActivity.this, UILoginActivity.class));
        UIAppIntroActivity.this.finish();
    }
}
