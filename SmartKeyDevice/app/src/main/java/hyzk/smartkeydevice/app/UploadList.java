package hyzk.smartkeydevice.app;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.fgtit.data.GlobalData;
import com.fgtit.data.HouseCheckItem;
import com.fgtit.data.HttpConnSoap;
import com.fgtit.data.RoomCheckItem;
import com.fgtit.data.TenantCheckItem;
import com.fgtit.data.XDataIO;
import com.fgtit.data.XmlParase;
import com.fgtit.fmgold.FtpClientActivity;
import com.fgtit.fmgold.R;
import com.fgtit.fmgold.RfidReaderActivity;
import com.fgtit.utils.ExtApi;
import com.fgtit.utils.FileFilterEx;
import com.fgtit.utils.ToastUtil;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;

public class UploadList {
	
	private final static int UPTIME=60000;
	
	private static UploadList instance;
	
	private Context pcontext=null;
	private Timer startTimer=null; 
    private TimerTask startTask; 
    public boolean   isUpload=false;
    
    public String 	tv02,tv04,tv06,tv08,tv09,tv10;
    public int 		fsUpTotal=0;
    public int		fsUpPos=0;
    public long		fsUpSize=0;
	private boolean	isstop=false;
	
	public int 		upcount=0;
	public int 		checkcount=0;
	
    public long	fspicSize=0;
    public File[]	fspicList=null;    
    public File[]	fsdatList=null;    
    private int iCheckno=0;
    
    private Runnable runnableUpload;
    private Handler msgHandler=null;
    
    private int soundId;
	private SoundPool soundPool=null;
    
    public static UploadList getInstance() {
    	if(null == instance) {
    		instance = new UploadList();
    		instance.InitUpload();
    	}
    	return instance;
    }
    
    public void setMainContext(Context context){
    	pcontext=context;
    	soundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(pcontext, R.raw.succeed, 1);
    }
    
    public void PlaySound(){
    	if(soundPool!=null)
    		soundPool.play(soundId, 1.0f, 0.5f, 1, 0, 1.0f);
    }
    
	public void SetMessageHandler(Handler handler){
    	msgHandler=handler;
    }
	
	private void SendMssage(int what,int arg1,int arg2){
		if(msgHandler!=null){
			//msgHandler.obtainMessage(cmd,state,size,buffer).sendToTarget();
			msgHandler.obtainMessage(what, arg1, arg2).sendToTarget();
		}
    }
    
    public void Start(){
    	TimerStart();
    }
    
    public void Stop(){
    	TimerStop();
    }
    
    public void Upload(){
    	if(ExtApi.IsWifi(pcontext)){
    		if(!isUpload){
    			LoadUploadPicFiles();
    			LoadUploadDatFiles();    			
    			tv10="�ļ��ϴ���...";
    			TimerStop();
    			isUpload=true;
				new Thread(runnableUpload).start();
    		}else tv10="��ȴ�,�ļ��ϴ���...";
    	}else tv10="��ʹ��WIFI�ϴ��ļ�.";
    	
    	SendMssage(0,fsUpTotal,0);
    }
    
    public void TimerStart() {
		if(startTimer!=null)
			return;
		
		startTimer = new Timer(); 
        startTask = new TimerTask() { 
            @Override 
            public void run() {
            	checkcount=checkcount+1;
            	if(ExtApi.IsWifi(pcontext)){
            		if(!isUpload){
            			LoadUploadPicFiles();
            			LoadUploadDatFiles();
       					tv10="�ļ��ϴ���...";
       					TimerStop();
       					isUpload=true;
						new Thread(runnableUpload).start();
            		}else tv10="��ȴ�,�ļ��ϴ���...";
            	}else tv10="��ʹ��WIFI�ϴ��ļ�.";
            	SendMssage(0,fsUpTotal,0);
            } 
        }; 
        startTimer.schedule(startTask, UPTIME, UPTIME);         
    }
	
