package com.dian.mycleandemo.data.source.remote.net.request;

import com.dian.mycleandemo.data.source.remote.net.body.Request;
import com.dian.mycleandemo.presentation.util.L;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;

/**
 * Created by Dian on 2018/11/27.
 */

public class LoginRequest extends Request {

    private String account;
    private String password;

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public RequestBody getRequestBody() {
        String bo = "$data=" + getRequestUrl();
        L.d(bo);
        return RequestBody.create(FORM_CONTENT_TYPE, bo);
    }

    @Override
    public JsonObject createRequestBody() {
        JsonObject jo = new JsonObject();
        jo.addProperty("account", account);
        jo.addProperty("password", password);
        return jo;
    }
}
