package com.example.danielgarciaalavarado;

/* Aplicaci�n de prueba - Daniel Garc�a Alvarado
 * 
 * 19 de Mayo de 2014
 * Xalapa de Enr�quez, Veracruz
 * 
 */

import org.json.JSONObject;

import com.andreabaccega.widget.FormEditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.devspark.appmsg.AppMsg;
import com.example.danigarciaalva.utils.Connection;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private RequestQueue mRequestQueue;
	private String tag = this.getClass().getSimpleName();
	private FormEditText mUsername, mPassword;
	private FormEditText[] mFields;
	private Button mLogin, mForgot;
	private Activity mContext;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* Para dar un estilo personalizado al ActionBar*/
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.actionbar_login_layout);
		initVariables();
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.act_log_button_login){
			/* Se valida que los campos cumplan con las reglas establecidas en el XML y lanza la siguiente Actividad si todo sale bien*/
			boolean allValid = true;
	        for (FormEditText field: mFields) {
	            allValid = field.testValidity() && allValid;
	        }
	        if (allValid){
	        	// Valida si hay conexi�n antes de hacer la petici�n al servidor
	        	if (Connection.isConnected(this)) 
	        		serverRequest();
	        	else
	        		AppMsg.makeText(mContext, "No hay conexi�n a Internet, por favor verifique", AppMsg.STYLE_ALERT).show();
	        }
		}else if(v.getId() == R.id.act_log_button_forgott){
			AlertDialog.Builder dialog = new AlertDialog.Builder(this).setCancelable(true).setTitle("Contrase�a olvidada").setMessage("Se enviar� un correo con la contrase�a, favor de verificarlo");
			dialog.create().show();
		}
		
	}

	public void initVariables(){
		mUsername = (FormEditText)findViewById(R.id.act_log_username);
		mPassword = (FormEditText)findViewById(R.id.act_log_password);
		mFields = new FormEditText[]{mUsername, mPassword};
		
		/*
		 * Volley framework permite manejar una cola de peticiones de manera que las tareas se ejecuten
		 * en orden. En caso de una aplicaci�n con muchos hilos de red es la mejor opci�n para el manejo
		 * de peticiones. Permite adem�s, peticiones con respuesta directamente en JSON
		 */
		mRequestQueue = Volley.newRequestQueue(this);
		mLogin = (Button)findViewById(R.id.act_log_button_login);
		mForgot = (Button)findViewById(R.id.act_log_button_forgott);
		mLogin.setOnClickListener(this);
		mForgot.setOnClickListener(this);
		mContext = this;
	}
	
	
	private ProgressDialog mProgressDialog;
	
	@SuppressWarnings("static-access")
	public void serverRequest(){
		String url = "http://www.daylinet.com/Evaluacion/json/login";
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("Iniciando sesi�n..");
		mProgressDialog.setProgressStyle(mProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.show();
		
		/*
		 * Solicitamos un servicio de JSON y lo agregamos a la cola de Volley
		 * 
		 */
		JsonObjectRequest rq = new JsonObjectRequest(Request.Method.POST, url , null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Log.d(tag+"OK", response.toString());
				try{
					if(response.getString("codigo").equals("0")){
						Intent i = new Intent(mContext, ClientList.class);
						i.putExtra(ClientList.ID_EMPRESA, response.getJSONObject("datos").getString(ClientList.ID_EMPRESA));
						startActivity(i);
						finish();
					}else{
						AppMsg.makeText(mContext, response.getString("mensaje"), AppMsg.STYLE_ALERT).show();
					}
				}catch(Exception e){e.printStackTrace();}
				mProgressDialog.dismiss();
			} 
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
				mProgressDialog.dismiss();
				AppMsg.makeText(mContext, mContext.getString(R.string.server_error), AppMsg.STYLE_ALERT).show();
			}
		}){
		@Override
			// Mandamos en el cuerpo de la petici�n el usuario y la contrase�a
			public byte[] getBody() {
				byte[] bytes = ("{'username':"+mUsername.getText().toString().trim()+",'password':"+
						mPassword.getText().toString()+"}").getBytes(); 
				return bytes;
			}
		};
		mRequestQueue.add(rq);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		AppMsg.cancelAll();
	}
}

