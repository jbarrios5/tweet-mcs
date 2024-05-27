package py.com.mcs.tweet.mapper;

import org.springframework.jdbc.core.RowMapper;
import py.com.mcs.tweet.bean.tweet.resp.TweetGetRes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TweetGetResMapper implements RowMapper<TweetGetRes> {
    @Override
    public TweetGetRes mapRow(ResultSet rs, int rowNum) throws SQLException {
        TweetGetRes res = new TweetGetRes();
        res.setContent(rs.getString("content"));
        res.setCreated(rs.getTimestamp("created_at").toString());
        return res;
    }
}
