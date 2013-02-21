package com.sundy.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ShortcutSampleActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ��ȡ�������Ӧ�ó���� ACTION
		if(getIntent().getAction().equals(Intent.ACTION_CREATE_SHORTCUT)) {
			Intent returnIntent = new Intent();
			returnIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Mythwind");
			returnIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, 
					Intent.ShortcutIconResource.fromContext(
							this, R.drawable.ic_launcher));
			// ����¼�
			returnIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, 
					new Intent(this, LauncherDemoActivity.class));
			setResult(RESULT_OK, returnIntent);
			finish();
		}
		
	}
	
}
