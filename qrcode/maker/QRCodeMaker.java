package com.kh.qrcode.maker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeMaker {

	//zxing
	public void makeQR(String url, String fileName) {
		// TODO Auto-generated method stub
		
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bm;
		try {
			
			bm = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300);
				MatrixToImageWriter.writeToStream(bm,"jpg", new FileOutputStream("C:\\CODE\\"+fileName + ".jpg"));
			    System.out.println("QR코드 파일이 생성되었습니다.");
			   
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
