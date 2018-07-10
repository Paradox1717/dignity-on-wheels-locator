package dignityonwheels.org.locator;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

public class Location {
    private int day;
    private String message;
    public Location(int day) {
        message = "";

        Calendar service;

        try {
            HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = AndroidJsonFactory.getDefaultInstance();

            GoogleCredential credential = GoogleCredential.fromStream(MainActivity.assets.open("dignity-on-wheels-locator-6e03f52e8423.json"))
                    .createScoped(Collections.singleton(CalendarScopes.CALENDAR_READONLY));

            service = new Calendar.Builder(httpTransport, jsonFactory, credential).build();
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            CalendarList list = service.calendarList().list().execute();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        /*try {
            Date today = new Date(System.currentTimeMillis());
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);

            Date tomorrow = (Date) today.clone();
            tomorrow.setDate(today.getDate() + 1);

            Events list = service.events().list("primary")
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .setTimeMax(new DateTime(tomorrow))
                    .setTimeMin(new DateTime(today))
                    .execute();

            List<Event> events = list.getItems();
            for(Event event: events) {
                message += event.getSummary() + "\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    public int getDay() {
        return day;
    }

    public String getMessage() {
        return message;
    }
}
