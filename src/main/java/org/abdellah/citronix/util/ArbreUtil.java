package org.abdellah.citronix.util;

import java.time.LocalDate;
import java.time.Period;

public class ArbreUtil {
    private ArbreUtil() {

    }


    public static int calculateAge(LocalDate datePlantation) {
        if (datePlantation == null) {
            return 0;
        }
        return Period.between(datePlantation, LocalDate.now()).getYears();
    }
}