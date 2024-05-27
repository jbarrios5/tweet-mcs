package py.com.mcs.tweet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowedDTO {
    private Integer id;
    private String fullName;
    private final String userName;
    private final String email;
}
