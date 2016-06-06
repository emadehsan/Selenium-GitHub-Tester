package tests;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SignOutTest {
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
   * Logs the user in
   * the tests whether signed out successfully?
   * @throws Exception
   */
  @Test
  public void testSignout() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.id("login_field")).clear();
    driver.findElement(By.id("login_field")).sendKeys("leeched@leeching.net");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("leeched@leeching.net");
    driver.findElement(By.name("commit")).click();
    driver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a")).click();
    driver.findElement(By.cssSelector("button.dropdown-item.dropdown-signout")).click();
    
    // after signing out
    // checks whether sign out was successful usin 
    // cookie value
    assertEquals("no", driver.manage().getCookieNamed("logged_in").getValue());
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