	public void TimerStop() {
    	if (startTimer!=null) {  
    		startTimer.cancel();  
    		startTimer = null;  
    		startTask.cancel();
    		startTask=null;
		}
    }
	
	private void LoadUploadPicFiles(){
		tv02="0";
		tv04="0";
		tv06="0";
		tv08="0";
		tv09="0%";
		tv10="";
		
		fspicSize=0;
		fsUpTotal=0;
		fsUpPos=0;
		fsUpSize=0;
		
		File file = new File(ActivityList.getInstance().DirWork);
		if(file.isDirectory()){
			fspicList = file.listFiles(new FileFilterEx(".jpg"));
			if(fspicList!=null){
				for(int i=0;i<fspicList.length;i++){
					fspicSize=fspicSize+fspicList[i].length();
				}
			}
		}
		fsUpTotal=fspicList.length;
		tv02=String.valueOf(fspicList.length);
		tv04=String.valueOf(fspicSize/1048576)+" MB "+String.valueOf(fspicSize%1048576/1024)+" KB";
	}
	
	private void LoadUploadDatFiles(){
		File file = new File(ActivityList.getInstance().DataWork);
		if(file.isDirectory()){
			fsdatList = file.listFiles(new FileFilterEx(".xml"));
		}
	}
	
	public void InitUpload(){
		runnableUpload = new Runnable(){
		@Override    
		public void run() {
			if(fsdatList!=null){
				if(fsdatList.length>0){
					for(int i=0;i<fsdatList.length;i++){
						if(isstop)
							break;
						
						uploadCheckFile(fsdatList[i].toString());
					}
				}
			}
			
			if(fspicList!=null){
				if(fspicList.length>0){
					for(int i=0;i<fspicList.length;i++){    				
						if(isstop)
							break;
						
						if(uploadFileToNet(fspicList[i].toString())){
							fsUpPos=i;
							SendMssage(1,fsUpPos,1);
							upcount=upcount+1;
							DeleteFile(fspicList[i].toString());
						}else{
			    			SendMssage(1,fsUpPos,0);
							isUpload=false;
							TimerStart();
							return;
						}
					}					
				}
			}
									
			SendMssage(1,fsUpPos,2);
			isUpload=false;
			TimerStart();
		}};
	}

	private void DeleteFile(String filename){
		File f=new File(filename);
		if(f.exists()){
			f.delete();
		} 
	}

    public boolean uploadFileToNet(String fileName){
      	//if(ExtApi.isNetworkConnected(pcontext)){
    	if(ExtApi.IsWifi(pcontext)){
   			 ArrayList<String> arrayList = new ArrayList<String>();  
   			 ArrayList<String> brrayList = new ArrayList<String>();  
   			 ArrayList<String> crrayList = new ArrayList<String>();  
   			 HttpConnSoap Soap = new HttpConnSoap();
         	
   			 arrayList.clear();  
   			 brrayList.clear();
   			 crrayList.clear();
   			 String fsname=ExtApi.getFileName(fileName);
   			 //fsname.indexOf("-")
   			 if(fsname.contains("-")){
   				 String rfid=fsname.substring(0,12);
   				 arrayList.add("filepath");	brrayList.add("d:\\upload\\"+rfid+"\\");
   			 }else{
   				 arrayList.add("filepath");	brrayList.add("d:\\upload\\idcard\\");
   			 }
   			 //arrayList.add("filepath");	brrayList.add("d:\\upload\\");
   			 arrayList.add("filename");	brrayList.add(fsname);
   			 String xmldat="";
   	            try{  
   	                File file=new File(fileName);                
   	                FileInputStream  fis= new FileInputStream(file);
   	                int count=(int) file.length();
   	                byte[] buffer = new byte[count];
   	                //byte[] buffer = new byte[102400];
   	                fis.read(buffer);
   	                xmldat=Base64.encodeToString(buffer,Base64.DEFAULT);
   	                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
   	                //baos.write(buffer, 0, count);  
   	                //xmldat = new String(Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT));  
   	                fis.close();  
   	            }catch(Exception e){  
   	            } 
   	            arrayList.add("filexml");	brrayList.add(xmldat);
   			 			 
   			 crrayList=Soap.GetWebServre(ActivityList.getInstance().WebService,"UploadFileToServer", arrayList, brrayList);
   			 if(crrayList!=null)
   			 if(crrayList.size()>0){
   				 for(int i=0;i<crrayList.size();i++){
   					 if(crrayList.get(i).equals("true")){
   						 //Toast.makeText(FtpClientActivity.this,"�ϴ��ļ��ɹ�", Toast.LENGTH_SHORT).show();
   						 return true;
   					 }
   				 }
   			 }
   			 //Toast.makeText(FtpClientActivity.this,"�ϴ��ļ���", Toast.LENGTH_SHORT).show();
   		 }else{
   			 //Toast.makeText(FtpClientActivity.this,"������Ч,���������", Toast.LENGTH_SHORT).show();
   		 }
   		 return false;
   		 
    }
    
