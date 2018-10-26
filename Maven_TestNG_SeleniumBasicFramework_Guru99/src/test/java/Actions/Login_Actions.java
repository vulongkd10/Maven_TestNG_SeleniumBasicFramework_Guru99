package Actions;

import Pages.BankLogin_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Actions {
    public static void enterUsernameAndPassword(WebDriver wd, String username, String password)
    {
        String userXpath = ".//input[@name='" + "uid" +"']";
        wd.findElement(By.xpath(userXpath)).sendKeys(username);
        wd.findElement(By.xpath(BankLogin_Page.txtPassword)).sendKeys(password);
    }

    public static void clickLoginButton(WebDriver wd)
    {
        wd.findElement(By.xpath(BankLogin_Page.btnLogin)).click();
    }
}
