package com.google.wrappers;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GenericWrappers {

	public RemoteWebDriver driver;
	public void invokeApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.geckodriver.driver", "./Drivers/geckodriver.exe");
				//driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./Drivers/IE.exe");
				//driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			System.out.println("The given URL '" + url + "' is launched in '" + browser + "' browser successfully.");
		} catch (WebDriverException e) {
			System.out.println(e.toString());
			System.err.println("The given URL '" + url + "' is not launched in '" + browser + "' browser successfully.");
		}
	}

	public void enterByXpath(String xpath, String data) {
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(data);
			System.out.println("The webelement with an ID: '" + xpath + "' is identified and entered with the data '"
					+ data + "' successfully.");
		} catch (WebDriverException e) {
			System.err.println("The application got crashed for unknown error.");
		} 
	}

	public void clickByXpath(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
			System.out.println("The webelement with an ID: '" + xpath + "' is identified and clicked successfully.");
		} catch (WebDriverException e) {
			System.err.println("The application got crashed for unknown error.");
		}
	}
	
	public String[][] getData() {
		String[][] resultData = null;
		try {
			FileInputStream fis = new FileInputStream("./TestData/GoogleSearch_TestData.xlsx");
			XSSFWorkbook virWB = new XSSFWorkbook(fis);
			XSSFSheet virWS = virWB.getSheetAt(0);
		
			int RowCnt = virWS.getLastRowNum();
			int ColCnt = virWS.getRow(0).getLastCellNum();
			
			resultData = new String[RowCnt][ColCnt];
			
			for (int i = 1; i<=RowCnt; i++) {
				XSSFRow virRow = virWS.getRow(i);
				for (int j = 0; j<ColCnt;j++) {
					resultData[i-1][j] = virRow.getCell(j).getStringCellValue();
				}
			}
			virWB.close();		
		} catch (Exception e) {
			System.out.println("Unable to read file");
		}
		return resultData;
	}
	
	public void threadSleep(long miliSecs) {
		try {
			Thread.sleep(miliSecs);
		} catch (Exception e) {
			System.err.println();
		} 
	}
	
	public void closeBrowser() {
		try {
			driver.close();
			System.out.println("The active browser is closed successully.");
		} catch (WebDriverException e) {
			System.err.println("The application got crashed for unknown error.");
		} 
	}

	public void closeAllBrowsers() {
		try {
			if (driver != null) {
				driver.quit();
				System.out.println("All the browsers are closed successully.");
			}
		} catch (WebDriverException e) {
			System.err.println("The application got crashed for unknown error.");
		} 
	}
	
	
}
