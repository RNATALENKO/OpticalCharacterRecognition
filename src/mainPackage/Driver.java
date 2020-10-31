

/*https://www.youtube.com/watch?v=6sdhCwfP-lg
 * 
 * download open CV, import cv library
 * 
 * download tesseract and dictionary
 * 
 * download trained data, move it into tesseract tessdata file
 * 
 * import tess4j library using maven dependency code, convert project to maven, incorporate dependency, from maven tess4j repo
 * 
 * config environ path to opencv library
 * 
 * 
 */

package mainPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.swing.JFileChooser;
import javax.imageio.metadata.*;

import org.apache.sanselan.*;
import org.apache.xmlgraphics.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

class TextRecognizer {
	
	static String SRC_PATH = "";
	static Tesseract tesseract = new Tesseract(); 
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		tesseract.setDatapath("C:\\Users\\rnata\\Desktop\\tesseract-master\\tesseract-master\\tessdata");
	}
	
	
	//method to gray and extract text
	String extractString(Mat inputMat) {
		String text = "";
		Mat gray = new Mat(); //stores values of an image
		
		
		//create gray output image
		Imgproc.cvtColor(inputMat, gray, Imgproc.COLOR_BGR2GRAY);
		
		
		
		//perform OCR on a new file with src path and gray png 
		try {
			text = tesseract.doOCR(new File(SRC_PATH + "gray.png"));
		}
		catch(TesseractException e) {
			e.printStackTrace();
		}
			
		return text; 
	}
	

}
	

public class Driver {

	public static void main(String[] args) {

		
			
		}
	}

