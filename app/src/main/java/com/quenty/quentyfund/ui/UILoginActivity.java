package com.quenty.quentyfund.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.quenty.quentyfund.R;
import com.quenty.quentyfund.bll.BLLUser;
import com.quenty.quentyfund.entity.User;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Set;

public class UILoginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 0;
    private ProgressDialog progressDialog;
    private GoogleApiClient plusClient;
    private CallbackManager callbackManager;
    private boolean mIntentInProgress;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        BLLUser bllUser = new BLLUser(getBaseContext());
        User user = bllUser.get();
        if (!user.getFirstName().equals("")) {
            startActivity(new Intent(UILoginActivity.this, UIMainActivity.class));
            this.finish();
        }
        plusClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();


        if (plusClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(plusClient);
            plusClient.disconnect();
            plusClient.connect();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Conectando...");

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions("public_profile, email, user_birthday, user_friends");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
//                            TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//                            String mPhoneNumber = tMgr.getLine1Number();

                            String id = object.getString("id");
                            String personName = object.getString("name");
                            System.out.println(personName + "AQUI entro");
//                            User user = new User();
//                            user.setId(0L);
//                            user.setGcm_id(id);
//                            user.setNroTelefono(mPhoneNumber);
//                            user.setNombres(personName);
//                            user.setId_facebook(id);
//                            BLLUser bllUser = new BLLUser(getBaseContext());
//                            bllUser.insert(newProfile.getFirstName(), newProfile.getLastName(), newProfile.getId());
                            startActivity(new Intent(UILoginActivity.this, UIWelcomeActivity.class));
                            UILoginActivity.this.finish();

                        } catch (Exception e) {
                            LoginManager.getInstance().logOut();
//                            new Utils(LoginActivity.this).writeUser(null);
                            e.printStackTrace();
                        }
                    }
                }).executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("Facebook", "Login cancelado");
            }

            @Override
            public void onError(FacebookException exception) {
                exception.printStackTrace();
            }
        });


//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginManager.getInstance().logInWithReadPermissions(UILoginActivity.this, Arrays.asList("public_profile, email, user_birthday, user_friends"));
//            }
//        });


        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusClient.connect();
                progressDialog.show();
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mIntentInProgress = false;
        }
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onConnected(Bundle bundle) {

        progressDialog.dismiss();
        if (Plus.PeopleApi.getCurrentPerson(plusClient) != null) {
            TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();

            Person currentPerson = Plus.PeopleApi.getCurrentPerson(plusClient);
            String personName = currentPerson.getDisplayName();
            String personPhoto = currentPerson.getImage().getUrl();
            String personId = currentPerson.getId();
            String personEmail = currentPerson.getUrl();

//            User user = new User();
//            user.setId(0L);
//            user.setGcm_id(personId);
//            user.setEmail(personEmail);
//            user.setNroTelefono(mPhoneNumber);
//            user.setNombres(personName);
//            user.setId_google(personPhoto);
//            BLLUser bllUser = new BLLUser(getBaseContext());
//            bllUser.insert(personName, "", email);
            startActivity(new Intent(UILoginActivity.this, UIWelcomeActivity.class));
            UILoginActivity.this.finish();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("onConnectionSuspended", " - ");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            progressDialog.dismiss();
            if (!mIntentInProgress && connectionResult.hasResolution()) {
                try {
                    mIntentInProgress = true;
                    startIntentSenderForResult(connectionResult.getResolution().getIntentSender(),
                            RC_SIGN_IN, null, 0, 0, 0);
                } catch (IntentSender.SendIntentException e) {
                    mIntentInProgress = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
