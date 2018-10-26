package Tests;

import Actions.Login_Actions;
import Commons.LoadConfigFile;
import Commons.Result2Excels;
import Objects.Users;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class LoginTC {

    Properties properties;
    String propertyFilePath= ".\\src\\test\\Resources\\Configs\\Config.properties";

    WebDriver dr;

    Users user1 = new Users();
    int TimeOut = 20;

    @BeforeMethod
    public void init() throws InterruptedException, IOException {

        properties = LoadConfigFile.loadPropertiesFile(propertyFilePath);

        user1.setUsername(properties.getProperty("ID"));
        user1.setPassword(properties.getProperty("pass"));

        System.setProperty(properties.getProperty("ChromeWebDriver"), properties.getProperty("WebDriver_Resource"));
        dr = new ChromeDriver();

        dr.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
        dr.manage().window().maximize();
        // Navigate to site
        dr.get(properties.getProperty("SiteURL"));
        System.out.println(dr.getTitle());
        Thread.sleep(1000);
    }

    @Test(description = "Verify that user can login with valid username and password")
    public  void LoginTC1() throws IOException {

        /*BankLogin_Page.enterUsernameAndPassword(dr,user1.getUsername(),user1.getPassword());
        BankLogin_Page.clickLoginButton(dr);*/

        Login_Actions.enterUsernameAndPassword(dr,user1.getUsername(),user1.getPassword());
        Login_Actions.clickLoginButton(dr);


        if(dr.getTitle().equals("GTPL Bank Manager HomePage"))
        {
            Result2Excels.saveResult2ExcelFile("ResultDemo","Result","TC01","Verify user can login with valid username and password","Passed");
        }else
            Result2Excels.saveResult2ExcelFile("ResultDemo","Result","TC01","Verify user can login with valid username and password","Failed");
        //Check Home page display after login successful
        Assert.assertEquals(" GTPL Bank Manager HomePage ".trim(),dr.getTitle());
        Assert.assertEquals(true,dr.findElement(By.xpath(".//a[@href='addcustomerpage.php']")).isDisplayed());
        Assert.assertEquals("http://demo.guru99.com/V1/html/Managerhomepage.php",dr.getCurrentUrl());

        //End
        dr.close();

    }

    //Verify that user cannot login with invalid username and password
    @Test(description = "Verify that user cannot login with invalid username and password")
    public  void LoginTC2() {

        /*BankLogin_Page.enterUsernameAndPassword(dr,user1.getUsername(),user1.getPassword()+"_wrong");
        BankLogin_Page.clickLoginButton(dr);*/

        Login_Actions.enterUsernameAndPassword(dr,user1.getUsername(),user1.getPassword()+"_wrong");
        Login_Actions.clickLoginButton(dr);

        Alert alert = dr.switchTo().alert();

        Assert.assertEquals("User is not valid",alert.getText());

        alert.accept();
        dr.switchTo().defaultContent();

        //Check Home page display after login successful
        Assert.assertEquals(" GTPL Bank Home Page ".trim(),dr.getTitle());
        Assert.assertEquals(true,dr.findElement(By.xpath(".//input[@name='uid']")).isDisplayed());
        Assert.assertEquals("http://demo.guru99.com/V1/index.php",dr.getCurrentUrl());

        //End
        dr.close();

    }

    @AfterTest
    public void end()
    {
        dr.quit();
    }
}
