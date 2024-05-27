package Webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import DataUtil.properties;

public class Test extends properties {
     static WebDriver d; 
     static Date d1,d2;
   
 	//public static String DepartureDt,ReturnDt;
 	
	public static void main(String[] args) throws InterruptedException, ParseException {
	/* System.setProperty("webdriver.chrome.driver","/Users/pavanivemula/Documents/Drivers/chromedriver");
		d= new ChromeDriver();
	    d.manage().window().maximize();
	    d.get("https://www.makemytrip.com/");
	    d.manage().deleteAllCookies();
	    d.manage().timeouts().pageLoadTimeout(45,TimeUnit.SECONDS);
	    d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        d.findElement(By.xpath("//li[@data-cy='account']")).click();
     //Thread.sleep(2000);
      * */
      
        DataToBeSelected();
	   
		
	}
	
	public static void DataToBeSelected() throws ParseException, InterruptedException {
		init_prop();
		SimpleDateFormat FormatDate = new SimpleDateFormat("MMM dd");
        String DepartureDt =prop.getProperty("DepartureDate");
   	    String  ReturnDt =prop.getProperty("ReturnDate");
   	    d1 = FormatDate.parse(DepartureDt);
   	    d2 = FormatDate.parse(ReturnDt);
      	System.out.println("The departure date is ... " + FormatDate.format(d1));
        System.out.println("The Return date is ... " + FormatDate.format(d2));
       
   	
	}
}

/*
  d.findElement(By.xpath("//div[@class='fsw_inputBox dates inactiveWidget ']")).click();
			String datetobeselected = Data.map().get(7);
			String day = Data.map1().get(15);
			String flag="false";
			 while(flag == "false") {
			    if(d.findElements(By.xpath("//div[contains(@aria-label,'"+datetobeselected+" "+day+"') and @class='DayPicker-Day']")).size()>0) {
			     d.findElement(By.xpath("//div[contains(@aria-label,'"+datetobeselected+" "+day+"') and @class='DayPicker-Day']")).click();
			     flag="true";
			          }
			       else {
			    	 d.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			       }
			 }	
			 
	}
 
*/