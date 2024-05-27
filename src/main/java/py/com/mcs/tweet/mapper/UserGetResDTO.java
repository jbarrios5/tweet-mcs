package py.com.mcs.tweet.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.mcs.tweet.bean.user.resp.UserGetRes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGetResDTO implements RowMapper<UserGetRes> {
    @Override
    public UserGetRes mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserGetRes result = new UserGetRes();
        result.setId(rs.getLong("id"));
        result.setUserName(rs.getString("user_name"));
        result.setFullName(rs.getString("full_name"));
        result.setEmail(rs.getString("email"));
        result.setFollowers(rs.getInt("followers"));
        result.setFollowed(rs.getInt("followed"));
        return result;
    }
}
