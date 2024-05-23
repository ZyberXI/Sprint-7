package ru.praktikum_services.qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

public class CourierAsserts {
    @Step("Валидация статус кода = 201. Проверка тела ответа ok = true")
    public void assertCourierCreatePositiveNewCourier(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }

    @Step("Валидация статус кода = 409. Проверка тела ответа message = Этот логин уже используется. Попробуйте другой.")
    public void assertCourierCreateNegativeAlreadyExist(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Валидация статус кода = 400. Проверка тела ответа message = Недостаточно данных для создания учетной записи")
    public void assertCreateCourierNegativeInvalidBody(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Валидация статус кода = 200. Проверка тела ответа id больше 0")
    public void assertNewCourierAutentificationCorrect(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(200)
                .body("id", greaterThan(0));
    }

    @Step("Валидация статус кода = 400. Проверка тела ответа message = Недостаточно данных для входа")
    public void assertNewCourierAutentificationWithOutMandatoryParams(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Валидация статус кода = 404. Проверка тела ответа message = Учетная запись не найдена")
    public void assertNewCourierAutentificationWithNonExistParam(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
