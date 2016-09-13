package com.golducks.sniffleradmin;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DELL on 9/9/2016.
 */
public class Http {

    DownloadListener downloadListener;
    Context context;

    public Http(Context context, DownloadListener downloadListener) {
        this.context = context;
        this.downloadListener = downloadListener;
    }

    public void requestData(final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder sb = new StringBuilder();
                String line = "";
                try {
                    URL url = new URL("http://goldducks.com/sniffler/whatsapp_download.php?account=" + account);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader in = new BufferedReader(inputStreamReader);
                    if (in != null) {
                        while ((line = in.readLine()) != null)
                            sb.append(line);
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            downloadListener.onDownloaded(sb.toString());
                        }
                    });
                } catch (final Exception e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            downloadListener.onDownloaded(e.toString());
                        }
                    });

                }

            }
        }).start();
    }
}
