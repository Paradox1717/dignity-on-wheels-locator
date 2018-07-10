package dignityonwheels.org.locator;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assets = getAssets();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetLocationTask().execute(0);
    }
}
