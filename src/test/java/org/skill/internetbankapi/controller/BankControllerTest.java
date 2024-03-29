package org.skill.internetbankapi.controller;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@WebMvcTest
public class BankControllerTest {
    @MockBean
    BankController bankController;
    @Test
    public void getApiResponseTest() throws Exception {
        String res= "{[]}";
        String apiUrl = "/api/{%s}?&userid=%d";
        Mockito.when(bankController
                .getRequest(
                        "getOperationList",
                        "17",
                        "155",
                        "15",
                        "21-03-2024",
                        "26-03-2024"))
                .thenReturn(res);
       }
}

