	package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

//�������浽XML��ʽ�ļ���

public class CheckItem {
	public String	RoomID="";	//����������
	public String 	RoomName=""; //������
	public int		Check=0;	//������״̬��0��ʾδ���,1��ʾ���δ���,2��ʾ������
	public int 		mode=0;		//ȡů��ʽ
	public int 		item1ret=0;		//������
	public String 	item1pic="";	//���������
	public int 		item2ret=0;		//�綷�����
	public String 	item2pic="";	//�綷�����ļ�
	public int 		item3ret=0;		//¯�߼����
	public String 	item3pic="";	//¯�������ļ�
	public int 		item4ret=0;		//�̵������
	public String 	item4pic="";	//�̵������ļ�
	public int 		item5ret=0;		//�̵���ͨ�����
	public String 	item5pic="";	//�̵���ͨ�����ļ�
	public int 		item6ret=0;		//��ȫ��ʾ������
	public String 	item6pic="";	//��ȫ��ʾ�������ļ�
	public int 		item7ret=0;		//Ԥ����ʶ�����
	public String 	item7pic="";	//Ԥ����ʶ�����ļ�
	public int 		item8ret=0;		//��ȫ����������
	public String 	item8pic="";	//��ȫ�����������ļ�
	public int 		item9ret=0;		//��ŵ������
	public String 	item9pic="";	//��ŵ�������ļ�
	
	public int 		item10ret=0;	//ˮ��
	public int 		item11ret=0;	//�����
	
	public int		UsersCount=0;
	public List<UserItem> 	UsersList=new ArrayList<UserItem>();	//ס���б�
	//public LordnoItem 	mLordnoItem=new LordnoItem();				//������Ϣ
	
}
