package ImagePreProcessor;

/*
 * Image pre-processing for OCR is a way to alter the image so that the computer vision program has an easier way to read text
 * 
 * tutorials:
 * https://stackoverflow.com/questions/48170714/reading-text-from-image-using-tesseract-and-opencv-java
 * 
 * 
 */

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class ImagePreProcessor {

	public static void main(String[] args) {
		
		//load the library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		
		//store the original file in a matrix
		
		String originalImagePath = "C:\\Users\\rnata\\Desktop\\ImgPreproc\\bookcover.png";
		Mat originalImage = Imgcodecs.imread(originalImagePath);
		//turn the image to gray scale reqs input mat, an empty destination mat, the data type
		Mat greyImage = new Mat(); 
		
		Imgproc.cvtColor(originalImage, greyImage, Imgproc.COLOR_RGB2GRAY,0); //convert original image to grey image
		
		
		//destination for grey blur image
		Mat greyBlurImage = new Mat(); 
		
		Imgproc.GaussianBlur(greyImage, greyBlurImage, new Size(0,0), 2); //apply gaussian blur to the image
		
		//calculate sum of the blurred matrix and unblurred
		Mat weightedImage = new Mat(); 
		
		Core.addWeighted(greyBlurImage, 1.5, greyImage, -.5, .5, weightedImage);
		
		
		//take weighted image and perform binarization
		Mat binaryImage = new Mat(); 
		Imgproc.threshold(weightedImage, binaryImage, 127, 255, Imgproc.THRESH_BINARY);
		
		
		
		//write images to folder
		Imgcodecs.imwrite("C:\\Users\\rnata\\Desktop\\ImgPreproc\\greyImage.png", greyImage);
		Imgcodecs.imwrite("C:\\Users\\rnata\\Desktop\\ImgPreproc\\greyBlurImage.png", greyBlurImage);
		Imgcodecs.imwrite("C:\\Users\\rnata\\Desktop\\ImgPreproc\\weightedImage.png", weightedImage);
		
		//write the binary image to folder
		MatOfInt intMat = new MatOfInt(Imgcodecs.IMWRITE_PNG_COMPRESSION);
		File ocrImage = new File("ocrImage.png");
		Imgcodecs.imwrite("C:\\Users\\rnata\\Desktop\\ImgPreproc\\ocrImage.png", binaryImage, intMat );
		
		
	}

}
