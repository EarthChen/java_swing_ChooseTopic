package cn.earthchen.views.student;

import cn.earthchen.db.DBHelper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


/**
 * Created by earthchen on 17-6-6.
 * 选题模型
 *
 */
public class ChooseTopicModel extends AbstractTableModel {

    private Vector<Vector<String>> rowData;
    private Vector<String> columnNames;
    private DBHelper dbHelper;
    private ResultSet resultSet;



    void init() {
        initTable();
    }

    private void initTable(){
        columnNames = new Vector<>();
        columnNames.add("课题号");
        columnNames.add("课题名");
        columnNames.add("内容");
        columnNames.add("教师号");
        columnNames.add("教师姓名");
        columnNames.add("学生数");
        rowData = new Vector<>();
        String sql = "select * from topic";
        try {
            dbHelper = new DBHelper(sql);
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

    //退选某一门课使课程人数+1
    boolean withdrawalTopic(String topicNo){
        boolean success=false;
        String sql="update topic set studentCount=studentCount+1 where topicNo=?";
        DBHelper dbHelper=new DBHelper(sql);
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

    //学生选了某一门课程设计（使课程待选人数-1 并且一个人只能选一门）
    boolean isChoose(String topicNo, String studentNo){
        boolean success=false;
        String sql="select * from topic where topicNo=?";
        try {
            dbHelper=new DBHelper(sql);
            dbHelper.pst.setString(1,topicNo);
            resultSet = dbHelper.pst.executeQuery();
            while (resultSet.next()){
                String studentCount=resultSet.getString(7);
                if (Integer.parseInt(studentCount)>0){
                    sql="select * from studentNoToTopicNo where studentNo=?";
                    dbHelper=new DBHelper(sql);
                    dbHelper.pst.setString(1,studentNo);
                    resultSet=dbHelper.pst.executeQuery();
                    if (!resultSet.next()){
                        sql="update topic set studentCount=studentCount-1 where topicNo=?";
                        try {
                            dbHelper=new DBHelper(sql);
                            dbHelper.pst.setString(1,topicNo);
                            dbHelper.pst.executeUpdate();
                            success=true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            dbHelper.close();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"你已经选过一门课程设计，请勿选择多门");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"该门课程已被选完，请重新选课");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbHelper.close();
        }
        return success;
    }

    public JTable chooseTopic(){
        initTable();
        DefaultTableModel dtm=new DefaultTableModel(rowData,columnNames);
        JTable jtb=new JTable(dtm);

        TableColumnModel tcm = jtb.getColumnModel();
        tcm.getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        tcm.getColumn(0).setCellRenderer(new MyTableCellRenderer());
        return jtb;
    }

    class MyTableCellRenderer extends JCheckBox implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Boolean b = (Boolean) value;
            this.setSelected(b);
            return this;
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
