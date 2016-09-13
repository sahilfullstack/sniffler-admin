package com.golducks.sniffleradmin;

/**
 * Created by DELL on 9/9/2016.
 */
public interface DownloadListener {
    public void onDownloaded(String data);

    public void onError(String data);

}
