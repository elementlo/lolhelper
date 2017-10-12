package com.demo.lolhelper.task;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.demo.lolhelper.MainActivity;
import com.demo.lolhelper.database.HeroesDatabasea;
import com.demo.lolhelper.object.Heroes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class DatabaseUpdate {

	private Context mContext;
	private Handler mHandler;
	private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(15, 1024, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	private Executor mExecutor = mThreadPoolExecutor;

	public DatabaseUpdate(Context context) {
		mContext = context;
	}

	public DatabaseUpdate(Context context, Handler handler) {
		mContext = context;
		mHandler = handler;
		start();
	}

	private void start() {
		new Thread(new Runnable() {

			public void run() {
				boolean flag = true;
				utils.SQLiteUtils db = new utils.SQLiteUtils();
				db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
		    	Cursor cursor = db.select("Log", new String[] {"time"}, "tag='更新' and text='英雄数据库'", "text", "MAX(time)", null, null);
		    	if(cursor.getCount() != 0) {
		    		cursor.moveToNext();
		    		if(System.currentTimeMillis() - Long.valueOf(cursor.getString(cursor.getColumnIndex("time"))) < 1000*60*60*24)
		    			flag = false;
		    	}
		    	db.close();
		    	if(flag) {
					String string = null;
					if(utils.NetworkUtils.isActiveNetwork(mContext)) {
						string = utils.HttpUtils.toString("http://ossweb-img.qq.com/upload/qqtalk/lol_hero/hero_list.js", "GET", "UTF-8", new char[1024*88], 3);
					}
					if(string != null) {
						HeroesDatabasea.insertHeroesInfo(mContext, string.replace("'", ""));
						HeroesDatabasea.insertLogInfo(mContext, new String[] {"更新", "英雄数据库", System.currentTimeMillis() + ""});
					}
		    	}
				ArrayList<Heroes> ArrayList = HeroesDatabasea.getHeroesList();
				if(ArrayList.size() > 0) {
					for(int i = 0; i < ArrayList.size(); i++) {
						mExecutor.execute(new Update(ArrayList.get(i).get_id(), System.currentTimeMillis()));
					}
				}
				while(true) {
					try {Thread.sleep(1000);} catch(Exception exception) {}
					if(mThreadPoolExecutor.getActiveCount() == 0) {
						Message message = new Message();
						message.obj = new String[] {"初始化任务结束"};
						mHandler.sendMessage(message);
						break;
					}
				}
			}

		}).start();
	}

	public class Update implements Runnable {

		private int _id;
		private long mTime;
		
		public Update(int id, long time) {
			_id = id;
			mTime = time;
		}

		public void run() {
			boolean flag = true;
			utils.SQLiteUtils db = new utils.SQLiteUtils();
			db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
	    	Cursor crsord = db.select("Heroes", new String[] {"update_time"}, "_id=" + _id, null, null, null, null);
	    	if(crsord.getCount() != 0) {
	    		crsord.moveToNext();
	    		if(crsord.getString(crsord.getColumnIndex("update_time")).equals("0")){
	    			flag = true;
	    		}
	    		else if(System.currentTimeMillis() - Long.valueOf(crsord.getString(crsord.getColumnIndex("update_time"))) < mTime){
	    			flag = false;
	    		}
	    	}
	    	db.close();
			if(flag) {
				String string = null;
				if(utils.NetworkUtils.isActiveNetwork(mContext)) {
					string = utils.HttpUtils.toString("http://ossweb-img.qq.com/upload/qqtalk/lol_hero/hero_" + _id + ".js", "GET", "UTF-8", new char[1024*8], 2);
				}
				if(string != null) {
					HeroesDatabasea.updateHeroesInfo(mContext, _id, string.replace("'", ""));
					HeroesDatabasea.insertLogInfo(mContext, new String[] {"更新", "英雄信息 id:" + _id, System.currentTimeMillis() + ""});
				}
			}
			final Heroes heroes = HeroesDatabasea.getHeroesInfo(_id);
			new ImageDownload(mContext, ImageDownload.IMAGE){
				public Object method() {
					String fileImg = MainActivity.mImagePath + "heroes/" + heroes.getEn_name() + ".png";
					utils.BitmapUtils.zoom(mContext, Bitmap.CompressFormat.PNG, fileImg, fileImg, 50, 50);
					return null;
				}
			}.executeOnExecutor(mExecutor, MainActivity.mImagePath + "heroes/" + heroes.getEn_name() + ".png");
			new ImageDownload(mContext, ImageDownload.PASSIVE){
				public Object method() {
					String fileImg = MainActivity.mImagePath + "skill1/" + heroes.getSkill1().subSequence(0, heroes.getSkill1().indexOf("|"));
					utils.BitmapUtils.zoom(mContext, Bitmap.CompressFormat.PNG, fileImg, fileImg, 50, 50);
					return null;
				}
			}.executeOnExecutor(mExecutor, MainActivity.mImagePath + "skill1/" + heroes.getSkill1().subSequence(0, heroes.getSkill1().indexOf("|")));
			new ImageDownload(mContext, ImageDownload.SKILL){
				public Object method() {
					String fileImg = MainActivity.mImagePath + "skill2/" + heroes.getSkill2().subSequence(0, heroes.getSkill2().indexOf("|"));
					utils.BitmapUtils.zoom(mContext, Bitmap.CompressFormat.PNG, fileImg, fileImg, 50, 50);
					return null;
				}
			}.executeOnExecutor(mExecutor, MainActivity.mImagePath + "skill2/" + heroes.getSkill2().subSequence(0, heroes.getSkill2().indexOf("|")));
			new ImageDownload(mContext, ImageDownload.SKILL){
				public Object method() {
					String fileImg = MainActivity.mImagePath + "skill3/" + heroes.getSkill3().subSequence(0, heroes.getSkill3().indexOf("|"));
					utils.BitmapUtils.zoom(mContext, Bitmap.CompressFormat.PNG, fileImg, fileImg, 50, 50);
					return null;
				}
			}.executeOnExecutor(mExecutor, MainActivity.mImagePath + "skill3/" + heroes.getSkill3().subSequence(0, heroes.getSkill3().indexOf("|")));
			new ImageDownload(mContext, ImageDownload.SKILL){
				public Object method() {
					String fileImg = MainActivity.mImagePath + "skill4/" + heroes.getSkill4().subSequence(0, heroes.getSkill4().indexOf("|"));
					utils.BitmapUtils.zoom(mContext, Bitmap.CompressFormat.PNG, fileImg, fileImg, 50, 50);
					return null;
				}
			}.executeOnExecutor(mExecutor, MainActivity.mImagePath + "skill4/" + heroes.getSkill4().subSequence(0, heroes.getSkill4().indexOf("|")));
			new ImageDownload(mContext, ImageDownload.SKILL){
				public Object method() {
					String fileImg = MainActivity.mImagePath + "skill5/" + heroes.getSkill5().subSequence(0, heroes.getSkill5().indexOf("|"));
					utils.BitmapUtils.zoom(mContext, Bitmap.CompressFormat.PNG, fileImg, fileImg, 50, 50);
					return null;
				}
			}.executeOnExecutor(mExecutor, MainActivity.mImagePath + "skill5/" + heroes.getSkill5().subSequence(0, heroes.getSkill5().indexOf("|")));
		}

	}

}
