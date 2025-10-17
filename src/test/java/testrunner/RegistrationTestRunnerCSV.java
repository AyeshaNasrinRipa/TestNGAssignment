package testrunner;

import config.RegistrationDataSet;
import config.SetUp;
import config.UserModel;
import org.testng.annotations.Test;
import pages.SignupPage;

public class RegistrationTestRunnerCSV extends SetUp {

    @Test(description="User Registration from CSV",dataProvider = "RegistrationDataSet", dataProviderClass = RegistrationDataSet.class)
    public void doRegistration(UserModel user) throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/register");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(user);
    }
}
