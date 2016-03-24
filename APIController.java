/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyBet.service.dao.controller;

import com.betfair.aping.ApiNGJsonRpcDemo;
import static com.betfair.aping.ApiNGJsonRpcDemo.DataFootBAll;
import com.betfair.aping.api.ApiNgJsonRpcOperations;
import com.betfair.aping.api.ApiNgOperations;
import com.betfair.aping.entities.MarketCatalogue;
import com.betfair.aping.entities.MarketFilter;
import com.betfair.aping.entities.TimeRange;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import com.betfair.aping.exceptions.APINGException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import speedyBet.service.ui.Frame;

public class APIController {

    static DefaultTableModel table_model;
    public static Object DATAHASID[][];
    private static ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();

    ////////////////////////////////////////////////
    public static Object[][] GetFootballData(String applicationKey, String sessionToken) {

        Object DataFootBAll[][] = null;
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 22);
        Date to = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        Date today2 = calendar2.getTime();

        calendar2.add(Calendar.DAY_OF_YEAR, -2);
        Date from = calendar2.getTime();

        TimeRange tr = new TimeRange();
        tr.setFrom(from);
        tr.setTo(to);
        Set<MarketProjection> marketProjections = new HashSet<MarketProjection>();
        marketProjections.add(MarketProjection.EVENT);

        Set<String> competitionIds = new HashSet<String>();
        competitionIds.add("31");
// what is all marketTypeCodes  For betfair football matchs
        Set<String> marketTypeCodes = new HashSet<String>();
        marketTypeCodes.add("MATCH_ODDS");
        marketTypeCodes.add("HALF_TIME_SCORE");
        marketTypeCodes.add("OVER_UNDER_25");
        marketTypeCodes.add("OVER_UNDER_55");
        marketTypeCodes.add("OVER_UNDER_65");
        marketTypeCodes.add("OVER_UNDER_45");
        marketTypeCodes.add("OVER_UNDER_35");
//        marketTypeCodes.add("FIRST_HALF_GOAL");
//        marketTypeCodes.add("CORRECT_SCORE");

