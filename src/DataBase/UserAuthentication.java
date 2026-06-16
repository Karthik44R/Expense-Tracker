package DataBase;
import java.sql.*;
public class UserAuthentication {
//Register user
    public static boolean registerUser(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO users(username,password) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            int rows = ps.executeUpdate();
            con.close();
            return rows>0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
//Login user
    public static boolean loginUser(String username,String password) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            boolean found = rs.next();
            con.close();
            return found;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
//Get user ID
    public static int getUserIdByUsername(String username) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            con.close();
            return id;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
}
