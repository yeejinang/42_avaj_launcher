package com.avaj_launcher.validator;
import com.avaj_launcher.exception.*;
import com.avaj_launcher.model.*;
import java.io.*;
import java.util.*;

public class CustomFileWriter {

    public CustomFileWriter() {}

    public static void writeIntoFile(String fileName, String msg) {
        FileWriter writer;
        try {
            if (!new File(fileName).exists()) {
                writer = new FileWriter(fileName);
            } else {
                writer = new FileWriter(fileName, true);
            }
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}