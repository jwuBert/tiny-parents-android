/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/13/15.
 *
 * Purpose: School model
 */

package com.cloudiya.app.tiny_parents_android.model;

import io.realm.RealmObject;

public class School extends RealmObject{

  private String schoolID;
  private String schoolName;
  private String locationID;

  public String getSchoolID() {
    return schoolID;
  }

  public void setSchoolID(String schoolID) {
    this.schoolID = schoolID;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public String getLocationID() {
    return locationID;
  }

  public void setLocationID(String locationID) {
    this.locationID = locationID;
  }
}
