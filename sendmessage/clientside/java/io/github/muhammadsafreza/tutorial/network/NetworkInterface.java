package io.github.muhammadsafreza.tutorial.network;

import io.github.muhammadsafreza.tutorial.model.Notif;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("rest")
    Observable<Notif> getNotif();
}
