package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

public class RoomCheckItem {
	
	public int checkmode;			//������״̬��0��ʾδ���,1��ʾ���δ���,2��ʾ������
	
	public int houseno;
    public int roomno;
    public int checkno;
     
	public String checkid = "";         //Ѳ���¼���:ʱ��+rfid+�����
    public String checktime = "";       //-- Ѳ��ʱ��  //����д�ɷ���ʱ���ʱ��
    public String houserfid = "";         //���ݱ��
    public String roomid = "";          //����������
    public String roomname = "";        //������
    public String checkmanuser = "";    //Ѳ���˵�¼��
    public String checkmanname = "";    //Ѳ��������
    public String dutymanno = "";       //�����˱��
    public String dutymanname = "";     //����������
    public int checkstatus = 0;     //Ѳ��״̬���Ѳ顢�Ѳ��б���������δ�顢����δ��
    public int selforrent = 0;      //��ס���ͣ���ס/����
    public int warmtype = 0;        //ȡů���ͣ��硢���й�ů��ú����ȡů
    public String nowarmway = "";       //��ȡů�����ȡů��ʽ     
    public int alarmstatus =0;     //������״̬
    public String alarmno = "";         //���������
    public String alarmphpath = "";     //��������Ƭ��ַ
    public int windscoopstatus = 0; //�綷״̬
    public String windsphpath = "";     //�綷��Ƭ��ַ
    public int cookerstatus = 0;    //¯��״̬
    public String cookerphpath = "";    //¯����Ƭ��ַ
    public int chimneystatus = 0;   //�̴�״̬
    public String chimneyphpath = "";   //�̴���Ƭ��ַ
    public int chthreestatus = 0;   //�̴���ͨ״̬
    public String chthreephpath = "";   //�̴���ͨ��Ƭ��ַ
    public int chclearstatus = 0;   //�̵�ͨ��״̬
    public String chclearphpath = "";   //�̵�ͨ����Ƭ��ַ
    public int safetipstatus = 0;   //��ȫ��ʾ��״̬
    public String safetipphpath = "";   //��ȫ��ʾ����Ƭ��ַ
    public int gasnousstatus = 0;   //Ԥ��ú��ʶ״̬
    public String gasnousphpath = "";   //Ԥ��ú��ʶ��Ƭ��ַ
    public int safebookstatus = 0;  //��ȫ������״̬
    public String safebookphpath = "";  //��ȫ��������Ƭ��ַ
    public int dangernotifystatus = 0;   //����֪ͨ��״̬
    public String dangerntyphpath = ""; //����֪ͨ����Ƭ��ַ
    public String safebookrscpath = ""; //��ȡů�����ŵ����¼���ַ
    public String dangerntyrscpath = "";//�Ի����¼���ַ
    public int safebookrscstatus=0;	//��ŵ��״̬
    public int residentnum = 0;         //ס������
    public int resicheckednum = 0;      //�Ѳ���֤��ס������
    public String residentname = "";    //����һ��ס����ʵ����
    public String residentphone = "";   //����һ��ס���绰����

    public int waterfeestatus = 0;  //ˮ��״̬���ѽ���δ��
    public int cleanfeestatus = 0;  //�����״̬���ѽ���δ��
    
    public int area=0;
    
    public List<TenantCheckItem> TenantCheckList = new ArrayList<TenantCheckItem>();
}
