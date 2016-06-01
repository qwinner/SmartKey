package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

public class HouseCheckItem {
	 public int houseno;
     public int checkno;
     
	public String checkid = "";     //���ţ����:ʱ��+rfid+����ţ�����ſ��Բ�Ҫ��
    public String checktime = "";   // -- Ѳ��ʱ��  //����д�����淿�䷢��ʱ���ʱ��
    public String houserfid = "";     // -- ���ݱ��
    public String checkmanuser = "";// -- Ѳ���˱��
    public String checkmanname = "";// -- Ѳ��������
    public int checkedroomnum = 0;  // -- �Ѳ鷿����
    public int ckriskroomnum = 0;   // -- �Ѳ鷿�����б���������
    public int uncheckedroomnum = 0;//-- δ�鷿����
    
    public List<RoomCheckItem> RoomCheckList = new ArrayList<RoomCheckItem>();	//���ݼ���б�
}
