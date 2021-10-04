package com.example.taleia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {


    // these two variables will be used by SharedPreferences
    public static final String FILE_NAME = "file_lang"; // preference file name
    public static final String KEY_LANG = "key_lang"; // preference key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // please load language after super and before setContentView
        loadLanguage();
        setContentView(R.layout.activity_settings);
    }
    //TODO(1):Fer que no nomes es guardin les preferencies per la pagina de settings, ara comença altre cop en angles

    private void saveLanguage(String lang) {
        // we can use this method to save language
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LANG, lang);
        editor.apply();
        // we have saved
        // recreate activity after saving to load the new language, this is the same
        // as refreshing activity to load new language
        recreate();
    }

    public String getLangCode() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String langCode = preferences.getString(KEY_LANG, "en");
        // save english 'en' as the default language
        return langCode;
    }
    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_en:
                if (checked)
                    saveLanguage("en");
                    break;
            case R.id.radio_cat:
                if (checked)
                    saveLanguage("ca");
                    break;
            case R.id.radio_es:
                if (checked)
                    saveLanguage("es");
                    break;
            case R.id.radio_fr:
                if (checked)
                    saveLanguage("fr");
                    break;
        }
    }
    public void loadLanguage() {
        // we can use this method to load language,
        // this method should be called before setContentView() method of the onCreate method
        Locale locale = new Locale(getLangCode());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
    //TODO(2): Un cop funcioni tot...fer la part de canviar el mode
}
