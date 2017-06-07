package cn.earthchen.views.root;


import cn.earthchen.db.DBHelper;
import cn.earthchen.views.student.StudentInfoModel;
import cn.earthchen.views.teacher.TeacherInfoModel;

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

    JTabbedPane jtp;

    JPanel studentTab, jp1, jp2, jp3, jp4, jp5, jp6;
    JTable jtb, jtb2, jtb3, jtb4, jtb5, jtb6;
    JScrollPane jsp, jsp2, jsp3, jsp4, jsp5, jsp6;
    JLabel jlb, teacherNoLabel, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb10, jlb11, teacherNoLabel5, welcomeLabel;
    JLabel jlb12, jlb13, jlb14, jlb15, jlb16, jlb17, jlb18, jlb19, teacherNoLabel0, teacherNoLabel1, teacherNoLabel2, teacherNoLabel3, teacherNoLabel4;
    JButton jb0,/*选项卡学生*/
            findStudentButton, addStudentButton, deleteStudentButton,/*选项卡教师*/
            findTeacherInfoButton, addTeacherButton, deleteTeacherButton/*选项卡选课*/, findStudentButton1, findStudentButton2, findStudentButton3, findStudentButton4, findStudentButton5, findStudentButton6, findStudentButton7;
    JButton addStudentButton0, addStudentButton1, addStudentButton2, addStudentButton3, addStudentButton4, addStudentButton5, addStudentButton6;
    JTextField jtf, teacherNoTextField, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, teacherNoTextField0, teacherNoTextField1;
    JTextField teacherNoTextField2, teacherNoTextField3, teacherNoTextField4, teacherNoTextField5, teacherNoTextField6, teacherNoTextField7, teacherNoTextField8, teacherNoTextField9, jtf20, jtf21, jtf22, jtf23;

    /*
    RootModel rm=null;
    TeacherModel tm=null;
    CurModel cm=null;
    SCModel scm=null;
    ChoModel chm=null;
*/
    StudentInfoModel studentInfoModel;
    TeacherInfoModel teacherInfoModel;
    JTable studentInfoTabel;
    JTable teacherInfoTabel;

    String adminNo;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RootView rootv = new RootView("admin");
    }

    public RootView(String adminNo) {

        System.out.println(adminNo);
        this.adminNo = adminNo;
        welcomeLabel = new JLabel("欢迎你:" + adminNo);
        welcomeLabel.setBounds(20, 30, 200, 30);
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.blue);

        //选项卡学生管理
        studentTab = new JPanel();
        studentTab.setLayout(null);

        jtf = new JTextField(10);
        jtf.setBounds(550, 10, 180, 28);

        findStudentButton = new JButton("查询");
        findStudentButton.setBounds(740, 10, 80, 28);
        findStudentButton.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton.setForeground(Color.blue);
        findStudentButton.addActionListener(this);

        jlb = new JLabel("请输入学号");
        jlb.setBounds(450, 10, 80, 28);
        jlb.setFont(new Font("楷体", Font.BOLD, 15));
        jlb.setForeground(Color.blue);

        //学生信息模型
        studentInfoModel = new StudentInfoModel("all");
        studentInfoTabel = new JTable(studentInfoModel);
        studentInfoTabel.setFont(new Font("楷体", Font.BOLD, 18));
        studentInfoTabel.setForeground(Color.blue);
        studentInfoTabel.setRowHeight(20);

        jsp = new JScrollPane(studentInfoTabel);
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
        jp1 = new JPanel();
        jp1.setLayout(null);

        teacherNoTextField = new JTextField(10);
        teacherNoTextField.setBounds(690, 10, 200, 28);

        findTeacherInfoButton = new JButton("查询");
        findTeacherInfoButton.setBounds(900, 10, 80, 28);
        findTeacherInfoButton.setFont(new Font("楷体", Font.BOLD, 15));
        findTeacherInfoButton.setForeground(Color.blue);
        findTeacherInfoButton.addActionListener(this);

        teacherNoLabel = new JLabel("请输入教师号");
        teacherNoLabel.setBounds(560, 10, 120, 28);
        teacherNoLabel.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel.setForeground(Color.blue);

        //教师信息模型
        teacherInfoModel = new TeacherInfoModel("all");
        teacherInfoTabel = new JTable(teacherInfoModel);
        teacherInfoTabel.setFont(new Font("楷体", Font.BOLD, 15));
        teacherInfoTabel.setForeground(Color.blue);
        teacherInfoTabel.setRowHeight(24);
        jsp2 = new JScrollPane(teacherInfoTabel);
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


        //选课管理选项卡
        jp2 = new JPanel();
        jp2.setLayout(null);

        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();

        jp3.setBounds(200, 0, 1100, 700);
        jp4.setBounds(200, 0, 1100, 700);
        jp5.setBounds(200, 0, 1100, 700);
        jp6.setBounds(200, 0, 1100, 700);


        findStudentButton1 = new JButton("学生选课表");
        findStudentButton1.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton1.setForeground(Color.blue);
        findStudentButton1.setBounds(40, 50, 120, 28);
        findStudentButton1.addActionListener(this);

        findStudentButton2 = new JButton("查看课程");
        findStudentButton2.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton2.setForeground(Color.blue);
        findStudentButton2.setBounds(40, 125, 120, 28);
        findStudentButton2.addActionListener(this);

        jb0 = new JButton("选课管理");
        jb0.setFont(new Font("楷体", Font.BOLD, 15));
        jb0.setForeground(Color.blue);
        jb0.setBounds(40, 200, 120, 28);
        jb0.addActionListener(this);


