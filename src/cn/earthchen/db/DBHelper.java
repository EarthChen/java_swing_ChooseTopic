package cn.earthchen.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
    public static final String url = "jdbc:mysql://127.0.0.1/chooseclass?useUnicode=true&characterEncoding=utf8&useSSL=true";
    public static final String name = "com.mysql.cj.jdbc.Driver";
    public static final String user = "earthchen";
    public static final String password = "cyw123456789";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库连接错误", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void close() {
        try {
            this.pst.close();
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}