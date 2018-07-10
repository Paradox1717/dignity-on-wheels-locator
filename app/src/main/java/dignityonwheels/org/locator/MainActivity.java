package dignityonwheels.org.locator;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static AssetManager assets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assets = getAssets();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location location = new Location(0);

        TextView text = findViewById(R.id.text);
        text.setText(location.getMessage());
    }
}
