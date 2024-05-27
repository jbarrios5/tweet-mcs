package py.com.mcs.tweet.dao.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import py.com.mcs.tweet.bean.interceptor.TraceContextHolder;
import py.com.mcs.tweet.bean.user.req.UserPostReq;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;
import py.com.mcs.tweet.constant.TweetConstant;
import py.com.mcs.tweet.dto.FollowedDTO;
import py.com.mcs.tweet.dto.FollowerDTO;
import py.com.mcs.tweet.dto.UserDTO;
import py.com.mcs.tweet.exceptions.TweetException;
import py.com.mcs.tweet.mapper.FollowedDTOMapper;
import py.com.mcs.tweet.mapper.FollowerMapper;
import py.com.mcs.tweet.mapper.UserDTOMapper;
import py.com.mcs.tweet.mapper.UserGetResDTO;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcPGS;

    @Override
    public List<UserGetRes> getUsers(Long userId) {
        return jdbcPGS.query(SQLQueries.GET_USER,new UserGetResDTO());
    }

    @Override
    public UserDTO getUserByUserNameWithPassword(String userName) {
        try {
            return jdbcPGS.queryForObject(SQLQueries.GET_USER_WITH_PASS, new Object[]{userName}, new UserDTOMapper());
        } catch (DataAccessException e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), e.getMessage());
            throw new TweetException("Usuario no encontrado.", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public boolean addUser(UserPostReq user) {
        int result = 0;
        try {
            result = jdbcPGS.update(SQLQueries.INSERT_USER,
                     user.getFullName(), user.getUserName(),
                     user.getEmail(),user.getPassword());
        }catch (DuplicateKeyException e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), e.getMessage());
            throw new TweetException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (DataAccessException e) {
            log.warn(TweetConstant.LOG_FORMATT, TraceContextHolder.getLogId(), e.getMessage());
            throw new TweetException("Error inesperado al insertar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result > 0;
    }
    @Override
    public List<FollowerDTO> getfollowers(Long followedId) {
        return jdbcPGS.query(SQLQueries.GET_FOLLOWERS,new Object[]{followedId},new FollowerMapper());
    }

    @Override
    public List<FollowedDTO> getfollowed(Long followedId) {
        return jdbcPGS.query(SQLQueries.GET_FOLLOWED,new Object[]{followedId},new FollowedDTOMapper());
    }
}
