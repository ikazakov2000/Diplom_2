package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.config.RequestSpec;
import site.nomoreparties.stellarburgers.ingredients.IngredientList;

import java.util.Map;

import static site.nomoreparties.stellarburgers.constantsApi.ApiEndPoints.*;
import static site.nomoreparties.stellarburgers.ingredients.IngredientList.ingredients;
import static site.nomoreparties.stellarburgers.ingredients.IngredientList.invalidKey;

public class OrderSteps extends RequestSpec {
    private IngredientList ingredientList;

    @Step("Создание заказа без авторизации /api/orders")
    public ValidatableResponse orderCreateWithOutAuthorization() {
        return requestSpec()
                .body(Map.of("ingredients", ingredients()))
                .when()
                .post(ORDER_CREATE_POST)
                .then();
    }

    @Step("Создание заказа без авторизации /api/orders")
    public ValidatableResponse orderCreateWithOutAuthorizationInvalidHash() {
        return requestSpec()
                .body(Map.of("ingredients", invalidKey))
                .when()
                .post(ORDER_CREATE_POST)
                .then();
    }

    @Step("Создание заказа без ингредиентов /api/orders")
    public ValidatableResponse orderCreateWithOutIngredients() {
        return requestSpec()
                .body(Map.of("ingredients", ""))
                .when()
                .post(ORDER_CREATE_POST)
                .then();
    }

    @Step("Создание заказа c авторизацией /api/orders")
    public ValidatableResponse orderCreateWithAuthorization(String accessToken) {
        return requestSpec()
                .header("Authorization", accessToken)
                .body(Map.of("ingredients", ingredients()))
                .when()
                .post(ORDER_CREATE_POST)
                .then();
    }

    @Step("Получение заказа без авторизации /api/orders")
    public ValidatableResponse orderReceiptWithOutAuthorization() {
        return requestSpec()
                .when()
                .get(USER_RECEIPT_ORDERS_GET)
                .then();
    }

    @Step("Получение заказа c авторизацией /api/orders")
    public ValidatableResponse orderReceiptWithAuthorization(String accessToken) {
        return requestSpec()
                .header("Authorization", accessToken)
                .when()
                .get(USER_RECEIPT_ORDERS_GET)
                .then();
    }
}
