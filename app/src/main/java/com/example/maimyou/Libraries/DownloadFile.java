package com.example.maimyou.Libraries;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class DownloadFile extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
        String fileName = strings[1];  // -> maven.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "testthreepdf");
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try{
            pdfFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        FileDownloader.downloadFile(fileUrl, pdfFile);
        return null;
    }
}

