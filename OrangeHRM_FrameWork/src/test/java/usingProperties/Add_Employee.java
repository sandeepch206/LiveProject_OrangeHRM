package usingProperties;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Add_Employee {

	public static void main(String[] args) throws Throwable 
	{
		String uname="Johnpoul2026";
		Properties po = new Properties();
		po.load(new FileInputStream("./PropertyFiles/OrangeHRM.properties"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(po.getProperty("Url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath (po.getProperty("Objuid"))).sendKeys("Admin");
        driver.findElement(By.xpath(po.getProperty("Objpwd"))).sendKeys("Qedge123!@#");
        driver.findElement(By.xpath(po.getProperty("Objlogin"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(po.getProperty("Objadmin"))).click();
		driver.findElement(By.xpath(po.getProperty("Objusermanagement"))).click();
		driver.findElement(By.xpath(po.getProperty("Objuser"))).click();
		driver.findElement(By.xpath(po.getProperty("Objadd"))).click();
		new Select(driver.findElement(By.xpath(po.getProperty("Objuserrole")))).selectByIndex(0);
		driver.findElement(By.xpath(po.getProperty("ObjEname"))).sendKeys("John Poul");
		driver.findElement(By.xpath(po.getProperty("ObjUname"))).sendKeys(uname);
		new Select(driver.findElement(By.xpath(po.getProperty("Objstatus")))).selectByIndex(0);
		driver.findElement(By.xpath(po.getProperty("Objpassword"))).sendKeys("Johnpoul123!@#");
		driver.findElement(By.xpath(po.getProperty("Objconfrmpassword"))).sendKeys("Johnpoul123!@#");
		Thread.sleep(2000);
		driver.findElement(By.xpath(po.getProperty("Objsave"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(po.getProperty("ObjSrchUname"))).sendKeys(uname);
        driver.findElement(By.xpath(po.getProperty("ObjSrch"))).click();
        
        WebElement userTable;
        userTable = driver.findElement(By.xpath(po.getProperty("ObjUserTbl")));
        List<WebElement> rows = driver.findElements(By.xpath(po.getProperty("ObjUserRow")));
        boolean res = false;
        for(int i=1;i<=rows.size(); i++)
        {
        	List<WebElement> cols = driver.findElements(By.xpath(po.getProperty("objUserData")));
        	if(cols.get(i).getText().equals(uname))
        	{
        		res = true;
        		break;
        	}
        	
        }
        Thread.sleep(2000);
        driver.quit();
	}

}
