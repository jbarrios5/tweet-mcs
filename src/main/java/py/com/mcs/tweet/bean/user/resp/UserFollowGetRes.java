package py.com.mcs.tweet.bean.user.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import py.com.mcs.tweet.dto.FollowedDTO;
import py.com.mcs.tweet.dto.FollowerDTO;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UserFollowGetRes {
    private List<FollowerDTO> followers;
    private List<FollowedDTO> followed;
}
