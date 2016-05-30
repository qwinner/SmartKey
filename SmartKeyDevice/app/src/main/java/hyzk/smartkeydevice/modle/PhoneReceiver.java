//package hyzk.smartkeydevice.modle;
//
//import java.lang.reflect.Method;
//
//import com.android.internal.telephony.ITelephony;
//
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.os.RemoteException;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//
//public class PhoneReceiver  extends BroadcastReceiver{
//	  private static final String TAG = "PhoneStatReceiver";
//	  private static boolean incomingFlag = false;
//	  private static String incoming_number = null;
//	  private Context mycon;
//	  public static int setting_nodesrap=0;
//
//	  @Override
//	  public void onReceive(Context context, Intent intent) {
//	          if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
//	                  incomingFlag = false;
//	                  String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
//	                  Log.i(TAG, "call OUT:"+phoneNumber);
//	          }else{
//	                  TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
//	                  switch (tm.getCallState()){
//	                  case TelephonyManager.CALL_STATE_RINGING:
//	                          incomingFlag = true;
//	                          incoming_number= intent.getStringExtra("incoming_number");
//	                          if(setting_nodesrap==1)  {
//	                                 stopCall(incoming_number);
//	                                 abortBroadcast();
//	                          }
//	                          Log.i(TAG, "RINGING :"+ incoming_number);
//	                          break;
//	                  case TelephonyManager.CALL_STATE_OFFHOOK:
//	                          if(incomingFlag){
//	                                  Log.i(TAG, "incoming ACCEPT :"+ incoming_number);
//	                          }
//	                          break;
//	                  case TelephonyManager.CALL_STATE_IDLE:
//	                          if(incomingFlag){
//	                                  Log.i(TAG, "incoming IDLE");
//	                          }
//	                          break;
//	                  }
//	          }
//	  }
//
//	  public void stopCall(String incoming_number) {
//          AudioManager mAudioManager = (AudioManager) mycon.getSystemService(Context.AUDIO_SERVICE);
//          mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//          ITelephony iTelephony = getITelephony(mycon);
//          try {
//        	  iTelephony.endCall();
//          } catch (RemoteException e) {
//              e.printStackTrace();
//          }
//          mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//          Log.i("----", "���� :"+ incoming_number);
//	  }
//
//	  private static ITelephony getITelephony(Context context) {
//	      TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//	      Class<TelephonyManager> c = TelephonyManager.class;
//	      Method getITelephonyMethod = null;
//	      ITelephony iTelephony=null;
//
//	      try {
//	          getITelephonyMethod = c.getDeclaredMethod("getITelephony",(Class[]) null);
//	          getITelephonyMethod.setAccessible(true);
//	      } catch (SecurityException e) {
//	          e.printStackTrace();
//	      } catch (NoSuchMethodException e) {
//	          e.printStackTrace();
//	      }
//
//	      try {
//	          iTelephony = (ITelephony) getITelephonyMethod.invoke(mTelephonyManager, (Object[]) null);
//	          return iTelephony;
//	      } catch (Exception e) {
//	          e.printStackTrace();
//	      }
//	      return iTelephony;
//	  }
//
//}
//
