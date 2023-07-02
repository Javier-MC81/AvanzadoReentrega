package com.jmoreno.avanzado.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.jmoreno.avanzado.R
import com.jmoreno.avanzado.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.Credentials

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val password = binding.etPassword.text.toString()
            val credential = Credentials.basic("$user", "$password")

            lifecycleScope.launch {
                viewModel.getLogin(credential)

                viewModel.token.observe(this@LoginActivity) {
                    MainActivity.launch(this@LoginActivity)
                }
            }

        }
    }
}