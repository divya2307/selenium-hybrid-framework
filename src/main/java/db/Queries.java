package db;

public class Queries {

    public static final String GET_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";

    public static final String GET_COURSE_BY_NAME =
            "SELECT * FROM courses WHERE name = ?";

}