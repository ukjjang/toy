package com.jinuk.toy.util.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secretKey: String,
) {
    private val validityInMilliseconds = 3600000 * 24 // 24시간 유효
    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun createToken(username: String): String {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
            .setClaims(
                Jwts.claims().setSubject(username),
            )
            .setIssuedAt(now) // 토큰 발급 시간
            .setExpiration(validity) // 토큰 만료 시간
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    // JWT 토큰에서 사용자 이름 추출
    private fun getUsername(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    // 요청 헤더에서 JWT 토큰 추출
    fun resolveToken(bearerToken: String): String? {
        return if (bearerToken.startsWith("Bearer ")) {
            val jwtToken = bearerToken.substring(7) // "Bearer " 부분 제거
            return getUsername(jwtToken)
        } else {
            null
        }
    }
}
