package ru.praktikum_services.qa_scooter.constants;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiEnum {
    public final static String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public final static String POST_COURIER_LOGIN = "/api/v1/courier/login";
    public final static String POST_COURIER_CREATION = "/api/v1/courier";
    public final static String DELETE_COURIER = "/api/v1/courier/";
    public final static String POST_ORDER_CREATE = "/api/v1/orders";
    public final static String PUT_ORDER_CANCEL = "/api/v1/orders/cancel";
    public final static String GET_ORDERS_LIST = "/api/v1/orders";

    public RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
