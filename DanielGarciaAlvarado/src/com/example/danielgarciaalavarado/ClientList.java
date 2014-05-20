package com.example.danielgarciaalavarado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devspark.appmsg.AppMsg;
import com.example.danigarciaalva.adapters.ClientsAdapter;
import com.example.danigarciaalva.models.Client;
import com.example.danigarciaalva.models.ClientService;
import com.example.danigarciaalva.utils.Connection;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ClientList extends Activity implements OnItemClickListener{

	public static final String ID_EMPRESA = "idempresa";
	private String mIDEmpresa;
	private ListView mListClients;
	private RequestQueue mRequestQueue;
	private ArrayList<Client> mClientes;
	private Activity mContext;
	private ImageButton mActionRefresh;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clients_list);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.actionbar_clients_layout);
		initVariables();
		if(Connection.isConnected(this))
			requestClients();
		else
			AppMsg.makeText(this, getString(R.string.no_internet),  AppMsg.STYLE_ALERT).show();
		
	}
	
	@SuppressLint("NewApi")
	public void initVariables(){
		mListClients = (ListView)findViewById(R.id.act_client_list);
		mListClients.setOnItemClickListener(this);
		mRequestQueue = Volley.newRequestQueue(this);
		mClientes = new ArrayList<Client>();
		mContext = this;
		mActionRefresh = (ImageButton)getActionBar().getCustomView().findViewById(R.id.actionbar_button_refresh);
		mActionRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(Connection.isConnected(mContext))
					requestClients();
				else
					AppMsg.makeText(mContext, mContext.getString(R.string.no_internet), AppMsg.STYLE_ALERT).show();
			}
		});
		mIDEmpresa = getIntent().getExtras().getString(ID_EMPRESA);
	}
	
	public void requestClients(){
		String url = getString(R.string.webservice_clients);
		StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
			public void onResponse(String response) {
				Gson json = new Gson();
				mClientes = json.fromJson(response, ClientService.class).getDatos();
				mListClients.setAdapter(new ClientsAdapter(mContext, R.layout.item_list_client, mClientes));
			};
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				AppMsg.makeText(mContext, mContext.getString(R.string.server_error), AppMsg.STYLE_ALERT).show();
			}
		}){ @Override
		protected Map<String, String> getParams() throws AuthFailureError {
			Map<String, String> params = new HashMap<String, String>();
			params.put(ID_EMPRESA, mIDEmpresa);
			return params;
		}};
		mRequestQueue.add(rq);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		AlertDialog dialog = new AlertDialog.Builder(this)
				.setMessage(mClientes.get(position).getNameVendedor())
				.setCancelable(true)
				.setTitle("Vendedor").create();
		dialog.show();
	}
	
}
