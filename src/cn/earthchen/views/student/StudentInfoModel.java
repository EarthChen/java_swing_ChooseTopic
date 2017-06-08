package cn.earthchen.views.student;

import cn.earthchen.db.DBHelper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by earthchen on 17-6-6.
 */
public class StudentInfoModel extends AbstractTableModel {

    private Vector<Vector<String>> rowData;
    private Vector<String> columnNames;
    private DBHelper dbHelper;
    private ResultSet resultSet;

    public StudentInfoModel(String studentNo) {
        init(studentNo);
    }

    public void init(String studentNo) {

        columnNames = new Vector<>();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("年龄");
        columnNames.add("出生日期");
        columnNames.add("入学时间");
        columnNames.add("学院");
        rowData = new Vector<>();
        String sql;
        try {
            if (studentNo.equals("all")) {
                sql = "select * from student";
                dbHelper = new DBHelper(sql);

            } else {
                sql = "select * from student where studentNo=?";
                dbHelper = new DBHelper(sql);
                dbHelper.pst.setString(1, studentNo);
            }
            resultSet = dbHelper.pst.executeQuery();
            while (resultSet.next()) {
                Vector<String> row = new Vector<String>();
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                row.add(resultSet.getString(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getString(6));
                row.add(resultSet.getString(7));
                row.add(resultSet.getString(8));
                rowData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbHelper.close();
        }

    }

    //更新学生个人信息
    boolean updateStudentInfo(String studentNo, String name, int age, String sex, String birthday) {
        boolean success = false;
        String sql = "update student set name=?,age=?,sex=?,birthday=? where studentNo=?";
        dbHelper = new DBHelper(sql);
        try {
            dbHelper.pst.setString(1, name);
            dbHelper.pst.setInt(2, age);
            dbHelper.pst.setString(3, sex);
            dbHelper.pst.setString(4, birthday);
            dbHelper.pst.setString(5, studentNo);
            dbHelper.pst.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    //删除指定学生的信息
    public boolean deleteStudentInfo(String studentNo) {
        boolean success = false;
        String sql = "delete from student where studentNo=?";
        DBHelper dbHelper = new DBHelper(sql);
        try {
            dbHelper.pst.setString(1, studentNo);
            dbHelper.pst.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "删除学生信息失败");
        } finally {
            dbHelper.close();
        }
        return success;
    }


    @Override
    public String getColumnName(int column) {
        return this.columnNames.get(column);
    }

    @Override
    public int getRowCount() {
        return this.rowData.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return ((Vector) this.rowData.get(row)).get(column);
    }
}
