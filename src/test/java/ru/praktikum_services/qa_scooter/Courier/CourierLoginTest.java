package ru.praktikum_services.qa_scooter.Courier;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.courier.*;

public class CourierLoginTest {
    private CourierData courierData;
    private CourierAsserts courierAsserts;
    private CourierClient courierClient;
    private CourierLoginData courierLoginData;

    private int courierId = 0;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courierData = CourierDataGenerator.random();
        courierClient.createNewCourier(courierData);
        courierLoginData = CourierLoginData.from(courierData);
        ValidatableResponse response = courierClient.courierLogin(courierLoginData);
        courierId = response.extract().path("id");
        courierAsserts = new CourierAsserts();
        Allure.step("Подготовлены данные курьера: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName()
                + " идентификатор курьера = " + courierId);

    }

    @After
    public void deleteData() {
        if (courierId != 0) {
            courierClient.courierRemove(courierId);
            Allure.step("Удаление courierId = " + courierId);
        }
        else {
            Allure.step("Удалять курьера не требуется");
        }
    }

    @Test
    @DisplayName("New Courier Autentification Positive Test")
    @Description("Check autentification new courier with courierId")
    public void checkNewCourierAutentificationPositiveTest() {
        ValidatableResponse response = courierClient.courierLogin(courierLoginData);
        courierId = response.extract().path("id");
        Allure.step("Идентификатор курьера = " + courierId);
        courierAsserts.assertNewCourierAutentificationCorrect(response);
        Allure.step("Проверка аутентификации курьера с идентификатором = " + courierId);
    }

    @Test
    @DisplayName("New Courier Autentification WithOut All Mandatory Params Negative Test")
    @Description("Check autentification new courier without all mandatory params in body request")
    public void checkNewCourierAutentificationWithOutAllMandatoryParamsNegativeTest() {
        courierLoginData.setLogin("");
        courierLoginData.setPassword("");
        ValidatableResponse response = courierClient.courierLogin(courierLoginData);
        Allure.step("Подготовлен курьер с данными: login = "
                + courierLoginData.getLogin()
                + " password = "
                + courierLoginData.getPassword());
        courierAsserts.assertNewCourierAutentificationWithOutMandatoryParams(response);
        Allure.step("Проверка аутентификации курьера с пустыми значениями");
    }

    @Test
    @DisplayName("New Courier Autentification WithOut Login Param Negative Test")
    @Description("Check autentification new courier without login mandatory param in body request")
    public void checkNewCourierAutentificationWithOutLoginNegativeTest() {
        courierLoginData.setLogin("");
        ValidatableResponse response = courierClient.courierLogin(new CourierLoginData(courierLoginData.getLogin(), courierLoginData.getPassword()));
        Allure.step("Подготовлен курьер с данными: login = "
                + courierLoginData.getLogin()
                + " password = "
                + courierLoginData.getPassword());
        courierAsserts.assertNewCourierAutentificationWithOutMandatoryParams(response);
        Allure.step("Проверка аутентификации курьера с пустыми логином");
    }

    @Test
    @DisplayName("New Courier Autentification WithOut Password Param Negative Test")
    @Description("Check autentification new courier without password mandatory param in body request")
    public void checkNewCourierAutentificationWithOutPasswordNegativeTest() {
        courierLoginData.setPassword("");
        ValidatableResponse response = courierClient.courierLogin(new CourierLoginData(courierLoginData.getLogin(),courierLoginData.getPassword()));
        Allure.step("Подготовлен курьер с данными: login = "
                + courierLoginData.getLogin()
                + " password = "
                + courierLoginData.getPassword());
        courierAsserts.assertNewCourierAutentificationWithOutMandatoryParams(response);
        Allure.step("Проверка аутентификации курьера с пустыми паролем");
    }

    @Test
    @DisplayName("New Courier Autentification With Invalid Params Negative Test")
    @Description("Check autentification new courier with invalid login and password params in body request")
    // В идеале данный тест должен иметь другую проверку, а именно - запрос всех возможных логинов/паролей, а уже потом чек на non-exist
    public void checkNewCourierAutentificationWithInvalidParams() {
        courierLoginData.setLogin("fakeLogin");
        courierLoginData.setPassword("fakePassword");
        ValidatableResponse response = courierClient.courierLogin(courierLoginData);
        Allure.step("Подготовлен курьер с данными: login = "
                + courierLoginData.getLogin()
                + " password = "
                + courierLoginData.getPassword());
        courierAsserts.assertNewCourierAutentificationWithNonExistParam(response);
        Allure.step("Проверка аутентификации курьера с несущесвтующим логином и паролем");
    }
}
