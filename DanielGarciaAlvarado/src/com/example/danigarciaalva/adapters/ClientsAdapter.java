package com.example.danigarciaalva.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.danielgarciaalavarado.R;
import com.example.danigarciaalva.models.Client;
/*
 * 
 * Adaptador para la lista de clientes
 * 
 * Se usa una clase estática ViewHolder para el reciclaje de objetos,
 * de esta manera se hace más eficiente la lista aún cuando ésta llegue
 * a tener una gran cantidad de elementos
 * 
 * 
 */
public class ClientsAdapter extends ArrayAdapter<Client>{
	
	private Context mContext;
	private ArrayList<Client> mClientes;
	public ClientsAdapter(Context context, int resId, ArrayList<Client> clientes){
		super(context, resId, clientes);
		this.mContext = context;
		mClientes = clientes;
	}
	
	@Override
	public int getCount() {
		return mClientes.size();
	}
	
	static class ViewHolder{
		public TextView name;
		public TextView rfc;
		public TextView vendor;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_client, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.name = (TextView)convertView.findViewById(R.id.item_client_name);
			holder.rfc = (TextView)convertView.findViewById(R.id.item_client_rfc);
			holder.vendor = (TextView)convertView.findViewById(R.id.item_client_vendor);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		Client c = getItem(position);
		holder.name.setText(c.getNombre());
		holder.rfc.setText("RFC: "+c.getRfc());
		holder.vendor.setText("Atendió: "+c.getNameVendedor());
		return convertView;
	}
}
