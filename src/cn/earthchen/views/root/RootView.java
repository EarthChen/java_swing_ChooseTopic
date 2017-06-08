package cn.earthchen.views.root;


import cn.earthchen.db.DBHelper;
import cn.earthchen.views.student.MyTopicModel;
import cn.earthchen.views.student.StudentInfoModel;
import cn.earthchen.views.teacher.TeacherInfoModel;
import cn.earthchen.views.teacher.TopicInfoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by earthchen on 17-6-4.
 * <p>
 * 管理员模块
 */
public class RootView extends JFrame implements ActionListener {

    private JPanel jp3;
    private JPanel jp4;
    private JPanel jp5;
    private JPanel jp6;

    private JButton findStudentButton;
    private JButton addStudentButton;
    private JButton deleteStudentButton;
    private JButton findTeacherInfoButton;
    private JButton addTeacherButton;
    private JButton deleteTeacherButton;
    private JButton chooseTopicContentButton;
    private JButton TopicInfoContentButton;
    private JButton findChooseTopicFromStudentNoButton;
    private JButton findChooseTopicFromTopicNoButton;
    private JButton findTopicInfoFromTeacherNoButton;
    private JButton findChooseTopicFromTeacherNoButton;
    private JButton findTopicInfoFromTopicNoButton;

    private JTextField jtf;
    private JTextField teacherNoTextField;
    private JTextField findTopicInfoFromTeacherNoTextField;
    private JTextField findTopicInfoFromTopicNoTextField;
    private JTextField findChooseTopicFromTeacherNoTextField;
    private JTextField findChooseTopicFromStudentNoTextField;
    private JTextField findChooseTopicFromTopicNoTextField;

    private StudentInfoModel studentInfoModel;
    private TeacherInfoModel teacherInfoModel;
    private MyTopicModel myTopicModel;
    private TopicInfoModel topicInfoModel;

    private JTable studentInfoTable;
    private JTable teacherInfoTable;
    private JTable myTopicTable;
    private JTable topicInfoTable;


    public static void main(String[] args) {
        RootView rootv = new RootView("admin");
    }

    public RootView(String adminNo) {
        System.out.println(adminNo);
        JLabel welcomeLabel = new JLabel("欢迎你:" + adminNo);
        welcomeLabel.setBounds(20, 30, 200, 30);
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.blue);

        //选项卡学生管理
        JPanel studentTab = new JPanel();
        studentTab.setLayout(null);

        jtf = new JTextField(10);
        jtf.setBounds(550, 10, 180, 28);

        findStudentButton = new JButton("查询");
        findStudentButton.setBounds(740, 10, 80, 28);
        findStudentButton.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton.setForeground(Color.blue);
        findStudentButton.addActionListener(this);

        JLabel jlb = new JLabel("请输入学号");
        jlb.setBounds(450, 10, 80, 28);
        jlb.setFont(new Font("楷体", Font.BOLD, 15));
        jlb.setForeground(Color.blue);

        //学生信息模型
        studentInfoModel = new StudentInfoModel("all");
        studentInfoTable = new JTable(studentInfoModel);
        studentInfoTable.setFont(new Font("楷体", Font.BOLD, 18));
        studentInfoTable.setForeground(Color.blue);
        studentInfoTable.setRowHeight(20);

        JScrollPane jsp = new JScrollPane(studentInfoTable);
        jsp.setBounds(0, 50, 1300, 500);

        addStudentButton = new JButton("添加");
        addStudentButton.setFont(new Font("楷体", Font.BOLD, 15));
        addStudentButton.setForeground(Color.blue);
        addStudentButton.setBounds(370, 600, 120, 28);
        addStudentButton.addActionListener(this);

        deleteStudentButton = new JButton("删除");
        deleteStudentButton.setFont(new Font("楷体", Font.BOLD, 15));
        deleteStudentButton.setForeground(Color.blue);
        deleteStudentButton.addActionListener(this);
        deleteStudentButton.setBounds(630, 600, 120, 28);

        studentTab.add(jtf);
        studentTab.add(findStudentButton);
        studentTab.add(jlb);
        studentTab.add(jsp);
        studentTab.add(addStudentButton);
        studentTab.add(deleteStudentButton);

