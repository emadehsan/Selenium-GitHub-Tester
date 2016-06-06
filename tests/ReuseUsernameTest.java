package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ReuseUsernameTest {
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
   * Checks whether website allows
   * reuse of the same username?
   * @throws Exception
   */
  @Test
  public void testReuseUsername() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user[login]")).clear();
    driver.findElement(By.name("user[login]")).sendKeys("leeched");
    driver.findElement(By.name("user[email]")).clear();
    driver.findElement(By.name("user[email]")).sendKeys("leeched@leeching.net");
    driver.findElement(By.name("user[password]")).clear();
    driver.findElement(By.name("user[password]")).sendKeys("leeched@leeching.net");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertEquals("Login is already taken", driver.findElement(By.cssSelector("dd.error")).getText());
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
