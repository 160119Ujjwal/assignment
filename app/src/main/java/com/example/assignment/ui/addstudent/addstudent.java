package com.example.assignment.ui.addstudent;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.assignment.MainActivity;
import com.example.assignment.R;
import com.example.assignment.model.Student;

public class addstudent extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    private AddstudentViewModel mViewModel;
    private String fullname,gender,address,age;
    private EditText editTextFullName,editTextAddress,editTextAge;
    private RadioGroup radioG;
    private Button btnsubmit;


    public static addstudent newInstance() {
        return new addstudent();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(AddstudentViewModel.class);
        View root = inflater.inflate(R.layout.addstudent_fragment, container, false);
        editTextFullName = root.findViewById(R.id.fullname);
        editTextAddress = root.findViewById(R.id.address);
        editTextAge = root.findViewById(R.id.age);
        radioG=root.findViewById(R.id.gender);
        btnsubmit=root.findViewById(R.id.btn_save);

        radioG.setOnCheckedChangeListener(this);
        btnsubmit.setOnClickListener(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddstudentViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean validate(){
        if(TextUtils.isEmpty(fullname))
        {
            editTextFullName.setError("Please enter a name");
            editTextFullName.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(age))
        {
            editTextAge.setError("Please enter age");
            editTextAge.requestFocus();
            return false;
        }

        if(!TextUtils.isDigitsOnly(age))
        {
            editTextAge.setError("Please enter age");
            editTextAge.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(address))
        {
            editTextAddress.setError("Please enter an address");
            editTextAddress.requestFocus();
            return false;
        }



        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int i) {
        if(i== R.id.male_radio_btn)
        {
            gender="Male";
        }
        if(i == R.id.female_radio_btn)
        {
            gender = "Female";
        }
        if(i== R.id.other_radio_btn)
        {
            gender ="Other";
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_save)
        {

            fullname = editTextFullName.getText().toString();
            age=editTextAge.getText().toString();
            address=editTextAddress.getText().toString();
            if(validate())
            {
                MainActivity.st.add(new Student(fullname,gender,age,address));
                Toast.makeText(getContext(),"Student added", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
