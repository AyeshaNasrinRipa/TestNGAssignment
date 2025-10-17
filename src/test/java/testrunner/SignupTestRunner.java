package testrunner;
import com.github.javafaker.Faker;
import config.SetUp;
import config.UserModel;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignupPage;
import services.GmailService;
import utils.Utils;


public class SignupTestRunner extends SetUp {
   @Test(priority = 1, description = "User can signup and receive congratulations email")
    public void testUserSignupAndEmail() throws Exception {
        driver.findElement(By.partialLinkText("Register")).click();
        SignupPage signupPage = new SignupPage(driver);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "ayesharipa25+" + Utils.generateRandomNumber(1000, 9999) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "0186" + Utils.generateRandomNumber(10000000, 99999999) + "";
        String address = faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userModel.setAddress(address);
        signupPage.signup(userModel);

       JSONObject jsonObject=new JSONObject();
       jsonObject.put("firstName",firstName);
       jsonObject.put("lastName",lastName);
       jsonObject.put("email",email);
       jsonObject.put("password",password);
       jsonObject.put("phoneNumber",phoneNumber);
       jsonObject.put("address",address);

       Utils.saveUserData(jsonObject,"./src/test/resources/users.json");
       Thread.sleep(3000);
       System.out.println("Sign Up");
        GmailService gmailService = new GmailService();
        String subject = gmailService.readLatestEmail();
        System.out.println(subject);
        Assert.assertTrue(subject.contains("Congratulations on Registering!"));

    }
}
