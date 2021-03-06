package ru.lanit.atschool.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage {

    /**
     * Метод открывает браузер на заданной странице
     * страница задается в файле config.properties.
     */
    public void openPage(String url) {
        driver.get(url);
        logger.info("Выполнен вход на страницу: " + url);
    }

    /**
     * Вкладка "Категории"
     */
    @FindBy(xpath = "//a[contains(text(),'Категории')]")
    public WebElement btnCategiries;

    /**
     * Вкладка "Пользователи"
     */
    @FindBy(xpath = "//a[contains(text(),'Пользователи')]")
    public WebElement btnUsers;

    /**
     * Кнопка "поиск по сайту"
     */
    @FindBy(xpath = "//*[@href=\"/search/\"]")
    public WebElement btnSearch;

    /**
     * Текстовое поле поиска
     */
    @FindBy(xpath = "//*[@class=\"form-control\"]")
    public WebElement searchField;

    /**
     * Кнопка "Показать полные результаты"
     */
    @FindBy(xpath = "//*[@class=\"dropdown-search-footer\"]")
    public WebElement btnShowFullSearchResults;

    /**
     * Кнопка "Регистрация"
     */
    @FindBy(xpath = "//*[@class=\"btn navbar-btn btn-primary btn-register\"]")
    public WebElement btnRegistration;

    /**
     * Кнопка "Зарегистрировать аккаунт"
     */
    @FindBy (xpath = "//*[@class=\"btn btn-primary\"]")
    public WebElement clickToNameUser;

    /**
     * Кнопка отмена
     */
    @FindBy (xpath = "//button[contains(text(),'Отмена')]")
    public WebElement btnCancellation;

    /**
     * Кнопка "Обновить страницу"
     */
    @FindBy (xpath = "//button[contains(text(),'Обновите страницу')]")
    public WebElement btnReloadField;

    /**
     * Текстовое поле "Пользователь"
     */
    @FindBy (id = "id_username")
    public WebElement usernameTextField;


}

