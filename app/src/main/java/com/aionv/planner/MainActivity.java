package com.aionv.planner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class MainActivity extends Activity {
    private static final int PICK_FILE = 1;
    private ValueCallback<Uri[]> pendingFile;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        WebView view = new WebView(this);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setAllowFileAccess(false);
        view.setWebViewClient(new WebViewClient());
        view.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> callback, FileChooserParams params) {
                if (pendingFile != null) pendingFile.onReceiveValue(null);
                pendingFile = callback;
                Intent picker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                picker.addCategory(Intent.CATEGORY_OPENABLE);
                picker.setType("*/*");
                try {
                    startActivityForResult(picker, PICK_FILE);
                } catch (android.content.ActivityNotFoundException unavailable) {
                    finishPicking(null);
                }
                return true;
            }
        });
        setContentView(view);
        view.loadUrl("file:///android_asset/index.html");
    }

    @Override protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == PICK_FILE) finishPicking(WebChromeClient.FileChooserParams.parseResult(result, data));
    }

    private void finishPicking(Uri[] result) {
        if (pendingFile == null) return;
        pendingFile.onReceiveValue(result);
        pendingFile = null;
    }
}
