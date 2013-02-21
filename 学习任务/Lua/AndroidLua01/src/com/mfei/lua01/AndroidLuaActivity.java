package com.mfei.lua01;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidLuaActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView txtView = (TextView) this.findViewById(R.id.textview);
		
		LuaState L = LuaStateFactory.newLuaState();
		L.openLibs();
		L.LdoString(getLuaString("lua/hello.lua"));
		// ʵ��1.Java����lua����
		L.getField(LuaState.LUA_GLOBALSINDEX, "plus");
		// ȡ��lua���plus����
		L.pushNumber(12);
		// ���ݲ���1��plus����
		L.pushNumber(2132);
		// ���ݲ���2��plus����
		L.call(2, 1);
		// ����plus����
		L.setField(LuaState.LUA_GLOBALSINDEX, "a");
		// �������Ľ�����浽һ������a��
		LuaObject obj = L.getLuaObject("a");
		// ȡ�ò���a
		txtView.setText("" + obj.getString());
		// ��ӡ����a��ֵ��TextView��
		// lua����Java����
		Value value = new Value();
		L.getField(LuaState.LUA_GLOBALSINDEX, "heihei");
		// ��ȡ(����˵���Ƕ�λ��)lua��heihei����
		try {
			L.pushObjectValue(value);
			// ��value���󴫵ݸ�heihei����
		} catch (LuaException e) {
		}
		L.call(1, 1);
		// ����heihei����
		L.setField(LuaState.LUA_GLOBALSINDEX, "v");
		// ����������浽����v��
		LuaObject v = L.getLuaObject("v");
		// ��ȡ����v
		try {
			txtView.setText("" + v.getObject());
			// ��ӡ��־
		} catch (LuaException e) {
		}
		L.close(); // �ر�lua
	}

	// �ٺ٣�ע�⿩����������~�����������������ȡlua�ļ���
	// Ȼ�󱣴浽һ���ַ������Ȼ�������������������ֻҪ�ܱ�����ַ�����
	private String getLuaString(String resPath) {
		InputStream isread = null;
		byte[] luaByte = new byte[1];
		try {
			// ���������ˣ����ǰ�lua ���ŵ�assetĿ¼�£�����ϵͳ�� //�����Ҳ����ļ�·���ˣ���~
			isread = this.getAssets().open(resPath);
			int len = isread.available();
			luaByte = new byte[len];
			isread.read(luaByte);
		} catch (IOException e1) {
		} finally {
			if (isread != null) {
				try {
					isread.close();
				} catch (IOException e) {
				}
			}
		}
		return EncodingUtils.getString(luaByte, "GBK");
	}

	// һ���򵥵��ڲ���
	class Value {
		private int num = 0;
		public void inc() {
			num++;
		}
		public String toString() {
			return "num is " + num;
		}
	}

}