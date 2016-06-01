package hyzk.smartkeydevice.data;

import java.util.ArrayList;
import java.util.List;

public class HouseItem {
	public int houseno;
	
	public String houserfid="";
    public String detailaddress="";
    public int lucity=0;
    public int ludistric=0;
    public int lutownship=0;
    public int luvillage=0;
    public int roomselfnum=0;
    public int roomrentnum=0;
    public String lordid="";
    public String lordname="";
    public String lordidcard="";
    public String lordphonemobile="";
    public String lordphonefix="";
    public String lordaddress="";
    public String lordphotopath="";
    public String agentname="";
    public String agentphonemobile="";
    public String agentphonefix="";
    public String chargername="";
    public String chargerphonemobile="";
    public String chargerphonefix="";
    public int houseproperty=0;
    public String threeparts="";
    public int oldalarmnum=0;
    public int olddisplaynum=0;
    public int totalalarmnum=0;
    public String displayno="";

    public String lastcheckno = "";     //���һ�μ��ļ�¼
    //public int roomcount = 0;	//��鷿������
    public List<RoomItem> RoomList = new ArrayList<RoomItem>();	//���ݼ���б�
}
