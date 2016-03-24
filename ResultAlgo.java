package speedyBet.service.dao.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ResultAlgo {

    static DefaultTableModel table_model;

    public int customerID[];
    public int customers_ForSingelBet[];
    public double Amounts[];
    public double odd_valueInDouble[];
    public String Bet_S[];
    public String Bet_type[];
    public String Bet_Name[];
    public String OddValue[];
    public String Estmidate[];
    public Date opendate[];
    public double betResultForEachCustomer[];
    int number_row = 0;
    public static int NumberOfRows;
    ArrayList<String> currentBet_S;
    ArrayList<Integer> multiCForSingelBetString;
    public static double GainAndLoss;

    public static Object DataResult[][];

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Object ddd[][];
//        ddd = DataResultForBets();
        getdataForSpecificCustomer(1);
    }

    public boolean checkIsOddOrNot(String ss) {
        String idd = ss.substring(6, ss.indexOf("|"));
        int num = Integer.parseInt(idd);
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }

    }

    public void getCustomerPaymentData() throws ClassNotFoundException, SQLException {
        PreparedStatement st = DBConnection.getconnection().prepareStatement("SELECT   takes , c_id , ID_B , typee , namee , estimated , odd_value  , openDate  FROM payment INNER JOIN bets ON payment.b_id=bets.idbets ;");
        ResultSet result = st.executeQuery();
        result.last();
//        number_row = result.getRow();
        int Nu = 0;

        number_row = 0;
        result.beforeFirst();

        while (result.next()) {
            try {
                if (IsDatepassedNow(result.getDate("openDate"))) {
                    number_row++;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ResultAlgo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        NumberOfRows = number_row;
        System.out.println(number_row + " ---------------- ");
        Amounts = new double[number_row];
        customerID = new int[number_row];
        Bet_S = new String[number_row];
        Bet_type = new String[number_row];
        Bet_Name = new String[number_row];
        OddValue = new String[number_row];
        opendate = new Date[number_row];

        number_row = 0;
        result.beforeFirst();
        while (result.next()) {
            try {
                if (IsDatepassedNow(result.getDate("openDate"))) {
                    Amounts[number_row] = result.getDouble("takes");
                    customerID[number_row] = result.getInt("c_id");
                    Bet_S[number_row] = result.getString("ID_B");
                    Bet_type[number_row] = result.getString("typee");
                    Bet_Name[number_row] = result.getString("namee");
                    OddValue[number_row] = result.getString("odd_value");
                    opendate[number_row] = result.getDate("openDate");
                    number_row++;
                } else {

                    continue;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ResultAlgo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < Amounts.length; i++) {
            System.out.print("   " + Amounts[i]);
            System.out.print("   " + customerID[i]);
            System.out.print("   " + Bet_S[i]);
            System.out.print("   " + Bet_type[i]);
            System.out.print("   " + Bet_Name[i]);
            System.out.print("   " + opendate[i]);
            System.out.print("   " + OddValue[i]);

            System.out.println("___________________________________________" + i);
        }
    }

    public ArrayList<Integer> getMultiCustomerForSingelBet_S(String TheBet_s, int C_idd[], String Bet_ss[], String Bet_ss_typee[]) {

        ArrayList<Integer> multiCustomerForOneBet_s = new ArrayList<Integer>();
        for (int i = 0; i < Bet_ss.length; i++) {
            if (TheBet_s.equals(Bet_ss[i] + "|" + Bet_ss_typee[i])) {
                multiCustomerForOneBet_s.add(C_idd[i]);
            }
        }
        return multiCustomerForOneBet_s;
    }

    public int getIndexOf(int x, int arr[], String bet_ssTypee, String[] bets, String[] bet_typee) {
        for (int i = 0; i < arr.length; i++) {
            if (x == arr[i] && bet_ssTypee.equals(bets[i] + "|" + bet_typee[i])) {
//                System.out.println("_________" + i);
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> removeDublicatedValue(String bet_s[], String Typeee[]) {

        ArrayList<String> current = new ArrayList<String>();
//        ArrayList<String> current = new ArrayList<String>();
        for (int i = 0; i < bet_s.length; i++) {
            if (!current.contains(bet_s[i] + "|" + Typeee[i])) {
                current.add(bet_s[i] + "|" + Typeee[i]);
            }
        }
//        System.out.println(current);
//        ArrayList<String> current2 = new ArrayList<>();
//       
//        
//
//        for (int i = 0; i < current.size(); i++) {
//            current2.add(current.get(i).substring(0, current.get(i).indexOf("|")));
//        }
//        return current2;
        return current;
    }

//    public double getSumPaymentForOneBet(String Bet, ArrayList<Integer> AllCustomerForBet, int allCustomer[], double[] theamounts, String allBets_S[]) {
//
//        System.out.println("______________________________________________________");
//        double sum = 0;
////        System.out.println(AllCustomerForBet +"       -------------");
//        for (int i = 0; i < AllCustomerForBet.size(); i++) {
//            System.out.println(getIndexOf(AllCustomerForBet.get(i), allCustomer, Bet, allBets_S) + "       ---------------------------");
//            sum = sum + theamounts[getIndexOf(AllCustomerForBet.get(i), allCustomer, Bet, allBets_S)];
//        }
//        return sum;
//
//    }
    public double[] getDoubleValueOfOdd_value(String[] odd_value) {
        double[] oddValueInDouble = new double[odd_value.length];
        for (int i = 0; i < odd_value.length; i++) {
            int x = odd_value[i].indexOf("/");
            double n1 = Double.parseDouble(odd_value[i].substring(0, x));
            double n2 = Double.parseDouble(odd_value[i].substring(x + 1, odd_value[i].length()));
            if (n2 != 0) {
                oddValueInDouble[i] = n1 / n2;
                System.out.println(oddValueInDouble[i]);
            } else {
                oddValueInDouble[i] = 0;
                System.out.println(oddValueInDouble[i]);

                continue;
            }
        }
        return oddValueInDouble;
    }

    public String[][] AllNotfication() {

        return null;
    }

    public String[][] SendNotfication() {

        return null;
    }

    public boolean IsDatepassedNow(Date date) throws ParseException {

//        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.UK);
//        Date date;
//        date = (Date) formatter.parse(dateStr);
        Calendar c = Calendar.getInstance();
// set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

// and get that as a Date
        Date today = c.getTime();

// or as a timestamp in milliseconss
        long todayInMillis = c.getTimeInMillis();

// user-specified date which you are testing
// let's say the components come from a form or something
        int year = 2011;
        int month = 5;
        int dayOfMonth = 20;

// reuse the calendar to set user specified date
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

// and get that as a Date
        Date dateSpecified = c.getTime();

// test your condition   Sun Mar 20 15:30:00 EET 2016
        if (date.before(new Date())) {
//            System.out.println("Before");
            return true; // l wa2t 3add def l data bta3tk  must be true but false for test 
        } else {
//            System.out.println("not Befor");
            return true; // lsa mga4 l wa2t da 
        }

    }

    public static Object[][] DataResultForBets() throws ClassNotFoundException, SQLException {

        ResultAlgo ra = new ResultAlgo();
        ra.getCustomerPaymentData();
        ra.odd_valueInDouble = ra.getDoubleValueOfOdd_value(ra.OddValue);
        ra.currentBet_S = ra.removeDublicatedValue(ra.Bet_S, ra.Bet_type);
        System.out.println(ra.currentBet_S);
        System.out.println("_____________________________________________________________________");
        DataResult = new Object[NumberOfRows][9];
        for (int i = 0; i < ra.currentBet_S.size(); i++) {
            String W_L = "";
            if (ra.checkIsOddOrNot(ra.currentBet_S.get(i))) { 
                W_L = "WIN";   // chech te id of bet and roundmy detected is it win or loss 
            } else {
                W_L = "LOSS";
            }
            ra.multiCForSingelBetString = ra.getMultiCustomerForSingelBet_S(ra.currentBet_S.get(i), ra.customerID, ra.Bet_S, ra.Bet_type);

            System.out.println(ra.multiCForSingelBetString);
            System.out.println("Index Of ");

            for (int j = 0; j < ra.multiCForSingelBetString.size(); j++) {
                System.out.print("  ---- " + ra.getIndexOf(ra.multiCForSingelBetString.get(j), ra.customerID, ra.currentBet_S.get(i), ra.Bet_S, ra.Bet_type));
                int index = ra.getIndexOf(ra.multiCForSingelBetString.get(j), ra.customerID, ra.currentBet_S.get(i), ra.Bet_S, ra.Bet_type);
                DataResult[index][0] = ra.customerID[index];
                DataResult[index][1] = ra.Bet_S[index];
                DataResult[index][2] = ra.Bet_Name[index];
                DataResult[index][3] = ra.Bet_type[index];
                DataResult[index][4] = ra.OddValue[index];
                DataResult[index][5] = ra.Amounts[index];
                DataResult[index][6] = W_L;
                if (W_L.equals("WIN")) {
                    DataResult[index][7] = ra.odd_valueInDouble[index] * ra.Amounts[index];
                } else {
                    DataResult[index][7] = 0;
                }
            }
            System.out.println("");
        }
//        ra.multiCForSingelBetString = ra.getMultiCustomerForSingelBet_S(ra.currentBet_S.get(1), ra.customerID, ra.Bet_S, ra.Bet_type);
//        System.out.println("Multi Cutomer ....  " + ra.multiCForSingelBetString);
//        System.out.println("Index Of " + ra.getIndexOf(ra.multiCForSingelBetString.get(0), ra.customerID, ra.currentBet_S.get(1), ra.Bet_S, ra.Bet_type));

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < DataResult.length; i++) {
            System.out.println("");
            System.out.print("      " + DataResult[i][0]);
            System.out.print("      " + DataResult[i][1]);
            System.out.print("      " + DataResult[i][4]);
            System.out.print("      " + DataResult[i][5]);
            System.out.print("      " + DataResult[i][6]);
            System.out.print("      " + DataResult[i][7]);

            System.out.print("      " + DataResult[i][2]);
            System.out.print("      " + DataResult[i][3]);

        }

        return DataResult;
    }

    public static Object[][] getdataForSpecificCustomer(int id_c) throws ClassNotFoundException, SQLException {
        GainAndLoss = 0;
        Object allData[][] = DataResultForBets();
        int x = 0;
        int NumberForCustomer = 0;
        for (int i = 0; i < allData.length; i++) {
            if ((int) allData[i][0] == id_c) {
                NumberForCustomer++;
            }
        }
        Object CustomerDataaa[][] = new Object[NumberForCustomer][8];
        for (int i = 0; i < allData.length; i++) {
            if ((int) allData[i][0] == id_c) {

                CustomerDataaa[x][6] = allData[i][0];
                CustomerDataaa[x][7] = allData[i][1];
                CustomerDataaa[x][0] = allData[i][2];
                CustomerDataaa[x][1] = allData[i][3];
                CustomerDataaa[x][2] = allData[i][4];
                CustomerDataaa[x][3] = allData[i][5];
                CustomerDataaa[x][4] = allData[i][6];
                CustomerDataaa[x][5] = allData[i][7];
                x++;
                System.out.println("*************** " + x);
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("_______________________________________");
        for (int i = 0; i < CustomerDataaa.length; i++) {

            System.out.println("");
            System.out.print("      " + CustomerDataaa[i][0]);
            System.out.print("      " + CustomerDataaa[i][1]);
            System.out.print("      " + CustomerDataaa[i][4]);
            System.out.print("      " + CustomerDataaa[i][5]);
            System.out.print("      " + CustomerDataaa[i][6]);
            System.out.print("      " + CustomerDataaa[i][7]);

            System.out.print("      " + CustomerDataaa[i][2]);
            System.out.print("      " + CustomerDataaa[i][3]);

        }
        for (int i = 0; i < CustomerDataaa.length; i++) {
            if (CustomerDataaa[i][4].toString().equals("WIN")) {
                GainAndLoss = GainAndLoss + Double.parseDouble(CustomerDataaa[i][5] + "");
            } else {
                GainAndLoss = GainAndLoss - Double.parseDouble(CustomerDataaa[i][3] + "");
            }
        }
        return CustomerDataaa;
    }

    public static void fullTableBetsByCustomerIDResultBets(JTable table, String name_searck) throws ClassNotFoundException, SQLException {
        String columns_name[] = {"Bet Name", "Markt Type", "ODD Value", "Payment", "Result", "Budget Bet"};
//        String columns_name[] = {" Bet Name", "Open Date", "ID"};
        table_model = new DefaultTableModel(getdataForSpecificCustomer(speedyBet.service.dao.model.Customer.C_IDCustomer), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println("Good");
    }

}
