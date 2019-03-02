package com.dian.mycleandemo.data.source.remote.net.converter;


import com.dian.mycleandemo.presentation.util.L;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by XTER on 2017/7/3.
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

	private final Gson gson;
	private final TypeAdapter<T> adapter;

	public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		String json = value.string();
		L.d(json);
		try {
			return adapter.fromJson(json);
		} finally {
			value.close();
		}
	}
}
