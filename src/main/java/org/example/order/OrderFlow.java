package org.example.order;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderFlow {
    public static final String ORDER = "api/v1/orders";
    public static final String CANCEL_ORDER = "api/v1/orders/cancel";
    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";

    @Step("Создать заказ")
    public ValidatableResponse createNewOrder(OrderCreate orderCreate){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(orderCreate)
                .when()
                .post(ORDER)
                .then();

    }

    @Step("Получить список заказов")
    public ValidatableResponse getOrderList(){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(ORDER)
                .then();
    }
    @Step("Отменить заказ")
    public ValidatableResponse cancelOrder(java.lang.Object track){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(track)
                .when()
                .put(CANCEL_ORDER)
                .then();
    }


}
