package niu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class DeleteForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("ѧ�ţ�");
    JLabel labDate=new JLabel("�������ڣ�");
    JLabel labScore=new JLabel("�ɼ���");
    JTextField txtName=new JTextField(20);
    JTextField txtDate=new JTextField(18);
    JTextField txtScore=new JTextField(20);
    JButton btnDel=new JButton("ɾ��");
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

    DeleteForm(){
        super("ɾ������");
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
        pan4.add(btnDel);
        pan4.add(btnCancel);
        pan.setLayout(new GridLayout(3,1));
        pan.add(pan1);
        pan.add(pan2);
        pan.add(pan3);
        getContentPane().add(pan,"Center");
        getContentPane().add(pan4,"South");
        btnQuery.addActionListener(this);
        btnDel.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDel.setEnabled(false);
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
                     txtDate.setText(rs.getDate("��������").toString());
                    txtScore.setText(new Integer(rs.getInt("�ɼ�")).toString
());
                    btnDel.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(this,"�����ڸü�¼��");
                    btnDel.setEnabled(false);
                    txtName.setText("");
                    txtScore.setText("");
                    txtDate.setText("");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==btnDel){
            try {
                if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog
(this,"ȷ��Ҫɾ���ü�¼��","��Ϣ",JOptionPane.YES_NO_OPTION)){
                    stm.executeUpdate("delete from  ��Ϣ�� where ѧ��='"+txtName.getText()+"'");
                    btnDel.setEnabled(false);
                    txtName.setText("");
                    txtScore.setText("");
                    txtDate.setText("");
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DeleteForm();
    }
}

