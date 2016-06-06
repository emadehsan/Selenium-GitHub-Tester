package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RepositoryCreationTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://github.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  /**
   * Logs in to the site
   * creates a repository with a name that 
   * does not exist.
   * @throws Exception
   */
  @Test
  public void testRepoCreation() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.id("login_field")).clear();
    driver.findElement(By.id("login_field")).sendKeys("leeched@leeching.net");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("leeched@leeching.net");
    driver.findElement(By.name("commit")).click();
    driver.findElement(By.linkText("Create a repository")).click();
    driver.findElement(By.id("repository_name")).clear();
    driver.findElement(By.id("repository_name")).sendKeys("test-repo4");
    driver.findElement(By.id("repository_description")).clear();
    driver.findElement(By.id("repository_description")).sendKeys("Selenium automated repository creation");
//    driver.findElement(By.id("repository_auto_init")).clear();
//    driver.findElement(By.id("repository_auto_init")).sendKeys("");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    String repourl = "https://github.com/leeched/test-repo4"; 
	  Writer.xlWriter("CreateRepo",
			  repourl
			  .equals(driver.getCurrentUrl())
	    		);
	  
    assertEquals(repourl, driver.getCurrentUrl());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