        //教师选项卡
        JPanel jp1 = new JPanel();
        jp1.setLayout(null);

        teacherNoTextField = new JTextField(10);
        teacherNoTextField.setBounds(690, 10, 200, 28);

        findTeacherInfoButton = new JButton("查询");
        findTeacherInfoButton.setBounds(900, 10, 80, 28);
        findTeacherInfoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findTeacherInfoButton.setForeground(Color.blue);
        findTeacherInfoButton.addActionListener(this);

        JLabel teacherNoLabel = new JLabel("请输入教师号");
        teacherNoLabel.setBounds(560, 10, 120, 28);
        teacherNoLabel.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel.setForeground(Color.blue);

        //教师信息模型
        teacherInfoModel = new TeacherInfoModel("all");
        teacherInfoTable = new JTable(teacherInfoModel);
        teacherInfoTable.setFont(new Font("楷体", Font.BOLD, 15));
        teacherInfoTable.setForeground(Color.blue);
        teacherInfoTable.setRowHeight(24);
        JScrollPane jsp2 = new JScrollPane(teacherInfoTable);
        jsp2.setBounds(200, 50, 1100, 550);

        addTeacherButton = new JButton("添加");
        addTeacherButton.setFont(new Font("楷体", Font.BOLD, 15));
        addTeacherButton.setForeground(Color.blue);
        addTeacherButton.setBounds(40, 50, 120, 28);
        addTeacherButton.addActionListener(this);

        deleteTeacherButton = new JButton("删除");
        deleteTeacherButton.setFont(new Font("楷体", Font.BOLD, 15));
        deleteTeacherButton.setForeground(Color.blue);
        deleteTeacherButton.addActionListener(this);
        deleteTeacherButton.setBounds(40, 150, 120, 28);

        jp1.add(teacherNoTextField);
        jp1.add(findTeacherInfoButton);
        jp1.add(teacherNoLabel);
        jp1.add(jsp2);
        jp1.add(addTeacherButton);
        jp1.add(deleteTeacherButton);

        //选题管理选项卡
        JPanel jp2 = new JPanel();
        jp2.setLayout(null);

        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();

        jp3.setBounds(200, 0, 1100, 700);
        jp4.setBounds(200, 0, 1100, 700);
        jp5.setBounds(200, 0, 1100, 700);
        jp6.setBounds(200, 0, 1100, 700);

        chooseTopicContentButton = new JButton("学生选题表");
        chooseTopicContentButton.setFont(new Font("楷体", Font.BOLD, 15));
        chooseTopicContentButton.setForeground(Color.blue);
        chooseTopicContentButton.setBounds(40, 50, 120, 28);
        chooseTopicContentButton.addActionListener(this);

        TopicInfoContentButton = new JButton("查看选题");
        TopicInfoContentButton.setFont(new Font("楷体", Font.BOLD, 15));
        TopicInfoContentButton.setForeground(Color.blue);
        TopicInfoContentButton.setBounds(40, 125, 120, 28);
        TopicInfoContentButton.addActionListener(this);

        myTopicModel = new MyTopicModel("all");
        myTopicTable = new JTable(myTopicModel);
        myTopicTable.setRowHeight(24);
        myTopicTable.setFont(new Font("楷体", Font.BOLD, 15));
        myTopicTable.setForeground(Color.blue);

        JScrollPane jsp3 = new JScrollPane(myTopicTable);
        jsp3.setBounds(0, 50, 1100, 500);

        JLabel jlb9 = new JLabel("请输入学号");
        findChooseTopicFromStudentNoTextField = new JTextField(20);
        jlb9.setBounds(0, 560, 80, 28);
        jlb9.setFont(new Font("楷体", Font.BOLD, 15));
        jlb9.setForeground(Color.blue);

        findChooseTopicFromStudentNoTextField.setBounds(90, 560, 160, 24);
        findChooseTopicFromStudentNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        findChooseTopicFromStudentNoTextField.setForeground(Color.blue);

        findChooseTopicFromStudentNoButton = new JButton("查询");
        findChooseTopicFromStudentNoButton.addActionListener(this);
        findChooseTopicFromStudentNoButton.setBounds(255, 560, 80, 26);
        findChooseTopicFromStudentNoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findChooseTopicFromStudentNoButton.setForeground(Color.blue);

