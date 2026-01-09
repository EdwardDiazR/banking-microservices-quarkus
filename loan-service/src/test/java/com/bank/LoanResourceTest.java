package com.bank;

import com.bank.loan.dto.LoanDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

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


}
