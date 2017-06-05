package cn.earthchen.views.teacher;

import cn.earthchen.db.DBHelper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by earthchen on 17-6-4.
 */
public class TeacherInfoModel extends AbstractTableModel {

    private Vector<Vector<String>> rowData;
    private Vector<String> columnNames;
    private DBHelper dbHelper;
    private ResultSet resultSet;
    private String teacherNo;


    TeacherInfoModel(String teacherNo)  {
        System.out.println(teacherNo);
        this.teacherNo=teacherNo;
        init(teacherNo);

    }

    private void init(String teacherNo){
        columnNames = new Vector<>();
        columnNames.add("教师号");
        columnNames.add("姓名");
        columnNames.add("年龄");
        columnNames.add("性别");
        columnNames.add("出生日期");
        columnNames.add("学院");
        rowData = new Vector<>();
        String sql;
        if (teacherNo.equals("")) {
            sql = "select * from teacher";
        } else {
            sql = "select * from teacher where teacherNo=?";
        }
        try {
            dbHelper = new DBHelper(sql);
            dbHelper.pst.setString(1, teacherNo);
            resultSet = dbHelper.pst.executeQuery();
            while (resultSet.next()) {
                Vector<String> row = new Vector<String>();
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                row.add(resultSet.getString(4));
                row.add(resultSet.getString(5));
                row.add(resultSet.getString(6));
                row.add(resultSet.getString(7));
                rowData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                dbHelper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void updateTeacherInfo(String name, int age, String sex, String birthday){
        String sql="update teacher set name=?,age=?,sex=?,birthday=? where teacherNo=?";
        DBHelper dbHelper1=new DBHelper(sql);
        try {
            dbHelper1.pst.setString(1,name);
            dbHelper1.pst.setInt(2,age);
            dbHelper1.pst.setString(3,sex);
            dbHelper1.pst.setString(4,birthday);
            dbHelper1.pst.setString(5,this.teacherNo);
            dbHelper1.pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "更新失败请重试");
        }finally {
            dbHelper1.close();
        }
        init(teacherNo);
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
    }
}
