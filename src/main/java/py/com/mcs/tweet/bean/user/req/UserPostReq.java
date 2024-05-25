package py.com.mcs.tweet.bean.user.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserPostReq {
    @NotBlank(message = "user name is  is required")
    private String fullName;
    @Email
    private String email;
    @NotBlank(message = "user password is required")
    private String password;

    private String userName;


}