    public boolean uploadHouseCheckToNet(HouseCheckItem hci){
		if(ExtApi.IsWifi(pcontext)){
			 ArrayList<String> arrayList = new ArrayList<String>();  
			 ArrayList<String> brrayList = new ArrayList<String>();  
			 ArrayList<String> crrayList = new ArrayList<String>();  
			 HttpConnSoap Soap = new HttpConnSoap();
    	
			 arrayList.clear();  
			 brrayList.clear();
			 crrayList.clear();
			 arrayList.add("checkid");  brrayList.add(hci.checkid);			 			 
			 arrayList.add("checktime");  brrayList.add(hci.checktime);
			 arrayList.add("houseno");  brrayList.add(String.valueOf(hci.houseno));
			 arrayList.add("houserfid");  brrayList.add(hci.houserfid);
			 arrayList.add("checkmanuser");  brrayList.add(hci.checkmanuser);
			 arrayList.add("checkmanname");  brrayList.add(hci.checkmanname);
			 arrayList.add("checkedroomnum");  brrayList.add(String.valueOf(hci.checkedroomnum));
			 arrayList.add("ckriskroomnum");  brrayList.add(String.valueOf(hci.ckriskroomnum));			 
			 arrayList.add("uncheckedroomnum");  brrayList.add(String.valueOf(hci.uncheckedroomnum));
			 arrayList.add("latitude");	brrayList.add(String.valueOf(ActivityList.getInstance().latitude));
			 arrayList.add("longitude");	brrayList.add(String.valueOf(ActivityList.getInstance().longitude));
			 
			 crrayList=Soap.GetWebServre(ActivityList.getInstance().WebService,"PostHouseCheckItem", arrayList, brrayList);
			 if(crrayList!=null)
			 if(crrayList.size()>0){
				 if(crrayList.get(0).equals("true")){
					 hci.checkno=Integer.valueOf(crrayList.get(1));
					 return true;
				 }
			 }
		 }
		 return false;
	}
	
