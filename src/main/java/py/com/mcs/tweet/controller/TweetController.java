package py.com.mcs.tweet.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.com.mcs.tweet.bean.tweet.req.FollowerPostResData;
import py.com.mcs.tweet.bean.tweet.req.TweetPostReqData;
import py.com.mcs.tweet.bean.tweet.resp.TweetGetResData;
import py.com.mcs.tweet.bean.tweet.resp.TweetPostResData;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.exceptions.TweetException;
import py.com.mcs.tweet.service.tweet.TweetService;

import javax.validation.Valid;

@RequestMapping("tweet/${version}")
@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @Operation(description = "Add a new tweet")
    @PostMapping(value = "/")
    public TweetPostResData addTweet(
            @RequestHeader(value = TweetConstant.ACCESS_TOKEN, required = true) String accessToken,
            @RequestHeader(value = TweetConstant.API_KEY, required = true) String apiKey,
            @RequestHeader(value = TweetConstant.CHANNEL, required = true) String channel,
            @RequestBody @Valid TweetPostReqData req) throws TweetException{
        return tweetService.addTweet(req.getData(),accessToken);
    }
    @Operation(description = "Add a new follower")
    @PostMapping(value = "/follower")
    public FollowerPostResData addTweet(
            @RequestHeader(value = TweetConstant.ACCESS_TOKEN, required = true) String accessToken,
            @RequestHeader(value = TweetConstant.API_KEY, required = true) String apiKey,
            @RequestHeader(value = TweetConstant.CHANNEL, required = true) String channel,
            @RequestParam (value = "followedId") Long followedId) throws TweetException{
        return tweetService.addFollow(accessToken,followedId);
    }
    @GetMapping("/")
    @Operation(description = "Get a user tweets")
    public TweetGetResData getTweetsByUserName( @RequestHeader(value = TweetConstant.ACCESS_TOKEN, required = true) String accessToken,
                                                @RequestHeader(value = TweetConstant.API_KEY, required = true) String apiKey,
                                                @RequestHeader(value = TweetConstant.CHANNEL, required = true) String channel,
                                                @RequestParam (value = "userName") String followedId) throws TweetException{
        return tweetService.getTweetsByUserName(accessToken,followedId);
    }
}
