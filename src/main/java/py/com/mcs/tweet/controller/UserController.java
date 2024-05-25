package py.com.mcs.tweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.mcs.tweet.bean.user.req.UserPostReqData;
import py.com.mcs.tweet.bean.user.req.UserPutReqData;
import py.com.mcs.tweet.bean.user.resp.UserGetResData;
import py.com.mcs.tweet.bean.user.resp.UserPostResData;
import py.com.mcs.tweet.bean.user.resp.UserPutResData;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.service.UserService;

import javax.validation.Valid;

@RequestMapping("user/${version}")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public UserGetResData getUsers(@RequestHeader(value = TweetConstant.ACCESS_TOKEN) String accessToken,
                                   @RequestHeader(value = TweetConstant.API_KEY) String apiKey) {
        return userService.getUsers(accessToken);
    }

    @PutMapping("/")
    public UserPutResData updateUser(@RequestHeader(value = TweetConstant.ACCESS_TOKEN) String accessToken,
                                     @RequestHeader(value = TweetConstant.API_KEY) String apiKey,
                                     @RequestBody UserPutReqData req) {
        return userService.updateUser(accessToken, req.getData());
    }

    @PostMapping("/")
    public UserPostResData addUser(@RequestBody @Valid UserPostReqData req,
                                   @RequestHeader(value = TweetConstant.CHANNEL) String channel,
                                   @RequestHeader(value = TweetConstant.API_KEY) String apiKey) {
        return userService.addUser(req.getData());
    }
}
