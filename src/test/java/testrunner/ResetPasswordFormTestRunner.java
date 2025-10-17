package testrunner;
import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import pages.ResetPasswordFormPage;
import services.GmailService;
import utils.Utils;

public class ResetPasswordFormTestRunner extends SetUp {

    @Test(description = "Retrieve Reset Password Link and set new Password")
    public void resetPasswordAndLogin() throws Exception {
        GmailService gmailService = new GmailService();
        String snippet = gmailService.readLatestEmailSnippet();
        String resetLink = snippet.replaceAll(".*(https://dailyfinance\\.roadtocareer\\.net/reset-password\\?token=[^\\s]+).*", "$1");

        driver.get(resetLink);

        String newPassword = "5678";
        ResetPasswordFormPage resetPasswordFormPage = new ResetPasswordFormPage(driver);
        resetPasswordFormPage.resetPassword(newPassword);

        JSONArray jsonArray = Utils.readJsonArray("./src/test/resources/users.json");
        JSONObject lastUser = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String email = lastUser.get("email").toString();
        Utils.updateUserPassword("./src/test/resources/users.json", email, newPassword);
        Thread.sleep(2000);
    }
}