//        scm=new SCModel();
//        jtb3=new JTable(scm);
//        jtb3.setRowHeight(24);
//        jtb3.setFont(new Font("楷体",Font.BOLD,15));
//        jtb3.setForeground(Color.blue);
//        jsp3=new JScrollPane(jtb3);
//        jsp3.setBounds(0,50,1100,500);

        jlb9 = new JLabel("请输入学号");
        jtf22 = new JTextField(20);
        jlb9.setBounds(0, 560, 80, 28);
        jlb9.setFont(new Font("楷体", Font.BOLD, 15));
        jlb9.setForeground(Color.blue);

        jtf22.setBounds(90, 560, 160, 24);
        jtf22.setFont(new Font("楷体", Font.BOLD, 15));
        jtf22.setForeground(Color.blue);

        findStudentButton5 = new JButton("查询");
        findStudentButton5.addActionListener(this);
        findStudentButton5.setBounds(255, 560, 80, 26);
        findStudentButton5.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton5.setForeground(Color.blue);


        jlb10 = new JLabel("请输入课程号");
        jtf23 = new JTextField(20);
        addStudentButton0 = new JButton("查询");
        addStudentButton0.addActionListener(this);
        jlb10.setBounds(360, 560, 100, 28);
        jlb10.setFont(new Font("楷体", Font.BOLD, 15));
        jlb10.setForeground(Color.blue);

        jtf23.setBounds(460, 560, 160, 28);
        jtf23.setFont(new Font("楷体", Font.BOLD, 15));
        jtf23.setForeground(Color.blue);

        addStudentButton0.setBounds(625, 560, 80, 28);
        addStudentButton0.setFont(new Font("楷体", Font.BOLD, 15));
        addStudentButton0.setForeground(Color.blue);


        teacherNoLabel5 = new JLabel("请输入教师号");
        jtf21 = new JTextField(20);
        addStudentButton5 = new JButton("查询");
        addStudentButton5.addActionListener(this);
        teacherNoLabel5.setBounds(720, 560, 100, 28);
        teacherNoLabel5.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel5.setForeground(Color.blue);

        jtf21.setBounds(820, 560, 160, 28);
        jtf21.setFont(new Font("楷体", Font.BOLD, 15));
        jtf21.setForeground(Color.blue);

        addStudentButton5.setBounds(985, 560, 80, 28);
        addStudentButton5.setFont(new Font("楷体", Font.BOLD, 15));
        addStudentButton5.setForeground(Color.blue);


        findStudentButton7 = new JButton("退选");
        findStudentButton7.setBounds(220, 600, 120, 26);
        findStudentButton7.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton7.setForeground(Color.blue);
        findStudentButton7.addActionListener(this);


        jp3.setLayout(null);

        jlb18 = new JLabel("学生选课表");
        jlb18.setBounds(400, 0, 500, 50);
        jlb18.setFont(new Font("楷体", Font.BOLD, 50));
        jlb18.setForeground(Color.blue);


        jp3.add(jlb10);
        jp3.add(jtf23);
        jp3.add(addStudentButton0);
        jp3.add(teacherNoLabel5);
        jp3.add(jtf21);
        jp3.add(addStudentButton5);