	public boolean uploadRoomCheckToNet(RoomCheckItem rci){
		if(ExtApi.IsWifi(pcontext)){
			 ArrayList<String> arrayList = new ArrayList<String>();  
			 ArrayList<String> brrayList = new ArrayList<String>();  
			 ArrayList<String> crrayList = new ArrayList<String>();  
			 HttpConnSoap Soap = new HttpConnSoap();
  	
			 arrayList.clear();  
			 brrayList.clear();
			 crrayList.clear();
			 arrayList.add("checkid");  brrayList.add(rci.checkid);	
			 arrayList.add("checktime");  brrayList.add(rci.checktime);
			 arrayList.add("houseno");  brrayList.add(String.valueOf(rci.houseno));
			 arrayList.add("houserfid");  brrayList.add(rci.houserfid);
			 arrayList.add("roomno");  brrayList.add(String.valueOf(rci.roomno));
			 arrayList.add("roomid");  brrayList.add(rci.roomid);
			 
			 arrayList.add("checkmanuser");  brrayList.add(rci.checkmanuser);
			 arrayList.add("checkmanname");  brrayList.add(rci.checkmanname);
			 arrayList.add("checkstatus");  brrayList.add(String.valueOf(rci.checkstatus));
			 arrayList.add("warmtype");  brrayList.add(String.valueOf(rci.warmtype));
			 
			 arrayList.add("alarmstatus");  brrayList.add(String.valueOf(rci.alarmstatus));
			 arrayList.add("alarmno");  brrayList.add(rci.alarmno);
			 arrayList.add("alarmphpath");  brrayList.add(rci.alarmphpath);			 
			 arrayList.add("windscoopstatus");  brrayList.add(String.valueOf(rci.windscoopstatus));
			 arrayList.add("windsphpath");  brrayList.add(rci.windsphpath);
			 arrayList.add("cookerstatus");  brrayList.add(String.valueOf(rci.cookerstatus));
			 arrayList.add("cookerphpath");  brrayList.add(rci.cookerphpath);
			 arrayList.add("chimneystatus");  brrayList.add(String.valueOf(rci.chimneystatus));
			 arrayList.add("chimneyphpath");  brrayList.add(rci.chimneyphpath);
			 arrayList.add("chthreestatus");  brrayList.add(String.valueOf(rci.chthreestatus));
			 arrayList.add("chthreephpath");  brrayList.add(rci.chthreephpath);
			 arrayList.add("safetipstatus");  brrayList.add(String.valueOf(rci.safetipstatus));
			 arrayList.add("safetipphpath");  brrayList.add(rci.safetipphpath);
			 arrayList.add("gasnousstatus");  brrayList.add(String.valueOf(rci.gasnousstatus));
			 arrayList.add("gasnousphpath");  brrayList.add(rci.gasnousphpath);
			 arrayList.add("safebookstatus");  brrayList.add(String.valueOf(rci.safebookstatus));
			 arrayList.add("safebookphpath");  brrayList.add(rci.safebookphpath);
			 arrayList.add("dangernotifystatus");  brrayList.add(String.valueOf(rci.dangernotifystatus));
			 arrayList.add("dangerntyphpath");  brrayList.add(rci.dangerntyphpath);
			 arrayList.add("waterfeestatus");  brrayList.add(String.valueOf(rci.waterfeestatus));
			 arrayList.add("cleanfeestatus");  brrayList.add(String.valueOf(rci.cleanfeestatus));
			 arrayList.add("selforrent");	brrayList.add(String.valueOf(rci.selforrent));
			 arrayList.add("area");	brrayList.add(String.valueOf(rci.area));

			 if(rci.TenantCheckList.size()>0){
				 rci.residentnum=rci.TenantCheckList.size();
				 int count=0;
				 for(int i=0;i<rci.TenantCheckList.size();i++){
					 if(rci.TenantCheckList.get(i).rsidcardphpath.length()>10){
						 count=count+1;
					 }
				 }
				 rci.resicheckednum=count;
				 rci.residentname=rci.TenantCheckList.get(0).realname;
				 rci.residentphone=rci.TenantCheckList.get(0).phone1;
			 }
			 arrayList.add("residentnum");		brrayList.add(String.valueOf(rci.residentnum));
			 arrayList.add("resicheckednum");	brrayList.add(String.valueOf(rci.resicheckednum));
			 arrayList.add("residentname");		brrayList.add(rci.residentname);
			 arrayList.add("residentphone");	brrayList.add(rci.residentphone);
			 
			 
			 crrayList=Soap.GetWebServre(ActivityList.getInstance().WebService,"PostRoomCheckItem", arrayList, brrayList);
			 if(crrayList!=null)
			 if(crrayList.size()>0){
				 if(crrayList.get(0).equals("true")){
					 rci.checkno=Integer.valueOf(crrayList.get(1));
					 iCheckno=rci.checkno;
					 return true;
				 }
			 }
		 }
		 return false;
	}
	
