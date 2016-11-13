package com.ubs.risk.arisk.ocr_demo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		int count =1 ;

		while(count<=2){
			Webcam webcam = Webcam.getDefault();
			webcam.open();
			BufferedImage image = webcam.getImage();

			String filepath = "C:/softwares/tesseract/test"+count+".png";
			

			try {
				ImageIO.write(image, "PNG", new File(filepath));
				String croppedImage = filepath;
				//croppedImage = cropImage(filepath,count);
				

				File imageFile = new File(croppedImage);
				Tesseract instance = new Tesseract(); //
				String result = instance.doOCR(imageFile);
				System.out.println("result one is from the following count "+count+"\n"+result);
				count++;
				Thread.sleep(3000);

			} catch (IOException  e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			catch (TesseractException e) {
				System.err.println(e.getMessage());
			}
			catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	
	public static String cropImage(String imageFile,int count){
		
		String destpath = "C:/softwares/tesseract/test_cropped_"+count+".png";
		try{
			
		
		File outputFile=new File(destpath);
        FileInputStream fis=new FileInputStream(imageFile);
        BufferedImage bimage=ImageIO.read(fis);
        System.out.println("Image Origilnal Height=="+bimage.getHeight());
        int cropWidth = 500;
		int cropHeight= 500;
		BufferedImage oneimg=new BufferedImage(cropHeight,cropWidth,bimage.getType());
        Graphics2D gr2d=oneimg.createGraphics();
        int commonPadding =10;
		int windowLeft =0;
		int windowTop = 10;
		boolean isOkResolution=gr2d.drawImage(bimage,0,0,cropWidth,cropHeight,windowLeft-commonPadding,windowTop-commonPadding,(windowLeft+cropWidth)-commonPadding,(windowTop+cropHeight)-commonPadding,null);
        gr2d.dispose();
        ImageIO.write(oneimg,"png",outputFile);
        int newImageWidth = cropWidth * (2);
        int newImageHeight = cropHeight *(2);
        
        
        
    } catch (FileNotFoundException fe) {
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return destpath;
	}
	
	

}
