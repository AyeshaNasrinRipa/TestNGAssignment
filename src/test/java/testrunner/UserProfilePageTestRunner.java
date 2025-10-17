package testrunner;

import com.github.javafaker.Faker;
import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserDashboardPage;
import utils.Utils;
import java.io.IOException;
import java.time.Duration;

public class UserProfilePageTestRunner extends SetUp {

    private String oldEmail;
    private String newEmail;
    private String password;

    @Test(priority = 1, description = "Update user Gmail from profile page")
    public void updateUserEmail() throws IOException, ParseException {
        Faker faker = new Faker();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        JSONArray jsonArray = Utils.readJsonArray("./src/test/resources/users.json");
        JSONObject jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        oldEmail = jsonObject.get("email").toString();
        password = jsonObject.get("password").toString();
        loginPage.userLogin(oldEmail, password);

        UserDashboardPage dashboardPage = new UserDashboardPage(driver);
        dashboardPage.gotoProfile();

        String[] parts = oldEmail.split("\\+");
        String base = parts[0] + "+";
        String domain = "@gmail.com";

        int randomDigits = faker.number().numberBetween(100, 9999); // 3 to 4 digits
        newEmail = base + randomDigits + domain;

        dashboardPage.editGmail(newEmail);
        try { Thread.sleep(5000); } catch (InterruptedException e) { throw new RuntimeException(e); }

        String filePath = "./src/test/resources/users.json";
        Utils.updateUserEmail(filePath, oldEmail, newEmail);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        dashboardPage.doLogout();
    }

    @Test(priority = 2, description = "Verify login with old email fails")
    public void verifyOldEmailLogin()  {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.userLogin(oldEmail, password);
        Assert.assertEquals(loginPage.assertInvalidLoginMessage(), "Invalid email or password", "Error message not displayed or incorrect!");
    }
    @Test(priority = 3, description = "Verify login with updated email works")
    public void verifyNewEmailLogin()  {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.userLogin(newEmail, password);
        Assert.assertEquals(loginPage.assertDashboardText(), "Dashboard", "Dashboard text not displayed!");
        UserDashboardPage dashboardPage = new UserDashboardPage(driver);
        dashboardPage.doLogout();
    }
}
