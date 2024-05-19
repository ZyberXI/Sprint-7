package Orders;

import constants.ApiEnum;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ApiEnum {
    public ValidatableResponse createNewOrder(OrderData orderData) {
        return given()
                .spec(getSpec())
                .when()
                .post(POST_ORDER_CREATE)
                .then();
    }

    public ValidatableResponse cancelNewOrder(int track) {
        return given()
                .spec(getSpec())
                .body(track)
                .when()
                .put(PUT_ORDER_CANCEL)
                .then();
    }

    public ValidatableResponse getAllOrdersList() {
        return given()
                .spec(getSpec())
                .when()
                .get(GET_ORDERS_LIST)
                .then();
    }

    public ValidatableResponse getAllOrdersListWithInvalidCourierId(int courierId) {
        return given()
                .spec(getSpec())
                .queryParam("courierId", courierId)
                .when()
                .get(GET_ORDERS_LIST)
                .then();
    }
}
