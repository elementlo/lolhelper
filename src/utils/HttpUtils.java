package utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;

public final class HttpUtils {

	private HttpUtils() {}

	private static InputStream initialization(String url, String method) {
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
			httpURLConnection.setRequestMethod(method);
			httpURLConnection.setConnectTimeout(2000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			if(method.equals("POST"))
				httpURLConnection.setUseCaches(false);
			httpURLConnection.connect();
			return httpURLConnection.getInputStream();
		} catch(Exception exception) {
			return null;
		}
	}

	private static InputStream initialization(String url, String method, String parameter) {
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
			httpURLConnection.setRequestMethod(method);
			httpURLConnection.setConnectTimeout(2000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			if(method.equals("POST"))
				httpURLConnection.setUseCaches(false);
			httpURLConnection.connect();
			httpURLConnection.getOutputStream().write(parameter.getBytes());
			httpURLConnection.getOutputStream().close();
			return httpURLConnection.getInputStream();
		} catch(Exception exception) {
			return null;
		}
	}

	public static InputStream toStream(String url, String method, int time) {
		InputStream inputStream = null;
		if(time == 0)
			while(inputStream == null) {
				inputStream = initialization(url, method);
			}
		else
			for(int i = 0 ; i < time; i++) {
				inputStream = initialization(url, method);
				if (inputStream != null)
					break;
			}
		return inputStream;
	}

	public static InputStream toStream(String url, String method, String parameter, int time) {
		InputStream inputStream = null;
		if(time == 0)
			while(inputStream == null) {
				inputStream = initialization(url, method, parameter);
			}
		else
			for(int i = 0 ; i < time; i++) {
				inputStream = initialization(url, method, parameter);
				if (inputStream != null)
					break;
			}
		return inputStream;
	}

	public static String toString(String url, String method, String charset, int time) {
		String string = null;
		if(time == 0)
			while(string == null) {
				string = StreamUtils.toString(initialization(url, method), charset);
			}
		else
			for(int i = 0 ; i < time; i++) {
				string = StreamUtils.toString(initialization(url, method), charset);
				if (string != null)
					break;
			}
		return string;
	}

	public static String toString(String url, String method, String charset, char[] buffer, int time) {
		String string = null;
		if(time == 0)
			while(string == null) {
				string = StreamUtils.toString(initialization(url, method), charset, buffer);
			}
		else
			for(int i = 0 ; i < time; i++) {
				string = StreamUtils.toString(initialization(url, method), charset, buffer);
				if (string != null)
					break;
			}
		return string;
	}

	public static String toString(String url, String method, String parameter, String charset, int time) {
		String string = null;
		if(time == 0)
			while(string == null) {
				string = StreamUtils.toString(initialization(url, method, parameter), charset);
			}
		else
			for(int i = 0 ; i < time; i++) {
				string = StreamUtils.toString(initialization(url, method, parameter), charset);
				if (string != null)
					break;
			}
		return string;
	}

	public static String toString(String url, String method, String parameter, String charset, char[] buffer, int time) {
		String string = null;
		if(time == 0)
			while(string == null) {
				string = StreamUtils.toString(initialization(url, method, parameter), charset, buffer);
			}
		else
			for(int i = 0 ; i < time; i++) {
				string = StreamUtils.toString(initialization(url, method, parameter), charset, buffer);
				if (string != null)
					break;
			}
		return string;
	}

	public static boolean download(Context context, String url, String method, String path, int time) {
		boolean flag = false;
		if(time == 0)
			while(!flag) {
				flag = StreamUtils.output(context, path, initialization(url, method));
			}
		else
			for(int i = 0 ; i < time; i++) {
				flag = StreamUtils.output(context, path, initialization(url, method));
				if (!flag)
					break;
			}
		return flag;
	}

	public static boolean download(Context context, String url, String method, String path, byte[] buffer, int time) {
		boolean flag = false;
		if(time == 0)
			while(!flag) {
				flag = StreamUtils.output(context, path, initialization(url, method), buffer);
			}
		else
			for(int i = 0 ; i < time; i++) {
				flag = StreamUtils.output(context, path, initialization(url, method), buffer);
				if (!flag)
					break;
			}
		return flag;
	}

	public static boolean download(Context context, String url, String method, String parameter, String path, int time) {
		boolean flag = false;
		if(time == 0)
			while(!flag) {
				flag = StreamUtils.output(context, path, initialization(url, method, parameter));
			}
		else
			for(int i = 0 ; i < time; i++) {
				flag = StreamUtils.output(context, path, initialization(url, method, parameter));
				if (!flag)
					break;
			}
		return flag;
	}

	public static boolean download(Context context, String url, String method, String parameter, String path, byte[] buffer, int time) {
		boolean flag = false;
		if(time == 0)
			while(!flag) {
				flag = StreamUtils.output(context, path, initialization(url, method, parameter), buffer);
			}
		else
			for(int i = 0 ; i < time; i++) {
				flag = StreamUtils.output(context, path, initialization(url, method, parameter), buffer);
				if (!flag)
					break;
			}
		return flag;
	}

}

