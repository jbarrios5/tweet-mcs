package py.com.mcs.tweet.service.tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.bean.tweet.req.FollowPostRes;
import py.com.mcs.tweet.bean.tweet.req.FollowerPostResData;
import py.com.mcs.tweet.bean.tweet.req.TweetPostReq;
import py.com.mcs.tweet.bean.tweet.resp.TweetPostRes;
import py.com.mcs.tweet.bean.tweet.resp.TweetPostResData;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.dao.tweet.TweetDAO;
import py.com.mcs.tweet.service.security.JwtService;

@Service
public class TweetServiceImpl implements TweetService{
    private static final Logger log = LoggerFactory.getLogger(TweetServiceImpl.class);

    @Autowired
    private TweetDAO tweetDAO;

    @Autowired
    private JwtService jwtService;
    @Override
    public TweetPostResData addTweet(TweetPostReq req,String accessToken) {
        log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "addTweet:start: data=",req.toString());
        TweetPostResData result = new TweetPostResData();
        TweetPostRes resData = new TweetPostRes();

        UserGetRes userAT = jwtService.isTokenValid(accessToken);

        int isInsertedTweet = tweetDAO.addTweet(userAT.getId(),req.getContent());

        resData.setIsInsertedTweet(isInsertedTweet == 0 ? Boolean.FALSE : Boolean.TRUE );
        resData.setMessage(isInsertedTweet == 0 ? "Error inserting tweet" : TweetConstant.TWEET_INSERTED_SUCCESS);
        result.setData(resData);
        return result;
    }

    @Override
    public FollowerPostResData addFollow(String accessToken, Long followedId) {
        FollowerPostResData result = new FollowerPostResData();
        FollowPostRes resData = new FollowPostRes();

        UserGetRes userAT = jwtService.isTokenValid(accessToken);

        int isInsertedTweet = tweetDAO.addFollower(followedId,userAT.getId());

        resData.setIsInsertedTweet(isInsertedTweet == 0 ? Boolean.FALSE : Boolean.TRUE );
        resData.setMessage(isInsertedTweet == 0 ? "Error inserting follower" : TweetConstant.FOLLOW_INSERTED_SUCCESS);
        result.setData(resData);
        return result;
    }
}
