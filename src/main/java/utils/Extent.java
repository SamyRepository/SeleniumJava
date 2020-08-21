package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Extent {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd-hh.mm.ss.SSS";
    public static int month;
    public static int day;
    public static int year;
    public static String[] monthName = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October",
            "November", "December"};
    static String strAbsolutepath = new File("").getAbsolutePath();
    private String TestCase;

    public Extent(String TestCase) {
        this.TestCase = TestCase;
    }

    public static String now() {
        //Calendar cal = Calendar.getInstance();
        //month = cal.get(Calendar.MONTH) + 1;
        //day = cal.get(Calendar.DAY_OF_MONTH);
        //year = cal.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        Random rand = new Random();
        int num = rand.nextInt(1000);
        Date dt = new Date();
        return sdf.format(dt) + num;
    }

    public String getPath() {

        String strPath = null;
        String sPathTillMonth;
        String sPathTillDate;
        String sPathTillUserName;
        String sPathTillResults;
        String strProjectLocation = System.getProperty("user.dir");

        Calendar cal = Calendar.getInstance();
        int iMonth = cal.get(Calendar.MONTH);
        String sMonthName = monthName[iMonth];
        String userName = System.getProperty("user.name");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String sDate = sdf.format(cal.getTime()).replace(".", "_");
        sPathTillResults = strAbsolutepath + "\\Results";
        sPathTillUserName = strAbsolutepath + "/Results/" + userName;
        sPathTillMonth = sPathTillUserName + "/" + sMonthName;
        sPathTillDate = sPathTillMonth + "/" + sDate;

        try {
            File oFilePathTillResults = new File(sPathTillResults);
            if (!oFilePathTillResults.exists()) {
                oFilePathTillResults.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sPathTillResults + "\\";
    }
}

