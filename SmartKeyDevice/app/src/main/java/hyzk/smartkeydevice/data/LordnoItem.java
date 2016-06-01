package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

public class LordnoItem {
	public String 	rfid="";		//RFID���
	public String 	id="";			//�������
	public String 	name="";		//��������	
	public String 	phone="";		//�����ֻ�
	public String	gphone="";		//�������
	public String	dname="";		//����������
	public String	dphone="";		//�������ֻ�
	public String	address="";		//��ַ	
	public int		zroomnum=0;		//������(��ס)
	public int		croomnum=0;		//������(����)
	public int		alarmnum=0;		//����������
	public String	dispsn="";		//��ʾ�ն˺�
	
	public String	adminid="";		//�����˱��
	public String	adminname="";	//����������
	
	public String 	city="";
	public String 	distric="";
	public String 	town="";
	public String 	village="";
	
	public int		roomcount=0;	//��鷿������
	public List<CheckItem>	CheckList=new ArrayList<CheckItem>();	//���ݼ���б�
}
