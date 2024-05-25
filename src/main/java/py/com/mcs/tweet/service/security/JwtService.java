package py.com.mcs.tweet.service.security;


import py.com.mcs.tweet.bean.user.resp.UserGetRes;

public interface JwtService {
    String extractUserDocument(String token);

    public String generateToken(UserGetRes user);

    UserGetRes isTokenValid(String token);
}
