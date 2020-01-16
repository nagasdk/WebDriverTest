import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFromExcel {

	private static String TestID="";
	private static String sheetName="Nov10";
	private static String parameterName="Unique Identifier";
	private static String path="src/main/resources/TestData.xls";


	public static void main(String[] args)  {
		Connection con  ; 
		ResultSet rs;
		java.sql.Statement st;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");   
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=" +path + ";READONLY=true;IMEX=1;" );   
			st = con.createStatement();    
			rs = st.executeQuery( "Select "+ parameterName +" from [" +sheetName +"$] Where [TestID]='" + TestID + "'");      

			//ResultSetMetaData rsmd = rs.getMetaData();  
			String dataFetched = "-1";
			while (rs.next()) {
				
			}
			
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}

}
