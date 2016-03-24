package speedyBet.service.dao.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import speedyBet.service.ui.Frame;
import speedyBet.service.ui.login;

public class Customer {

    int number_row;
    public static String AnswerToQuestion = "";
    public static String questionDB = "-";
    public static String ideintefied = "";

    
    // update Customer Method 
    public void UpdateCustomerData(speedyBet.service.dao.model.Customer a) {
        try {
            System.out.println("in try insert");
            String sss = "update  customer set firstName = ? , lastName = ? , DOB = ? , userName = ?, email = ?, password = ?, question = ? , answer = ?   where customerID = " + speedyBet.service.dao.model.Customer.C_IDCustomer;

            PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
            st.setString(1, a.getFirstName());
            st.setString(2, a.getLastNamr());
            st.setDate(3, new java.sql.Date(a.getDateOfBirth().getTime()));
            st.setString(4, a.getUserName());
            st.setString(5, a.getEmail());
            st.setString(6, a.getPassword());
            st.setString(7, a.getQuestion());
            st.setString(8, a.getAnswer());

            st.executeUpdate();

            speedyBet.service.dao.model.Customer.C_FirstName = a.getFirstName();
            speedyBet.service.dao.model.Customer.C_LastName = a.getLastNamr();
            speedyBet.service.dao.model.Customer.C_Email = a.getEmail();
            speedyBet.service.dao.model.Customer.C_Password = a.getPassword();
            speedyBet.service.dao.model.Customer.C_Question = a.getQuestion();
            speedyBet.service.dao.model.Customer.C_Answer = a.getAnswer();
            Frame.registration_alerm1.setText("Succsessful Update For Your personal Account Information");
            Frame.LNAME.setText(speedyBet.service.dao.model.Customer.C_FirstName);
            new speedyBet.service.ui.ViewClass().releseCustomerDateFildesUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AddCustomer(speedyBet.service.dao.model.Customer a) {
        try {
            String sss = "insert into customer (firstName , lastName , email , userName , password , question , answer , DOB)"
                    + "values (?,?,?,?,?,?,?,?)";
            PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
            st.setString(1, a.getFirstName());
            st.setString(2, a.getLastNamr());
            st.setString(3, a.getEmail());
            st.setString(4, a.getUserName());
            st.setString(5, a.getPassword());
            st.setString(6, a.getQuestion());
            st.setString(7, a.getAnswer());
            st.setDate(8, new java.sql.Date(a.getDateOfBirth().getTime()));

            st.executeUpdate();
            Frame.registration_alerm.setText("Succsessful Registraion Click Back to Login With Your Email And Password .");

            new speedyBet.service.ui.ViewClass().releseCustomerDateFildes();
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    // get question according to UserName Or password From DB 
    public String getQuestionVaUserNameOrEmail(speedyBet.service.dao.model.Customer a) throws ClassNotFoundException, SQLException {

//        
        PreparedStatement st = DBConnection.getconnection().prepareStatement("SELECT question , answer  from customer where email ='" + a.getRecoveField() + "'  OR user_name = '" + a.getRecoveField() + "'");
        ResultSet result = st.executeQuery();

        if (result.next()) {
            questionDB = result.getString("question");
            AnswerToQuestion = result.getString("answer");
            ideintefied = a.getRecoveField();
        } else {
            questionDB = "";

        }

        if (questionDB.equals("")) {
            Frame.RecoverAlert1.setText("Not a Valid  Enter  Email Or User Name");
            Frame.recoverquestion.setText("");

        } else {

            Frame.RecoverAlert1.setText("Write the Answer to This Question to recover Your Password ");
            Frame.recoverquestion.setText(questionDB);

        }

        return questionDB;

    }

    // update password according to customer inputs 
    public void updatePasswordForUser(speedyBet.service.dao.model.Customer a) {
        try {
           
            String sss = "update customer  set password = ?    where userName = '" + ideintefied + "'  OR email = '" + ideintefied + "' ";
            PreparedStatement st = DBConnection.getconnection().prepareStatement(sss);
            st.setString(1, a.getPassword());
            st.executeUpdate();
            speedyBet.service.ui.updatePassword.updatePasswordAlert.setText("Successfull Update Click Back To Login ");

        } catch (Exception e) {
            System.out.println(e);

        }

    }


    // check the UserName And password Is the user is real user or not (AUthentication)
    public boolean checkLoginCustomer() throws ClassNotFoundException, SQLException {
        PreparedStatement st = DBConnection.getconnection().prepareStatement("SELECT * from  customer "
                + " where userName = '" + login.LoginCustomerUserName.getText() + "' and  password = '" + login.LoginCustomerPassword.getText() + "'");

        ResultSet result = st.executeQuery();
        result.last();
        number_row = result.getRow();
        number_row = 0;
        result.beforeFirst();
        while (result.next()) {
            System.out.println(login.LoginCustomerUserName.getText());
            System.out.println(login.LoginCustomerPassword.getText());
            speedyBet.service.dao.model.Customer.C_IDCustomer = result.getInt("customerID");
            speedyBet.service.dao.model.Customer.C_FirstName = result.getString("firstName");
            speedyBet.service.dao.model.Customer.C_LastName = result.getString("lastName");
            speedyBet.service.dao.model.Customer.C_Email = result.getString("email");
            speedyBet.service.dao.model.Customer.C_Password = result.getString("password");
            speedyBet.service.dao.model.Customer.C_UserName = result.getString("userName");
            return true;
        }
        return false;
    }

    // get all data for customer to set this data on the filds of Customer edit panal to update the Customer personal information 
    public void putUserCustomer() {
        try {
            java.sql.Statement stt = DBConnection.getconnection().createStatement();

            ResultSet result = stt.executeQuery("SELECT firstName , lastName , userName , email, password , question , answer  from customer   "
                    + "where customerID  = " + speedyBet.service.dao.model.Customer.C_IDCustomer);

            result.last();
            int b = result.getRow();
            result.beforeFirst();
            System.out.println(b);
            if (b >= 1) {
                while (result.next()) {
                    Frame.CustomerFName1.setText(result.getString("firstName"));
                    Frame.CustomerLName1.setText(result.getString("lastName"));
                    Frame.CustomerUserName1.setText(result.getString("userName"));
                    Frame.CustomerEmail1.setText(result.getString("email"));

                }
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
