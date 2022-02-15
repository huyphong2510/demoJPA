package com.vti.demo.dto.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DepartmentFilter {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxDate;

    public DepartmentFilter() {
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

}
