package py.com.mcs.tweet.dao.user;

public final class SQLQueries {

    public static final String GET_USER_WITH_PASS = "select id,full_name, user_name, email,   password" +
            " from users where user_name = ? ";
    public static final String INSERT_USER = "INSERT INTO public.users( " +
            " full_name,user_name, email, password )" +
            " VALUES ( ?,?, ?, ?)";


}