        JLabel jlb10 = new JLabel("请输入选题号");
        findChooseTopicFromTopicNoTextField = new JTextField(20);
        findChooseTopicFromTopicNoButton = new JButton("查询");
        findChooseTopicFromTopicNoButton.addActionListener(this);
        jlb10.setBounds(360, 560, 100, 28);
        jlb10.setFont(new Font("楷体", Font.BOLD, 15));
        jlb10.setForeground(Color.blue);

        findChooseTopicFromTopicNoTextField.setBounds(460, 560, 160, 28);
        findChooseTopicFromTopicNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        findChooseTopicFromTopicNoTextField.setForeground(Color.blue);

        findChooseTopicFromTopicNoButton.setBounds(625, 560, 80, 28);
        findChooseTopicFromTopicNoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findChooseTopicFromTopicNoButton.setForeground(Color.blue);

        JLabel teacherNoLabel5 = new JLabel("请输入教师号");
        findChooseTopicFromTeacherNoTextField = new JTextField(20);
        findChooseTopicFromTeacherNoButton = new JButton("查询");
        findChooseTopicFromTeacherNoButton.addActionListener(this);
        teacherNoLabel5.setBounds(720, 560, 100, 28);
        teacherNoLabel5.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel5.setForeground(Color.blue);

        findChooseTopicFromTeacherNoTextField.setBounds(820, 560, 160, 28);
        findChooseTopicFromTeacherNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        findChooseTopicFromTeacherNoTextField.setForeground(Color.blue);

        findChooseTopicFromTeacherNoButton.setBounds(985, 560, 80, 28);
        findChooseTopicFromTeacherNoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findChooseTopicFromTeacherNoButton.setForeground(Color.blue);

        jp3.setLayout(null);

        JLabel jlb18 = new JLabel("学生选题表");
        jlb18.setBounds(400, 0, 500, 50);
        jlb18.setFont(new Font("楷体", Font.BOLD, 50));
        jlb18.setForeground(Color.blue);

        jp3.add(jlb10);
        jp3.add(findChooseTopicFromTopicNoTextField);
        jp3.add(findChooseTopicFromTopicNoButton);
        jp3.add(teacherNoLabel5);
        jp3.add(findChooseTopicFromTeacherNoTextField);
        jp3.add(findChooseTopicFromTeacherNoButton);
        jp3.add(jsp3);
        jp3.add(jlb9);
        jp3.add(jlb18);
        jp3.add(findChooseTopicFromStudentNoTextField);
        jp3.add(findChooseTopicFromStudentNoButton);

        //jp4
        JLabel teacherNoLabel2 = new JLabel("请输入教师号");
        findTopicInfoFromTeacherNoTextField = new JTextField(20);
        findTopicInfoFromTeacherNoButton = new JButton("查询");
        findTopicInfoFromTeacherNoButton.addActionListener(this);
        teacherNoLabel2.setBounds(160, 580, 100, 28);
        teacherNoLabel2.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel2.setForeground(Color.blue);

        findTopicInfoFromTeacherNoTextField.setBounds(260, 580, 160, 28);
        findTopicInfoFromTeacherNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        findTopicInfoFromTeacherNoTextField.setForeground(Color.blue);

        findTopicInfoFromTeacherNoButton.setBounds(425, 580, 80, 28);
        findTopicInfoFromTeacherNoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findTopicInfoFromTeacherNoButton.setForeground(Color.blue);

        JLabel teacherNoLabel3 = new JLabel("请输入选题号");
        findTopicInfoFromTopicNoTextField = new JTextField(20);
        findTopicInfoFromTopicNoButton = new JButton("查询");
        findTopicInfoFromTopicNoButton.addActionListener(this);
        teacherNoLabel3.setBounds(530, 580, 100, 28);
        teacherNoLabel3.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel3.setForeground(Color.blue);

        findTopicInfoFromTopicNoTextField.setBounds(630, 580, 160, 28);
        findTopicInfoFromTopicNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        findTopicInfoFromTopicNoTextField.setForeground(Color.blue);

