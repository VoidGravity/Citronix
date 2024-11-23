package org.abdellah.citronix.util;

import java.time.LocalDate;
import java.time.Period;

public class ArbreUtil {
    private ArbreUtil() {

    }

    public static int calculateAge(LocalDate datePlantation) {
        return Period.between(datePlantation, LocalDate.now()).getYears();
    }
}