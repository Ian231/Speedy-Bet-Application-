package speedyBet.service.dao.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import static speedyBet.service.dao.controller.Bet.table_model;
import speedyBet.service.ui.Frame;

public class Payment {

    public static Object DataHasID[][];

       // adding new payment for my bets placed 
    public void Addpayment(speedyBet.service.dao.model.Payment a) {
        try {
            String sss = "insert into payment (stakes , c_id , b_id , estimated , odd_value )"
                    + "values (?,?,?,?,?)";
            PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
         
            st.setDouble(1 , a.getsTakes());
            st.setInt(2, a.getC_id());
            st.setInt(3, a.getB_id());
            st.setDouble(4, a.getEstimated());
            st.setString(5, a.getOddsAvilabel());

            st.executeUpdate();
            Frame.paymentAlart.setText("Succsess To Add Payment to Bet ");

            double CurrentMoney = Double.parseDouble(Frame.AleartBankMonyBaymentLable.getText());
            String ssss = "update  bank set money = ?   where c_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer;
            PreparedStatement stt = DBConnection.getconnection().prepareStatement(ssss);

           stt.setDouble(1, CurrentMoney - a.getsTakes());
            stt.executeUpdate();
            Frame.BankLpagest.setText(new Bank().getAmountFromBankAccont() + "");
            Frame.AleartBankMonyBaymentLable.setText(new Bank().getAmountFromBankAccont() + "");
            new speedyBet.service.ui.ViewClass().resetpaymentFeld2();
        } catch (Exception e) {
            System.out.println(e);

        }

    }
     

    public static void fullTableBetsByCutomerID(JTable table, String name_search) throws ClassNotFoundException {
        String columns_name[] = {" Bet Name", "Open Date"};
//        String columns_name[] = {" Bet Name", "Open Date", "ID"};
        table_model = new DefaultTableModel(getDataMYbets("bets"), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
    }

    // get all bets for user 
    // to set it on the table 
    public static Object[][] getDataMYbets(String table_name) throws ClassNotFoundException {
        Object[][] data = null;
        try {
            Statement st = DBConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select betID , name , openDate from bets where customer_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer);
            rs.last();
            data = new String[rs.getRow()][3];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString(2);
                data[i][1] = rs.getString(3);
                data[i][2] = rs.getString(1);
                i++;
            }
            DataHasID = data;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public static String getID(int x) {
        return DataHasID[x][2] + "";
    }

    public void deleteMyPayments(String bettype[], String Name[]) throws ClassNotFoundException {
        try {
            java.sql.Statement st = DBConnection.getconnection().createStatement();
            for (int i = 0; i < bettype.length; i++) {
                ResultSet rs = st.executeQuery("select betID from bets where customer_id = "
                        + speedyBet.service.dao.model.Customer.C_IDCustomer + "  and betType = '" + bettype[i] + "' and name = '" + Name[i] + "' ;");
                rs.next();
                int x = rs.getInt("betID");
                st.executeUpdate("delete  FROM payment  where c_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer + "  and b_id = " + x);
                st.executeUpdate("delete  FROM bets  where customer_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer + "  and betID = " + x);
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
