package utils;


import config.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static int  generateRandomNumber(int min,int max){
        double randomNumber= Math.random()*(max-min)+min;
        return (int) randomNumber;
    }
    public static void saveUserData(JSONObject jsonObject,String jsonFilePath) throws IOException, ParseException {
         JSONArray jsonArray=readJsonArray(jsonFilePath);
         jsonArray.add(jsonObject);

        FileWriter fw=new FileWriter(jsonFilePath);
        fw.write(jsonArray.toJSONString());
        fw.flush();
        fw.close();
    }
    public static void updateUserPassword(String filePath, String email, String newPassword) {
        try {
            JSONArray users =readJsonArray(filePath);

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("email").equals(email)) {
                    user.put("password", newPassword);
                    break;
                }
            }

            // Write back updated JSON to file
            FileWriter writer = new FileWriter(filePath);
            writer.write(users.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateUserEmail(String filePath, String oldEmail, String newEmail) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("email").equals(oldEmail)) {
                    user.put("email", newEmail); // update email
                    break;
                }
            }

            // Write back updated JSON to file
            FileWriter writer = new FileWriter(filePath);
            writer.write(users.toJSONString());
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getToken(WebDriver driver) throws IOException {
        //wait until the authToken is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')") != null);

        //get the authToken from the localstorage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken');");
        String authTokenData=(String) js.executeScript("return window.localStorage.getItem('authTokenData');");
        System.out.println("Auth Token Retrieved: " + authToken);
        System.out.println("Auth Token Retrieved: " + authTokenData);

        // Save the auth token to a localstorage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken", authToken);
        jsonObject.put("authTokenData", authTokenData);
        FileWriter writer=new FileWriter("./src/test/resources/localstorage.json");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }
    public static void setAuth(WebDriver driver) throws ParseException, InterruptedException, IOException {
        JSONObject authObj=readJsonObject("./src/test/resources/localstorage.json");
        String authToken= authObj.get("authToken").toString();
        String authTokenData= authObj.get("authTokenData").toString();
        System.out.println(authToken);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", authToken);
        js.executeScript("window.localStorage.setItem('authTokenData', arguments[0]);", authTokenData);
        Thread.sleep(2000);
    }
    public static void setEnv(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }
    public static void writeListToFile(List<String> list, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        // Optional: Add headers for readability
        writer.write("First Name | Last Name | Email | Phone Number | Address | Gender | Registration Date\n");

        for (String line : list) {
            writer.write(line + "\n");
        }
        writer.close();
    }
    public static JSONArray readJsonArray(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        return jsonArray;
    }
    public static JSONObject readJsonObject(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(new FileReader(filePath));
        return jsonObj;
    }

}
