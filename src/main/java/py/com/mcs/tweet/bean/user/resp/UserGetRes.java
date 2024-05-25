package py.com.mcs.tweet.bean.user.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserGetRes {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
}
