package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taleia.R
import android.content.SharedPreferences
import android.content.res.Configuration
import android.view.View
import com.example.taleia.SettingsActivity
import android.widget.RadioButton
import java.util.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // please load language after super and before setContentView
        loadLanguage()
        setContentView(R.layout.activity_settings)
    }

    //TODO(1):Fer que no nomes es guardin les preferencies per la pagina de settings, ara comença altre cop en angles
    private fun saveLanguage(lang: String) {
        // we can use this method to save language
        val preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(KEY_LANG, lang)
        editor.apply()
        // we have saved
        // recreate activity after saving to load the new language, this is the same
        // as refreshing activity to load new language
        recreate()
    }

    // save english 'en' as the default language
    val langCode: String?
        get() {
            val preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            // save english 'en' as the default language
            return preferences.getString(KEY_LANG, "en")
        }

    fun onRadioButtonClicked(view: View) {

        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.radio_en -> if (checked) saveLanguage("en")
            R.id.radio_cat -> if (checked) saveLanguage("ca")
            R.id.radio_es -> if (checked) saveLanguage("es")
            R.id.radio_fr -> if (checked) saveLanguage("fr")
        }
    }

    fun loadLanguage() {
        // we can use this method to load language,
        // this method should be called before setContentView() method of the onCreate method
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    } //TODO(2): Un cop funcioni tot...fer la part de canviar el mode

    companion object {
        // these two variables will be used by SharedPreferences
        const val FILE_NAME = "file_lang" // preference file name
        const val KEY_LANG = "key_lang" // preference key
    }
}