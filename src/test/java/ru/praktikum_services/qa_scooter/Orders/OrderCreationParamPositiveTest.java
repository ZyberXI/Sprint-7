package ru.praktikum_services.qa_scooter.Orders;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.orders.OrderClient;
import ru.praktikum_services.qa_scooter.orders.OrderData;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@DisplayName("New Orders List Create Positive Test")
@RunWith(Parameterized.class)
public class OrderCreationParamPositiveTest {
    private final List<String> color;
    private OrderClient orderClient;
    private int track;

    public OrderCreationParamPositiveTest(List<String> color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @After
    public void deleteData() {
        orderClient.cancelNewOrder(track);
        Allure.step("Удаление заказа с идентификатором track = " + track);
    }

    @Parameterized.Parameters(name = "{index}: Color={0}")
    public static Object[][] selectDifferentScooterColor() {
        return new Object[][]{
                {List.of("GREY","BLACK")},
                {List.of("GREY")},
                {List.of("BLACK")},
                {List.of()}
        };
    }
    @Test
    @DisplayName("New Order Create")
    @Description("Creation new order with all data")
    public void createNewOrderListWithDiffColorsPositiveTest() {
        Allure.parameter("Color", color.toString());
        OrderData orderData = new OrderData(color);
        ValidatableResponse response = orderClient.createNewOrder(orderData);
        Allure.step("Создание нового заказа c данными: " +
                "\n" + "firstName = " + orderData.getFirstName() +
                "\n" + "lastName = " + orderData.getLastName() +
                "\n" + "address = " + orderData.getAddress() +
                "\n" + "metroStation = " + orderData.getMetroStation() +
                "\n\r phone = " + orderData.getPhone() +
                "\n\r rentTime = " + orderData.getRentTime() +
                "\n\r deliveryDate = " + orderData.getDeliveryDate() +
                "\n\r comment = " + orderData.getComment() +
                "\n\r color = " + orderData.getColor()
        );
        track = response.extract().path("track");
        Allure.step("Идентификатор нового заказа track = " + track);
        response.assertThat().statusCode(201).body("track", is(notNullValue()));
        Allure.step("Проверка что новый заказ создан");
    }
}
