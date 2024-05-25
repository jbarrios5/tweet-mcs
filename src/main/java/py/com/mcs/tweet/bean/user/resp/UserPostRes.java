package py.com.mcs.tweet.bean.user.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPostRes {
    private Boolean isUserInserted;
    private String message;

}
