package hyzk.smartkeydevice.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;

public class GlobalData {

	public static PositionItem	positionItem=new PositionItem();	//λ����Ϣ
	
	public static AdminItem 		adminItem=new AdminItem();			//��ǰ��¼����Ա
	public static List<AdminItem> 	adminList=null;						//���ڱ��صĹ���Ա�б�
	
	public static HouseItem 		houseItem=new HouseItem();			//��ǰ����
	public static HouseCheckItem 	houseCheckItem=new HouseCheckItem();//��ǰ���ݼ��
	public static int				housecheckstatus=0;					//���ݼ��״̬
	
	public static List<HistoryItem> 	historyList=null;	
	public static List<HouseIndex>	houseIndexList=null;
	//public static List<HouseItem>	houseItemList=null;	
	
	public static List<HouseItem>		localHouseList=null;	//����ģʽ��ʹ��
	public static List<HouseCheckItem>	loaclCheckList=null;	//����ģʽ��ʹ��
	
	public static boolean LoadPositionList(){
		if(FileIsExists("/sdcard/Gold/","/sdcard/Gold/positionlist.xml")){
			positionItem=XmlParase.XmlToPositionItem(XDataIO.ReadXmlFile("/sdcard/Gold/positionlist.xml"));
			return true;
		}else{
			return false;
		}
	}
	
	public static void SavePositionList(){
		XDataIO.WriteXmlFile(XmlParase.PositionItemToXml(positionItem),"/sdcard/Gold/positionlist.xml",/*"gb2312"*/"utf-8");
	}
	
	public static void LoadAdminList(){
		if(FileIsExists("/sdcard/Gold/","/sdcard/Gold/adminlist.xml")){
			adminList=XmlParase.XmlToAdminList(XDataIO.ReadXmlFile("/sdcard/Gold/adminlist.xml"));
		}else{
			adminList=new ArrayList<AdminItem>();
		}
	}
	
	public static void SaveAdminList(){
		XDataIO.WriteXmlFile(XmlParase.AdminListToXml(adminList),"/sdcard/Gold/adminlist.xml",/*"gb2312"*/"utf-8");
	}

    public static boolean AddendAdminItem(AdminItem ai){
    	if(adminList==null){
    		return false;
    	}
    	boolean bfind=false;
    	for(int i=0;i<adminList.size();i++){
    		if(adminList.get(i).username.equals(ai.username)){
    			bfind=true;
    			break;
    		}
    	}
    	if(bfind){
    		return false;
    	}else{
    		adminList.add(ai);
    		return true;
    	}
    }
    
    public static void DeleteAdminItem(String username) {
    	List<AdminItem> list=new ArrayList<AdminItem>();
    	list.addAll(adminList);
    	
    	for(int i=0;i<adminList.size();i++) {
    		if(adminList.get(i).username.equals(username)) {
    			list.remove(adminList.get(i));
    		}
    	}
    	
    	adminList.clear();
    	adminList.addAll(list);
    }
    
    public static void UpdateAdminItemEx(AdminItem ai){
    	DeleteAdminItem(ai.username);
    	adminList.add(ai);
    }
    
    public static void UpdateAdminItem(AdminItem ai){
    	boolean bupdate=false;
    	for(int i=0;i<adminList.size();i++){
    		if(adminList.get(i).username.equals(ai.username)){
    			//AdminList.get(i).setUserName(ai.getUserName());
    			adminList.get(i).password=ai.password;
    			adminList.get(i).realname=ai.realname;
    			adminList.get(i).idcardno=ai.idcardno;
    			adminList.get(i).checklevel=ai.checklevel;
    			adminList.get(i).checkrange=ai.checkrange;
    			adminList.get(i).userstatus=ai.userstatus;
    			adminList.get(i).usertype=ai.usertype;
    			adminList.get(i).setTempStr1(ai.getTempStr1());
    			adminList.get(i).setTempStr2(ai.getTempStr2());
    			adminList.get(i).setPhotoStr(ai.getPhotoStr());
    			bupdate=true;
    			break;
    		}
    	}
    	if(!bupdate){
    		GlobalData.adminList.add(ai);
    	}
    }
    
    //History
    public static void LoadHistoryList(){
		if(FileIsExists("/sdcard/Gold/","/sdcard/Gold/history.json")){
			historyList=JsonParase.JsonToHistoryList(XDataIO.ReadJsonFile("/sdcard/Gold/history.json"));
		}else{
			historyList=new ArrayList<HistoryItem>();
		}
	}
	
	public static void SaveHistoryList(){
		XDataIO.WriteJsonFile(JsonParase.HistoryListToJson(historyList),"/sdcard/Gold/history.json");
	}
	
	public static boolean FileIsExists(String sDir,String sFile) {
		 File file = new File(sDir);
		 List<Uri> fsList = new ArrayList<Uri>();
		 if(file.isDirectory()) {
			 File[] all_file = file.listFiles();
			 if (all_file != null) {
				 for(int i=0;i<all_file.length;i++){
					 fsList.add(Uri.parse(all_file[i].toString()));
				 }
			 }
		 } 
		 for(int i=0;i<fsList.size();i++) {
	    	if(fsList.get(i).equals(Uri.parse(sFile)))
	    		return true;
		 }
		 return false;
	}
	 
	//Ϊ�˿���,û�жԱ������з�װ.����ֱ�ӷ������ڵı���
	/*
	private static GlobalData instance;
	
	private AdminItem 	AdminInfo=new AdminItem();
	private LordnoItem 	LordnoInfo=new LordnoItem();
	private boolean aisload=false;
	private boolean lisload=false;
		
	public static GlobalData getInstance() {
    	if(null == instance) {
    		instance = new GlobalData();
    	}
    	return instance;
    }
	
	//
	public AdminItem GetAdminInfo(){
		if(aisload){
			return AdminInfo;
		}else{
			return null;
		}
	}
	
	public void SetAdminInfo(AdminItem item){		
		AdminInfo=item;
		aisload=true;
	}
	
	public void ClearAdminInfo(){
		aisload=false;
	}
	
	//
	public LordnoItem GetLordnoInfo(){
		if(lisload){
			return LordnoInfo;
		}else{
			return null;
		}
	}
	
	public void SetLordnoInfo(LordnoItem item){
		LordnoInfo=item;
		lisload=true;
	}
	
	public void ClearLordnoInfo(){
		lisload=false;
	}
	
	*/
}
