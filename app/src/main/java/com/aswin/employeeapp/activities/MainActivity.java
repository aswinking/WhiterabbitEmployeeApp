package com.aswin.employeeapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.aswin.employeeapp.R;
import com.aswin.employeeapp.adapter.EmployeeAdapter;
import com.aswin.employeeapp.helper.DBHandler;
import com.aswin.employeeapp.model.EmployeeModel;
import androidx.appcompat.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "https://www.mocky.io/v2/5d565297300000680030a986";

    private RecyclerView employeeRV;

    private EmployeeAdapter adapter;
    private ArrayList<EmployeeModel> employeeModalArrayList;

    private DBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeRV = findViewById(R.id.idRVEmployee);

        employeeModalArrayList = new ArrayList<>();

        dbHandler = new DBHandler(this);

        /**Checking table already load**/
        if (dbHandler.getEmployeeCount() == 0){
            //Fetch data from API call
            getEmployeeData();
        }else{
            //Fetch data from SQLite Database
            employeeModalArrayList = dbHandler.getAllEmployees();
            buildRecyclerView();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    /** Fetch Data from API call **/
    private void getEmployeeData(){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait loading..."); // set message
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        employeeRV.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                        try{
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject employee = response.getJSONObject(i);

                                String name = employee.getString("name");
                                String username = employee.getString("username");
                                String email = employee.getString("email");
                                String addressStreet = employee.getJSONObject("address").getString("street");
                                String addressSute = employee.getJSONObject("address").getString("suite");
                                String addressCity = employee.getJSONObject("address").getString("city");
                                String addressZipcode = employee.getJSONObject("address").getString("zipcode");
                                String phone = employee.getString("phone");
                                String website = employee.getString("website");
                                String profileImage = employee.getString("profile_image");
                                String companyName = employee.getJSONObject("company").getString("name");
                                String companyCatchPhrase= employee.getJSONObject("company").getString("catchPhrase");
                                String companybs= employee.getJSONObject("company").getString("bs");

                                employeeModalArrayList.add(new EmployeeModel(name,username,email,addressStreet,addressSute,addressCity,addressZipcode,phone,website,profileImage,companyName,companyCatchPhrase,companybs));
                                dbHandler.addEmployee(new EmployeeModel(name,username,email,addressStreet,addressSute,addressCity,addressZipcode,phone,website,profileImage,companyName,companyCatchPhrase,companybs));
                                buildRecyclerView();

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        progressDialog.dismiss();
                        Log.e("MainActivity", "onErrorResponse: message = "+ error.getMessage() );
                    }
                }
        );

        queue.add(jsonArrayRequest);

    }

    /**Build RecyclerView**/
    private void buildRecyclerView() {
        adapter = new EmployeeAdapter(employeeModalArrayList, MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        employeeRV.setHasFixedSize(true);
        employeeRV.setLayoutManager(manager);
        employeeRV.setAdapter(adapter);
    }
}