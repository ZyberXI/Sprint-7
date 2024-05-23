package ru.praktikum_services.qa_scooter.courier;

import io.restassured.response.ValidatableResponse;
import ru.praktikum_services.qa_scooter.constants.ApiEnum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierClient extends ApiEnum {

    public ValidatableResponse createNewCourier(CourierData courierData) {
        return given()
                .spec(getSpec())
                .body(courierData)
                .when()
                .post(POST_COURIER_CREATION)
                .then().log().all();
    }

    public ValidatableResponse courierLogin(CourierLoginData courierData) {
        return given()
                .spec(getSpec())
                .body(courierData)
                .when()
                .post(POST_COURIER_LOGIN)
                .then().log().all();
    }

    public ValidatableResponse courierRemove(int courierId) {
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE_COURIER + courierId)
                .then().log().all()
                .statusCode(200).body("ok", equalTo(true));
    }
}
