package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BankLogin_Page {

    public static String txtUsername = ".//input[@name='" +
            "']";
    public static String txtPassword = ".//input[@name='password']";
    public static String btnLogin = ".//input[@name='btnLogin']";

   /* @FindBy(xpath = ".//input[@name='uid']" )
    static WebElement txtUsername;

    @FindBy(xpath = ".//input[@name='password']" )
    static WebElement txtPassword;*/

    /*public BankLogin_Page(WebDriver wd)
    {

    }*/

  /*  public static void enterUsernameAndPassword(WebDriver wd,String username, String password)
    {
        wd.findElement(By.xpath(txtUsername)).sendKeys(username);
        wd.findElement(By.xpath(txtPassword)).sendKeys(password);
    }

    public static void clickLoginButton(WebDriver wd)
    {
        wd.findElement(By.xpath(btnLogin)).click();
    }*/
}
