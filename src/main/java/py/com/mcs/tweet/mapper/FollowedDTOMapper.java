package py.com.mcs.tweet.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.mcs.tweet.dto.FollowedDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowedDTOMapper implements RowMapper<FollowedDTO> {
    @Override
    public FollowedDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FollowedDTO(rs.getInt("followed"),rs.getString("full_name"), rs.getString("user_name"), rs.getString("email"));
    }
}
