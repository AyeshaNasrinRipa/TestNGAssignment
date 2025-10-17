package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserDashboardPage {
    @FindBy(css="[data-testid=AccountCircleIcon]")
    WebElement btnProfileIcon;
    @FindBy(tagName = "li")
    List<WebElement> comboMenu;
    @FindBy(xpath = "//button[normalize-space()='Edit']")
    WebElement btnEdit;
    @FindBy(xpath = "//input[@type='email']")
    WebElement emailInput;
    @FindBy(xpath = "//button[normalize-space()='Update']")
    WebElement btnUpdate;

    WebDriver driver;

    @FindBy(css = ".add-cost-button")
    WebElement btnAddCost;

    public UserDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddCost() {
        btnAddCost.click();
    }
    public void gotoProfile(){
        btnProfileIcon.click();
        comboMenu.get(0).click();
    }
    public void doLogout(){
        btnProfileIcon.click();
        comboMenu.get(1).click();
    }
    public void editGmail(String gmail){
        btnEdit.click();

        emailInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        emailInput.sendKeys(Keys.DELETE);

        System.out.println(gmail);
        emailInput.sendKeys(gmail);
        btnUpdate.click();
    }

}
