package io.flutter.plugins.webviewflutter;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class Util {

    static final String LOG_TAG = "Util";
    public static final String ANDROID_ASSET_URL = "file:///android_asset/";

    private Util() {}

    public static String getUrlAsset(String assetFilePath) throws IOException {
        String key = (Shared.registrar != null) ? Shared.registrar.lookupKeyForAsset(assetFilePath) : Shared.flutterAssets.getAssetFilePathByName(assetFilePath);
        InputStream is = null;
        IOException e = null;

        try {
            is = getFileAsset(assetFilePath);
        } catch (IOException ex) {
            e = ex;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    e = ex;
                }
            }
        }
        if (e != null) {
            throw e;
        }

        return ANDROID_ASSET_URL + key;
    }

    public static InputStream getFileAsset(String assetFilePath) throws IOException {
        String key = (Shared.registrar != null) ? Shared.registrar.lookupKeyForAsset(assetFilePath) : Shared.flutterAssets.getAssetFilePathByName(assetFilePath);
        AssetManager mg = Shared.applicationContext.getResources().getAssets();
        return mg.open(key);
    }
}
