package usingProperties;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEmp_Code 
{
	public static void main(String[] args) throws Throwable 
	{
		Properties po = new Properties();
		po.load(new FileInputStream("OrangeHRM.Properties"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(po.getProperty("Url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath (po.getProperty("Objuid"))).sendKeys("Admin");
        driver.findElement(By.xpath(po.getProperty("Objpwd"))).sendKeys("Qedge123!@#");
        driver.findElement(By.xpath(po.getProperty("Objlogin"))).click();
        driver.findElement(By.xpath(po.getProperty("Objpim"))).click();
        driver.findElement(By.xpath(po.getProperty("Objaddemp"))).click();
        driver.findElement(By.xpath(po.getProperty("Objfname"))).sendKeys("Richard");
        driver.findElement(By.xpath(po.getProperty("Objmname"))).sendKeys("C");
        driver.findElement(By.xpath(po.getProperty("Objlame"))).sendKeys("Carl");
        String empid = driver.findElement(By.xpath(po.getProperty("Objempid"))).getAttribute("value");
        driver.findElement(By.xpath(po.getProperty("Objsave"))).click();
       
       driver.findElement(By.xpath(po.getProperty("Objemplist"))).click();
       driver.findElement(By.xpath(po.getProperty("Objempsrchid"))).sendKeys(empid);
       driver.findElement(By.xpath(po.getProperty("Objsrchbtn"))).click();
       WebElement emptbl;
       emptbl = driver.findElement(By.xpath(po.getProperty("ObjSrchTbl")));
       
       List<WebElement> rows = emptbl.findElements(By.xpath(po.getProperty("ObjTblRow")));
       boolean res = false;
       for(int i=1; i<rows.size(); i++)
       {
    	   List<WebElement> cols = rows.get(i).findElements(By.xpath(po.getProperty("ObjTblData")));
    	   if(cols.get(i).getText().equals(empid))
			{
				res = true;
				break;				
			}
       }
       Thread.sleep(2000);
       driver.quit();
	}
	
}
