package com.apu.maamuu.ui.databook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apu.maamuu.db.DatabaseHelper;
import com.apu.maamuu.entities.Databook;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.melnykov.fab.FloatingActionButton;
import com.apu.maamuu.R;

public class DatabookFragment extends Fragment {
	String LOG_TAG = DatabookFragment.class.getName();

	private DatabaseHelper databaseHelper = null;
	private JSONObject json = null;
	private JSONArray dataBookJSON = null;
	private String DATABOOK = "databook";
	private String ID = "id";
	private String NAME = "name";
	private String ADDRESS = "address";
	private String PN1 = "phonenumber1";
	private String PN2 = "phonenumber2";
	private String PN3 = "phonenumber3";
	private String TYPE = "type";

	private List<Databook> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.databook_view, container, false);

		RecyclerView recyclerView = (RecyclerView) root
				.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		try {
			list = new ArrayList<Databook>();
			json = new JSONObject(loadJSONFromAsset());
			dataBookJSON = json.getJSONArray(DATABOOK);
			Log.d(LOG_TAG,"womenseho.length "+dataBookJSON.length());
			for(int i=0; i<dataBookJSON.length(); i++){
				JSONObject db = dataBookJSON.getJSONObject(i);
				Databook dataBook = new Databook();
				dataBook.setId(Integer.parseInt(db.getString(ID)));
				dataBook.setName(db.getString(NAME));
				dataBook.setAddress(db.getString(ADDRESS));
				dataBook.setPhone1(db.getString(PN1));
				dataBook.setPhone2(db.getString(PN2));
				dataBook.setPhone3(db.getString(PN3));
				dataBook.setType(db.getString(TYPE));
				list.add(dataBook);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (list != null) {
			DatabookAdapter adapter = new DatabookAdapter(getActivity(), list);
			recyclerView.setAdapter(adapter);
		}
		FloatingActionButton fab = (FloatingActionButton) root
				.findViewById(R.id.fab);
		fab.attachToRecyclerView(recyclerView);
		return root;
	}

	 public String loadJSONFromAsset() {
	        String json = null;
	        try {
	            InputStream is = getActivity().getAssets().open("databook.json");
	            int size = is.available();
	            byte[] buffer = new byte[size];
	            is.read(buffer);
	            is.close();
	            json = new String(buffer, "UTF-8");
	       } catch (IOException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	        return json;
	    }
	 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}
