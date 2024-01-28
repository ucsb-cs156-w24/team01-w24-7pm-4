package edu.ucsb.cs156.spring.backenddemo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;

import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

@RestClientTest(TidesQueryService.class)
public class TidesQueryServiceTests {
    
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private TidesQueryService tidesQueryService; 

    @Test
    public void test_getJSON() {
        String beginDate = "20240122";
        String endDate = "20240124";
        String station = "9411340"; 
        String expectedURL = TidesQueryService.ENDPOINT
            .replace("{beginDate}",beginDate)
            .replace( "{endDate}", endDate)
            .replace("{station}", station);
        
        String fakeJsonResult = "{ \"fake\" : \"result\" }";
        this.mockRestServiceServer.expect(requestTo(expectedURL))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andRespond(withSuccess(fakeJsonResult, MediaType.APPLICATION_JSON));

        String actualJsonResult = tidesQueryService.getJSON(beginDate, endDate, station); 
        assertEquals(fakeJsonResult, actualJsonResult);
    }

}
