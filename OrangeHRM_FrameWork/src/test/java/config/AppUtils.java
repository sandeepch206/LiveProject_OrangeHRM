package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtils 
{
	public static WebDriver driver;
	public static Properties conpro;
	
	@BeforeTest
	public static void setUp() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/OrangeHRM.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			Reporter.log("Browser is not matching",true);
		}
	}
	
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	}
}
