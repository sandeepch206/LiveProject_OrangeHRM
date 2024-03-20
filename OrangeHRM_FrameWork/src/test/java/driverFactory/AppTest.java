package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class AppTest extends FunctionLibrary
{
	String inputPath = "./FileInput/Orange_HRM.xlsx";
	String outputPath = "./FileOutput/LoginData.xlsx";
	String empInputPath = "./FileInput/Emp_Details.xlsx";
	String empOutputPath = "./FileOutput/AddEmp_Data.xlsx";
	String userInputPath = "./FileInput/User_Details.xlsx";
	String userOutputPath = "./FileOutput/AddUser_Data.xlsx";
	ExtentReports report;
	ExtentTest logger;
	

	
	@Test
	public void startTest() throws Throwable
	{
		report = new ExtentReports("./ExtentReports/LoginReport.html");
		boolean res = false;
		ExcelFileUtil xl = new ExcelFileUtil(inputPath);
		int rc = xl.rowCount("Login");
		Reporter.log("No of Rows::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = report.startTest("Validation Login");
			logger.assignAuthor("Sandeep Ch");
			String userName = xl.getcellData("Login", i, 1);
			String passWord = xl.getcellData("Login", i, 2);
			logger.log(LogStatus.INFO,userName+"---"+passWord);
			res = FunctionLibrary.adminLogin(userName, passWord);
			if(res)
			{
				xl.setCellData("Login", i, 3, "Valid Username and Password", outputPath);
				xl.setCellData("Login", i, 4, "Pass", outputPath);
				logger.log(LogStatus.PASS, "Valid Username and Password");
			}else
			{
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./ScreenShot/Iteration/"+i+"LoginPage.png"));
				xl.setCellData("Login", i, 3, "InValid Username and Password", outputPath);
				xl.setCellData("Login", i, 4, "Fail", outputPath);
				logger.log(LogStatus.FAIL, "InValid Username and Password");
			}
			report.endTest(logger);
			report.flush();
		}
		
	}
	
	@Test
	public void addEmpTest() throws Throwable
	{
		report = new ExtentReports("./ExtentReports/AddEmpReport.html");
		boolean res = false;
		ExcelFileUtil xl = new ExcelFileUtil(empInputPath);
		int rc = xl.rowCount("Emp_Details");
		Reporter.log("No of Rows"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger=report.startTest("Add Employee");
			logger.assignAuthor("Sandeep Ch");
			String fName = xl.getcellData("Emp_Details", i, 0);
			String lName = xl.getcellData("Emp_Details", i, 1);
			logger.log(LogStatus.INFO, fName+"  "+lName);
			res = FunctionLibrary.addEmployee(fName, lName);
			if(res)
			{
				xl.setCellData("Emp_Details", i, 2, empid, empOutputPath);
				xl.setCellData("Emp_Details", i, 3, "Employee added Successfully", empOutputPath);
				xl.setCellData("Emp_Details", i, 4, "Pass", empOutputPath);
				logger.log(LogStatus.PASS, "Employee Added Succesfully");
			}else
			{
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./ScreenShot/Iteration/"+i+"AddEmp.png"));
				xl.setCellData("Emp_Details", i, 3, "Employee added unsuccessfully", empOutputPath);
				xl.setCellData("Emp_Details", i, 4, "Fail", empOutputPath);
				logger.log(LogStatus.FAIL, "Employee Added Unsuccessfully");
			}
			report.endTest(logger);
			report.flush();
		}
		
	}
	
	@Test
	public void addUserTest() throws Throwable
	{
		report = new ExtentReports("./ExtentReports/AddUserReport.html");
		boolean res = false;
		ExcelFileUtil xl = new ExcelFileUtil(userInputPath);
		int rc = xl.rowCount("User_Details");
		Reporter.log("No of Rows"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger=report.startTest("Add User");
			logger.assignAuthor("Sandeep Ch");
			String eName = xl.getcellData("User_Details", i, 0);
			String uName = xl.getcellData("User_Details", i, 1);
			String pWord = xl.getcellData("User_Details", i, 2);
			logger.log(LogStatus.INFO, eName+"  "+uName);
			res = FunctionLibrary.addUser(eName, uName, pWord);
			if(res)
			{
				xl.setCellData("User_Details", i, 3, "User added Successfully", userOutputPath);
				xl.setCellData("User_Details", i, 4, "Pass", userOutputPath);
				logger.log(LogStatus.PASS, "User added Successfully");
			}else
			{
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./ScreenShot/Iteration/"+i+"AddUser.png"));
				xl.setCellData("User_Details", i, 3, "User added unsuccessfully", userOutputPath);
				xl.setCellData("User_Details", i, 4, "Fail", userOutputPath);
				logger.log(LogStatus.FAIL, "User Added Unsuccessfully");
			}
			report.endTest(logger);
			report.flush();
		}
	}
}
