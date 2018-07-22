package io.github.muhammadsafreza.untukbelajar.pushnotification;


import android.annotation.SuppressLint;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static io.github.muhammadsafreza.untukbelajar.utils.CDM.TAG;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        getDataNetwork(token);
        Log.d(TAG, "onTokenRefresh: "+token);
    }

    private void getDataNetwork(String token) {
        getObservable(token).subscribeWith(getObserver());
    }

    public Observable<Notif> getObservable(String token) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .register(token)
                .subscribeOn(Schedulers.io()) //async
                .observeOn(AndroidSchedulers.mainThread());
    }

    //3
    public DisposableObserver<Notif> getObserver() {
        return new DisposableObserver<Notif>() {

            @Override
            public void onNext(Notif notif) {
                Log.d("disini", "onNext: sukses");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("disini", "onNext: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("disini", "onNext: complete");
            }
        };
    }
}