//        jp3.add(jsp3);
        jp3.add(jlb9);
        jp3.add(jlb18);
        jp3.add(jtf22);
        jp3.add(findStudentButton5);
        jp3.add(findStudentButton7);

        //jp4

        teacherNoLabel2 = new JLabel("请输入教师号");
        teacherNoTextField8 = new JTextField(20);
        addStudentButton2 = new JButton("查询");
        addStudentButton2.addActionListener(this);
        teacherNoLabel2.setBounds(160, 580, 100, 28);
        teacherNoLabel2.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel2.setForeground(Color.blue);

        teacherNoTextField8.setBounds(260, 580, 160, 28);
        teacherNoTextField8.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoTextField8.setForeground(Color.blue);

        addStudentButton2.setBounds(425, 580, 80, 28);
        addStudentButton2.setFont(new Font("楷体", Font.BOLD, 15));
        addStudentButton2.setForeground(Color.blue);

        teacherNoLabel3 = new JLabel("请输入课程号");
        teacherNoTextField9 = new JTextField(20);
        addStudentButton3 = new JButton("查询");
        addStudentButton3.addActionListener(this);
        teacherNoLabel3.setBounds(530, 580, 100, 28);
        teacherNoLabel3.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel3.setForeground(Color.blue);

        teacherNoTextField9.setBounds(630, 580, 160, 28);
        teacherNoTextField9.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoTextField9.setForeground(Color.blue);

        addStudentButton3.setBounds(795, 580, 80, 28);
        addStudentButton3.setFont(new Font("楷体", Font.BOLD, 15));
        addStudentButton3.setForeground(Color.blue);

        jp4.setLayout(null);
