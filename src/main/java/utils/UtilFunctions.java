package utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static java.lang.System.out;

/**
 * Created by anand on 2/20/2018.
 */
public class UtilFunctions {

    /**
     * Timestamp of execution
     */
    public static String DATETIME = "";
    public static String RESULTPHAT = "";

    public static String getDATETIME() {
        return DATETIME;
    }

    public static void setDATETIME(String aDateTime) {
        DATETIME = aDateTime;
    }

    public static String getRESULTPHAT() {
        return RESULTPHAT;
    }

    public static void setRESULTPHAT(String aPath) {

        RESULTPHAT = "/results/" + aPath;

    }



    public static void CreateResultLog(File strFile, String strTestName, Boolean ExecutionStatus, String ResultPath) {
        try {
            File file = strFile;
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("TestCaseName_" + strTestName);
            writer.println("ExecutionStatus_" + ExecutionStatus);
            writer.println("ResultFile_" + ResultPath);
            writer.close();
        } catch (Exception e) {
            out.println(e);
        }
    }

    public static void CreateDir(String strPath) {
        Path path = Paths.get(strPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }


    public static void CreateClearDir(String strPath) {
        try {
            Path path = Paths.get(strPath);
            if (Files.exists(path)) {
                FileUtils.forceDelete(new File(path.toString()));
                Files.createDirectories(path);
            } else {
                Files.createDirectories(path);
            }
        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }


    public static void OverWriteToDir(String SourcePath, String DestPath) {
        try {
            File SourceFilePath = new File(SourcePath);
            File DestFilePath = new File(DestPath);
            FileUtils.copyDirectory(SourceFilePath, DestFilePath, false);

        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }

    public static void ReNameFile(String SourceFileName, String DestFileName) {
        try {
            File SourceFile = new File(SourceFileName);
            File DestFile = new File(DestFileName);
            if (DestFile.exists()) DestFile.delete();
            SourceFile.renameTo(DestFile);

        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }

    public static void MoveToFile(String SourcePath, String DestPath) {
        try {
            File SourceFilePath = new File(SourcePath);
            String name = SourceFilePath.getName();
            File DestFilePath = new File(DestPath + "\\" + name);
            FileUtils.copyFile(SourceFilePath, DestFilePath, true);

        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }

    public static void FileMoveToDir(String SourcePath, String DestPath) {
        try {
            File SourceFilePath = new File(SourcePath);
            File DestFilePath = new File(DestPath);
            FileUtils.copyDirectory(SourceFilePath, DestFilePath, true);

        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }

    public static void FileReName(String SourcePath, String NameOfTest) {
        try {
            File SourceFilePath = new File(SourcePath + "\\index.html");
            File DestPath = new File(SourcePath + "\\" + NameOfTest + ".html");
            SourceFilePath.renameTo(DestPath);

            File SourceImage = new File(SourcePath + "\\images");
            File DestImg = new File(SourcePath + "\\" + NameOfTest);
            SourceImage.renameTo(DestImg);

        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }

    public static void UpdateFile(String FileName, String imageFolderName) {
        try {
            File file = new File(FileName + "\\" + imageFolderName + ".html");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();
            String newtext = oldtext.replaceAll("images", imageFolderName);
            FileWriter writer = new FileWriter(FileName + "\\" + imageFolderName + ".html");
            writer.write(newtext);
            writer.close();


        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }

    public static void UpdateWEBFile(String FileName, String imageFolderName) {
        try {
            File file = new File(FileName + "\\" + imageFolderName + ".html");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line;//+ "\r\n";
            }
            reader.close();

            String a = "file:///" + FileName + "\\";
            String newtext = oldtext.replace(a, "");
            FileWriter writer = new FileWriter(FileName + "\\" + imageFolderName + ".html");
            writer.write(newtext);
            writer.close();
        } catch (Exception e) {
            //fail to create directory
            e.printStackTrace();
        }
    }


    public static String getEmailServer() throws IOException {
        return getProperties("EMAIL.properties").getProperty("EmailServer");
    }


    public static String getEmailTo() throws IOException {
        return getProperties("EMAIL.properties").getProperty("EmailTo");
    }


    public static String getEmailCC() throws IOException {
        return getProperties("EMAIL.properties").getProperty("EmailCC");
    }


    public static String getEmailBCC() throws IOException {
        return getProperties("EMAIL.properties").getProperty("EmailBCC");
    }


    public static String getEmailFrom() throws IOException {
        return getProperties("EMAIL.properties").getProperty("EmailFrom");
    }


    private static Properties getProperties(String aFile) throws IOException {
        Properties myProp = new Properties();
        FileInputStream fp = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\" + aFile);
        myProp.load(fp);
        return myProp;
    }


    /**
     * @param timeInMilis Time in ms for convert to String
     * @return Time as String in Form mm:ss.SSS
     */
    public static String getFormatedTimeResult(long timeInMilis) {

        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        Time time = new Time(timeInMilis);
        String result = formatter.format(time);
        return result;
    }




}
