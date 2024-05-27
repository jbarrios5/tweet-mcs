package py.com.mcs.tweet.dao.tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.bean.tweet.resp.TweetGetRes;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.dto.FollowerDTO;
import py.com.mcs.tweet.mapper.FollowerMapper;
import py.com.mcs.tweet.mapper.TweetGetResMapper;

import java.util.List;

@Repository
public class TweetDAOImpl implements TweetDAO{
    private static final Logger log = LoggerFactory.getLogger(TweetDAOImpl.class);
    @Autowired
    private JdbcTemplate jdbcPGS;

    @Override
    public int addTweet(Long userId, String content) {
        try {
            return jdbcPGS.update(SQLQueries.ADD_TWEET, userId,content);
        } catch (DataAccessException e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "addTweet: Error", e.getMessage());
            return 0;
        }
    }

    @Override
    public int addFollower(Long followedId, Long followerId) {
        try {
            return jdbcPGS.update(SQLQueries.ADD_FOLLOW, followerId,followedId);
        } catch (DataAccessException e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "addFollower: Error", e.getMessage());
            return 0;
        }
    }

    @Override
    public List<TweetGetRes> getTweets(String userName) {
        return jdbcPGS.query(SQLQueries.GET_TWEETS,new Object[]{userName},new TweetGetResMapper());
    }
}
