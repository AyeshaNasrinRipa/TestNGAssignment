package testrunner;

import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class AdminDashboardTestRunner extends SetUp {

    @Test(priority = 1, description="Admin can login successfully with valid email and password")
    public void doLogin() throws IOException {
        LoginPage loginPage=new LoginPage(driver);

        if(System.getProperty("email")!=null && System.getProperty("password")!=null){
            loginPage.userLogin(System.getProperty("email"),System.getProperty("password"));
        }
        String txtHeaderActual=driver.findElement(By.tagName("h2")).getText();
        String txtHeaderExpected="Admin Dashboard";
        Assert.assertEquals(txtHeaderActual,txtHeaderExpected);
        Utils.getToken(driver);
    }
    @Test(priority = 2,description = "Search updated Gmail and verify it appears on Admin Dashboard")
    public void searchByUpdatedGmail() throws IOException, ParseException, InterruptedException {
        Utils.setAuth(driver);
        Thread.sleep(2000);
        driver.get("https://dailyfinance.roadtocareer.net/admin");

        JSONArray users = Utils.readJsonArray("./src/test/resources/users.json");
        JSONObject latestUser = (JSONObject) users.get(users.size() - 1);
        String updatedEmail = latestUser.get("email").toString();

        AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
        adminDashboardPage.searchUserByEmail(updatedEmail);

        Thread.sleep(2000);

        String displayedEmail = adminDashboardPage.getSearchedEmailText();
        Assert.assertEquals(displayedEmail, updatedEmail, "Updated email not shown on Admin Dashboard!");

        System.out.println("Verified updated user email on Admin Dashboard: " + displayedEmail);
    }
}
