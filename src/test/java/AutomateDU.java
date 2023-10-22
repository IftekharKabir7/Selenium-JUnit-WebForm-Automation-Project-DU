import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class AutomateDU {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void automateForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Rahim");
        driver.findElement(By.id("edit-number")).sendKeys("01700123456");
        driver.findElement(By.xpath("//label[normalize-space()='20-30']")).click();
        scroll(0,500);
        WebElement txtCalender= driver.findElement(By.id("edit-date"));
        txtCalender.click();
        txtCalender.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        txtCalender.sendKeys("10/14/2023");
        txtCalender.sendKeys(Keys.ENTER);
        driver.findElement(By.id("edit-email")).sendKeys("rahim@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I'm no one");
        scroll(0,1000);
        Thread.sleep(3000);
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/Padma.jpg");
        //scroll(0,500);
        Thread.sleep(5000);
        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();
        Thread.sleep(3000);
        ArrayList <String> w = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(w.get(0));
        String actual_msg = driver.findElement(By.id("block-pagetitle-2")).getText();
        String expected_msg = "Thank you for your submission!";
        Assertions.assertEquals(actual_msg,expected_msg);
    }




    public void scroll(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo("+x+","+y+")");
    }
}
