package hyzk.smartkeydevice.modle;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction()))
		{
			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();
			if (bundle != null)
			{
				Object[] objArray = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[objArray.length];
				for (int i = 0; i < objArray.length; i++)
				{
					messages[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
				}
				for (SmsMessage currentMessage : messages)
				{
					sb.append("������Դ:");
					sb.append(currentMessage.getDisplayOriginatingAddress());
					sb.append("\n------��������------\n");
					sb.append(currentMessage.getDisplayMessageBody());
				}
			}
			//Intent mainIntent = new Intent(context, LogonActivity.class);
			//mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//context.startActivity(mainIntent);
			Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
