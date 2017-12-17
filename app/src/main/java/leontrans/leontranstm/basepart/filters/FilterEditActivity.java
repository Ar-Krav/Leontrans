package leontrans.leontranstm.basepart.filters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.mikepenz.materialdrawer.Drawer;

import org.json.JSONException;
import org.json.JSONObject;

import leontrans.leontranstm.R;
import leontrans.leontranstm.basepart.cardpart.CardsActivity;
import leontrans.leontranstm.utils.Constants;
import leontrans.leontranstm.utils.NavigationDrawerMain;
import leontrans.leontranstm.utils.SiteDataParseUtils;

public class FilterEditActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String notifyId;

    Spinner notifyTypeSpinenr;
    Spinner carTypeSpinenr;
    Spinner carKindSpinenr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_edit);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notifyTypeSpinenr = (Spinner) findViewById(R.id.notify_type_spinner);
        carTypeSpinenr = (Spinner) findViewById(R.id.car_type);
        carKindSpinenr = (Spinner) findViewById(R.id.car_kind);

        ArrayAdapter<?> notifyTypeAdapter = ArrayAdapter.createFromResource(this, R.array.notify_types, android.R.layout.simple_spinner_item);
            notifyTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<?> carTypeAdapter = ArrayAdapter.createFromResource(this, R.array.car_types, android.R.layout.simple_spinner_item);
            carTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<?> carKindAdapter = ArrayAdapter.createFromResource(this, R.array.car_kind, android.R.layout.simple_spinner_item);
            carKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        notifyTypeSpinenr.setAdapter(notifyTypeAdapter);
        carTypeSpinenr.setAdapter(carTypeAdapter);
        carKindSpinenr.setAdapter(carKindAdapter);

        notifyId = getIntent().getStringExtra("notifyId");
        new LoadFilterInfo().execute();
    }


    private class LoadFilterInfo extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... voids) {
            SharedPreferences userPasswordSharedPreferences = FilterEditActivity.this.getSharedPreferences("hashPassword", MODE_PRIVATE);
            String userPassword = userPasswordSharedPreferences.getString("userPassword","");
            int userID = new SiteDataParseUtils().getUserIdByHashpassword("https://leon-trans.com/api/ver1/login.php?action=get_hash_id&hash=" + userPassword);
            return new SiteDataParseUtils().getSiteRequestResult("https://leon-trans.com/api/ver1/login.php?action=get_user&id=" + userID);
        }

        @Override
        protected void onPostExecute(String jsonStr) {

            try {
                JSONObject dataJson = new JSONObject(jsonStr);

                if (dataJson.getString(notifyId).isEmpty()) return;

                JSONObject notifyData = new JSONObject(dataJson.getString(notifyId));

                ((EditText) findViewById(R.id.country_from)).setText(notifyData.getString("country_from_name"));
                ((EditText) findViewById(R.id.country_to)).setText(notifyData.getString("country_to_name"));
                ((EditText) findViewById(R.id.city_from)).setText(notifyData.getString("city_from_name"));
                ((EditText) findViewById(R.id.city_to)).setText(notifyData.getString("city_to_name"));

                ((EditText) findViewById(R.id.capacity_from)).setText(notifyData.getString("capacity_from"));
                ((EditText) findViewById(R.id.capacity_to)).setText(notifyData.getString("capacity_to"));

                ((EditText) findViewById(R.id.weight_from)).setText(notifyData.getString("weight_from"));
                ((EditText) findViewById(R.id.weight_to)).setText(notifyData.getString("weight_to"));

                setNotifySpinnerSelection(notifyData.getString("type"));
                setCarTypeSpinnerSelection(notifyData.getString("trans_type"));
                setCarKindSpinnerSelection(notifyData.getString("trans_kind"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //TODO change names to correct!!!
        private void setNotifySpinnerSelection(String notifyType){
            switch (notifyType){
                case "": {
                    notifyTypeSpinenr.setSelection(0);
                    break;
                }
                case "car": {
                    notifyTypeSpinenr.setSelection(1);
                    break;
                }case "luggage": {
                    notifyTypeSpinenr.setSelection(2);
                    break;
                }
                default: notifyTypeSpinenr.setSelection(0);
            }
        }

        private void setCarTypeSpinnerSelection(String carType){
            switch (carType){
                case "":{
                    carTypeSpinenr.setSelection(0);
                    break;
                }
                case "any":{
                    carTypeSpinenr.setSelection(1);
                    break;
                }
                case "bus":{
                    carTypeSpinenr.setSelection(2);
                    break;
                }
                case "avto":{
                    carTypeSpinenr.setSelection(3);
                    break;
                }
                case "fuel_oil":{
                    carTypeSpinenr.setSelection(4);
                    break;
                }
                case "concrete":{
                    carTypeSpinenr.setSelection(5);
                    break;
                }
                case "gas":{
                    carTypeSpinenr.setSelection(6);
                    break;
                }
                case "hard":{
                    carTypeSpinenr.setSelection(7);
                    break;
                }
                case "grain":{
                    carTypeSpinenr.setSelection(8);
                    break;
                }
                case "isotherms":{
                    carTypeSpinenr.setSelection(9);
                    break;
                }
                case "containertrans":{
                    carTypeSpinenr.setSelection(10);
                    break;
                }
                case "tap":{
                    carTypeSpinenr.setSelection(11);
                    break;
                }
                case "closed":{
                    carTypeSpinenr.setSelection(12);
                    break;
                }
                case "trees":{
                    carTypeSpinenr.setSelection(13);
                    break;
                }
                case "microbus":{
                    carTypeSpinenr.setSelection(1);
                    break;
                }
                case "oversized":{
                    carTypeSpinenr.setSelection(14);
                    break;
                }
                case "unclosed":{
                    carTypeSpinenr.setSelection(15);
                    break;
                }
                case "refrigerator":{
                    carTypeSpinenr.setSelection(16);
                    break;
                }
                case "tipper":{
                    carTypeSpinenr.setSelection(17);
                    break;
                }
                case "animaltruck":{
                    carTypeSpinenr.setSelection(18);
                    break;
                }
                case "awning":{
                    carTypeSpinenr.setSelection(19);
                    break;
                }
                case "trall":{
                    carTypeSpinenr.setSelection(20);
                    break;
                }
                case "avtotipper":{
                    carTypeSpinenr.setSelection(21);
                    break;
                }
                case "fullmetal":{
                    carTypeSpinenr.setSelection(22);
                    break;
                }
                case "fuel_oil_small":{
                    carTypeSpinenr.setSelection(23);
                    break;
                }
                case "evacuator":{
                    carTypeSpinenr.setSelection(24);
                    break;
                }
                default: carTypeSpinenr.setSelection(0);
            }
        }

        private void setCarKindSpinnerSelection(String carKind){
            switch (carKind){
                case "":{
                    carKindSpinenr.setSelection(0);
                    break;
                }
                case "truck":{
                    carKindSpinenr.setSelection(1);
                    break;
                }
                case "trailer":{
                    carKindSpinenr.setSelection(2);
                    break;
                }
                case "half-trailer":{
                    carKindSpinenr.setSelection(3);
                    break;
                }
                default: carKindSpinenr.setSelection(0);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(FilterEditActivity.this, FilterSettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
