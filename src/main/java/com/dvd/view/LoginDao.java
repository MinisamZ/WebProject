package com.dvd.view;

import java.sql.*;

public class LoginDao {

    private String dbUrl = "jdbc:mysql://localhost/userdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private String dbUname = "sam2";
    private String dbPassword = "Yys0mIf0AZb0wnth";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";

    public void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }


    public String verify(Member member) {
        loadDriver(dbDriver);
        Connection con = getConnection();
        String result = "Data received successfully ";
        String sql = "select `uname`, `password`, `email`, `phone` FROM `member` WHERE(";
        System.out.println(sql);
        try {
//            ps.setString(1, member.getUname());
//            ps.setString(2, member.getPassword());

            sql += "uname = '" + member.getUname() + "'";
            sql += " AND ";
            sql += "password = '" + member.getPassword() + "')";

            System.out.println(sql);
//            PreparedStatement ps;
//            ps =
            PreparedStatement prSt = con.prepareStatement(sql);
            result += prSt.executeQuery();
            ResultSet resultSet = prSt.executeQuery();

            System.out.println("zzz");
            System.out.println(resultSet.toString());
            System.out.println("zzz");

            if(resultSet.next()){
                MemberDao c = new MemberDao();
                c.uname = resultSet.getString("umane");
                System.out.println("asdasd " + c.toString() + " asdasd");
            }else {
                System.out.println(false);
            }
//            while (resultSet.next()) {
//            }
//                c.password = "password";


//                c.toString();
//            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = "Data not correct";
        }
        return result;
    }

}
