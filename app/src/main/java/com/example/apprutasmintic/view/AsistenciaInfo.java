package com.example.apprutasmintic.view;

public class AsistenciaInfo {

    long date;


    Boolean attendad_Morning;
    Boolean attendad_Afternoon;

    public AsistenciaInfo(long date, Boolean attendad_Morning, Boolean attendad_Afternoon) {

        this.date = date;
        this.attendad_Morning = attendad_Morning;
        this.attendad_Afternoon = attendad_Afternoon;
    }

    public AsistenciaInfo() {
    }



    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Boolean getAttendad_Morning() {
        return attendad_Morning;
    }

    public void setAttendad_Morning(Boolean attendad_Morning) {
        this.attendad_Morning = attendad_Morning;
    }

    public Boolean getAttendad_Afternoon() {
        return attendad_Afternoon;
    }

    public void setAttendad_Afternoon(Boolean attendad_Afternoon) {
        this.attendad_Afternoon = attendad_Afternoon;
    }

    @Override
    public String toString() {
        return "AsistenciaInfo{" +
                ", date=" + date +
                ", attendad_Morning=" + attendad_Morning +
                ", attendad_Afternoon=" + attendad_Afternoon +
                '}';
    }
    /*
    public AsistenciaInfo(String trip,  long date, Boolean attendad) {
        this.trip = trip;
        this.date = date;
        this.attendad = attendad;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public  long getFecha() {
        return date;
    }

    public void setFecha(long  date) {
        this.date = date;
    }

    public Boolean isAttendad() {
        return attendad;
    }

    public void setAttendad(Boolean attendad) {
        this.attendad = attendad;
    }

    @Override
    public String toString() {
        return "AsistenciaInfo{" +
                "trip='" + trip + '\'' +
                ", date=" + date +
                ", attendad=" + attendad +
                '}';
    }

 */
}
