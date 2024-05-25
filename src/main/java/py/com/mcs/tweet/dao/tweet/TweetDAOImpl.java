package py.com.mcs.tweet.dao.tweet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.constant.TweetConstant;

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
}