        findTopicInfoFromTopicNoButton.setBounds(795, 580, 80, 28);
        findTopicInfoFromTopicNoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findTopicInfoFromTopicNoButton.setForeground(Color.blue);

        jp4.setLayout(null);
        topicInfoModel = new TopicInfoModel("all");
        topicInfoTable = new JTable(topicInfoModel);
        topicInfoTable.setRowHeight(24);
        topicInfoTable.setFont(new Font("楷体", Font.BOLD, 15));
        topicInfoTable.setForeground(Color.blue);

        JScrollPane jsp4 = new JScrollPane(topicInfoTable);
        jsp4.setBounds(0, 50, 1100, 500);

        JLabel jlb19 = new JLabel("选题表");
        jlb19.setBounds(500, 0, 500, 50);
        jlb19.setFont(new Font("楷体", Font.BOLD, 50));
        jlb19.setForeground(Color.blue);

        jp4.add(jsp4);
        jp4.add(jlb19);
        jp4.add(teacherNoLabel2);
        jp4.add(findTopicInfoFromTeacherNoTextField);
        jp4.add(findTopicInfoFromTeacherNoButton);
        jp4.add(teacherNoLabel3);
        jp4.add(findTopicInfoFromTopicNoTextField);
        jp4.add(findTopicInfoFromTopicNoButton);
        jp4.setVisible(false);

        //jp6
        JLabel jlb11 = new JLabel("请输入选题号");
        JTextField teacherNoTextField1 = new JTextField(20);
        JButton addStudentButton1 = new JButton("查询");
        addStudentButton1.addActionListener(this);
        jlb11.setBounds(380, 10, 120, 28);
        jlb11.setFont(new Font("楷体", Font.BOLD, 15));
        jlb11.setForeground(Color.blue);

        teacherNoTextField1.setBounds(490, 10, 200, 24);
        teacherNoTextField1.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoTextField1.setForeground(Color.blue);

        addStudentButton1.setBounds(700, 10, 80, 26);
        addStudentButton1.setFont(new Font("楷体", Font.BOLD, 15));
        addStudentButton1.setForeground(Color.blue);

        jp6.setLayout(null);
        jp6.add(jlb11);
        jp6.add(teacherNoTextField1);
        jp6.add(addStudentButton1);
        jp6.setVisible(false);

        //
        jp2.add(chooseTopicContentButton);
        jp2.add(TopicInfoContentButton);
        jp2.add(jp3);
        jp2.add(jp4);
        jp2.add(jp6);
        JTabbedPane jtp = new JTabbedPane();
        jtp.add("学生管理", studentTab);
        jtp.add("教师管理", jp1);
        jtp.add("选题管理", jp2);

