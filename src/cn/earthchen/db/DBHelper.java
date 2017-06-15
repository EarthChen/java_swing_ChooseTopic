package cn.earthchen.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
    private static final String url = "jdbc:mysql://127.0.0.1/chooseclass?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static final String name = "com.mysql.cj.jdbc.Driver";
    private static final String user = "earthchen";
    private static final String password = "cyw123456789";

    private Connection conn = null;
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
        
        System.out.println();
    }
}