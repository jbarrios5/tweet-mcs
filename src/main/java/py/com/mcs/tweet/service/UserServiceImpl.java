package py.com.mcs.tweet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.kytech.mcs.kytech.utils.EncryptUtil;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.bean.user.req.UserPostReq;
import py.com.mcs.tweet.bean.user.req.UserPutReq;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;
import py.com.mcs.tweet.bean.user.resp.UserGetResData;
import py.com.mcs.tweet.bean.user.resp.UserPostRes;
import py.com.mcs.tweet.bean.user.resp.UserPostResData;
import py.com.mcs.tweet.bean.user.resp.UserPutResData;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.dao.user.UserDAO;
import py.com.mcs.tweet.dto.UserDTO;
import py.com.mcs.tweet.service.security.JwtService;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserGetResData getUsers(String accessToken) {
        UserGetResData result = new UserGetResData();

        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "getUsers:Before check AT", null);
        UserGetRes userAT = jwtService.isTokenValid(accessToken);
        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "getUsers:After check AT", Objects.nonNull(userAT));

        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "getUsers:Before get user", null);
        List<UserGetRes> data = userDAO.getUsers();
        log.info(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "getUsers:After get user", data.size());
        result.setData(data);
        return result;
    }

    @Override
    public UserDTO getUserByUserNameWithPassword(String userName) {
        return userDAO.getUserByUserNameWithPassword(userName);
    }

    @Override
    public UserPutResData updateUser(String accessToken, UserPutReq req) {
        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "updateUser:Before check AT", null);
        UserGetRes userAT = jwtService.isTokenValid(accessToken);
        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "updateUser:After check AT", Objects.nonNull(userAT));


        return null;
    }

    @Override
    public UserPostResData addUser(UserPostReq req) {
        UserPostResData result = new UserPostResData();
        UserPostRes data = new UserPostRes();

        //encrypt pasword
        req.setPassword(EncryptUtil.passwordEnconde(req.getPassword()));

        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "addUser:Before add user", req);
        boolean isUserAdded = userDAO.addUser(req);
        log.debug(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "addUser:After add user", isUserAdded);

        data.setIsUserInserted(isUserAdded);
        data.setMessage("User inserted successfully.");
        result.setData(data);
        return result;
    }

}
