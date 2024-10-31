package com.example.listviewexcercise

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp1.AddressHelper

class Bai3Activity: AppCompatActivity() {
    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai_3)

        addressHelper = AddressHelper(resources)

        val spProvince = findViewById<Spinner>(R.id.spProvince)
        val spDistrict = findViewById<Spinner>(R.id.spDistrict)
        val spWard = findViewById<Spinner>(R.id.spWard)

        // Load Provinces
        val provinces = addressHelper.getProvinces()
        val provinceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        spProvince.adapter = provinceAdapter

        spProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                loadDistricts(selectedProvince, spDistrict)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = provinces[spProvince.selectedItemPosition]
                val selectedDistrict = spDistrict.selectedItem.toString()
                loadWards(selectedProvince, selectedDistrict, spWard)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Set up Submit button and validation
        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            validateForm()
        }
    }

    private fun loadDistricts(province: String, spDistrict: Spinner) {
        val districts = addressHelper.getDistricts(province)
        val districtAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, districts)
        spDistrict.adapter = districtAdapter
    }

    private fun loadWards(province: String, district: String, spWard: Spinner) {
        val wards = addressHelper.getWards(province, district)
        val wardAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, wards)
        spWard.adapter = wardAdapter
    }

    private fun validateForm() {
        val mssv = findViewById<EditText>(R.id.etMSSV).text.toString()
        val name = findViewById<EditText>(R.id.etName).text.toString()
        val email = findViewById<EditText>(R.id.etEmail).text.toString()
        val phone = findViewById<EditText>(R.id.etPhone).text.toString()
        val isAgree = findViewById<CheckBox>(R.id.cbAgree).isChecked

        if (mssv.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || !isAgree) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}