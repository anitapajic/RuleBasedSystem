package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Expires("2h30m")
public class Landing implements Serializable {

    private String flight;

    //private Date landingTime;


    public Landing(String flight) {
        super();
        //this.landingTime = new Date();
        this.flight = flight;
    }

    public Landing() {
        super();
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

//    public Date getLandingTime() {
//        return landingTime;
//    }
//
//    public void setLandingTime(Date landingTime) {
//        this.landingTime = landingTime;
//    }
}
