package niu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class AddForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("学号：");
    JLabel labDate=new JLabel("出生日期：");
    JLabel labScore=new JLabel("成绩：");
    JTextField txtName=new JTextField(20);
    JTextField txtDate=new JTextField(18);
    JTextField txtScore=new JTextField(20);
    JButton btnOk=new JButton("确定");
    JButton btnClear=new JButton("清空");
    JPanel pan=new JPanel();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();

    Connection cnn;
    Statement stm;
    ResultSet rs;

    AddForm(){
        super("添加数据");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pan.setBorder(BorderFactory.createEtchedBorder());
        pan1.add(labName);
        pan1.add(txtName);
        pan2.add(labDate);
        pan2.add(txtDate);
        pan3.add(labScore);
        pan3.add(txtScore);
        pan4.add(btnOk);
        pan4.add(btnClear);
        pan.setLayout(new GridLayout(3,1));
        pan.add(pan1);
        pan.add(pan2);
        pan.add(pan3);
        getContentPane().add(pan,"Center");
        getContentPane().add(pan4,"South");
        btnOk.addActionListener(this);
        btnClear.addActionListener(this);
        setVisible(true);
        txtName.requestFocus();


    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnClear){
            txtName.setText("");
            txtDate.setText("");
            txtScore.setText("");
            txtName.requestFocus();
        }else if(ae.getSource()==btnOk){
            String strName=txtName.getText();
            String strDate=txtDate.getText();
            String strScore=txtScore.getText();
            if(strName.equals(""))
                JOptionPane.showMessageDialog(this,"学号不能为空！","警告",JOptionPane.ERROR_MESSAGE);
            else if(strDate.equals(""))
                JOptionPane.showMessageDialog(this,"出生日期不能为空！","警告",JOptionPane.ERROR_MESSAGE);
            else if(strScore.equals(""))
                JOptionPane.showMessageDialog(this,"成绩不能为空！","警告",JOptionPane.ERROR_MESSAGE);
            else{
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    cnn=DriverManager.getConnection("Jdbc:Odbc:MyDB");
                    stm=cnn.createStatement();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try{
                    rs = stm.executeQuery("select * from 信息表 where 学号='" +strName + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this,"对不起，该成绩信息已存在！");
                    } else  //否则插入记录
                    {
                        //System.out.println("insert into 成绩表 values('"+strName+"',#"+strDate+"#,"+strScore+")");
                        stm.executeUpdate("insert into 信息表 values('"+strName+"','"+strDate+"',"+strScore+")");
                        JOptionPane.showMessageDialog(null,"记录已经成功添加！");
                    }
                    //断开连接
                    stm.close();
                    cnn.close();
                } catch (SQLException ex) {
                    System.out.println("SQLException:" + ex.getMessage());
                }

            }
        }
    }
    public static void main(String[] args) {
        new AddForm();
    }
}
