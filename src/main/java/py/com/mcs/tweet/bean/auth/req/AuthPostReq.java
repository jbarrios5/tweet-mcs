package py.com.mcs.tweet.bean.auth.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthPostReq {
    @NotBlank(message = "password can not be null or empty")
    private String password;
    @NotBlank(message = "userName can not be null or empty")
    private String userName;
}
