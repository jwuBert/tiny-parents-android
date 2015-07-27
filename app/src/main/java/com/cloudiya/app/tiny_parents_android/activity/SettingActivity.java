package com.cloudiya.app.tiny_parents_android.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.model.Parent;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by bert on 7/25/15.
 */
public class SettingActivity extends Activity {
	private static final String TAG=SettingActivity.class.getSimpleName();

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sharepre=getSharedPreferences(getString(R.string.preference_file_key),
				MODE_PRIVATE);
		String currentParentId=sharepre.getString(getString(R.string.current_parent_ID), null);
		Realm  realm=Realm.getInstance(getApplicationContext());
		RealmResults<Parent>currentParentResults=realm.where(Parent.class).equalTo("userID",currentParentId).findAll();
		Parent currentParent;
		if(currentParentResults.size()>1){
			Log.e(TAG,"多个用户登录");
			return;
		}else if(currentParentResults.size()==0){
			Log.e(TAG,"发现未有用户登录过");
			return;
		}else{
		currentParent=currentParentResults.first();
		}
		String nickname=currentParent.getNickname();
		final TextView nicknametextview=(TextView)findViewById(R.id.nick_name);
		 nicknametextview.setText(nickname);

	}

	@Override protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
	}


}
