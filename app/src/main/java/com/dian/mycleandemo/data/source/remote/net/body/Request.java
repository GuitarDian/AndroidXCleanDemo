package com.dian.mycleandemo.data.source.remote.net.body;

import com.google.gson.JsonObject;

import okhttp3.MediaType;

/**
 * Created by Dian on 2018/11/27.
 * 统一请求接口
 */

public abstract class Request implements IRequest {

    public static final MediaType FORM_CONTENT_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    public abstract JsonObject createRequestBody();

    @Override
    public String getRequestUrl() {
        JsonObject serviceContent = createRequestBody();

        JsonObject jsonObj = new JsonObject();
        jsonObj.add("serviceContent", serviceContent);

        return jsonObj.toString();
    }
}