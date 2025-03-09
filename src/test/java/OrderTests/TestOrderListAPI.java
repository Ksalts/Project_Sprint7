package OrderTests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.OrderFlow;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;

public class TestOrderListAPI {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Успешное получение списка заказов")
    public void getOrderList() {
        OrderFlow orderFlow = new OrderFlow();
        ValidatableResponse responseOrderList = orderFlow.getOrderList();
        responseOrderList.statusCode(200).and().body("orders", notNullValue());
    }

}
