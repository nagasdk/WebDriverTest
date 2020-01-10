package exceltest;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;






public class HighLightUI  {

	final JRadioButton rdbtnByID = new JRadioButton("By ID");
	final JRadioButton rdbtnByName = new JRadioButton("By Name");
	final JRadioButton rdbtnByXpath = new JRadioButton("By Xpath");
	final JRadioButton rdbtnByPartialText = new JRadioButton("By Partial LinkText");
	final JRadioButton rdbtnByLinkText = new JRadioButton("By LinkText");
	JList list_BrConfig = new JList();
	ButtonGroup bg = new ButtonGroup();

	WebDriver fd=null;
	private JTextField textField;
	private JTextField url;

	private JTextField Instance;
  
	final JButton btnPerform = new JButton("Perform");
	//private JTextField edit_Attribute;
	private JTextField edit_Attribute;
	private JTextField textField_1;
	JTextArea  lblAttribute;
	@SuppressWarnings("deprecation")
	public HighLightUI() {

		JFrame frmNextgen = new JFrame("JTabbedPane");
		frmNextgen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {

					fd.quit();


				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}


				//System.exit(JFrame.EXIT_ON_CLOSE);

			}
		});





		frmNextgen.setForeground(Color.WHITE);
		frmNextgen.setTitle("HighLight UI");


		final Container contentPane1 = frmNextgen.getContentPane();
		
		
	
		final Container contentPane = frmNextgen.getContentPane();
		JTabbedPane jtp = new JTabbedPane();
		contentPane.add(jtp, BorderLayout.CENTER);
		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setToolTipText("");


		desktopPane_2.setBackground(new Color(240, 248, 255));
		jtp.addTab("HighLight", null, desktopPane_2, null);
		
	

		list_BrConfig.setModel(new AbstractListModel() {
			String[] values = new String[] {"Microsoft IE", "Mozilla Firefox", "Google Chrome", "Apple Safari"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_BrConfig.setSelectedIndex(0);

		list_BrConfig.setForeground(Color.BLACK);
		list_BrConfig.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		list_BrConfig.setBackground(Color.WHITE);
		list_BrConfig.setBounds(10, 31, 139, 84);
		desktopPane_2.add(list_BrConfig);

		JLabel lblNewLabel_4 = new JLabel("Browser Config");
		lblNewLabel_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 11, 123, 14);
		desktopPane_2.add(lblNewLabel_4);

		JButton btnNewBrowser = new JButton("Launch Browser Instance");
		btnNewBrowser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch (list_BrConfig.getSelectedIndex()) {
				case 0:
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					System.setProperty("webdriver.ie.driver","src/IEDriverServer.exe");																 		 
					fd = new InternetExplorerDriver();	

					break;
				case 1:


					String filepath = "src/BrowserSettings.xml";


					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = null;
					String firefoxPath = "C:/Program Files (x86)/Mozilla Firefox/firefox.exe";
					try {
						docBuilder = docFactory.newDocumentBuilder();
						Document doc = docBuilder.parse(filepath);
						firefoxPath = doc.getElementsByTagName("FirefoxExePath").item(0).getTextContent();



					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


					System.setProperty("webdriver.firefox.bin", firefoxPath); 
					ProfilesIni allProfiles = new ProfilesIni();
					FirefoxProfile profile = allProfiles.getProfile("default");
					
					
					//fd = new FirefoxDriver(profile);
					fd = new FirefoxDriver();
					

					break;
					
				case 2:
					
					System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
					//DesiredCapabilities capabilities4= new DesiredCapabilities(); 
					//capabilities4.setCapability("chrome.binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-extensions");
					fd = new ChromeDriver(options);
					
				default:
					fd= new FirefoxDriver();
					break;
				}
				if (!url.getText().isEmpty()) {
					fd.get(url.getText());
				}
			}

		});
		btnNewBrowser.setBounds(157, 65, 185, 23);
		desktopPane_2.add(btnNewBrowser);

		JButton btnCloseBrowserInstance = new JButton("Close Browser Instance");
		btnCloseBrowserInstance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fd.quit();

			}
		});
		btnCloseBrowserInstance.setBounds(157, 88, 185, 23);
		desktopPane_2.add(btnCloseBrowserInstance);


		rdbtnByID.setBackground(new Color(240, 248, 255));
		rdbtnByID.setBounds(10, 122, 67, 23);
		desktopPane_2.add(rdbtnByID);
		
		bg.add(rdbtnByID);
		
		rdbtnByName.setBackground(new Color(240, 248, 255));
		rdbtnByName.setBounds(128, 122, 91, 23);
		desktopPane_2.add(rdbtnByName);
		bg.add(rdbtnByName);

		
		rdbtnByXpath.setBackground(new Color(240, 248, 255));
		rdbtnByXpath.setSelected(true);
		rdbtnByXpath.setBounds(231, 118, 123, 23);
		desktopPane_2.add(rdbtnByXpath);
		bg.add(rdbtnByXpath);
		textField = new JTextField();
		textField.setBounds(10, 177, 300, 29);
		desktopPane_2.add(textField);
		textField.setColumns(10);
		
		rdbtnByPartialText.setSelected(true);
		rdbtnByPartialText.setBackground(new Color(240, 248, 255));
		rdbtnByPartialText.setBounds(10, 148, 123, 23);
		desktopPane_2.add(rdbtnByPartialText);
		bg.add(rdbtnByPartialText);
		
		rdbtnByLinkText.setSelected(true);
		rdbtnByLinkText.setBackground(new Color(240, 248, 255));
		rdbtnByLinkText.setBounds(138, 148, 123, 23);
		desktopPane_2.add(rdbtnByLinkText);
		bg.add(rdbtnByLinkText);
		JButton btnHighlight = new JButton("HighLight");
		btnHighlight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				//JOptionPane.showMessageDialog(null,textField.getText());
				
				WebElement element = GetElement();
				for (int i = 0; i < 2; i++) {

					((JavascriptExecutor)fd).executeScript("arguments[0].style.border='3px solid red'", element);

					((JavascriptExecutor)fd).executeScript("arguments[0].style.border='none'", element); 

				}


			}
		});
		btnHighlight.setBounds(320, 177, 109, 29);
		desktopPane_2.add(btnHighlight);

		url = new JTextField();
		url.setColumns(10);
		url.setBounds(203, 31, 314, 23);
		desktopPane_2.add(url);

		JLabel lblUrl = new JLabel("URL");
		lblUrl.setForeground(new Color(0, 0, 128));
		lblUrl.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUrl.setBounds(161, 34, 58, 14);
		desktopPane_2.add(lblUrl);
		
		JButton btnSwitchBrowser = new JButton("Switch  Browser");
		btnSwitchBrowser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Instance.getText().isEmpty()){
					
					JOptionPane.showMessageDialog(null, "Please provide the browser instance number to switch the browser");
					return;
				}
				
				
				int valueSent = Integer.parseInt(Instance.getText());
				fd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				fd.switchTo().alert().accept();
				Set<String> Windowid = fd.getWindowHandles();
				String ReqWindow=null;
				Iterator<String> win=Windowid.iterator();
				for (int BrowserIndexx = 1;BrowserIndexx<=fd.getWindowHandles().size();BrowserIndexx++) {  
					if(valueSent>=BrowserIndexx){
						ReqWindow=win.next();
					}      
				}
				fd.switchTo().window(ReqWindow);
				

				
				
			}
		});
		btnSwitchBrowser.setBounds(379, 65, 133, 23);
		desktopPane_2.add(btnSwitchBrowser);
		
		

		
		Instance = new JTextField();
		Instance.setText("2");
		Instance.setBounds(352, 66, 25, 20);
		desktopPane_2.add(Instance);
		Instance.setColumns(10);
		
		JLabel lblOperationToPerform = new JLabel("Operation");
		lblOperationToPerform.setForeground(new Color(0, 0, 128));
		lblOperationToPerform.setFont(new Font("Verdana", Font.BOLD, 12));
		lblOperationToPerform.setBounds(10, 217, 153, 14);
		desktopPane_2.add(lblOperationToPerform);
		
		final JComboBox operationsToPerform = new JComboBox();
		operationsToPerform.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String operation = operationsToPerform.getSelectedItem().toString();
				
				edit_Attribute.setText("");
				
				if (operation.equals("SetTextOnEdit")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Text to set or keep blank to clear text");
				}
				if (operation.equals("SetTextOnEditEnter")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Text to set or keep blank to clear text");
				}
				else if  (operation.equals("ClickOnLink")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("(optional) link text  or leave blank");
				}
				else if (operation.equals("SelectItemInDropdown")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Item to select(Ex:#1 or itemText or PartialText.*");
					
				}
				else if (operation.equals("VerifyProperty")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("PropertyName;PropertyValue (Ex:Visible;True or Enable;True or text;xyz)");
				}
				else if (operation.equals("GetProperty")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Property/attribute to get the value");
				}
				else if (operation.equals("MouseOver")){
					edit_Attribute.setEnabled(false);
					lblAttribute.setText("Not Required");
				}
				else if (operation.equals("SetCheckBox")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("State to set (Ex: ON or OFF");
				}
				else if (operation.equals("Verifyitemnotindropdown")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Item to verify");
				}
				else if (operation.equals("VerifyNotVisible")){
					edit_Attribute.setEnabled(false);
					lblAttribute.setText("Not Required");
				}
				else if (operation.equals("MouseOverAndClickSubMenuLink")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Sub menu text to click");
				}
				else if (operation.equals("MouseOverAndValidateSubMenu")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("sum menus to verify, max 5 items seperated by ;");
				}
				else if (operation.equals("SelectRadiobutton")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Radio button value to select, refer the value attribute of your element");
				}
				else if (operation.equals("VerifyElementByXpath")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Xpath of the element");
				}
				else if (operation.equals("ClickOnLinkByXpath")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Xpath of the element");
				}
				else if (operation.equals("VerifyText")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Text to verify");
				}
				else if (operation.equals("VerifyLink")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Link text to verify");
				}
				else if (operation.equals("BackBrowser")){
					edit_Attribute.setEnabled(false);
					lblAttribute.setText("Not required");
				}
				else if (operation.equals("RefreshBrowser")){
					edit_Attribute.setEnabled(false);
					lblAttribute.setText("Not required");
				}
				else if (operation.equals("CopyURL")){
					edit_Attribute.setEnabled(false);
					lblAttribute.setText("Not required");
				}
				else if (operation.equals("SearchAString_URL")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("text to verify in URL");
				}
				else if (operation.equals("getUniqueID")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("type of the unique id( ex: OID or bankemaild or number");
				}
				else if (operation.equals("SearchAString_PageTitle")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("text to verify in page title");
				}
				else if (operation.equals("UploadFile")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Filename(place your file under src folder and give the file name) ; dialog box name ( ex: Open File, Choose file)");
				}
				else if (operation.equals("UploadFile1")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Xpath of the file choose button ;Filename( place your file under src folder and give the file name) ; dialog box name ( ex: Open File, Choose file)");
				}
				else if (operation.equals("DownloadFile")){
					edit_Attribute.setEnabled(false);
					
				}else if (operation.equals("SwitchWindow")){
					edit_Attribute.setEnabled(true);
					lblAttribute.setText("Enter the instance no of the Browser Window on which focus has to be taken");
					
				}
				
								
				
				
				
			}
		});
		operationsToPerform.setModel(new DefaultComboBoxModel(new String[] {"Select Operation", "SetTextOnEdit", "ClickOnLink", "SelectItemInDropdown", "VerifyProperty", "GetProperty", "MouseOver", "SetCheckBox", "Verifyitemnotindropdown", "VerifyNotVisible", "MouseOverAndClickSubMenuLink", "MouseOverAndValidateSubMenu", "SelectRadiobutton", "VerifyElementByXpath", "ClickOnLinkByXpath", "VerifyText", "VerifyLink", "BackBrowser", "RefreshBrowser", "CopyURL", "SearchAString_URL", "UploadFile", "UploadFile1", "DownloadFile", "getUniqueID", "SearchAString_PageTitle" , "SwitchWindow"}));
		operationsToPerform.setBounds(10, 250, 200, 20);
		desktopPane_2.add(operationsToPerform);
		btnPerform.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
   String operation = operationsToPerform.getSelectedItem().toString();
				
				
				
				 
				 
				 
				String edittext= edit_Attribute.getText();
				 if  (edit_Attribute.isEnabled() && (edit_Attribute.getText().toString().isEmpty())){
					 if (!operation.toLowerCase().startsWith("settextonedit") &&!operation.toLowerCase().startsWith("clickonlink")){
						JOptionPane.showMessageDialog(null, "Please provide " + lblAttribute.getText());
					 }
						
					 }
				
				
				 try{
				 
				 if (operation.equals("ClickOnLink")){
					 WebElement element = GetElement();
						element.click();
					}
				 
				 else if (operation.equals("GetProperty")){
					 String PropertyValue = "";
					 try{
					 List<WebElement>  elementList=fd.findElements((By.xpath(textField.getText())));     
					 
						if (elementList.size()>0 ){
							WebElement element = elementList.get(elementList.size()-1);
							if (element.isDisplayed()){
								if (edittext.equals("text"))
									PropertyValue=element.getText();
								else if(edittext.equals("visible"))
									PropertyValue="True";
								else if(edittext.equals("enable"))
									if (element.isEnabled()){
										PropertyValue="True";
									}
									else{
										PropertyValue="False";
									}
								else if(edittext.equals("selected"))
									if (element.isSelected()){
										PropertyValue="True";
									}
									else{
										PropertyValue="False";
									}
								
								else
									PropertyValue=element.getAttribute(edittext);     

								                 
							}
							else{
								
								
								
								JOptionPane.showMessageDialog(null, "The element is not found by the xpath provided");
								
							}

						}     //close of element.isDisplayed()
						else
						{
							if (edittext.equals("visible")){
								PropertyValue="False";
							    
							}
						}
					 }
					 catch(Exception ex){
						 
					 }
					 JOptionPane.showMessageDialog(null, PropertyValue);
					}
				else if  (operation.equals("SetTextOnEdit")){
					WebElement element = GetElement();
						if (edit_Attribute.getText().toString().isEmpty()){
							
							element.clear();
						}
						else
						{
							element.sendKeys(edit_Attribute.getText());
						}
					}
				 
				else if  (operation.equals("SetTextOnEditEnter")){
					WebElement element = GetElement();
						if (edit_Attribute.getText().toString().isEmpty()){
							
							element.clear();
						}
						else
						{
							Thread.sleep(5000);
							
							 Actions builder = new Actions(fd);
							 builder.moveToElement(element).click().build().perform();
							 builder.moveToElement(element).sendKeys(edit_Attribute.getText()).build().perform();
							/*element.sendKeys("");
							element.click();
							
							Robot robot = null;
							
							robot = new Robot();
						
						typeText(edit_Attribute.getText(),robot);*/
						}
					}
					
					
				else if (operation.equals("SelectItemInDropdown")){
					WebElement element = GetElement();
						String ItemToSelect = edit_Attribute.getText();
						boolean itemFound = false;
					Select select1 = new Select(element);
						if (ItemToSelect.endsWith(".*")){
							ItemToSelect=ItemToSelect.substring(0,ItemToSelect.lastIndexOf(".*"));

							for (int item = 1; item < select1.getOptions().size(); item++) {
								if (select1.getOptions().get(item).getText().toString().replaceAll("\u00A0", "").startsWith(ItemToSelect)){
									ItemToSelect=select1.getOptions().get(item).getText();

									select1.selectByIndex(item);
									itemFound=true;   
									break;
								}
								// End of itemFound1
							}
							if(!itemFound){              
							   JOptionPane.showMessageDialog(null, "The item: "+ItemToSelect + " is not found");
							}		
						}			
						else{
							if (ItemToSelect.charAt(0)=='#'){
								ItemToSelect=ItemToSelect.replaceFirst("#", "");
								select1.selectByIndex(Integer.parseInt(ItemToSelect));                               
								
							}
							else{
								try{
									select1.selectByVisibleText(ItemToSelect);                             
									
								}


								catch(Exception Ex){
									if(ItemToSelect.contains("\\u00A0")){
										ItemToSelect=ItemToSelect.replaceAll("\\u00A0", " ");
									}                        	
									for (int item = 1; item < select1.getOptions().size(); item++) {
										//if (select1.getOptions().get(item).getText().toString().replaceAll("\u00A0", "").startsWith(ItemToSelect)){                        	   
										String strAppValue=select1.getOptions().get(item).getText().toString().trim().toString();
										//strAppValue=strAppValue.replaceAll("\\u0020"," ");
										if ((strAppValue.replaceAll("\\u0020"," ")).equalsIgnoreCase(ItemToSelect.replaceAll("\\u00A0"," "))){
											ItemToSelect=select1.getOptions().get(item).getText();
											select1.selectByIndex(item);
											itemFound=true;   
											break;
										}
									} 	//End of For
									if(!itemFound){              
										 JOptionPane.showMessageDialog(null, "The item: "+ItemToSelect + " is not found");
									}

								}// End of Catch
							}// End of inner else
						}//End of ItemToSelect.charAt(1)=='#'
						
						
						
					}
					
					else if (operation.equals("VerifyProperty")){
						WebElement element = GetElement();
					   String[] arrdata = edit_Attribute.getText().split(";");
					    String propertyName =arrdata[0];
						String propertyValue = arrdata[1];


						WebElement objElement = element;

						if (propertyName.toLowerCase().equals("visible")) {
							if (propertyValue.toLowerCase().equals("true")) {
								
								if (objElement.isDisplayed()){
									JOptionPane.showMessageDialog(null, "Yes the element is visible");
								
								} 
								else{
									JOptionPane.showMessageDialog(null, "oops the element is not visible");
								}
								 
								}
								return;
							
						}
						else if (propertyName.toLowerCase().equals("enable")) {
							if (propertyValue.toLowerCase().equals("true")) {
								if (objElement.isEnabled()){;
								JOptionPane.showMessageDialog(null, "Yes the element is enable");
								} 
								else
								{
									JOptionPane.showMessageDialog(null, "oops the element is not enable");
								}
								{
								} 
							}
							else if (propertyValue.toLowerCase().equals("false")) {
								if (objElement.isEnabled()){;
								JOptionPane.showMessageDialog(null, "Yes the element is not enable");     

								} 
								else
								{
									JOptionPane.showMessageDialog(null, "oops the element is enable");
								}
								{
								} 
							}
						}

					
						else if (propertyName.toLowerCase().contains("text")) {
							String textFromAUT=objElement.getText();
							if(textFromAUT.contains(arrdata[1])){
								JOptionPane.showMessageDialog(null, "Yes the text is same as expected");
							}
							
							else{
								JOptionPane.showMessageDialog(null, "oops the text is not same as expected, the actual text is:"+textFromAUT);
							}
							
						}
						
						else  {
							
							String PropertyValue=objElement.getAttribute(arrdata[0]).trim();
							if( PropertyValue.equalsIgnoreCase(arrdata[1].trim())){
								JOptionPane.showMessageDialog(null, "Yes the property/attribute value is same as expected");
							}
							else{
								JOptionPane.showMessageDialog(null, "oops property/attribute value is not same as expected. Atual value is:"+PropertyValue);
							}
							
						}
					}
					else if (operation.equals("MouseOver")){
						WebElement element = GetElement();
					 Actions action = new Actions(fd);
					 action.moveToElement(element).build().perform();
						
					}
					else if (operation.startsWith("SetCheckBox")){
						WebElement element = GetElement();
					 if (element.isSelected() ) {
						 if (edittext.equals("OFF")) {
						 element.click();
						 }
						 } else {
						 if (!element.isSelected() ) {
						 if ( edittext.equals("ON") ) {
						   element.click();
						 }
						
					}
				 
						 }
				 }
				 
					else if (operation.equals("Verifyitemnotindropdown")){
						WebElement element = GetElement();
					 Boolean itemFound=false;
					 Select select1 = new Select(element);
					 for (int item = 1; item < select1.getOptions().size();item++){
					 if (select1.getOptions().get(item).getText().startsWith(edittext)){
					   JOptionPane.showMessageDialog(null, "oops the item is present");
					    itemFound=true;
					 break;
					 }
					 }
					 if(itemFound==false){
						 JOptionPane.showMessageDialog(null, "The item is not present");
					 }
					 else {
						 JOptionPane.showMessageDialog(null, "oops the item is present");
					 }
					 }
					 
					else if (operation.equals("VerifyNotVisible")){
						
						List<WebElement> elementlist =fd.findElements(By.xpath(textField.getText()));
						if (elementlist.size()>0 ){
						
							if (elementlist.get(0).isDisplayed()){
								JOptionPane.showMessageDialog(null, "oops the element is visible");
							}
							else{
								JOptionPane.showMessageDialog(null, "The element is not visible");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "The element is not visible");
						}
						
						
							
						}
					else if (operation.equals("MouseOverAndClickSubMenuLink")){
						WebElement element = GetElement();
						Actions action = new Actions(fd);
			
						action.moveToElement(element).build().perform();
					Thread.sleep(3000);
					String Submenu1 = edittext;
					if(Submenu1!=""){                     
							Submenu1=".//*[contains(text(),\""+Submenu1+ "\")]";
							WebElement objElement1=fd.findElement(By.xpath(Submenu1));
						
							if (objElement1.isDisplayed()){
								objElement1.click();
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "The submenu item is not found to click");
								}
							}
						}
							
					else if (operation.equals("MouseOverAndValidateSubMenu")){
						WebElement element = GetElement();
						edittext=edittext+";Skip;Skip;Skip;Skip;Skip";
						String[] arredittext = edittext.split(";");
						
						String Submenu1=arredittext[0];
						String Submenu2=arredittext[1];
						String Submenu3=arredittext[2];
						String Submenu4=arredittext[3];
						String Submenu5=arredittext[4];
						
						
						
						Actions action = new Actions(fd);
						WebElement ele1 = element;
						
						action.moveToElement(ele1).build().perform();

						Thread.sleep(3000);
						
						List<WebElement> eleList;
						if(Submenu1!=" "){                     
							Submenu1=".//*[contains(text(),'" + Submenu1 + "')]";
							eleList=fd.findElements(By.xpath(Submenu1));

							if (eleList.size()>0){

								JOptionPane.showMessageDialog(null, "The sub menu:"+Submenu1+ " is found");
							}

							else{
								JOptionPane.showMessageDialog(null, "oops the sub menu:"+Submenu1+ " is not found");
							}
						}                         

						if(!Submenu2.equals("Skip")){     
							Submenu2=".//*[contains(text(),'" + Submenu2 + "')]";
							eleList=fd.findElements(By.xpath(Submenu2));

							if (eleList.size()>0){

								JOptionPane.showMessageDialog(null, "The sub menu:"+Submenu2+ " is found");
							}

							else{
								JOptionPane.showMessageDialog(null, "oops the sub menu:"+Submenu2+ " is not found");
							}
						}     

						if(!Submenu3.equals("Skip")){     
							Submenu3=".//*[contains(text(),'" + Submenu3 + "')]";
							eleList=fd.findElements(By.xpath(Submenu3));

							if (eleList.size()>0){

								JOptionPane.showMessageDialog(null, "The sub menu:"+Submenu3+ " is found");
							}

							else{
								JOptionPane.showMessageDialog(null, "oops the sub menu:"+Submenu3+ " is not found");
							}
						}     

						if(!Submenu4.equals("Skip")){                     
							Submenu4=".//*[contains(text(),'" + Submenu4 + "')]";
							eleList=fd.findElements(By.xpath(Submenu4));

							if (eleList.size()>0){

								JOptionPane.showMessageDialog(null, "The sub menu:"+Submenu4+ " is found");
							}

							else{
								JOptionPane.showMessageDialog(null, "oops the sub menu:"+Submenu4+ " is not found");
							}
						}     
						if(!Submenu3.equals("Skip")){                     
							Submenu5=".//*[contains(text(),'" + Submenu5 + "')]";
							eleList=fd.findElements(By.xpath(Submenu5));

							if (eleList.size()>0){

								JOptionPane.showMessageDialog(null, "The sub menu:"+Submenu5+ " is found");
							}

							else{
								JOptionPane.showMessageDialog(null, "oops the sub menu:"+Submenu5+ " is not found");
							}
							
						}
					
					}
					else if (operation.equals("SelectRadiobutton")){
						Boolean itemFound=false;
						List<WebElement> elementlist =fd.findElements(By.xpath(textField.getText()));
						if (elementlist.size()>0){
						for (int i = 0;i<=elementlist.size()-1; i++) {
						 if (elementlist.get(i).getAttribute("value").equalsIgnoreCase("")){
						elementlist.get(i).click();
						 itemFound=true;
						 }
						}
						}
					}
					else if (operation.equals("VerifyElementByXpath")){
						int index=0;
						String strindex = "";
						String childObject=edit_Attribute.getText();
						if (childObject.lastIndexOf("][")!=-1){
						strindex=childObject.substring(childObject.lastIndexOf("][")+2,childObject.lastIndexOf("]"));
						if (!strindex.isEmpty()){
						try {
						index=Integer.parseInt(strindex);
						childObject=childObject.replace("["+strindex+"]", "");
						} catch (Exception e) {
						}
						}
						}
						List<WebElement> elementlist =fd.findElements(By.xpath( childObject ));
						if (elementlist.size()>0 ){
						WebElement LinkElement=elementlist.get(index);
						if(LinkElement.isDisplayed()){
						  JOptionPane.showMessageDialog(null, "The element is present");
						           }
						       else {
						    	   JOptionPane.showMessageDialog(null, "oops the element is not present");
						           }
						           }
						       else {
						    	   JOptionPane.showMessageDialog(null, "oops the element is not present");
						           }
						   }
							
					else if (operation.equals("ClickOnLinkByXpath")){
						int index=0;
						String strindex = "";
						String childObject=edit_Attribute.getText();
						if (childObject.lastIndexOf("][")!=-1){
						strindex=childObject.substring(childObject.lastIndexOf("][")+2,childObject.lastIndexOf("]"));
						if (!strindex.isEmpty()){
						try {
						index=Integer.parseInt(strindex);
						childObject=childObject.replace("["+strindex+"]", "");
						} catch (Exception e) {
						}
						}
						}
						List<WebElement> elementlist =fd.findElements(By.xpath( childObject ));
						if (elementlist.size()>0 ){
						WebElement LinkElement=elementlist.get(index);
						if(LinkElement.isDisplayed()){
						    LinkElement.click();
						           }
						       else {
						    	   JOptionPane.showMessageDialog(null, "oops the element is not present");
						           }
						           }
						       else {
						    	   JOptionPane.showMessageDialog(null, "oops the element is not present");
						           }
							
						}
					else if (operation.equals("VerifyText")){
						List<WebElement> elementlist =fd.findElements(By.xpath("//*[contains(text(),'"+edit_Attribute.getText()+"')]"));
						if (elementlist.size()>0 ){
							 JOptionPane.showMessageDialog(null, "The text is present");
						           }
						       else {
						    	   JOptionPane.showMessageDialog(null, "oops the text is not present");
						}
							
						}
					else if (operation.startsWith("VerifyLink")){
						List<WebElement> elementlist =fd.findElements(By.partialLinkText(edit_Attribute.getText()));
						if (elementlist.size()>0 ){
							 JOptionPane.showMessageDialog(null, "The link is present");
						           }
						       else {
						    	   JOptionPane.showMessageDialog(null, "oops the link is not present");
						}
							
						}
				 
				 else if (operation.equals("BackBrowser")){
					 fd.navigate().back();
				 }
				 else if (operation.equals("RefreshBrowser")){
					 fd.navigate().refresh();
				 }
				 else if (operation.equals("CopyURL")){
					 JOptionPane.showMessageDialog(null, fd.getCurrentUrl());
				 }
				 else if (operation.equals("SearchAString_URL")){
					 String sURL=fd.getCurrentUrl();
						if (sURL.contains(edittext)){                              
							 JOptionPane.showMessageDialog(null,"The string/text is present in the url");
						}
						else{
							JOptionPane.showMessageDialog(null,"oops the string/text is present in the url");
						}  
				 }
				 else if (operation.equals("getUniqueID")){
					 String str = "";
						Calendar localCalendar = GregorianCalendar.getInstance();
						if (edittext.equalsIgnoreCase("OID")) {
							str = "OID_" + localCalendar.get(11) + localCalendar.get(12) + localCalendar.get(13) + localCalendar.get(14);
						} else if (edittext.equalsIgnoreCase("BANKEMAILID")) {
							str = "bac" + localCalendar.get(11) + localCalendar.get(12) + localCalendar.get(13) + "@bac.com";
						} else if (edittext.equalsIgnoreCase("NUMBER")) {
							str = Integer.toString(localCalendar.get(11)) + localCalendar.get(12) + localCalendar.get(13) + localCalendar.get(14);
						}
						JOptionPane.showMessageDialog(null, str);
				 }
				 else if (operation.equals("SearchAString_PageTitle")){
					 String sTitle=fd.getTitle();
						if (sTitle.contains(edittext)){                            
							JOptionPane.showMessageDialog(null,"The string/text is present in the page title");
						}
						else{
							JOptionPane.showMessageDialog(null,"oops the string/text is present in the page title");
						}   
				 }
				 
				 else if (operation.equals("UploadFile1")){
					 WebElement element = GetElement();
					 String[] arredittext = edittext.split(";");
					 String strFileName = arredittext[0];
					 String DialogName = arredittext[1];
					 if(fd.toString().contains("Firefox")==true){
							WebElement elementList=element;


							File file= new File("");

							final File folder = new File(file.getCanonicalPath()+"/src/");

							for (final File fileEntry : folder.listFiles()) {
								if (fileEntry.isFile()) {

									if(fileEntry.getName().contains(strFileName)){
										String Filename=fileEntry.getName();

										String Filename1="\\";
										String FilePath=Filename1+Filename;

										elementList.sendKeys(folder+FilePath);
									}   
								}
							}
						}



						else if(fd.toString().contains("explorer")==true){

							File file = new File("");

							final File folder = new File(file.getCanonicalPath() + "/src/");

							for (final File fileEntry : folder.listFiles()) {
								if (fileEntry.isFile()) {

									if (fileEntry.getName().contains(strFileName)) {
										String Filename = fileEntry.getName();

										String Filename1 = "\\";
										String FilePath = Filename1+Filename;
										String s0=";";
										String s1=DialogName;
										String s3=s1+s0;
										Runtime.getRuntime().exec(file.getCanonicalPath()+"/src/UploadAttachments.exe "+s3+folder+FilePath);

										WebElement elementlist=element;

										JavascriptExecutor js = (JavascriptExecutor)fd;
										js.executeScript("arguments[0].click();", elementlist);
										// String submenu1="Lighthouse.jpg";

									}
								}
							}
				 }
				 }
				 else if (operation.equals("UploadFile")){
					 String[] arredittext = edittext.split(";");
					 String strKRPath=arredittext[0];
					  String strFileName=arredittext[1];
					  String DialogName=arredittext[2];
					 File file = new File("");

						final File folder = new File(file.getCanonicalPath() + "/src/");

						for (final File fileEntry : folder.listFiles()) {
							if (fileEntry.isFile()) {

								if (fileEntry.getName().contains(strFileName)) {
									String Filename = fileEntry.getName();

									String Filename1 = "\\";
									String FilePath = Filename1+Filename;
									String s0=";";
									String s1=DialogName;
									String s3=s1+s0;
									Runtime.getRuntime().exec(file.getCanonicalPath()+"/src/UploadAttachments.exe "+s3+folder+FilePath);
								}
							}
						}
				 }
				 else if (operation.equals("DownloadFile")){
					 File file = new File("");

						File folder = new File(file.getCanonicalPath() + "/src/");

						Runtime.getRuntime().exec(folder + "\\downloadAttachments.exe");
				 }
				 else if (operation.equals("SwitchWindow")){
					 
						int valueSent = Integer.parseInt(edittext);
						Set<String> Windowid = fd.getWindowHandles();
					
						String ReqWindow=null;
						Iterator<String> win=Windowid.iterator();
						for (int BrowserIndexx = 1;BrowserIndexx<=fd.getWindowHandles().size();BrowserIndexx++) {  
							if(valueSent>=BrowserIndexx){
								ReqWindow=win.next();
							}      
						}
						fd.switchTo().window(ReqWindow);
					 
					 
				 }
				 
				 }				 
				 catch( Exception ex){
					 JOptionPane.showMessageDialog(null, "Error occured :"+ ex.getMessage());
				 }
				 
		
				
			}
		});
		
		
		btnPerform.setBounds(416, 249, 109, 23);
		desktopPane_2.add(btnPerform);
		
		
		
		
		
		
		 lblAttribute = new JTextArea ("Text/Attribute");
		 lblAttribute.setLineWrap(true);
		 lblAttribute.setBackground(new Color(240, 255, 255));
		lblAttribute.setForeground(new Color(0, 0, 128));
		lblAttribute.setFont(new Font("Verdana", Font.BOLD, 10));
		lblAttribute.setBounds(211, 205, 314, 40);
		desktopPane_2.add(lblAttribute);
		Instance.setColumns(10);
		
	
		
		edit_Attribute = new JTextField();
		edit_Attribute.setColumns(10);
		edit_Attribute.setBounds(211, 251, 205, 20);
		edit_Attribute.setEnabled(true);
		desktopPane_2.add(edit_Attribute);
		
		JDesktopPane desktopPane = new JDesktopPane();
		jtp.addTab("Test Your Code", null, desktopPane, null);
		desktopPane.setToolTipText("");
		desktopPane.setBackground(new Color(240, 248, 255));
		
		JLabel lblEnterOrUpdate = new JLabel("Enter or Update your code to test");
		lblEnterOrUpdate.setForeground(new Color(0, 0, 128));
		lblEnterOrUpdate.setFont(new Font("Verdana", Font.BOLD, 12));
		lblEnterOrUpdate.setBounds(10, 11, 256, 14);
		desktopPane.add(lblEnterOrUpdate);
        

		frmNextgen.setSize(562, 359);




		frmNextgen.show();

	}

	public WebElement GetElement() {
		fd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement element =null;
		if (rdbtnByID.isSelected()){
			try{
				element = fd.findElement(By.id(textField.getText()));
			}
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "The Element is not found");
				return null;
			}



		}
		if (rdbtnByName.isSelected()){
			try{
				element = fd.findElement(By.name(textField.getText()));

			}
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "The Element is not found");
				return null;
			}


		}

		if (rdbtnByXpath.isSelected()){
			try{
				if (textField.getText().contains("][")){
					Integer index = Integer.parseInt(textField.getText().substring(textField.getText().lastIndexOf("][")+2,textField.getText().lastIndexOf("]")));
					String Xpath = textField.getText().substring(1,textField.getText().lastIndexOf("][")+1);
					List<WebElement> element1 = fd.findElements(By.xpath(Xpath));
					element=element1.get(index);
				}
				else
				{
					
					element = fd.findElement(By.xpath(textField.getText()));
					//element.click();

					
					
					//WebElement parent = fd.findElement(By.xpath(textField.getText() + "/../.."));
					/*JOptionPane.showMessageDialog(null,"The element is :"+element.getText());
	      					WebElement element1 = element;
	      					String xpath = textField.getText();

	      					while (!element1.getTagName().equalsIgnoreCase("a")){
	      						if(element1.getTagName().equalsIgnoreCase("html")){
	      							break;
	      						}
	      						element1 = fd.findElement(By.xpath(xpath + "/.."));
		      					System.out.println(element1.getTagName());
		      					xpath=xpath+"/..";
	      					}
	      					if(element1.getTagName().toLowerCase()!="a"){
	      						element=element1;

	      					}*/

				}
			}
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "The Element is not found");
				return null;
			}



		}
		if (rdbtnByLinkText.isSelected()){
			try{
				element = fd.findElement(By.linkText(textField.getText()));
			}
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "The Element is not found");
				return null;
			}



		}

		if (rdbtnByPartialText.isSelected()){
			try{
				element = fd.findElement(By.partialLinkText(textField.getText()));

			}
			catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "The Element is not found");
				return null;
			}



		}
		return element;
	}


	public static void main(String args[]) {


		new HighLightUI(); 


	}
	
	
	 public void keyType(int keyCode,Robot robot) {
	 robot.keyPress(keyCode);
		 robot.delay(50);
		 robot.keyRelease(keyCode);
		 }
		 
		 public void keyType(int keyCode, int keyCodeModifier,Robot robot) {
			 robot.keyPress(keyCodeModifier);
			 robot.keyPress(keyCode);
			 robot.delay(50);
			 robot.keyRelease(keyCode);
			 robot.keyRelease(keyCodeModifier);
		 }

		 
		 public void typeText(String text,Robot robot) {
		  String textUpper = text.toUpperCase();

		  for (int i=0; i<text.length(); ++i) {
		   typeChar(textUpper.charAt(i),robot);
		  }  
		 }
		 
		 private void typeChar(char c,Robot robot) {
		  boolean shift = true;
		  int keyCode;
		  
		  switch (c) {
		  case '~':
		   keyCode = (int)'`';
		   break;
		  case '!':
		   keyCode = (int)'1';
		   break;
		  case '@':
		   keyCode = (int)'2';
		   break;
		  case '#':
		   keyCode = (int)'3';
		   break;
		  case '$':
		   keyCode = (int)'4';
		   break;
		  case '%':
		   keyCode = (int)'5';
		   break;
		  case '^':
		   keyCode = (int)'6';
		   break;
		  case '&':
		   keyCode = (int)'7';
		   break;
		  case '*':
		   keyCode = (int)'8';
		   break;
		  case '(':
		   keyCode = (int)'9';
		   break;
		  case ')':
		   keyCode = (int)'0';
		   break;
		  case ':':
		   keyCode = (int)';';
		   break;
		  case '_':
		   keyCode = (int)'-';
		   break;
		  case '+':
		   keyCode = (int)'=';
		   break;
		  case '|':
		   keyCode = (int)'\\';
		   break;
		//  case '"':

		//   keyCode = (int)'\'';

		//   break;

		  case '?':
		   keyCode = (int)'/';
		   break;
		  case '{':
		   keyCode = (int)'[';
		   break;
		  case '}':
		   keyCode = (int)']';
		   break;
		  case '<':
		   keyCode = (int)',';
		   break;
		  case '>':
		   keyCode = (int)'.';
		   break;
		  default:
		   keyCode = (int)c;
		   shift = false;
		  }
		  if (shift)
			   keyType(keyCode, KeyEvent.VK_SHIFT,robot);
			  else
			   keyType(keyCode,robot);
			 }
		 
		 private int charToKeyCode(char c) {
		  switch (c) {
		  case ':':
		   return ';';
		  }
		  return (int)c;
		 }
		
}






