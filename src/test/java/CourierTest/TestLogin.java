package CourierTest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.COURIER.*;
import org.example.COURIER.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestLogin {
    private RandomCourier randomCourier = new RandomCourier();
    private CourierCreds  courierCreds;
    private CourierClient courierClient;
    private Courier courier;
    CourierAssertions courierAssertions;
    int idCourier;

    @Before
    @Step("Предусловия для логина курьера")
    public void setUp() {
        courierClient = new CourierClient();
        courier = randomCourier.random();
        courierClient.create(courier);
        courierCreds = CourierCreds.from(courier);
        courierAssertions = new CourierAssertions();
    }
    @Test
    @DisplayName("Логин курьера c существующими данными")
    @Description("Можно войти с существующими данными")
    public void courierCanSuccessfullyLogin() {
        ValidatableResponse responseLoginCourier = courierClient.login(courierCreds);
        courierAssertions.succesfullyLoggedIn(responseLoginCourier);
        idCourier = responseLoginCourier.extract().path("id");
    }
    @Test
    @DisplayName("Логин курьера без заполнения логина")
    @Description("Попытка входа без заполнения логина. Проверка сообщения об ошибке")
    public void courierLoginUnsuccessfullyWithoutLogin() {
        CourierCreds credentialsWithoutLogin = new CourierCreds("", courier.getPassword()); // c null тесты виснут
        ValidatableResponse responseLoginErrorMessage = courierClient.login(credentialsWithoutLogin).statusCode(400);
        responseLoginErrorMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Попытка логина без пароля")
    @Description("Попытка логина без ввода пароля. Проверка сообщения об ошибке")
    public void courierLoginUnsuccessfullyWithoutPassword() {
        CourierCreds credentialsWithoutLogin = new CourierCreds(courier.getLogin(), "");
        ValidatableResponse responsePasswordErrorMessage = courierClient.login(credentialsWithoutLogin).statusCode(400);
        responsePasswordErrorMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Логин курьера без логина и пароля")
    @Description("Попытка входа без логина и пароля. Проверка сообщения об ошибке")
    public void courierLoginWithoutLoginAndPassword() {
        CourierCreds credentialsWithoutLoginAndPassword = new CourierCreds("", "");
        ValidatableResponse responseWithoutLoginAndPasswordMessage = courierClient.login(credentialsWithoutLoginAndPassword).statusCode(400);
        responseWithoutLoginAndPasswordMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Логин курьера с несуществующим логином")
    @Description("Попытка логина с несуществующим логином. Проверка сообщения об ошибке")
    public void courierLoginWithNotExistingLogin() {
        CourierCreds credentialsWithNotExistingLogin = new CourierCreds(RandomStringUtils.randomAlphanumeric(6), courier.getPassword());
        ValidatableResponse responseWithWithNotExistingLoginMessage = courierClient.login(credentialsWithNotExistingLogin).statusCode(404);
        responseWithWithNotExistingLoginMessage.assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
    @After
    @Step("Удалить курьера")
    public void deleteCourier() {
        if (idCourier != 0) {
            courierClient.delete(idCourier);
        }
    }

}
