package py.com.mcs.tweet.dao.tweet;

import py.com.mcs.tweet.bean.tweet.resp.TweetGetRes;
import py.com.mcs.tweet.dto.FollowerDTO;

import java.util.List;

public interface TweetDAO {

    int addTweet(Long userId,String content);

    int addFollower(Long followedId,Long followerId);

    List<TweetGetRes> getTweets(String userName);
}
