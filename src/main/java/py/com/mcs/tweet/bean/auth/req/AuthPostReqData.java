package py.com.mcs.tweet.bean.auth.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class AuthPostReqData {
    @NotNull(message = "req can not be  null")
    @Valid
    private AuthPostReq data;
}
