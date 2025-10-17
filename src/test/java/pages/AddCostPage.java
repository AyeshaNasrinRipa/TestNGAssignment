package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddCostPage {

    WebDriver driver;

    @FindBy(id = "itemName")
    WebElement txtItemName;

    @FindBy(id = "amount")
    WebElement txtAmount;

    @FindBy(css = ".submit-button")
    WebElement btnSubmit;

    @FindBy(xpath = "//button[text()='+']")
    WebElement btnAdd;

    @FindBy(id = "purchaseDate")
    WebElement txtPurchaseTime;

    @FindBy(id = "month")
    WebElement dropdownMonth;

    @FindBy(id = "remarks")
    WebElement txtRemarks;

    public AddCostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Add cost with mandatory fields only
    public void addCost(String itemName, String amount) {
        txtItemName.clear();
        txtItemName.sendKeys(itemName);

        txtAmount.clear();
        txtAmount.sendKeys(amount);

        btnSubmit.click();
    }

    public void addCostAllFields(String itemName, String amount,String date, String month, String remarks) {
        txtItemName.clear();
        txtItemName.sendKeys(itemName);
        btnAdd.click();

        txtAmount.clear();
        txtAmount.sendKeys(amount);

        txtPurchaseTime.clear();
        String[] dateParts = date.split("-");
        String dateMonth = dateParts[0];
        String dateDay = dateParts[1];
        String dateYear = dateParts[2];

        txtPurchaseTime.clear();
        txtPurchaseTime.sendKeys(dateMonth);
        txtPurchaseTime.sendKeys(dateDay);
        txtPurchaseTime.sendKeys(dateYear);

        Select selectMonth = new Select(dropdownMonth);
        selectMonth.selectByVisibleText(month);

        txtRemarks.clear();
        txtRemarks.sendKeys(remarks);

        btnSubmit.click();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
    public boolean isItemPresent(String itemName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

        for (WebElement row : rows) {
            String name = row.findElement(By.xpath("td[1]")).getText();
            if (name.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }


}
