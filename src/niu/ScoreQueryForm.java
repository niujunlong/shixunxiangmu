package niu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ScoreQueryForm extends JFrame implements ActionListener{
    JLabel labScore=new JLabel("������ɼ���");
    JTextField txtScore=new JTextField(10);
    JButton btnQuery=new JButton("��ѯ");
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    String []str={"ѧ��","��������","�ɼ�"};
    Object[][] data=new Object[10][3];
    JTable table=new JTable(data,str);
    JTableHeader head=table.getTableHeader();
    JScrollPane jsp=new JScrollPane(table);
    Connection conn;
    Statement stmt;
    ResultSet rs;
    ScoreQueryForm(){
        super("���ɼ���ѯ");
        setSize(400,300);
        pan1.add(labScore);
        pan1.add(txtScore);
        pan1.add(btnQuery);
        getContentPane().add(pan1,"North");
        table=new JTable(data,str);
        pan2.setLayout(new BorderLayout());
        head=table.getTableHeader();
        jsp=new JScrollPane(table);
        pan2.add(head,"North");
        pan2.add(jsp,"Center");
        getContentPane().add(pan2,"Center");
        btnQuery.addActionListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnQuery){
            int i,j,row;
            try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                conn=DriverManager.getConnection("jdbc:odbc:MyDB","","");
                stmt=conn.createStatement();

                rs=stmt.executeQuery("select COUNT(*) as rowcount from ��Ϣ�� where �ɼ�="+txtScore.getText());
                rs.next();
                row=rs.getInt("rowcount");
                rs.close();
                data=new Object[row][3];
                rs=stmt.executeQuery("select * from ��Ϣ�� where �ɼ�="+txtScore.getText());
                i=0;j=0;
                while(rs.next()){
                    data[i][j++]=rs.getString("ѧ��");
                    data[i][j++]=rs.getDate("��������");
                    data[i][j]=new Integer(rs.getInt("�ɼ�"));
                    i++;j=0;
                }

                pan2.removeAll();
                getContentPane().remove(pan2);

                table=new JTable(data,str);
                pan2.setLayout(new BorderLayout());
                head=table.getTableHeader();
                jsp=new JScrollPane(table);
                pan2.add(head,"North");
                pan2.add(jsp,"Center");
                getContentPane().add(pan2,"Center");
                this.validate();
                rs.close();
                stmt.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new ScoreQueryForm();
    }
}

