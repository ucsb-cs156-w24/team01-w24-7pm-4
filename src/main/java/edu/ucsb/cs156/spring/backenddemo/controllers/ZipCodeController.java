package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Zip Code info from  http://api.zippopotam.us/")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {

    @Autowired
    private ZipCodeQueryService ZipCodeQueryService;

    @Operation(summary = "Get info about a zipcode", description = "JSON return format documented here: https://api.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCodeInfo(
        @Parameter(name = "zipcode", example = "93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipCodeInfo: zipcode={}", zipcode);
        String result = ZipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }
}
