package niu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class MyPanel extends JPanel{
    Image img=Toolkit.getDefaultToolkit().getImage("c:/a.jpg");
    public void paint(Graphics g){
        g.drawImage(img,0,0,this);
    }
}
class MainForm extends JFrame implements ActionListener{
    JMenu mSystem=new JMenu("系统");
    JMenuItem mExit=new JMenuItem("退出");
    JMenu mOperate=new JMenu("数据操作");
    JMenuItem mAdd=new JMenuItem("添加");
    JMenuItem mDel=new JMenuItem("删除");
    JMenuItem mModify=new JMenuItem("修改");
    JMenuItem mBrowse=new JMenuItem("浏览");
    JMenu mQuery=new JMenu("查询");
    JMenuItem mNumber=new JMenuItem("按学号查询");
    JMenuItem mScore=new JMenuItem("按成绩查询");
    JMenu mHelp=new JMenu("帮助");
    JMenuItem mAbout=new JMenuItem("关于");
    JMenuBar mBar=new JMenuBar();
    MainForm(){
        super("学生信息管理系统");
        setSize(700,630);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mSystem.add(mExit);
        mOperate.add(mAdd);
        mOperate.add(mDel);
        mOperate.add(mModify);
        mOperate.add(mBrowse);
        mQuery.add(mNumber);
        mQuery.add(mScore);
        mHelp.add(mAbout);
        mBar.add(mSystem);
        mBar.add(mOperate);
        mBar.add(mQuery);
        mBar.add(mHelp);
        setJMenuBar(mBar);
        mExit.addActionListener(this);
        mAdd.addActionListener(this);
        mDel.addActionListener(this);
        mModify.addActionListener(this);
        mBrowse.addActionListener(this);
        mNumber.addActionListener(this);
        mScore.addActionListener(this);
        mAbout.addActionListener(this);
        setContentPane(new MyPanel());
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==mExit)
            System.exit(0);
        else if(ae.getSource()==mAbout)
            JOptionPane.showMessageDialog(this,"学生管理系统 V1.0\n\n皖西学院电子与信息工程学院计算机科学与技术\n\n2018年6月","关于",JOptionPane.INFORMATION_MESSAGE);
        else if(ae.getSource()==mAdd)
            new AddForm().setVisible(true);
        else if(ae.getSource()==mDel)
            new DeleteForm().setVisible(true);
        else if(ae.getSource()==mModify)
            new ModifyForm().setVisible(true);
        else if(ae.getSource()==mBrowse)
            new BrowseForm().setVisible(true);
        else if(ae.getSource()==mNumber)
            new NumberQueryForm().setVisible(true);
        else if(ae.getSource()==mScore)
            new ScoreQueryForm().setVisible(true);

    }
    public static void main(String[] args) {
        new MainForm();
    }
}
