package py.com.mcs.tweet.bean.tweet.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TweetGetResData {
    private List<TweetGetRes> data;
}
