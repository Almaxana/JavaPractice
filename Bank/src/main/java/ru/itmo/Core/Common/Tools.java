package ru.itmo.Core.Common;

import org.joda.time.DateTime;

/**
 * Tools contains common useful functions
 */
public class Tools {
    /**
     * Clones DateTime
     * @param currentData param to be cloned
     * @return copy of DateTime
     */
    public DateTime cloneDateTime(DateTime currentData){
        return new DateTime(currentData.getYear(), currentData.getMonthOfYear(), currentData.getDayOfMonth(), currentData.getHourOfDay(), currentData.getMinuteOfHour());
    }
}
