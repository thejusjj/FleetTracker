package android.util;
//Change these parameters according to your DB
public class Constants {
    public static String dbClass = "com.mysql.jdbc.Driver";
    public static String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    public static String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    public static String dbUrl = "jdbc:mysql://"+host+":"+port+"/mytomcatapp";
    public static String dbUser = "adminAMi8NtZ";
    public static String dbPwd = "smgcb2HCSEux";
}