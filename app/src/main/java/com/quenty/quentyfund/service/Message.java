package com.quenty.quentyfund.service;

import com.quenty.quentyfund.entity.Proyecto;

/**
 * Created by Diego on 24/10/15.
 */
public class Message {
    private boolean error;

    public Proyecto[] getMessage() {
        return message;
    }

    public void setMessage(Proyecto[] message) {
        this.message = message;
    }

    private Proyecto[] message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
