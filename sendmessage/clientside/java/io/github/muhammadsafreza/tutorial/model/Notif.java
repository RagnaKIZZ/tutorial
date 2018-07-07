package io.github.muhammadsafreza.tutorial.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notif {

    @SerializedName("id_notif")
    @Expose
    private String idNotif;
    @SerializedName("messages")
    @Expose
    private String messages;

    public String getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(String idNotif) {
        this.idNotif = idNotif;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

}