        MarketFilter marketFilter = new MarketFilter();
        marketFilter.setCompetitionIds(competitionIds);
        marketFilter.setMarketTypeCodes(marketTypeCodes);

//      
        marketFilter.setMarketStartTime(tr);

        ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
        try {
            List<MarketCatalogue> marketCatalogueResult = jsonOperations.listMarketCatalogue(marketFilter,
                    marketProjections,
                    MarketSort.MAXIMUM_AVAILABLE,
                    "44",
                    applicationKey,
                    sessionToken);
            DataFootBAll = new Object[marketCatalogueResult.size()][5];
            for (int i = 0; i < marketCatalogueResult.size(); i++) {
                System.out.println(i + " Event :- " + marketCatalogueResult.get(i).getEvent() + "      ----  NAME -" + marketCatalogueResult.get(i).getMarketName());
                DataFootBAll[i][0] = marketCatalogueResult.get(i).getEvent().getName();
                DataFootBAll[i][1] = marketCatalogueResult.get(i).getEvent().getOpenDate();
                DataFootBAll[i][2] = marketCatalogueResult.get(i).getMarketName();
                DataFootBAll[i][3] = marketCatalogueResult.get(i).getEvent().getId();

            }
            DATAHASID = DataFootBAll;

        } catch (APINGException ex) {
            Logger.getLogger(ApiNGJsonRpcDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DataFootBAll;
    }

    public static Object[][] GetFootballData_TypeMarket(String applicationKey, String sessionToken, String FootballMarketType) {

        Object DataFootBAll[][] = null;
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 22);
        Date to = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        Date today2 = calendar2.getTime();

        calendar2.add(Calendar.DAY_OF_YEAR, -2);
        Date from = calendar2.getTime();

        TimeRange tr = new TimeRange();
        tr.setFrom(from);
        tr.setTo(to);
        Set<MarketProjection> marketProjections = new HashSet<MarketProjection>();
        marketProjections.add(MarketProjection.EVENT);

        Set<String> competitionIds = new HashSet<String>();
        competitionIds.add("31");

        Set<String> marketTypeCodes = new HashSet<String>();
        marketTypeCodes.add(FootballMarketType);

        MarketFilter marketFilter = new MarketFilter();
        marketFilter.setCompetitionIds(competitionIds);
        marketFilter.setMarketTypeCodes(marketTypeCodes);

//      
        marketFilter.setMarketStartTime(tr);

        ApiNgOperations jsonOperations = ApiNgJsonRpcOperations.getInstance();
        try {
            List<MarketCatalogue> marketCatalogueResult = jsonOperations.listMarketCatalogue(marketFilter,
                    marketProjections,
                    MarketSort.MAXIMUM_AVAILABLE,
                    "44",
                    applicationKey,
                    sessionToken);
            DataFootBAll = new Object[marketCatalogueResult.size()][5];
            for (int i = 0; i < marketCatalogueResult.size(); i++) {
                System.out.println(i + " Event :- " + marketCatalogueResult.get(i).getEvent() + "      ----  NAME -" + marketCatalogueResult.get(i).getMarketName());

                DataFootBAll[i][0] = marketCatalogueResult.get(i).getEvent().getName();
                DataFootBAll[i][1] = marketCatalogueResult.get(i).getEvent().getOpenDate();
                DataFootBAll[i][2] = marketCatalogueResult.get(i).getMarketName();
                DataFootBAll[i][3] = marketCatalogueResult.get(i).getEvent().getId();
            }
            DATAHASID = DataFootBAll;
        } catch (APINGException ex) {
            Logger.getLogger(ApiNGJsonRpcDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DataFootBAll;
    }

    // method to get all horse racing and set data into arrat 2D 
    public static Object[][] getAllHorseRacing(String applicationKey, String sessionToken) throws APINGException {

        Object DataFootBAll[][] = null;
        System.out.println("______ _______  ______ ______  ______ ______  _____ ______ ______");
        System.out.println(" Get next horse racing market in the UK...\n");

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 22);
        Date to = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        Date today2 = calendar2.getTime();

        calendar2.add(Calendar.DAY_OF_YEAR, -2);
        Date from = calendar2.getTime();

        TimeRange tr = new TimeRange();
        tr.setFrom(from);
        tr.setTo(to);

        Set<String> countries = new HashSet<String>();
        countries.add("GB");

        Set<String> idsForEvents = new HashSet<String>();

        Set<String> typesCode = new HashSet<String>();
        typesCode.add("WIN");
        MarketFilter marketFilter = new MarketFilter();

//        idsForEvents.add("7");
//        idsForEvents.add("1");
//        idsForEvents.add("2");
//        idsForEvents.add("3");
//        idsForEvents.add("4");
//        idsForEvents.add("6");
        marketFilter.setEventTypeIds(idsForEvents);
        marketFilter.setMarketStartTime(tr);
        marketFilter.setMarketCountries(countries);
        marketFilter.setMarketTypeCodes(typesCode);

        Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
        marketProjection.add(MarketProjection.COMPETITION);
        marketProjection.add(MarketProjection.EVENT);
        marketProjection.add(MarketProjection.EVENT_TYPE);
        marketProjection.add(MarketProjection.MARKET_DESCRIPTION);
        marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

        String maxResult = "44";

        List<MarketCatalogue> marketCatalogueResult = jsonOperations.listMarketCatalogue(marketFilter, marketProjection, MarketSort.FIRST_TO_START, maxResult,
                applicationKey, sessionToken);
        DataFootBAll = new Object[marketCatalogueResult.size()][5];
        System.out.println(marketCatalogueResult.size() + "                     00000000000000000000000000");
//        ArrayList<String> ssss = new ArrayList<String>();
        for (int i = 0; i < marketCatalogueResult.size(); i++) {
            System.out.println("- - - - - - - " + marketCatalogueResult.get(i).getEvent().getName() + "---------- " + marketCatalogueResult.get(i).getEventType().getName());
            DataFootBAll[i][0] = marketCatalogueResult.get(i).getEvent().getName();
            DataFootBAll[i][1] = marketCatalogueResult.get(i).getEvent().getOpenDate();
            DataFootBAll[i][2] = marketCatalogueResult.get(i).getMarketName();
            DataFootBAll[i][3] = marketCatalogueResult.get(i).getEvent().getId();

//            if (!ssss.contains(marketCatalogueResult.get(i).getMarketName())) {
//                ssss.add(marketCatalogueResult.get(i).getMarketName());
//            }
        }
        DATAHASID = DataFootBAll;
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//        System.out.println(ssss);
        return DataFootBAll;
    }

    // method to get all horse racing and set data into arrat 2D 
    public static Object[][] getAllHorseRacingSingleMarketType(String applicationKey, String sessionToken, String MarketTypee) throws APINGException {

        Object DataFootBAll[][] = null;
        System.out.println("______ _______  ______ ______  ______ ______  _____ ______ ______");
        System.out.println(" Get next horse racing market in the UK...\n");

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 22);
        Date to = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        Date today2 = calendar2.getTime();

        calendar2.add(Calendar.DAY_OF_YEAR, -1);
        Date from = calendar2.getTime();

        TimeRange tr = new TimeRange();
        tr.setFrom(from);
        tr.setTo(to);

        Set<String> countries = new HashSet<String>();
        countries.add("GB");

        Set<String> idsForEvents = new HashSet<String>();

        Set<String> typesCode = new HashSet<String>();
        typesCode.add("WIN");
        MarketFilter marketFilter = new MarketFilter();

//  
        marketFilter.setEventTypeIds(idsForEvents);
        marketFilter.setMarketStartTime(tr);
        marketFilter.setMarketCountries(countries);
        marketFilter.setMarketTypeCodes(typesCode);

        Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
        marketProjection.add(MarketProjection.COMPETITION);
        marketProjection.add(MarketProjection.EVENT);
        marketProjection.add(MarketProjection.EVENT_TYPE);
        marketProjection.add(MarketProjection.MARKET_DESCRIPTION);
        marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

        String maxResult = "44";

        List<MarketCatalogue> marketCatalogueResult = jsonOperations.listMarketCatalogue(marketFilter, marketProjection, MarketSort.FIRST_TO_START, maxResult,
                applicationKey, sessionToken);
        DataFootBAll = new Object[marketCatalogueResult.size()][5];
        System.out.println(marketCatalogueResult.size() + "                   00000000000000000000000000");
//        ArrayList<String> ssss = new ArrayList<String>();
        int xx = 0;
        for (int i = 0; i < marketCatalogueResult.size(); i++) {
            System.out.println("- - - - - - - " + marketCatalogueResult.get(i).getMarketName() + "---------- " + marketCatalogueResult.get(i).getEventType().getName());

            if (marketCatalogueResult.get(i).getMarketName().contains(MarketTypee)) {
                System.out.println(marketCatalogueResult.get(i).getEvent().getName());
                DataFootBAll[xx][0] = marketCatalogueResult.get(i).getEvent().getName();
                DataFootBAll[xx][1] = marketCatalogueResult.get(i).getEvent().getOpenDate();
                DataFootBAll[xx][2] = marketCatalogueResult.get(i).getMarketName();
                DataFootBAll[xx][3] = marketCatalogueResult.get(i).getEvent().getId();
                xx++;
            }
//            if (!ssss.contains(marketCatalogueResult.get(i).getMarketName())) {
//                ssss.add(marketCatalogueResult.get(i).getMarketName());
//            }
        }
        DATAHASID = DataFootBAll;

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//        System.out.println(ssss);
        return DataFootBAll;
    }

