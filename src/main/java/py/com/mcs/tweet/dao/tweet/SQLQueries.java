package py.com.mcs.tweet.dao.tweet;

public final class SQLQueries {

    public static final String ADD_TWEET = "INSERT INTO public.tweets( " +
            " user_id, content) " +
            " VALUES ( ?, ?)";

    public static final String ADD_FOLLOW  = "INSERT INTO public.followers( " +
            " follower_id, followed_id)" +
            " VALUES (?, ?)";
}
