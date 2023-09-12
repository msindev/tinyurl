package net.penguincoders.tinyurl.service;

import net.penguincoders.tinyurl.dao.CounterDao;
import net.penguincoders.tinyurl.model.Url;
import net.penguincoders.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private CounterDao counterDao;

    @Autowired
    private UrlRepository urlRepository;

    public String convertToShortUrl(String url) {
        String tinyUrl = getUrlIfExists(url);
        if (tinyUrl != null) {
            return tinyUrl;
        }

        long counter = counterDao.getCounter();
        counterDao.setCounter(counter + 1);
        String newTinyUrl = "http://tinyurl.com/" + convertNumberToBase62(counter);

        Url newUrl = new Url();
        newUrl.setUrl(url);
        newUrl.setTinyUrl(newTinyUrl);
        urlRepository.save(newUrl);

        return newTinyUrl;
    }

    public String getUrlIfExists(String url) {
        Optional<Url> existingUrl = urlRepository.findByUrl(url);
        return existingUrl.map(Url::getTinyUrl).orElse(null);
    }

    public String convertNumberToBase62(long num) {
        String base62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            result.insert(0, base62.charAt((int) (num % 62)));
            num /= 62;
        }
        return result.toString();
    }
}
