package com.demo.lolhelper.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.demo.lolhelper.MainActivity;
import com.demo.lolhelper.R;
import com.demo.lolhelper.database.HeroesDatabasea;
import com.demo.lolhelper.object.Heroes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public final class HeroesAdapter extends BaseAdapter {

	private Context mContext;
	private ViewHolder mViewHolder;
	private ArrayList<Heroes> mHeroseList = new ArrayList<Heroes>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HeroesAdapter(Context context, String type, String seat, final String rank, TextView textview) {
		mContext = context;
		ArrayList<Heroes> arrayList = HeroesDatabasea.getHeroesList();
		for(int i = 0; i < arrayList.size(); i++) {
			String tag = arrayList.get(i).getTag1() + arrayList.get(i).getTag2() + arrayList.get(i).getTag3();
			if(type.equals("无限制")&&seat.equals("无限制"))
				mHeroseList.add(arrayList.get(i));
			else if(type.equals("无限制")) {
				if(tag.lastIndexOf(seat) != -1)
					mHeroseList.add(arrayList.get(i));
			}
			else if(seat.equals("无限制")) {
				if(tag.lastIndexOf(type) != -1)
					mHeroseList.add(arrayList.get(i));
			} else {
				if(tag.lastIndexOf(type) != -1 && tag.lastIndexOf(seat) != -1)
					mHeroseList.add(arrayList.get(i));
			}
		}
		if(!rank.equals("默认"))
		Collections.sort(mHeroseList, new Comparator() {

			public int compare(Object lhs, Object rhs) {
				if(rank.equals("物理")) {
					return (Integer.valueOf(((Heroes)rhs).getAttack() + "")).compareTo(Integer.valueOf(((Heroes)lhs).getAttack() + ""));
				} else if(rank.equals("魔法")) {
					return (Integer.valueOf(((Heroes)rhs).getMagic() + "")).compareTo(Integer.valueOf(((Heroes)lhs).getMagic() + ""));
				} else if(rank.equals("防御")) {
					return (Integer.valueOf(((Heroes)rhs).getDefense() + "")).compareTo(Integer.valueOf(((Heroes)lhs).getDefense() + ""));
				} else if(rank.equals("操作")) {
					return (Integer.valueOf(((Heroes)rhs).getDifficulty() + "")).compareTo(Integer.valueOf(((Heroes)lhs).getDifficulty() + ""));
				} else
					return 0;
			}

		});
		if(mHeroseList.size() == 0)
			textview.setVisibility(0);
		else
			textview.setVisibility(8);
	}

	public int getCount() {
		return mHeroseList.size();
	}

	public Object getItem(int position) {
		return mHeroseList.get(position);
	}

	public long getItemId(int position) {
		return Integer.valueOf(mHeroseList.get(position).get_id());
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = View.inflate(mContext, R.layout.item_heroes_list, null);
			mViewHolder = new ViewHolder();
			mViewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativelayout_herose_list);
			mViewHolder.imageViewHeroImg = (ImageView) convertView.findViewById(R.id.imageview_heroes_list_img );
			mViewHolder.textViewHeroesName = (TextView) convertView.findViewById(R.id.textview_heroes_list_name);
			mViewHolder.textViewHeroesTitle = (TextView) convertView.findViewById(R.id.textview_heroes_list_title);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		if(position % 2 == 0)
			mViewHolder.relativeLayout.setBackgroundResource(R.drawable.div_style_00);
		else
			mViewHolder.relativeLayout.setBackgroundResource(R.drawable.div_style_01);
		mViewHolder.imageViewHeroImg.setImageBitmap(utils.BitmapUtils.toBitmap(new File(MainActivity.mImagePath + "heroes", mHeroseList.get(position).getEn_name() + ".png").getAbsolutePath()));
		mViewHolder.textViewHeroesName.setText(mHeroseList.get(position).getName());
		StringBuffer tag = new StringBuffer();
		if(!mHeroseList.get(position).getTag1().equals(""))
			tag.append(mHeroseList.get(position).getTag1() + "  ");
		if(!mHeroseList.get(position).getTag2().equals(""))
			tag.append(mHeroseList.get(position).getTag2() + "  ");
		if(!mHeroseList.get(position).getTag2().equals(""))
			tag.append(mHeroseList.get(position).getTag3() + "  ");
		mViewHolder.textViewHeroesTitle.setText(mHeroseList.get(position).getNick() + "    " + tag);
		return convertView;
	}

	static class ViewHolder{
		ImageView imageViewHeroImg;
		TextView textViewHeroesName,textViewHeroesTitle;
		RelativeLayout relativeLayout;
	}

}
