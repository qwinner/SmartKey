package hyzk.smartkeydevice.modle;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSSender {
	Context pcontext;
	  
	public void SendSmsText(Context context,String mobile,String text) {
		pcontext=context;		  
		if(!ValidateCfg(mobile,text))
			return;
		SmsManager.getDefault().sendTextMessage(mobile.trim(),null,text,null,null);
		Toast toast=Toast.makeText(pcontext, "���ŷ��ͳɹ�!",Toast.LENGTH_LONG);
		toast.show();
	}
	
	private boolean ValidateCfg(String mobile,String text){
		if(mobile.equals("")){
			Toast toast=Toast.makeText(pcontext, "�ֻ���벻��Ϊ��!",Toast.LENGTH_LONG);
			toast.show();
			return false;
		}
		//else if(!checkMobile(mobile)){
		else if(!isCheckMobile(mobile)){
			Toast toast=Toast.makeText(pcontext, "������ĵ绰���벻��ȷ����������!",Toast.LENGTH_LONG);
			toast.show();
			return false;
		}
		else if(text.equals("")){
			Toast toast=Toast.makeText(pcontext, "�������ݲ���Ϊ������������!",Toast.LENGTH_LONG);
			toast.show();
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
	*�й��ƶ�134.135.136.137.138.139.150.151.152.157.158.159.187.188 ,147(��ݿ�����֤)
	*�й���ͨ130.131.132.155.156.185.186
	*�й����133.153.180.189
	CDMA 133,153
	�ֻ������֤ �ʺ�Ŀǰ���е��ֻ�
	*/
	public boolean checkMobile(String mobile){
		//String regex="^1(3[0-9]|5[012356789]|8[0789])\d{8}$";
		String regex = "^((13[0-9])|(15[^4])|(18[0,2,5-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		return m.find();
	}

	public static boolean isCheckMobile(String mobile) {
        boolean isCheck = true;
        String regex = "^((13[0-9])|(15[^4])|(18[0,2,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
        if (!m.matches()) {
            isCheck = false;
        }
        return isCheck;
    }
}
