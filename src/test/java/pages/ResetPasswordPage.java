package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage {
    @FindBy(id=":r1:")
    WebElement txtEmail;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSendResetLink;

    @FindBy(tagName = "p")
    WebElement paragraphMessage;

    public ResetPasswordPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void requestPasswordReset (String email) throws InterruptedException {
        txtEmail.sendKeys(email);
        btnSendResetLink.click();
    }
    public String getInvalidEmailMessage(){
        return paragraphMessage.getText();
    }
    public String getValidEmailMessage() {
        return paragraphMessage.getText();
    }


}