package com.tfb.aibank.chl.general.qu001.model;

import java.io.Serializable;
import java.time.LocalTime;

// @formatter:off
/**
 * @(#)CurOrder.java
 *
 * <p>Description: TimePeroid</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2024/04/22, MartyPan
 *  <li>[TimePeroid 時間區段]</li>
 * </ol>
 */
//@formatter:on
public class TimePeriod implements Serializable {

    public static final String MORNING = "MORNING";
    public static final String NOON = "NOON";
    public static final String NIGHT = "NIGHT";

    private static final long serialVersionUID = 1L;

    private String key;

    private LocalTime start;

    private LocalTime end;

    public TimePeriod() {
    }

    public TimePeriod(String periodKey) {

        switch (periodKey) {
        case "MORNING":
            this.start = LocalTime.parse("05:30");
            this.end = LocalTime.parse("09:30");
        case "NOON":
            this.start = LocalTime.parse("11:00");
            this.end = LocalTime.parse("14:00");
        case "NIGHT":
            this.start = LocalTime.parse("18:00");
            this.end = LocalTime.parse("23:59");
        }

    }

    public boolean timeInPeriod(LocalTime inputTime) {
        return start.isBefore(inputTime) && end.isAfter(inputTime);
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }
}
