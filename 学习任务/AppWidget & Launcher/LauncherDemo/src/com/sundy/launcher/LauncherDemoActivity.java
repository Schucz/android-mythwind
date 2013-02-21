package com.sundy.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LauncherDemoActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.button);
        // �����ť������ݷ�ʽ
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				returnIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
				returnIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "AutoMythwind");
				returnIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, 
						Intent.ShortcutIconResource.fromContext(
								LauncherDemoActivity.this, R.drawable.ic_launcher));
				// ����¼�
				returnIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, 
						new Intent(LauncherDemoActivity.this, LauncherDemoActivity.class));
				// ���͹㲥
				sendBroadcast(returnIntent);
			}
		});
    }
    
}
