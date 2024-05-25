package py.com.mcs.tweet.bean.tweet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tweet {
    private String content;
    private Integer userId;
}
