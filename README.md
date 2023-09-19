# TinyURL System Design

## Requirements

1. Given a URL, our service should generate a shorter and unique alias of it. This is called a short link. This link
   should be short enough to be easily copied and pasted into applications.
2. When users access a short link, our service should redirect them to the original link.

## Approach

We have 2 endpoints in our project -

1. **POST /api/convert** - This endpoint will take a long URL and convert it into a short URL.
2. **GET /api/{shortUrl}** - This endpoint will take a short URL and redirect the user to the original URL.

## Algorithm

- We will use a counter to generate a unique id for every URL. Whenever we see a new URL, we will increment our
  counter, convert the counter to a base 62 number, and use that as the unique id for the URL.
- To convert the counter to a base 62 number, we will use the following algorithm -

   ```java
   public String convertNumberToBase62(long num) {
   String base62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   StringBuilder result = new StringBuilder();
   while (num > 0) {
     result.insert(0, base62.charAt((int) (num % 62)));
     num /= 62;
   }
   return result.toString();
   }

- To convert the short URL back to the counter, we will use the following algorithm -
    - Check the DB for the shortURL and if present redirect the user to the original URL.
    - If the shortURL is not present in the DB, return a 404.