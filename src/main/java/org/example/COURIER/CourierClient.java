package org.example.COURIER;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient {
    protected final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    protected final String ROOT = "api/v1/courier";
    protected final String COURIER_LOGIN = "/api/v1/courier/login";
    protected final String COURIER_DELETE = "api/v1/courier/"; //удалить курьера api/v1/courier/:id

    @Step("Регистрация курьера")
    public ValidatableResponse create (Courier courier){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }
    @Step("Авторизация курьера")
    public ValidatableResponse login (CourierCreds courierCreds){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(courierCreds)
                .when()
                .post(COURIER_LOGIN)
                .then().log().all();
    }

    @Step("Удалить курьера")
    public ValidatableResponse delete(int courierId){
        String json = String.format("{\"id\": \"%d\"}" , courierId);
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(json)
                .when()
                .delete(COURIER_DELETE + courierId)
                .then().log().all();

    }

}
