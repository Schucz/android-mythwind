package com.mfei.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * ���� ��Ҫ��AndroidMainfest.xml�����Ȩ��
 */
public class MainActivity extends Activity {
	// ��ť���
	public static Button btConnect, btOpen, btIsVisible, btSearch;
	// ����������
	public static BluetoothAdapter bluetoothAdapter;
	// UUIDЭ��
	public static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
	// ��������
	public static BluetoothSocket bluetoothSocket;
	// ��������
	public static MainActivity ma;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����û������ȫ����Ϊ�˱��ڹ۲쵱ǰ�����Ƿ�򿪵�״̬�仯
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		//ʵ����ť
		btOpen = (Button) findViewById(R.id.Btn_OpenBt);
		btIsVisible = (Button) findViewById(R.id.Btn_BtIsVisible);
		btSearch = (Button) findViewById(R.id.Btn_SearchDrives);
		btConnect = (Button) findViewById(R.id.Btn_ConnectDrives);
		//Ϊ��ť�󶨼�����
		btOpen.setOnClickListener(buttonListener);
		btIsVisible.setOnClickListener(buttonListener);
		btSearch.setOnClickListener(buttonListener);
		btConnect.setOnClickListener(buttonListener);
		//ʵ������������
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(bluetoothAdapter == null) {
			Toast.makeText(MainActivity.this, "�豸��֧������", Toast.LENGTH_LONG).show();
			finish();
		} else {
			if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {
				btOpen.setText("������");
			} else if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
				btOpen.setText("�ر�����");
			}
			// ע��Receiver����ȡ�����豸��صĽ��
			IntentFilter intent = new IntentFilter();
			intent.addAction(BluetoothDevice.ACTION_FOUND);// Զ���豸���ֶ�����
			intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//Զ���豸�ļ�̬�ı仯������
			intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);//����ɨ�豾��������ģ�ı䶯����
			intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//״̬�ı䶯��
			registerReceiver(searchDevices, intent);//ע�����
		}
	}
	
	private OnClickListener buttonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (MySurfaceView.gameState != MySurfaceView.CONNTCTED) {
				if (v == btOpen) {//��������
					if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {
						bluetoothAdapter.enable();
						btOpen.setText("�ر�����");
					} else if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
						bluetoothAdapter.disable();
						btOpen.setText("������");
					}
				} else if (v == btIsVisible) {//�����Ƿ�ɼ�
					Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 110);
					//�ڶ��������Ǳ������������ֵ�ʱ�䣬ϵͳĬ�Ϸ�Χ[1-300],������ΧĬ��300��С�ڷ�ΧĬ��120
					startActivity(intent);
				} else if (v == btSearch) {//��������
					if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {// ���������û��
						Toast.makeText(MainActivity.this, "���ȴ�����", 1000).show();
						return;
					}
					setTitle("����������ַ��" + bluetoothAdapter.getAddress());
					MySurfaceView.vc_str.removeAllElements();
					bluetoothAdapter.startDiscovery();
				} else if (v == btConnect) {
					if (MySurfaceView.vc_str.size() == 0) {
						Toast.makeText(MainActivity.this, "��ǰû���豸", Toast.LENGTH_LONG).show();
					} else {
						Intent intent = new Intent();//��������������Ҫ�򿪵����Activity
						intent.setClass(MainActivity.this, ChoiceDrivesList.class);
						MainActivity.this.startActivity(intent);
					}
				}
			} else {
				Toast.makeText(MainActivity.this, "����ֻ��һ��Demoʾ�����ܶ����û�н��д���Ϊ�˵ȳ������������쳣��������������Ŀ��", Toast.LENGTH_LONG).show();
				MainActivity.this.finish();
			}
		}
	};
	
	//��������
	private BroadcastReceiver searchDevices = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			//�����豸ʱ��ȡ���豸��MAC��ַ
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				String str = "�豸��" + device.getName() + "*" + device.getAddress();
				if (MySurfaceView.vc_str != null) {
					if (MySurfaceView.vc_str.size() != 0) {
						for (int j = 0; j < MySurfaceView.vc_str.size(); j++) {
							// ��ֹ�ظ����
							if (MySurfaceView.vc_str.elementAt(j).equals(str) == false) {
								// ������ӷ��ֵ��豸���ƺ�mac��ַ
								MySurfaceView.vc_str.addElement(str);
							}
						}
					} else {
						MySurfaceView.vc_str.addElement(str);
					}
				}
			}
		}
	};

	@Override
	protected void onDestroy() {
		if(bluetoothAdapter != null) {
			this.unregisterReceiver(searchDevices);
		}
		super.onDestroy();
		System.exit(0);
	}

}
