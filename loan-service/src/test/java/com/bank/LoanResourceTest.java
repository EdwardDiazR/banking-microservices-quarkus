package com.bank;

import com.bank.loan.dto.LoanDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LoanResourceTest {

    @Test
    void testGetLoanByIdTest(){
       LoanDTO loanDTO = given().when().get("/loan/10020")
                .then()
                .statusCode(200)
                .extract()
                .body().as(LoanDTO.class);
        Assertions.assertEquals("100120",loanDTO.loanNumber());
    }

    @Test
    void testChangePaymentDate(){
        given().when().patch("/loan/change-payment-date")
                .then()
                .statusCode(200)
                .body(is("Date changed successfully"));
    }


}
