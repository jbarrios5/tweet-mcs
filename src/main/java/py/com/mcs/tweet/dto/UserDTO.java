package py.com.mcs.tweet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String userName;
    private String passwordHash;
    private String email;


}
