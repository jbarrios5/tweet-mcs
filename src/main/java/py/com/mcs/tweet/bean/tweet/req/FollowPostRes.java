package py.com.mcs.tweet.bean.tweet.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowPostRes {
    private Boolean isInsertedTweet;
    private String message;
}
