package com.tonnybunny.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${file.local.dbpath}")
    String dbpath;

    @GetMapping("/envPath")
    public ResponseEntity<Object> getEnvPath() {
        return new ResponseEntity<>(dbpath, HttpStatus.OK);
    }
}
