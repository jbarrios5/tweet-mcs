package py.com.mcs.tweet.bean.auth.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthPostRes {
    private String accessToken;
}
