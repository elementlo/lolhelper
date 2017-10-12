package utils;

import java.io.File;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public final class SQLiteUtils {

	private SQLiteDatabase mSQLiteDatabase;
	private Cursor mCursor;
	private StringBuffer mString = new StringBuffer();

	public boolean openOrCreateDatabase(String path) {
		try{
			if(mSQLiteDatabase != null)
				mSQLiteDatabase.close();
			mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path, null);
			return true;
		} catch(Exception exception) {
			return false;
		}
	}

	@SuppressLint("NewApi")
	public boolean deleteDatabase(String path) {
		return SQLiteDatabase.deleteDatabase(new File(path));
	}

	public void close() {
		if(mCursor != null)
			mCursor.close();
		if(mSQLiteDatabase != null)
			mSQLiteDatabase.close();
	}

	public boolean createTable(String tableName, String[] td) {
		try{
			mString.delete(0, mString.length()+1);
			mString.append("create table " + tableName + "(");
			for(int i=0;i<td.length;i++){
				if(i == td.length-1)
					mString.append(td[i] + ")");
				else
					mString.append(td[i] + ",");
			}
			mSQLiteDatabase.execSQL(mString.toString());
			return true;
		} catch(Exception exception) {
			return false;
		}
	}

	public boolean dropTable(String tableName) {
		try{
			mSQLiteDatabase.execSQL("drop table " + tableName);
			return true;
		} catch(Exception exception){
			return false;
		}
	}

	public boolean insert(String tableName, String[] td, String[] values) {
		try{
			mString.delete(0, mString.length()+1);
			mString.append("insert into " + tableName + "(");
			for(int i=0;i<td.length;i++){
				if(i == td.length-1)
					mString.append(td[i] + ") ");
				else
					mString.append(td[i] + ",");
			}
			mString.append("values(");
			for(int i=0;i<values.length;i++){
				if(i == values.length-1)
					mString.append("'" + values[i] + "')");
				else
					mString.append("'" + values[i] + "',");
			}
			mSQLiteDatabase.execSQL(mString.toString());
			return true;
		} catch(Exception exception){
			return false;
		}
	}

	public boolean update(String tableName, String[] td, String[] values, String where) {
		try{
			if(td.length == values.length) {
				mString.delete(0, mString.length()+1);
				mString.append("update " + tableName);
				mString.append(" set ");
				for(int i=0;i<td.length;i++){
					if(i == td.length-1)
						mString.append(td[i] + "=" + "'" + values[i] + "'");
					else
						mString.append(td[i] + "=" + "'" + values[i] + "'" + ",");
				}
				mString.append(" where " + where);
				mSQLiteDatabase.execSQL(mString.toString());
				return true;
			}
			return false;
		} catch(Exception exception){
			return false;
		}
	}

	public boolean delete(String tableName, String where) {
		try{
			mString.delete(0, mString.length()+1);
			mString.append("delete from " + tableName + " where " + where);
			mSQLiteDatabase.execSQL(mString.toString());
			return true;
		} catch(Exception exception){
			return false;
		}
	}

	public Cursor select(String tableName, String[] td, String where, String group, String having, String order, String limit) {
		try{
			mString.delete(0, mString.length()+1);
			mString.append("select ");
			if(td.length == 1)
				mString.append(td[0]);
			else{
				for(int i=0;i<td.length;i++){
					if(i == td.length-1)
						mString.append(td[i]);
					else
						mString.append(td[i] + ",");
				}
			}
			mString.append(" from " + tableName);
			if(where != null)
				mString.append(" where " + where);
			if(group != null)
				mString.append(" group by " + group);
			if(having != null)
				mString.append(" having " + having);
			if(order != null)
				mString.append(" order by " + having);
			if(limit != null)
				mString.append(" limit " + limit);
			return mCursor = mSQLiteDatabase.rawQuery(mString.toString(), null);
		} catch(Exception exception){
			return null;
		}
	}

}
