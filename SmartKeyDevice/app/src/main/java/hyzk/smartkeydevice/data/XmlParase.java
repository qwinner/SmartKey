package hyzk.smartkeydevice.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class XmlParase
{
	//����
	//Write
	public static Document LordnoItemToXml(LordnoItem li){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try{
		   builder = dbf.newDocumentBuilder();
		}
		catch(Exception e){}
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("LordnoItem");
		doc.appendChild(root); //����Ԫ����ӵ��ĵ���
		
		Element le=doc.createElement("LordnoItem");
		root.appendChild(le);
		
		//
		Element rfid=doc.createElement("rfid");
		le.appendChild(rfid);
		rfid.appendChild(doc.createTextNode(li.rfid));
		
		Element id=doc.createElement("id");
		le.appendChild(id);
		id.appendChild(doc.createTextNode(li.id));
		
		Element name=doc.createElement("name");
		le.appendChild(name);
		name.appendChild(doc.createTextNode(li.name));
		
		Element phone=doc.createElement("phone");
		le.appendChild(phone);
		phone.appendChild(doc.createTextNode(li.phone));
		
		Element gphone=doc.createElement("gphone");
		le.appendChild(gphone);
		gphone.appendChild(doc.createTextNode(li.gphone));
		
		Element dname=doc.createElement("dname");
		le.appendChild(dname);
		dname.appendChild(doc.createTextNode(li.dname));
		
		Element dphone=doc.createElement("dphone");
		le.appendChild(dphone);
		dphone.appendChild(doc.createTextNode(li.dphone));
		
		Element address=doc.createElement("address");
		le.appendChild(address);
		address.appendChild(doc.createTextNode(li.address));
		
		Element adminid=doc.createElement("adminid");
		le.appendChild(adminid);
		adminid.appendChild(doc.createTextNode(li.adminid));
		
		Element adminname=doc.createElement("adminname");
		le.appendChild(adminname);
		adminname.appendChild(doc.createTextNode(li.adminname));
		
		Element dispsn=doc.createElement("dispsn");
		le.appendChild(dispsn);
		dispsn.appendChild(doc.createTextNode(li.dispsn));
		
		Element zroomnum=doc.createElement("zroomnum");
		le.appendChild(zroomnum);
		zroomnum.appendChild(doc.createTextNode(String.valueOf(li.zroomnum)));
		
		Element croomnum=doc.createElement("croomnum");
		le.appendChild(croomnum);
		croomnum.appendChild(doc.createTextNode(String.valueOf(li.croomnum)));
		
		Element alarmnum=doc.createElement("alarmnum");
		le.appendChild(alarmnum);
		alarmnum.appendChild(doc.createTextNode(String.valueOf(li.alarmnum)));
		
		Element roomcount=doc.createElement("roomcount");
		le.appendChild(roomcount);
		roomcount.appendChild(doc.createTextNode(String.valueOf(li.roomcount)));
		
		for(int i=0;i<li.CheckList.size();i++){
			Element cl=doc.createElement("CheckItem");
			le.appendChild(cl);
			
			//
			Element RoomID=doc.createElement("RoomID");
			cl.appendChild(RoomID);
			RoomID.appendChild(doc.createTextNode(li.CheckList.get(i).RoomID));
			
			Element RoomName=doc.createElement("RoomName");
			cl.appendChild(RoomName);
			RoomName.appendChild(doc.createTextNode(li.CheckList.get(i).RoomName));
			
			Element Check=doc.createElement("Check");
			cl.appendChild(Check);
			Check.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).Check)));
			
			Element mode=doc.createElement("mode");
			cl.appendChild(mode);
			mode.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).mode)));
			
			//������
			Element item1ret=doc.createElement("item1ret");
			cl.appendChild(item1ret);
			item1ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item1ret)));
			
			Element item1pic=doc.createElement("item1pic");			
			cl.appendChild(item1pic);
			item1pic.appendChild(doc.createTextNode(li.CheckList.get(i).item1pic));
			
			Element item2ret=doc.createElement("item2ret");
			cl.appendChild(item2ret);
			item2ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item2ret)));
			
			Element item2pic=doc.createElement("item2pic");			
			cl.appendChild(item2pic);
			item2pic.appendChild(doc.createTextNode(li.CheckList.get(i).item2pic));
			
			Element item3ret=doc.createElement("item3ret");
			cl.appendChild(item3ret);
			item3ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item3ret)));
			
			Element item3pic=doc.createElement("item3pic");			
			cl.appendChild(item3pic);
			item3pic.appendChild(doc.createTextNode(li.CheckList.get(i).item3pic));
			
			Element item4ret=doc.createElement("item4ret");
			cl.appendChild(item4ret);
			item4ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item4ret)));
			
			Element item4pic=doc.createElement("item4pic");			
			cl.appendChild(item4pic);
			item4pic.appendChild(doc.createTextNode(li.CheckList.get(i).item4pic));
			
			Element item5ret=doc.createElement("item5ret");
			cl.appendChild(item5ret);
			item5ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item5ret)));
			
			Element item5pic=doc.createElement("item5pic");			
			cl.appendChild(item5pic);
			item5pic.appendChild(doc.createTextNode(li.CheckList.get(i).item5pic));
			
			Element item6ret=doc.createElement("item6ret");
			cl.appendChild(item6ret);
			item6ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item6ret)));
			
			Element item6pic=doc.createElement("item6pic");			
			cl.appendChild(item6pic);
			item6pic.appendChild(doc.createTextNode(li.CheckList.get(i).item6pic));
			
			Element item7ret=doc.createElement("item7ret");
			cl.appendChild(item7ret);
			item7ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item7ret)));
			
			Element item7pic=doc.createElement("item7pic");			
			cl.appendChild(item7pic);
			item7pic.appendChild(doc.createTextNode(li.CheckList.get(i).item7pic));
			
			Element item8ret=doc.createElement("item8ret");
			cl.appendChild(item8ret);
			item8ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item8ret)));
			
			Element item8pic=doc.createElement("item8pic");			
			cl.appendChild(item8pic);
			item8pic.appendChild(doc.createTextNode(li.CheckList.get(i).item8pic));
			
			Element item9ret=doc.createElement("item9ret");
			cl.appendChild(item9ret);
			item9ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item9ret)));
			
			Element item9pic=doc.createElement("item9pic");			
			cl.appendChild(item9pic);
			item9pic.appendChild(doc.createTextNode(li.CheckList.get(i).item9pic));
			
			//ˮ��
			Element item10ret=doc.createElement("item10ret");
			cl.appendChild(item10ret);
			item10ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item10ret)));
			
			Element item11ret=doc.createElement("item11ret");
			cl.appendChild(item11ret);
			item11ret.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).item11ret)));
			
			Element UsersCount=doc.createElement("UsersCount");
			cl.appendChild(UsersCount);
			UsersCount.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).UsersCount)));
			
			for(int j=0;j<li.CheckList.get(i).UsersList.size();j++){
				Element ui=doc.createElement("UserItem");
				cl.appendChild(ui);
				
				//
				Element uin=doc.createElement("name");
				ui.appendChild(uin);
				uin.appendChild(doc.createTextNode(li.CheckList.get(i).UsersList.get(j).name));
				
				Element uphone=doc.createElement("phone");
				ui.appendChild(uphone);
				uphone.appendChild(doc.createTextNode(li.CheckList.get(i).UsersList.get(j).phone));
				
				Element idcard=doc.createElement("idcard");
				ui.appendChild(idcard);
				idcard.appendChild(doc.createTextNode(li.CheckList.get(i).UsersList.get(j).idcard));
				
				Element cardphoto=doc.createElement("cardphoto");
				ui.appendChild(cardphoto);
				cardphoto.appendChild(doc.createTextNode(li.CheckList.get(i).UsersList.get(j).cardphoto));
				
				Element utype=doc.createElement("type");
				ui.appendChild(utype);
				utype.appendChild(doc.createTextNode(String.valueOf(li.CheckList.get(i).UsersList.get(j).type)));
			}
		}
		
		return doc;
	}
	
	//Read
	public static LordnoItem XmlToLordnoItem(Document doc){
		LordnoItem li=new LordnoItem();
		Element root = doc.getDocumentElement();
		NodeList items = root.getElementsByTagName("LordnoItem");
		//for (int i = 0; i < items.getLength(); i++) //�����ж����ֻ��ȡ1��
		Element lin = (Element)items.item(0);
		li.rfid=lin.getElementsByTagName("rfid").item(0).getFirstChild().getNodeValue();
		li.id=lin.getElementsByTagName("id").item(0).getFirstChild().getNodeValue();
		li.name=lin.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
		
		li.phone=lin.getElementsByTagName("phone").item(0).getFirstChild().getNodeValue();
		li.gphone=getNodeString(lin,"gphone");//lin.getElementsByTagName("gphone").item(0).getFirstChild().getNodeValue();
		li.dname=getNodeString(lin,"dname");//lin.getElementsByTagName("dname").item(0).getFirstChild().getNodeValue();
		li.dphone=getNodeString(lin,"dphone");//lin.getElementsByTagName("dphone").item(0).getFirstChild().getNodeValue();
		li.address=getNodeString(lin,"address");//lin.getElementsByTagName("address").item(0).getFirstChild().getNodeValue();
		li.adminid=getNodeString(lin,"adminid");//lin.getElementsByTagName("adminid").item(0).getFirstChild().getNodeValue();
		li.adminname=getNodeString(lin,"adminname");//lin.getElementsByTagName("adminname").item(0).getFirstChild().getNodeValue();
		li.dispsn=getNodeString(lin,"dispsn");//lin.getElementsByTagName("dispsn").item(0).getFirstChild().getNodeValue();
		
		li.zroomnum=Integer.valueOf(lin.getElementsByTagName("zroomnum").item(0).getFirstChild().getNodeValue());
		li.croomnum=Integer.valueOf(lin.getElementsByTagName("croomnum").item(0).getFirstChild().getNodeValue());
		li.alarmnum=Integer.valueOf(lin.getElementsByTagName("alarmnum").item(0).getFirstChild().getNodeValue());
		li.roomcount=Integer.valueOf(lin.getElementsByTagName("roomcount").item(0).getFirstChild().getNodeValue());
		
		NodeList items2=lin.getElementsByTagName("CheckItem");
		for(int i=0;i<items2.getLength();i++){
			CheckItem ci=new CheckItem();
			Element cin=(Element)items2.item(i);
			ci.RoomID=cin.getElementsByTagName("RoomID").item(0).getFirstChild().getNodeValue();
			ci.RoomName=cin.getElementsByTagName("RoomName").item(0).getFirstChild().getNodeValue();
			ci.Check=Integer.valueOf(cin.getElementsByTagName("Check").item(0).getFirstChild().getNodeValue());
			ci.mode=Integer.valueOf(cin.getElementsByTagName("mode").item(0).getFirstChild().getNodeValue());
			
			ci.item1ret=Integer.valueOf(cin.getElementsByTagName("item1ret").item(0).getFirstChild().getNodeValue());
			ci.item1pic=getNodeString(cin,"item1pic");//cin.getElementsByTagName("item1pic").item(0).getFirstChild().getNodeValue();
			ci.item2ret=Integer.valueOf(cin.getElementsByTagName("item2ret").item(0).getFirstChild().getNodeValue());
			ci.item2pic=getNodeString(cin,"item2pic");//cin.getElementsByTagName("item2pic").item(0).getFirstChild().getNodeValue();
			ci.item3ret=Integer.valueOf(cin.getElementsByTagName("item3ret").item(0).getFirstChild().getNodeValue());
			ci.item3pic=getNodeString(cin,"item3pic");//cin.getElementsByTagName("item3pic").item(0).getFirstChild().getNodeValue();
			ci.item4ret=Integer.valueOf(cin.getElementsByTagName("item4ret").item(0).getFirstChild().getNodeValue());
			ci.item4pic=getNodeString(cin,"item4pic");//cin.getElementsByTagName("item4pic").item(0).getFirstChild().getNodeValue();
			ci.item5ret=Integer.valueOf(cin.getElementsByTagName("item5ret").item(0).getFirstChild().getNodeValue());
			ci.item5pic=getNodeString(cin,"item5pic");//cin.getElementsByTagName("item5pic").item(0).getFirstChild().getNodeValue();
			ci.item6ret=Integer.valueOf(cin.getElementsByTagName("item6ret").item(0).getFirstChild().getNodeValue());
			ci.item6pic=getNodeString(cin,"item6pic");//cin.getElementsByTagName("item6pic").item(0).getFirstChild().getNodeValue();
			ci.item7ret=Integer.valueOf(cin.getElementsByTagName("item7ret").item(0).getFirstChild().getNodeValue());
			ci.item7pic=getNodeString(cin,"item7pic");//cin.getElementsByTagName("item7pic").item(0).getFirstChild().getNodeValue();
			ci.item8ret=Integer.valueOf(cin.getElementsByTagName("item8ret").item(0).getFirstChild().getNodeValue());
			ci.item8pic=getNodeString(cin,"item8pic");//cin.getElementsByTagName("item8pic").item(0).getFirstChild().getNodeValue();
			ci.item9ret=Integer.valueOf(cin.getElementsByTagName("item9ret").item(0).getFirstChild().getNodeValue());
			ci.item9pic=getNodeString(cin,"item9pic");//cin.getElementsByTagName("item9pic").item(0).getFirstChild().getNodeValue();
			
			ci.item10ret=Integer.valueOf(cin.getElementsByTagName("item10ret").item(0).getFirstChild().getNodeValue());
			ci.item11ret=Integer.valueOf(cin.getElementsByTagName("item11ret").item(0).getFirstChild().getNodeValue());
			ci.UsersCount=Integer.valueOf(cin.getElementsByTagName("UsersCount").item(0).getFirstChild().getNodeValue());
			
			NodeList items3=cin.getElementsByTagName("UserItem");
			for(int j=0;j<items3.getLength();j++){
				UserItem ui=new UserItem();
				Element uin=(Element)items3.item(j);
				//ui.name=uin.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				ui.name=getNodeString(uin,"name");//uin.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				ui.phone=getNodeString(uin,"phone");//uin.getElementsByTagName("phone").item(0).getFirstChild().getNodeValue();
				ui.idcard=getNodeString(uin,"idcard");//uin.getElementsByTagName("idcard").item(0).getFirstChild().getNodeValue();
				ui.cardphoto=getNodeString(uin,"cardphoto");//uin.getElementsByTagName("cardphoto").item(0).getFirstChild().getNodeValue();
				ui.type=Integer.valueOf(uin.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
				ci.UsersList.add(ui);
			}
			
			li.CheckList.add(ci);
		}
			
		return li;
	}
	
	public static String getNodeString(Element em,String name){
		try{ 
			return em.getElementsByTagName(name).item(0).getFirstChild().getNodeValue();
		}catch(Exception e){ 
		}
		return "";
	}
	
	public static String getNodeStringEx(Element em,String name){
		try{ 
			return em.getElementsByTagName(name).item(0).getFirstChild().getNodeValue().replaceAll("\\s","");			
		}catch(Exception e){ 
		}
		return "";
	}
	
	public static int getNodeInt(Element em,String name){
		try{ 
			return Integer.valueOf(em.getElementsByTagName(name).item(0).getFirstChild().getNodeValue());
		}catch(Exception e){ 
		}
		return 0;
	}
	
	public static ArrayList<HouseItem> paraseHouseItem(InputStream inputStream){
		ArrayList<HouseItem> list = new ArrayList<HouseItem>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc=null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			Element root = doc.getDocumentElement();
			NodeList items = root.getElementsByTagName("HouseItem");
			for (int c = 0; c < items.getLength(); c++){ //�����ж��
			//if(items.getLength()>0){	//ֻ��ȡ1��
				HouseItem li=new HouseItem();
				Element lin = (Element)items.item(c);
				//Element lin = (Element)items.item(0);
				li.houseno=Integer.valueOf(lin.getElementsByTagName("houseno").item(0).getFirstChild().getNodeValue());
				li.houserfid=lin.getElementsByTagName("houserfid").item(0).getFirstChild().getNodeValue();
				li.houserfid=li.houserfid.replaceAll("\\s","");
								
				li.lordid=getNodeString(lin,"lordid");//lin.getElementsByTagName("lordid").item(0).getFirstChild().getNodeValue();
				li.lordname=getNodeString(lin,"lordname");//lin.getElementsByTagName("lordname").item(0).getFirstChild().getNodeValue();

				li.lordphonemobile=getNodeString(lin,"lordphonemobile");//lin.getElementsByTagName("lordphonemobile").item(0).getFirstChild().getNodeValue();
				li.lordphonefix=getNodeString(lin,"lordphonefix");//lin.getElementsByTagName("gphone").item(0).getFirstChild().getNodeValue();
				li.agentname=getNodeString(lin,"agentname");//lin.getElementsByTagName("dname").item(0).getFirstChild().getNodeValue();
				li.agentphonefix=getNodeString(lin,"agentphonefix");//lin.getElementsByTagName("dphone").item(0).getFirstChild().getNodeValue();
				li.detailaddress=getNodeString(lin,"detailaddress");//lin.getElementsByTagName("address").item(0).getFirstChild().getNodeValue();
				//li.adminid=getNodeString(lin,"adminid");//lin.getElementsByTagName("adminid").item(0).getFirstChild().getNodeValue();
				li.chargername=getNodeString(lin,"chargername");//lin.getElementsByTagName("adminname").item(0).getFirstChild().getNodeValue();
				li.displayno=getNodeString(lin,"displayno");//lin.getElementsByTagName("dispsn").item(0).getFirstChild().getNodeValue();
				
				li.roomselfnum=Integer.valueOf(lin.getElementsByTagName("roomselfnum").item(0).getFirstChild().getNodeValue());
				li.roomrentnum=Integer.valueOf(lin.getElementsByTagName("roomrentnum").item(0).getFirstChild().getNodeValue());
				li.totalalarmnum=Integer.valueOf(lin.getElementsByTagName("totalalarmnum").item(0).getFirstChild().getNodeValue());
				
				li.lastcheckno=getNodeString(lin,"lastcheckno");
				
				//li.roomcount=Integer.valueOf(lin.getElementsByTagName("roomcount").item(0).getFirstChild().getNodeValue());
				NodeList items2=lin.getElementsByTagName("RoomItem");
				for(int i=0;i<items2.getLength();i++){
					RoomItem ci=new RoomItem();
					Element cin=(Element)items2.item(i);
					ci.houseno=li.houseno;
					ci.roomid=cin.getElementsByTagName("roomid").item(0).getFirstChild().getNodeValue();
					ci.roomid=ci.roomid.replaceAll("\\s","");
					ci.roomname=cin.getElementsByTagName("roomname").item(0).getFirstChild().getNodeValue();
					ci.roomname=ci.roomname.replaceAll("\\s","");

					ci.properties=Integer.valueOf(cin.getElementsByTagName("properties").item(0).getFirstChild().getNodeValue());
					ci.selforrent=Integer.valueOf(cin.getElementsByTagName("selforrent").item(0).getFirstChild().getNodeValue());
					ci.warmtype=Integer.valueOf(cin.getElementsByTagName("warmtype").item(0).getFirstChild().getNodeValue());
					ci.checknum=Integer.valueOf(cin.getElementsByTagName("checknum").item(0).getFirstChild().getNodeValue());
					
					ci.area=Integer.valueOf(cin.getElementsByTagName("area").item(0).getFirstChild().getNodeValue());
					
					/*
					ci.item1ret=Integer.valueOf(cin.getElementsByTagName("item1ret").item(0).getFirstChild().getNodeValue());
					ci.item1pic=getNodeString(cin,"item1pic");//cin.getElementsByTagName("item1pic").item(0).getFirstChild().getNodeValue();
					ci.item2ret=Integer.valueOf(cin.getElementsByTagName("item2ret").item(0).getFirstChild().getNodeValue());
					ci.item2pic=getNodeString(cin,"item2pic");//cin.getElementsByTagName("item2pic").item(0).getFirstChild().getNodeValue();
					ci.item3ret=Integer.valueOf(cin.getElementsByTagName("item3ret").item(0).getFirstChild().getNodeValue());
					ci.item3pic=getNodeString(cin,"item3pic");//cin.getElementsByTagName("item3pic").item(0).getFirstChild().getNodeValue();
					ci.item4ret=Integer.valueOf(cin.getElementsByTagName("item4ret").item(0).getFirstChild().getNodeValue());
					ci.item4pic=getNodeString(cin,"item4pic");//cin.getElementsByTagName("item4pic").item(0).getFirstChild().getNodeValue();
					ci.item5ret=Integer.valueOf(cin.getElementsByTagName("item5ret").item(0).getFirstChild().getNodeValue());
					ci.item5pic=getNodeString(cin,"item5pic");//cin.getElementsByTagName("item5pic").item(0).getFirstChild().getNodeValue();
					ci.item6ret=Integer.valueOf(cin.getElementsByTagName("item6ret").item(0).getFirstChild().getNodeValue());
					ci.item6pic=getNodeString(cin,"item6pic");//cin.getElementsByTagName("item6pic").item(0).getFirstChild().getNodeValue();
					ci.item7ret=Integer.valueOf(cin.getElementsByTagName("item7ret").item(0).getFirstChild().getNodeValue());
					ci.item7pic=getNodeString(cin,"item7pic");//cin.getElementsByTagName("item7pic").item(0).getFirstChild().getNodeValue();
					ci.item8ret=Integer.valueOf(cin.getElementsByTagName("item8ret").item(0).getFirstChild().getNodeValue());
					ci.item8pic=getNodeString(cin,"item8pic");//cin.getElementsByTagName("item8pic").item(0).getFirstChild().getNodeValue();
					ci.item9ret=Integer.valueOf(cin.getElementsByTagName("item9ret").item(0).getFirstChild().getNodeValue());
					ci.item9pic=getNodeString(cin,"item9pic");//cin.getElementsByTagName("item9pic").item(0).getFirstChild().getNodeValue();
					
					ci.item10ret=Integer.valueOf(cin.getElementsByTagName("item10ret").item(0).getFirstChild().getNodeValue());
					ci.item11ret=Integer.valueOf(cin.getElementsByTagName("item11ret").item(0).getFirstChild().getNodeValue());
					ci.UsersCount=Integer.valueOf(cin.getElementsByTagName("UsersCount").item(0).getFirstChild().getNodeValue());
					*/
										
					NodeList items3=cin.getElementsByTagName("TenantItem");
					for(int j=0;j<items3.getLength();j++){
						TenantItem ui=new TenantItem();
						Element uin=(Element)items3.item(j);
						ui.realname=getNodeString(uin,"realname");//uin.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
						ui.idcardno=getNodeString(uin,"idcardno");//uin.getElementsByTagName("phone").item(0).getFirstChild().getNodeValue();
						ui.phone1=getNodeString(uin,"phone1");//uin.getElementsByTagName("idcard").item(0).getFirstChild().getNodeValue();
						ui.rsidcardphpath=getNodeString(uin,"rsidcardphpath");//uin.getElementsByTagName("cardphoto").item(0).getFirstChild().getNodeValue();
						//ui.type=Integer.valueOf(uin.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
						ci.TenantList.add(ui);
					}
					li.RoomList.add(ci);
				}
				list.add(li);
			}
			//inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<HouseItem> XmlToHouseItemList(Document doc){
		ArrayList<HouseItem> list = new ArrayList<HouseItem>();
		if(doc==null)
			return null;
		try {
			Element root = doc.getDocumentElement();
			NodeList items = root.getElementsByTagName("HouseItem");
			for (int c = 0; c < items.getLength(); c++){ //�����ж��
			//if(items.getLength()>0){	//ֻ��ȡ1��
				HouseItem li=new HouseItem();
				Element lin = (Element)items.item(c);
				//Element lin = (Element)items.item(0);
				li.houseno=Integer.valueOf(lin.getElementsByTagName("houseno").item(0).getFirstChild().getNodeValue());
				li.houserfid=lin.getElementsByTagName("houserfid").item(0).getFirstChild().getNodeValue();
				li.houserfid=li.houserfid.replaceAll("\\s","");
								
				li.lordid=getNodeString(lin,"lordid");//lin.getElementsByTagName("lordid").item(0).getFirstChild().getNodeValue();
				li.lordname=getNodeString(lin,"lordname");//lin.getElementsByTagName("lordname").item(0).getFirstChild().getNodeValue();

				li.lordphonemobile=getNodeString(lin,"lordphonemobile");//lin.getElementsByTagName("lordphonemobile").item(0).getFirstChild().getNodeValue();
				li.lordphonefix=getNodeString(lin,"lordphonefix");//lin.getElementsByTagName("gphone").item(0).getFirstChild().getNodeValue();
				li.agentname=getNodeString(lin,"agentname");//lin.getElementsByTagName("dname").item(0).getFirstChild().getNodeValue();
				li.agentphonefix=getNodeString(lin,"agentphonefix");//lin.getElementsByTagName("dphone").item(0).getFirstChild().getNodeValue();
				li.detailaddress=getNodeString(lin,"detailaddress");//lin.getElementsByTagName("address").item(0).getFirstChild().getNodeValue();
				//li.adminid=getNodeString(lin,"adminid");//lin.getElementsByTagName("adminid").item(0).getFirstChild().getNodeValue();
				li.chargername=getNodeString(lin,"chargername");//lin.getElementsByTagName("adminname").item(0).getFirstChild().getNodeValue();
				li.displayno=getNodeString(lin,"displayno");//lin.getElementsByTagName("dispsn").item(0).getFirstChild().getNodeValue();
				
				li.roomselfnum=Integer.valueOf(lin.getElementsByTagName("roomselfnum").item(0).getFirstChild().getNodeValue());
				li.roomrentnum=Integer.valueOf(lin.getElementsByTagName("roomrentnum").item(0).getFirstChild().getNodeValue());
				li.totalalarmnum=Integer.valueOf(lin.getElementsByTagName("totalalarmnum").item(0).getFirstChild().getNodeValue());
				
				li.lastcheckno=getNodeString(lin,"lastcheckno");
				
				//li.roomcount=Integer.valueOf(lin.getElementsByTagName("roomcount").item(0).getFirstChild().getNodeValue());
				NodeList items2=lin.getElementsByTagName("RoomItem");
				for(int i=0;i<items2.getLength();i++){
					RoomItem ci=new RoomItem();
					Element cin=(Element)items2.item(i);
					ci.houseno=li.houseno;
					ci.roomid=cin.getElementsByTagName("roomid").item(0).getFirstChild().getNodeValue();
					ci.roomid=ci.roomid.replaceAll("\\s","");
					ci.roomname=cin.getElementsByTagName("roomname").item(0).getFirstChild().getNodeValue();
					ci.roomname=ci.roomname.replaceAll("\\s","");

					ci.properties=Integer.valueOf(cin.getElementsByTagName("properties").item(0).getFirstChild().getNodeValue());
					ci.selforrent=Integer.valueOf(cin.getElementsByTagName("selforrent").item(0).getFirstChild().getNodeValue());
					ci.warmtype=Integer.valueOf(cin.getElementsByTagName("warmtype").item(0).getFirstChild().getNodeValue());
					ci.checknum=Integer.valueOf(cin.getElementsByTagName("checknum").item(0).getFirstChild().getNodeValue());
					
					ci.area=Integer.valueOf(cin.getElementsByTagName("area").item(0).getFirstChild().getNodeValue());
										
					NodeList items3=cin.getElementsByTagName("TenantItem");
					for(int j=0;j<items3.getLength();j++){
						TenantItem ui=new TenantItem();
						Element uin=(Element)items3.item(j);
						ui.realname=getNodeString(uin,"realname");//uin.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
						ui.idcardno=getNodeString(uin,"idcardno");//uin.getElementsByTagName("phone").item(0).getFirstChild().getNodeValue();
						ui.phone1=getNodeString(uin,"phone1");//uin.getElementsByTagName("idcard").item(0).getFirstChild().getNodeValue();
						ui.rsidcardphpath=getNodeString(uin,"rsidcardphpath");//uin.getElementsByTagName("cardphoto").item(0).getFirstChild().getNodeValue();
						//ui.type=Integer.valueOf(uin.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
						ci.TenantList.add(ui);
					}
					li.RoomList.add(ci);
				}
				list.add(li);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<HouseItem> XmlToHouseItemListEx(InputStream inputStream)
    {
        ArrayList<HouseItem> list = new ArrayList<HouseItem>();
        XmlPullParser parser = Xml.newPullParser();

        try{
            parser.setInput (inputStream, "UTF-8");
            int eventType = parser.getEventType();
            HouseItem hi = new HouseItem();
            RoomItem  ri = new RoomItem();
            TenantItem ti = new TenantItem();
            
            int depth1=0;
            int depth2=0;
            int depth3=0;

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType)
                {
                case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ�����ݳ�ʼ������
                    break;
                case XmlPullParser.START_TAG:{// ��ʼԪ���¼�
                    	String name = parser.getName();
                    	if (name.equalsIgnoreCase ("HouseItem")){
                    		hi = new HouseItem();
                    		depth1=parser.getDepth()+1;
                    	}else if (name.equalsIgnoreCase ("RoomItem")){
                    		ri = new RoomItem();
                    		depth2=parser.getDepth()+1;
                    	}else if (name.equalsIgnoreCase ("TenantItem")){
                    		ti = new TenantItem();
                    		depth3=parser.getDepth()+1;
                    	}else{
                    		//���
                    		int dp=parser.getDepth();
                    		if(dp==depth1){
                    			if (name.equalsIgnoreCase ("houseno")){	//
                        			eventType = parser.next();
                        			hi.houseno=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("houserfid")){
                        			eventType = parser.next();
                        			hi.houserfid=getParserString(parser);
                        			hi.houserfid=hi.houserfid.replaceAll("\\s","");
                        		}else if (name.equalsIgnoreCase ("detailaddress")){
                        			eventType = parser.next();
                        			hi.detailaddress=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lucity")){
                        			eventType = parser.next();
                        			hi.lucity=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("ludistric")){
                        			eventType = parser.next();
                        			hi.ludistric=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("lutownship")){
                        			eventType = parser.next();
                        			hi.lutownship=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("luvillage")){
                        			eventType = parser.next();
                        			hi.luvillage=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("roomselfnum")){
                        			eventType = parser.next();
                        			hi.roomselfnum=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("roomrentnum")){
                        			eventType = parser.next();
                        			hi.roomrentnum=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("lordid")){
                        			eventType = parser.next();
                        			hi.lordid=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lordname")){
                        			eventType = parser.next();
                        			hi.lordname=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lordidcard")){
                        			eventType = parser.next();
                        			hi.lordidcard=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lordphonemobile")){
                        			eventType = parser.next();
                        			hi.lordphonemobile=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lordphonefix")){
                        			eventType = parser.next();
                        			hi.lordphonefix=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lordaddress")){
                        			eventType = parser.next();
                        			hi.lordaddress=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lordphotopath")){
                        			eventType = parser.next();
                        			hi.lordphotopath=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("agentname")){
                        			eventType = parser.next();
                        			hi.agentname=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("agentphonemobile")){
                        			eventType = parser.next();
                        			hi.agentphonemobile=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("agentphonefix")){
                        			eventType = parser.next();
                        			hi.agentphonefix=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("chargername")){
                        			eventType = parser.next();
                        			hi.chargername=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("chargerphonemobile")){
                        			eventType = parser.next();
                        			hi.chargerphonemobile=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("chargerphonefix")){
                        			eventType = parser.next();
                        			hi.chargerphonefix=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("houseproperty")){
                        			eventType = parser.next();
                        			hi.houseproperty=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("threeparts")){
                        			eventType = parser.next();
                        			hi.threeparts=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("oldalarmnum")){
                        			eventType = parser.next();
                        			hi.oldalarmnum=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("olddisplaynum")){
                        			eventType = parser.next();
                        			hi.olddisplaynum=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("totalalarmnum")){
                        			eventType = parser.next();
                        			hi.totalalarmnum=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("displayno")){
                        			eventType = parser.next();
                        			hi.displayno=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("lastcheckno")){
                        			eventType = parser.next();
                        			hi.lastcheckno=getParserString(parser);
                        		}
                    		}else if(dp==depth2){
                    			if (name.equalsIgnoreCase ("houseno")){	//
                        			eventType = parser.next();
                        			ri.houseno=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("roomno")){	//
                        			eventType = parser.next();
                        			ri.roomno=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("houserfid")){
                        			eventType = parser.next();
                        			ri.houserfid=getParserString(parser);
                        			ri.houserfid=ri.houserfid.replaceAll("\\s","");
                        		}else if (name.equalsIgnoreCase ("roomid")){
                        			eventType = parser.next();
                        			ri.roomid=getParserString(parser);
                        			ri.roomid=ri.roomid.replaceAll("\\s","");
                        		}else if (name.equalsIgnoreCase ("roomname")){
                        			eventType = parser.next();
                        			ri.roomname=getParserString(parser);
                        			ri.roomname=ri.roomname.replaceAll("\\s","");
                        		}else if (name.equalsIgnoreCase ("properties")){	//
                        			eventType = parser.next();
                        			ri.properties=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("selforrent")){	//
                        			eventType = parser.next();
                        			ri.selforrent=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("warmtype")){	//
                        			eventType = parser.next();
                        			ri.warmtype=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("checknum")){	//
                        			eventType = parser.next();
                        			ri.checknum=getParserInt(parser);
                        		}else if (name.equalsIgnoreCase ("area")){	//
                        			eventType = parser.next();
                        			ri.area=getParserInt(parser);
                        		}
                    		}else if(dp==depth3){
                    			if (name.equalsIgnoreCase ("realname")){	//
                        			eventType = parser.next();
                        			ti.realname=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("houserfid")){
                        			eventType = parser.next();
                        			ti.idcardno=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("phone1")){
                        			eventType = parser.next();
                        			ti.phone1=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("rsidcardphpath")){
                        			eventType = parser.next();
                        			ti.rsidcardphpath=getParserString(parser);
                        		}else if (name.equalsIgnoreCase ("residentphpath")){
                        			eventType = parser.next();
                        			ti.residentphpath=getParserString(parser);
                        		}
                    		}                    		
                    	}
                	}
                    break;                
                case XmlPullParser.END_TAG:{// ����Ԫ���¼�
                		String name = parser.getName();
                    	if (name.equalsIgnoreCase ("HouseItem")){
                        	list.add (hi);
                        	hi = null;
                    	}else if (name.equalsIgnoreCase ("RoomItem")){
                    		hi.RoomList.add(ri);
                    	}else if (name.equalsIgnoreCase ("TenantItem")){
                    		ri.TenantList.add(ti);
                    	}
                	}
                    break;
                }
                eventType = parser.next();
            }
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
		
    public static Element XmlAppendRoot(Document doc,String name){
    	Element ele = doc.createElement(name);
		doc.appendChild(ele);
		return ele;
    }

    public static Element XmlAppendElement(Document doc,Element root,String name){
    	Element ele = doc.createElement(name);
    	root.appendChild(ele);
		return ele;
    }
    
    public static void XmlAppendChild(Document doc,Element elep,String name,int val){
    	Element elec=doc.createElement(name);
    	elep.appendChild(elec);
		elec.appendChild(doc.createTextNode(String.valueOf(val)));
    }
    
    public static void XmlAppendChild(Document doc,Element elep,String name,String val){
    	Element elec=doc.createElement(name);
    	elep.appendChild(elec);
    	elec.appendChild(doc.createTextNode(val));
    }
    
    public static Document HouseItemToXml(List<HouseItem> list){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try{
		   builder = dbf.newDocumentBuilder();
		}
		catch(Exception e){}
		Document doc = builder.newDocument();
		Element root = XmlAppendRoot(doc,"HouseItem");
		
		for(int i=0;i<list.size();i++){
			HouseItem hi=list.get(i);
			Element elhi=XmlAppendElement(doc,root,"HouseItem");
			
			XmlAppendChild(doc,elhi,"houserfid",hi.houserfid);
			XmlAppendChild(doc,elhi,"lordname",hi.lordname);
			XmlAppendChild(doc,elhi,"lordaddress",hi.lordaddress);

		}
		return doc;
	}
    
    //Write
    public static Document HouseCheckToXml(List<HouseCheckItem> list){
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try{
		   builder = dbf.newDocumentBuilder();
		}
		catch(Exception e){}
		Document doc = builder.newDocument();
		Element root = XmlAppendRoot(doc,"HouseCheckItem");	
		
		for(int i=0;i<list.size();i++){
			HouseCheckItem hci=list.get(i);
			Element elhci=XmlAppendElement(doc,root,"HouseCheckItem");
			
			XmlAppendChild(doc,elhci,"houseno",hci.houseno);
			XmlAppendChild(doc,elhci,"checkno",hci.checkno);
			XmlAppendChild(doc,elhci,"checkid",hci.checkid);
			XmlAppendChild(doc,elhci,"checktime",hci.checktime);
			XmlAppendChild(doc,elhci,"houserfid",hci.houserfid);
			XmlAppendChild(doc,elhci,"checkmanuser",hci.checkmanuser);
			XmlAppendChild(doc,elhci,"checkmanname",hci.checkmanname);
			XmlAppendChild(doc,elhci,"checkedroomnum",hci.checkedroomnum);
			XmlAppendChild(doc,elhci,"ckriskroomnum",hci.ckriskroomnum);
			XmlAppendChild(doc,elhci,"uncheckedroomnum",hci.uncheckedroomnum);
			
			for(int j=0;j<hci.RoomCheckList.size();j++){
				RoomCheckItem rci=hci.RoomCheckList.get(j);
				Element elrci=XmlAppendElement(doc,elhci,"RoomCheckItem");
				
				XmlAppendChild(doc,elrci,"checkmode",rci.checkmode);
				XmlAppendChild(doc,elrci,"houseno",rci.houseno);
				XmlAppendChild(doc,elrci,"roomno",rci.roomno);
				XmlAppendChild(doc,elrci,"checkno",rci.checkno);
				XmlAppendChild(doc,elrci,"checkid",rci.checkid);
				XmlAppendChild(doc,elrci,"checktime",rci.checktime);
				XmlAppendChild(doc,elrci,"houserfid",rci.houserfid);
				XmlAppendChild(doc,elrci,"roomid",rci.roomid);
				XmlAppendChild(doc,elrci,"roomname",rci.roomname);				
				XmlAppendChild(doc,elrci,"checkmanuser",rci.checkmanuser);
				XmlAppendChild(doc,elrci,"checkmanname",rci.checkmanname);
				XmlAppendChild(doc,elrci,"dutymanno",rci.dutymanno);
				XmlAppendChild(doc,elrci,"dutymanname",rci.dutymanname);
				XmlAppendChild(doc,elrci,"checkstatus",rci.checkstatus);
				XmlAppendChild(doc,elrci,"selforrent",rci.selforrent);
				XmlAppendChild(doc,elrci,"warmtype",rci.warmtype);
				XmlAppendChild(doc,elrci,"nowarmway",rci.nowarmway);
				XmlAppendChild(doc,elrci,"alarmstatus",rci.alarmstatus);
				XmlAppendChild(doc,elrci,"alarmno",rci.alarmno);
				XmlAppendChild(doc,elrci,"alarmphpath",rci.alarmphpath);
				XmlAppendChild(doc,elrci,"windscoopstatus",rci.windscoopstatus);
				XmlAppendChild(doc,elrci,"windsphpath",rci.windsphpath);
				XmlAppendChild(doc,elrci,"cookerstatus",rci.cookerstatus);
				XmlAppendChild(doc,elrci,"cookerphpath",rci.cookerphpath);				
				XmlAppendChild(doc,elrci,"chimneystatus",rci.chimneystatus);
				XmlAppendChild(doc,elrci,"chimneyphpath",rci.chimneyphpath);				
				XmlAppendChild(doc,elrci,"chthreestatus",rci.chthreestatus);				
				XmlAppendChild(doc,elrci,"chthreephpath",rci.chthreephpath);
				XmlAppendChild(doc,elrci,"chclearstatus",rci.chclearstatus);
				XmlAppendChild(doc,elrci,"chclearphpath",rci.chclearphpath);				
				XmlAppendChild(doc,elrci,"safetipstatus",rci.safetipstatus);
				XmlAppendChild(doc,elrci,"safetipphpath",rci.safetipphpath);
				XmlAppendChild(doc,elrci,"gasnousstatus",rci.gasnousstatus);
				XmlAppendChild(doc,elrci,"gasnousphpath",rci.gasnousphpath);
				XmlAppendChild(doc,elrci,"safebookstatus",rci.safebookstatus);
				XmlAppendChild(doc,elrci,"safebookphpath",rci.safebookphpath);
				XmlAppendChild(doc,elrci,"dangernotifystatus",rci.dangernotifystatus);
				XmlAppendChild(doc,elrci,"dangerntyphpath",rci.dangerntyphpath);
				XmlAppendChild(doc,elrci,"safebookrscpath",rci.safebookrscpath);
				XmlAppendChild(doc,elrci,"dangerntyrscpath",rci.dangerntyrscpath);				
				XmlAppendChild(doc,elrci,"safebookrscstatus",rci.safebookrscstatus);
				XmlAppendChild(doc,elrci,"residentnum",rci.residentnum);
				XmlAppendChild(doc,elrci,"resicheckednum",rci.resicheckednum);
				XmlAppendChild(doc,elrci,"residentname",rci.residentname);
				XmlAppendChild(doc,elrci,"residentphone",rci.residentphone);
				XmlAppendChild(doc,elrci,"waterfeestatus",rci.waterfeestatus);
				XmlAppendChild(doc,elrci,"cleanfeestatus",rci.cleanfeestatus);
				XmlAppendChild(doc,elrci,"area",rci.area);
				
				for(int k=0;k<rci.TenantCheckList.size();k++){
					TenantCheckItem tci=rci.TenantCheckList.get(k);
					Element eltci=XmlAppendElement(doc,elrci,"TenantCheckItem");
					
					XmlAppendChild(doc,eltci,"checkno",tci.checkno);
					XmlAppendChild(doc,eltci,"roomno",tci.roomno);
					XmlAppendChild(doc,eltci,"checkid",tci.checkid);
					XmlAppendChild(doc,eltci,"roomid",tci.roomid);
					XmlAppendChild(doc,eltci,"checktime",tci.checktime);
					XmlAppendChild(doc,eltci,"realname",tci.realname);
					XmlAppendChild(doc,eltci,"idcardno",tci.idcardno);
					XmlAppendChild(doc,eltci,"phone1",tci.phone1);
					XmlAppendChild(doc,eltci,"rsidcardphpath",tci.rsidcardphpath);
					XmlAppendChild(doc,eltci,"residentphpath",tci.residentphpath);
				}
			}
		}
		
		return doc;
    }
	
    //Read
    public static List<HouseCheckItem> XmlToHouseCheck(Document doc){
		ArrayList<HouseCheckItem> list = new ArrayList<HouseCheckItem>();
		if(doc==null)
			return null;
		try {
			Element root = doc.getDocumentElement();
			NodeList hcilist = root.getElementsByTagName("HouseCheckItem");
			for (int i = 0; i < hcilist.getLength(); i++){
				HouseCheckItem hci=new HouseCheckItem();
				Element elhci = (Element)hcilist.item(i);
				hci.houseno=getNodeInt(elhci,"houseno");
				hci.checkno=getNodeInt(elhci,"checkno");				
				hci.checkid=getNodeStringEx(elhci,"checkid");
				hci.checktime=getNodeStringEx(elhci,"checktime");
				hci.houserfid=getNodeStringEx(elhci,"houserfid");
				hci.checkmanuser=getNodeString(elhci,"checkmanuser");
				hci.checkmanname=getNodeString(elhci,"checkmanname");
				hci.checkedroomnum=getNodeInt(elhci,"checkedroomnum");
				hci.ckriskroomnum=getNodeInt(elhci,"ckriskroomnum");
				hci.uncheckedroomnum=getNodeInt(elhci,"uncheckedroomnum");
				
				NodeList rcilist=elhci.getElementsByTagName("RoomCheckItem");
				for(int j=0;j<rcilist.getLength();j++){
					RoomCheckItem rci=new RoomCheckItem();
					Element elrci=(Element)rcilist.item(j);
					
					rci.checkmode=getNodeInt(elrci,"checkmode");
					rci.houseno=getNodeInt(elrci,"houseno");
					rci.roomno=getNodeInt(elrci,"roomno");
					rci.checkno=getNodeInt(elrci,"checkno");

					rci.checkid=getNodeStringEx(elrci,"checkid");         //Ѳ���¼���:ʱ��+rfid+�����
				    rci.checktime=getNodeStringEx(elrci,"checktime");       //-- Ѳ��ʱ��  //����д�ɷ���ʱ���ʱ��
				    rci.houserfid=getNodeStringEx(elrci,"houserfid");       //���ݱ��
				    rci.roomid=getNodeStringEx(elrci,"roomid");          //������
				    rci.roomname=getNodeStringEx(elrci,"roomname");          //������
				    rci.checkmanuser=getNodeString(elrci,"checkmanuser");    //Ѳ���˵�¼��
				    rci.checkmanname=getNodeString(elrci,"checkmanname");    //Ѳ��������
				    rci.dutymanno=getNodeString(elrci,"dutymanno");       //�����˱��
				    rci.dutymanname=getNodeString(elrci,"dutymanname");     //����������
				    rci.checkstatus=getNodeInt(elrci,"checkstatus");     	//Ѳ��״̬���Ѳ顢�Ѳ��б���������δ�顢����δ��
				    rci.selforrent=getNodeInt(elrci,"selforrent");      	//��ס���ͣ���ס/����
				    rci.warmtype=getNodeInt(elrci,"warmtype");        	//ȡů���ͣ��硢���й�ů��ú����ȡů
				    rci.nowarmway=getNodeString(elrci,"nowarmway");       //��ȡů�����ȡů��ʽ     
				    rci.alarmstatus=getNodeInt(elrci,"alarmstatus");     	//������״̬
				    rci.alarmno=getNodeString(elrci,"alarmno");         //���������
				    rci.alarmphpath=getNodeString(elrci,"alarmphpath");     //��������Ƭ��ַ
				    rci.windscoopstatus=getNodeInt(elrci,"windscoopstatus"); 	//�綷״̬
				    rci.windsphpath=getNodeString(elrci,"windsphpath");     //�綷��Ƭ��ַ
				    rci.cookerstatus=getNodeInt(elrci,"cookerstatus");    	//¯��״̬
				    rci.cookerphpath=getNodeString(elrci,"cookerphpath");    //¯����Ƭ��ַ
				    rci.chimneystatus=getNodeInt(elrci,"chimneystatus");   	//�̴�״̬
				    rci.chimneyphpath=getNodeString(elrci,"chimneyphpath");   //�̴���Ƭ��ַ
				    rci.chthreestatus=getNodeInt(elrci,"chthreestatus");   	//�̴���ͨ״̬
				    rci.chthreephpath=getNodeString(elrci,"chthreephpath");   //�̴���ͨ��Ƭ��ַ
				    rci.chclearstatus=getNodeInt(elrci,"chclearstatus");   	//�̵�ͨ��״̬
				    rci.chclearphpath=getNodeString(elrci,"chclearphpath");   //�̵�ͨ����Ƭ��ַ
				    rci.safetipstatus=getNodeInt(elrci,"safetipstatus");   	//��ȫ��ʾ��״̬
				    rci.safetipphpath=getNodeString(elrci,"safetipphpath");   //��ȫ��ʾ����Ƭ��ַ
				    rci.gasnousstatus=getNodeInt(elrci,"gasnousstatus");   	//Ԥ��ú��ʶ״̬
				    rci.gasnousphpath=getNodeString(elrci,"gasnousphpath");   //Ԥ��ú��ʶ��Ƭ��ַ
				    rci.safebookstatus=getNodeInt(elrci,"safebookstatus");  	//��ȫ������״̬
				    rci.safebookphpath=getNodeString(elrci,"safebookphpath");  //��ȫ��������Ƭ��ַ
				    rci.dangernotifystatus=getNodeInt(elrci,"dangernotifystatus"); //����֪ͨ��״̬
				    rci.dangerntyphpath=getNodeString(elrci,"dangerntyphpath"); //����֪ͨ����Ƭ��ַ
				    rci.safebookrscpath=getNodeString(elrci,"safebookrscpath"); //��ȡů�����ŵ����¼���ַ
				    rci.dangerntyrscpath=getNodeString(elrci,"dangerntyrscpath");//�Ի����¼���ַ
				    rci.safebookrscstatus=getNodeInt(elrci,"safebookrscstatus");	//��ŵ��״̬
				    rci.residentnum=getNodeInt(elrci,"residentnum");        //ס������
				    rci.resicheckednum=getNodeInt(elrci,"resicheckednum");     //�Ѳ���֤��ס������
				    rci.residentname=getNodeString(elrci,"residentname");    //����һ��ס����ʵ����
				    rci.residentphone=getNodeString(elrci,"residentphone");   //����һ��ס���绰����

				    rci.waterfeestatus=getNodeInt(elrci,"waterfeestatus");  //ˮ��״̬���ѽ���δ��
				    rci.cleanfeestatus=getNodeInt(elrci,"cleanfeestatus");  //�����״̬���ѽ���δ��
				    
				    rci.area=getNodeInt(elrci,"area");
										
					NodeList tcilist=elrci.getElementsByTagName("TenantCheckItem");
					for(int k=0;k<tcilist.getLength();k++){
						TenantCheckItem tci=new TenantCheckItem();
						Element eltci=(Element)tcilist.item(k);
						
						tci.checkno=getNodeInt(eltci,"checkno");
						tci.roomno=getNodeInt(eltci,"roomno");
						tci.roomid=getNodeStringEx(eltci,"roomid");
						tci.checktime=getNodeString(eltci,"checktime");
						tci.realname=getNodeString(eltci,"realname");
						tci.idcardno=getNodeString(eltci,"idcardno");
						tci.phone1=getNodeString(eltci,"phone1");
						tci.rsidcardphpath=getNodeString(eltci,"rsidcardphpath");
						tci.residentphpath=getNodeString(eltci,"residentphpath");

						rci.TenantCheckList.add(tci);
					}
					hci.RoomCheckList.add(rci);
				}
				list.add(hci);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    
	private static String getParserString(XmlPullParser parser){
		try{ 
			return parser.getText();
		}catch(Exception e){ 
			
		}
		return "";
	}
	
	private static int getParserInt(XmlPullParser parser){
		try{ 
			return Integer.valueOf(parser.getText());
		}catch(Exception e){ 
			
		}
		return 0;
	}
	
	//����Ա
	
	//������ı��ڵ��ǿգ�����쳣
    public static ArrayList<AdminItem> paraseAdminItem(InputStream inputStream)
    {
        ArrayList<AdminItem> list = new ArrayList<AdminItem>();

        XmlPullParser parser = Xml.newPullParser();
        try{
            parser.setInput (inputStream, "UTF-8");
            int eventType = parser.getEventType();
            AdminItem info = new AdminItem();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType)
                {
                case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ�����ݳ�ʼ������
                    break;
                case XmlPullParser.START_TAG:// ��ʼԪ���¼�
                    String name = parser.getName();
                    if (name.equalsIgnoreCase ("AdminItem")){
                        info = new AdminItem();
                    }else if (name.equalsIgnoreCase ("username")){	//
                        eventType = parser.next();
                        info.username=getParserString(parser);
                    }else if (name.equalsIgnoreCase ("password")){
                        eventType = parser.next();
                        info.password=getParserString(parser);
                    }else if (name.equalsIgnoreCase ("usertype")){
                        eventType = parser.next();
                        info.usertype=Integer.valueOf(getParserString(parser));
                    }else if (name.equalsIgnoreCase ("userstatus")){
                        eventType = parser.next();
                        info.userstatus=Integer.valueOf(getParserString(parser));
                    }else if (name.equalsIgnoreCase ("checklevel")){
                        eventType = parser.next();
                        info.checklevel=Integer.valueOf(getParserString(parser));
                    }else if (name.equalsIgnoreCase ("checkrange")){
                        eventType = parser.next();
                        info.checkrange=getParserString(parser);
                    }else if (name.equalsIgnoreCase ("fingerm")){
                        eventType = parser.next();
                        info.setTempStr1(getParserString(parser));
                    }else if (name.equalsIgnoreCase ("fingers")){
                        eventType = parser.next();
                        info.setTempStr2(getParserString(parser));
                    }else if (name.equalsIgnoreCase ("realname")){	//
                        eventType = parser.next();
                        info.realname=getParserString(parser);
                    }else if (name.equalsIgnoreCase ("idcardno")){
                        eventType = parser.next();
                        info.idcardno=getParserString(parser);                    
                    }else if (name.equalsIgnoreCase ("photo")){
                        eventType = parser.next();
                        info.setTempStr2(getParserString(parser) );
                    }else if (name.equalsIgnoreCase ("phonemobile")){
                        eventType = parser.next();
                        info.phonemobile=getParserString(parser);                    
                    }else if (name.equalsIgnoreCase ("phonefix")){
                        eventType = parser.next();
                        info.phonefix=getParserString(parser);                    
                    }else if (name.equalsIgnoreCase ("email")){
                        eventType = parser.next();
                        info.email=getParserString(parser);                    
                    }else if (name.equalsIgnoreCase ("address")){
                        eventType = parser.next();
                        info.address=getParserString(parser);                    
                    }
                    break;
                case XmlPullParser.END_TAG:// ����Ԫ���¼�
                    if (parser.getName().equalsIgnoreCase ("AdminItem")){
                        list.add (info);
                        info = null;
                    }
                    break;
                }
                eventType = parser.next();
            }
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<AdminItem> paraseAdminItemEx(InputStream inputStream){
		ArrayList<AdminItem> list = new ArrayList<AdminItem>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc=null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			Element root = doc.getDocumentElement();
			NodeList items = root.getElementsByTagName("AdminItem");
			for(int i=0;i<items.getLength();i++){
				AdminItem li=new AdminItem();
				Element lin = (Element)items.item(i);
				li.username=getNodeString(lin,"username");
				li.password=getNodeString(lin,"password");
				li.tpstr1=getNodeString(lin,"fingerm");
				li.tpstr2=getNodeString(lin,"fingers");

				li.usertype=Integer.valueOf(getNodeString(lin,"usertype"));
				li.userstatus=Integer.valueOf(getNodeString(lin,"userstatus"));
				li.checklevel=Integer.valueOf(getNodeString(lin,"checklevel"));
				li.checkrange=getNodeString(lin,"checkrange");
				
				li.realname=getNodeString(lin,"realname");
				li.idcardno=getNodeString(lin,"idcardno");
				li.photo=getNodeString(lin,"photo");
				li.phonemobile=getNodeString(lin,"phonemobile");
				li.phonefix=getNodeString(lin,"phonefix");
				li.email=getNodeString(lin,"email");
				li.address=getNodeString(lin,"address");
				
				list.add(li);
			}
			//inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    
    //Write
    public static Document AdminListToXml(List<AdminItem> list){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try{
		   builder = dbf.newDocumentBuilder();
		}
		catch(Exception e){}
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("AdminItem");
		doc.appendChild(root); //����Ԫ����ӵ��ĵ���
		
		for(int i=0;i<list.size();i++){
			AdminItem ai=list.get(i);
		
			Element le=doc.createElement("AdminItem");
			root.appendChild(le);
			//
			Element username=doc.createElement("username");
			le.appendChild(username);
			username.appendChild(doc.createTextNode(ai.username));
			
			Element password=doc.createElement("password");
			le.appendChild(password);
			password.appendChild(doc.createTextNode(ai.password));
			
			Element tpstr1=doc.createElement("tpstr1");
			le.appendChild(tpstr1);
			tpstr1.appendChild(doc.createTextNode(ai.getTempStr1()));
			
			Element tpstr2=doc.createElement("tpstr2");
			le.appendChild(tpstr2);
			tpstr2.appendChild(doc.createTextNode(ai.getTempStr2()));
			
			Element usertype=doc.createElement("usertype");
			le.appendChild(usertype);
			usertype.appendChild(doc.createTextNode(String.valueOf(ai.usertype)));
			
			Element userstatus=doc.createElement("userstatus");
			le.appendChild(userstatus);
			userstatus.appendChild(doc.createTextNode(String.valueOf(ai.userstatus)));
			
			Element checklevel=doc.createElement("checklevel");
			le.appendChild(checklevel);
			checklevel.appendChild(doc.createTextNode(String.valueOf(ai.checklevel)));
			
			Element checkrange=doc.createElement("checkrange");
			le.appendChild(checkrange);
			checkrange.appendChild(doc.createTextNode(ai.checkrange));
			
			//
			Element realname=doc.createElement("realname");
			le.appendChild(realname);
			realname.appendChild(doc.createTextNode(ai.realname));
			
			Element idcardno=doc.createElement("idcardno");
			le.appendChild(idcardno);
			idcardno.appendChild(doc.createTextNode(ai.idcardno));
			
			Element photo=doc.createElement("photo");
			le.appendChild(photo);
			photo.appendChild(doc.createTextNode(ai.getPhotoStr()));
			
			Element phonemobile=doc.createElement("phonemobile");
			le.appendChild(phonemobile);
			phonemobile.appendChild(doc.createTextNode(ai.phonemobile));
			
			Element phonefix=doc.createElement("phonefix");
			le.appendChild(phonefix);
			phonefix.appendChild(doc.createTextNode(ai.phonefix));
			
			Element email=doc.createElement("email");
			le.appendChild(email);
			email.appendChild(doc.createTextNode(ai.email));
			
			Element address=doc.createElement("address");
			le.appendChild(address);
			address.appendChild(doc.createTextNode(ai.address));
			
		}
		return doc;
	}
	
    //Read
	public static List<AdminItem> XmlToAdminList(Document doc){
		List<AdminItem> list=new ArrayList<AdminItem>();
		
		Element root = doc.getDocumentElement();
		NodeList items = root.getElementsByTagName("AdminItem");
		for (int i = 0; i < items.getLength(); i++) //�����ж����ֻ��ȡ1��
		{
			AdminItem ai=new AdminItem();
			
			Element ain = (Element)items.item(i);
			/*
			ai.username=(ain.getElementsByTagName("username").item(0).getFirstChild().getNodeValue());
			ai.password=(ain.getElementsByTagName("password").item(0).getFirstChild().getNodeValue());
			ai.setTempStr1(ain.getElementsByTagName("tpstr1").item(0).getFirstChild().getNodeValue());
			ai.setTempStr2(ain.getElementsByTagName("tpstr2").item(0).getFirstChild().getNodeValue());			
			ai.realname=(ain.getElementsByTagName("realname").item(0).getFirstChild().getNodeValue());
			ai.idcardno=(ain.getElementsByTagName("idcardno").item(0).getFirstChild().getNodeValue());
			ai.setPhotoStr(ain.getElementsByTagName("photo").item(0).getFirstChild().getNodeValue());
			*/
			ai.username=getNodeString(ain,"username");
			ai.password=getNodeString(ain,"password");
			ai.setTempStr1(getNodeString(ain,"tpstr1"));
			ai.setTempStr2(getNodeString(ain,"tpstr2"));
			
			ai.usertype=Integer.valueOf(ain.getElementsByTagName("usertype").item(0).getFirstChild().getNodeValue());
			ai.userstatus=Integer.valueOf(ain.getElementsByTagName("userstatus").item(0).getFirstChild().getNodeValue());
			ai.checklevel=Integer.valueOf(ain.getElementsByTagName("checklevel").item(0).getFirstChild().getNodeValue());
			ai.checkrange=getNodeString(ain,"checkrange");
			
			ai.realname=getNodeString(ain,"realname");
			ai.idcardno=getNodeString(ain,"idcardno");
			ai.setPhotoStr(getNodeString(ain,"photo"));
			
			ai.phonemobile=getNodeString(ain,"phonemobile");
			ai.phonefix=getNodeString(ain,"phonefix");
			ai.email=getNodeString(ain,"email");
			ai.address=getNodeString(ain,"address");
			
			list.add(ai);
		}
		
		return list;
	}
	
	public static PositionItem XmlToPositionItem(Document doc){
		PositionItem positem=new PositionItem();
		Element root = doc.getDocumentElement();
		NodeList items = root.getElementsByTagName("PositionItem");
		Element lin = (Element)items.item(0);
		
		NodeList items2=lin.getElementsByTagName("City");
		for(int i=0;i<items2.getLength();i++){
			City ct=new City();
			Element cin=(Element)items2.item(i);
			ct.cityid=Integer.valueOf(cin.getElementsByTagName("cityid").item(0).getFirstChild().getNodeValue());			
			ct.cityname=cin.getElementsByTagName("cityname").item(0).getFirstChild().getNodeValue();

			NodeList items3=cin.getElementsByTagName("Distric");
			for(int j=0;j<items3.getLength();j++){
				Distric dt=new Distric();
				Element uin=(Element)items3.item(j);				
				dt.districid=Integer.valueOf(uin.getElementsByTagName("districid").item(0).getFirstChild().getNodeValue());
				dt.districname=uin.getElementsByTagName("districname").item(0).getFirstChild().getNodeValue();
				
				NodeList items4=uin.getElementsByTagName("Township");
				for(int k=0;k<items4.getLength();k++){
					Township ts=new Township();
					Element tin=(Element)items4.item(k);				
					ts.townshipid=Integer.valueOf(tin.getElementsByTagName("townshipid").item(0).getFirstChild().getNodeValue());
					ts.townshipname=tin.getElementsByTagName("townshipname").item(0).getFirstChild().getNodeValue();
				
					NodeList items5=tin.getElementsByTagName("Village");
					for(int l=0;l<items5.getLength();l++){
						Village vl=new Village();
						Element vin=(Element)items5.item(l);				
						vl.villageid=Integer.valueOf(vin.getElementsByTagName("villageid").item(0).getFirstChild().getNodeValue());
						vl.villagename=vin.getElementsByTagName("villagename").item(0).getFirstChild().getNodeValue();
					
						ts.VillageList.add(vl);
					}
					
					dt.TownshipList.add(ts);
				}
				
				ct.DistricList.add(dt);
			}
			positem.CityList.add(ct);
		}
			
		return positem;
	}
	
	public static PositionItem parasePositionItem(InputStream inputStream){
		PositionItem positem=new PositionItem();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc=null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			Element root = doc.getDocumentElement();
			NodeList items = root.getElementsByTagName("PositionItem");
			Element lin = (Element)items.item(0);
			
			NodeList items2=lin.getElementsByTagName("City");
			for(int i=0;i<items2.getLength();i++){
				City ct=new City();
				Element cin=(Element)items2.item(i);
				ct.cityid=Integer.valueOf(cin.getElementsByTagName("cityid").item(0).getFirstChild().getNodeValue());			
				ct.cityname=cin.getElementsByTagName("cityname").item(0).getFirstChild().getNodeValue();

				NodeList items3=cin.getElementsByTagName("Distric");
				for(int j=0;j<items3.getLength();j++){
					Distric dt=new Distric();
					Element uin=(Element)items3.item(j);				
					dt.districid=Integer.valueOf(uin.getElementsByTagName("districid").item(0).getFirstChild().getNodeValue());
					dt.districname=uin.getElementsByTagName("districname").item(0).getFirstChild().getNodeValue();
					
					NodeList items4=uin.getElementsByTagName("Township");
					for(int k=0;k<items4.getLength();k++){
						Township ts=new Township();
						Element tin=(Element)items4.item(k);				
						ts.townshipid=Integer.valueOf(tin.getElementsByTagName("townshipid").item(0).getFirstChild().getNodeValue());
						ts.townshipname=tin.getElementsByTagName("townshipname").item(0).getFirstChild().getNodeValue();
					
						NodeList items5=tin.getElementsByTagName("Village");
						for(int l=0;l<items5.getLength();l++){
							Village vl=new Village();
							Element vin=(Element)items5.item(l);				
							vl.villageid=Integer.valueOf(vin.getElementsByTagName("villageid").item(0).getFirstChild().getNodeValue());
							vl.villagename=vin.getElementsByTagName("villagename").item(0).getFirstChild().getNodeValue();
						
							ts.VillageList.add(vl);
						}
						
						dt.TownshipList.add(ts);
					}
					
					ct.DistricList.add(dt);
				}
				positem.CityList.add(ct);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return positem;
	}
	
	public static Document PositionItemToXml(PositionItem pi){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try{
		   builder = dbf.newDocumentBuilder();
		}
		catch(Exception e){}
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("PositionItem");
		doc.appendChild(root); //����Ԫ����ӵ��ĵ���
		
		Element le=doc.createElement("PositionItem");
		root.appendChild(le);
		
		for(int i=0;i<pi.CityList.size();i++){	//����
			Element ct=doc.createElement("City");
			le.appendChild(ct);
			
			//
			Element cityid=doc.createElement("cityid");
			ct.appendChild(cityid);
			cityid.appendChild(doc.createTextNode(String.valueOf(pi.CityList.get(i).cityid)));
			
			Element cityname=doc.createElement("cityname");
			ct.appendChild(cityname);
			cityname.appendChild(doc.createTextNode(pi.CityList.get(i).cityname));
			
			for(int j=0;j<pi.CityList.get(i).DistricList.size();j++){	//��
				Element dt=doc.createElement("Distric");
				ct.appendChild(dt);
				
				//
				Element districid=doc.createElement("districid");
				dt.appendChild(districid);
				districid.appendChild(doc.createTextNode(String.valueOf(pi.CityList.get(i).DistricList.get(j).districid)));
				
				Element districname=doc.createElement("districname");
				dt.appendChild(districname);
				districname.appendChild(doc.createTextNode(pi.CityList.get(i).DistricList.get(j).districname));
			
				for(int k=0;k<pi.CityList.get(i).DistricList.get(j).TownshipList.size();k++){	//��
					Element ts=doc.createElement("Township");
					dt.appendChild(ts);
					//
					Element townshipid=doc.createElement("townshipid");
					ts.appendChild(townshipid);
					townshipid.appendChild(doc.createTextNode(String.valueOf(pi.CityList.get(i).DistricList.get(j).TownshipList.get(k).townshipid)));
					
					Element townshipname=doc.createElement("townshipname");
					ts.appendChild(townshipname);
					townshipname.appendChild(doc.createTextNode(pi.CityList.get(i).DistricList.get(j).TownshipList.get(k).townshipname));
					
					for(int l=0;l<pi.CityList.get(i).DistricList.get(j).TownshipList.get(k).VillageList.size();l++){	//��
						Element vl=doc.createElement("Village");
						ts.appendChild(vl);
						//
						Element villageid=doc.createElement("villageid");
						vl.appendChild(villageid);
						villageid.appendChild(doc.createTextNode(String.valueOf(pi.CityList.get(i).DistricList.get(j).TownshipList.get(k).VillageList.get(l).villageid)));
						
						Element villagename=doc.createElement("villagename");
						vl.appendChild(villagename);
						villagename.appendChild(doc.createTextNode(pi.CityList.get(i).DistricList.get(j).TownshipList.get(k).VillageList.get(l).villagename));
												
					}
				}
			}
		}
		
		return doc;
	}
	
	//HouseIndex
	//Write
    public static Document HouseIndexToXml(List<HouseIndex> list){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try{
		   builder = dbf.newDocumentBuilder();
		}
		catch(Exception e){}
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("AdminItem");
		doc.appendChild(root); //����Ԫ����ӵ��ĵ���
		
		for(int i=0;i<list.size();i++){
			HouseIndex hi=list.get(i);
		
			Element le=doc.createElement("HouseIndex");
			root.appendChild(le);
			//
			Element houserfid=doc.createElement("houserfid");
			le.appendChild(houserfid);
			houserfid.appendChild(doc.createTextNode(hi.houserfid));
			
			Element lordname=doc.createElement("lordname");
			le.appendChild(lordname);
			lordname.appendChild(doc.createTextNode(hi.lordname));
			
			Element lordaddress=doc.createElement("lordaddress");
			le.appendChild(lordaddress);
			lordaddress.appendChild(doc.createTextNode(hi.lordaddress));
		}
		return doc;
	}
	
    //Read
	public static List<HouseIndex> XmlToHouseIndexList(Document doc){
		List<HouseIndex> list=new ArrayList<HouseIndex>();
		
		Element root = doc.getDocumentElement();
		NodeList items = root.getElementsByTagName("HouseIndex");
		for (int i = 0; i < items.getLength(); i++) 
		{
			HouseIndex hi=new HouseIndex();
			
			Element ain = (Element)items.item(i);
			hi.houserfid=getNodeString(ain,"houserfid");
			hi.houserfid=hi.houserfid.replaceAll("\\s","");
			hi.lordname=getNodeString(ain,"lordname");
			hi.lordaddress=getNodeString(ain,"lordaddress");
			
			list.add(hi);
		}
		
		return list;
	}

	public static List<HouseIndex> paraseHouseIndexList(InputStream inputStream){
		List<HouseIndex> list=new ArrayList<HouseIndex>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc=null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			Element root = doc.getDocumentElement();
			NodeList items = root.getElementsByTagName("HouseIndex");
			for(int i=0;i<items.getLength();i++){
				HouseIndex hi=new HouseIndex();
				Element lin = (Element)items.item(i);
				hi.houserfid=getNodeString(lin,"houserfid");
				hi.houserfid=hi.houserfid.replaceAll("\\s","");
				hi.lordname=getNodeString(lin,"lordname");
				hi.lordaddress=getNodeString(lin,"lordaddress");
				list.add(hi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
