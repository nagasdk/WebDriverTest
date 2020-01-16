import java.io.*;
import java.util.*;

public class ReadPropertiesFileTest {
   public static void main(String args[]) throws IOException {
      Properties prop = readPropertiesFile("src/main/resources/abc.properties");
      System.out.println("username: "+ prop.getProperty("name"));
      System.out.println("password: "+ prop.getProperty("job"));
   }
   public static Properties readPropertiesFile(String fileName) throws IOException {
      FileInputStream fis = null;
      Properties prop = null;
      try {
         fis = new FileInputStream(fileName);
         prop = new Properties();
         prop.load(fis);
      } catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      } catch(IOException ioe) {
         ioe.printStackTrace();
      } finally {
         fis.close();
      }
      return prop;
   }
}