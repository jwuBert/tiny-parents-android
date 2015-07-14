/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/13/15.
 *
 * Purpose: SchoolClass model
 */

package com.cloudiya.app.tiny_parents_android.model;

import io.realm.RealmObject;

public class SchoolClass extends RealmObject{

  private String classID;
  private String className;
  private String termCode;

  // Belonged school
  private School belongedSchool;

  public String getClassID() {
    return classID;
  }

  public void setClassID(String classID) {
    this.classID = classID;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getTermCode() {
    return termCode;
  }

  public void setTermCode(String termCode) {
    this.termCode = termCode;
  }

  public School getBelongedSchool() {
    return belongedSchool;
  }

  public void setBelongedSchool(School belongedSchool) {
    this.belongedSchool = belongedSchool;
  }
}
