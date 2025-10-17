package testrunner;

import com.github.javafaker.Faker;
import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCostPage;
import pages.LoginPage;
import pages.UserDashboardPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class AddCostTestRunner extends SetUp {

    @Test(description = "Add 2 items (all fields + mandatory fields) and assert items are shown in table")
    public void addTwoItems() throws  IOException, ParseException {
        Faker faker = new Faker();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        JSONArray jsonArray = Utils.readJsonArray("./src/test/resources/users.json");
        JSONObject jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String email = jsonObject.get("email").toString();
        String password = jsonObject.get("password").toString();
        loginPage.userLogin(email, password);

        UserDashboardPage dashboardPage = new UserDashboardPage(driver);
        dashboardPage.clickAddCost();
        AddCostPage addCostPage = new AddCostPage(driver);

        String item1Name = faker.commerce().productName();
        String item1Amount = String.valueOf(faker.number().numberBetween(100, 1000));
        addCostPage.addCost(item1Name, item1Amount);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert1 = driver.switchTo().alert();
        alert1.accept();

        dashboardPage.clickAddCost();

        String item2Name = faker.commerce().productName();
        String item2Amount = String.valueOf(faker.number().numberBetween(100, 1000));
        String purchaseDate = "02-10-2000";
        String month = "November";
        String remarks = "Test remarks for item 2";

        addCostPage.addCostAllFields(item2Name, item2Amount,purchaseDate, month, remarks);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();

        Assert.assertTrue(addCostPage.isItemPresent(item1Name), "First item should be present in the table");
        Assert.assertTrue(addCostPage.isItemPresent(item2Name), "Second item should be present in the table");


    }
}
