package CourierTest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.COURIER.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierAPI {

    protected final RandomCourier random = new RandomCourier();
    private Courier courier;
    private CourierClient courierClient;
    private CourierAssertions courierAssertions;
    int courierId;

    @Before
    @Step("Предусловия для создания курьера")
    public void setUp(){
        courierClient = new CourierClient();
        courier = random.random();
        courierAssertions = new CourierAssertions();
    }
    @After
    @Step("Удалить курьера")
    public void deleteCourier(){
        if(courierId > 0){
            courierClient.delete(courierId);
        }
    }

    @Test
    @DisplayName("Создать нового курьера")
    @Description("Курьера можно создать")
    public void courierCanBeCreated() {
        ValidatableResponse responseCreateCourier = courierClient.create(courier);
        courierAssertions.successfullyCreated(responseCreateCourier);
        CourierCreds courierCreds = CourierCreds.from(courier);
        ValidatableResponse responseLoginCourier = courierClient.login(courierCreds);
        courierId = responseLoginCourier.extract().path("id");
    }
    @Test
    @DisplayName("Создать курьера м пустым логином")
    @Description("Курьера нельзя создать без логина")
    public void courierCanNotBeCreatedWithoutLogin() {
        courier.setLogin(null);
        ValidatableResponse responseNullLogin = courierClient.create(courier);
        courierAssertions.failedCreation(responseNullLogin);
    }


    @Test
    @DisplayName("Создать курьера без пароля")
    @Description("Курьера нельзя создать без пароля")
    public void courierCanNotBeCreatedWithoutPassword() {
        courier.setPassword(null);
        ValidatableResponse responseNullPassword = courierClient.create(courier);
        courierAssertions.failedCreation(responseNullPassword);
    }
    @Test
    @DisplayName("Создать курьера без пароля и логина")
    @Description("Курьера нельзя создать без пароля и логина. ")
    public void courierCanNotBeCreatedWithoutLoginAndPassword() {
        courier.setLogin(null);
        courier.setPassword(null);
        ValidatableResponse responseNullFields = courierClient.create(courier);
        courierAssertions.failedCreation(responseNullFields);
    }
    @Test
    @DisplayName("Создать курьера с существующими данными")
    @Description("Создание курьера с существующими данными")
    public void courierCanNotBeCreatedWithExistingCreds() {
        courierClient.create(courier);
        ValidatableResponse responseCreateCourier = courierClient.create(courier);
        courierAssertions.userIsExistsFail(responseCreateCourier);
    }

}
