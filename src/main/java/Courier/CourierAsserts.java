package Courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

public class CourierAsserts {
    public void assertCourierCreatePositiveNewCourier(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }

    public void assertCourierCreateNegativeAlreadyExist(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    public void assertCreateCourierNegativeInvalidBody(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public void assertNewCourierAutentificationCorrect(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(200)
                .body("id", greaterThan(0));
    }

    public void assertNewCourierAutentificationWithOutMandatoryParams(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    public void assertNewCourierAutentificationWithNonExistParam(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
