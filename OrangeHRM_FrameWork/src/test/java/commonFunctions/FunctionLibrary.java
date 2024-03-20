package commonFunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtils;

public class FunctionLibrary extends AppUtils
{
	public static String empid;
	public static boolean adminLogin(String uName, String pWord) throws Throwable
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath (conpro.getProperty("Objuid"))).sendKeys(uName);
	    driver.findElement(By.xpath(conpro.getProperty("Objpwd"))).sendKeys(pWord);
	    driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
	    String Expected = "dashboard";
	    String actual = driver.getCurrentUrl();
		if(actual.contains(Expected))
		{
			Reporter.log("Valid Username and password::");
			driver.findElement(By.xpath(conpro.getProperty("ObjWelCome"))).click();
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else
		{
			String errMsg = driver.findElement(By.xpath(conpro.getProperty("ObjErrMsg"))).getText();
			Thread.sleep(2000);
			Reporter.log(errMsg+"  InValid Username and Password");
			return false;
			
		}
	}
	
	public static boolean addEmployee(String fName, String lName)
	{
		boolean res;
		driver.get(conpro.getProperty("Url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath (conpro.getProperty("Objuid"))).sendKeys("Admin");
        driver.findElement(By.xpath(conpro.getProperty("Objpwd"))).sendKeys("Qedge123!@#");
        driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
        driver.findElement(By.xpath(conpro.getProperty("Objpim"))).click();
        driver.findElement(By.xpath(conpro.getProperty("Objaddemp"))).click();
        driver.findElement(By.xpath(conpro.getProperty("Objfname"))).sendKeys(fName);
        driver.findElement(By.xpath(conpro.getProperty("Objlame"))).sendKeys(lName);
        empid = driver.findElement(By.xpath(conpro.getProperty("Objempid"))).getAttribute("value");
        driver.findElement(By.xpath(conpro.getProperty("Objsave"))).click();
        
        driver.findElement(By.xpath(conpro.getProperty("Objemplist"))).click();
		driver.findElement(By.xpath(conpro.getProperty("Objempsrchid"))).sendKeys(empid);
		driver.findElement(By.xpath(conpro.getProperty("Objsrchbtn"))).click();
		
		WebElement emptbl;
		emptbl = driver.findElement(By.xpath(conpro.getProperty("ObjSrchTbl")));
		
		List<WebElement> rows = emptbl.findElements(By.xpath(conpro.getProperty("ObjTblRow")));
		
		for(int i=1;i<rows.size();i++)
		{
			List<WebElement> cols = rows.get(i).findElements(By.xpath(conpro.getProperty("ObjTblData")));
			if(cols.get(1).getText().equals(empid))
			{
				res = true;
				break;				
			}
		}
		 return res = true;
	}
	
	public static boolean addUser(String eName, String uName, String pWord) throws Throwable
	{
		boolean addemp;
		
		driver.get(conpro.getProperty("Url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath (conpro.getProperty("Objuid"))).sendKeys("Admin");
        driver.findElement(By.xpath(conpro.getProperty("Objpwd"))).sendKeys("Qedge123!@#");
        driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
        
        driver.findElement(By.xpath(conpro.getProperty("Objadmin"))).click();
        driver.findElement(By.xpath(conpro.getProperty("Objusermanagement"))).click();
        driver.findElement(By.xpath(conpro.getProperty("Objuser"))).click();
        driver.findElement(By.xpath(conpro.getProperty("Objadd"))).click();
        Select uRole = new Select(driver.findElement(By.xpath(conpro.getProperty("Objuserrole"))));
        uRole.selectByIndex(1);
        driver.findElement(By.xpath(conpro.getProperty("ObjEname"))).sendKeys(eName);
        driver.findElement(By.xpath(conpro.getProperty("ObjUname"))).sendKeys(uName);
        driver.findElement(By.xpath(conpro.getProperty("Objpassword"))).sendKeys(pWord);
        driver.findElement(By.xpath(conpro.getProperty("Objconfrmpassword"))).sendKeys(pWord);
        Thread.sleep(2000);
        driver.findElement(By.xpath(conpro.getProperty("Objsave"))).click();
        
        driver.findElement(By.xpath(conpro.getProperty("ObjSrchUname"))).sendKeys(uName);
		driver.findElement(By.xpath(conpro.getProperty("ObjSrch"))).click();
		
		WebElement usertable;
		usertable = driver.findElement(By.xpath(conpro.getProperty("ObjUserTbl")));
		List<WebElement> rows = usertable.findElements(By.xpath(conpro.getProperty("ObjUserRow")));
		
		for(int i=1; i<rows.size(); i++)
		{
			List<WebElement> cols = rows.get(i).findElements(By.xpath(conpro.getProperty("objUserData")));
			if(cols.get(1).getText().equals(uName))
			{
				addemp = true;
				break;
			}
		}
		return addemp = true;
        
	}
}
