package dignityonwheels.org.locator;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static AssetManager assets;
    public static File cacheDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assets = getAssets();
        cacheDir = getCacheDir();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location location = new Location(0);

        TextView text = findViewById(R.id.text);
        text.setText(location.getMessage());
    }
}
