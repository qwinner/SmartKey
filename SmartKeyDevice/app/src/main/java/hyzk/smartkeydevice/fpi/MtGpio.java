package hyzk.smartkeydevice.fpi;


import android.util.Log;

public class MtGpio {
	
	private boolean mOpen = false;
	private static MtGpio mMe = null;
	private MtGpio() {
		mOpen = openDev()>=0?true:false;
		Log.d("MtGpio","openDev->ret:"+mOpen);
	}
	
	public static MtGpio getInstance(){
		if (mMe == null){
			mMe = new MtGpio();
		}
		return mMe;
	}
	
	public void BCPowerSwitch(boolean bonoff){
		if(bonoff){
			//Barcode Power
			sGpioMode(105,0);
			sGpioDir(105,1);
			sGpioOut(105,1);			
		}else{
			sGpioMode(105,0);
	    	sGpioDir(105,1);
	    	sGpioOut(105,0);
		}
	}
	
	public void BCReadSwitch(boolean bonoff){
		if(bonoff){
			//Barcode TRIG
			sGpioMode(106,0);
			sGpioDir(106,1);
			sGpioOut(106,1);			
		}else{
			sGpioMode(106,0);
	    	sGpioDir(106,1);
	    	sGpioOut(106,0);
		}
	}
	
	public void FPPowerSwitch(boolean bonoff){
		if(bonoff){
			//FP Power
			sGpioMode(119,0);
			sGpioDir(119,1);
			sGpioOut(119,1);
		}else{
			sGpioMode(119,0);
	    	sGpioDir(119,1);
	    	sGpioOut(119,0);
		}
	}
	
	public void RFPowerSwitch(boolean bonoff){
		if(bonoff){
			//RFID 1.8V Reset
			sGpioMode(20,0);
			sGpioDir(20,1);
			sGpioOut(20,1);
	        
			//RFID M
			sGpioMode(19,0);
			sGpioDir(19,1);
			sGpioOut(19,0);
			
			//RF EN
			sGpioMode(107,0);
			sGpioDir(107,1);
			sGpioOut(107,1);
		}else{
			sGpioMode(20,0);
			sGpioDir(20,1);
			sGpioOut(20,0);
	        
			sGpioMode(19,0);
			sGpioDir(19,1);
			sGpioOut(19,0);
			
			sGpioMode(107,0);
			sGpioDir(107,1);
			sGpioOut(107,0);
		}
	}
	
	public boolean isOpen(){
		return mOpen;
	}
	
	public void sGpioDir(int pin, int dir){
		setGpioDir(pin,dir);
	}
	
	public void sGpioOut(int pin, int out){
		setGpioOut(pin,out);
	}
	
	public int getGpioPinState(int pin){
		return getGpioIn(pin);
	}
	
	public void sGpioMode(int pin, int mode){
		setGpioMode(pin, mode);
	}
	
	// JNI
	private native int openDev();
	public native void closeDev();
	private native int setGpioMode(int pin, int mode);
	private native int setGpioDir(int pin, int dir);
	private native int setGpioPullEnable(int pin, int enable);
	private native int setGpioPullSelect(int pin, int select);
	public native  int setGpioOut(int pin, int out);
	private native int getGpioIn(int pin);
	static {
		System.loadLibrary("mtgpio");
	}
}
