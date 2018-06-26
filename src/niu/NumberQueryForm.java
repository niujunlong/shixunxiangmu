package niu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class NumberQueryForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("学号：");
    JLabel labDate=new JLabel("出生日期：");
    JLabel labScore=new JLabel("成绩：");
    JTextField txtName=new JTextField(20);
    JTextField txtDate=new JTextField(18);
    JTextField txtScore=new JTextField(20);
    JButton btnCancel=new JButton("取消");
    JButton btnQuery=new JButton("查询");
    JPanel pan=new JPanel();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();

    Connection cnn;
    Statement stm;
    ResultSet rs;

    NumberQueryForm(){
        super("按学号查询");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pan.setBorder(BorderFactory.createEtchedBorder());
        pan1.add(labName);
        pan1.add(txtName);
        pan2.add(labDate);
        pan2.add(txtDate);
        pan3.add(labScore);
        pan3.add(txtScore);
        pan4.add(btnQuery);
        pan4.add(btnCancel);
        pan.setLayout(new GridLayout(3,1));
        pan.add(pan1);
        pan.add(pan2);
        pan.add(pan3);
        getContentPane().add(pan,"Center");
        getContentPane().add(pan4,"South");
        btnQuery.addActionListener(this);
        btnCancel.addActionListener(this);
        txtDate.setEditable(false);
        txtScore.setEditable(false);
        setVisible(true);
        txtName.requestFocus();

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnCancel){
            try {
                if(stm!=null)
                    stm.close();
                if(cnn!=null)
                    cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        } else if(ae.getSource()==btnQuery){
            try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                cnn=DriverManager.getConnection("jdbc:odbc:MyDB","","");
                stm=cnn.createStatement();
                rs=stm.executeQuery("select * from 信息表 where 学号='"+txtName.getText()+"'");
                if(rs.next()){
                    txtName.setText(rs.getString("学号"));
                    txtScore.setText(new Integer(rs.getInt("成绩")).toString());
                    txtDate.setText(rs.getDate("出生日期").toString());
                }else{
                    JOptionPane.showMessageDialog(this,"不存在该记录！");
                    txtName.setText("");
                    txtScore.setText("");
                    txtDate.setText("");
                    txtName.requestFocus();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new NumberQueryForm();
    }
}
