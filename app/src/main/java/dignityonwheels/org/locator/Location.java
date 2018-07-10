package dignityonwheels.org.locator;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Location {
    private int day;
    private String message;
    public Location(int day) {
        message = "";
        Calendar service;

        try {
            GoogleCredential credential = GoogleCredential.fromStream(MainActivity.assets.open("dignity-on-wheels-locator-6e03f52e8423.json"))
                    .createScoped(Collections.singleton(CalendarScopes.CALENDAR_READONLY));

            HttpTransport httpTransport = new NetHttpTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            service = new Calendar.Builder(httpTransport, jsonFactory, credential).build();
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Date today = new Date(System.currentTimeMillis());
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);

            Date tomorrow = (Date) today.clone();
            tomorrow.setDate(today.getDate() + 1);

            Events list = service.events().list("primary")
                    /*.setOrderBy("startTime")
                    .setSingleEvents(true)
                    .setTimeMax(new DateTime(tomorrow))
                    .setTimeMin(new DateTime(today))*/
                    .execute();

            /*List<Event> events = list.getItems();
            for(Event event: events) {
                message += event.getSummary() + "\n";
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDay() {
        return day;
    }

    public String getMessage() {
        return message;
    }
}
