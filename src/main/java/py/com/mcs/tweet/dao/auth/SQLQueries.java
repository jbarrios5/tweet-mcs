package py.com.mcs.tweet.dao.auth;

public final class SQLQueries {

    public static String ADD_AUTH = "INSERT INTO public.auth " +
            "(user_id) VALUES (?)";
}
