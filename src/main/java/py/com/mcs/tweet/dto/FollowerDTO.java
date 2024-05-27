package py.com.mcs.tweet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FollowerDTO {
    private final Integer id;
    private final String fullName;
    private final String userName;
    private final String email;
    private  Boolean isFollowed;
}
