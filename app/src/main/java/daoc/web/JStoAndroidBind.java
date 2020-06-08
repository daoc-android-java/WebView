package daoc.web;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JStoAndroidBind {
    private final Context context;

    JStoAndroidBind(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
    
    @JavascriptInterface
    public String callActivity() {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
        return "Desde Android";
    }
}
