package utils;
import io.jsonwebtoken.*;
import java.util.Date;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Date;
import model.Accounts.AccountToken;


public class TokenHandler {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");


    public static AccountToken generateToken(String idAccount, String accountType) {

        String token = Jwts.builder()
                .setSubject(idAccount)
                .claim("accountType", accountType)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120)) // 2 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return new AccountToken(token, idAccount, accountType);
    }


    public static AccountToken decryptToken(String token) {
        try {

            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload();

            String idAccount = claims.getSubject(); // Get the idAccount (subject)
            String accountType = claims.get("accountType", String.class); // Extract accountType from claims


            return new AccountToken(token, idAccount, accountType);

        } catch (JwtException e) {
            System.out.println("Invalid or expired token.");
            return null;
        }
    }

    public static void main(String[] args) {

        String idAccount = "user123";
        String accountType = "Admin";
        AccountToken accountToken = generateToken(idAccount, accountType);
        System.out.println("Generated Token: " + accountToken.getToken());


        AccountToken decryptedToken = decryptToken(accountToken.getToken());
        if (decryptedToken != null) {
            System.out.println("Decrypted Token: " + decryptedToken.toString());
        }
    }
}