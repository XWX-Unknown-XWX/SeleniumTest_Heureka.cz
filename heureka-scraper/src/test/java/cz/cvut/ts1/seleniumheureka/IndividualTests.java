package cz.cvut.ts1.seleniumheureka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class IndividualTests extends TestCase {

  @ParameterizedTest
  @CsvFileSource(resources = "/loginInfo.csv")
  public void logIn(String login, String password) {
    var name = new MainPage(getDriver())
      .goToLoginPage()
      .login(login, password)
      .getUserName();
    assertEquals(login, name);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/loginInfo.csv")
  public void logOut(String login, String password)
    throws InterruptedException {
    MainPage mainPage = new MainPage(getDriver());
    mainPage.acceptCookies();
    assertFalse(mainPage.isLoggedIn());
    UserMainPage ump = mainPage.goToLoginPage().login(login, password);
    assertTrue(ump.isLoggedIn());
    ProfilePage pp = ump.navToHeurekaProfile();
    pp.logOut();
    assertFalse(pp.isLoggedIn());
  }

  @Test
  public void dismissCookies() {
    MainPage page = new MainPage(getDriver());
    assertTrue(page.isThereACookiePopUp()); // need to start with cookie pop-up
    page.acceptCookies();
    assertFalse(page.isThereACookiePopUp());
    LoginPage loginPage = page.goToLoginPage();
    assertFalse(loginPage.isThereACookiePopUp()); // check if it persists
  }

  @ParameterizedTest
  @CsvSource({ "10000,30000", "25000,20000", "60000,70000" })
  public void laptopPrice(int min, int max) {
    if (min > max) {
      int tmp = min;
      min = max;
      max = tmp;
    }
    var driver = getDriver();
    driver.get("https://notebooky.heureka.cz/");
    var pg = new LaptopsSearchPage(driver);
    pg.setPriceRange(min, max); //.setReviewTier(1).requireAvailability();
    List<int[]> pricepairs = pg.getPriceRange();
    for (int[] prices : pricepairs) {
      for (int price : prices) {
        assertTrue(price >= min);
        assertTrue(price <= max);
      }
    }
  }
}
    pp.logOut();
    assertFalse(pp.isLoggedIn());
  }

  @Test
  public void dismissCookies() {
    MainPage page = new MainPage(getDriver());
    assertTrue(page.isThereACookiePopUp()); // need to start with cookie pop-up
    page.acceptCookies();
    assertFalse(page.isThereACookiePopUp());
    LoginPage loginPage = page.goToLoginPage();
    assertFalse(loginPage.isThereACookiePopUp()); // check if it persists
  }

  @ParameterizedTest
  @CsvSource({ "10000,30000", "25000,20000", "60000,70000" })
  public void laptopPrice(int min, int max) {
    if (min > max) {
      int tmp = min;
      min = max;
      max = tmp;
    }
    var driver = getDriver();
    driver.get("https://notebooky.heureka.cz/");
    var pg = new LaptopsSearchPage(driver);
    pg.setPriceRange(min, max); //.setReviewTier(1).requireAvailability();
    List<int[]> pricepairs = pg.getPriceRange();
    for (int[] prices : pricepairs) {
      for (int price : prices) {
        assertTrue(price >= min);
        assertTrue(price <= max);
      }
    }
  }
}
