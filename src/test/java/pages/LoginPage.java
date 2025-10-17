package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    @FindBy(id="email")
    public WebElement txtEmail;
    @FindBy(id="password")
    public WebElement txtPassword;
    @FindBy(css="[type=submit]")
    WebElement btnSubmit;
    @FindBy(css="div.MuiTypography-root.MuiTypography-h6")
    WebElement dashboardText;
    @FindBy(xpath = "//p[contains(text(),'Invalid email or password')]")
    WebElement invalidLoginMessage;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void userLogin(String email,String password){
        txtEmail.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtEmail.sendKeys(Keys.DELETE);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtPassword.sendKeys(Keys.DELETE);
        txtPassword.sendKeys(password);
        btnSubmit.click();
    }
    public String assertDashboardText(){
        String text = dashboardText.getText();
        return text;
    }
    public String assertInvalidLoginMessage() {
        String text = invalidLoginMessage.getText();
        return text;
    }
}
