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
    JMenu mSystem=new JMenu("ϵͳ");
    JMenuItem mExit=new JMenuItem("�˳�");
    JMenu mOperate=new JMenu("���ݲ���");
    JMenuItem mAdd=new JMenuItem("���");
    JMenuItem mDel=new JMenuItem("ɾ��");
    JMenuItem mModify=new JMenuItem("�޸�");
    JMenuItem mBrowse=new JMenuItem("���");
    JMenu mQuery=new JMenu("��ѯ");
    JMenuItem mNumber=new JMenuItem("��ѧ�Ų�ѯ");
    JMenuItem mScore=new JMenuItem("���ɼ���ѯ");
    JMenu mHelp=new JMenu("����");
    JMenuItem mAbout=new JMenuItem("����");
    JMenuBar mBar=new JMenuBar();
    MainForm(){
        super("ѧ����Ϣ����ϵͳ");
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
            JOptionPane.showMessageDialog(this,"ѧ������ϵͳ V1.0\n\n����ѧԺ��������Ϣ����ѧԺ�������ѧ�뼼��\n\n2018��6��","����",JOptionPane.INFORMATION_MESSAGE);
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
