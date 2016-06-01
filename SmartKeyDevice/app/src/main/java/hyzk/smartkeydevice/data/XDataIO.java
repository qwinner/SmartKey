package hyzk.smartkeydevice.data;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XDataIO {
	
	//XML File Write
	public static void WriteXmlFile(Document doc,String filename,String codetype) {
		   try {
			   FileOutputStream fos = new FileOutputStream(filename);
			   OutputStreamWriter outwriter = new OutputStreamWriter(fos);
			   try {
					Source source = new DOMSource(doc);
					Result result = new StreamResult(outwriter);
					Transformer xformer = TransformerFactory.newInstance().newTransformer();
					xformer.setOutputProperty(OutputKeys.ENCODING, codetype);	//"gb2312" "utf-8"
					xformer.transform(source, result);
				}
				catch (TransformerConfigurationException e) {
					e.printStackTrace();
				}
				catch (TransformerException e) {
					e.printStackTrace();
				}
			   outwriter.close();
			   fos.close();
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	}
	
	//XML File Read
	public static Document ReadXmlFile(String filename){
		FileInputStream fin=null;
		InputStream inStream=null;
		Document doc=null;
		try {
			fin = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}		
		inStream = new BufferedInputStream(fin);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			//���ַ��
			//ByteArrayInputStream is = new ByteArrayInputStream(txt.getBytes());
			doc = builder.parse(inStream);			
			//inStream.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static InputStream ReadXmlFileEx(String filename){
		FileInputStream fin=null;
		try {
			fin = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}		
		return (new BufferedInputStream(fin));		
	}
	
	
	//JSon File Write
	public static void WriteJsonFile(String txt,String filename){
		try{
            File file= new File(filename);
            FileWriter fw= new FileWriter(file);
            BufferedWriter bw= new BufferedWriter(fw);// ʹ�û����������װ�����
            bw.write(txt);
            bw.newLine();
            bw.flush();
            bw.close();
         }catch (Exception e) {
            e.printStackTrace();
         }
	}
	
	//JSon File Read
	public static String ReadJsonFile(String filename){
		String txt=null;
	    try{
	         File file= new File(filename);
	         InputStream inputStream = null;
	         if(file.exists()){
	        	 inputStream= new FileInputStream(file);
		         int size = inputStream.available();
		         byte[]buffer = new byte[size];
		         inputStream.read(buffer);
		         inputStream.close();
		         txt= new String(buffer);
	         }	         
	    }catch (IOException e) {
	         e.printStackTrace();
	    }
	    return txt;
	}
		
}
