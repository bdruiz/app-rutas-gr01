package com.example.apprutasmintic.view.monitor;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class lectorTXT {

    private Context mContext;

    public lectorTXT(Context context) {
        this.mContext = context;
    }

    //indicio de macro
    //public List<String> readLine(String path) {
    public String readfull(String path) {
        List<String> mLines = new ArrayList<>();
        String full = "";
        AssetManager am = mContext.getAssets();

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                full = full + "\n" + line;
                mLines.add(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return full;
    }
}