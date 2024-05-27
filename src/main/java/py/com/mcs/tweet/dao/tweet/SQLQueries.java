package py.com.mcs.tweet.dao.tweet;

public final class SQLQueries {

    public static final String ADD_TWEET = "INSERT INTO public.tweets( " +
            " user_id, content) " +
            " VALUES ( ?, ?)";

    public static final String ADD_FOLLOW  = "INSERT INTO public.followers( " +
            " follower_id, followed_id)" +
            " VALUES (?, ?)";


    public static final String GET_TWEETS = "select t.content,t.created_at from tweets t join users u on u.id = t.user_id where u.user_name = ?";
}
