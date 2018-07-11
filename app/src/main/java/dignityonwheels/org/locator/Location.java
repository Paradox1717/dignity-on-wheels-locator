package dignityonwheels.org.locator;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Location {
    private int day;
    private String[] message;

    private static String cleanDateTime(DateTime dateTime) {
        Date date = new Date(dateTime.getValue());

        DateFormat format = new SimpleDateFormat();

        return format.format(date);
    }

    public Location(int day) {
        this.day = day;

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
            Date lower = new Date(System.currentTimeMillis());
            lower.setDate(lower.getDate() + day);
            lower.setHours(1);
            lower.setMinutes(0);
            lower.setSeconds(0);

            Date upper = (Date) lower.clone();
            upper.setHours(22);

            Events list = service.events().list("dignityonwheels.org_ir9db41bmvs6s5g926c0od0guk@group.calendar.google.com")
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .setTimeMax(new DateTime(upper))
                    .setTimeMin(new DateTime(lower))
                    .execute();

            List<Event> events = list.getItems();
            message = new String[events.size()];

            for(int i = 0; i < events.size(); i++) {
                message[i] = events.get(i).getSummary() + "\n"
                        + events.get(i).getLocation() + "\n"
                        + cleanDateTime(events.get(i).getStart().getDateTime()) + " - " + cleanDateTime(events.get(i).getEnd().getDateTime());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDay() {
        return day;
    }

    public String[] getMessage() {
        return message;
    }
}
