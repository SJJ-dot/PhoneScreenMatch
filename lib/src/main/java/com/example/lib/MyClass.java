package com.example.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class MyClass {
    private static final String[] defaultDPArr = new String[]{"384", "392", "400", "410", "411", "480", "533", "592", "600", "640", "662","712", "720", "768","780", "800", "811", "820", "960", "961", "1024", "1280", "1365"};
    public static void main(String[] args) throws IOException {

        //设计px宽度
        String pathBase = "D:\\androidWork\\PhoneScreenMatch\\res";

        createFile(pathBase + "\\" + "values\\dimens_px_dp.xml", 360);

        for (String s : defaultDPArr) {
            String path = pathBase + "\\" + "values-w"+s+"dp"+"\\dimens_px_dp.xml";
            createFile(path, Integer.parseInt(s));
        }


    }

    private static void  createFile(String path,int  dpWidth) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("\n");
        sb.append("<resources>");
        sb.append("\n");
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = -100; i <= 2560; i++) {
            double px = pxToDp(i, 2560, dpWidth);
            String dp = df.format(px);
            sb.append("\t<dimen name=\"px_").append(Math.abs(i)).append(i>=0?"":"_").append("\">").append(dp).append("dp</dimen>");
            sb.append("\n");
        }
        for (int i = -25; i <= 1024; i++) {
            double px = pxToDp(i, 1024, dpWidth);
            String dp = df.format(px);
            sb.append("\t<dimen name=\"dp_").append(Math.abs(i)).append(i>=0?"":"_").append("\">").append(dp).append("dp</dimen>");
            sb.append("\n");
        }

        sb.append("</resources>");
        sb.append("\n");
        System.out.println(sb);
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        pw.print(sb.toString());
        pw.close();
    }

    public static double pxToDp(int px,int designSize,int actualSize) {
        return (double) actualSize * px / designSize;
    }
}
