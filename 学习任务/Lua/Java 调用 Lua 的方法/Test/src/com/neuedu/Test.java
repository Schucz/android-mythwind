package com.neuedu;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

public class Test {
	public static void main(String[] args) {
		LuaState L = LuaStateFactory.newLuaState();
		// ����lua��׼��,����һЩlua���������޷�ʹ��
		L.openLibs();
		System.out.println("������Java�������Lua�ű�");
		// ���ؽű�MathStatist.lua,��ִ��
		L.LdoFile("src/MathStatist.lua");
	}
}
