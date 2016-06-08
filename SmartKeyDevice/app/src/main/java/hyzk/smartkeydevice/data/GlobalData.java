package hyzk.smartkeydevice.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;

public class GlobalData {


	
	public static boolean FileIsExists(String sDir,String sFile) {
		 File file = new File(sDir);
		 List<Uri> fsList = new ArrayList<Uri>();
		 if(file.isDirectory()) {
			 File[] all_file = file.listFiles();
			 if (all_file != null) {
				 for(int i=0;i<all_file.length;i++){
					 fsList.add(Uri.parse(all_file[i].toString()));
				 }
			 }
		 } 
		 for(int i=0;i<fsList.size();i++) {
	    	if(fsList.get(i).equals(Uri.parse(sFile)))
	    		return true;
		 }
		 return false;
	}
	 

}
