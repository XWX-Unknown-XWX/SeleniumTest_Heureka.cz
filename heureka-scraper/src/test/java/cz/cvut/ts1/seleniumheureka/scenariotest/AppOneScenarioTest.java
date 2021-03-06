package cz.cvut.ts1.seleniumheureka.scenariotest;

import java.util.List;

import cz.cvut.ts1.seleniumheureka.MainPage;
import cz.cvut.ts1.seleniumheureka.TestCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AppOneScenarioTest extends TestCase {

  @ParameterizedTest
  @CsvSource("ngvup3414@budgermile.rest,123456789")
  public void shouldAnswerWithTrue(String login, String password) {
    var pg = new MainPage(getDriver());
    pg.acceptCookies();
    pg
      .goToLoginPage()
      .login(login, password) // Enter your login and password before start testing
      .navToLaptopsPage()
      .setReviewTier(1)
      .setPriceRange(27000, 40000)
      .selectManufacturers(List.of("Apple"))
      .laptopInfo()
      .laptopPriceDescriptionOne()
      .backToLaptopPage()
      .laptopPriceDescriptionTwo()
      .backToLaptopPage()
      .openThirdLaptopDescription()
      .addThirdLaptopToUserBasket()
      .goToMainPage()
      .navToCarPage()
      .skodaAutoDetails()
      .advisoryCarSearch()
      .navToHowToChooseAuto()
      .goBackToMainPage()
      .navToHeurekaProfile()
      .logOut();
    System.out.println(); //Select this line and run debug to see the testing process
  }
}
