package com.itcast.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itcast.domain.Person;

public class PersonService {
	private DBHelper dbHelper;
	
	public PersonService(Context context) {
		this.dbHelper = new DBHelper(context);
	}
	
	
	public void transfor() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.beginTransaction(); // ��������
		try {
			db.execSQL("update person set amount = amount - 10 where personid=2");
			db.execSQL("update person set amount = amount + 10 where personid=3");
			// ��һ�仰һ��Ҫ����������������
			db.setTransactionSuccessful();  // ��������ı�ʶΪ True
		} finally {
			db.endTransaction(); // ��������
			// ������ύ���߻ع���������ı�ʶ�����ģ���������ʶΪTrue���ύ������ع���Ĭ��ΪFalse
		}
	}
	
	/**
	 *  ��Ӽ�¼
	 * @param person
	 */
	public void save(Person person) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("insert into person(name, phone, amount) values(?, ?, ?)", 
				new Object[] { person.getName(), person.getPhone(), person.getAmount() } );
	}
	
	/**
	 * ɾ����¼
	 * @param id
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("delete from person where personid = ?", new Object[] { id });
	}
	
	/**
	 * ���¼�¼
	 * @param person
	 */
	public void update(Person person) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("update person set name = ?, phone = ?, amount = ? where personid = ?", 
				new Object[] { person.getName(), person.getPhone(), person.getAmount(), person.getId() });
	}
	
	/**
	 *  ���� ID ��ѯ��¼
	 * @param id
	 * @return
	 */
	public Person findById(Integer id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where personid = ?", new String[] { id.toString() } );
		if(cursor.moveToFirst()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			return new Person(personid, name, phone, amount);
		}
		cursor.close();
		return null;
	}
	
	/**
	 *  ��ҳ��ѯ
	 * @param offset ����ǰ���������¼
	 * @param maxResult ÿҳ��������¼
	 * @return
	 */
	public List<Person> findScrollData(int offset, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person order by personid asc limit ?, ?", 
				new String[] { String.valueOf(offset), String.valueOf(maxResult) } );
		while(cursor.moveToNext()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int amount = cursor.getInt(cursor.getColumnIndex("amount"));
			persons.add(new Person(personid, name, phone, amount));
		}
		cursor.close();
		return persons;
	}
	
	/**
	 *  ��ȡ��¼����
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
	
}
