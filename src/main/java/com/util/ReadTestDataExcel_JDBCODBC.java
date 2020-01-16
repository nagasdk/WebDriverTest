package com.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ReadTestDataExcel_JDBCODBC {

	
	Connection con  ; 
	ResultSet rs;
	java.sql.Statement st;

	
	public String ReadTestdataFromExcel( String path,String strParameter,String TestID,Integer Instance )  {
		try{
			File f = new File(path);
			if(!f.exists()){								
				System.out.println("Error Running scripts The Test data path is wrong. path given is:"+ path );
				return "-1";
			}
			if (strParameter.toLowerCase().contains("param_")) {
				strParameter=strParameter.replace(strParameter.substring(0,6), "");
			}
			int position = strParameter.indexOf("_");
			String sheetName = strParameter.substring(0, position);
			String parameterName = strParameter.substring(position+1);	 
			if (Instance>1) {
				TestID=TestID +"_"+Instance;
			}
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");   
			 con = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=" +path + ";READONLY=true;IMEX=1;" );   
			 st = con.createStatement();    
			 rs = st.executeQuery( "Select "+ parameterName +" from [" +sheetName +"$] Where [TestID]='" + TestID + "'");      
			 String dataFetched = "-1";
			 while (rs.next()){
				try{
					dataFetched = rs.getString(parameterName);
					if (dataFetched==null){
						dataFetched="";
					}
				}
				catch(Exception ex){					
				}
				/*if(dataFetched.toLowerCase().startsWith("database_")){
					String strNewKeywordRepAttachPath ="";
					strNewKeywordRepAttachPath = downloadProdcertTestData(AutoGenMainCls.FrameworkPath+"/TestData/");					
					if(strNewKeywordRepAttachPath.equals("Failed")){
						System.out.println(strNewKeywordRepAttachPath);						
						return "-1";
					}else{
					
						String[] parms = {"wscript", "src/QueryAccessDatabase.vbs", strNewKeywordRepAttachPath, dataFetched};						
						Process p = Runtime.getRuntime().exec(parms);
						String testdata = null;
						try {
							InputStream stdin = p.getInputStream();
					        InputStreamReader isr = new InputStreamReader(stdin);
					        BufferedReader br = new BufferedReader(isr);
					        String data;
							while ( (data = br.readLine()) != null){
					        	testdata=data;
							}
							p.waitFor();            					       
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						try { if (rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();};
						try { if (st != null) st.close(); } catch (Exception e) {e.printStackTrace();};
						try { if (con != null) con.close(); } catch (Exception e) {e.printStackTrace();};
						return testdata;
					}
				}
				
				
				*/
			}


			try { if (rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();};
			try { if (st != null) st.close(); } catch (Exception e) {e.printStackTrace();};
			try { if (con != null) con.close(); } catch (Exception e) {e.printStackTrace();};
			return dataFetched;


		}  
		catch(Exception ex) { 
			ex.printStackTrace();
			//System.out.println("Error Running scripts , issue reading The Test Data , path given is:"+ path + "Parameter is:"+strParameter + " , TestID is: "+ TestID +" , Instance is:"+ Instance);
			try { if (rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();};
			try { if (st != null) st.close(); } catch (Exception e) {e.printStackTrace();};
			try { if (con != null) con.close(); } catch (Exception e) {e.printStackTrace();};

		}
		return "";

	}


	public static void main(String args[]){
		
		ReadTestDataExcel_JDBCODBC rtde = new ReadTestDataExcel_JDBCODBC();
		String xlPath="D:\\eclipse-jee-oxygen\\workspaces1\\Gherkin\\src\\main\\resources\\TestData.xlsx.xls";
		System.out.println(rtde.ReadTestdataFromExcel(xlPath, "Students_username", "Demo1", 1));
		
	}
	
}