//        cm=new CurModel();
//        jtb4=new JTable(cm);
//        jtb4.setRowHeight(24);
//        jtb4.setFont(new Font("楷体",Font.BOLD,15));
//        jtb4.setForeground(Color.blue);
        jsp4 = new JScrollPane(jtb4);
        jsp4.setBounds(0, 50, 1100, 500);

        jlb19 = new JLabel("课程表");
        jlb19.setBounds(500, 0, 500, 50);
        jlb19.setFont(new Font("楷体", Font.BOLD, 50));
        jlb19.setForeground(Color.blue);

        jp4.add(jsp4);
        jp4.add(jlb19);
        jp4.add(teacherNoLabel2);
        jp4.add(teacherNoTextField8);
        jp4.add(addStudentButton2);
        jp4.add(teacherNoLabel3);
        jp4.add(teacherNoTextField9);
        jp4.add(addStudentButton3);
        jp4.setVisible(false);

        //jp5
        jp5.setLayout(null);

        teacherNoLabel = new JLabel("　课程编号");
        teacherNoLabel.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoLabel.setForeground(Color.blue);
        teacherNoLabel.setBounds(10, 50, 100, 28);

        jlb3 = new JLabel("课程名称");
        jlb3.setFont(new Font("楷体", Font.BOLD, 15));
        jlb3.setForeground(Color.blue);
        jlb3.setBounds(360, 50, 100, 28);

        jlb4 = new JLabel("任课教师号");
        jlb4.setFont(new Font("楷体", Font.BOLD, 15));
        jlb4.setForeground(Color.blue);
        jlb4.setBounds(700, 50, 100, 28);

        jlb5 = new JLabel("任课教师名");
        jlb5.setFont(new Font("楷体", Font.BOLD, 15));
        jlb5.setForeground(Color.blue);
        jlb5.setBounds(10, 100, 100, 28);

        jlb6 = new JLabel("　　学分");
        jlb6.setFont(new Font("楷体", Font.BOLD, 15));
        jlb6.setForeground(Color.blue);
        jlb6.setBounds(360, 100, 100, 28);

        jlb7 = new JLabel("　是否选修");
        jlb7.setFont(new Font("楷体", Font.BOLD, 15));
        jlb7.setBounds(700, 100, 100, 28);
        jlb7.setForeground(Color.blue);

        jlb8 = new JLabel("　上课时间");
        jlb8.setFont(new Font("楷体", Font.BOLD, 15));
        jlb8.setBounds(10, 150, 100, 28);
        jlb8.setForeground(Color.blue);

        jlb9 = new JLabel("上课地点");
        jlb9.setFont(new Font("楷体", Font.BOLD, 15));
        jlb9.setBounds(360, 150, 100, 28);
        jlb9.setForeground(Color.blue);

        jlb10 = new JLabel("　截止时间");
        jlb10.setFont(new Font("楷体", Font.BOLD, 15));
        jlb10.setBounds(700, 150, 100, 28);
        jlb10.setForeground(Color.blue);

        jlb16 = new JLabel("-");
        jlb16.setFont(new Font("楷体", Font.BOLD, 20));
        jlb16.setBounds(845, 150, 45, 28);
        jlb16.setForeground(Color.blue);

        jlb17 = new JLabel("-");
        jlb17.setFont(new Font("楷体", Font.BOLD, 20));
        jlb17.setBounds(920, 150, 45, 28);
        jlb17.setForeground(Color.blue);

        jtf2 = new JTextField(20);
        jtf2.setBounds(100, 52, 200, 24);
        jtf2.setFont(new Font("楷体", Font.BOLD, 15));
        jtf2.setForeground(Color.blue);

        jtf3 = new JTextField(20);
        jtf3.setBounds(440, 52, 200, 24);
        jtf3.setFont(new Font("楷体", Font.BOLD, 15));
        jtf3.setForeground(Color.blue);

        jtf4 = new JTextField(20);
        jtf4.setBounds(790, 52, 200, 24);
        jtf4.setFont(new Font("楷体", Font.BOLD, 15));
        jtf4.setForeground(Color.blue);

        jtf5 = new JTextField(20);
        jtf5.setBounds(100, 102, 200, 24);
        jtf5.setFont(new Font("楷体", Font.BOLD, 15));
        jtf5.setForeground(Color.blue);

        jtf6 = new JTextField(20);
        jtf6.setBounds(440, 102, 200, 24);
        jtf6.setFont(new Font("楷体", Font.BOLD, 15));
        jtf6.setForeground(Color.blue);

        jtf7 = new JTextField(20);
        jtf7.setBounds(790, 102, 200, 24);
        jtf7.setFont(new Font("楷体", Font.BOLD, 15));
        jtf7.setForeground(Color.blue);

        jtf8 = new JTextField(20);
        jtf8.setBounds(100, 152, 200, 24);
        jtf8.setFont(new Font("楷体", Font.BOLD, 15));
        jtf8.setForeground(Color.blue);

        jtf9 = new JTextField(20);
        jtf9.setBounds(440, 152, 200, 24);
        jtf9.setFont(new Font("楷体", Font.BOLD, 15));
        jtf9.setForeground(Color.blue);

        teacherNoTextField0 = new JTextField(20);
        teacherNoTextField0.setBounds(790, 152, 50, 24);
        teacherNoTextField0.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoTextField0.setForeground(Color.blue);

        teacherNoTextField6 = new JTextField(20);
        teacherNoTextField6.setBounds(865, 152, 50, 24);
        teacherNoTextField6.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoTextField6.setForeground(Color.blue);

        teacherNoTextField7 = new JTextField(20);
        teacherNoTextField7.setBounds(940, 152, 50, 24);
        teacherNoTextField7.setFont(new Font("楷体", Font.BOLD, 15));
        teacherNoTextField7.setForeground(Color.blue);

        findStudentButton4 = new JButton("发布选课");
        findStudentButton4.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton4.setForeground(Color.blue);
        findStudentButton4.setBounds(10, 200, 120, 28);
        findStudentButton4.addActionListener(this);

        findStudentButton6 = new JButton("重置");
        findStudentButton6.setFont(new Font("楷体", Font.BOLD, 15));
        findStudentButton6.setForeground(Color.blue);
        findStudentButton6.setBounds(135, 200, 120, 28);
        findStudentButton6.addActionListener(this);

        jp5.add(teacherNoLabel);
        jp5.add(jtf2);
        jp5.add(jlb3);
        jp5.add(jtf3);
        jp5.add(jlb4);
        jp5.add(jtf4);
        jp5.add(jlb5);
        jp5.add(jlb6);
        jp5.add(jlb7);
        jp5.add(jlb8);
        jp5.add(jlb9);
        jp5.add(jlb10);
        jp5.add(jlb16);
        jp5.add(jlb17);
        jp5.add(jtf5);
        jp5.add(jtf6);
        jp5.add(jtf7);
        jp5.add(jtf8);
        jp5.add(jtf9);
        jp5.add(teacherNoTextField0);
        jp5.add(teacherNoTextField6);
        jp5.add(teacherNoTextField7);
        jp5.add(findStudentButton4);
        jp5.add(findStudentButton6);
        jp5.setVisible(false);

        //jp6

        jlb11 = new JLabel("请输入课程号");
        teacherNoTextField1 = new JTextField(20);
        addStudentButton1 = new JButton("查询");
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
//        chm=new ChoModel();
//        jtb6=new JTable(chm);
//        jtb6.setRowHeight(24);
//        jtb6.setFont(new Font("楷体",Font.BOLD,15));
//        jtb6.setForeground(Color.blue);
//        jsp6=new JScrollPane(jtb6);
//        jsp6.setBounds(0,50,1100,500);


        addStudentButton6 = new JButton("删除待选课程");
        addStudentButton6.setBounds(0, 600, 200, 30);
        addStudentButton6.setFont(new Font("楷体", Font.BOLD, 20));
        addStudentButton6.setForeground(Color.blue);
        addStudentButton6.addActionListener(this);

        teacherNoLabel0 = new JLabel("待选课程表");
        teacherNoLabel0.setBounds(10, 20, 120, 28);
        teacherNoLabel0.setFont(new Font("楷体", Font.BOLD, 20));
        teacherNoLabel0.setForeground(Color.blue);


