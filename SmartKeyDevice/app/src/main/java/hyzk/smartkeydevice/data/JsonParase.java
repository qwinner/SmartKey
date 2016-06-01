package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParase {
	
	public static String LordnoItemToJson(LordnoItem li){
		String jsonresult="";
		JSONObject object = new JSONObject();//����һ���ܵĶ��������������json��
		 try {  
            JSONArray jsonarray1 = new JSONArray();	//json���飬����������ΪLordnoItem�����ж���
            JSONObject jsonObj1 = new JSONObject();	//LordnoItem����json��ʽ  
            jsonObj1.put("id", li.id);	//��LordnoItem�����������ֵ  
            jsonObj1.put("rfid", li.rfid);
            jsonObj1.put("name", li.name);
            
            JSONArray jsonarray2 = new JSONArray();
            for(int i=0;i<li.CheckList.size();i++){
            	JSONObject jsonObj2 = new JSONObject();
            	jsonObj2.put("RoomID", li.CheckList.get(i).RoomID);
            	jsonObj2.put("Check", li.CheckList.get(i).Check);
            	JSONArray jsonarray3 = new JSONArray();
            	for(int j=0;j<li.CheckList.get(i).UsersList.size();j++){
            		JSONObject jsonObj3 = new JSONObject();
            		jsonObj3.put("name",li.CheckList.get(i).UsersList.get(j).name);
            		jsonObj3.put("phone",li.CheckList.get(i).UsersList.get(j).phone);
            		jsonObj3.put("idcard",li.CheckList.get(i).UsersList.get(j).idcard);
            		jsonarray3.put(jsonObj3);
            	}
            	jsonObj2.put("UserItem", jsonarray3);
            	jsonarray2.put(jsonObj2);
            }
            jsonObj1.put("CheckItem", jsonarray2);
            jsonarray1.put(jsonObj1);//��json�����������LordnoItem����  
            object.put("LordnoItem", jsonarray1);//���ܶ���������Ӱ�LordnoItem������  
            jsonresult = object.toString();//��ɷ����ַ�  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
		return jsonresult;
	}
	
	public static LordnoItem JsonToLordnoItem(String json){
		LordnoItem li=new LordnoItem();
		if(json.startsWith("error")){//���������һ�¼�⣬�����json��ʽ�ľ�ֱ�ӷ���  
            return null;  
        }  
        try {  
            JSONObject jsonObject=new JSONObject(json); 
            JSONArray jsonArray1=jsonObject.getJSONArray("LordnoItem");  
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);  
            li.id=jsonObject1.getString("id");  
            li.rfid=jsonObject1.getString("rfid");  
            li.name=jsonObject1.getString("name");            
            JSONArray jsonArray2=jsonObject1.getJSONArray("CheckItem");
            for(int i=0;i<jsonArray2.length();i++){
            	JSONObject jsonObject2 = jsonArray2.getJSONObject(i);
            	CheckItem ci=new CheckItem();
            	ci.RoomID=jsonObject2.getString("RoomID");
            	ci.Check=jsonObject2.getInt("Check");
            	JSONArray jsonArray3=jsonObject2.getJSONArray("UserItem");
            	for(int j=0;j<jsonArray3.length();j++){
            		JSONObject jsonObject3 = jsonArray3.getJSONObject(j);
            		UserItem ui=new UserItem();
            		ui.name=jsonObject3.getString("name");
            		ui.phone=jsonObject3.getString("phone");
            		ui.idcard=jsonObject3.getString("idcard");
            		ci.UsersList.add(ui);
            	}            	
            	li.CheckList.add(ci);
            }

        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
		return li;
	}
	
	//��ʷ��¼
	public static String HistoryListToJson(List<HistoryItem> his){
		String jsonresult="";
		JSONObject object = new JSONObject();					//����һ���ܵĶ��������������json��
		 try {
			 for(int i=0;i<his.size();i++){
				 JSONArray jsonarray1 = new JSONArray();		//json���飬����������ΪHistoryItem�����ж���
				 JSONObject jsonObj1 = new JSONObject();		//HistoryItem����json��ʽ  
				 jsonObj1.put("checkid", his.get(i).checkid);	//��HistoryItem�����������ֵ  
				 jsonObj1.put("checkname", his.get(i).checkname);
				 jsonObj1.put("checktime", his.get(i).checktime);
				 jsonarray1.put(jsonObj1);						//��json�����������HistoryItem����  
				 object.put("HistoryItem", jsonarray1);			//���ܶ���������Ӱ�HistoryItem������  
				 jsonresult = object.toString();				//��ɷ����ַ�  
			 }
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
		return jsonresult;
	}
	
	public static List<HistoryItem> JsonToHistoryList(String json){
		List<HistoryItem> his=new ArrayList<HistoryItem>();		
		if(json.startsWith("error")){							//���������һ�¼�⣬�����json��ʽ�ľ�ֱ�ӷ���  
            return null;  
        }  
        try {
            JSONObject jsonObject=new JSONObject(json); 
            JSONArray jsonArray1=jsonObject.getJSONArray("HistoryItem");
            for(int i=0;i<jsonArray1.length();i++){
            	HistoryItem hi=new HistoryItem();
            	JSONObject jsonObject1 = jsonArray1.getJSONObject(i);  
                hi.checkid=jsonObject1.getString("checkid");  
                hi.checkname=jsonObject1.getString("checkname");  
                hi.checktime=jsonObject1.getString("checktime");
                his.add(hi);
            }
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
		return his;
	}
}
