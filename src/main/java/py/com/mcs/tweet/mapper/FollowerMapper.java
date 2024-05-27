package py.com.mcs.tweet.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.mcs.tweet.dto.FollowerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowerMapper implements RowMapper<FollowerDTO> {
    @Override
    public FollowerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new FollowerDTO(rs.getInt("followers"), rs.getString("full_name"), rs.getString("user_name"), rs.getString("email"),Boolean.FALSE);
    }
}
