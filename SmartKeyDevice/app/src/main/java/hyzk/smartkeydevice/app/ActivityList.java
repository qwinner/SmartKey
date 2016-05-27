package hyzk.smartkeydevice.app;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

//public class ActivityList extends Application{
public class ActivityList {	
	private List<Activity> activityList = new LinkedList<Activity>();	
	private static ActivityList instance;
	private Context pcontext;
	private Context ccontext;
	
	public String	DirWork="/sdcard/Gold";					//
	public String 	TmpWork="/sdcard/fgtit";
	public String	CacheWork="/sdcard/Gold/Cache";
	public String	DataWork="/sdcard/Gold/Data";	
	public String	FileCheckList="/CheckList.xml";			//
	public String	FilePersonList="/UsersList.xml";		//
	public String	FilePictureList="/PictureList.xml";		//
	
	public boolean  islocal=false;
	public double   latitude=0.0;
	public double   longitude=0.0;
	public float	radius=0.0f;
	public float	direction=0.0f;
	
	public String   CheckRange="";
	public boolean 	SaveIndex=false;
	
	public String 	RFID,RoomID;
	public int		RoomIndex;
		
	public String	DeviceSN="";
	public String	UpdateUrl;
	public String	WebAddr;
	public String	WebService;	//XML
	public String	WebHandler;	//JSON
	
	public boolean	isonline=true;
	public String 	DefaultUser;	//ȱʡ��¼�û�
	public String	CheckDate;	
	public long		RxTxBytes=0;
	
	public boolean TestMode=false;
	
	private ActivityList(){ 
    	//LoadConfig();
    }
    
  //����ģʽ�л�ȡΨһ��MyApplicationʵ��
    public static ActivityList getInstance() {
    	if(null == instance) {
    		instance = new ActivityList();
    	}
    	return instance;
    }

    public void setMainContext(Context context){
    	pcontext=context;
    }
    
    public void setCurrContext(Context context){
    	ccontext=context;
    }
    
    public void Relogon(){
    	for(Activity activity:activityList) {
    		activity.finish();
    	}
    }
    
    public Context getCurrContext(){
    	return ccontext;
    }
    
    //���Activity��������
    public void addActivity(Activity activity){
    	activityList.add(activity);
    }
    
    public void removeActivity(Activity activity){
    	activityList.remove(activity);
    }
    
    //��������Activity��finish
    public void exit(){
    	//SaveConfig();
    	for(Activity activity:activityList) {
    		activity.finish();
    	}
    	System.exit(0);
    }
    
    public void SaveConfigByVal(String name,String val){
    	SharedPreferences sp;
		sp = PreferenceManager.getDefaultSharedPreferences(pcontext);
		Editor edit=sp.edit();
		edit.putString(name,val);
		edit.commit();
    }
    
	//��������
	public void SaveConfig(){
    	SharedPreferences sp;
		sp = PreferenceManager.getDefaultSharedPreferences(pcontext);
		Editor edit=sp.edit();
				
		edit.putString("WebAddr",WebAddr);
		edit.putString("UpdateUrl",UpdateUrl);
		//edit.putString("WebService",WebService);
		//edit.putString("WebHandler",WebHandler);
		edit.putString("DefaultUser",DefaultUser);
		edit.putBoolean("IsOnline", isonline);
		
		edit.commit();

    }
    
	//��ȡ����
    public void LoadConfig(){
    	SharedPreferences sp;
		sp = PreferenceManager.getDefaultSharedPreferences(pcontext);
		
		//123.127.52.227
		//http://www.coiot.cn/
		//www.coiot.cn
		
		WebAddr=sp.getString("WebAddr","http://www.coiot.cn/");		
		UpdateUrl=sp.getString("UpdateUrl","http://www.coiot.cn/apk/update.xml");
		//WebService=sp.getString("WebService","http://www.coiot.cn/FingermapWebApp/FingermapService.asmx");
		//WebHandler=sp.getString("WebHandler","http://www.coiot.cn/FingermapWebApp/FingermapHandler.FP");
		WebService=WebAddr+"FingermapWebApp/FingermapService.asmx";
		WebHandler=WebAddr+"FingermapWebApp/FingermapHandler.FP";

		
		DefaultUser=sp.getString("DefaultUser","admin");
		isonline=sp.getBoolean("IsOnline", true);
		
		//android.net.TrafficStats.getMobileRxBytes();
		//android.net.TrafficStats.getMobileTxBytes();
		
		RxTxBytes=android.net.TrafficStats.getMobileRxBytes()+android.net.TrafficStats.getMobileTxBytes();
    }
    
    public long getMobileRxBytes(){  //��ȡͨ��Mobile�����յ����ֽ������WiFi  
        return android.net.TrafficStats.getMobileRxBytes()==android.net.TrafficStats.UNSUPPORTED?0:(android.net.TrafficStats.getMobileRxBytes()/1024);  
    }     
    
}