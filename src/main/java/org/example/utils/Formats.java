package org.example.utils;

import java.time.format.DateTimeFormatter;

public class Formats {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
}
