import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SampleTest {

    WebDriver driver = new ChromeDriver();
    String originalSite = "C:\\Users\\bkanumur\\Desktop\\fall22\\565\\assignment5\\index.html";
    String modifiedSite = "C:\\Users\\bkanumur\\Desktop\\fall22\\565\\assignment5\\index2.html";

    @BeforeMethod
    public void setUp(){
//        driver.get(originalSite);
        driver.get(modifiedSite);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // CONTENT VERIFICATION TEST CASES
    @Test
    public void verifyTitle(){
        String actualTitle = driver.getTitle();
        String expectedTitle = "Fountain of Fruit";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    //verifying that the text content is the same
    @Test
    public void verifyTextInApples(){
        driver.findElement(By.id("appleBtn")).click();
        String text = driver.findElement(By.id("appleTxt")).getText();
        System.out.println(text);
        Assert.assertEquals(text,"Worm Wiggled Into An Apple");
    }

    //EXISTENCE OF ELEMENT TEST CASES

    //verifying that the banana image exists on the banana page
    @Test
    public void verifyBananaImgExists(){
        driver.findElement(By.id("bananaBtn")).click();
        String imgSrc = driver.findElement(By.id("bananaImg")).getDomAttribute("src");
        Assert.assertEquals(imgSrc, "https://i.ytimg.com/vi/SpAHPXNhAwk/maxresdefault.jpg");
    }

    //verifying that the apple image exists on the home page
    @Test
    public void verifyAppleImgExists(){
        String imgSrc = driver.findElement(By.id("appleimg")).getDomAttribute("src");
        System.out.println(imgSrc);
        Assert.assertEquals(imgSrc, "https://www.realfoods.co.uk/ProductImagesID/16061_1.jpg");
    }

    //ELEMENTS MODIFIED TEST CASES
    @Test
    public void verifyBananaImgWidth(){
        String width = driver.findElement(By.id("bananaImg")).getDomAttribute("width");
        Assert.assertEquals(width,"400");
    }

    //NAVIGATION TEST CASES
    //verifying that clicking on the banana image on the home page redirects it to banana.html page
    @Test
    public void verifyBananaImg(){
        String src = driver.findElement(By.id("bananaImg")).getDomAttribute("src");
        Assert.assertEquals(src,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSoGtRNlYb9s_NcmTEOBZu8NeJa82efGXn2Yr6lOAYR9S1mlWzA");
    }

    //verifying that the link on bananas page takes it to apples page
    @Test
    public void applesBtntoBanana(){
        driver.findElement(By.id("bananaBtn")).click();
        String link = driver.findElement(By.id("clickApple")).getDomAttribute("href");
        Assert.assertEquals(link, "Apples.html");
    }

    // To verify correct navigation from first page to the third and back to first page
    @Test
    public void verifyNavigation(){
        driver.findElement(By.id("toBananas")).click();
        driver.findElement(By.id("toApple")).click();
        driver.findElement(By.tagName("a")).click();
        String title = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(title, "Fountain of Fruit");
    }

    @AfterTest
    public void breakDown(){
        driver.quit();
    }
}