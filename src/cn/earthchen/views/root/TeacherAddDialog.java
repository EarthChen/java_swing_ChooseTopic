package cn.earthchen.views.root;

import cn.earthchen.db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by earthchen on 17-6-8.
 */
public class TeacherAddDialog extends JDialog implements ActionListener {


    private JTextField teacherNoField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField sexField;
    private JTextField birthdayField;
    private JTextField departmentField;

    private JPasswordField passwordField;

    private JButton confirmButton;


    TeacherAddDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        Font font = new Font("楷体", Font.BOLD, 15);

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JPanel jp9 = new JPanel();
        JPanel jp10 = new JPanel();
        JPanel jp11 = new JPanel();
        JPanel jp12 = new JPanel();
        JPanel jp13 = new JPanel();

        JLabel jbl1 = new JLabel("　教师号*");
        JLabel jbl2 = new JLabel("　　姓名*");
        JLabel jbl3 = new JLabel("　　年龄*");
        JLabel jbl4 = new JLabel("　　性别*");
        JLabel jbl5 = new JLabel("出生日期*");

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

        jbl11.setFont(font);
        jbl11.setForeground(Color.blue);

        jbl12.setFont(font);
        jbl12.setForeground(Color.blue);

        teacherNoField = new JTextField(20);
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        sexField = new JTextField(20);
        birthdayField = new JTextField(20);

        departmentField = new JTextField(20);
        passwordField = new JPasswordField(20);

        teacherNoField.setFont(font);
        teacherNoField.setForeground(Color.blue);
        nameField.setFont(font);
        nameField.setForeground(Color.blue);
        ageField.setFont(font);
        ageField.setForeground(Color.blue);
        sexField.setFont(font);
        sexField.setForeground(Color.blue);
        birthdayField.setFont(font);
        birthdayField.setForeground(Color.blue);

        departmentField.setFont(font);
        departmentField.setForeground(Color.blue);
        passwordField.setFont(font);
        passwordField.setForeground(Color.blue);

        confirmButton = new JButton("确定");
        confirmButton.addActionListener(this);
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(this);

        jp1.add(jbl1);
        jp1.add(teacherNoField);

        jp2.add(jbl2);
        jp2.add(nameField);

        jp3.add(jbl3);
        jp3.add(ageField);

        jp4.add(jbl4);
        jp4.add(sexField);

        jp5.add(jbl5);
        jp5.add(birthdayField);

        jp11.add(jbl11);
        jp11.add(departmentField);

        jp12.add(jbl12);
        jp12.add(passwordField);

        jp13.add(confirmButton);
        jp13.add(cancelButton);

        this.setLayout(new GridLayout(11, 1));

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
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
        if (e.getSource() == confirmButton) {
            char[] passwordChar = passwordField.getPassword();
            StringBuilder sb = new StringBuilder();
            for (char apasswordFieldp : passwordChar) {
                sb.append(apasswordFieldp);
            }
            String password = sb.toString();

            boolean insertTeacherInfoSuccess = false;
            String sql = "insert into teacher (teacherNo,name,sex,age,birthday,department) values (?,?,?,?,?,?)";
            DBHelper dbHelper = new DBHelper(sql);
            try {
                dbHelper.pst.setString(1, teacherNoField.getText().trim());
                dbHelper.pst.setString(2, nameField.getText().trim());
                dbHelper.pst.setString(3, sexField.getText().trim());
                dbHelper.pst.setInt(4, Integer.parseInt(ageField.getText().trim()));
                dbHelper.pst.setString(5, birthdayField.getText().trim());
                dbHelper.pst.setString(6, departmentField.getText().trim());
                dbHelper.pst.executeUpdate();
                insertTeacherInfoSuccess = true;
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "添加教师信息失败");
            } finally {
                dbHelper.close();
            }
            if (insertTeacherInfoSuccess) {
                String addAccountSql = "insert into account (userNo,password,role) values (?,?,'teacher')";
                DBHelper dbHelper1 = new DBHelper(addAccountSql);
                try {
                    dbHelper1.pst.setString(1, teacherNoField.getText().trim());
                    dbHelper1.pst.setString(2, password);
                    dbHelper1.pst.executeUpdate();
                    JOptionPane.showMessageDialog(this, "添加教师账户成功");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "添加教师账户失败");
                } finally {
                    dbHelper1.close();
                }
            } else {
                JOptionPane.showMessageDialog(this, "添加教师账户失败");
            }
            this.dispose();
        } else {
            this.dispose();
        }
    }
}
