package cn.earthchen.views.student;

import cn.earthchen.db.DBHelper;

import javax.swing.table.AbstractTableModel;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * Created by earthchen on 17-6-6.
 * <p>
 * 我的选题
 */
public class MyTopicModel extends AbstractTableModel {
    private Vector<Vector<String>> rowData;
    private Vector<String> columnNames;
    private DBHelper dbHelper;
    private ResultSet resultSet;
    private String studentNo;

    MyTopicModel(String studentNo) {
        this.studentNo = studentNo;
        init(studentNo);
    }


     void init(String studentNo) {
        columnNames = new Vector<>();
        columnNames.add("课题号");
        columnNames.add("课题名");
        columnNames.add("内容");
        columnNames.add("教师姓名");
        rowData = new Vector<>();
        String sql = "select topic.topicNo,topicName,content,teacherName" +
                " from studentNoToTopicNo,topic where topic.topicNo=studentNoToTopicNo.topicNo and studentNo=?";
        dbHelper = new DBHelper(sql);
        try {
            dbHelper.pst.setString(1, studentNo);
            resultSet = dbHelper.pst.executeQuery();
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                row.add(resultSet.getString(4));
                rowData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //删除学号与课题号的对应关系
    boolean deleteStudentNoTopicNo(String topicNo) {
        boolean success = false;
        String sql = "delete from studentNoToTopicNo where topicNo=?";
        dbHelper=new DBHelper(sql);
        try {
            dbHelper.pst.setString(1,topicNo);
            dbHelper.pst.executeUpdate();
            success=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.close();
        }
        return success;
    }

    //存入学号与课题号的对应关系
    boolean saveStudentNoToTopicNo(String studentNo, String topicNo) {
        boolean success = false;
        ResultSet resultSet = null;
        String sql = "select * from studentNoToTopicNo where studentNo=? and topicNo=?";
        DBHelper dbHelper = new DBHelper(sql);
        try {
            dbHelper.pst.setString(1, studentNo);
            dbHelper.pst.setString(2, topicNo);
            resultSet = dbHelper.pst.executeQuery();
            if (resultSet.next()) {
                sql = "update studentNoToTopicNo set studentNo=?,topicNo=?";
            } else {
                sql = "insert into studentNoToTopicNo (studentNo,topicNo) values (?,?)";
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
        dbHelper = new DBHelper(sql);
        try {
            dbHelper.pst.setString(1, studentNo);
            dbHelper.pst.setString(2, topicNo);
            dbHelper.pst.executeUpdate();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
    }
}
