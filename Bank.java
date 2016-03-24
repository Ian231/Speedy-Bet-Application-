/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyBet.service.dao.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static speedyBet.service.dao.controller.Payment.DataHasID;
import speedyBet.service.ui.Frame;

public class Bank {

    public void AddBankAccount(speedyBet.service.dao.model.Bank a) {
        try {
            String sss = "insert into bank (c_id , cardNO , billingAddress , bankAccount , sortCode , money)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
            st.setInt(1, a.getC_ID());
            st.setInt(2, a.getCatdNo());
            st.setString(3, a.getBillingAddress());
            st.setDate(4, new java.sql.Date(a.getBankAccount().getTime()));
            st.setString(5, a.getSortCode());
            st.setDouble(6, a.getMoney());
            st.executeUpdate();
            new speedyBet.service.ui.ViewClass().releseBankDateFildes();
            Frame.BankAlearm2.setText("Succsessful Registraion For Your Bank Acount ");
            HasBankAcount("bank", "c_id", speedyBet.service.dao.model.Customer.C_IDCustomer + "");
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public double getAmountFromBankAccont() {
        double money = 0.0;

        try {
            Statement st = DBConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select money from bank where c_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer);
            rs.last();
            rs.beforeFirst();
            while (rs.next()) {
                money = rs.getDouble(1);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return money;

    }

    public boolean HasBankAcount(String table, String column, String key) {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();
            ResultSet result = stt.executeQuery("SELECT " + column + " from  " + table + "  where " + column + " = " + key);
            result.last();
            int b = result.getRow();
            result.beforeFirst();
            System.out.println(b);
            int xy = 0;
            if (b >= 1) {
                while (result.next()) {
                    xy = 1;
                }
            }
            if (xy == 1) {
                if (column.equals("c_id")) {
//                    JOptionPane.showMessageDialog(null, "Hass Bank ACCOunt");
                    Frame.BankLpagest.setText(getAmountFromBankAccont() + "");
                    Frame.AleartBankMonyBaymentLable.setText(getAmountFromBankAccont() + "");
                }
                return true;
            }
            if (xy == 0) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void UpdateBankAccountMoneyData(speedyBet.service.dao.model.Bank a) {
        if (HasBankAcount("bank", "c_id", speedyBet.service.dao.model.Customer.C_IDCustomer + "")) {
            try {
                System.out.println("in try update");
                String sss = "update  bank set money = ?   where c_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer;
                PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
                double currentMoney = getAmountFromBankAccont();
                st.setDouble(1, a.getMoney() + currentMoney);
                st.executeUpdate();
                Frame.BankLpagest.setText(getAmountFromBankAccont() + "");
                Frame.AleartBankMonyBaymentLable.setText(getAmountFromBankAccont() + "");

                new speedyBet.service.ui.ViewClass().releseUpadateBankMoneyData();
                Frame.BankAlearm3.setText("Successful Transfer For Money");

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "You are unable to Transfer money at this present momemnt of time");
        }
    }

    public void UpdateBankAccountMoneyData(double money) {
        if (HasBankAcount("bank", "c_id", speedyBet.service.dao.model.Customer.C_IDCustomer + "")) {
            try {
                System.out.println("in try update");
                String sss = "update  bank set money = ?   where c_id = " + speedyBet.service.dao.model.Customer.C_IDCustomer;
                PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
                double currentMoney = getAmountFromBankAccont();
                st.setDouble(1, money + currentMoney);
                st.executeUpdate();
                Frame.BankLpagest.setText(getAmountFromBankAccont() + "");
                Frame.AleartBankMonyBaymentLable.setText(getAmountFromBankAccont() + "");

                new speedyBet.service.ui.ViewClass().releseUpadateBankMoneyData();
                Frame.BankAlearm3.setText("Successful Transfer For Money");

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "You Can't Transfer  Money unfortunately");
        }
    }

}
