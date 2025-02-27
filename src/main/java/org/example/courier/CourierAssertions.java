package org.example.courier;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;



public class CourierAssertions {
    public void successfullyCreated(ValidatableResponse response){
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }
    public int succesfullyLoggedIn(ValidatableResponse response){
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }
    public void userIsExistsFail(ValidatableResponse response){
        response.assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    public void failedCreation(ValidatableResponse response){
        response.assertThat()
                .statusCode(400)
                .body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

}
