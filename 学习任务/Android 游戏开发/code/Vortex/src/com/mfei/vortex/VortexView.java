package com.mfei.vortex;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class VortexView extends GLSurfaceView {
	
	private static final String LOG_TAG = VortexView.class.getSimpleName();
	private VortexRenderer renderer;
	
	public VortexView(Context context) {
		super(context);
		renderer = new VortexRenderer();
		setRenderer(renderer);
	}
	
	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		queueEvent(new Runnable() {
			@Override
			public void run() {
				// ���� RGB ������ɫ
				renderer.setColor(event.getX() / getWidth(), event.getY() / getHeight(), 1.0f);
				// ��ת
				renderer.setAngle(event.getX() / 10);
			}
		});
		return true;
	}
	
}
