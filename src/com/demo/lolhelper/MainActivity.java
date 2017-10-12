package com.demo.lolhelper;

import java.io.File;

import com.demo.lolhelper.database.HeroesDatabasea;
import com.demo.lolhelper.task.DatabaseUpdate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public static String mDataBasePath,mImagePath;
	private HeroesFragment mHeroesFragment;
	float X;
	@SuppressLint("HandlerLeak")
	public Handler mHandler = new Handler(){
		
		public void handleMessage(Message message) {
			String[] string = (String[])message.obj;
			if(string[0].equals("初始化任务结束")) {
				((ImageView)findViewById(R.id.imageview_loader)).setVisibility(8);
				getFragmentManager().beginTransaction().add(R.id.relativelayout_fragment, new HeroesFragment()).commit();
				new Handler().postDelayed(new Runnable() {
					
					public void run() {
						mHeroesFragment = (HeroesFragment) getFragmentManager().findFragmentById(R.id.relativelayout_fragment);
						
					}
					
				}, 1);
			}
		}
	
	};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization() {
    	new Thread(new Runnable() {

			public void run() {
		    	mDataBasePath = new File(getFilesDir().toString()).getParentFile().toString() + "/database/";
		    	if(!new File(mDataBasePath).exists())
		    		new File(mDataBasePath).mkdirs();
		    	mImagePath =  new File(getExternalFilesDir(null).toString()).getParentFile().toString() + "/.image/";
		    	if(!new File(mImagePath).exists())
		    		new File(mImagePath).mkdirs();
		    	try {
		    		String[] files = {"heroes", "skill1", "skill2", "skill3", "skill4", "skill5"};
		    		for(int i = 0; i < files.length; i++) {
		    			File file = new File(mImagePath + files[i]);
		    			if(!file.exists())
		    				file.mkdirs();
		    		}
		    		for(int i = 0; i < files.length; i++) {
			    		String[] file = getResources().getAssets().list("images/" + files[i]);
						if(file.length != 0)
						for(int j = 0; j < file.length; j++) {
							if(!new File(mImagePath + files[i] + "/" + file[j]).exists())
								utils.StreamUtils.output(MainActivity.this, mImagePath + files[i] + "/" + file[j], getResources().getAssets().open("images/" + files[i] + "/" + file[j]));
						}
		    		}
		    		files = getResources().getAssets().list("database");
		    		if(files.length != 0)
						for(int i = 0; i < files.length; i++) {
							if(!new File(mDataBasePath + files[i]).exists())
								utils.StreamUtils.output(MainActivity.this, mDataBasePath + files[i], getResources().getAssets().open("database/" + files[i]));
						}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		    	if(!new File(new File(getFilesDir().toString()).getParentFile().toString() + "/database/Heroes.db").exists())
		    		HeroesDatabasea.createTable();
		    	//new DatabaseUpdate(MainActivity.this, mHandler);
				Message message = new Message();
				message.obj = new String[] {"初始化任务结束"};
				mHandler.sendMessage(message);
			}

		}).start();
    }

    public void onBackPressed() {

    	if(mHeroesFragment != null) {
    		if(mHeroesFragment.mHeroesInfo.getVisibility() == 0) {
	    		mHeroesFragment.mGridViewheroesInfoMenu.setVisibility(8);
	    		mHeroesFragment.mGridViewheroesListRankMenu.setVisibility(8);
	    		mHeroesFragment.mGridViewheroesListSeatMenu.setVisibility(8);
	    		mHeroesFragment.mGridViewheroesListTypeMenu.setVisibility(8);
	    		mHeroesFragment.mHeroesInfo.setVisibility(8);
    		}
    		else
        		super.onBackPressed();
    	} else
    		super.onBackPressed();
    }

 
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	if(mHeroesFragment != null) {
	    	if(mHeroesFragment.mHeroesInfo.getVisibility() == 0){
	    		if(mHeroesFragment.mHeroesInfoBasic.getVisibility() == 0) {
	            	if(ev.getAction() == MotionEvent.ACTION_MOVE) {
	
	            	} else if(ev.getAction() == MotionEvent.ACTION_DOWN) {
	        				X = ev.getX();
	        		} else if(ev.getAction() == MotionEvent.ACTION_UP) {
	        			if(ev.getX() - X < -utils.DensityUtil.dip2px(this, 120)) {
	        				mHeroesFragment.mTextViewHeroesInfo.setText(mHeroesFragment.mArrayHeroesInfo[1]);
		        			mHeroesFragment.mHeroesInfoBasic.setVisibility(8);
		        			mHeroesFragment.mHeroesInfoUseSkill.setVisibility(0);
		        			mHeroesFragment.mWebViewheroesInfoStory.setVisibility(8);
	        			} else if(ev.getX() - X > utils.DensityUtil.dip2px(this, 120)) {
	        	    		mHeroesFragment.mGridViewheroesInfoMenu.setVisibility(8);
	        	    		mHeroesFragment.mGridViewheroesListRankMenu.setVisibility(8);
	        	    		mHeroesFragment.mGridViewheroesListSeatMenu.setVisibility(8);
	        	    		mHeroesFragment.mGridViewheroesListTypeMenu.setVisibility(8);
	        	    		mHeroesFragment.mHeroesInfo.setVisibility(8);
	        			}
	        		}
	    		} else if(mHeroesFragment.mHeroesInfoUseSkill.getVisibility() == 0) {
	            	if(ev.getAction() == MotionEvent.ACTION_MOVE) {
	
	            	} else if(ev.getAction() == MotionEvent.ACTION_DOWN) {
	        				X = ev.getX();
	        		} else if(ev.getAction() == MotionEvent.ACTION_UP) {
	        			if(ev.getX() - X < -utils.DensityUtil.dip2px(this, 120)) {
	        				mHeroesFragment.mTextViewHeroesInfo.setText(mHeroesFragment.mArrayHeroesInfo[2]);
		        			mHeroesFragment.mHeroesInfoBasic.setVisibility(8);
		        			mHeroesFragment.mHeroesInfoUseSkill.setVisibility(8);
		        			mHeroesFragment.mWebViewheroesInfoSkillDesc.scrollTo(0, 0);
		        			mHeroesFragment.mWebViewheroesInfoStory.setVisibility(0);
	        			}
	        			else if(ev.getX() - X > utils.DensityUtil.dip2px(this, 120)) {
	        				mHeroesFragment.mTextViewHeroesInfo.setText(mHeroesFragment.mArrayHeroesInfo[0]);
		        			mHeroesFragment.mHeroesInfoBasic.setVisibility(0);
		        			mHeroesFragment.mHeroesInfoUseSkill.setVisibility(8);
		        			mHeroesFragment.mWebViewheroesInfoStory.setVisibility(8);
	        			}
	        		}
	    		} else if(mHeroesFragment.mWebViewheroesInfoStory.getVisibility() == 0) {
	            	if(ev.getAction() == MotionEvent.ACTION_MOVE) {
	
	            	} else if(ev.getAction() == MotionEvent.ACTION_DOWN) {
	        				X = ev.getX();
	        		} else if(ev.getAction() == MotionEvent.ACTION_UP) {
	        			if(ev.getX() - X > utils.DensityUtil.dip2px(this, 120)) {
	        				mHeroesFragment.mTextViewHeroesInfo.setText(mHeroesFragment.mArrayHeroesInfo[1]);
		        			mHeroesFragment.mHeroesInfoBasic.setVisibility(8);
		        			mHeroesFragment.mHeroesInfoUseSkill.setVisibility(0);
		        			mHeroesFragment.mWebViewheroesInfoStory.scrollTo(0, 0);
		        			mHeroesFragment.mWebViewheroesInfoStory.setVisibility(8);
	        			}
	        		}
	    		}
	
	    	}
    	}
    	return super.dispatchTouchEvent(ev);
    }
}
