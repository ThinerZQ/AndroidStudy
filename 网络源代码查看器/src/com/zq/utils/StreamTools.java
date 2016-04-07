package com.zq.utils;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;

public class StreamTools {

	/**
	 * 把输入流的数据转化成字符串
	 * @param is
	 * @return
	 * @throws Exception 
	 */
	public static String readInputStream(InputStream is){
		
		ByteArrayOutputStream baos  = new ByteArrayOutputStream();
	
		int len=0;
		byte[] buffer = new byte[1024];
		try {
			while((len=is.read(buffer))!=-1){
				baos.write(buffer,0,len);
			}
			is.close();
			baos.close();
			byte[] result = baos.toByteArray();
			return new String(result);
			
		} catch (Exception e) {
		
			e.printStackTrace();
			return "转换错误";
		}
	
	
	
	
	
	
	
	
	}
}
