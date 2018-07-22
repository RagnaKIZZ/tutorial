package io.github.muhammadsafreza.untukbelajar.pushnotification;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkInterface {


    @FormUrlEncoded
    @POST("register/index.php")
    Observable<Notif> register(@Field("token") String token);


}
