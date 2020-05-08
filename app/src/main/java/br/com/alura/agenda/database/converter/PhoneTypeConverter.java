package br.com.alura.agenda.database.converter;

import androidx.room.TypeConverter;

import br.com.alura.agenda.model.PhoneType;

public class PhoneTypeConverter {

    @TypeConverter
    public String toString(PhoneType type){
        if(type != null) {
            return type.name();
        }
        return null;
    }

    @TypeConverter
    public PhoneType toPhoneType(String type){
        if(type != null){
            return PhoneType.valueOf(type);
        }
        return PhoneType.PHONE;
    }
}
