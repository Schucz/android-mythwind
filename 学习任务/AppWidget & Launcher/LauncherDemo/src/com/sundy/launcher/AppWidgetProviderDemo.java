package com.sundy.launcher;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class AppWidgetProviderDemo extends AppWidgetProvider {

	private static final String TAG = "AppWidgetSample";
	
	/**
	 *  ɾ��һ�� appwidget �õ�����Ϣ
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Log.i(TAG, "----- onDeleted -----");
	}

	/**
	 *   ���һ�� appwidget ��ɾ��
	 */
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Log.i(TAG, "----- onDisabled -----");
	}

	/**
	 *  �״���� appwidget ʱ����յ���Ϣ
	 */
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.i(TAG, "----- onEnabled -----");
	}
	
	/**
	 * recevier Ĭ�ϵ���Ϣ��������
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.i(TAG, "----- onReceive -----");
	}

	/**
	 * ����ʱ������ʱ����յ�����Ϣ��appwidgetservice����������Ϣ
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i(TAG, "----- onUpdate -----");
		
		// ����Զ�̽���(launcher���ҵ����е�appwidget)
		for (int i = 0; i < appWidgetIds.length; i++) {
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_appwidget);
			views.setTextViewText(R.id.widgetbutton, "sundyClick");
			// �����¼�
			views.setOnClickPendingIntent(R.id.widgetbutton, 
					PendingIntent.getActivity(context, 0, new Intent(context, LauncherDemoActivity.class), 0));
			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
		}
	}
	
}
