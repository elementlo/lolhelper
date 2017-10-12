package com.demo.lolhelper.task;

import java.io.File;

import android.content.Context;
import android.os.AsyncTask;

public class ImageDownload extends AsyncTask<Object, Object, Object> {
	
	public final static String IMAGE = "http://ossweb-img.qq.com/images/lol/img/champion/";
	public final static String SKILL = "http://ossweb-img.qq.com/images/lol/img/spell/";
	public final static String PASSIVE = "http://ossweb-img.qq.com/images/lol/img/passive/";
	
	private Context mContext;
	private String mURL;

	public ImageDownload(Context context, String url) {
		mContext = context;
		mURL = url;
	}

	protected Object doInBackground(Object... params) {
		File fileImg = new File((String)params[0]);
		if(fileImg.exists()) {
			if(fileImg.length() > 1)
				return null;
		}
		if(utils.NetworkUtils.isActiveNetwork(mContext)) {
			utils.HttpUtils.download(mContext, mURL + fileImg.getName(), "GET", fileImg.getAbsolutePath(), new byte[1024*8], 2);
		}
		return method();
	}

	public Object method() {
		return null;
	}

}
