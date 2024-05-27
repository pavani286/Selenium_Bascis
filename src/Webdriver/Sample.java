package Webdriver;

import java.util.ArrayList;

import DataUtil.Xls_Reader;

public class Sample {
	public static ArrayList<Object> main(String[] args)  {
	  ArrayList<Object> list = new ArrayList<Object>();
	  Xls_Reader reader = new Xls_Reader("/Users/pavanivemula/Documents/Workspace/Selenium_Bascis/src/DataUtil/Login.xlsx");
	  int rows = reader.getRowCount("Twitter");
	  System.out.println(rows);
	  for(int i=2;i<=rows;i++) {
		String username = reader.getCellData("Twitter","Username", i); 
		String password = reader.getCellData("Twitter", "Password", i);
		System.out.println(username +" "+password);
		Object data [] = {username,password};
		list.add(data);
	  }
	return list;
	}
	
	
}
