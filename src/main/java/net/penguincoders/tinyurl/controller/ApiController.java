package net.penguincoders.tinyurl.controller;

import net.penguincoders.tinyurl.dto.UrlRequest;
import net.penguincoders.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrlByShortUrl(shortUrl);

        if (originalUrl != null) {
            // Redirect to the original URL
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", originalUrl)
                    .build();
        } else {
            // Handle case where short URL doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Short URL not found");
        }
    }
}
