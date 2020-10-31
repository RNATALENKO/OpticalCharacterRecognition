package ComputerVision;


/*Note: creating a user library is a way to reference one jar file, to multiple projects. 

 * then just import the packages which contain the classes.
 * 
 * 
 * 
 * 
 * 
 * 
 * Note: unsatisifed linked error means library was not loaded. You need to load the library to use opencv
 * 
 * Tutorials: https://www.tutorialspoint.com/opencv/index.htm
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class vision {

	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String originalImagePath = "C:\\Users\\rnata\\Desktop\\bookcover.png";
		
		
		//read the image and store as buffered image
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(originalImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get the parameters to create a matrix
		int rows = bi.getHeight();
		int cols = bi.getWidth();
		int type = CvType.CV_8UC3;
		
		Mat mat1 = new Mat(rows,cols, type);
		
		
		//another way to read the image is with a file path
		//imagecodecs class provides way to READ and WRITE images
		Imgcodecs imgcd = new Imgcodecs(); 
		Mat mat2 = Imgcodecs.imread("C:\\Users\\rnata\\Desktop\\bookcover.png");
		
		
		//imwrite() saves an image to a file, specify the file name/path, then the matrix to save it to that file
		//this will just write the image to a new location with the new name of "bookcover2"
		String fileName = "C:\\Users\\rnata\\Desktop\\bookcover2.png";
		Imgcodecs.imwrite(fileName, mat2);
		
		
		
		/*although you get a matrix from using imread and imwrite, you need a buffered image to use with swing, and javaFx GUI
		 * 
		 * 
		 */
		
		MatOfByte mob = new MatOfByte();  //create matrix of bytes
				
		Imgcodecs.imencode(".jpg", mat2, mob); //output the image into the matrix of bytes using imencode, encoding the img
		
		byte[] byteArray = mob.toArray(); //convert mat of bytes to byte array and store
		
		//create a byte array input stream with array of bytes
		InputStream in = new ByteArrayInputStream(byteArray); 
		
		//create buffered image and read from the input stream, surround with try catch to handle thrown exception
		try {
			BufferedImage bi2 = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		/*
		 * 
		 * converting colored images to grayscale
		 * Class imgprocess
		 * 
		 * cvtColor(source, destination, conversion_type) primarily does the color conversion. 
		 * 
		 * source represents the original matrix of original image
		 * destination represents a new empty matrix where it will be stored to 
		 * the conversion type is the image conversion code to alter it
		 * 
		 */
		
		Mat dst = new Mat(); 
		
		Imgproc.cvtColor(mat2, dst, Imgproc.COLOR_RGB2GRAY);    //converting image to grayscale
		
		
		
		/*
		 * 
		 * The most important in image pre-processing is 
		 * 
		 * converting to gray scale
		 * 
		 * unsharp marking
		 * 
		 * Binarization of the image
		 * 
		 * https://stackoverflow.com/questions/48170714/reading-text-from-image-using-tesseract-and-opencv-java
		 * 
		 */
		
		Mat originalImage = Imgcodecs.imread(originalImagePath); //original image stored. 
		
		Mat greyImage = null; 
		
		
		
		
		
		
		/*
		 * morphological transformations are operations on an image to reduce noise, process an image based on it's shapes i.e. morphology
		 * 
		 */
		
		
		
		
		
		
		
		
		
		/*draw a rectangle on an image using imgproc.rectangle(Mat, ); 
		 * 
		 * mat is image where rectangle is to be drawn
		 * pt 1, pt2 represent two objects, vertices of where the rectangle is to be drawn
		 * scalar object representing the color of the rectangle
		 * thickness, representing thickness of the rectangle border
		 * 
		 */
		
		Imgproc.rectangle(dst, new Point(200, 600),  new Point(1700, 2500), new Scalar(200,90,255), 3); //draws a rectangle at this location
		
		
		
		
		
		
		
		
		
		//this will write the img to a location
		Imgcodecs.imwrite("C:\\Users\\rnata\\Desktop\\bookcoverGray.png", dst);
				
	}

}
