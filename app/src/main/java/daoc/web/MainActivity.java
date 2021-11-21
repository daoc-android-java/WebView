package daoc.web;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import androidx.core.app.ActivityCompat;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText url;
	private WebView web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pidePermiso();

		url = (EditText) findViewById(R.id.url);
		web = (WebView) findViewById(R.id.web);
		
		//permite que los nuevos links se carguen directamente en el Webview
		web.setWebViewClient(new WebViewClient());
		
		//activa la ejecución de JavaScript
		web.getSettings().setJavaScriptEnabled(true);
		
		//establece una conexión para que JavaScript llame a metodos en Android
		web.addJavascriptInterface(new JStoAndroidBind(this), "Android");
		
		web.loadUrl("https://www.android.com");
	}
	
	public void irAUrl(View view) {	
		web.loadUrl(url.getText().toString());
	}

	public void irAJuego(View view) {	
		Button b = (Button) view;
		web.loadUrl("file:///android_asset/index.html");
	}
	
	public void execJs(View view) {	
		web.loadUrl("javascript:desdeAndroid('Cambiado desde Android')");
	}	
	
	public void goBack(View view) {	
		web.goBack();
	}
	
	public void reload(View view) {	
		web.reload();
	}
	
	public void goForward(View view) {	
		web.goForward();
	}


	private void pidePermiso() {
		int permissionCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
		if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
			//OK. Ya tiene el permiso
		} else if(permissionCheck == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},1);
			//Dependerá... si el usuario da o no el permiso
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if(requestCode == 1) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Toast.makeText(this, "Permiso concedido!!!", Toast.LENGTH_LONG).show();
				//OK. El usuario SÍ dio el permiso
			} else {
				Toast.makeText(this, "Permiso negado XXX", Toast.LENGTH_LONG).show();
				//NOK. El usuario NO dio el permiso
			}
		} else {
			Toast.makeText(this, "Pedido desconocido", Toast.LENGTH_LONG).show();
		}
	}
}
