package py.com.mcs.tweet.bean.user.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserPostReqData {
    @Valid
    @NotNull(message = "data is required")
    private UserPostReq data;
}
