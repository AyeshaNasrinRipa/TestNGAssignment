package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardPage {

    @FindBy(css = "input.search-box")
    WebElement searchBox;

    @FindBy(xpath = "//table//tbody//tr//td[3]")
    WebElement emailColumn;

    @FindBy(css = ".items-per-page-select")
    WebElement itemsPerPageDropdown;

    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> tableRows;


    public AdminDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchUserByEmail(String email) {
        searchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchBox.sendKeys(Keys.DELETE);
        searchBox.sendKeys(email);
    }

    public String getSearchedEmailText() {
        return emailColumn.getText();
    }

    public void selectItemsPerPage(int number) {
        Select select = new Select(itemsPerPageDropdown);
        select.selectByValue(String.valueOf(number));
    }

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        for (WebElement row : tableRows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            String userData = "";
            for (int i = 0; i < cols.size() - 1; i++) {
                userData += cols.get(i).getText() + " | ";
            }
            users.add(userData.trim());
        }
        return users;
    }
}

