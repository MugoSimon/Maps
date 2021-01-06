package lastie_wangechian_Maps.com;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServicesOk()) {

            init();
        }
    }

    private void init() {

        button = findViewById(R.id.button_intent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jump_intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(jump_intent);
            }
        });

    }

    public boolean isServicesOk() {

        Log.d("isServicesOK: ", "checking google service versions");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {

            //everything is fine and user could make map requests.
            Log.d("isServicesOK: ", "Everything is alright...");
            return true;

        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {

            //there is an error but its resolvable.
            Log.d("isServicesOK: ", "Resolvable error in our midst...");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        } else {

            //Not map request you can definitely not make any map request.
            Log.d("isServicesOK: ", "Unresolvable error in our midst...Hopeless");
            Toast.makeText(this, "couldn't make any map request", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

}
