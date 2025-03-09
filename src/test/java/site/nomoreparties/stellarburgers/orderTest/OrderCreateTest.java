package site.nomoreparties.stellarburgers.orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.order.OrderResult;
import site.nomoreparties.stellarburgers.order.OrderSteps;
import site.nomoreparties.stellarburgers.user.UserRandom;
import site.nomoreparties.stellarburgers.user.UserSteps;

public class OrderCreateTest {
    private OrderSteps orderSteps;
    private OrderResult orderResult;
    private UserSteps userSteps;
    private UserRandom userRandom;
    private ValidatableResponse response;

    @Before
    @Step("Создание тестовых данных пользователя")
    public void setUp() {
    orderSteps = new OrderSteps();
    userSteps = new UserSteps();
    orderResult = new OrderResult();
    userRandom = new UserRandom();
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Проверяем, что заказ можно создать")
    public void orderCreateWithOutAuthorization() {
        ValidatableResponse validatableResponse = orderSteps.orderCreateWithOutAuthorization();
        orderResult.orderCreateSuccess(validatableResponse);
    }

    @Test
    @DisplayName("Создание заказа c авторизацией и с ингредиентами")
    @Description("Проверяем, что заказ можно создать")
    public void orderCreateWithAuthorization() {
        String accessToken = userRandom.userGetAccessToken();
        response = userSteps.userDelete(StringUtils.substringAfter(accessToken, ""));

        ValidatableResponse validatableResponse = orderSteps.orderCreateWithAuthorization(accessToken);
        orderResult.orderCreateSuccess(validatableResponse);
    }

    @Test
    @DisplayName("Создание заказа без авторизации и без ингредиентов")
    @Description("Проверяем, что заказ нельзя создать, вернётся ошибка 400")
    public void orderCreateWithOutIngredient() {
        ValidatableResponse validatableResponse = orderSteps.orderCreateWithOutIngredients();
        orderResult.orderCreateWithOutIngredients(validatableResponse);
    }

    @Test
    @DisplayName("Создание заказа без авторизации и с неверным хэшом ингредиента")
    @Description("Проверяем, что заказ нельзя создать, вернётся ошибка 500")
    public void orderCreateInvalidHashIngredients() {
        ValidatableResponse validatableResponse = orderSteps.orderCreateWithOutAuthorizationInvalidHash();
        orderResult.orderCreateInvalidHashIngredients(validatableResponse);
    }
}
