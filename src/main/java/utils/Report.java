package utils;

import com.relevantcodes.extentreports.ExtentReports;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Report {
    public static String imgPath;
    public static String strPath;
    public static String testCase;

    private static String projectBaseDirectory = System.getProperty("user.dir");

    public static ExtentReports Instance(String TestCase) {

        Extent dir = new Extent(TestCase);
        strPath = dir.getPath()+TestCase+"\\";
        try {
            File oFilePathTillResults = new File(strPath);
            if (!oFilePathTillResults.exists()) {
                oFilePathTillResults.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        testCase = TestCase;
        ExtentReports extent;
        String Path = strPath + TestCase + ".html";
        imgPath = Path.substring(0, Path.length() - 5);
        System.out.println(Path);

        extent = new ExtentReports(Path, false);

        extent
                .addSystemInfo("Project Name", "Digital Communication")
                .addSystemInfo("Environment", "Testing");

        return extent;
    }


    public static String CaptureScreen(WebDriver driver) {
        SimpleDateFormat sd = new SimpleDateFormat("ddMMyyHHmmssSSS");
        Date now = new Date();
        String strName = sd.format(now);

      /*  File file = new File(imgPath);
        if (!file.exists()) {
            file.mkdir();
        }*/
        String strScrPath = strPath + "/" + strName;
        org.openqa.selenium.TakesScreenshot oScn = (org.openqa.selenium.TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(strScrPath + ".jpg");
        try {
            FileUtils.copyFile(oScnShot, oDest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return strName + ".jpg";
       // return strScrPath + ".jpg";
    }

}


