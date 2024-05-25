package py.com.mcs.tweet.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.mcs.tweet.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDTOMapper implements RowMapper<UserDTO> {


    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException  {
        UserDTO result = new UserDTO();
        result.setId(rs.getLong("id"));
        result.setUserName(rs.getString("user_name"));
        result.setFullName(rs.getString("full_name"));
        result.setEmail(rs.getString("email"));
        result.setPasswordHash(rs.getString("password"));
        return result;
    }
}
