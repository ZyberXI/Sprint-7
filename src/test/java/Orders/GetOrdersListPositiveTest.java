package Orders;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

@DisplayName("Get Orders List Positive Tests")
public class GetOrdersListPositiveTest {
    private OrderClient orderClient;
    private GetOrderList getOrderList = new GetOrderList();

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Get Orders List WithOut CourierId")
    @Description("Get orders list without courierId positive test")
    public void getOrderListWithOutCourierIdPositiveTest() {
        ValidatableResponse response = orderClient.getAllOrdersList();
        Allure.step("Получение списка заказов, количесвто заказов = " + response.extract().path("orders.size()"));
        GetOrderList getOrderList = new GetOrderList();
        getOrderList.getOrdersListWithOutCourierId(response);
        Allure.step("Проверка получения заказа без необзятельного атрибута courierId");
        int ordersCount = response.extract().path("orders.size()");
        Allure.step("Количесвто заказов = " + ordersCount);
    }

    @Test
    @DisplayName("Get Orders List With Invalid CourierId")
    @Description("Get orders list with invalid query param - courierId positive test")
    public void getOrderListWithInvalidCourierIdPositiveTest() {
        int courierId = 100500;
        ValidatableResponse response = orderClient.getAllOrdersListWithInvalidCourierId(courierId);
        Allure.step("Получение списка заказов с невалидным courierId = " + courierId);
        getOrderList.getOrdersListWithInvalidCourierId(response, courierId);
        Allure.step("Проверка ответа на запрос получение списка заказов с невалидным courierId = " + courierId);
    }
}