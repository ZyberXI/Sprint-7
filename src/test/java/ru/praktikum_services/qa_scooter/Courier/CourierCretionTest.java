package ru.praktikum_services.qa_scooter.Courier;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.courier.*;

public class CourierCretionTest {

    private CourierData courierData;
    private CourierAsserts courierAsserts;
    private CourierClient courierClient;
    private int courierId = 0;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courierData = CourierDataGenerator.random();
        courierAsserts = new CourierAsserts();
        Allure.step("Подготовлены данные курьера: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName());
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
    @DisplayName("Create New Courier Positive Test")
    @Description("New courier creation with full validaliable body")
    public void createNewCourierPositiveTest() {
        ValidatableResponse response = courierClient.createNewCourier(courierData);
        CourierLoginData courierLoginData = CourierLoginData.from(courierData);
        Allure.step("Выполнение аутентификации курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword());
        courierId = courierClient.courierLogin(courierLoginData).extract().path("id");
        Allure.step("Идентификатор курьера = " + courierId);
        courierAsserts.assertCourierCreatePositiveNewCourier(response);
    }

    @Test
    @DisplayName("Create New Duplicated Courier Negative Test")
    @Description("New courier creation which already exist")
    public void createNewCourierAlreadyExistNegativeTest() {
        courierClient.createNewCourier(courierData);
        Allure.step("Создание курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName());
        ValidatableResponse response = courierClient.createNewCourier(courierData);
        Allure.step("Повторное создание курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName());
        courierAsserts.assertCourierCreateNegativeAlreadyExist(response);
        Allure.step("Проверка создание курьера с одинаковыми данными");
        CourierLoginData courierLoginData = CourierLoginData.from(courierData);
        Allure.step("Выполнение аутентификации курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword());
        courierId = courierClient.courierLogin(courierLoginData).extract().path("id");
        Allure.step("Идентификатор курьера = " + courierId);
    }

    @Test
    @DisplayName("Create New Courier Without Login Negative Test")
    @Description("New corier creation without login in body request")
    public void createNewCourierWithoutLoginNegativeTest() {
        courierData.setLogin(null);
        Allure.step("Создание курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName());
        ValidatableResponse response = courierClient.createNewCourier(courierData);
        courierAsserts.assertCreateCourierNegativeInvalidBody(response);
        Allure.step("Проверка создание курьера без логина");
    }

    @Test
    @DisplayName("Create New Courier Without Password Negative Test")
    @Description("New courier creation without password in body request")
    public void createNewCourierWithoutPasswordNegativeTest() {
        courierData.setPassword(null);
        Allure.step("Создание курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName());
        ValidatableResponse response = courierClient.createNewCourier(courierData);
        courierAsserts.assertCreateCourierNegativeInvalidBody(response);
        Allure.step("Проверка создание курьера без пароля");
    }

    @Test
    @DisplayName("Create New Courier Without Login And FirstName Negative Test")
    @Description("New courier creation without login and firstName in body request")
    public void createNewCourierWithoutLoginAndFirstNameNegativeTest() {
        courierData.setLogin(null);
        courierData.setFirstName(null);
        Allure.step("Создание курьера с данными: login = "
                + courierData.getLogin()
                + " password = "
                + courierData.getPassword()
                + " firstName = "
                + courierData.getFirstName());
        ValidatableResponse response = courierClient.createNewCourier(courierData);
        courierAsserts.assertCreateCourierNegativeInvalidBody(response);
        Allure.step("Проверка создание курьера без логина и имя");
    }
}
