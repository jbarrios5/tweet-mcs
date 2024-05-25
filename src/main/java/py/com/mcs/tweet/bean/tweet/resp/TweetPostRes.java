package py.com.mcs.tweet.bean.tweet.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TweetPostRes {
    private Boolean isInsertedTweet;
    private String message;
}
