package py.com.mcs.tweet.dao.tweet;

public interface TweetDAO {

    int addTweet(Long userId,String content);

    int addFollower(Long followedId,Long followerId);
}
