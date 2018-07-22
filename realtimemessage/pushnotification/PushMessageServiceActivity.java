package io.github.muhammadsafreza.untukbelajar.pushnotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import io.github.muhammadsafreza.untukbelajar.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PushMessageServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_message_service);
        FirebaseMessaging.getInstance().subscribeToTopic("sendmessage");
        FirebaseInstanceId.getInstance().getToken();
    }
}
