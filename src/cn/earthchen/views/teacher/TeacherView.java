package cn.earthchen.views.teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by earthchen on 17-6-4.
 * <p>
 * <p>
 * 教师模块
 */
public class TeacherView extends JFrame implements ActionListener {

    private JPanel[] jp;

    private JButton personInfoContentButton;
    private JButton myTopicContentButton;
    private JButton issueTopicContentButton;
    private JButton updateTeacherInfoButton;
    private JButton issueTopicButton;
    private JButton clearButton;

    private JLabel chooseTopicLabel;
    private JLabel myPersonInfoLabel;
    private JLabel myTopicInfoLabel;

    private JTable topicInfoTable;

    private JTextField topicNoTextField;
    private JTextField topicNameTextField;
    private JTextField topicTeacherNoTextField;
    private JTextField topicTeacherNameTextField;
    private JTextField topicStudentCountTextField;

    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField sexTextField;
    private JTextField birthdayTextField;

    private JTextArea topicContentTextArea;

    private String teacherNo = null;

    private TeacherInfoModel teacherInfoModel = null;
    private TopicInfoModel topicInfoModel;


    public static void main(String[] args) {
        TeacherView teacherView = new TeacherView("10001");
    }

    public TeacherView(String teacherNo) {
        this.teacherNo = teacherNo;
        JLabel welcomeLabel = new JLabel("欢迎你:" + teacherNo);
        welcomeLabel.setBounds(20, 10, 400, 30);
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.blue);
        jp = new JPanel[4];
        JPanel jp1 = new JPanel();
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
        }
        chooseTopicLabel = new JLabel("选题表");
        myTopicInfoLabel = new JLabel("我的课程设计信息表");
        myPersonInfoLabel = new JLabel("个人信息表");

        chooseTopicLabel.setBounds(700, 10, 500, 40);
        chooseTopicLabel.setForeground(Color.blue);
        chooseTopicLabel.setFont(new Font("楷体", Font.BOLD, 28));
        myPersonInfoLabel.setBounds(700, 10, 500, 40);
        myPersonInfoLabel.setForeground(Color.blue);
        myPersonInfoLabel.setFont(new Font("楷体", Font.BOLD, 28));
        myTopicInfoLabel.setBounds(700, 10, 500, 40);
        myTopicInfoLabel.setForeground(Color.blue);
        myTopicInfoLabel.setFont(new Font("楷体", Font.BOLD, 28));

        //jp1组件
        jp1.setLayout(null);
        personInfoContentButton = new JButton("个人信息");
        personInfoContentButton.setFont(new Font("楷体", Font.BOLD, 15));
        personInfoContentButton.setForeground(Color.blue);
        personInfoContentButton.addActionListener(this);
        personInfoContentButton.setBounds(50, 0, 100, 28);

        myTopicContentButton = new JButton("我的选题");
        myTopicContentButton.setFont(new Font("楷体", Font.BOLD, 15));
        myTopicContentButton.setForeground(Color.blue);
        myTopicContentButton.addActionListener(this);
        myTopicContentButton.setBounds(50, 75, 100, 28);

        issueTopicContentButton = new JButton("发布选题");
        issueTopicContentButton.addActionListener(this);
        issueTopicContentButton.setFont(new Font("楷体", Font.BOLD, 15));
        issueTopicContentButton.setForeground(Color.blue);
        issueTopicContentButton.setBounds(50, 150, 100, 28);

        jp1.add(personInfoContentButton);
        jp1.add(myTopicContentButton);
        jp1.add(issueTopicContentButton);

        //jp[0]组件 个人信息
        JLabel nameLabel = new JLabel("姓  名:");
        nameLabel.setFont(new Font("楷体", Font.BOLD, 15));
        nameLabel.setBounds(0, 220, 120, 28);
        nameTextField = new JTextField();
        nameTextField.setBounds(150, 220, 120, 28);

        JLabel ageLabel = new JLabel("年  龄:");
        ageLabel.setFont(new Font("楷体", Font.BOLD, 15));
        ageLabel.setBounds(0, 270, 120, 28);
        ageTextField = new JTextField();
        ageTextField.setBounds(150, 270, 120, 28);

        JLabel sexLabel = new JLabel("性  别:");
        sexLabel.setBounds(0, 320, 120, 28);
        sexLabel.setFont(new Font("楷体", Font.BOLD, 15));
        sexTextField = new JTextField();
        sexTextField.setBounds(150, 320, 120, 28);

        JLabel birthdayLabel = new JLabel("出生日期:");
        birthdayLabel.setFont(new Font("楷体", Font.BOLD, 15));
        birthdayLabel.setBounds(0, 370, 120, 28);
        birthdayTextField = new JTextField();
        birthdayTextField.setBounds(150, 370, 120, 28);

        updateTeacherInfoButton = new JButton("修改个人信息");
        updateTeacherInfoButton.setFont(new Font("楷体", Font.BOLD, 15));
        updateTeacherInfoButton.setForeground(Color.blue);
        updateTeacherInfoButton.addActionListener(this);
        updateTeacherInfoButton.setBounds(900, 400, 120, 28);
        teacherInfoModel = new TeacherInfoModel(teacherNo);
        JTable teacherInfoTable = new JTable();
        teacherInfoTable.setModel(teacherInfoModel);
        teacherInfoTable.setFont(new Font("楷体", Font.BOLD, 15));
        teacherInfoTable.setForeground(Color.blue);
        JScrollPane teacherInfoScrollPane = new JScrollPane(teacherInfoTable);
        teacherInfoScrollPane.setBounds(0, 0, 1050, 200);
        jp[0].add(teacherInfoScrollPane);
        jp[0].add(nameLabel);
        jp[0].add(nameTextField);
        jp[0].add(ageLabel);
        jp[0].add(ageTextField);
        jp[0].add(sexLabel);
        jp[0].add(sexTextField);
        jp[0].add(birthdayLabel);
        jp[0].add(birthdayTextField);
        jp[0].add(updateTeacherInfoButton);

        //jp[1]组件
        topicInfoModel = new TopicInfoModel(this.teacherNo);
        topicInfoTable = new JTable();
        topicInfoTable.setModel(topicInfoModel);
        topicInfoTable.setFont(new Font("楷体", Font.BOLD, 15));
        topicInfoTable.setForeground(Color.blue);
        JScrollPane jsp2 = new JScrollPane(topicInfoTable);
        jsp2.setBounds(0, 0, 1050, 400);
        jp[1].add(jsp2);

        //jp[2]发布选题
        jp[2].setLayout(null);
        JLabel topicNoLabel = new JLabel("　课题编号");
        topicNoLabel.setFont(new Font("楷体", Font.BOLD, 15));
        topicNoLabel.setForeground(Color.blue);
        topicNoLabel.setBounds(10, 0, 100, 28);

        JLabel topicNameLabel = new JLabel("课题名称");
        topicNameLabel.setFont(new Font("楷体", Font.BOLD, 15));
        topicNameLabel.setForeground(Color.blue);
        topicNameLabel.setBounds(360, 0, 100, 28);

        JLabel topicTeacherNoLabel = new JLabel("任课教师号");
        topicTeacherNoLabel.setFont(new Font("楷体", Font.BOLD, 15));
        topicTeacherNoLabel.setForeground(Color.blue);
        topicTeacherNoLabel.setBounds(700, 0, 100, 28);

        JLabel topicTeacherNameLabel = new JLabel("任课教师名");
        topicTeacherNameLabel.setFont(new Font("楷体", Font.BOLD, 15));
        topicTeacherNameLabel.setForeground(Color.blue);
        topicTeacherNameLabel.setBounds(10, 50, 100, 28);

        JLabel topicStudentCountLabel = new JLabel("　　人数");
        topicStudentCountLabel.setFont(new Font("楷体", Font.BOLD, 15));
        topicStudentCountLabel.setForeground(Color.blue);
        topicStudentCountLabel.setBounds(360, 50, 100, 28);

        JLabel topicContentLabel = new JLabel("　内容");
        topicContentLabel.setFont(new Font("楷体", Font.BOLD, 15));
        topicContentLabel.setBounds(10, 100, 100, 28);
        topicContentLabel.setForeground(Color.blue);

        topicNoTextField = new JTextField(20);
        topicNoTextField.setBounds(100, 2, 200, 24);
        topicNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        topicNoTextField.setForeground(Color.blue);

        topicNameTextField = new JTextField(20);
        topicNameTextField.setBounds(440, 2, 200, 24);
        topicNameTextField.setFont(new Font("楷体", Font.BOLD, 15));
        topicNameTextField.setForeground(Color.blue);

        topicTeacherNoTextField = new JTextField(20);
        topicTeacherNoTextField.setBounds(790, 2, 200, 24);
        topicTeacherNoTextField.setFont(new Font("楷体", Font.BOLD, 15));
        topicTeacherNoTextField.setForeground(Color.blue);
        topicTeacherNoTextField.setText(teacherInfoModel.getValueAt(0, 0).toString());
        topicTeacherNoTextField.setEditable(false);

        topicTeacherNameTextField = new JTextField(20);
        topicTeacherNameTextField.setBounds(100, 52, 200, 24);
        topicTeacherNameTextField.setFont(new Font("楷体", Font.BOLD, 15));
        topicTeacherNameTextField.setForeground(Color.blue);
        topicTeacherNameTextField.setText(teacherInfoModel.getValueAt(0, 1).toString());
        topicTeacherNameTextField.setEditable(false);

        topicStudentCountTextField = new JTextField(20);
        topicStudentCountTextField.setBounds(440, 52, 200, 24);
        topicStudentCountTextField.setFont(new Font("楷体", Font.BOLD, 15));
        topicStudentCountTextField.setForeground(Color.blue);

        topicContentTextArea = new JTextArea();
        topicContentTextArea.setBounds(10, 130, 1000, 200);
        topicContentTextArea.setFont(new Font("楷体", Font.BOLD, 15));
        topicContentTextArea.setForeground(Color.blue);

        issueTopicButton = new JButton("发布选题");
        issueTopicButton.setFont(new Font("楷体", Font.BOLD, 15));
        issueTopicButton.setForeground(Color.blue);
        issueTopicButton.setBounds(10, 370, 120, 28);
        issueTopicButton.addActionListener(this);

        clearButton = new JButton("重置");
        clearButton.setFont(new Font("楷体", Font.BOLD, 15));
        clearButton.setForeground(Color.blue);
        clearButton.setBounds(150, 370, 120, 28);
        clearButton.addActionListener(this);

        jp[2].add(topicNoLabel);
        jp[2].add(topicNoTextField);
        jp[2].add(topicNameLabel);
        jp[2].add(topicNameTextField);
        jp[2].add(topicTeacherNoLabel);
        jp[2].add(topicTeacherNoTextField);
        jp[2].add(topicTeacherNameLabel);
        jp[2].add(topicTeacherNameTextField);
        jp[2].add(topicStudentCountLabel);
        jp[2].add(topicStudentCountTextField);
        jp[2].add(topicContentLabel);
        jp[2].add(topicContentTextArea);
        jp[2].add(issueTopicButton);
        jp[2].add(clearButton);

        //jp[4]
        jp[3].setLayout(null);
        JTable jtb0 = new JTable();
        jtb0.setFont(new Font("楷体", Font.BOLD, 15));
        jtb0.setForeground(Color.blue);
        JScrollPane jsp0 = new JScrollPane(jtb0);
        jsp0.setBounds(0, 0, 1050, 400);
        jp[3].add(jsp0);

        //总布局
        jtb0.setRowHeight(28);
        teacherInfoTable.setRowHeight(28);
        topicInfoTable.setRowHeight(28);
        jp1.setBounds(20, 50, 200, 600);
        for (JPanel aJp : jp) {
            aJp.setBounds(250, 50, 1050, 600);
            aJp.setLayout(null);
            aJp.setVisible(false);
        }

        jp[0].setVisible(true);
        this.add(chooseTopicLabel);
        this.add(myPersonInfoLabel);
        myPersonInfoLabel.setVisible(false);
        myTopicInfoLabel.setVisible(false);
        this.add(myTopicInfoLabel);
        this.setLayout(null);
        this.add(jp1);
        for (JPanel aJp : jp) {
            this.add(aJp);
        }
        this.add(welcomeLabel);
        this.setSize(1300, 700);
        this.setResizable(false);
        this.setTitle("学生选题管理系统");
        this.setBackground(Color.gray);
        this.setLocation(50, 30);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //DeleteAuto da=new DeleteAuto(teacherNo);
            }
        }, 500);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                //new AddAuto();
            }
        }, 1000);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == personInfoContentButton) {
            for (int i = 1; i < jp.length; i++) {
                jp[i].setVisible(false);
            }
            jp[0].setVisible(true);
            myPersonInfoLabel.setVisible(true);
            chooseTopicLabel.setVisible(false);
            myTopicInfoLabel.setVisible(false);
        } else if (e.getSource() == myTopicContentButton) {
            for (JPanel aJp : jp) {
                aJp.setVisible(false);
            }
            //更新table数据
            topicInfoModel.init(this.teacherNo);
            topicInfoTable.revalidate();
            jp[1].setVisible(true);
            myTopicInfoLabel.setVisible(true);
            myPersonInfoLabel.setVisible(false);
            chooseTopicLabel.setVisible(false);
        } else if (e.getSource() == issueTopicContentButton) {
            topicNoTextField.setText("");
            topicNameTextField.setText("");
            topicStudentCountTextField.setText("");
            for (JPanel aJp : jp) {
                aJp.setVisible(false);
            }
            jp[2].setVisible(true);
            chooseTopicLabel.setVisible(false);
            myPersonInfoLabel.setVisible(false);
            myTopicInfoLabel.setVisible(false);
        } else if (e.getSource() == updateTeacherInfoButton) {
            boolean success = teacherInfoModel.updateTeacherInfo(
                    nameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()),
                    sexTextField.getText(),
                    birthdayTextField.getText());
            if (success){
                JOptionPane.showMessageDialog(this, "个人信息更新成功");
            }else {
                JOptionPane.showMessageDialog(this, "个人信息更新失败");
            }

        } else if (e.getSource() == issueTopicButton) {
            if (topicNoTextField.getText().length() != 0 &&
                    topicContentTextArea.getText().length() != 0 &&
                    topicNameTextField.getText().length() != 0 &&
                    topicStudentCountTextField.getText().length() != 0 &&
                    topicTeacherNameTextField.getText().length() != 0 &&
                    topicTeacherNoTextField.getText().length() != 0) {
                topicInfoModel.saveTopicInfo(topicNoTextField.getText(),
                        topicNameTextField.getText(),
                        topicContentTextArea.getText(),
                        topicTeacherNoTextField.getText(),
                        topicTeacherNameTextField.getText(),
                        Integer.parseInt(topicStudentCountTextField.getText()));
            } else {
                JOptionPane.showMessageDialog(this, "所有字段都必须填写");
            }
        } else if (e.getSource() == clearButton) {
            topicNoTextField.setText("");
            topicNameTextField.setText("");
            topicStudentCountTextField.setText("");
            topicContentTextArea.setText("");
        }
    }
}
