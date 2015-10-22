package com.quenty.quentyfund.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quenty.quentyfund.R;
import com.quenty.quentyfund.bll.BLLUser;
import com.quenty.quentyfund.entity.User;

public class UIWelcomeActivity extends AppCompatActivity {
    private TextView tvEmail;
    private TextView tvFullName;
    private Button btnYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiwelcome);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvFullName = (TextView) findViewById(R.id.tvFullName);
        btnYes = (Button) findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UIWelcomeActivity.this, UIMainActivity.class));
            }
        });
        BLLUser bllUser = new BLLUser(this);
        User user = bllUser.get();
        tvFullName.setText(user.getFirstName() + " " + user.getLastName());
        tvEmail.setText(user.getEmail());
    }

}
