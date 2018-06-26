package niu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class NumberQueryForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("ѧ�ţ�");
    JLabel labDate=new JLabel("�������ڣ�");
    JLabel labScore=new JLabel("�ɼ���");
    JTextField txtName=new JTextField(20);
    JTextField txtDate=new JTextField(18);
    JTextField txtScore=new JTextField(20);
    JButton btnCancel=new JButton("ȡ��");
    JButton btnQuery=new JButton("��ѯ");
    JPanel pan=new JPanel();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();

    Connection cnn;
    Statement stm;
    ResultSet rs;

    NumberQueryForm(){
        super("��ѧ�Ų�ѯ");
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
                rs=stm.executeQuery("select * from ��Ϣ�� where ѧ��='"+txtName.getText()+"'");
                if(rs.next()){
                    txtName.setText(rs.getString("ѧ��"));
                    txtScore.setText(new Integer(rs.getInt("�ɼ�")).toString());
                    txtDate.setText(rs.getDate("��������").toString());
                }else{
                    JOptionPane.showMessageDialog(this,"�����ڸü�¼��");
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
