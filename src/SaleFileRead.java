/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class SaleFileRead extends TxtFileRead {
    private List<String[]> data;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    
    public SaleFileRead(String filePath){
        super(filePath);
        data = new ArrayList<>();
    }
    
    public List<String[]> getData() {
        return data;
    }
    
    @Override
    public void processLine(String[] fileLine){
        data.add(fileLine);
    }
    
    private Date parseDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String listYear(String[] fileLine){
        String year = null;
        try {
            Date bookingDate = dateFormat.parse(fileLine[3]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(bookingDate);
            year = String.valueOf(cal.get(Calendar.YEAR));
            
        } catch (ParseException ex) {
            Logger.getLogger(SaleFileRead.class.getName()).log(Level.SEVERE, null, ex);
        }
        return year;
    }
    
    public int getMonthNumber(String monthName) {
        try {
            Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monthName);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.MONTH) + 1; 
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String[]> getSalesByYear(int year) throws NumberFormatException {
        List<String[]> yearlySales = new ArrayList<>();
        for (String[] sale : data) {
            try {
                Date saleDate = dateFormat.parse(sale[3]);
                Calendar cal = Calendar.getInstance();
                cal.setTime(saleDate);

                if (cal.get(Calendar.YEAR) == year) {
                    String[] selectedData = {sale[0], sale[2], sale[3], sale[8]};
                    yearlySales.add(selectedData);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return yearlySales;
    }
    
    public List<String[]> getSalesByYearAndMonth(int year, int month) throws NumberFormatException {
        List<String[]> yearmonthlySales = new ArrayList<>();
        for (String[] sale : data) {
            try {
                Date saleDate = dateFormat.parse(sale[3]);  
                Calendar cal = Calendar.getInstance();
                cal.setTime(saleDate);

                if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) + 1 == month) {
                    String[] selectedData = {sale[0], sale[2], sale[3], sale[8]};
                    yearmonthlySales.add(selectedData);  
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return yearmonthlySales;
    }
        
    public List<String[]> getSalesByYearMonthWeek(int year, int month, int week) throws NumberFormatException {
        List<String[]> weeklySales = new ArrayList<>();
        for (String[] sale : data) {
            try {
                Date saleDate = dateFormat.parse(sale[3]);  // Parse the date (4th column in the data)
                Calendar cal = Calendar.getInstance();
                cal.setTime(saleDate);

                int saleYear = cal.get(Calendar.YEAR);
                int saleMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-based
                int saleDay = cal.get(Calendar.DAY_OF_MONTH);

                if (saleYear == year && saleMonth == month) {
                    int weekOfMonth = getCustomWeek(saleDay);
                    if (weekOfMonth == week) {
                        String[] selectedData = {sale[0], sale[2], sale[3], sale[8]};
                        weeklySales.add(selectedData);  // Add the sale if it matches the week
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return weeklySales;
    }
    
    private int getCustomWeek(int dayOfMonth) {
        if (dayOfMonth >= 1 && dayOfMonth <= 8) {
            return 1;  
        } else if (dayOfMonth >= 9 && dayOfMonth <= 16) {
            return 2;  
        } else if (dayOfMonth >= 17 && dayOfMonth <= 24) {
            return 3;  
        } else if (dayOfMonth >= 25 && dayOfMonth <= 31) {
            return 4;  
        } else {
            return -1; 
        }
    }
    
    public List<String[]> getAllSalesData() {
        return data;
    }
}

    