package com.example.foododering

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foododering.Fragment.ProfileFragment
import com.example.foododering.databinding.ActivityEditProfileBinding
import com.example.foododering.databinding.ActivityLoginBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
           // intent = Intent(this@EditProfileActivity, ProfileFragment::class.java)
           // startActivity(intent)
            finish()

        }
    }
}