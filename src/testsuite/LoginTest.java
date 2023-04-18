package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/ ";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoinPageSuccessfully() {
        WebElement loginLink = driver.findElement(By.xpath("//a[@href ='/users/sign_in']"));
        loginLink.click();
        String expectedText = "Welcome Back!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[@class = 'page__heading']"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Welcome back message not displayed", expectedText,actualText);
    }

    @Test
    public void verifyTheErrorMessage(){
        //Find the Email Field Element
        WebElement signInLink = driver.findElement(By.xpath("//a[@href ='/users/sign_in']"));
        //Click on Sign
        signInLink.click();
        //Find username field element
        WebElement usernameField = driver.findElement(By.id("user[email]"));
        //Type invalid username to username field element
        usernameField.sendKeys("Bee_Patel123@outlook.com");
        //Find the password element and send invalid password on password filed
        driver.findElement(By.id("user[password]")).sendKeys("secret_sauce");
        //Find the login button element and click
        WebElement loginButton = driver.findElement(By.xpath("//button[@type= 'submit']"));
        loginButton.click();
        String expectedMessage = "Invalid email or password."; // Expected message from requirements
        String actualMessage = driver.findElement(By.xpath("//div[@id='notice']")).getText();// Find the text element and get the text
        Assert.assertEquals("Error Message was not displayed.", expectedMessage, actualMessage);
    }
    @After
    public void teamDown() {
        closeBrowser();
    }
}
