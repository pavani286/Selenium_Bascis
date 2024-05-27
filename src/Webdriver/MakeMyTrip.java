package Webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import DataUtil.dataprovider;
import DataUtil.properties;


public class MakeMyTrip extends properties{

	public static WebDriver driver;
	static Date d1,d2,d3,d4;
	public String DepartureDt,ReturnDt,Dept,Ret;
	static List<WebElement> DepFilghtCount,ArrFilghtCount;
    public static String Departure_Fare,Dept_Fare;
	public static String Return_Fare;
	@BeforeTest
	public void intialization() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/Users/pavanivemula/Documents/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		Thread.sleep(2000);
	        }
	@Test(priority =1)
	public static void Flight() throws InterruptedException {
		driver.findElement(By.xpath("//ul[@class='fswTabs latoBlack greyText']/li[2]")).click();
    	driver.findElement(By.xpath("//div[@class='fsw_inputBox searchCity inactiveWidget ']")).click();
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("DEL");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()='DEL']")).click();
    	
    	driver.findElement(By.cssSelector("div.fsw_inputBox.searchToCity.inactiveWidget ")).click(); 
    	driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("Bengaluru");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[text()='BLR']")).click(); 
	}
	@Test(priority =2,dependsOnMethods="Flight")
	public void DataPicker() throws ParseException, InterruptedException {
		init_prop();
    	SimpleDateFormat FormatDate = new SimpleDateFormat("dd MMM");
    	 DepartureDt =prop.getProperty("DepartureDate");
    	 ReturnDt =prop.getProperty("ReturnDate");
    	 d1 = FormatDate.parse(DepartureDt);
    	 d2 = FormatDate.parse(ReturnDt);
    	System.out.println("The departure date is ... " + FormatDate.format(d1));
        System.out.println("The Return date is ... " + FormatDate.format(d2));
        
        SimpleDateFormat FormatDate1 = new SimpleDateFormat("MMM dd");
        Dept = prop.getProperty("DeptDt");
        Ret = prop.getProperty("RetDt");
        d3 = FormatDate1.parse(Dept);
        d4 = FormatDate1.parse(Ret);
        System.out.println("The departure date 1 is ... " + FormatDate1.format(d3));
        System.out.println("The Return date 1 is ... " + FormatDate1.format(d4));
    	if(Differences(d3,d4)>=7) {
    	driver.findElement(By.xpath("//div[contains(@aria-label,'"+Dept+"')]")).click();
    	driver.findElement(By.xpath("//div[contains(@aria-label,'"+Ret+"')]")).click();

        }else {
          System.out.println("This Method is restericated to accept 7 days difference between"
                             + " departure and Arrival date. Please check Given Date again. "
                              + "!!Contact Git Admin for more info");

        }

    	
	}
    	
	@Test(priority =3,dependsOnMethods="DataPicker")
	public static void DepartureFlightInfo() throws InterruptedException {
		driver.findElement(By.cssSelector("a.primaryBtn.font24.latoBold.widgetSearchBtn")).click();
    	driver.manage().deleteAllCookies();
    	Thread.sleep(6000);
    	
    	ScrollDown();
	    DepFilghtCount = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label"));
        System.out.println("No.of Departure Flights avialable ..."+DepFilghtCount.size());
        
	}
	
	@Test(priority =4,dependsOnMethods="DepartureFlightInfo")
	public void ReturnFilghtInfo() {
		 ArrFilghtCount = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label"));
	     System.out.println("No.of Return Flights avialable ..."+ArrFilghtCount.size());
	           
	}
	//@Test(priority = 5,dependsOnMethods="ReturnFilghtInfo")
	public static void One_Stop() throws InterruptedException{
		//1-stop
		ScrollUp();
        System.out.println("********  One-Stop option is selected  *********");
        driver.findElement(By.xpath("//p[contains(text(),'New Delhi')]/parent::div/child::div//span[text()='1 Stop']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//p[contains(text(),'Bengaluru')]/parent::div/child::div//span[text()='1 Stop']")).click();
        ScrollDown();
        DepFilghtCount = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label"));
        System.out.println("No.of Departure Flights avialable ....."+ DepFilghtCount.size());
        
	    ArrFilghtCount  = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label"));
        System.out.println("No.of Return Flights avialable ....."+ ArrFilghtCount.size());
        
	                }
	
	public void Non_Stop() throws InterruptedException {
		System.out.println("********** Non-Stop option is selected **********");
        //non-stop
		ScrollUp();
        driver.findElement(By.xpath("//p[contains(text(),'New Delhi')]/parent::div/child::div//span[text()='Non Stop']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//p[contains(text(),'Bengaluru')]/parent::div/child::div//span[text()='Non Stop']")).click();
        
        ScrollDown();
        DepFilghtCount = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label"));
        System.out.println("No.of Departure Flights avialable ....."+ DepFilghtCount.size());
        
	    ArrFilghtCount  = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label"));
        System.out.println("No.of Return Flights avialable ....."+ ArrFilghtCount.size());
      
	          }
   @Test(priority=5,dependsOnMethods ="ReturnFilghtInfo", dataProviderClass = dataprovider.class, dataProvider = "getData")
	
   public static void FarePriceCheck(int depaInx, int ArrivalIdx) throws InterruptedException {
	   ScrollUp();
	   SelectDepartureFlight(depaInx);
	                         
		
       List<WebElement> Price_Left = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[1]/child::div[2]/div/div/label/descendant::div[@class='makeFlex column relative splitfare ']/p"));
      
       		
       Departure_Fare = driver.findElement(By.xpath("//div[@class='splitviewSticky makeFlex']//child::div[1]"
       		                                 + "/p[contains(text(),'Departure')]"
       		                                 + " //parent::div//following-sibling::div/span")).getText();
       Thread.sleep(4000);	                                 
       if(Price_Left.get(depaInx).getText().equalsIgnoreCase(Departure_Fare)) {
         System.out.println("Prices of Departureflight's are Equal ....");
                     }
       Dept_Fare = Price_Left.get(depaInx).getText();
       SelectArrivalFlight(ArrivalIdx);  
       Thread.sleep(1000);
       
	    List<WebElement> Price_Right = driver.findElements(By.xpath("//div[@class='splitVw']/child::div[2]/child::div[2]/div/div/label/descendant::div[@class='makeFlex column relative splitfare ']/p"));
	  
	    Return_Fare = driver.findElement(By.xpath("//div[@class='splitviewSticky makeFlex']//child::div[2]"
	    		+ "/p[contains(text(),'Return')]//parent::div//following-sibling::div/span")).getText();
	    		
	    if(Price_Right.get(ArrivalIdx).getText().equalsIgnoreCase(Return_Fare)) {
       	System.out.println("Prices of Returnflight's are Equal ....");
                     }
	    String TotalFare_bottom = driver.findElement(By.xpath("//div[@class='textRight appendRight10']/p/span")).getText();
		System.out.println("TotalFarePriceCheck string ..."+TotalFare_bottom);
		Dept_Fare = Dept_Fare.replaceAll("[^a-zA-Z0-9]","");
        Return_Fare =  Return_Fare.replaceAll("[^a-zA-Z0-9]","");
    	int New_Dept_Fare = Integer.parseInt(Dept_Fare);
	    int New_Return_Fare = Integer.parseInt(Return_Fare);
	    int Total = New_Dept_Fare + New_Return_Fare;
        System.out.println(Total);
		  
	}
 // @Test(priority=6,dependsOnMethods ="FarePriceCheck")
  public static void TotalFarePriceCheck() {
	  String TotalFare_bottom = driver.findElement(By.xpath("//div[@class='textRight appendRight10']/p/span")).getText();
	  System.out.println("TotalFarePriceCheck string ..."+TotalFare_bottom);
	  Departure_Fare = Departure_Fare.replaceAll("[^a-zA-Z0-9]","");
	  Return_Fare =  Return_Fare.replaceAll("[^a-zA-Z0-9]","");
	  int New_Departure_Fare = Integer.parseInt(Departure_Fare);
	  int New_Return_Fare = Integer.parseInt(Return_Fare);
      int Total = New_Departure_Fare + New_Return_Fare;
	  System.out.println(Total);
	  
      }
      
	
	@AfterTest
   public void quit() {
	   //driver.quit();
	}
	
	
	
	public static void SelectDepartureFlight(int Flight_Index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;
		if (DepFilghtCount.size() > Flight_Index && Flight_Index > 0) {
			for (WebElement e : DepFilghtCount) {
				if (count == Flight_Index) {
					js.executeScript("arguments[0].click();", e);
					break;
				                 }
				    count++;
				             }
	                   }
                }	
	public static void SelectArrivalFlight(int Flight_Index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;
		if (ArrFilghtCount.size() > Flight_Index && Flight_Index > 0) {
			for (WebElement e : ArrFilghtCount) {
				if (count == Flight_Index) {
					js.executeScript("arguments[0].click();", e);
					break;
				                 }
				    count++;
				             }
	                   }
	          }
	
	
	
public static int Differences(Date one, Date two) {
		
		int difference = (int) (two.getTime() - one.getTime());
	    int daysBetween = (int) (difference / (1000*60*60*24));
	    System.out.println("Number of Days between dates: "+daysBetween);
		return daysBetween;
		
	                }
   public static void ScrollDown() throws InterruptedException {
   	Actions act = new Actions(driver);
   	WebElement ScrollBar = driver.findElement(By.xpath("//html[@lang='en']"));
		for (int i = 0; i <= 30; i++){
             act.moveToElement(ScrollBar).sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
             Thread.sleep(1000);  
		              }
       
       
	                                               }
	public static void ScrollUp() throws InterruptedException {
   	Actions act = new Actions(driver);
   	WebElement ScrollBar = driver.findElement(By.xpath("//html[@lang='en']"));
		for (int i = 0; i <= 26; i++){
             act.moveToElement(ScrollBar).sendKeys(Keys.PAGE_UP).build().perform(); //Page Up
             Thread.sleep(1000);  
   	               }
                                        }



}
