/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/13/15.
 *
 * Purpose: Parent model
 */

package com.cloudiya.app.tiny_parents_android.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Parent extends RealmObject{

  private String userID;
  private String loginName;
  private String nickname;
  private String realName;
  private String phoneNumber;
  private String avatarURL;
  private String QRCodeURL;

  // Children list
  private RealmList<Child> children;

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAvatarURL() {
    return avatarURL;
  }

  public void setAvatarURL(String avatarURL) {
    this.avatarURL = avatarURL;
  }

  public String getQRCodeURL() {
    return QRCodeURL;
  }

  public void setQRCodeURL(String QRCodeURL) {
    this.QRCodeURL = QRCodeURL;
  }

  public RealmList<Child> getChildren() {
    return children;
  }

  public void setChildren(RealmList<Child> children) {
    this.children = children;
  }
}
