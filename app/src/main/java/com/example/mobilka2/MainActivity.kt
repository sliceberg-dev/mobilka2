package com.example.mobilka2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobilka2.databinding.ActivityMainBinding

/**
 * Główna aktywność aplikacji rejestracji konta.
 * Autor: 00000000000
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ustawienie początkowego komunikatu z numerem PESEL
        binding.tvMessage.text = getString(R.string.initial_author_pesel)

        // Obsługa przycisku ZATWIERDŹ
        binding.btnSubmit.setOnClickListener {
            validateAndRegister()
        }
    }

    /********************************************************
     * nazwa funkcji: validateAndRegister
     * parametry wejściowe: brak
     * wartość zwracana: brak
     * autor: 00000000000
     * opis: Funkcja pobiera wprowadzony adres e-mail,
     *       hasło oraz powtórzone hasło. Następnie sprawdza,
     *       czy adres e-mail zawiera znak '@' oraz czy
     *       podane hasła są identyczne.
     *       W zależności od wyniku walidacji wyświetla
     *       odpowiedni komunikat w obszarze komunikatów.
     ********************************************************/
    private fun validateAndRegister() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()

        if (!email.contains("@")) {
            binding.tvMessage.text = getString(R.string.error_invalid_email)
        } else if (password != repeatPassword) {
            binding.tvMessage.text = getString(R.string.error_passwords_differ)
        } else {
            binding.tvMessage.text = getString(R.string.welcome_message, email)
        }
    }
}