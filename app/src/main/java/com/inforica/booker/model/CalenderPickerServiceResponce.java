package com.inforica.booker.model;

/**
 * Created by user on 4/19/2016.
 */
public class CalenderPickerServiceResponce {
    String calender_name;
    String calender_desc;

    public CalenderPickerServiceResponce( ) {
        super();
       /* this.calender_name=calender_name;
        this.calender_desc=calender_desc;*/
    }

    public String getCalender_desc() {
        return calender_desc;
    }

    public void setCalender_desc(String calender_desc) {
        this.calender_desc = calender_desc;
    }

    public String getCalender_name() {
        return calender_name;
    }

    public void setCalender_name(String calender_name) {
        this.calender_name = calender_name;
    }


}
