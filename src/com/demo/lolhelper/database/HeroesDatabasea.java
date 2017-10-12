package com.demo.lolhelper.database;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;

import com.demo.lolhelper.MainActivity;
import com.demo.lolhelper.object.Heroes;

public final class HeroesDatabasea {

	private HeroesDatabasea() {}

	public static void createTable() {
		utils.SQLiteUtils db = new utils.SQLiteUtils();
		db.deleteDatabase(MainActivity.mDataBasePath + "Heroes.db");
		db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
    	db.createTable("Heroes", new String[] {
    			"_id Integer Primary Key",
    			"en_name Text",
    			"name Text",
    			"nick Text",
    			"money Real",
    			"coin Real",
    			"attack Integer",
    			"magic Integer",
    			"defense Integer",
    			"difficulty Integer",
    			"tag1 Text",
    			"tag2 Text",
    			"tag3 Text",
    			"tag4 Text",
    			"story Text",
    			"use_skill1 Text",
    			"use_skill2 Text",
    			"use_skill3 Text",
    			"ag_skill1 Text",
    			"ag_skill2 Text",
    			"ag_skill3 Text",
    			"skill1 Text",
    			"skill1_desc Text",
    			"skill2 Text",
    			"skill2_desc Text",
    			"skill2_expend Text",
    			"skill2_add Text",
    			"skill2_cooling Text",
    			"skill2_damage Text",
    			"skill3 Text",
    			"skill3_desc Text",
    			"skill3_expend Text",
    			"skill3_add Text",
    			"skill3_cooling Text",
    			"skill3_damage Text",
    			"skill4 Text",
    			"skill4_desc Text",
    			"skill4_expend Text",
    			"skill4_add Text",
    			"skill4_cooling Text",
    			"skill4_damage Text",
    			"skill5 Text",
    			"skill5_desc Text",
    			"skill5_expend Text",
    			"skill5_add Text",
    			"skill5_cooling Text",
    			"skill5_damage Text",
    			"op_skill Text",
    			"teamwork Text",
    			"best_partner1 Text",
    			"partner_reason1 Text",
    			"best_partner2 Text",
    			"partner_reason2 Text",
    			"strongest_opponent1 Text",
    			"opponent_reason1 Text",
    			"strongest_opponent2 Text",
    			"opponent_reason2 Text",
    			"last_modify_date Text",
    			"update_time Integer"});
		db.createTable("Log", new String[] {
				"_id Integer Primary Key",
				"tag Text",
				"text Text",
				"time Integer"});
    	db.close();
	}

	public static boolean insertLogInfo(Context context, String[] arrayString) {
		synchronized (context) {
			boolean flag = false;
			utils.SQLiteUtils db = new utils.SQLiteUtils();
			db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
			flag = db.insert("Log", new String[] {
					"tag",
					"text",
					"time"}, arrayString);
			db.close();
			return flag;
		}
	}

	public static boolean insertHeroesInfo(Context context, String string) {
		synchronized (context) {
			boolean flag = false;
			utils.SQLiteUtils db = new utils.SQLiteUtils();
			db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
	    	Cursor cursor = db.select("Heroes", new String[] {"_id"}, null, null, null, null, null);
	    	try {
	    		JSONArray jsonArray = new JSONArray(string.replace("'", ""));
		    	if(cursor == null || cursor.getCount() < jsonArray.length()) {
			    	for(int i = 0;i<jsonArray.length();i++) {
			    		flag = db.insert("Heroes", new String[] {
				    			"_id",
				    			"en_name",
				    			"name",
				    			"nick",
				    			"tag1",
				    			"tag2",
				    			"tag3",
				    			"tag4",
				    			"attack",
				    			"magic",
				    			"defense",
				    			"difficulty",
				    			"update_time"}, new String[] {
				    			jsonArray.getJSONObject(i).get("id").toString().trim(),
				    			jsonArray.getJSONObject(i).get("en_name").toString().trim(),
				    			jsonArray.getJSONObject(i).get("name").toString().trim(),
				    			jsonArray.getJSONObject(i).get("nick").toString().trim(),
				    			jsonArray.getJSONObject(i).get("tag1").toString().trim(),
				    			jsonArray.getJSONObject(i).get("tag2").toString().trim(),
				    			jsonArray.getJSONObject(i).get("tag3").toString().trim(),
				    			jsonArray.getJSONObject(i).get("tag4").toString().trim(),
				    			jsonArray.getJSONObject(i).get("attack").toString().trim(),
				    			jsonArray.getJSONObject(i).get("magic").toString().trim(),
				    			jsonArray.getJSONObject(i).get("defense").toString().trim(),
				    			jsonArray.getJSONObject(i).get("difficulty").toString().trim(),
				    			"0"});
			    	}
		    	}
		    	db.close();
		    	return flag;
	    	} catch(Exception exception) {
	    		db.close();
	    		return flag;
	    	}
		}
	}

