package py.com.mcs.tweet.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import py.com.kytech.mcs.kytech.utils.EncryptUtil;
import py.com.mcs.tweet.bean.auth.req.AuthPostReq;
import py.com.mcs.tweet.bean.auth.resp.AuthPostRes;
import py.com.mcs.tweet.bean.auth.resp.AuthPostResData;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.dao.auth.AuthDAO;
import py.com.mcs.tweet.dto.UserDTO;
import py.com.mcs.tweet.exceptions.TweetException;
import py.com.mcs.tweet.service.UserService;
import py.com.mcs.tweet.service.security.JwtService;

import java.util.Objects;


@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AuthDAO authDAO;
    @Autowired
    private JwtService jwtService;

    @Override
    public AuthPostResData login(AuthPostReq req) {
        log.info(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "login: Starting login userName=", req.getUserName());
        AuthPostResData result = new AuthPostResData();
        AuthPostRes data = new AuthPostRes();

        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "login: Before getting user with userName", req.getUserName());
        UserDTO user = userService.getUserByUserNameWithPassword(req.getUserName());
        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "login: After getting user with userName", user);

        if (!EncryptUtil.isPasswordMatch(req.getPassword(), user.getPasswordHash()))
            throw new TweetException("Credenciales invalidas", HttpStatus.UNAUTHORIZED);

        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "login: Before generating jwt", false);
        String jwt = jwtService.generateToken(buildUserGetRes(user));
        log.info(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "login: After generating jwt", Objects.nonNull(jwt));


        data.setAccessToken(jwt);
        result.setData(data);
        return result;
    }

    @Override
    public Boolean isJWTValid(String accessToken) {
        log.info(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "isJWTValid: Starting verify jwt=", Objects.nonNull(accessToken));

        jwtService.isTokenValid(accessToken);

        return Boolean.TRUE;
    }

    private UserGetRes buildUserGetRes(UserDTO user) {
        UserGetRes res = new UserGetRes();
        res.setId(user.getId());
        res.setFullName(user.getFullName());
        res.setEmail(user.getEmail());
        res.setUserName(user.getUserName());
        return res;
    }
}
