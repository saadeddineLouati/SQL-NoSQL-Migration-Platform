package routines;
import java.awt.image.BufferedImage; 
import java.io.File;  
import java.io.IOException;  
import java.util.Scanner;  
import javax.imageio.ImageIO;

import com.googlecode.javacv.FFmpegFrameGrabber;
import com.googlecode.javacv.Frame;
import com.googlecode.javacv.cpp.opencv_core.IplImage; 
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
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
public class blobToString {

    /**
     * helloExample: not return value, only print "hello" + message.
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} helloExemple("world") # hello world !.
     * @throws SQLException 
     * @throws IOException 
     */
   /* public static void helloExample(String message) {
        if (message == null) {
            message = "World"; //$NON-NLS-1$
        }
        System.out.println("Hello " + message + " !"); //$NON-NLS-1$ //$NON-NLS-2$
    }*/
    
    public static byte[] extractBlobFromMySql(Object obj, String name) throws SQLException, IOException  
    {
    	  String url = "jdbc:mysql://localhost:3306/northwind?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    	   String username = "root";
    	   String password = "";
    		  Connection conn = null;
    		   conn = DriverManager.getConnection(url, username, password);
     	      byte[] buffer = new byte[2];
    	    String sql = "SELECT picture FROM `categories` WHERE categoryId="+name+"";
    	    PreparedStatement stmt = conn.prepareStatement(sql);
    	    ResultSet resultSet = stmt.executeQuery();
    	    while (resultSet.next()) {
    	      File image = new File("C:\\tmp\\"+name+".jpg");
    	      FileOutputStream fos = new FileOutputStream(image);

    	      InputStream is = resultSet.getBinaryStream(1);
    	      while (is.read(buffer) > 0) {
    	        fos.write(buffer);
    	      }
    	      fos.close();
    	    }
    	    conn.close();
			return buffer;
    	  }
    
    public static Object addToGridFs(String filePath,String fileName)
    {
		  System.out.println("Calling upload...");
		  MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		  DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		  ObjectId fileId = null;

		  try {
			  System.out.println("in the try/catch");
		   MongoDatabase database = mongoClient.getDatabase("gridfs");
		   GridFSBucket gridBucket = GridFSBuckets.create(database);
		   InputStream inputStream = new FileInputStream(new File(filePath));
		   // Create some custom options
		   GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024).metadata(new Document("type", "image").append("upload_date", format.parse("2016-09-01T00:00:00Z")).append("content_type", "image/jpg"));
		   fileId = gridBucket.uploadFromStream(fileName, inputStream, uploadOptions);
		 
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		   mongoClient.close();
		  }
		 
		  return fileId;
    }
}