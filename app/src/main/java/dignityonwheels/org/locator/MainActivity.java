package dignityonwheels.org.locator;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static AssetManager assets;
    public static File cacheDir;
    public static TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assets = getAssets();
        cacheDir = getCacheDir();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        new GetLocationTask().execute(0);
    }
}
