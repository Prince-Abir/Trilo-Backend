package com.tshirtmart.trilo.services;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tshirtmart.trilo.dto.LoginRequestDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String secretKey = "";

	public JwtService() {
		try {

			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			this.secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
	}

	public String generateToken(LoginRequestDTO user) {

		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUserEmail())
				.setIssuer("Abir")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.signWith(generateKey())
				.compact();

	}

	public String getSecretKey() {
		return secretKey;
	}

	private SecretKey generateKey() {
		byte[] decode = Decoders.BASE64.decode(getSecretKey());
		return Keys.hmacShaKeyFor(decode);
	}



	public String extractUserEmail(String token) {

		return extractClaims(token,Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims,T> claimResolver) {
		Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);

	}

    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(generateKey()).build();
        return parser.parseClaimsJws(token).getBody();
    }

	public boolean validateToken(String token, UserDetails userDetails) {

		String userEmail = extractUserEmail(token);

		return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

	private boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date(System.currentTimeMillis()));

	}

	private Date extractExpiration(String token) {
		return extractClaims(token,Claims::getExpiration);
	}

}
