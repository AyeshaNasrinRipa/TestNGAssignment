package testrunner;

import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;
import java.io.IOException;

public class UserLoginTestRunner extends SetUp {
    @Test(priority = 1,description = "User can login with valid credentials")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);
        JSONArray jsonArray= Utils.readJsonArray("./src/test/resources/users.json");
        JSONObject jsonObject=(JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=jsonObject.get("email").toString();
        String password=jsonObject.get("password").toString();
        loginPage.userLogin(email,password);
        Assert.assertEquals(loginPage.assertDashboardText(), "Dashboard", "Dashboard text not displayed!");
    }

}
