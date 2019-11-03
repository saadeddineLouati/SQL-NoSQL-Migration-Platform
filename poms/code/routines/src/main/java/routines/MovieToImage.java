package routines;
import java.awt.image.BufferedImage;


 
import java.io.File;  
import java.io.IOException;  
import java.util.*;  
import javax.imageio.ImageIO;

import com.googlecode.javacv.FFmpegFrameGrabber;
import com.googlecode.javacv.Frame;
import com.googlecode.javacv.cpp.opencv_core.IplImage; 
/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class MovieToImage {

    public ArrayList<Occurence> occList(ArrayList<ImageSimilarityDegree> res) {
    	boolean bool=false;
		  int i=0;
		  routines.Occurence occ=new routines.Occurence();
		  java.util.ArrayList<routines.Occurence> listOfOccurrence=new java.util.ArrayList<>();
		  	while(i<res.size())
		  	{
			  if(res.get(i).getDegree()<=10 && bool==false)
			  {
				  bool=true;
				  occ.setBeginInstant(Integer.toString(((i)*(res.size()*2/30))/(res.size()*2)));
				  i++;				  
			  }
			  else if(bool==true && res.get(i).getDegree()>10)
			  {
				occ.setEndInstant(Integer.toString(((i-1)*(res.size()*2/30))/(res.size()*2))); 
				bool=false;
				listOfOccurrence.add(occ);
				i++;
			  }
			  else
			  {
				  i++;
			  }
		  	}
		  	
		  	if(listOfOccurrence.size()>0)
		  	{
			  	System.out.println("*************************** Matching found ***************************");
		  		for( i=0;i<listOfOccurrence.size();i++)
			  	{
			  		System.out.println("*************************** From: "+listOfOccurrence.get(i).getBeginInstant()+"s To: "+listOfOccurrence.get(i).getEndInstant()+"s **************************");
			  	}
			  	System.out.println("**********************************************************************");

		  	}
		  	else{
			  	System.out.println("*************************** Matching found ***************************");
		  		System.out.println("No matching found...");
			  	System.out.println("**********************************************************************");

		  	}

		  	
		  	return listOfOccurrence;
    }
    
    
    
    public static ArrayList<ImageSimilarityDegree> figureOut(String mp4Path,String imagePath, String imageToCampre)  throws Exception
    {
        ArrayList<ImageSimilarityDegree> res=new ArrayList<>();
    	MovieToImage m2i=new MovieToImage();
        ArrayList<String> results=new ArrayList<>();

        BufferedImage img1 = ImageIO.read(new File(imageToCampre));
        results=m2i.convertMovietoJPG(mp4Path, imagePath,"jpg",0); 
        for(int i=0;i<results.size();i++)
        {
            ImageSimilarityDegree isd=new ImageSimilarityDegree();
            BufferedImage img2 = ImageIO.read(new File(results.get(i)));
            double p= m2i.getDifferencePercent(img1, img2);
            isd.setInstant(Integer.toString(i));
            isd.setDegree(p);
            res.add(isd);

        }
        return res;
		
    }
    
    
    
    public ArrayList<String> convertMovietoJPG(String mp4Path, String imagePath, String imgType, int frameJump) throws Exception, IOException  
    {  
  	 ArrayList<String> results=new ArrayList<>();
  	 FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(mp4Path);  
       frameGrabber.start();  
       double frameRate=frameGrabber.getFrameRate();  
       System.out.println("By SL and OK: Video has "+frameGrabber.getLengthInFrames()+" frames and has frame rate of "+frameRate);  
       int nbf=frameGrabber.getLengthInFrames();
       try {            
    	   		for (int ii = 0 ; ii < nbf; ii=ii+5) 
    	   		{
    	   			 String path = imagePath+File.separator+ii+".jpg";
		      		 results.add(path);
		             BufferedImage  bi = frameGrabber.grab().getBufferedImage();
		      		 ImageIO.write(bi, imgType, new File(path));
    	   		}
    	   		frameGrabber.stop();
    	   		frameGrabber.stop();  
       } catch (Exception e) 
       {  
         e.printStackTrace();  
       }
		return results; 
     } 
    private double getDifferencePercent(BufferedImage img1, BufferedImage img2) {
  	  MovieToImage m2i=new MovieToImage();
  	  int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        if (width != width2 || height != height2) {
            throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
        }

        long diff = 0;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                diff += m2i.pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        long maxDiff = 3L * 255 * width * height;

        return 100.0 * diff / maxDiff;
    }

    private int pixelDiff(int rgb1, int rgb2)
    {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }
}