        this.add(welcomeLabel);
        this.add(jtp);
        this.setTitle("学生选题管理系统");
        this.setBackground(Color.gray);
        this.setResizable(false);
        this.setLocation(60, 10);
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*学生按钮*/
        if (e.getSource() == findStudentButton) {
            String studentNo = this.jtf.getText().trim();
            if (studentNo.length() == 0) {
                studentNo = "all";
            }
            studentInfoModel = new StudentInfoModel(studentNo);
            studentInfoTable.setModel(studentInfoModel);
            studentInfoTable.revalidate();
        } else if (e.getSource() == addStudentButton) {
            StudentAddDialog studentAddDialog = new StudentAddDialog(this, "添加学生信息", true);
            studentInfoModel = new StudentInfoModel("all");
            studentInfoTable.setModel(studentInfoModel);
            studentInfoTable.revalidate();
        } else if (e.getSource() == deleteStudentButton) {
            int rowNum = this.studentInfoTable.getSelectedRow(); //返回用户点中的行，如果一行没选，则返回-1
            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择一行");
            } else {
                String studentNo = this.studentInfoTable.getValueAt(rowNum, 0).toString();
                boolean deleteStudentInfoSuccess = studentInfoModel.deleteStudentInfo(studentNo);
                if (deleteStudentInfoSuccess) {
                    String deleteAccountsql = "delete from account where userNo=?";
                    DBHelper dbHelper = new DBHelper(deleteAccountsql);
                    try {
                        dbHelper.pst.setString(1, studentNo);
                        dbHelper.pst.executeUpdate();
                        JOptionPane.showMessageDialog(this, "删除学生用户成功");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this, "删除学生账户信息失败");
                    } finally {
                        dbHelper.close();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "删除学生信息失败");
                }
                studentInfoModel = new StudentInfoModel("all");
                studentInfoTable.setModel(studentInfoModel);
                studentInfoTable.revalidate();
            }
        } else if (e.getSource() == findTeacherInfoButton) {
            String teacherNo = this.teacherNoTextField.getText().trim();
            if (teacherNo.length() == 0) {
                teacherNo = "all";
            }
            teacherInfoModel = new TeacherInfoModel(teacherNo);
            teacherInfoTable.setModel(teacherInfoModel);
            teacherInfoTable.revalidate();
        } else if (e.getSource() == addTeacherButton) {
            TeacherAddDialog teacherAddDialog = new TeacherAddDialog(this, "添加学生信息", true);
            teacherInfoModel = new TeacherInfoModel("all");
            teacherInfoTable.setModel(teacherInfoModel);
            teacherInfoTable.revalidate();
        } else if (e.getSource() == deleteTeacherButton) {
            int rowNum = this.teacherInfoTable.getSelectedRow();
            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择中一行");
            } else {
                String teacherNo = this.teacherInfoTable.getValueAt(rowNum, 0).toString();
                boolean deleteTeacherInfoSuccess = teacherInfoModel.deleteStudentInfo(teacherNo);
                if (deleteTeacherInfoSuccess) {
                    String deleteAccountsql = "delete from account where userNo=?";
                    DBHelper dbHelper = new DBHelper(deleteAccountsql);
                    try {
                        dbHelper.pst.setString(1, teacherNo);
                        dbHelper.pst.executeUpdate();
                        JOptionPane.showMessageDialog(this, "删除教师用户成功");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this, "删除教师账户信息失败");
                    } finally {
                        dbHelper.close();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "删除教师账户信息失败");
                }
                teacherInfoModel = new TeacherInfoModel("all");
                teacherInfoTable.setModel(teacherInfoModel);
                teacherInfoTable.revalidate();
            }
            //选题按钮
        } else if (e.getSource() == chooseTopicContentButton) {
            jp3.setVisible(true);
            jp4.setVisible(false);
            jp5.setVisible(false);
            jp6.setVisible(false);

            myTopicModel = new MyTopicModel("all");
            myTopicTable.setModel(myTopicModel);
            myTopicTable.revalidate();


        } else if (e.getSource() == TopicInfoContentButton) {
            jp4.setVisible(true);
            jp5.setVisible(false);
            jp3.setVisible(false);
            jp6.setVisible(false);

            topicInfoModel = new TopicInfoModel("all");
            topicInfoTable.setModel(topicInfoModel);
            topicInfoTable.revalidate();

        } else if (e.getSource() == findChooseTopicFromStudentNoButton) {
            myTopicModel = new MyTopicModel(findChooseTopicFromStudentNoTextField.getText().trim());
            myTopicTable.setModel(myTopicModel);
            myTopicTable.revalidate();
        } else if (e.getSource() == findChooseTopicFromTopicNoButton) {
            myTopicModel.findChooseTopicFromTopicNo(findChooseTopicFromTopicNoTextField.getText().trim());
            myTopicTable.setModel(myTopicModel);
            myTopicTable.revalidate();

        } else if (e.getSource() == findChooseTopicFromTeacherNoButton) {
            myTopicModel.findChooseTopicFromTeacherNo(findChooseTopicFromTeacherNoTextField.getText().trim());
            myTopicTable.setModel(myTopicModel);
            myTopicTable.revalidate();

        } else if (e.getSource() == findTopicInfoFromTeacherNoButton) {
            topicInfoModel.findTopicInfoFromTeacherNo(findTopicInfoFromTeacherNoTextField.getText().trim());
            topicInfoTable.setModel(topicInfoModel);
            topicInfoTable.revalidate();

        } else if (e.getSource() == findTopicInfoFromTopicNoButton) {
            topicInfoModel.findTopicInfoFromTopicNo(findTopicInfoFromTopicNoTextField.getText().trim());
            topicInfoTable.setModel(topicInfoModel);
            topicInfoTable.revalidate();
        }
    }
}
