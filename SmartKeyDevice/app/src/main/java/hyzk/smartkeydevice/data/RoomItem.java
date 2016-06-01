package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

public class RoomItem {
	 public int houseno;
     public int roomno;
     
	public String houserfid = "";         //���ݱ��
    public String roomid = "";          //����������
    public String roomname = "";        //������
    public int properties = 0;          //�������ʣ�����/���
    public int selforrent = 0;          //��ס���ͣ���ס/����
    public int warmtype = 0;            //ȡů���ͣ��硢���й�ů��ú����ȡů
    public int checknum = 0;            //������
    public int area = 0;   				//���

    public List<TenantItem> TenantList = new ArrayList<TenantItem>();	//���ݼ���б�
}
