package com.inforica.booker.model;

/**
 * Created by user on 4/22/2016.
 */
public class CalenderAgendaServiceResponce {
    String start_time;
    String end_time;
    String service_name;
    String customer_name;
    String service_location;
    public String getService_location() {
        return service_location;
    }

    public void setService_location(String service_location) {
        this.service_location = service_location;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }




}
