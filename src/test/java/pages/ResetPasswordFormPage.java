package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordFormPage {

    @FindBy(id=":r1:")
    private WebElement txtNewPassword;

    @FindBy(id=":r3:")
    private WebElement txtConfirmPassword;

    @FindBy(xpath="//button[text()='Reset Password']")
    private WebElement btnResetPassword;

    public ResetPasswordFormPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void resetPassword(String newPassword){
        txtNewPassword.sendKeys(newPassword);
        txtConfirmPassword.sendKeys(newPassword);
        btnResetPassword.click();
    }
}
