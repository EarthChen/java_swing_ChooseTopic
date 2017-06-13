package cn.earthchen.views.student;

import cn.earthchen.views.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by earthchen on 17-6-4.
 * <p>
 * 学生模块
 */
public class StudentView extends JFrame implements ActionListener {

    private String studentNo;

    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JPanel jp5;

    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField sexTextField;
    private JTextField birthdayTextField;

    private JButton personInfoContentButton;
    private JButton chooseTopicContentButton;
    private JButton myTopicContentButton;
    private JButton updateStudentInfoButton;
    private JButton withdrawalButton;
    private JButton chooseTopicButton;

    private JTable studentInfoTable;
    private JTable chooseTopicTable;
    private JTable myTopicTable;

    private StudentInfoModel studentInfoModel;
    private ChooseTopicModel chooseTopicModel;
    private MyTopicModel myTopicModel;

    public static void main(String[] args) {
        StudentView studentView = new StudentView("20170001");
    }

    public StudentView(String studentNo) {
        System.out.println(studentNo);
        this.studentNo = studentNo;
        JLabel welcomeLabel = new JLabel("欢迎你:" + studentNo);
        welcomeLabel.setBounds(20, 10, 400, 30);
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.blue);

        Font myfont = new Font("楷体", Font.BOLD, 15);

        JPanel jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();

        personInfoContentButton = new JButton("个人信息");
        personInfoContentButton.setFont(myfont);
        personInfoContentButton.setForeground(Color.BLUE);
        personInfoContentButton.addActionListener(this);
        personInfoContentButton.setBounds(50, 0, 120, 28);
        chooseTopicContentButton = new JButton("开始选题");
        chooseTopicContentButton.setFont(myfont);
        chooseTopicContentButton.setForeground(Color.BLUE);
        chooseTopicContentButton.addActionListener(this);
        chooseTopicContentButton.setBounds(50, 100, 120, 28);
        myTopicContentButton = new JButton("我的选题");
        myTopicContentButton.setFont(myfont);
        myTopicContentButton.setForeground(Color.BLUE);
        myTopicContentButton.addActionListener(this);
        myTopicContentButton.setBounds(50, 50, 120, 28);


        jp1.add(personInfoContentButton);
        jp1.add(chooseTopicContentButton);
        jp1.add(myTopicContentButton);

        //个人信息
        String sql = "select * from student where 学号='" + this.studentNo + "'";
        studentInfoModel = new StudentInfoModel(this.studentNo);
        studentInfoTable = new JTable(studentInfoModel);
        studentInfoTable.setFont(myfont);
        studentInfoTable.setForeground(Color.BLUE);
        studentInfoTable.setRowHeight(28);
        JScrollPane studentInfoScrollPane = new JScrollPane(studentInfoTable);
        studentInfoScrollPane.setBounds(0, 50, 1050, 100);
        JLabel personInfoTableLabel = new JLabel("个人信息表");
        personInfoTableLabel.setBounds(400, 0, 500, 50);
        personInfoTableLabel.setFont(new Font("楷体", Font.BOLD, 45));
        personInfoTableLabel.setForeground(Color.blue);

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

        updateStudentInfoButton = new JButton("修改个人信息");
        updateStudentInfoButton.setFont(myfont);
        updateStudentInfoButton.setForeground(Color.BLUE);
        updateStudentInfoButton.setBounds(900, 400, 120, 28);
        updateStudentInfoButton.addActionListener(this);

        jp2.add(studentInfoScrollPane);
        jp2.add(nameLabel);
        jp2.add(nameTextField);
        jp2.add(ageLabel);
        jp2.add(ageTextField);
        jp2.add(sexLabel);
        jp2.add(sexTextField);
        jp2.add(birthdayLabel);
        jp2.add(birthdayTextField);
        jp2.add(updateStudentInfoButton);
        jp2.add(personInfoTableLabel);

        //开始选题
        chooseTopicModel = new ChooseTopicModel();
        chooseTopicModel.init();

        chooseTopicTable = new JTable();
        chooseTopicTable.setModel(chooseTopicModel);
        chooseTopicTable.setFont(myfont);
        chooseTopicTable.setForeground(Color.blue);
        chooseTopicTable.setRowHeight(28);

        JScrollPane studentInfoScrollPane2 = new JScrollPane(chooseTopicTable);
        studentInfoScrollPane2.setBounds(0, 50, 1050, 400);

        JLabel jlb5 = new JLabel("选题信息表");
        jlb5.setBounds(400, 0, 500, 50);
        jlb5.setFont(new Font("楷体", Font.BOLD, 45));
        jlb5.setForeground(Color.blue);


        chooseTopicButton = new JButton("选题");
        chooseTopicButton.setBounds(400, 500, 120, 28);
        chooseTopicButton.setFont(new Font("楷体", Font.BOLD, 15));
        chooseTopicButton.setForeground(Color.blue);
        chooseTopicButton.addActionListener(this);