	public static boolean updateHeroesInfo(Context context, int id, String string) {
		synchronized (context) {
			boolean flag = false;
			utils.SQLiteUtils db = new utils.SQLiteUtils();
			db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
			try {
				JSONObject jsonObject = new JSONObject(string.replace("'", ""));
				flag = db.update("Heroes", new String[] {
		    			"en_name",
		    			"name",
		    			"nick",
		    			"money",
		    			"coin",
		    			"attack",
		    			"magic",
		    			"defense",
		    			"difficulty",
		    			"tag1",
		    			"tag2",
		    			"tag3",
		    			"tag4",
		    			"story",
		    			"use_skill1",
		    			"use_skill2",
		    			"use_skill3",
		    			"ag_skill1",
		    			"ag_skill2",
		    			"ag_skill3",
		    			"skill1",
		    			"skill1_desc",
		    			"skill2",
		    			"skill2_desc",
		    			"skill2_expend",
		    			"skill2_add",
		    			"skill2_cooling",
		    			"skill2_damage",
		    			"skill3",
		    			"skill3_desc",
		    			"skill3_expend",
		    			"skill3_add",
		    			"skill3_cooling",
		    			"skill3_damage",
		    			"skill4",
		    			"skill4_desc",
		    			"skill4_expend",
		    			"skill4_add",
		    			"skill4_cooling",
		    			"skill4_damage",
		    			"skill5",
		    			"skill5_desc",
		    			"skill5_expend",
		    			"skill5_add",
		    			"skill5_cooling",
		    			"skill5_damage",
		    			"op_skill",
		    			"teamwork",
		    			"best_partner1",
		    			"partner_reason1",
		    			"best_partner2",
		    			"partner_reason2",
		    			"strongest_opponent1",
		    			"opponent_reason1",
		    			"strongest_opponent2",
		    			"opponent_reason2",
		    			"last_modify_date",
		    			"update_time"}, new String[] {
		    			jsonObject.getString("en_name"),
		    			jsonObject.getString("name"),
		    			jsonObject.getString("nick"),
		    			jsonObject.getString("money"),
		    			jsonObject.getString("coin"),
		    			jsonObject.getString("attack"),
		    			jsonObject.getString("magic"),
		    			jsonObject.getString("defense"),
		    			jsonObject.getString("difficulty"),
		    			jsonObject.getString("tag1"),
		    			jsonObject.getString("tag2"),
		    			jsonObject.getString("tag3"),
		    			jsonObject.getString("tag4"),
		    			jsonObject.getString("story"),
		    			jsonObject.getString("use_skill1"),
		    			jsonObject.getString("use_skill2"),
		    			jsonObject.getString("use_skill3"),
		    			jsonObject.getString("ag_skill1"),
		    			jsonObject.getString("ag_skill2"),
		    			jsonObject.getString("ag_skill3"),
		    			jsonObject.getString("skill1"),
		    			jsonObject.getString("skill1_desc"),
		    			jsonObject.getString("skill2"),
		    			jsonObject.getString("skill2_desc"),
		    			jsonObject.getString("skill2_expend"),
		    			jsonObject.getString("skill2_add"),
		    			jsonObject.getString("skill2_cooling"),
		    			jsonObject.getString("skill2_damage"),
		    			jsonObject.getString("skill3"),
		    			jsonObject.getString("skill3_desc"),
		    			jsonObject.getString("skill3_expend"),
		    			jsonObject.getString("skill3_add"),
		    			jsonObject.getString("skill3_cooling"),
		    			jsonObject.getString("skill3_damage"),
		    			jsonObject.getString("skill4"),
		    			jsonObject.getString("skill4_desc"),
		    			jsonObject.getString("skill4_expend"),
		    			jsonObject.getString("skill4_add"),
		    			jsonObject.getString("skill4_cooling"),
		    			jsonObject.getString("skill4_damage"),
		    			jsonObject.getString("skill5"),
		    			jsonObject.getString("skill5_desc"),
		    			jsonObject.getString("skill5_expend"),
		    			jsonObject.getString("skill5_add"),
		    			jsonObject.getString("skill5_cooling"),
		    			jsonObject.getString("skill5_damage"),
		    			jsonObject.getString("op_skill"),
		    			jsonObject.getString("teamwork"),
		    			jsonObject.getString("best_partner1"),
		    			jsonObject.getString("partner_reason1"),
		    			jsonObject.getString("best_partner2"),
		    			jsonObject.getString("partner_reason2"),
		    			jsonObject.getString("strongest_opponent1"),
		    			jsonObject.getString("opponent_reason1"),
		    			jsonObject.getString("strongest_opponent2"),
		    			jsonObject.getString("opponent_reason2"),
		    			jsonObject.getString("last_modify_date"),
		    			System.currentTimeMillis() + ""}, "_id=" + id);
		    	db.close();
				return flag;	
			} catch(Exception exception) {
				db.close();
				return flag;
			}
		}
	}

