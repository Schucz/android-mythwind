package com.apress;

import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// NFC ������
	NfcAdapter mNfcAdapter;
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		textView = (TextView) findViewById(R.id.textView);
		
		// ����Ƿ���NFC�����������һ���ֻ��豸Ĭ�ϵ�NfcAdapter
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		// �ֻ��豸��֧�� NFC
		if (mNfcAdapter == null) {
			Toast.makeText(this, "�豸��֧��NFC", Toast.LENGTH_LONG).show();
			textView.setText("�豸��֧��NFC");
			return;
		}
		// �ֻ��豸û������NFC����
		if (!mNfcAdapter.isEnabled()) {
			Toast.makeText(this, "����ϵͳ������������NFC����", Toast.LENGTH_LONG).show();
			textView.setText("����ϵͳ������������NFC����");
			return;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// �õ��Ƿ��⵽ACTION_NDEF_DISCOVERED����
		// NfcAdapter.ACTION_TAG_DISCOVERED
		// NfcAdapter.ACTION_TECH_DISCOVERED
		// NDEF��NFC ���ݽ�����ʽ
		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())) {
			// ����ɨ�赽�� NdefMessage
			processIntent(getIntent());
		}
	}

	// �ַ�����ת��Ϊ16�����ַ���
	private String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("0x");
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
			buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
			System.out.println(buffer);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}

	// �ؼ�������������ɨ�赽��NdefMessage
	private void processIntent(Intent intent) {
		Parcelable[] rawMsgs = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		// ��ȡɨ�赽��һ�� NdefMessage �ĵ�һ��ֵ
		NdefMessage msg = (NdefMessage) rawMsgs[0];
		// record 0 contains the MIME type, record 1 is the AAR, if present
		textView.setText(new String(msg.getRecords()[0].getPayload()));

		// ȡ����װ��intent�е�TAG
		Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		for (String tech : tagFromIntent.getTechList()) {
			System.out.println(tech);
		}
		boolean auth = false;
		// �ǽӴ�ʽ����������ȡTAG
		MifareClassic mfc = MifareClassic.get(tagFromIntent);
		try {
			String metaInfo = "";
			// ����I/O���������TagTechnology��������ȡ����
			mfc.connect();
			// ��ȡTAG������
			int type = mfc.getType();
			// ��ȡTAG�а�����������
			int sectorCount = mfc.getSectorCount();
			String typeS = "";
			switch (type) {
			// ���俨
			case MifareClassic.TYPE_CLASSIC:
				typeS = "TYPE_CLASSIC";
				break;
			case MifareClassic.TYPE_PLUS:
				typeS = "TYPE_PLUS";
				break;
			case MifareClassic.TYPE_PRO:
				typeS = "TYPE_PRO";
				break;
			// δ֪�����Ǽ��ݿ�
			case MifareClassic.TYPE_UNKNOWN:
				typeS = "TYPE_UNKNOWN";
				break;
			}
			metaInfo += "��Ƭ���ͣ�" + typeS + "\n��" + sectorCount + "������\n��"
					+ mfc.getBlockCount() + "����\n�洢�ռ�: " + mfc.getSize() + "B\n";
			for (int j = 0; j < sectorCount; j++) {
				// Authenticate a sector with key A.
				auth = mfc.authenticateSectorWithKeyA(j, MifareClassic.KEY_DEFAULT);
				int bCount;
				int bIndex;
				if (auth) {
					metaInfo += "Sector " + j + ":��֤�ɹ�\n";
					// ��ȡ�����еĿ�
					bCount = mfc.getBlockCountInSector(j);
					bIndex = mfc.sectorToBlock(j);
					for (int i = 0; i < bCount; i++) {
						byte[] data = mfc.readBlock(bIndex);
						metaInfo += "Block " + bIndex + " : "
								+ bytesToHexString(data) + "\n";
						bIndex++;
					}
				} else {
					metaInfo += "Sector " + j + ":��֤ʧ��\n";
				}
			}
			textView.setText(metaInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����Activity�෽��������Intent�����¼�
	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
	}

	public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
		byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				mimeBytes, new byte[0], payload);
		return mimeRecord;
	}

}
