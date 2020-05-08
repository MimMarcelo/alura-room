package br.com.alura.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarConverter {

    @TypeConverter
    public Long toLong(Calendar date){
        if(date != null) {
            return date.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long millis){
        Calendar calendar = Calendar.getInstance();
        if(millis != null){
            calendar.setTimeInMillis(millis);
        }
        return calendar;
    }
}
