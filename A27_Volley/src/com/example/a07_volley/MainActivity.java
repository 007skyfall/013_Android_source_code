package com.example.a07_volley;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getDatabyNet("http://www.weather.com.cn/data/sk/101010100.html");

	}

	private void JSONparse(String stra) {
		// 解析内容
		try {
			// 将字符串转换为json串
			JSONObject jsonObject = new JSONObject(stra);
			Object city = jsonObject.get("city");
			JSONObject data = jsonObject.getJSONObject("data");
			Object wendu = data.get("wendu");
			JSONObject yesterday = data.getJSONObject("yesterday");
			Object date = yesterday.get("date");
			JSONArray forecast = data.getJSONArray("forecast");
			JSONObject first = (JSONObject) forecast.get(0);
			Object fx = first.get("fx");

			Toast.makeText(getApplicationContext(),
					city + "\n" + wendu + "\n" + date + "\n" + fx, 1).show();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class MyRequest extends StringRequest {

		public MyRequest(int method, String url, Listener<String> listener,
				ErrorListener errorListener) {
			super(method, url, listener, errorListener);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected Response<String> parseNetworkResponse(NetworkResponse response) {
			String parsed;
			try {
				parsed = new String(response.data, "utf-8");
			} catch (UnsupportedEncodingException e) {
				parsed = new String(response.data);
			}
			return Response.success(parsed,
					HttpHeaderParser.parseCacheHeaders(response));

		}
	}

	private void getDatabyNet(String url) {
		RequestQueue instance = Volley.getInstance(this);
		MyRequest stringRequest = new MyRequest(Request.Method.GET, url,
				new Listener<String>() {

					public void onResponse(String response) {
						Log.i("Tag", response);
//						JSONparse(response);
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Log.i("Test", "=====" + arg0);
					}

				});
		instance.add(stringRequest);
	}
}
