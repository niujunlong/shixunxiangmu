package niu;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class BrowseForm extends JFrame {
    String []str={"ѧ��","��������","�ɼ�"};
    Object[][] data;
    JTable table;
    JTableHeader head;
    JScrollPane jsp;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    BrowseForm(){
        super("�������");
        setSize(400,300);
        int i=0,j=0;
        int row;
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn=DriverManager.getConnection("jdbc:odbc:MyDB","","");
            stmt=conn.createStatement();

            rs=stmt.executeQuery("select COUNT(*) as a from ��Ϣ��");
            rs.next();
            row=rs.getInt("a");
            rs.close();
            data=new Object[row][3];
            rs=stmt.executeQuery("select * from ��Ϣ��");
            while(rs.next()){
                data[i][j++]=rs.getString("ѧ��");
                data[i][j++]=rs.getDate("��������");
                data[i][j]=new Integer(rs.getInt("�ɼ�"));
                i++;j=0;
            }
            table=new JTable(data,str);
            head=table.getTableHeader();
            jsp=new JScrollPane(table);
           getContentPane().add(head,"North");
            getContentPane().add(jsp,"Center");
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new BrowseForm();
    }
}


 