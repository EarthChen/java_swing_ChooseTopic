package cn.earthchen.views.root;

import cn.earthchen.db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by earthchen on 17-6-7.
 */
public class StudentAddDialog extends JDialog implements ActionListener {
    
    private JTextField studentNoTextField;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField sexTextField;
    private JTextField birthdayTextField;
    private JTextField timeOfEnrollmentTextField;
    private JTextField departmentTextField;
    private JPasswordField passwordPasswordField;
    private JButton addButton;


    StudentAddDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        Font font = new Font("楷体", Font.BOLD, 15);

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JPanel jp7 = new JPanel();
        JPanel jp8 = new JPanel();
        JPanel jp9 = new JPanel();
        JPanel jp10 = new JPanel();
        JPanel jp11 = new JPanel();
        JPanel jp12 = new JPanel();
        JPanel jp13 = new JPanel();


        JLabel jbl1 = new JLabel("　　学号*");
        JLabel jbl2 = new JLabel("　　姓名*");
        JLabel jbl3 = new JLabel("　　年龄*");
        JLabel jbl4 = new JLabel("　　性别*");
        JLabel jbl5 = new JLabel("出生日期*");
        JLabel jbl7 = new JLabel("入学时间*");
        JLabel jbl11 = new JLabel("　　学院*");
        JLabel jbl12 = new JLabel("　　密码*");


        jbl1.setFont(font);
        jbl1.setForeground(Color.blue);

        jbl2.setFont(font);
        jbl2.setForeground(Color.blue);

        jbl3.setFont(font);
        jbl3.setForeground(Color.blue);

        jbl4.setFont(font);
        jbl4.setForeground(Color.blue);

        jbl5.setFont(font);
        jbl5.setForeground(Color.blue);

        jbl7.setFont(font);
        jbl7.setForeground(Color.blue);

        jbl11.setFont(font);
        jbl11.setForeground(Color.blue);

        jbl12.setFont(font);
        jbl12.setForeground(Color.blue);

        studentNoTextField = new JTextField(20);
        nameTextField = new JTextField(20);
        ageTextField = new JTextField(20);
        sexTextField = new JTextField(20);
        birthdayTextField = new JTextField(20);
        timeOfEnrollmentTextField = new JTextField(20);
        departmentTextField = new JTextField(20);
        passwordPasswordField = new JPasswordField(20);

        studentNoTextField.setFont(font);
        studentNoTextField.setForeground(Color.blue);
        nameTextField.setFont(font);
        nameTextField.setForeground(Color.blue);
        ageTextField.setFont(font);
        ageTextField.setForeground(Color.blue);
        sexTextField.setFont(font);
        sexTextField.setForeground(Color.blue);
        birthdayTextField.setFont(font);
        birthdayTextField.setForeground(Color.blue);

        timeOfEnrollmentTextField.setFont(font);
        timeOfEnrollmentTextField.setForeground(Color.blue);

        departmentTextField.setFont(font);
        departmentTextField.setForeground(Color.blue);
        passwordPasswordField.setFont(font);
        passwordPasswordField.setForeground(Color.blue);


        addButton = new JButton("确定");
        addButton.addActionListener(this);
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(this);

        jp1.add(jbl1);
        jp1.add(studentNoTextField);

        jp2.add(jbl2);
        jp2.add(nameTextField);

        jp3.add(jbl3);
        jp3.add(ageTextField);

        jp4.add(jbl4);
        jp4.add(sexTextField);


        jp5.add(jbl5);
        jp5.add(birthdayTextField);

        jp7.add(jbl7);
        jp7.add(timeOfEnrollmentTextField);


        jp11.add(jbl11);
        jp11.add(departmentTextField);

        jp12.add(jbl12);
        jp12.add(passwordPasswordField);

        jp13.add(addButton);
        jp13.add(cancelButton);

        this.setLayout(new GridLayout(13, 1));

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.add(jp8);
        this.add(jp9);
        this.add(jp10);
        this.add(jp11);
        this.add(jp12);
        this.add(jp13);

        this.setSize(300, 400);
        this.setLocation(400, 150);
        this.setResizable(false);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            char[] jpfp = passwordPasswordField.getPassword();
            StringBuilder sb = new StringBuilder();
            for (char aJpfp : jpfp) {
                sb.append(aJpfp);
            }
            String password = sb.toString();

            boolean insertStudentInfoSuccess = false;
            String sql = "insert into student (studentNo,name,sex,age,birthday,timeOfEnrollment,department) values (?,?,?,?,?,?,?)";
            DBHelper dbHelper = new DBHelper(sql);
            try {
                dbHelper.pst.setString(1, studentNoTextField.getText().trim());
                dbHelper.pst.setString(2, nameTextField.getText().trim());
                dbHelper.pst.setString(3, sexTextField.getText().trim());
                dbHelper.pst.setInt(4, Integer.parseInt(ageTextField.getText().trim()));
                dbHelper.pst.setString(5, birthdayTextField.getText().trim());
                dbHelper.pst.setString(6, timeOfEnrollmentTextField.getText().trim());
                dbHelper.pst.setString(7, departmentTextField.getText().trim());
                dbHelper.pst.executeUpdate();
                insertStudentInfoSuccess = true;
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "添加学生信息失败");
            } finally {
                dbHelper.close();
            }
            if (insertStudentInfoSuccess) {
                String addAccountSql = "insert into account (userNo,password,role) values (?,?,'student')";
                DBHelper dbHelper1 = new DBHelper(addAccountSql);
                try {
                    dbHelper1.pst.setString(1, studentNoTextField.getText().trim());
                    dbHelper1.pst.setString(2, password);
                    dbHelper1.pst.executeUpdate();
                    JOptionPane.showMessageDialog(this, "添加学生账户成功");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "添加学生账户失败");
                } finally {
                    dbHelper1.close();
                }
            } else {
                JOptionPane.showMessageDialog(this, "添加学生账户失败");
            }
            this.dispose();
        }else {
            this.dispose();
        }
    }
}
