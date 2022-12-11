package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    @FindBy(how = How.XPATH, using = ".//button [text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.CSS, using = ".Auth_login__3hAey > h2")
    protected SelenideElement loginHeader;

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement regLink;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement resetPasswordLink;

    @Step("Установка значения в поле вода 'Email'")
    public LoginPage setEmail(String email) {
        inputEmail.shouldBe(empty).click();
        inputEmail.setValue(email);
        return this;
    }

    @Step("Установка значения в поле вода 'Пароль'")
    public LoginPage setPassword(String password) {
        inputPassword.click();
        inputPassword.setValue(password);
        return this;
    }

    @Step("Клик на кнопку 'Войти'")
    public MainPage loginButtonClick() {
        loginButton.click();
        return page(MainPage.class);
    }

    @Step("Клик на ссылку 'Зарегистрироваться'")
    public RegistrationPage regLinkClick() {
        regLink.click();
        return page(RegistrationPage.class);
    }

    @Step("Клик на ссылку 'Войти'")
    public LoginPage loginLinkClick() {
        loginLink.click();
        return this;
    }

    @Step("Клик на ссылку 'Восстановить пароль'")
    public LoginPage resetPasswordLinkClick() {
        resetPasswordLink.scrollIntoView(true);
        resetPasswordLink.click();
        return this;
    }

    @Step("Логин в хидере")
    public void loginPage() {
        loginHeader.shouldHave(Condition.text("Вход"));
    }

    @Step("Проверка видимости авторизации")
    public void checkAuthorised() {
        loginButton.shouldBe(Condition.visible);
    }
}