package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DontStarWithoutLogin {
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
   * checks whether user is redirected back to the page he came from.
   * @throws Exception
   */
  @Test
  public void testDontStarWithoutLogin() throws Exception {
	  driver.get(baseUrl + "/parkjs814/AlgorithmVisualizer");
	  driver.findElement(By.cssSelector("svg.octicon.octicon-star"))
	  	.click();
	  
	  
	  Writer.xlWriter("DontStartWithoutLogin",
			  "https://github.com/login?return_to=%2Fparkjs814%2FAlgorithmVisualizer".equals(driver.getCurrentUrl())
	    		);
	    
	  
	  // checks whether user is redirected back to the page he came from.
	  assertEquals(
			  "https://github.com/login?return_to=%2Fparkjs814%2FAlgorithmVisualizer", 
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
