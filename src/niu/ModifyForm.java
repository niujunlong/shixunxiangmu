package niu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class ModifyForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("学号：");
    JLabel labDate=new JLabel("出生日期：");
    JLabel labScore=new JLabel("成绩：");
    JTextField txtName=new JTextField(20);
    JTextField txtDate=new JTextField(18);
    JTextField txtScore=new JTextField(20);
    JButton btnModify=new JButton("修改");
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

    ModifyForm(){
        super("修改数据");
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
        pan4.add(btnModify);
        pan4.add(btnCancel);
        pan.setLayout(new GridLayout(3,1));
        pan.add(pan1);
        pan.add(pan2);
        pan.add(pan3);
        getContentPane().add(pan,"Center");
        getContentPane().add(pan4,"South");
        btnQuery.addActionListener(this);
        btnModify.addActionListener(this);
        btnCancel.addActionListener(this);
        btnModify.setEnabled(false);
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
                    btnModify.setEnabled(true);
                    txtDate.setEditable(true);
                    txtScore.setEditable(true);
                }else{
                    JOptionPane.showMessageDialog(this,"不存在该记录！");
                    btnModify.setEnabled(false);
                    txtName.setText("");
                    txtScore.setText("");
                    txtDate.setText("");
                    txtDate.setEditable(false);
                    txtScore.setEditable(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==btnModify){
            try{
                System.out.println("Update  信息表 set 出生日期=#"+txtDate.getText()+"#,成绩="+txtScore.getText()+" where 学号='"+txtName.getText()+"'");
                stm.executeUpdate("Update  信息表 set 出生日期=#"+txtDate.getText()+"#,成绩="+txtScore.getText()+" where 学号='"+txtName.getText()+"'");
                JOptionPane.showMessageDialog(this,"记录修改完毕！");
                btnModify.setEnabled(false);
                txtName.setText("");
                txtScore.setText("");
                txtDate.setText("");
                txtDate.setEditable(false);
                txtScore.setEditable(false);
                stm.close();
                cnn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ModifyForm();
    }
}
