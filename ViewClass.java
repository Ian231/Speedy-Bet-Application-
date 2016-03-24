package speedyBet.service.ui;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import speedyBet.service.dao.controller.DBConnection;
import speedyBet.service.dao.model.Bank;
import speedyBet.service.dao.model.Bet;
import speedyBet.service.dao.model.Customer;
import speedyBet.service.dao.model.Payment;
import static speedyBet.service.ui.Frame.TableBetsTable;

public class ViewClass {

//  payment object  contains data of the payments
    Payment payment = new Payment();
//    customer object contains data of 
    Customer customer = new Customer();
    // bet contians data of bets 
    Bet BET = new Bet();

//    object form bank account 
    Bank bank = new Bank();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy   'at' hh:mm a");
    java.util.Date date = new java.util.Date();
    public String today = format.format(date);

    public void insertPayments(JTextField PayentAmount, JTextField Estimated, JComboBox oddsAvialable) {

        int flag = 0;
//        check is the field contains data on empty 
//        if it empty chnage the BOrder color to red 
        if (PayentAmount.getText().isEmpty()) {
            flag = 1;
            PayentAmount.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red)); // border red 
            flag = 1;
        } else {
            if (Double.parseDouble(PayentAmount.getText()) > Double.parseDouble(Frame.AleartBankMonyBaymentLable.getText())) {
                flag = 1;
                PayentAmount.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red)); // border red 
                flag = 1;
                JOptionPane.showMessageDialog(null, "Sorry Your budget Only Contain " + Frame.AleartBankMonyBaymentLable.getText());
            } else {
                PayentAmount.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
            }
        }

        if (Estimated.getText().isEmpty() || !Estimated.getText().matches("[0-9]{1,13}(\\.[0-9]*)?")) {
            flag = 1;
            Estimated.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red)); // border red 
            flag = 1;
        } else {
            Estimated.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        }

        if (oddsAvialable.getSelectedItem().toString().equals("Selecte Odds")) {
            flag = 1;
            oddsAvialable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red)); // border red 
            flag = 1;
        } else {
            oddsAvialable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        }

        if (flag == 0) {
            int row = Frame.TablePayments.getSelectedRow();  // get selected row from table 
            if (row != -1) {    // true if no row selected 
                int id_b = Integer.parseInt(speedyBet.service.dao.controller.Payment.getID(row));  // get bet id form table 
                payment.setB_id(id_b);
                payment.setC_id(speedyBet.service.dao.model.Customer.C_IDCustomer);

                payment.setsTakes(Double.parseDouble(PayentAmount.getText()));
                payment.setEstimated(Double.parseDouble(Estimated.getText()));
                payment.setOddsAvilabel(oddsAvialable.getSelectedItem().toString());
                new speedyBet.service.dao.controller.Payment().Addpayment(payment);  // invok method addpayment to add payment data 

            } else {
                JOptionPane.showMessageDialog(null, "Pleas Select Items From Bets Table To Add Amount OF Payments ");
            }
        } else {
            Frame.paymentAlart.setText("OOPS Please Make Sure form You Data ");
        }
    }

    public void resetpaymentFeld() {
        Frame.paymentAlart.setText(" ");
        Frame.paymentField.setText("");
        Frame.paymentField_estmaited.setText("");

        Frame.payment_jComboBox3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        Frame.paymentField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        Frame.paymentField_estmaited.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        Frame.payment_jComboBox3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
    }

    public void resetpaymentFeld2() {

        Frame.paymentField.setText("");
        Frame.paymentField_estmaited.setText("");

        Frame.payment_jComboBox3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        Frame.paymentField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        Frame.paymentField_estmaited.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
        Frame.payment_jComboBox3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153))); // border with normal color
    }

    public void UpdateCustomer(JTextField firstName, JTextField lastName,
            JTextField Email, JTextField UserName, JTextField password, JTextField Answar, JDateChooser dateOfBirth, JComboBox question) {
        int flag = 0;
        // check the field is empty or contains data 
        if (firstName.getText().isEmpty()) {

            // is empty set the border read and flag = 1 
            flag = 1;
            firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        // check the field is empty or contains data 
        if (lastName.getText().isEmpty()) {
            lastName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            lastName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (Email.getText().isEmpty()) {
            Email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (UserName.getText().isEmpty()) {

            // is empty set the border read and flag = 1 
            UserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            UserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (password.getText().isEmpty()) {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }
        // check the field is empty or contains data 
        if (Answar.getText().isEmpty()) {
            Answar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Answar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (question.getSelectedItem().toString().equals("Choose Your Security Question ..")) {
            question.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            question.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (dateOfBirth.getDate() == null) {

            // is null set the border read and flag = 1 
            dateOfBirth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            dateOfBirth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        if (flag == 0) {
            customer.setFirstName(firstName.getText());
            customer.setLastNamr(lastName.getText());
            customer.setEmail(Email.getText());
            customer.setUserName(UserName.getText());
            customer.setPassword(password.getText());
            customer.setDateOfBirth(dateOfBirth.getDate());
            customer.setQuestion(question.getSelectedItem().toString());
            customer.setAnswer(Answar.getText());
            new speedyBet.service.dao.controller.Customer().UpdateCustomerData(customer);
        } else {
            Frame.registration_alerm1.setText("OOPS Please Make Sure form You Data ");
        }
    }

    public void releseCustomerDateFildesUpdate() {

        // reset this textfildes 
        Frame.CustomerFName1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerLName1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerEmail1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerUserName1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerPassword1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerAnswer1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerPassword1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerQusetion1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        Frame.CustomerFName1.setText("");
        Frame.CustomerLName1.setText("");
        Frame.CustomerEmail1.setText("");
        Frame.CustomerPassword1.setText("");
        Frame.CustomerUserName1.setText("");
        Frame.CustomerAnswer1.setText("");
        Frame.Customer_DateChooser1.setDateFormatString(today);

    }

    public void releseCustomerDateFildes() {

        // reset the fields of Customer panale 
        Frame.CustomerFName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerLName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerUserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerAnswar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.CustomerQusetion.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        Frame.CustomerFName.setText("");
        Frame.CustomerLName.setText("");
        Frame.CustomerEmail.setText("");
        Frame.CustomerPassword.setText("");
        Frame.CustomerUserName.setText("");
        Frame.CustomerAnswar.setText("");
        Frame.Customer_DateChooser.setDateFormatString(today);

    }

    public void releseBankDateFildes() {

        // reset the fields of Customer panale 
        Frame.Bank_BillingAddress.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.Bank_Cardnumber.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.Bank_Money.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.Bank_SortCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        Frame.BankAlearm2.setText("");
        Frame.Bank_BillingAddress.setText("");
        Frame.Bank_Cardnumber.setText("");
        Frame.Bank_Money.setText("");
        Frame.Bank_SortCode.setText("");

    }

    public void releseUpadateBankMoneyData() {
        Frame.Bank_Money22.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        Frame.BankPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        Frame.Bank_Money22.setText("");
        Frame.BankPassword.setText("");
        Frame.BankAlearm3.setText("");
    }

    public void insertCustomer(JTextField firstName, JTextField lastName,
            JTextField Email, JTextField UserName, JTextField password, JTextField Answar, JDateChooser dateOfBirth, JComboBox question) {
        int flag = 0;

        // check the field is empty or contains data 
        if (firstName.getText().isEmpty()) {

            flag = 1;

            // is empty set the border read and flag = 1 
            firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        // check the field is empty or contains data 
        if (lastName.getText().isEmpty()) {

            // is empty set the border read and flag = 1 
            lastName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            lastName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (Email.getText().isEmpty()) {
            Email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (UserName.getText().isEmpty()) {
            UserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            UserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (password.getText().isEmpty()) {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (Answar.getText().isEmpty()) {

            // is empty set the border read and flag = 1 
            Answar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Answar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (question.getSelectedItem().toString().equals("Choose Your Security Question ..")) {

            // is empty set the border read and flag = 1 
            question.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            question.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (dateOfBirth.getDate() == null) {
            dateOfBirth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            dateOfBirth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        if (flag == 0) {
            customer.setFirstName(firstName.getText());
            customer.setLastNamr(lastName.getText());
            customer.setEmail(Email.getText());
            customer.setUserName(UserName.getText());
            customer.setPassword(password.getText());
            customer.setDateOfBirth(dateOfBirth.getDate());
            customer.setQuestion(question.getSelectedItem().toString());
            customer.setAnswer(Answar.getText());
            new speedyBet.service.dao.controller.Customer().AddCustomer(customer);
        } else {
            Frame.registration_alerm.setText("Please fill in all required fields on this form! ");
        }
    }

    public void insertBankAccount(JTextField Bank_Cardnumber, JTextField Bank_SortCode,
            JTextField Bank_BillingAddress, JTextField Bank_Money) {
        int flag = 0;

        // check the field is empty or contains data 
        if (Bank_Cardnumber.getText().isEmpty() || Bank_Cardnumber.getText().length() >= 9) {

            flag = 1;

            // is empty set the border read and flag = 1 
            Bank_Cardnumber.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Bank_Cardnumber.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        // check the field is empty or contains data 
        if (Bank_SortCode.getText().isEmpty()) {

            // is empty set the border read and flag = 1 
            Bank_SortCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Bank_SortCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (Bank_BillingAddress.getText().isEmpty()) {
            Bank_BillingAddress.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Bank_BillingAddress.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (Bank_Money.getText().isEmpty()) {
            Bank_Money.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Bank_Money.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

//        // check the field is empty or contains data 
//        if (dateOfBirth.getDate() == null) {
//            dateOfBirth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
//            flag = 1;
//        } else {
//            dateOfBirth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
//
//        }
        if (flag == 0) {
            bank.setC_ID(Customer.C_IDCustomer);
            bank.setCatdNo(Integer.parseInt(Bank_Cardnumber.getText()));
            bank.setBankAccount(new Date());
            bank.setBillingAddress(Bank_BillingAddress.getText());
            bank.setSortCode(Bank_SortCode.getText());
            bank.setMoney(Double.parseDouble(Bank_Money.getText()));

//            customer.setDateOfBirth(dateOfBirth.getDate());
            new speedyBet.service.dao.controller.Bank().AddBankAccount(bank);
        } else {
            Frame.BankAlearm2.setText("Please fill in the right information before procceding");
        }
    }

    public boolean repetornotUpdate(String table, String column, String key) {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();
            ResultSet result = stt.executeQuery("SELECT " + column + " from  " + table + "  where " + column + " = '" + key + "'");
            result.last();
            int b = result.getRow();
            result.beforeFirst();
            System.out.println(b);
            int xy = 0;
            if (b >= 2) {
                while (result.next()) {
                    xy = 2;
                }
            }
            if (xy == 2) {
                if (column.equals("user_name")) {
                    JOptionPane.showMessageDialog(null, "This UserName is Already in our system ");
                }
                if (column.equals("email")) {

                    JOptionPane.showMessageDialog(null, "The Email is Already registered before ");
                }
                return false;

            }
            if (xy == 0) {
              
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    // check is this key repeted or not pefore ading to DB
    public boolean repetornot(String table, String column, String key) {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();
            ResultSet result = stt.executeQuery("SELECT " + column + " from  " + table + "  where " + column + " = '" + key + "'");
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
                if (column.equals("user_name")) {
                    JOptionPane.showMessageDialog(null, "The UserName is Already recevide you Have to Change is ");
                }
                if (column.equals("cardNO")) {
                    JOptionPane.showMessageDialog(null, "The Card Number is Already recevide you Have to Change is ");
                }
                if (column.equals("email")) {

                    JOptionPane.showMessageDialog(null, "The Email is Already register before ");
                }
                if (column.equals("c_id")) {
                    JOptionPane.showMessageDialog(null, "You already  Add Banking Account Before  ");
                    new ViewClass().releseBankDateFildes();
                }
                return false;

            }
            if (xy == 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    // check is this key repeted or not pefore ading to DB
    public boolean repetornotForBankAccount(String table, String culum, String key) {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();
            ResultSet result = stt.executeQuery("SELECT " + culum + " from  " + table + "  where " + culum + " = '" + key + "'");
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

    // check this Key Repeting or not befor adding to Payment Table 
    public boolean repetornotForIDPayment(String key) {
        try {

            java.sql.Statement stt = DBConnection.getconnection().createStatement();
            System.out.println(key + " b_id -----------");
            ResultSet result = stt.executeQuery("SELECT b_id from  payment  where b_id =  " + key + " and c_id = " + Customer.C_IDCustomer);
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

                JOptionPane.showMessageDialog(null, "Hello " + Customer.C_FirstName + " \nYou Adding a Payment Amount For This Bet Before");

                return false;

            }
            if (xy == 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    //check this key is repeted or not before adding it to bets table 
    public boolean repetOrNotForIDBet(String table, String culum, String key) {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();

//            ResultSet result = stt.executeQuery("SELECT " + culum + " from  " + table + "  where " + culum + " = '" + key + "' and customer_id = '" + Customer.C_IDCustomer + "'");
            ResultSet result = stt.executeQuery("SELECT " + culum + "  FROM `" + table + "` WHERE " + culum + " = '" + key + "' and customer_id = " + Customer.C_IDCustomer);
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
                if (culum.equals("user_name")) {
                    JOptionPane.showMessageDialog(null, "The UserName is Already recevide you Have to Change is ");
                }
                if (culum.equals("email")) {
                    JOptionPane.showMessageDialog(null, "The Email is Already register before ");
                }
                return false;

            }
            if (xy == 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean repetOrNotForIDBet2Key(String table, String column, String key, String key2) {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();
            ResultSet result = stt.executeQuery("SELECT " + column + "  FROM `" + table + "` WHERE " + column + " = '" + key + "' and customerID = " + Customer.C_IDCustomer + " and  betType = '" + key2 + "'");
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
                if (column.equals("user_name")) {
                    JOptionPane.showMessageDialog(null, "The UserName has already been registered before");
                }
                if (column.equals("email")) {
                    JOptionPane.showMessageDialog(null, "This email address has already been registered before");
                }
                return false;

            }
            if (xy == 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean recoverThePasswordView(JTextField Email_OR_UserName) throws ClassNotFoundException, SQLException {
        int flag = 0;

        // check the field is empty or contains data 
        if (Email_OR_UserName.getText().isEmpty()) {
            flag = 1;
            Email_OR_UserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        } else {
            Email_OR_UserName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }
        // check the field is empty or contains data 
        if (flag == 0) {
            customer.setRecoveField(Email_OR_UserName.getText());
            new speedyBet.service.dao.controller.Customer().getQuestionVaUserNameOrEmail(customer);
            return true;
        } else {
            Frame.recoverquestion.setText("");
            Frame.RecoverAlert1.setText("Please add the relevant fields ");
        }
        return false;
    }

    // compare to string to gether and return true if it is the same 
    public boolean checkTheAnswer(String ans, String myAnswerInput) {
        if (ans.equals(myAnswerInput)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePasswordOperation(JTextField password1, JTextField password2) throws ClassNotFoundException, SQLException {
        int flag = 0;

        // check the field is empty or contains data 
        if (password1.getText().isEmpty()) {
            flag = 1;
            password1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        } else {
            password1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        // check the field is empty or contains data 
        if (password2.getText().isEmpty()) {
            flag = 1;
            password2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        } else {
            password2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        if (flag == 0) {
            if (checkToPassIsEquals(password1.getText(), password2.getText())) {
                customer.setPassword(password1.getText());
                new speedyBet.service.dao.controller.Customer().updatePasswordForUser(customer);
                resetFields(password1, password2);
                return true;
            } else {
                updatePassword.updatePasswordAlert.setText("The password does not currently seem to be the same ");
                return false;
            }
        } else {

            updatePassword.updatePasswordAlert.setText("Please check the fields and try again");
        }
        return false;
    }

    // compare to string pass and repass 
    public boolean checkToPassIsEquals(String pass1, String Pass2) {
        if (pass1.equals(Pass2)) {
            return true;
        } else {
            return false;
        }
    }

    public void updateMoneyBanking(JTextField Money, JTextField password) {
        int flag = 0;
        // check the field is empty or contains data 
        if (Money.getText().isEmpty()) {

            // is empty set the border read and flag = 1 
            flag = 1;
            Money.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            Money.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        // check the field is empty or contains data 
        if (password.getText().isEmpty()) {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            flag = 1;
        } else {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }

        // check the field is empty or contains data 
        if (!password.getText().equals(Customer.C_Password)) {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
            Frame.BankAlearm3.setText("Please Make Sure form You Password  ");
            flag = 1;
        } else {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));

        }
        if (flag == 0) {
            bank.setMoney(Double.parseDouble(Money.getText()));
            bank.setC_ID(Customer.C_IDCustomer);
            new speedyBet.service.dao.controller.Bank().UpdateBankAccountMoneyData(bank);

        } else {
            Frame.BankAlearm3.setText("please fill in the correct details for your bank account");
        }
    }

    // reset the Password field
    public void resetFields(JTextField password1, JTextField password2) {
        password1.setText("");
        password2.setText("");
    }

    public boolean checkloginFields(JTextField user_Name, JPasswordField password) throws ClassNotFoundException, SQLException {

        int flag = 0;

        // check the field is empty or contains data 
        if (password.getText().isEmpty()) {
            flag = 1;
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        } else {
            password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }

        // check the field is empty or contains data 
        if (user_Name.getText().isEmpty()) {
            flag = 1;
            user_Name.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        } else {
            user_Name.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 204, 153)));
        }
        if (flag == 0) {

            if (new speedyBet.service.dao.controller.Customer().checkLoginCustomer()) {
               
                return true;
            } else {
               
                login.LoginAleart.setText("Incorrect Password Or UserName");
            }

        } else {

            login.LoginAleart.setText("Please fill in the correct corresponding fields ");
        }
        return false;
    }

    public void placeMarketBets(String ID_B, String namee, Date opendate, String marketTypee, int C_ID) {
        BET.setID_B(ID_B);
        BET.setDatee(new Date());
        BET.setNamee(namee);
        BET.setOpenDate(opendate);
        BET.setTypee(marketTypee);
        BET.setC_ID(Customer.C_IDCustomer);
        BET.setResult("");
        BET.setStakes(0);

        if (repetOrNotForIDBet2Key("bets", "ID_B", ID_B, marketTypee)) {
            new speedyBet.service.dao.controller.Bet().AddBETReplace(BET);

        } else {
            JOptionPane.showMessageDialog(null, " Hello  " + speedyBet.service.dao.model.Customer.C_FirstName + ""
                    + " \n your BET " + namee + " \n has been placed before ");

        }

    }

    public void DelteTheBetsMethodCheckSelected(String ID_B, String typee) throws ClassNotFoundException {

        new speedyBet.service.dao.controller.Bet().deleteBet(ID_B, typee);

    }

    public void notificationAction() {

    }

    public void DynamicCalcolatePaymentResult() {

    }

}
