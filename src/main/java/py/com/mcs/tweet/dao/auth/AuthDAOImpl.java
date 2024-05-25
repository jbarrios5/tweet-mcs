package py.com.mcs.tweet.dao.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.constant.TweetConstant;

@Repository
public class AuthDAOImpl implements AuthDAO {
    private static final Logger log = LoggerFactory.getLogger(AuthDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcPGS;

    @Override
    public boolean addUserAuth(long userId) {
        int result = 0;
        try {
            result = jdbcPGS.update(SQLQueries.ADD_AUTH, userId);
        } catch (DataAccessException e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), "addUserAuth: Error", e.getMessage());
            result = 0;
        }
        return result > 0;
    }
}
