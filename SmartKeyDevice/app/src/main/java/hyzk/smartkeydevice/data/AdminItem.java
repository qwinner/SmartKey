package hyzk.smartkeydevice.data;

import android.util.Base64;

//��ָ�Ʊ��浽XML�ļ���ʽ��

public class AdminItem {
	public String 	username="";	//��¼�˺�	//�û���Ҳ��ΪCheckMain ID
	public String	password="";	//����
	public String 	tpstr1="null";	//ָ��1
	public String 	tpstr2="null";	//ָ��2
	
	public int 	usertype=0;		//Ѳ��Ա���Ǹ�����
	public int		userstatus=0;		//�û�״̬�������Ƿ����ֹ��¼��
	public int		checklevel=0;		//��Ѳ����Χ����1ʡ2��3����4����ֵ�5ׯ������6��
	public String	checkrange="";		//��Ѳ����Χ��ţ�����Ͱ�XML��JSON����,���߶��ŷָ�
	
	public String 	realname="";	//����������
	public String	idcardno="";	//���֤	
	public String  	photo="null";	//��Ƭ
	public String	phonemobile="";	//�ƶ��绰
	public String	phonefix="";	//�̶��绰
	public String	email="";		//����
	public String 	address="";		//סַ
	
	public void Clear(){
		username="";		//��¼�˺�
		password="";	//����
		realname="";			//����������
		tpstr1="null";		//ָ��1
		tpstr2="null";		//ָ��2
		photo="null";		//��Ƭ
		usertype=0;
		userstatus=0;
		checklevel=0;
		checkrange="";
		phonemobile="";
		phonefix="";
		email="";
		address="";
	}
	//
	public void setTempStr1(String tpstr){
		this.tpstr1=tpstr;
	}
	
	public String getTempStr1(){
		return tpstr1;
	}
	
	public byte[] getTemplate1(){
		return Base64.decode(tpstr1,Base64.DEFAULT);
	}
	
	public void setTemplate1(byte[] tpbuf,int size){
		this.tpstr1= Base64.encodeToString(tpbuf,Base64.DEFAULT);
	}
	
	//
	public void setTempStr2(String tpstr){
		this.tpstr2=tpstr;
	}
		
	public String getTempStr2(){
		return tpstr2;
	}
	
	public byte[] getTemplate2(){
		return Base64.decode(tpstr2,Base64.DEFAULT);
	}
	
	public void setTemplate2(byte[] tpbuf,int size){
		this.tpstr2= Base64.encodeToString(tpbuf,Base64.DEFAULT);
	}
	
	//
	public void setPhotoStr(String tpstr){
		this.photo=tpstr;
	}
	
	public String getPhotoStr(){
		return photo;
	}
	
	public void setPhoto(byte[] tpbuf,int size){
		this.photo= Base64.encodeToString(tpbuf,Base64.DEFAULT);
	}
	
	public byte[] getPhoto(){
		return Base64.decode(this.photo,Base64.DEFAULT);
	}
	
	/*
	public void setTemplate(byte[] tpbuf,int size){
		System.arraycopy(tpbuf,0,this.template, 0, size);	//template=tpbuf;
	}
	
	public byte[] getTemplate(){
		return template;
	}
	
	public void setTempStr(String tpstr){
		byte b[]=android.util.Base64.decode(tpstr,Base64.DEFAULT);
		System.arraycopy(b,0,this.template,0,b.length);
	}
	
	public String getTempStr(){
		return android.util.Base64.encodeToString(template,Base64.DEFAULT);
	}
	*/
}
