package ru.praktikum_services.qa_scooter.orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderList {
    @Step("Валидация статус кода = 200. Проверка тела ответа orders не нулевое значение")
    public void getOrdersListWithOutCourierId(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
        int ordersCount = response.extract().path("orders.size()");
        boolean isHaveOrderId = false;

        for (int i = 0; i < ordersCount; i++) {
            int orderId = response.extract().path("orders["
                    + i
                    + "].id");
            if (orderId != 0) {
                isHaveOrderId = true;
                break;
            }
        }

        if (!isHaveOrderId) {
            throw new AssertionError("No");
        }
    }

    @Step("Валидация статус кода = 404. Проверка тела ответа message = Курьер с идентификатором {id курьера} не найден")
    public void getOrdersListWithInvalidCourierId(ValidatableResponse response, int courierId) {
        if (response.extract().statusCode() == 404) {
            response
                    .assertThat()
                    .body("message", equalTo("Курьер с идентификатором "
                            + courierId
                            + " не найден"));
        } else {
            throw new AssertionError("Unexpected response for invalid courier ID");
        }
    }
}
