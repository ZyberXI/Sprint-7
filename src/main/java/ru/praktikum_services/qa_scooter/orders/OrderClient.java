package ru.praktikum_services.qa_scooter.orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_services.qa_scooter.constants.ApiEnum;

import static io.restassured.RestAssured.given;

public class OrderClient extends ApiEnum {
    @Step("Создание нового заказа")
    public ValidatableResponse createNewOrder(OrderData orderData) {
        return given()
                .spec(getSpec())
                .when()
                .post(POST_ORDER_CREATE)
                .then();
    }

    @Step("Откат заказа")
    public ValidatableResponse cancelNewOrder(int track) {
        return given()
                .spec(getSpec())
                .body(track)
                .when()
                .put(PUT_ORDER_CANCEL)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getAllOrdersList() {
        return given()
                .spec(getSpec())
                .when()
                .get(GET_ORDERS_LIST)
                .then();
    }

    @Step("Получение списка заказов по невалидному courierId")
    public ValidatableResponse getAllOrdersListWithInvalidCourierId(int courierId) {
        return given()
                .spec(getSpec())
                .queryParam("courierId", courierId)
                .when()
                .get(GET_ORDERS_LIST)
                .then();
    }
}
