package py.com.mcs.tweet.service.auth;


import py.com.mcs.tweet.bean.auth.req.AuthPostReq;
import py.com.mcs.tweet.bean.auth.resp.AuthPostResData;

public interface AuthService {
    AuthPostResData login(AuthPostReq req);

    Boolean isJWTValid(String accessToken);
}
