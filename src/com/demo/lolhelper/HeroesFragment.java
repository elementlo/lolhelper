package com.demo.lolhelper;

import java.util.ArrayList;
import java.util.HashMap;

import com.demo.lolhelper.adapter.HeroesAdapter;
import com.demo.lolhelper.database.HeroesDatabasea;
import com.demo.lolhelper.object.Heroes;
import com.demo.lolhelper.task.DatabaseUpdate;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HeroesFragment extends Fragment{

	private Boolean mState = false,MenuClickFlag = true,SkillClickFlag = true;
	 GridView mGridViewheroesList,mGridViewheroesListTypeMenu,mGridViewheroesListSeatMenu,mGridViewheroesListRankMenu,mGridViewheroesInfoMenu;
	 HeroesAdapter mHeroesListAdapter;
	TextView mTextViewHeroesType,mTextViewHeroesSeat,mTextViewHeroesRank,mTextViewHeroesListError,mTextViewHeroesInfo,mTextViewHeroesInfoName
	,mTextViewHeroesInfoNick,mTextViewHeroesInfoTag,mTextViewHeroesInfoMoney,mTextViewHeroesInfoCoin,mTextViewHeroesInfoOpSkill
	,mTextViewHeroesInfoTeamwork,mTextViewHeroesInfoUseSkill,mTextViewHeroesInfoAgSkill,mTextViewHeroesInfoSkill;
	 ImageView mImageViewHeroesImg,mImageViewHeroesAttack,mImageViewHeroesMagic,mImageViewHeroesDefense,mImageViewHeroesDifficulty
	,mImageViewHeroesSkill1,mImageViewHeroesSkill2,mImageViewHeroesSkill3,mImageViewHeroesSkill4,mImageViewHeroesSkill5;
	WebView mWebViewheroesInfoStory,mWebViewheroesInfoSkillDesc;
	String[] mArrayType = {"无限制", "刺客", "战士", "法师", "辅助", "射手", "坦克"};
	String[] mArraySeat = {"无限制", "上单", "打野", "中单", "ADC", "辅助"};
	String[] mArrayRank = {"默认", "物理", "魔法", "防御", "操作"};
	String[] mArrayHeroesInfo = {"基本信息", "使用技巧", "背景故事"};
	String mStringType = "无限制",mStringSeat = "无限制",mStringRank = "默认";
	RelativeLayout mHeroesInfo,mHeroesInfoBasic,mHeroesInfoUseSkill,mHeroesInfoTouch;
	Heroes mHeroes;

 	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_heroes, container, false);
	}

	public void onStart() {
		super.onStart();
		if(!mState){
			mState = true;
			initialization();
		}
	}

	private void initialization() {
    	mTextViewHeroesType = (TextView) getActivity().findViewById(R.id.textview_heroes_list_heroes_type);
    	mTextViewHeroesType.setOnClickListener(new MenuClick());
    	mTextViewHeroesSeat = (TextView) getActivity().findViewById(R.id.textview_heroes_list_seat);
    	mTextViewHeroesSeat.setOnClickListener(new MenuClick());
    	mTextViewHeroesRank = (TextView) getActivity().findViewById(R.id.textview_heroes_list_rank);
    	mTextViewHeroesRank.setOnClickListener(new MenuClick());
    	mTextViewHeroesListError = (TextView) getActivity().findViewById(R.id.textview_heroes_list_error);
    	mTextViewHeroesInfo = (TextView) getActivity().findViewById(R.id.textview_heroes_info);
    	mTextViewHeroesInfo.setOnClickListener(new MenuClick());
    	mTextViewHeroesInfoName = (TextView) getActivity().findViewById(R.id.textview_heroes_name);
    	mTextViewHeroesInfoNick = (TextView) getActivity().findViewById(R.id.textview_heroes_nick);
    	mTextViewHeroesInfoTag = (TextView) getActivity().findViewById(R.id.textview_heroes_tag);
    	mTextViewHeroesInfoMoney = (TextView) getActivity().findViewById(R.id.textview_heroes_money);
    	mTextViewHeroesInfoCoin = (TextView) getActivity().findViewById(R.id.textview_heroes_coin);
    	mTextViewHeroesInfoSkill = (TextView) getActivity().findViewById(R.id.textview_heroes_skill);
    	mTextViewHeroesInfoOpSkill = (TextView) getActivity().findViewById(R.id.textview_heroes_op_skill);
    	mTextViewHeroesInfoTeamwork = (TextView) getActivity().findViewById(R.id.textview_heroes_teamwork);
    	mTextViewHeroesInfoUseSkill = (TextView) getActivity().findViewById(R.id.textview_heroes_use_skuill);
    	mTextViewHeroesInfoAgSkill = (TextView) getActivity().findViewById(R.id.textview_heroes_ag_skill);
    	mImageViewHeroesImg = (ImageView) getActivity().findViewById(R.id.imageview_heroes_img);
    	mImageViewHeroesSkill1 = (ImageView) getActivity().findViewById(R.id.imageview_heroes_skill1);
    	mImageViewHeroesSkill1.setOnClickListener(new skillClick());
    	mImageViewHeroesSkill2 = (ImageView) getActivity().findViewById(R.id.imageview_heroes_skill2);
    	mImageViewHeroesSkill2.setOnClickListener(new skillClick());
    	mImageViewHeroesSkill3 = (ImageView) getActivity().findViewById(R.id.imageview_heroes_skill3);
    	mImageViewHeroesSkill3.setOnClickListener(new skillClick());
    	mImageViewHeroesSkill4 = (ImageView) getActivity().findViewById(R.id.imageview_heroes_skill4);
    	mImageViewHeroesSkill4.setOnClickListener(new skillClick());
    	mImageViewHeroesSkill5 = (ImageView) getActivity().findViewById(R.id.imageview_heroes_skill5);
    	mImageViewHeroesSkill5.setOnClickListener(new skillClick());
    	mImageViewHeroesAttack = (ImageView) getActivity().findViewById(R.id.imageview_heroes_attack);
    	mImageViewHeroesMagic = (ImageView) getActivity().findViewById(R.id.imageview_heroes_magic);
    	mImageViewHeroesDefense = (ImageView) getActivity().findViewById(R.id.imageview_heroes_defense);
    	mImageViewHeroesDifficulty = (ImageView) getActivity().findViewById(R.id.imageview_heroes_difficulty);
    	mWebViewheroesInfoSkillDesc= (WebView) getActivity().findViewById(R.id.webview_heroes_skill_desc);
    	mWebViewheroesInfoSkillDesc.setOverScrollMode(View.OVER_SCROLL_NEVER);
    	mWebViewheroesInfoSkillDesc.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    	mWebViewheroesInfoStory = (WebView) getActivity().findViewById(R.id.webview_heroes_story);
    	mWebViewheroesInfoStory.setOverScrollMode(View.OVER_SCROLL_NEVER);
    	mWebViewheroesInfoStory.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    	mHeroesInfo = (RelativeLayout) getActivity().findViewById(R.id.relativelayout_heroes_info);
    	mHeroesInfo.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
			
		});
    	mHeroesInfoTouch = (RelativeLayout) getActivity().findViewById(R.id.relativelayout_heroes_info_touch);
    	mHeroesInfoTouch.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {
				closeMenu();
				return false;
			}

		});
    	mHeroesInfoBasic = (RelativeLayout) getActivity().findViewById(R.id.relativelayout_heroes_info_basic);
    	mHeroesInfoUseSkill = (RelativeLayout) getActivity().findViewById(R.id.relativelayout_heroes_info_use_skill);
		ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> hashMap = new HashMap<String,String>();
		mGridViewheroesListTypeMenu = (GridView) getActivity().findViewById(R.id.gridview_heroes_list_type_menu);
		mGridViewheroesListTypeMenu.setSelector(new ColorDrawable(Color.TRANSPARENT));
		for(int i = 0; i < mArrayType.length; i++) {
			hashMap = new HashMap<String,String>();
			hashMap.put("title", mArrayType[i]);
			arrayList.add(hashMap);
		}
		mGridViewheroesListTypeMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(mArrayType[position].equals("无限制"))
					mTextViewHeroesType.setText("英雄类型");
				else
					mTextViewHeroesType.setText(mArrayType[position]);
				mStringType = mArrayType[position];
				closeMenu();
				mHeroesListAdapter = new HeroesAdapter(getActivity(), mStringType, mStringSeat, mStringRank, mTextViewHeroesListError);
				mGridViewheroesList.setAdapter(mHeroesListAdapter);
			}
			
		});
		mGridViewheroesListTypeMenu.setAdapter(new SimpleAdapter(getActivity(), arrayList, R.layout.item_heroes_list_sort_menu, new String[] {"title"}, new int[] {R.id.textview_heroes_list_title}));
		mGridViewheroesListSeatMenu = (GridView) getActivity().findViewById(R.id.gridview_heroes_list_seat_menu);
		mGridViewheroesListSeatMenu.setSelector(new ColorDrawable(Color.TRANSPARENT));
		arrayList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < mArraySeat.length; i++) {
			hashMap = new HashMap<String,String>();
			hashMap.put("title", mArraySeat[i]);
			arrayList.add(hashMap);
		}
		mGridViewheroesListSeatMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(mArraySeat[position].equals("无限制"))
					mTextViewHeroesSeat.setText("位置");
				else
					mTextViewHeroesSeat.setText(mArraySeat[position]);
				mStringSeat = mArraySeat[position];
				closeMenu();
				mHeroesListAdapter = new HeroesAdapter(getActivity(), mStringType, mStringSeat, mStringRank, mTextViewHeroesListError);
				mGridViewheroesList.setAdapter(mHeroesListAdapter);
			}
			
		});
		mGridViewheroesListSeatMenu.setAdapter(new SimpleAdapter(getActivity(), arrayList, R.layout.item_heroes_list_sort_menu, new String[] {"title"}, new int[] {R.id.textview_heroes_list_title}));
		mGridViewheroesListRankMenu = (GridView) getActivity().findViewById(R.id.gridview_heroes_list_rank_menu);
		mGridViewheroesListRankMenu.setSelector(new ColorDrawable(Color.TRANSPARENT));
		arrayList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < mArrayRank.length; i++) {
			hashMap = new HashMap<String,String>();
			hashMap.put("title", mArrayRank[i]);
			arrayList.add(hashMap);
		}
		mGridViewheroesListRankMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(mArrayRank[position].equals("默认"))
					mTextViewHeroesRank.setText("排序");
				else
					mTextViewHeroesRank.setText(mArrayRank[position]);
				mStringRank = mArrayRank[position];
				closeMenu();
				mHeroesListAdapter = new HeroesAdapter(getActivity(), mStringType, mStringSeat, mStringRank, mTextViewHeroesListError);
				mGridViewheroesList.setAdapter(mHeroesListAdapter);
			}
			
		});
		mGridViewheroesListRankMenu.setAdapter(new SimpleAdapter(getActivity(), arrayList, R.layout.item_heroes_list_sort_menu, new String[] {"title"}, new int[] {R.id.textview_heroes_list_title}));
		mGridViewheroesInfoMenu = (GridView) getActivity().findViewById(R.id.gridview_heroes_info_menu);
		mGridViewheroesInfoMenu.setSelector(new ColorDrawable(Color.TRANSPARENT));
		arrayList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < mArrayHeroesInfo.length; i++) {
			hashMap = new HashMap<String,String>();
			hashMap.put("title", mArrayHeroesInfo[i]);
			arrayList.add(hashMap);
		}
		mGridViewheroesInfoMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				mTextViewHeroesInfo.setText(mArrayHeroesInfo[position]);
				if(mArrayHeroesInfo[position].equals("基本信息")) {
					mHeroesInfoBasic.setVisibility(0);
					mHeroesInfoUseSkill.setVisibility(8);
					mWebViewheroesInfoStory.setVisibility(8);
				} else if(mArrayHeroesInfo[position].equals("背景故事")) {
					mHeroesInfoBasic.setVisibility(8);
					mHeroesInfoUseSkill.setVisibility(8);
					mWebViewheroesInfoStory.setVisibility(0);
				} else {
					mHeroesInfoBasic.setVisibility(8);
					mHeroesInfoUseSkill.setVisibility(0);
					mWebViewheroesInfoStory.setVisibility(8);
				}
				closeMenu();
			}
			
		});
		mGridViewheroesInfoMenu.setAdapter(new SimpleAdapter(getActivity(), arrayList, R.layout.item_heroes_list_sort_menu, new String[] {"title"}, new int[] {R.id.textview_heroes_list_title}));
		mGridViewheroesList = (GridView) getActivity().findViewById(R.id.gridview_heroes_list);
		mGridViewheroesList.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mGridViewheroesList.setOverScrollMode(View.OVER_SCROLL_NEVER);
		mGridViewheroesList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				openheroesInfo((int) mHeroesListAdapter.getItemId(position));
			}
			
		});
		mHeroesListAdapter = new HeroesAdapter(getActivity(), mStringType, mStringSeat, mStringRank, mTextViewHeroesListError);
		mGridViewheroesList.setAdapter(mHeroesListAdapter);
	 }

	private void openMenu(final GridView gridView) {
		gridView.setVisibility(0);
		for(int i = 0; i < 40; i++) {
			final float j = (float)(i*0.01+0.60);
			new Handler().postDelayed(new Runnable() {

				public void run() {
					gridView.setAlpha(j);
				}

			}, i*10);
		}
	 }

	public void closeMenu() {
		mGridViewheroesListTypeMenu.setVisibility(8);
		mGridViewheroesListSeatMenu.setVisibility(8);
		mGridViewheroesListRankMenu.setVisibility(8);
		mGridViewheroesInfoMenu.setVisibility(8);
		mTextViewHeroesType.setTextColor(Color.rgb(23, 100, 68));
		mTextViewHeroesSeat.setTextColor(Color.rgb(23, 100, 68));
		mTextViewHeroesRank.setTextColor(Color.rgb(23, 100, 68));
		mTextViewHeroesInfo.setTextColor(Color.rgb(23, 100, 68));
		mGridViewheroesListTypeMenu.setAlpha(0);
		mGridViewheroesListSeatMenu.setAlpha(0);
		mGridViewheroesListRankMenu.setAlpha(0);
		mGridViewheroesInfoMenu.setAlpha(0);
	}
	
	private class MenuClick implements OnClickListener {

		public void onClick(final View view) {
			if(MenuClickFlag && ((TextView)view).getTextColors().getDefaultColor() != Color.rgb(212, 168, 50)) {
				MenuClickFlag = false;
				closeMenu();
				((TextView)view).setTextColor(Color.rgb(212, 168, 50));
				new Handler().postDelayed(new Runnable() {
	
					public void run() {
						if(mTextViewHeroesType == (TextView)view) {
							openMenu(mGridViewheroesListTypeMenu);
						} else if(mTextViewHeroesSeat == (TextView)view) {
							openMenu(mGridViewheroesListSeatMenu);
						} else if(mTextViewHeroesRank == (TextView)view) {
							openMenu(mGridViewheroesListRankMenu);
						} else if(mTextViewHeroesInfo == (TextView)view) {
							openMenu(mGridViewheroesInfoMenu);
						}
						MenuClickFlag = true;
					}
	
				}, 100);
			}
		}

	}

	private class skillClick implements OnClickListener {

		public void onClick(View view) {
			mImageViewHeroesSkill1.setAlpha((float) 0.3);
			mImageViewHeroesSkill2.setAlpha((float) 0.3);
			mImageViewHeroesSkill3.setAlpha((float) 0.3);
			mImageViewHeroesSkill4.setAlpha((float) 0.3);
			mImageViewHeroesSkill5.setAlpha((float) 0.3);
			((ImageView)view).setAlpha((float) 1.0);
			StringBuffer html = new StringBuffer();
			html.append("<html><body style=\"width:96%;background-color:#131313\"><div style=\"width:100%;word-break:break-all;color:#f7f7f7;font-size:0.95em\">");
			if(mImageViewHeroesSkill1 == (ImageView)view) {
				mTextViewHeroesInfoSkill.setText(mHeroes.getSkill1().subSequence(mHeroes.getSkill1().indexOf("|") + 1, mHeroes.getSkill1().length()));
				html.append(toSkill(mHeroes.getSkill1_desc()));
				html.append("</div></body></html>");
			} else if(mImageViewHeroesSkill2 == (ImageView)view) {
				mTextViewHeroesInfoSkill.setText(mHeroes.getSkill2().subSequence(mHeroes.getSkill2().indexOf("|") + 1, mHeroes.getSkill2().length()));
				html.append(toSkill(mHeroes.getSkill2_desc()));
				if(!mHeroes.getSkill2_add().equals("") && !mHeroes.getSkill2_add().equals("无") && !mHeroes.getSkill2_add().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill2_add()));
				if(!mHeroes.getSkill2_damage().equals("") && !mHeroes.getSkill2_damage().equals("无") && !mHeroes.getSkill2_damage().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill2_damage()));
				if(!mHeroes.getSkill2_expend().equals("") && !mHeroes.getSkill2_expend().equals("无") && !mHeroes.getSkill2_expend().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill2_expend()));
				if(!mHeroes.getSkill2_cooling().equals("") && !mHeroes.getSkill2_cooling().equals("无") && !mHeroes.getSkill2_cooling().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill2_cooling()));
				html.append("</div></body></html>");
			} else if(mImageViewHeroesSkill3 == (ImageView)view) {
				mTextViewHeroesInfoSkill.setText(mHeroes.getSkill3().subSequence(mHeroes.getSkill3().indexOf("|") + 1, mHeroes.getSkill3().length()));
				html.append(toSkill(mHeroes.getSkill3_desc()));
				if(!mHeroes.getSkill3_add().equals("") && !mHeroes.getSkill3_add().equals("无") && !mHeroes.getSkill3_add().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill3_add()));
				if(!mHeroes.getSkill3_damage().equals("") && !mHeroes.getSkill3_damage().equals("无") && !mHeroes.getSkill3_damage().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill3_damage()));
				if(!mHeroes.getSkill3_expend().equals("") && !mHeroes.getSkill3_expend().equals("无") && !mHeroes.getSkill3_expend().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill3_expend()));
				if(!mHeroes.getSkill3_cooling().equals("") && !mHeroes.getSkill3_cooling().equals("无") && !mHeroes.getSkill3_cooling().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill3_cooling()));
				html.append("</div></body></html>");
			} else if(mImageViewHeroesSkill4 == (ImageView)view) {
				mTextViewHeroesInfoSkill.setText(mHeroes.getSkill4().subSequence(mHeroes.getSkill4().indexOf("|") + 1, mHeroes.getSkill4().length()));
				html.append(toSkill(mHeroes.getSkill4_desc()));
				if(!mHeroes.getSkill4_add().equals("") && !mHeroes.getSkill4_add().equals("无") && !mHeroes.getSkill4_add().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill4_add()));
				if(!mHeroes.getSkill4_damage().equals("") && !mHeroes.getSkill4_damage().equals("无") && !mHeroes.getSkill4_damage().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill4_damage()));
				if(!mHeroes.getSkill4_expend().equals("") && !mHeroes.getSkill4_expend().equals("无") && !mHeroes.getSkill4_expend().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill4_expend()));
				if(!mHeroes.getSkill4_cooling().equals("") && !mHeroes.getSkill4_cooling().equals("无") && !mHeroes.getSkill4_cooling().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill4_cooling()));
				html.append("</div></body></html>");
			} else if(mImageViewHeroesSkill5 == (ImageView)view) {
				mTextViewHeroesInfoSkill.setText(mHeroes.getSkill5().subSequence(mHeroes.getSkill5().indexOf("|") + 1, mHeroes.getSkill5().length()));
				html.append(toSkill(mHeroes.getSkill5_desc()));
				if(!mHeroes.getSkill5_add().equals("") && !mHeroes.getSkill5_add().equals("无") && !mHeroes.getSkill5_add().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill5_add()));
				if(!mHeroes.getSkill5_damage().equals("") && !mHeroes.getSkill5_damage().equals("无") && !mHeroes.getSkill5_damage().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill5_damage()));
				if(!mHeroes.getSkill5_expend().equals("") && !mHeroes.getSkill5_expend().equals("无") && !mHeroes.getSkill5_expend().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill5_expend()));
				if(!mHeroes.getSkill5_cooling().equals("") && !mHeroes.getSkill5_cooling().equals("无") && !mHeroes.getSkill5_cooling().equals("0.0"))
					html.append("<br/><br/>" + toSkill(mHeroes.getSkill5_cooling()));
				html.append("</div></body></html>");
			}
			if(SkillClickFlag) {
				SkillClickFlag = false;
				mWebViewheroesInfoSkillDesc.scrollTo(0, 0);
				mWebViewheroesInfoSkillDesc.loadData(html.toString(), "text/html; charset=UTF-8", null);
				mWebViewheroesInfoSkillDesc.reload();
				new Handler().postDelayed(new Runnable() {

					public void run() {
						SkillClickFlag = true;
					}
					
				}, 50);
			}
		}

	}
	private void openheroesInfo(int _id) {
		mHeroesInfo.setAlpha(0);
		mHeroesInfo.setVisibility(0);
		mHeroesInfoBasic.setVisibility(0);
		mHeroesInfoUseSkill.setVisibility(4);
		mWebViewheroesInfoStory.setVisibility(4);
		new Thread(new DatabaseUpdate(getActivity()).new Update(_id, 1000*60*60*24)).start();
		final Heroes heroes = HeroesDatabasea.getHeroesInfo(_id);
		mHeroes = heroes;
		mTextViewHeroesInfo.setText(mArrayHeroesInfo[0]);
		mTextViewHeroesInfoName.setText(heroes.getName());
		mTextViewHeroesInfoNick.setText(heroes.getNick());
		StringBuffer tag = new StringBuffer();
		StringBuffer html = new StringBuffer();
		StringBuffer useSkill = new StringBuffer();
		StringBuffer agSkill = new StringBuffer();
		if(!heroes.getTag1().equals(""))
			tag.append(heroes.getTag1() + "  ");
		if(!heroes.getTag2().equals(""))
			tag.append(heroes.getTag2() + "  ");
		if(!heroes.getTag2().equals(""))
			tag.append(heroes.getTag3() + "  ");
		mTextViewHeroesInfoTag.setText(tag);
		mTextViewHeroesInfoMoney.setText("金币：" + (int)heroes.getMoney());
    	mTextViewHeroesInfoCoin.setText("点卷：" + (int)heroes.getCoin());
    	mTextViewHeroesInfoSkill.setText(heroes.getSkill1().subSequence(heroes.getSkill1().indexOf("|") + 1, heroes.getSkill1().length()));
    	if(!heroes.getOp_skill().equals(""))
    		mTextViewHeroesInfoOpSkill.setText("操作技巧\n\n" + heroes.getOp_skill().replace("\r", "").replace("\n", ""));
    	if(!heroes.getTeamwork().equals(""))
    		mTextViewHeroesInfoTeamwork.setText("团战思路\n\n" + heroes.getTeamwork().replace("\r", "").replace("\n", ""));
    	if(!heroes.getUse_skill1().equals(""))
    		useSkill.append("\n"+heroes.getUse_skill1());
    	if(!heroes.getUse_skill2().equals(""))
    		useSkill.append("\n"+heroes.getUse_skill2());
    	if(!heroes.getUse_skill3().equals(""))
    		useSkill.append("\n"+heroes.getUse_skill3());
    	mTextViewHeroesInfoUseSkill.setText("当你使用此英雄时\n" + useSkill);
    	if(!heroes.getAg_skill1().equals(""))
    		agSkill.append("\n"+heroes.getAg_skill1());
    	if(!heroes.getAg_skill2().equals(""))
    		agSkill.append("\n"+heroes.getAg_skill2());
    	if(!heroes.getAg_skill3().equals(""))
    		agSkill.append("\n"+heroes.getAg_skill3());
    	mTextViewHeroesInfoAgSkill.setText("当对方使用此英雄时\n" + agSkill);
    	mImageViewHeroesImg.setImageBitmap(utils.BitmapUtils.toBitmap(MainActivity.mImagePath + "heroes/" + heroes.getEn_name() + ".png"));
    	mImageViewHeroesSkill1.setImageBitmap(utils.BitmapUtils.toBitmap(MainActivity.mImagePath + "skill1/" + heroes.getSkill1().subSequence(0, heroes.getSkill1().indexOf("|"))));
    	mImageViewHeroesSkill2.setImageBitmap(utils.BitmapUtils.toBitmap(MainActivity.mImagePath + "skill2/" + heroes.getSkill2().subSequence(0, heroes.getSkill2().indexOf("|"))));
    	mImageViewHeroesSkill3.setImageBitmap(utils.BitmapUtils.toBitmap(MainActivity.mImagePath + "skill3/" + heroes.getSkill3().subSequence(0, heroes.getSkill3().indexOf("|"))));
    	mImageViewHeroesSkill4.setImageBitmap(utils.BitmapUtils.toBitmap(MainActivity.mImagePath + "skill4/" + heroes.getSkill4().subSequence(0, heroes.getSkill4().indexOf("|"))));
    	mImageViewHeroesSkill5.setImageBitmap(utils.BitmapUtils.toBitmap(MainActivity.mImagePath + "skill5/" + heroes.getSkill5().subSequence(0, heroes.getSkill5().indexOf("|"))));
		mImageViewHeroesSkill1.setAlpha((float) 1.0);
		mImageViewHeroesSkill2.setAlpha((float) 0.3);
		mImageViewHeroesSkill3.setAlpha((float) 0.3);
		mImageViewHeroesSkill4.setAlpha((float) 0.3);
		mImageViewHeroesSkill5.setAlpha((float) 0.3);
    	html.append("<html><body style=\"width:96%;background-color:#131313\"><div style=\"width:100%;word-break:break-all;color:#f7f7f7;font-size:0.95em\">");
		html.append(toSkill(heroes.getSkill1_desc()));
		html.append("</div></body></html>");
		mWebViewheroesInfoSkillDesc.scrollTo(0, 0);
		mWebViewheroesInfoSkillDesc.loadData(html.toString(), "text/html; charset=UTF-8", null);
		mWebViewheroesInfoSkillDesc.reload();
    	DisplayMetrics displayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		RelativeLayout.LayoutParams layoutParams = new LayoutParams((int)(displayMetrics.widthPixels*0.975*heroes.getAttack()*0.1), utils.DensityUtil.dip2px(getActivity(), 18));
		layoutParams.setMargins(10, 5, 2, 2);
		mImageViewHeroesAttack.setLayoutParams(layoutParams);
		layoutParams = new LayoutParams((int)(displayMetrics.widthPixels*0.975*heroes.getMagic()*0.1), utils.DensityUtil.dip2px(getActivity(), 18));
		layoutParams.setMargins(7, 5, 5, 2);
		mImageViewHeroesMagic.setLayoutParams(layoutParams);
		layoutParams = new LayoutParams((int)(displayMetrics.widthPixels*0.975*heroes.getDefense()*0.1), utils.DensityUtil.dip2px(getActivity(), 18));
		layoutParams.setMargins(10, 5, 2, 2);
		mImageViewHeroesDefense.setLayoutParams(layoutParams);
		layoutParams = new LayoutParams((int)(displayMetrics.widthPixels*0.975*heroes.getDifficulty()*0.1), utils.DensityUtil.dip2px(getActivity(), 18));
		layoutParams.setMargins(7, 5, 5, 2);
		mImageViewHeroesDifficulty.setLayoutParams(layoutParams);
		html.delete(0, html.length()+1);
		html.append("<html><body style=\"width:96%;background-color:#151515\"><div style=\"width:98%;word-break:break-all;color:#f7f7f7;font-size:0.95em\">");
		html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + toStory(heroes.getStory()));
		html.append("</div></body></html>");
		mWebViewheroesInfoStory.scrollTo(0, 0);
		mWebViewheroesInfoStory.loadData(html.toString(), "text/html; charset=UTF-8", null);
		mWebViewheroesInfoStory.reload();
		new Handler().postDelayed(new Runnable() {
			public void run() {
				for(int i = 0; i < 40; i++) {
					final float j = (float)(i*0.01+0.6);
					new Handler().postDelayed(new Runnable() {

						public void run() {
							mHeroesInfo.setAlpha(j);
						}

					}, i*15);
				}
			}
		}, 300);
	}
	
	private String toStory(String string) {
		return string.trim()
				.replace(" ", "")
				.replace("　", "")
				.replace("\r\n\r\n", "\r\n")
				.replace("\r\n", "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	}
	
	private String toSkill(String string) {
		return string.trim()
				.replace(" ", "")
				.replace("　", "")
				.replace("\r\n\r\n", "\r\n")
				.replace("\r\n", "<br/><br/>");
	}

}
