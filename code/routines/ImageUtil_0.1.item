package routines;

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
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
public class ImageUtil {
	public static void ByteArrayToFile(byte[] input, String filepath) {
		  try 
		       {   
		            java.io.File fp = new java.io.File(filepath);
		            java.io.FileOutputStream fos = new java.io.FileOutputStream(fp);
		            fos.write(input);
		            fos.close();
		       }
		       catch(Exception err)
		       {
		           err.printStackTrace();
		           
		       }
		}
}