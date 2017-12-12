package com.financial.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;



public class HttpUtils {
	public static String PATH;
	public static URL url;
	public static boolean netflag=true;
	
	public static void setUrl(String PATH){
		try {
			url=new URL(PATH);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String sendPostMessage(Map<String,String> params,String encode,URL url) {
		
		StringBuffer buffer=new StringBuffer();
		if(params!=null&!params.isEmpty()){
			for(Map.Entry<String ,String> entry:params.entrySet()){
				try {
					buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), encode)).append("&");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
	//	buffer.deleteCharAt(buffer.length()-1);
		System.out.println(buffer.toString());
		HttpURLConnection urlConnection=null;
		try {
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.setDoInput(true);//表示从服务器获取数据
			urlConnection.setDoOutput(true);//表示向服务器写数�?
			byte[] mydata=buffer.toString().getBytes();
			urlConnection.setRequestProperty("content-Type", "application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("content-Length", String.valueOf(mydata.length));
			//获得输出�?
				OutputStream outputStream=urlConnection.getOutputStream();
				outputStream.write(mydata);
			//获得服务器返回的结果和状态码
				int responseCode=urlConnection.getResponseCode();
				if(responseCode==200){
					System.out.println("OK");
					return ChangeInputStream(urlConnection.getInputStream(), encode);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			netflag=false;
			e.printStackTrace();
		}
		return "";
		
	}
	//将一个输入流转换成指定编码的字符�?
	public static String ChangeInputStream(InputStream inputStream,String encode) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
		byte[] data=new byte[1024];
		int len=0;
		String result="";
		if(inputStream!=null){
			try {
				while((len=inputStream.read(data))!=-1){
					outputstream.write(data, 0, len);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			result=new String(outputstream.toByteArray(),encode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	public static void main(String[] args){
		HttpUtils.PATH="http://c8bf5a85.ngrok.io/test_myhttp/RegisterAction";
		HttpUtils.setUrl(PATH);
		Map<String,String> params=new HashMap<String,String>();
		params.put("sel_name", "admin");
		params.put("sel_pword", "123");
		String result=sendPostMessage(params,"utf-8",url);
		System.out.println("-result->"+result);
	}
	
	
}
