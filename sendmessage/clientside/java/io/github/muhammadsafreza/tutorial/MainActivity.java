package io.github.muhammadsafreza.tutorial;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.github.muhammadsafreza.tutorial.model.Notif;
import io.github.muhammadsafreza.tutorial.network.NetworkClient;
import io.github.muhammadsafreza.tutorial.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataNetwork();
    }

    private void getNotif(String title, String messages){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messages)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.FLAG_AUTO_CANCEL);

        NotificationManager notificationManager =
                (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,mBuilder.build());
    }

    private void getDataNetwork(){
        getObservable().subscribeWith(getObserver());
    }

    public Observable<Notif> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getNotif()
                .subscribeOn(Schedulers.io()) //async
                .observeOn(AndroidSchedulers.mainThread());
    }

    //3
    public DisposableObserver<Notif> getObserver(){
        return new DisposableObserver<Notif>() {

            @Override
            public void onNext(Notif notif) {
                getNotif("Notif Server : "+notif.getIdNotif(),notif.getMessages());
                Log.d("disini", "onNext: "+notif.getMessages());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("disini", "onNext: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("disini", "onNext: complete");
            }
        };
    }
}
