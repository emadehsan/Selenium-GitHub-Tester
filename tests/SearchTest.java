package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchTest {
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
   * checks whether user searching with
   * the specified username takes to that particular page?
   * @throws Exception
   */
  @Test
  public void testSearch() throws Exception {
	String username = "emadehsan";
	
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.id("login_field")).clear();
    driver.findElement(By.id("login_field")).sendKeys("leeched@leeching.net");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("leeched@leeching.net");
    driver.findElement(By.name("commit")).click();
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys(username);
    driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
    assertEquals(
    				"https://github.com/search?utf8=%E2%9C%93&q=" 
    				+ 
    				username, 
    				driver.getCurrentUrl()
			);
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