	public static ArrayList<Heroes> getHeroesList() {
		Heroes heroes = null;
		ArrayList<Heroes> arrayList = new ArrayList<Heroes>();
		utils.SQLiteUtils db = new utils.SQLiteUtils();
		db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
    	Cursor cursor = db.select("Heroes", new String[] {"*"}, null, null, null, null, null);
    	if(cursor.getCount() != 0) {
			while(!cursor.isLast()) {
				cursor.moveToNext();
				heroes = new Heroes();
				heroes.set_id(Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))));
				heroes.setEn_name(cursor.getString(cursor.getColumnIndex("en_name")));
				heroes.setName(cursor.getString(cursor.getColumnIndex("name")));
				heroes.setNick(cursor.getString(cursor.getColumnIndex("nick")));
				heroes.setMoney(Float.valueOf(cursor.getString(cursor.getColumnIndex("money"))));
				heroes.setCoin(Float.valueOf(cursor.getString(cursor.getColumnIndex("coin"))));
				heroes.setAttack(Integer.valueOf(cursor.getString(cursor.getColumnIndex("attack"))));
				heroes.setMagic(Integer.valueOf(cursor.getString(cursor.getColumnIndex("magic"))));
				heroes.setDefense(Integer.valueOf(cursor.getString(cursor.getColumnIndex("defense"))));
				heroes.setDifficulty(Integer.valueOf(cursor.getString(cursor.getColumnIndex("difficulty"))));
				heroes.setTag1(cursor.getString(cursor.getColumnIndex("tag1")));
				heroes.setTag2(cursor.getString(cursor.getColumnIndex("tag2")));
				heroes.setTag3(cursor.getString(cursor.getColumnIndex("tag3")));
				heroes.setTag4(cursor.getString(cursor.getColumnIndex("tag4")));
				heroes.setSkill1(cursor.getString(cursor.getColumnIndex("skill1")));
				heroes.setSkill2(cursor.getString(cursor.getColumnIndex("skill2")));
				heroes.setSkill3(cursor.getString(cursor.getColumnIndex("skill3")));
				heroes.setSkill4(cursor.getString(cursor.getColumnIndex("skill4")));
				heroes.setSkill5(cursor.getString(cursor.getColumnIndex("skill5")));
				heroes.setUpdate_time(cursor.getString(cursor.getColumnIndex("update_time")));
				boolean flag = true;
				if(heroes.getUpdate_time().equals("0"))
					flag = false;
				if(!new File(MainActivity.mImagePath + "heroes/" + heroes.getEn_name() + ".png").exists())
					flag = false;
				if(!new File(MainActivity.mImagePath + "skill1/" + heroes.getSkill1().subSequence(0, heroes.getSkill1().indexOf("|"))).exists())
					flag = false;
				if(!new File(MainActivity.mImagePath + "skill2/" + heroes.getSkill2().subSequence(0, heroes.getSkill2().indexOf("|"))).exists())
					flag = false;
				if(!new File(MainActivity.mImagePath + "skill3/" + heroes.getSkill3().subSequence(0, heroes.getSkill3().indexOf("|"))).exists())
					flag = false;
				if(!new File(MainActivity.mImagePath + "skill4/" + heroes.getSkill4().subSequence(0, heroes.getSkill4().indexOf("|"))).exists())
					flag = false;
				if(!new File(MainActivity.mImagePath + "skill5/" + heroes.getSkill5().subSequence(0, heroes.getSkill5().indexOf("|"))).exists())
					flag = false;
				if(flag) {
					arrayList.add(heroes);
				}
			}
			db.close();
			return arrayList;
    	} else {
    		db.close();
    		return null;
    	}
	}

	public static Heroes getHeroesInfo(int _id) {
		Heroes heroes = new Heroes();
		utils.SQLiteUtils db = new utils.SQLiteUtils();
		db.openOrCreateDatabase(MainActivity.mDataBasePath + "Heroes.db");
		Cursor cursor = db.select("Heroes", new String[] {"*"}, "_id=" + _id, null, null, null, null);
		if(cursor.getCount() != 0) {
			cursor.moveToNext();
			heroes.set_id(Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))));
			heroes.setEn_name(cursor.getString(cursor.getColumnIndex("en_name")));
			heroes.setName(cursor.getString(cursor.getColumnIndex("name")));
			heroes.setNick(cursor.getString(cursor.getColumnIndex("nick")));
			heroes.setMoney(Float.valueOf(cursor.getString(cursor.getColumnIndex("money"))));
			heroes.setCoin(Float.valueOf(cursor.getString(cursor.getColumnIndex("coin"))));
			heroes.setAttack(Integer.valueOf(cursor.getString(cursor.getColumnIndex("attack"))));
			heroes.setMagic(Integer.valueOf(cursor.getString(cursor.getColumnIndex("magic"))));
			heroes.setDefense(Integer.valueOf(cursor.getString(cursor.getColumnIndex("defense"))));
			heroes.setDifficulty(Integer.valueOf(cursor.getString(cursor.getColumnIndex("difficulty"))));
			heroes.setTag1(cursor.getString(cursor.getColumnIndex("tag1")));
			heroes.setTag2(cursor.getString(cursor.getColumnIndex("tag2")));
			heroes.setTag3(cursor.getString(cursor.getColumnIndex("tag3")));
			heroes.setTag4(cursor.getString(cursor.getColumnIndex("tag4")));
			heroes.setStory(cursor.getString(cursor.getColumnIndex("story")));
			heroes.setUse_skill1(cursor.getString(cursor.getColumnIndex("use_skill1")));
			heroes.setUse_skill2(cursor.getString(cursor.getColumnIndex("use_skill2")));
			heroes.setUse_skill3(cursor.getString(cursor.getColumnIndex("use_skill3")));
			heroes.setAg_skill1(cursor.getString(cursor.getColumnIndex("ag_skill1")));
			heroes.setAg_skill2(cursor.getString(cursor.getColumnIndex("ag_skill2")));
			heroes.setAg_skill3(cursor.getString(cursor.getColumnIndex("ag_skill3")));
			heroes.setSkill1(cursor.getString(cursor.getColumnIndex("skill1")));
			heroes.setSkill1_desc(cursor.getString(cursor.getColumnIndex("skill1_desc")));
			heroes.setSkill2(cursor.getString(cursor.getColumnIndex("skill2")));
			heroes.setSkill2_desc(cursor.getString(cursor.getColumnIndex("skill2_desc")));
			heroes.setSkill2_expend(cursor.getString(cursor.getColumnIndex("skill2_expend")));
			heroes.setSkill2_add(cursor.getString(cursor.getColumnIndex("skill2_add")));
			heroes.setSkill2_cooling(cursor.getString(cursor.getColumnIndex("skill2_cooling")));
			heroes.setSkill2_damage(cursor.getString(cursor.getColumnIndex("skill2_damage")));
			heroes.setSkill3(cursor.getString(cursor.getColumnIndex("skill3")));
			heroes.setSkill3_desc(cursor.getString(cursor.getColumnIndex("skill3_desc")));
			heroes.setSkill3_expend(cursor.getString(cursor.getColumnIndex("skill3_expend")));
			heroes.setSkill3_add(cursor.getString(cursor.getColumnIndex("skill3_add")));
			heroes.setSkill3_cooling(cursor.getString(cursor.getColumnIndex("skill3_cooling")));
			heroes.setSkill3_damage(cursor.getString(cursor.getColumnIndex("skill3_damage")));
			heroes.setSkill4(cursor.getString(cursor.getColumnIndex("skill4")));
			heroes.setSkill4_desc(cursor.getString(cursor.getColumnIndex("skill4_desc")));
			heroes.setSkill4_expend(cursor.getString(cursor.getColumnIndex("skill4_expend")));
			heroes.setSkill4_add(cursor.getString(cursor.getColumnIndex("skill4_add")));
			heroes.setSkill4_cooling(cursor.getString(cursor.getColumnIndex("skill4_cooling")));
			heroes.setSkill4_damage(cursor.getString(cursor.getColumnIndex("skill4_damage")));
			heroes.setSkill5(cursor.getString(cursor.getColumnIndex("skill5")));
			heroes.setSkill5_desc(cursor.getString(cursor.getColumnIndex("skill5_desc")));
			heroes.setSkill5_expend(cursor.getString(cursor.getColumnIndex("skill5_expend")));
			heroes.setSkill5_add(cursor.getString(cursor.getColumnIndex("skill5_add")));
			heroes.setSkill5_cooling(cursor.getString(cursor.getColumnIndex("skill5_cooling")));
			heroes.setSkill5_damage(cursor.getString(cursor.getColumnIndex("skill5_damage")));
			heroes.setOp_skill(cursor.getString(cursor.getColumnIndex("op_skill")));
			heroes.setTeamwork(cursor.getString(cursor.getColumnIndex("teamwork")));
			heroes.setBest_partner1(cursor.getString(cursor.getColumnIndex("best_partner1")));
			heroes.setPartner_reason1(cursor.getString(cursor.getColumnIndex("partner_reason1")));
			heroes.setBest_partner2(cursor.getString(cursor.getColumnIndex("best_partner2")));
			heroes.setPartner_reason2(cursor.getString(cursor.getColumnIndex("partner_reason2")));
			heroes.setStrongest_opponent1(cursor.getString(cursor.getColumnIndex("strongest_opponent1")));
			heroes.setOpponent_reason1(cursor.getString(cursor.getColumnIndex("opponent_reason1")));
			heroes.setStrongest_opponent2(cursor.getString(cursor.getColumnIndex("strongest_opponent2")));
			heroes.setOpponent_reason2(cursor.getString(cursor.getColumnIndex("opponent_reason2")));
			heroes.setLast_modify_date(cursor.getString(cursor.getColumnIndex("last_modify_date")));
			heroes.setUpdate_time(cursor.getString(cursor.getColumnIndex("update_time")));
			db.close();
			return heroes;
		} else {
			db.close();
			return null;
		}

	}

}
