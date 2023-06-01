package com.groupe2.redline.dto;

import com.groupe2.redline.validation.groups.Creation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ReservationDTO {

    @NotNull(groups = Creation.class)
    @Future
    private Date date;
    private int creneau;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCreneau() {
        return creneau;
    }

    public void setCreneau(int creneau) {
        this.creneau = creneau;
    }
}
