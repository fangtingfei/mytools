package cn.ftf.mytools.service;

import java.io.*;

public class Work_2_163music {
    public static void main(String[] args) throws Exception {
        String fileName="C:\\Users\\user\\Documents\\七月上 - Jam.lrc";
        FileReader read=new FileReader(fileName);
        FileWriter write=new FileWriter(fileName.split("-")[0]+".mytooltxt");
        BufferedReader bufferedReader=new BufferedReader(read);
        BufferedWriter bufferedWriter=new BufferedWriter(write);
        String str;
        while((str=bufferedReader.readLine())!=null){
            String str1="\""+str+"\\n\" +";
            bufferedWriter.newLine();
            bufferedWriter.write(str1);
        }
        bufferedReader.close();
        bufferedWriter.close();
        read.close();
        write.close();
    }

}
