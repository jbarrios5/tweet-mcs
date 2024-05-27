package py.com.mcs.tweet.bean.tweet.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import py.com.mcs.tweet.bean.tweet.Tweet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetGetRes  extends Tweet {
        private String created;
}
