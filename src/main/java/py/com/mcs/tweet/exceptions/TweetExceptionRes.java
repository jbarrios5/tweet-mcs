package py.com.mcs.tweet.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class TweetExceptionRes {

    private final String message;
    private final HttpStatus status;
    private final int statusCode;
}
