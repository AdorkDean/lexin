//    Writefile.java

package com.caipiao.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writefile {

    public Writefile() {
    }

    public void wt(String url, String count) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(url));
            bw.write(count);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args1[])
            throws IOException {
    }
}
