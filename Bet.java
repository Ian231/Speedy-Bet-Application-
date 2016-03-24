/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyBet.service.dao.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import speedyBet.service.ui.Frame;

public class Bet {

    static DefaultTableModel table_model;
    public static Object DataHasID[][];

//    add payment to table sql commands 
    public void AddBETReplace(speedyBet.service.dao.model.Bet a) {
        try {
            String sss = "insert into bets (betType , date , openDate, stakes , result , customerID  , ID_B , name)"
                    + "values (?,?,?,?,?,?,?,?)";
            PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
            st.setString(1, a.getTypee());
            st.setDate(2, new java.sql.Date(a.getDatee().getTime()));
            st.setDate(3, new java.sql.Date(a.getOpenDate().getTime()));
            st.setDouble(4, a.getStakes());
            st.setString(5, a.getResult());
            st.setInt(6, a.getC_ID());
            st.setString(7, a.getID_B());
            st.setString(8, a.getNamee());

            st.executeUpdate();

            JOptionPane.showMessageDialog(null, " Hello  " + speedyBet.service.dao.model.Customer.C_FirstName + ""
                    + " \n your BET " + a.getNamee() + " \n has been placed successfully ");

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    
    public static void fullTableBetsByCutomerID(JTable table, String name_search) throws ClassNotFoundException {
        String columns_name[] = {" Bet Name", "Market Type", "Replace Date", "Open Date"};
        table_model = new DefaultTableModel(getDataMYbets("bets"), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     
    }

    public static Object[][] getDataMYbets(String table_name) throws ClassNotFoundException {
        Object[][] data = null;
        try {
            Statement st = DBConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select ID_B , name , betType , date , openDate from bets where customerID = " + speedyBet.service.dao.model.Customer.C_IDCustomer);
            rs.last();
            data = new String[rs.getRow()][5];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString(2);
                data[i][1] = rs.getString(3);
                data[i][2] = rs.getString(4);
                data[i][3] = rs.getString(5);
                data[i][4] = rs.getString(1);

                i++;
            }
            DataHasID = data;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public static String getID(int x) {
        return DataHasID[x][4] + "";
    }

   
    public String[] getDataBySearch(String table_name, int number, String column_name, String key_search) throws ClassNotFoundException {
        String[] data = null;
        try {
            Statement st = DBConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from " + table_name + " where " + column_name + " =" + key_search);
            rs.last();
            data = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {

                data[i] = rs.getString(number);
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public void deleteBet(String ID_B, String bettype) throws ClassNotFoundException {
        try {
            java.sql.Statement st = DBConnection.getconnection().createStatement();
            st.executeUpdate("delete  FROM bets where ID_B = '" + ID_B + "' and customerID = " + speedyBet.service.dao.model.Customer.C_IDCustomer + "  and betType = '" + bettype + "';");
            st.close();
            Frame.DeleteAlert.setText("Your Bet has been successfully removed ");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
