import java.sql.*;
import java.util.Properties;

public class TestDb {


    public static void main(String args[]) {
        Connection con;
        Statement sql;
        ResultSet rs;
        try{ Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        }
        catch(Exception e) {
            System.out.print(e);
        }
        try {
            Properties userInfo = new Properties();
            userInfo.put("user", "someuser");
            userInfo.put("password", "somepassword");
            con=DriverManager.getConnection("jdbc:derby:myDatabase;create=true",userInfo);
            sql=con.createStatement();
            rs=sql.executeQuery("SELECT * FROM PROJECTS ");
            while(rs.next()) {
//                String number=rs.getString(1);
//                String addr=rs.getString(2);
//
//                System.out.print(number+"|");
//                System.out.print(addr+"|");
                System.out.println("ok");

            }
            con.close();
        }
        catch(SQLException e) {
            System.out.println("============");
            System.out.println(e);
        }
    }


}
