package com.demoweb.common;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	
	//데이터 암호화기능 구현 메서드
	public static byte[] getHashedData(String source, String algorithm) {
		
		byte[] hashedData = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			hashedData = md.digest(source.getBytes());
		} catch (NoSuchAlgorithmException ex) {	
			hashedData = null;
		}
		
		return hashedData;
	}
	
	public static String getHashedString(String source, String algorithm) {
		byte[] hashedData = getHashedData(source, algorithm);
		
		if (hashedData == null) return null;
		 
		String hashedString = "";
		for (int i = 0; i < hashedData.length; i++) {
			String hexString = 
				Integer.toHexString((int)hashedData[i] & 0x000000ff);
			if (hexString.length() < 2)
				hexString = "0" + hexString;
			
			hashedString += hexString;
		}
		
		return hashedString;
	}
	//중복되지 않는 고유한 파일 이름 생성 메서드
	public static String getUniqueFileName(String path, String fileName)
    {	
        String name =
            fileName.substring(0, fileName.lastIndexOf("."));
        String ext =
            fileName.substring(fileName.lastIndexOf("."));
        int index = 1;
        while (true) {
        	File file = 
        		new File(path + "\\" + name + "_" + index + ext);
        	if (file.exists())
        		index++;
        	else
        		break;
        }

        return name + "_" + index + ext;
    }

}
