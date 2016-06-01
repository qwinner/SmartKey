package hyzk.smartkeydevice.data;

public class Conversions {
	
	private static Conversions mCom=null;
	
	public static Conversions getInstance(){
		if(mCom==null){
			mCom=new Conversions();
		}
		return mCom;
	}
	
	public native int CheckSum(byte[] data,int size);
	public native void GetSE955Cmd(int icmd,byte[] cmdoup);
	
	static {
		System.loadLibrary("conversions");
	}
}
