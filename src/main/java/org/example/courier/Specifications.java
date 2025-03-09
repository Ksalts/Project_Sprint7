package org.example.courier;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    protected RequestSpecification requestSpec(String Url){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }
}