	public boolean uploadTenantCheckToNet(TenantCheckItem tci){
		if(ExtApi.IsWifi(pcontext)){
			 ArrayList<String> arrayList = new ArrayList<String>();  
			 ArrayList<String> brrayList = new ArrayList<String>();  
			 ArrayList<String> crrayList = new ArrayList<String>();  
			 HttpConnSoap Soap = new HttpConnSoap();
   	
			 arrayList.clear();  
			 brrayList.clear();
			 crrayList.clear();
			 arrayList.add("checkno");  brrayList.add(String.valueOf(tci.checkno));
			 arrayList.add("checkid");  brrayList.add(tci.checkid);			 			 
			 arrayList.add("roomid");  brrayList.add(tci.roomid);
			 arrayList.add("checktime");  brrayList.add(tci.checktime);
			 arrayList.add("realname");  brrayList.add(tci.realname);
			 arrayList.add("idcardno");  brrayList.add(tci.idcardno);
			 arrayList.add("phone1");  brrayList.add(tci.phone1);
			 arrayList.add("rsidcardphpath");  brrayList.add(tci.rsidcardphpath);
			       	
			 crrayList=Soap.GetWebServre(ActivityList.getInstance().WebService,"PostTenantCheckItem", arrayList, brrayList);
			 if(crrayList!=null)
			 if(crrayList.size()>0){
				 for(int i=0;i<crrayList.size();i++){
					 if(crrayList.get(i).equals("true")){
						 return true;
					 }
				 }
			 }
		 }
		 return false;
	}
	
	public boolean uploadCheckFile(String filename){
		boolean bOk=false;
		if(ExtApi.IsFileExists(filename)){
			List<HouseCheckItem> list=XmlParase.XmlToHouseCheck(XDataIO.ReadXmlFile(filename));
			if(list.size()>0){
				HouseCheckItem hci=list.get(0);
				//���ύ���������
				for(int i=0;i<hci.RoomCheckList.size();i++){
					if(hci.RoomCheckList.get(i).checkmode>0){
						hci.checkedroomnum++;
					}else{
						hci.uncheckedroomnum++;
					}
					if(hci.RoomCheckList.get(i).alarmno.length()>2){
						hci.ckriskroomnum++;
					}
				}
				
				if(uploadHouseCheckToNet(hci)){
					//����checkno
					bOk=true;
					
					for(int i=0;i<hci.RoomCheckList.size();i++){
						hci.RoomCheckList.get(i).checkno=hci.checkno;
		    			for(int j=0;j<hci.RoomCheckList.get(i).TenantCheckList.size();j++){
		    				hci.RoomCheckList.get(i).TenantCheckList.get(j).checkno=hci.checkno;
		    			}
					}

					for(int i=0;i<hci.RoomCheckList.size();i++){
						if(hci.RoomCheckList.get(i).checkmode>0){//������˲��ϴ�		
							if(uploadRoomCheckToNet(hci.RoomCheckList.get(i))){
		    					for(int j=0;j<hci.RoomCheckList.get(i).TenantCheckList.size();j++){
		    						hci.RoomCheckList.get(i).TenantCheckList.get(j).checkno=iCheckno;
		    						if(uploadTenantCheckToNet(hci.RoomCheckList.get(i).TenantCheckList.get(j))){
		    						}
		    					}
		    				}
						}
	    			}
				}
				
			}			
		}
		if(bOk)
			ExtApi.DeleteFile(filename);
		return bOk;
	}
	
}
