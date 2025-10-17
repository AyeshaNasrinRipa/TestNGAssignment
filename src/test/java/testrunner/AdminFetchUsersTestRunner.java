package testrunner;

import config.SetUp;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class AdminFetchUsersTestRunner extends SetUp {
    @Test(priority = 1, description = "Admin can login and fetch all users from the user table, then write them into a text file")
    public void doLoginAndFetchUsers() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin("admin@test.com", "admin123");

        AdminDashboardPage dashboard = new AdminDashboardPage(driver);

        dashboard.selectItemsPerPage(50);
        Thread.sleep(2000);

        List<String> allUsers = dashboard.getAllUsers();

        Utils.writeListToFile(allUsers, "./src/test/resources/allUsers.txt");
    }



}
