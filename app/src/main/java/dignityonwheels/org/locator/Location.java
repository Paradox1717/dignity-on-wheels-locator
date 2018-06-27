package dignityonwheels.org.locator;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class Location {
    private int day;
    private String location;
    public Location(int day) {
        Calendar service;

        try {
            GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("INSERT PUBLIC/PRIVATE KEY HERE"))
                    .createScoped(Collections.singleton(CalendarScopes.CALENDAR_READONLY));

            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            service = new Calendar.Builder(httpTransport, jsonFactory, credential).build();
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            throw new RuntimeException(e);
        } catch(GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        
    }

    public int getDay() {
        return day;
    }

    public String getLocation() {
        return location;
    }
}
