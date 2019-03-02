package com.dian.mycleandemo.data.source.remote.net.body;

import okhttp3.RequestBody;

/**
 * Created by Dian on 2017/10/23.
 */
public interface IRequest {
	String getRequestUrl();
	RequestBody getRequestBody();
}
