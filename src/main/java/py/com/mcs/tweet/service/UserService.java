package py.com.mcs.tweet.service;


import py.com.mcs.tweet.bean.user.req.UserPostReq;
import py.com.mcs.tweet.bean.user.req.UserPutReq;
import py.com.mcs.tweet.bean.user.resp.UserGetResData;
import py.com.mcs.tweet.bean.user.resp.UserPostResData;
import py.com.mcs.tweet.bean.user.resp.UserPutResData;
import py.com.mcs.tweet.dto.UserDTO;

public interface UserService {
    UserGetResData getUsers(String accessToken);

    UserPutResData updateUser(String accessToken, UserPutReq req);

    UserDTO getUserByUserNameWithPassword(String userName);

    UserPostResData addUser(UserPostReq user);

}
