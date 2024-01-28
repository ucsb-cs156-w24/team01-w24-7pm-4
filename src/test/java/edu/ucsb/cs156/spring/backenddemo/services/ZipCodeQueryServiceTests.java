package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

@RestClientTest(ZipCodeQueryService.class)
public class ZipCodeQueryServiceTests {
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ZipCodeQueryService ZipCodeQueryService;

    @Test
    public void test_getJSON() {
    String zipCode = "93106"; // Example ZIP code
    String expectedURL = "http://api.zippopotam.us/us/" + zipCode; // Corrected expected URL

    String fakeJsonResult = "{ \"fake\" : \"result\" }"; // Mock JSON response

    this.mockRestServiceServer.expect(requestTo(expectedURL)) // Use the corrected expected URL
        .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
        .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
        .andRespond(withSuccess(fakeJsonResult, MediaType.APPLICATION_JSON));

    String actualResult = ZipCodeQueryService.getJSON(zipCode);
    assertEquals(fakeJsonResult, actualResult);
}



}
