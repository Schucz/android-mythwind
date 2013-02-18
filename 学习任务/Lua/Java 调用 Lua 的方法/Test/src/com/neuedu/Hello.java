package com.neuedu;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class Hello {
	public static void main(String[] args) {
		LuaState lua = LuaStateFactory.newLuaState();
		// ����lua��׼��,����һЩlua���������޷�ʹ��
		lua.openLibs();
		// doFile
		lua.LdoFile("src/test01.lua");
		// ---------------------------------------------ֵ���ݲ���
		// �ҵ����� sum
		lua.getField(LuaState.LUA_GLOBALSINDEX, "sum");
		// ����1ѹջ
		lua.pushNumber(100);
		// ����2ѹջ
		lua.pushNumber(50);
		// ���ã���2������1������ֵ
		lua.call(2, 1);
		// ���淵��ֵ��result��
		lua.setField(LuaState.LUA_GLOBALSINDEX, "result");
		// ����result
		LuaObject lobj = lua.getLuaObject("result");
		// ��ӡ���
		System.out.println(lobj.getNumber());
		// ---------------------------------------------���󴫵ݲ���
		Value v = new Value();
		lua.getField(LuaState.LUA_GLOBALSINDEX, "test1");
		try {
			lua.pushObjectValue(v);
		} catch (LuaException e) {
			e.printStackTrace();
		}
		lua.call(1, 0);
		v.print();
	}
	
}

class Value {
	private int i;
	public void init(){
		i = 111;
	}
	public void print(){
		System.out.println(i);
	}
}
