package py.com.mcs.tweet.service.tweet;

import py.com.mcs.tweet.bean.tweet.req.FollowerPostResData;
import py.com.mcs.tweet.bean.tweet.req.TweetPostReq;
import py.com.mcs.tweet.bean.tweet.resp.TweetPostResData;

public interface TweetService {
    TweetPostResData addTweet(TweetPostReq req,String accessToken);

    FollowerPostResData addFollow(String accessToken,Long followedId);

}