        jp3.add(studentInfoScrollPane2);
        jp3.add(jlb5);
        jp3.add(chooseTopicButton);

        //查看我的选题
        myTopicModel = new MyTopicModel(this.studentNo);
        myTopicTable = new JTable();
        myTopicTable.setModel(myTopicModel);
        myTopicTable.setFont(myfont);
        myTopicTable.setForeground(Color.BLUE);
        myTopicTable.setRowHeight(28);

        withdrawalButton = new JButton("退选");
        withdrawalButton.setBounds(0, 570, 80, 28);
        withdrawalButton.setFont(new Font("楷体", Font.BOLD, 15));
        withdrawalButton.setForeground(Color.blue);
        withdrawalButton.addActionListener(this);

        JScrollPane studentInfoScrollPane3 = new JScrollPane(myTopicTable);
        jp3.add(studentInfoScrollPane3);
        studentInfoScrollPane3.setBounds(0, 50, 1050, 500);

        JLabel myTopicTableLabel = new JLabel("我的课题表");
        myTopicTableLabel.setBounds(400, 0, 500, 50);
        myTopicTableLabel.setFont(new Font("楷体", Font.BOLD, 45));
        myTopicTableLabel.setForeground(Color.blue);

        jp4.add(studentInfoScrollPane3);
        jp4.add(withdrawalButton);
        jp4.add(myTopicTableLabel);

        //
        jp1.setLayout(null);
        jp1.setBounds(0, 50, 200, 600);

        jp2.setBounds(250, 0, 1150, 600);
        jp2.setLayout(null);

        jp3.setBounds(250, 0, 1150, 600);
        jp3.setVisible(false);
        jp3.setLayout(null);

        jp4.setBounds(250, 0, 1150, 600);
        jp4.setVisible(false);
        jp4.setLayout(null);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(welcomeLabel);
        this.setLayout(null);
        this.setTitle("学生选题管理系统");
        this.setBackground(Color.gray);
        this.setSize(1300, 700);
        this.setLocation(50, 20);
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                dispose();
                new Login();
            }});
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == personInfoContentButton) {
            jp2.setVisible(true);
            jp3.setVisible(false);
            jp4.setVisible(false);
            jp5.setVisible(false);
        } else if (e.getSource() == myTopicContentButton) {
            myTopicModel.init(this.studentNo);
            myTopicTable.revalidate();
            jp4.setVisible(true);
            jp3.setVisible(false);
            jp2.setVisible(false);
            jp5.setVisible(false);
        } else if (e.getSource() == chooseTopicContentButton) {
            chooseTopicModel.init();
            chooseTopicTable.revalidate();
            jp3.setVisible(true);
            jp2.setVisible(false);
            jp4.setVisible(false);
            jp5.setVisible(false);
        } else if (e.getSource() == updateStudentInfoButton) {
            boolean success = studentInfoModel.updateStudentInfo(
                    this.studentNo,
                    nameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()),
                    sexTextField.getText(),
                    birthdayTextField.getText());
            if (success) {
                JOptionPane.showMessageDialog(this, "修改成功");
                studentInfoModel.init(this.studentNo);
                studentInfoTable.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "修改失败请重试");
            }
        } else if (e.getSource() == withdrawalButton) {
            int confirmValue = JOptionPane.showConfirmDialog(this, "一旦退选无法恢复，是否确认退选");
            String topicNo = null;
            if (confirmValue==0){
                topicNo = myTopicTable.getValueAt(myTopicTable.getSelectedRow(), 1).toString();
            }
            boolean deleteMyTopic=myTopicModel.deleteStudentNoTopicNo(topicNo);
            if (deleteMyTopic){
                boolean withdrawalTopicSuccess=chooseTopicModel.withdrawalTopic(topicNo);
                if (withdrawalTopicSuccess){
                    myTopicModel.init(this.studentNo);
                    myTopicTable.revalidate();
                    JOptionPane.showMessageDialog(this, "退选 成功");
                }
            }else {
                JOptionPane.showMessageDialog(this, "退选失败，请重试");
            }
        } else if (e.getSource() == chooseTopicButton) {
            int confirmValue = JOptionPane.showConfirmDialog(this, "确认选题");
            if (confirmValue == 0) {
                String topicNo = chooseTopicTable.getValueAt(chooseTopicTable.getSelectedRow(), 0).toString();
                boolean chooseTopicSuccess = chooseTopicModel.isChoose(topicNo, this.studentNo);
                if (chooseTopicSuccess) {
                    boolean saveTopicSuccess = myTopicModel.saveStudentNoToTopicNo(this.studentNo, topicNo);
                    if (saveTopicSuccess) {
                        chooseTopicModel.init();
                        chooseTopicTable.revalidate();
                        JOptionPane.showMessageDialog(this, "选课成功");
                    }
                }
            }
        }
    }
}
