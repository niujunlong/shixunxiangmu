package niu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class AddForm extends JFrame implements ActionListener{
    JLabel labName=new JLabel("ѧ�ţ�");
    JLabel labDate=new JLabel("�������ڣ�");
    JLabel labScore=new JLabel("�ɼ���");
    JTextField txtName=new JTextField(20);
    JTextField txtDate=new JTextField(18);
    JTextField txtScore=new JTextField(20);
    JButton btnOk=new JButton("ȷ��");
    JButton btnClear=new JButton("���");
    JPanel pan=new JPanel();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();

    Connection cnn;
    Statement stm;
    ResultSet rs;

    AddForm(){
        super("�������");
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
                JOptionPane.showMessageDialog(this,"ѧ�Ų���Ϊ�գ�","����",JOptionPane.ERROR_MESSAGE);
            else if(strDate.equals(""))
                JOptionPane.showMessageDialog(this,"�������ڲ���Ϊ�գ�","����",JOptionPane.ERROR_MESSAGE);
            else if(strScore.equals(""))
                JOptionPane.showMessageDialog(this,"�ɼ�����Ϊ�գ�","����",JOptionPane.ERROR_MESSAGE);
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
                    rs = stm.executeQuery("select * from ��Ϣ�� where ѧ��='" +strName + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this,"�Բ��𣬸óɼ���Ϣ�Ѵ��ڣ�");
                    } else  //��������¼
                    {
                        //System.out.println("insert into �ɼ��� values('"+strName+"',#"+strDate+"#,"+strScore+")");
                        stm.executeUpdate("insert into ��Ϣ�� values('"+strName+"','"+strDate+"',"+strScore+")");
                        JOptionPane.showMessageDialog(null,"��¼�Ѿ��ɹ���ӣ�");
                    }
                    //�Ͽ�����
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
