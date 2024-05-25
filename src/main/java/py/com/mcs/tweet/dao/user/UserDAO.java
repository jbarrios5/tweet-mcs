package py.com.mcs.tweet.dao.user;

import py.com.mcs.tweet.bean.user.req.UserPostReq;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;
import py.com.mcs.tweet.dto.UserDTO;

import java.util.List;

public interface UserDAO {
    List<UserGetRes> getUsers();

    boolean addUser(UserPostReq user);

    UserDTO getUserByUserNameWithPassword(String userName);
}
