package dignityonwheels.org.locator;

import android.os.AsyncTask;

public class GetLocationTask extends AsyncTask<Integer, Void, Location> {
    protected Location doInBackground(Integer ... args) {
        return new Location(args[0]);
    }

    protected void onPostExecute(Location result) {

    }
}