//        jp6.add(jsp6);
        jp6.add(jlb11);
        jp6.add(teacherNoLabel0);
        jp6.add(teacherNoTextField1);
        jp6.add(addStudentButton1);
        jp6.add(addStudentButton6);
        jp6.setVisible(false);

        //

        jp2.add(findStudentButton1);
        jp2.add(findStudentButton2);
        jp2.add(jb0);
        jp2.add(jp3);
        jp2.add(jp4);
        jp2.add(jp5);
        jp2.add(jp6);
        //
        jtp = new JTabbedPane();
        jtp.add("学生管理", studentTab);
        jtp.add("教师管理", jp1);
        jtp.add("选课管理", jp2);

        this.add(welcomeLabel);
        this.add(jtp);
        this.setTitle("学生选课管理系统");
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
        String sql;
        if (e.getSource() == findStudentButton) {
            String studentNo = this.jtf.getText().trim();
            if (studentNo.length() == 0) {
                studentNo = "all";
            }
            studentInfoModel = new StudentInfoModel(studentNo);
            studentInfoTabel.setModel(studentInfoModel);
            studentInfoTabel.revalidate();
        } else if (e.getSource() == addStudentButton) {
            StudentAddDialog studentAddDialog = new StudentAddDialog(this, "添加学生信息", true);
            studentInfoModel = new StudentInfoModel("all");
            studentInfoTabel.setModel(studentInfoModel);
            studentInfoTabel.revalidate();
        } else if (e.getSource() == deleteStudentButton) {
            int rowNum = this.studentInfoTabel.getSelectedRow(); //返回用户点中的行，如果一行没选，则返回-1
            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择一行");
            } else {
                String studentNo = this.studentInfoTabel.getValueAt(rowNum, 0).toString();
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
                studentInfoTabel.setModel(studentInfoModel);
                studentInfoTabel.revalidate();
            }
        } else if (e.getSource() == findTeacherInfoButton) {
            String teacherNo = this.teacherNoTextField.getText().trim();
            if (teacherNo.length() == 0) {
                teacherNo = "all";
            }
            teacherInfoModel = new TeacherInfoModel(teacherNo);
            teacherInfoTabel.setModel(teacherInfoModel);
            teacherInfoTabel.revalidate();
        } else if (e.getSource() == addTeacherButton) {
            TeacherAddDialog teacherAddDialog = new TeacherAddDialog(this, "添加学生信息", true);
            teacherInfoModel = new TeacherInfoModel("all");
            teacherInfoTabel.setModel(teacherInfoModel);
            teacherInfoTabel.revalidate();
        } else if (e.getSource() == deleteTeacherButton) {
            int rowNum = this.teacherInfoTabel.getSelectedRow();
            if (rowNum == -1) {
                JOptionPane.showMessageDialog(this, "请选择中一行");
            } else {
                String teacherNo = this.teacherInfoTabel.getValueAt(rowNum, 0).toString();
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
                teacherInfoTabel.setModel(teacherInfoModel);
                teacherInfoTabel.revalidate();
            }
        /*选课按钮
        else if(e.getSource()==findStudentButton1){
            jp3.setVisible(true);
            jp4.setVisible(false);
            jp5.setVisible(false);
            jp6.setVisible(false);

            scm=new SCModel();
            jtb3.setModel(scm);
        }else if(e.getSource()==findStudentButton2){
            jp4.setVisible(true);
            jp5.setVisible(false);
            jp3.setVisible(false);
            jp6.setVisible(false);
            cm=new CurModel();
            jtb4.setModel(cm);

        }else if(e.getSource()==findStudentButton3){
            jtf2.setText("");
            jtf3.setText("");
            jtf4.setText("");
            jtf5.setText("");
            jtf6.setText("");
            jtf7.setText("");
            jtf8.setText("");
            jtf9.setText("");
            teacherNoTextField0.setText("");
            teacherNoTextField6.setText("");
            teacherNoTextField7.setText("");

            jp5.setVisible(true);
            jp4.setVisible(false);
            jp3.setVisible(false);
            jp6.setVisible(false);
        }else if(e.getSource()==jb0){
            jp6.setVisible(true);
            jp4.setVisible(false);
            jp3.setVisible(false);
            jp5.setVisible(false);

            chm=new ChoModel();
            jtb6.setModel(chm);

        }else if(e.getSource()==findStudentButton4){
            if(jtf8.getText().length()!=0 && jtf2.getText().length()!=0 && jtf3.getText().length()!=0 && jtf4.getText().length()!=0 && jtf5.getText().length()!=0 && jtf6.getText().length()!=0 && jtf7.getText().length()!=0 && jtf9.getText().length()!=0 && teacherNoTextField0.getText().length()!=0 && teacherNoTextField6.getText().length()!=0 && teacherNoTextField7.getText().length()!=0){

                if(jtf7.getText().equals("1") && Float.parseFloat(jtf6.getText())<1){
                    JOptionPane.showMessageDialog(this,"选修课程学分不得低于1学分");
                }else{
                    sql="insert into choosecourse values(?,?,?,?,?,?,?,?,?,?)";
                    String Time=teacherNoTextField0.getText().trim()+"-"+teacherNoTextField6.getText().trim()+"-"+teacherNoTextField7.getText().trim();
                    String[] paras={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText(),jtf8.getText(),jtf9.getText(),Time,"0"};
                    chm=new ChoModel();
                    if(chm.updateCourse(sql, paras)!=true){
                        JOptionPane.showMessageDialog(this, "发布失败");
                    }else{
                        JOptionPane.showMessageDialog(this, "发布成功");
                        jtf2.setText("");
                        jtf3.setText("");
                        jtf4.setText("");
                        jtf5.setText("");
                        jtf6.setText("");
                        jtf7.setText("");
                        jtf8.setText("");
                        jtf9.setText("");
                        teacherNoTextField0.setText("");
                        teacherNoTextField6.setText("");
                        teacherNoTextField7.setText("");

                        jtb6.setModel(chm);
                    }
                }
            }else{
                if(jtf2.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"课程号不能为空");
                }else if(jtf3.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"课程名不能为空");
                }else if(jtf4.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"任课教师号不能为空");
                }else if(jtf5.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"任课教师不能为空");
                }else if(jtf6.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"学分不能为空");
                }else if(jtf7.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"是否选修不能为空");
                }else if(jtf8.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"上课时间不能为空");
                }else if(jtf9.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"上课地点不能为空");
                }else if(teacherNoTextField0.getText().length()==0){
                    JOptionPane.showMessageDialog(this,"截止日期不能为空");
                }else{
                    JOptionPane.showMessageDialog(this,"时间不正确");
                }
            }
        }else if(e.getSource()==findStudentButton6){
            jtf2.setText("");
            jtf3.setText("");
            jtf4.setText("");
            jtf5.setText("");
            jtf6.setText("");
            jtf7.setText("");
            jtf8.setText("");
            jtf9.setText("");
            teacherNoTextField0.setText("");
            teacherNoTextField6.setText("");
            teacherNoTextField7.setText("");
        }else if(e.getSource()==findStudentButton7){
            int rowNum=this.jtb3.getSelectedRow();
            if(rowNum==-1){
                JOptionPane.showMessageDialog(this, "请选择一行");
            }else{
                String SnoId=(String)scm.getValueAt(rowNum,0);
                String CnoId=(String)scm.getValueAt(rowNum,1);
                sql="delete from SC where 学号=? and 课程号=?";
                String[] paras={SnoId,CnoId};
                SCModel temp=new SCModel();
                temp.updateSC(sql, paras);
                scm=new SCModel();
                jtb3.setModel(scm);
            }
        }else if(e.getSource()==findStudentButton8){
            int rowNum=this.jtb3.getSelectedRow();
            if(rowNum==-1){
                JOptionPane.showMessageDialog(this, "请选择一行");
            }else{
                new updateTime(this,"考试时间修改",true,scm,rowNum);
                scm=new SCModel();
                jtb3.setModel(scm);
            }
        }else if(e.getSource()==findStudentButton9){
            int rowNum=this.jtb3.getSelectedRow();
            if(rowNum==-1){
                JOptionPane.showMessageDialog(this, "请选择一行");
            }else{
                new updataGrade(this,"成绩修改",true,scm,rowNum);
                scm=new SCModel();
                jtb3.setModel(scm);
            }
        }else if(e.getSource()==jb23){
            String mysql=null;
            if(teacherNoTextField8.getText().length()==0 && teacherNoTextField9.getText().length()==0){
                mysql="select * from course";
            }else if(teacherNoTextField8.getText().length()==0 && teacherNoTextField9.getText().length()!=0){
                mysql="select * from course where 课程号 like'%"+teacherNoTextField9.getText()+"%'";
            }
            else if(teacherNoTextField8.getText().length()!=0 && teacherNoTextField9.getText().length()!=0){
                mysql="select * from course where 课程号 like'%"+teacherNoTextField9.getText()+"%' and 任课教师号 like '%"+teacherNoTextField8.getText()+"%'";
            }

            cm=new CurModel(mysql);
            jtb4.setModel(cm);
        }else if(e.getSource()==jb22){
            String mysql=null;
            if(teacherNoTextField8.getText().length()==0 && teacherNoTextField9.getText().length()==0){
                mysql="select * from course";
            }else if(teacherNoTextField8.getText().length()!=0 && teacherNoTextField9.getText().length()==0){
                mysql="select * from course where 任课教师号 like'%"+teacherNoTextField8.getText()+"%'";
            }
            else if(teacherNoTextField8.getText().length()!=0 && teacherNoTextField9.getText().length()!=0){
                mysql="select * from course where 课程号 like'%"+teacherNoTextField9.getText()+"%' and 任课教师号 like '%"+teacherNoTextField8.getText()+"%'";
            }
            cm=new CurModel(mysql);
            jtb4.setModel(cm);
        }else if(e.getSource()==jb21){
            if(teacherNoTextField1.getText().length()==0){
                sql="select * from choosecourse";
            }else {
                sql="select * from choosecourse where 课程号 like'%"+teacherNoTextField1.getText()+"%'";
            }
            chm=new ChoModel(sql);
            jtb6.setModel(chm);
        }else if(e.getSource()==findStudentButton5){
            String mysql=null;
            if(jtf22.getText().length()==0 && jtf23.getText().length()==0 && jtf21.getText().length()==0){
                mysql="select * from SC";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()==0 && jtf21.getText().length()==0){
                mysql="select * from SC where 学号 like '%"+jtf22.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()!=0 && jtf21.getText().length()==0){
                mysql="select * from SC where 学号 like '%"+jtf22.getText()+"%' and 课程号 like '%"+jtf23.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()==0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 学号 like '%"+jtf22.getText()+"%'and 任课教师号 like'%"+jtf21.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()!=0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 学号 like '%"+jtf22.getText()+"%' and 课程号 like '%"+jtf23.getText()+"%' and 任课教师号 like'%"+jtf21.getText()+"%'";
            }
            scm=new SCModel(mysql);
            jtb3.setModel(scm);
        }else if(e.getSource()==jb20){
            String mysql=null;
            if(jtf22.getText().length()==0 && jtf23.getText().length()==0 && jtf21.getText().length()==0){
                mysql="select * from SC";
            }else if(jtf22.getText().length()==0 && jtf23.getText().length()!=0 && jtf21.getText().length()==0){
                mysql="select * from SC where 课程号 like '%"+jtf23.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()!=0 && jtf21.getText().length()==0){
                mysql="select * from SC where 课程号 like '%"+jtf23.getText()+"%' and 学号 like '%"+jtf22.getText()+"%'";
            }else if(jtf22.getText().length()==0 && jtf23.getText().length()!=0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 课程号 like '%"+jtf23.getText()+"%'and 任课教师号 like'%"+jtf21.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()!=0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 课程号 like '%"+jtf23.getText()+"%' and 学号 like '%"+jtf22.getText()+"%' and 任课教师号 like'%"+jtf21.getText()+"%'";
            }
            scm=new SCModel(mysql);
            jtb3.setModel(scm);
        }else if(e.getSource()==jb25){
            String mysql=null;
            if(jtf22.getText().length()==0 && jtf23.getText().length()==0 && jtf21.getText().length()==0){
                mysql="select * from SC";
            }else if(jtf22.getText().length()==0 && jtf23.getText().length()==0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 任课教师号 like '%"+jtf21.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()==0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 学号 like '%"+jtf22.getText()+"%' and 任课教师号 like '%"+jtf21.getText()+"%'";
            }else if(jtf22.getText().length()==0 && jtf23.getText().length()!=0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 课程号 like '%"+jtf23.getText()+"%'and 任课教师号 like'%"+jtf21.getText()+"%'";
            }else if(jtf22.getText().length()!=0 && jtf23.getText().length()!=0 && jtf21.getText().length()!=0){
                mysql="select * from SC where 课程号 like '%"+jtf23.getText()+"%' and 学号 like '%"+jtf22.getText()+"%' and 任课教师号 like'%"+jtf21.getText()+"%'";
            }
            scm=new SCModel(mysql);
            jtb3.setModel(scm);
        }else if(e.getSource()==jb26){
            int rowNum=this.jtb6.getSelectedRow();
            if(rowNum==-1){
                JOptionPane.showMessageDialog(this,"请选择删除的课程");
            }else{
                String CnoId=(String)chm.getValueAt(rowNum,0);
                String TnoId=(String)chm.getValueAt(rowNum,2);
                sql="delete from choosecourse where 课程号=? and 任课教师号=?";
                String[] paras={CnoId,TnoId};
                CurModel temp=new CurModel();
                temp.updateCourse(sql, paras);
                chm=new ChoModel();
                jtb6.setModel(chm);
            }
        }*/
        }
    }
}