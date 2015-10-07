package com.quenty.quentyfund.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.Set;

public class UILoginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ServerAuthCodeCallbacks {

    //    Google+
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 0;
    private boolean banActive = false;
//    private static final String WEB_CLIENT_ID = "844991406801-psilhe0c6rqgp1urvjnkp22eusthirli.apps.googleusercontent.com";

    // Base URL for your token exchange server, no trailing slash.
//    private static final String SERVER_BASE_URL = "SERVER_BASE_URL";
    //    Facebook
    LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;


    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
//            AccessToken accessToken = loginResult.getAccessToken();
//            Profile profile = Profile.getCurrentProfile();
//            if (profile != null) {
//                Log.i("profile", profile.getName());
////                BLLUser bllUser = new BLLUser(getBaseContext());
////                bllUser.insert(profile.getFirstName(), profile.getLastName(), profile.getId());
////                startActivity(new Intent(UILoginActivity.this, UIWelcomeActivity.class));
////                UILoginActivity.this.finish();
////            displayMessage(profile);
//            }
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code
                            Log.v("LoginActivity", response.toString());
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions("public_profile, email, user_birthday, user_friends");
//        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, callback);
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                if (newProfile != null) {
                    Log.i("newProfile", newProfile.toString());
                    BLLUser bllUser = new BLLUser(getBaseContext());
                    bllUser.insert(newProfile.getFirstName(), newProfile.getLastName(), newProfile.getId());
                    startActivity(new Intent(UILoginActivity.this, UIWelcomeActivity.class));
                    UILoginActivity.this.finish();
                }
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGoogleApiClient.connect();

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
//            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mGoogleApiClient.isConnected()) {

//            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);

//            mGoogleApiClient.disconnect();
//            mGoogleApiClient.connect();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            Log.i("Resumen", profile.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        banActive = true;
        Log.i("Conectado", " - ");
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi
                    .getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            String personPhotoUrl = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            Log.i("Google +", personName + " - " + email);
            BLLUser bllUser = new BLLUser(getBaseContext());
            bllUser.insert(personName, "", email);
            startActivity(new Intent(UILoginActivity.this, UIWelcomeActivity.class));
            UILoginActivity.this.finish();

        }
//        Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
////        Person signedInUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
//        String userEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
//        Log.i("Google +", currentUser.getName() + " - " + userEmail);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("onConnectionSuspended", " - ");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("onConnectionFailed", " - " + connectionResult.toString());
        if (banActive) {
            try {
                startIntentSenderForResult(connectionResult.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
            banActive = false;
        }

    }

    @Override
    public CheckResult onCheckServerAuthorization(String s, Set<Scope> set) {
        Log.i("onCheckServerAuthorization", " - ");
        return null;
    }

    @Override
    public boolean onUploadServerAuthCode(String s, String s1) {
        Log.i("onUploadServerAuthCode", " - ");
        return false;
    }
}
