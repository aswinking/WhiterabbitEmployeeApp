package com.aswin.employeeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aswin.employeeapp.R;
import com.aswin.employeeapp.model.EmployeeModel;
import com.squareup.picasso.Picasso;

public class EmployeeDetailActivity extends AppCompatActivity {

    private EmployeeModel employeeModel;

    TextView idName,idTVuser,idTVemail,idTVphone,idTVWebsite, idTVStreet,idTVSute,idTVCity,idTVZipCode, idTVCompanyname,idTVCatchPhrase,idTVBs;
    ImageView idProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        employeeModel =  (EmployeeModel) getIntent().getSerializableExtra("employeeObj");

//        Log.d("EmployeeDetailActivity ", "onCreate: name = "+ employeeModel.getName());

        idName = findViewById(R.id.idName);
        idTVuser = findViewById(R.id.idTVuser);
        idTVemail = findViewById(R.id.idTVemail);
        idTVphone = findViewById(R.id.idTVphone);
        idTVWebsite = findViewById(R.id.idTVWebsite);
        idTVStreet = findViewById(R.id.idTVStreet);
        idTVSute = findViewById(R.id.idTVSute);
        idTVCity = findViewById(R.id.idTVCity);
        idTVZipCode = findViewById(R.id.idTVZipCode);
        idTVCompanyname = findViewById(R.id.idTVCompanyname);
        idTVCatchPhrase = findViewById(R.id.idTVCatchPhrase);
        idTVBs = findViewById(R.id.idTVBs);
        idProfileImage = findViewById(R.id.idProfileImage);

        idName.setText(employeeModel.getName());
        idTVuser.setText(employeeModel.getUsername());
        idTVemail.setText(employeeModel.getEmail());
        idTVphone.setText(employeeModel.getPhone());
        idTVWebsite.setText(employeeModel.getWebsite());
        idTVStreet.setText(employeeModel.getAddressStreet());
        idTVSute.setText(employeeModel.getAddressSute());
        idTVCity.setText(employeeModel.getAddressCity());
        idTVZipCode.setText(employeeModel.getAddressZipcode());
        idTVCompanyname.setText(employeeModel.getCompanyName());
        idTVCatchPhrase.setText(employeeModel.getCompanyCatchPhrase());
        idTVBs.setText(employeeModel.getCompanybs());
        Picasso.get().load(employeeModel.getProfileImage()).into(idProfileImage);
    }

}