package py.com.mcs.tweet.bean.user.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserPutReq {
    private String name;
    @NotBlank(message = "user document is required")
    private String document;
    @NotBlank(message = "user password is required")
    private String birthDate;
    private String lastName;
    @NotBlank(message = "user role is required")
    private String role;
}
