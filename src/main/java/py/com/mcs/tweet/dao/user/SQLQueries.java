package py.com.mcs.tweet.dao.user;

public final class SQLQueries {

    public static final String GET_USER_WITH_PASS = "select id,full_name, user_name, email,   password" +
            " from users where user_name = ? ";
    public static final String INSERT_USER = "INSERT INTO public.users( " +
            " full_name,user_name, email, password )" +
            " VALUES ( ?,?, ?, ?)";

    public static final String GET_USER = "SELECT u.id, u.full_name, u.user_name,u.email, \n" +
            " COALESCE(followers_count, 0) AS followers, " +
            " COALESCE(followed_count, 0) AS followed" +
            " FROM users u " +
            " LEFT JOIN ( SELECT followed_id, COUNT(*) AS followers_count " +
            " FROM followers GROUP BY followed_id ) f1 ON u.id = f1.followed_id" +
            " LEFT JOIN ( SELECT follower_id, COUNT(*) AS followed_count" +
            " FROM followers GROUP BY follower_id ) f2 ON u.id = f2.follower_id "
             ;
    public static final String GET_FOLLOWERS  = "select f.follower_id as followers,  u.full_name, u.user_name,u.email  from followers  f " +
            " join users u on u.id = f.follower_id WHERE f.followed_id = ?";
    public static final String GET_FOLLOWED  = "select f.followed_id as followed,  u.full_name , u.user_name,u.email from followers  f " +
            " join users u on u.id = f.followed_id WHERE f.follower_id =?";
}
