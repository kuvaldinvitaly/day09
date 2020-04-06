package ru.lanit.atschool.steps;


import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.lanit.atschool.pages.MainPage;
import ru.lanit.atschool.webdriver.WebDriverManager;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainPageSteps {
    private WebDriver driver = WebDriverManager.getDriver();
    private MainPage mainPage = new MainPage();



    @Пусть("открыт браузер и введен адрес \"(.*)\"$")
    public void openedBrowserAndEnteredUrl(String url){
        mainPage.openPage(url);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Lanit education", "Test not complited");
        System.out.println("Test openedBrowserAndEnteredUrl complited");
    }



    @И("переходим в раздел Категории")
    public void goToCategories() {
        mainPage.btnCategiries.click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Категории | Lanit education", "Test goToCategories not complited");
        System.out.println("Test goToCategories complited");

    }



    @И("переходим в раздел Пользователи")
    public void goToUsers(){
        mainPage.btnUsers.click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Top posters | Пользователи | Lanit education", "Test goToUsers not complited");
        System.out.println("Test goToUsers complited");

    }

    @И("выполняем поиск пользователя из предусловия")
    public void searchForUserFromPrecondition() {
        mainPage.btnSearch.click();
        mainPage.searchField.sendKeys("kuvaldinvitaly");
        mainPage.btnShowFullSearchResults.click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Поиск по сайту | Lanit education", "Test searchForUserFromPrecondition not complited");
        System.out.println("Test searchForUserFromPrecondition complited");
    }

    @Тогда("пробуем выполнить регистрацию с пустыми полями")
    public void registratedZeroField() throws InterruptedException {
        mainPage.btnRegistration.click();
        mainPage.btnRegistration.isDisplayed();
        while (! mainPage.btnRegistration.isDisplayed()){
            Thread.sleep(1000);
        }
       mainPage.clickToNameUser.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait webDriverWait1 = new WebDriverWait(driver, 10);
        webDriverWait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"help-block errors\"]")));
        checkFieldsZero("id_username");
        checkFieldsZero("id_email");
        checkFieldsZero("id_password");
        System.out.println("Test registratedZeroField complited ");
    }

    private void checkFieldsZero(String id){
        WebElement field = driver.findElement(By.id(id));
        WebElement fieldParrent = field.findElement(By.xpath(".."));
        String fielsError = fieldParrent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(fielsError, "Это поле обязательно.", "Test registratedZeroField not complited");
    }


    @Тогда("пробуем выполнить регистрацию существующем пользователем")
    public void registratedExistingUser() {
        fillFields("id_username","kuvaldinvitaly");
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        String email = "kuvaldinvitaly" + n + "@gmail.com";
        fillFields("id_email",email);
        fillFields("id_password", n + "veryhardpassword");
        mainPage.clickToNameUser.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"help-block errors\"]")));
        checkFieldUserName("id_username");
        mainPage.btnCancellation.click();
        System.out.println("Test registratedExistingUser complited");
    }

    private void fillFields(String id, String text){
        WebElement field = driver.findElement(By.id(id));
        field.sendKeys(text);
    }

    private void checkFieldUserName(String id)  {
        WebElement field = driver.findElement(By.id(id));
        WebElement fieldParrent = field.findElement(By.xpath(".."));
        String fielsError = fieldParrent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(fielsError, "Данное имя пользователя недоступно.", "Test registratedExistingUser not complited");
    }

    @И("пробуем зарегестрировать нового пользователя")
    public void redistratedNewUser(){
        mainPage.btnRegistration.click();
        Random random = new Random();
        int n = random.nextInt(10000) - 1;
        fillFields("id_username","TestUser" + n);
        String email = "newTestUser" + n + "@gmail.com";
        fillFields("id_email",email);
        fillFields("id_password", n + "veryhardpassword");
        mainPage.clickToNameUser.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"lead\"]")));
        String reloadPage = driver.findElement(By.xpath("//*[@class=\"lead\"]")).getText();
        Assert.assertTrue(reloadPage.contains("Вы вошли как"), "Test redistratedNewUser not complited");
        mainPage.btnReloadField.click();
        System.out.println("Test redistratedNewUser complited");
    }


    @Тогда("завершаем тест")
    public void theEnd(){
        driver.quit();
        System.out.println("Test close");
    }

}
