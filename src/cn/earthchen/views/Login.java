package cn.earthchen.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import cn.earthchen.db.DBHelper;
import cn.earthchen.views.root.RootView;
import cn.earthchen.views.student.StudentView;
import cn.earthchen.views.teacher.TeacherView;

/**
 * Created by earthchen on 17-6-4.
 * <p>
 * 登录模块
 */
public class Login extends JFrame implements ActionListener {

    private JTextField usernameField;//文本框
    private JPasswordField passwordField;//密码框
    private JRadioButton teacherRadioButton;
    private JRadioButton studentRadioButton;
    private JRadioButton adminRadioButton;

    private Login() {
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();


        JLabel titleLabel = new JLabel("学年设计选题系统");
        titleLabel.setFont(new Font("楷体", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLUE);
        JLabel usernameLabel = new JLabel("用户名");
        usernameLabel.setFont(new Font("楷体", Font.BOLD, 15));
        usernameLabel.setForeground(Color.BLUE);
        JLabel passwordLabel = new JLabel("密码　");
        passwordLabel.setFont(new Font("楷体", Font.BOLD, 15));
        passwordLabel.setForeground(Color.BLUE);
        JButton loginButton = new JButton("login");
        loginButton.setForeground(Color.BLUE);
        JButton cancelButton = new JButton("cancel");
        cancelButton.setForeground(Color.BLUE);
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        adminRadioButton = new JRadioButton("管理员");
        adminRadioButton.setFont(new Font("楷体", Font.BOLD, 15));
        adminRadioButton.setForeground(Color.BLUE);
        teacherRadioButton = new JRadioButton("教师");
        teacherRadioButton.setFont(new Font("楷体", Font.BOLD, 15));
        teacherRadioButton.setForeground(Color.BLUE);
        studentRadioButton = new JRadioButton("学生");
        studentRadioButton.setFont(new Font("楷体", Font.BOLD, 15));
        studentRadioButton.setForeground(Color.BLUE);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(teacherRadioButton);
        buttonGroup.add(studentRadioButton);

        this.setLayout(new GridLayout(5, 1));
        jp1.add(titleLabel);


        jp2.add(usernameLabel);
        jp2.add(usernameField);

        jp3.add(passwordLabel);
        jp3.add(passwordField);

        jp4.add(teacherRadioButton);
        jp4.add(studentRadioButton);
        jp4.add(adminRadioButton);

        jp5.add(loginButton);
        jp5.add(cancelButton);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);

        this.setBackground(Color.gray);
        this.setTitle("欢迎进入学年设计选题系统");
        this.setSize(400, 250);
        this.setLocation(400, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        loginButton.addActionListener(this);//事件监听
        loginButton.setActionCommand("login");//命令
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("close");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("close")) {
            System.exit(0);
        } else {
            JRadioButton jrb;
            if (teacherRadioButton.isSelected()) {
                jrb = teacherRadioButton;
            } else if (studentRadioButton.isSelected()) {
                jrb = studentRadioButton;
            } else if (adminRadioButton.isSelected()) {
                jrb = adminRadioButton;
            } else {
                jrb = null;
            }

            if (usernameField.getText().length() != 0 && passwordField.getPassword().length != 0 && jrb != null) {
                char[] passwordp = passwordField.getPassword();
                StringBuilder sb = new StringBuilder();
                for (char aPasswordp : passwordp) {
                    sb.append(aPasswordp);
                }
                String password = sb.toString();
                String user = jrb.getText();
                String sql;
                ResultSet resultSet;
                DBHelper dbHelper;
                switch (user) {
                    case "教师":
                        sql = "select * FROM account where  userNo=? and password=? and role='teacher'";
                        break;
                    case "学生":
                        sql = "select * FROM account where userNo=? and password=? and role='student'";
                        break;
                    default:
                        sql = "select * FROM account where userNo=? and password=? and role='admin'";
                        break;
                }
                try {
                    dbHelper = new DBHelper(sql);
                    dbHelper.pst.setString(1, usernameField.getText());
                    dbHelper.pst.setString(2, password);
                    System.out.println(dbHelper.pst);
                    resultSet = dbHelper.pst.executeQuery();

                    if (resultSet.next()) {
                        switch (user) {
                            case "教师":
                                new TeacherView(usernameField.getText());
                                break;
                            case "学生":
                                new StudentView(usernameField.getText());
                                break;
                            default:
                                new RootView(usernameField.getText());
                                break;
                        }
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.ERROR_MESSAGE);
                    }
                    dbHelper.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                if (usernameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入用户名");
                } else if (passwordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "请输入密码");
                } else {
                    JOptionPane.showMessageDialog(null, "请选择角色");
                }
            }
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
    }
}
