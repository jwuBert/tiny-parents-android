/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/13/15.
 *
 * Purpose:
 */

package com.cloudiya.app.tiny_parents_android.helper;

import com.cloudiya.app.tiny_parents_android.model.Child;
import com.cloudiya.app.tiny_parents_android.model.Parent;
import com.cloudiya.app.tiny_parents_android.model.School;
import com.cloudiya.app.tiny_parents_android.model.SchoolClass;
import io.realm.Realm;
import org.json.JSONException;
import org.json.JSONObject;

public class PersistModelHelper {

  public static void saveUserModel(JSONObject response, Realm realm) {
    realm.beginTransaction();
    Parent parent = realm.createObject(Parent.class);
    Child child = realm.createObject(Child.class);
    SchoolClass schoolClass = realm.createObject(SchoolClass.class);
    School school = realm.createObject(School.class);
    try {
      parent.setUserID(response.getString("uid"));
      parent.setLoginName(response.getString("loginName"));
      parent.setNickname(response.getString("nickName"));
      parent.setRealName(response.getString("realName"));
      parent.setPhoneNumber(response.getString("phone"));
      parent.setAvatarURL(response.getString("headUri"));
      parent.setQRCodeURL(response.getString("qrUri"));

      JSONObject childInfo = response.getJSONObject("childInfo");
      child.setUserID(childInfo.getString("childId"));
      child.setGender(childInfo.getString("gender"));
      child.setNickname(childInfo.getString("nickName"));
      child.setName(childInfo.getString("name"));
      child.setAvatarURL(childInfo.getString("headUri"));

      schoolClass.setClassID(response.getString("classId"));
      schoolClass.setClassName(response.getString("className"));
      schoolClass.setTermCode(response.getString("termCode"));

      school.setSchoolID(response.getString("unitId"));
      school.setSchoolName(response.getString("schoolName"));
      school.setLocationID(response.getString("locationId"));

      schoolClass.setBelongedSchool(school);
      child.setSchoolClass(schoolClass);
      parent.getChildren().add(child);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    realm.commitTransaction();
  }
}
