package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import page.Pages;

public class BaseApiClient {
    public RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Pages.MAIN_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}