    /////////////////////////////////////
    // adding data to Jtable 
    public static void fullTableDomainBySearch(JTable table, String name_domain, long offset) throws ClassNotFoundException {
        Frame.MarketWait.setText("Pleas Wait ...");
        BufferedReader br = null;
        String everything = "";
        String line = "";
        try {

            br = new BufferedReader(new FileReader("session.txt"));
            StringBuilder sb = new StringBuilder();
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
//            everything.replace("\n", "").replace("\r", "");
            everything = everything.substring(0, 44);
        } catch (Exception ex) {
            System.out.println("ex");
        }
        System.out.println(everything + "-----------------------");

        String columns_name[] = {"Name", "Open Date", "Market Type"};
//        String columns_name[] = {"Name", "Open Date", "Market Type", "ID"};

        table_model = new DefaultTableModel(GetFootballData("7dOwsjQhaw7lSoES", everything), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Frame.MarketWait.setText(" ");

    }

    // adding data to Jtable 
    public static void fullTableDomainBySearch_SingleTypeOfFootballMakret(JTable table, String name_domain, long offset, String FootballMarketType) throws ClassNotFoundException {
        Frame.MarketWait.setText("Pleas Wait ...");
        BufferedReader br = null;
        String everything = "";
        String line = "";
        try {

            br = new BufferedReader(new FileReader("session.txt"));
            StringBuilder sb = new StringBuilder();
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
//            everything.replace("\n", "").replace("\r", "");
            everything = everything.substring(0, 44);
        } catch (Exception ex) {
            System.out.println("ex");
        }
        System.out.println(everything + "-----------------------");

//        String columns_name[] = {"Name", "Open Date", "Market Type", "ID"};
        String columns_name[] = {"Name", "Open Date", "Market Type"};

        table_model = new DefaultTableModel(GetFootballData_TypeMarket("7dOwsjQhaw7lSoES", everything, FootballMarketType), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Frame.MarketWait.setText(" ");

    }

    public static void fullTableDomainBySearch2(JTable table, String name_domain, long offset) throws ClassNotFoundException, APINGException {
        Frame.MarketWait.setText("Pleas Wait ...");
        BufferedReader br = null;
        String everything = "";
        String line = "";
        try {

            br = new BufferedReader(new FileReader("session.txt"));
            StringBuilder sb = new StringBuilder();
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
//            everything.replace("\n", "").replace("\r", "");
            everything = everything.substring(0, 44);
        } catch (Exception ex) {
            System.out.println("ex");
        }
        System.out.println(everything + "-----------------------");
//        if (everything.equals("q+n2FovGQiTC0hKcbdFuH1pjqDVqxfZD7y0p+klSJYc=")) {
//            JOptionPane.showMessageDialog(null, "Valid Sesiion ");
//        } else {
//            JOptionPane.showMessageDialog(null, "Not vaild  Sesiion The Size Is " + everything.length());
//
//        }
//        String columns_name[] = {"Name", "Open Date", "Market Type", "ID"};
        String columns_name[] = {"Name", "Open Date", "Market Type"};

        table_model = new DefaultTableModel(getAllHorseRacing("7dOwsjQhaw7lSoES", everything), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Frame.MarketWait.setText(" ");

    }

    public static void fullTableDomainBySearch2horseRacing(JTable table, String name_domain, long offset, String marketType) throws ClassNotFoundException, APINGException {
        Frame.MarketWait.setText("Pleas Wait ...");
        BufferedReader br = null;
        String everything = "";
        String line = "";
        try {

            br = new BufferedReader(new FileReader("session.txt"));
            StringBuilder sb = new StringBuilder();
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
//            everything.replace("\n", "").replace("\r", "");
            everything = everything.substring(0, 44);
        } catch (Exception ex) {
            System.out.println("ex");
        }
        System.out.println(everything + "-----------------------");
//        if (everything.equals("q+n2FovGQiTC0hKcbdFuH1pjqDVsZD7y0p+klSJYc=")) {
//            JOptionPane.showMessageDialog(null, "Valid Sesiio
//        } else {
//            JOptionPane.showMessageDialog(null, "Not vaild  Sesiion The Size Is " + everything.length());
//
//        }
//        String columns_name[] = {"Name", "Open Date", "Market Type", "ID"};
        String columns_name[] = {"Name", "Open Date", "Market Type"};
        table_model = new DefaultTableModel(getAllHorseRacingSingleMarketType("7dOwsjQhaw7lSoES", everything, marketType), columns_name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table.setModel(table_model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Frame.MarketWait.setText(" ");

    }

    public static String getID(int x) {
//        for (int i = 0; i < DATAHASID.length; i++) {
//            
//        }
        return DATAHASID[x][3] + "";
    }
}

//    public static Object[][] getDate(String colums, String name_table, String condition, int size, long offset) throws ClassNotFoundException {
//        Object data[][] = null;
//        try {
//            PreparedStatement stat = new Conn().getconnection().prepareStatement("select " + colums + " from " + name_table + " where " + condition + " LIMIT " + offset + ", 1000");
//            rs = stat.executeQuery();
//            rs.last();
//            int d = rs.getRow();
//            rs.beforeFirst();
//            data = new Object[d][size];
//            int aa = 0;
//            while (rs.next()) {
//                for (int i = 0; i < size; i++) {
//                    String s = rs.getObject(i + 1).toString();
//                    data[aa][i] = s;
//                }
//                aa++;
//            }
//            stat.close();
//            rs.close();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return data;
//     }
//}
