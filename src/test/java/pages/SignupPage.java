package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignupPage {
    @FindBy(id="firstName")
    WebElement txtFirstName;
    @FindBy(id="lastName")
    WebElement txtLastName;
    @FindBy(id="email")
    WebElement txtEmail;
    @FindBy(id="password")
    WebElement txtPassword;
    @FindBy(id="phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy(id="address")
    WebElement txtAddress;
    @FindBy(css="[type=radio]")
    List<WebElement> rbGender;
    @FindBy(css="[type=checkbox]")
    WebElement chkTerms;
    @FindBy(id="register")
    WebElement btnRegister;


    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void signup (UserModel userModel) throws InterruptedException {
        txtFirstName.sendKeys(userModel.getFirstname());
        txtLastName.sendKeys(userModel.getLastname()!=null?userModel.getLastname():"");
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhonenumber());
        txtAddress.sendKeys(userModel.getAddress()!=null?userModel.getAddress():"");
        rbGender.get(1).click();
        chkTerms.click();
        btnRegister.click();
    }}