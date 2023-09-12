package net.penguincoders.tinyurl.controller;

import net.penguincoders.tinyurl.dto.UrlRequest;
import net.penguincoders.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/convert")
    public String convertToShortUrl(@RequestBody UrlRequest urlRequest) {
        String url = urlRequest.getUrl();
        return urlService.convertToShortUrl(url);
    }
}
