package testrunner;

import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.ResetPasswordPage;
import utils.Utils;
import java.io.IOException;

public class ResetPasswordTestRunner extends SetUp {
    @Test(priority = 1, description = "User cannot reset password with blank email")
    public void resetPasswordWithBlankEmail() throws InterruptedException {
        driver.findElement(By.partialLinkText("Reset it here")).click();
        ResetPasswordPage resetPasswordPage=new ResetPasswordPage(driver);
        resetPasswordPage.requestPasswordReset("");

        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return document.querySelector('input[type=\"email\"]').validationMessage");
        Assert.assertTrue(validationMessage.contains("Please fill out this field"),
                "Expected validation message not displayed");

    }

    @Test(priority = 2, description = "User cannot reset password with unregistered email")
    public void resetPasswordWithUnregisteredEmail() throws InterruptedException {
        driver.findElement(By.partialLinkText("Reset it here")).click();
        ResetPasswordPage resetPasswordPage=new ResetPasswordPage(driver);
        resetPasswordPage.requestPasswordReset("ayesha@gmail.com");
        String message= resetPasswordPage.getInvalidEmailMessage();
        Assert.assertEquals(message, "Your email is not registered",
                "Expected 'Your email is not registered' message not displayed");

    }

    @Test(priority = 3, description = "User can request password reset using valid registered email")
    public void requestPasswordResetWithValidEmail() throws IOException, ParseException, InterruptedException {
        driver.findElement(By.partialLinkText("Reset it here")).click();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        JSONArray jsonArray = Utils.readJsonArray("./src/test/resources/users.json");
        JSONObject jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String email = jsonObject.get("email").toString();
        resetPasswordPage.requestPasswordReset(email);

        Thread.sleep(3000);
        String successMessage = resetPasswordPage.getValidEmailMessage();
        Assert.assertTrue(successMessage.contains("Password reset link sent to your email"),
                "Success message not displayed after password reset request");
    }

    @AfterMethod
    public void navigateToLoginPage(){
        driver.navigate().to("https://dailyfinance.roadtocareer.net/");
    }

}
