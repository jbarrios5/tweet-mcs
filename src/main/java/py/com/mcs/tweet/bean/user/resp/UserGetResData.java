package py.com.mcs.tweet.bean.user.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserGetResData {
    private List<UserGetRes> data;
}
