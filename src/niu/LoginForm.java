package niu;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class LoginForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("����");
    JLabel labPwd=new JLabel("����");
    JTextField txtName=new JTextField(20);
    JPasswordField txtPwd=new JPasswordField(20);
    JButton btnOk=new JButton("ȷ��");
    JButton btnCancel=new JButton("ȡ��");
    JPanel pan=new JPanel();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();

    Connection cnn;
    Statement stm;
    ResultSet rs;

    LoginForm(){
        super("�û���¼");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pan.setBorder(BorderFactory.createTitledBorder("��¼"));
        pan.setLayout(new GridLayout(2,1));
        pan1.add(labName);
        pan1.add(txtName);
        pan2.add(labPwd);
        pan2.add(txtPwd);
        pan.add(pan1);
        pan.add(pan2);
        pan3.add(btnOk);
        pan3.add(btnCancel);
        pan4.add(pan);
        getContentPane().add(pan4,"Center");
        getContentPane().add(pan3,"South");
        txtName.addActionListener(this);
        txtPwd.addActionListener(this);
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);
        setVisible(true);

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
        txtName.requestFocus();

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==txtName)
            txtPwd.requestFocus();
        else if(ae.getSource()==txtPwd)
            btnOk.requestFocus();
        else if(ae.getSource()==btnCancel){
            txtName.setText("");
            txtPwd.setText("");
            txtName.requestFocus();
        }else if(ae.getSource()==btnOk){
            String str="select * from �û��� where �û���='"+txtName.getText()+"'and ����='"+new String(txtPwd.getPassword())+"'";
            try {
                rs=stm.executeQuery(str);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if(rs.next()){
                    JOptionPane.showMessageDialog(this,"��֤ͨ��!","��Ϣ",JOptionPane.INFORMATION_MESSAGE);
                    rs.close();
                    stm.close();
                    cnn.close();
                   new MainForm().setVisible(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this,"�û��������벻��ȷ!","��Ϣ",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new LoginForm();
    }
}
