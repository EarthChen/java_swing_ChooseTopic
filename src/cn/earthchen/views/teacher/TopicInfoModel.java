package cn.earthchen.views.teacher;

import cn.earthchen.db.DBHelper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



/**
 * Created by earthchen on 17-6-5.
 */
public class TopicInfoModel extends AbstractTableModel {

    private Vector<Vector<String>> rowData;
    private Vector<String> columnNames;
    private DBHelper dbHelper;
    private ResultSet resultSet;


    TopicInfoModel(String teacherNo) {
        init(teacherNo);
    }

    void init(String teacherNo) {
        columnNames = new Vector<>();
        columnNames.add("课题号");
        columnNames.add("课题名");
        columnNames.add("内容");
        columnNames.add("教师号");
        columnNames.add("教师姓名");
        columnNames.add("学生数");
        rowData = new Vector<>();
        String sql;
        try {
            if (teacherNo.equals("")) {
                sql = "select * from topic";
                dbHelper = new DBHelper(sql);
            } else {
                sql = "select * from topic where teacherNo=?";
                dbHelper = new DBHelper(sql);
                dbHelper.pst.setString(1, teacherNo);
            }
            resultSet = dbHelper.pst.executeQuery();
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
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

    void saveTopicInfo(String topicNo, String topicName, String concent, String teacherNo, String teacherName, int studentCount) {
        String sql = "insert into topic (topicNo,topicName,content,teacherNo,teacherName,studentCount) values (?,?,?,?,?,?)";
        dbHelper = new DBHelper(sql);
        try {
            dbHelper.pst.setString(1, topicNo);
            dbHelper.pst.setString(2, topicName);
            dbHelper.pst.setString(3, concent);
            dbHelper.pst.setString(4, teacherNo);
            dbHelper.pst.setString(5, teacherName);
            dbHelper.pst.setInt(6, studentCount);
            dbHelper.pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "发布课题成功");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "发布课题失败请重试");
        } finally {
            dbHelper.close();
        }
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
