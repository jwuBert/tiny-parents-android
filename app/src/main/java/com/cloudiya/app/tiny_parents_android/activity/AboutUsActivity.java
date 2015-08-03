/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/27/15.
 *
 * Purpose:
 */

package com.cloudiya.app.tiny_parents_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.cloudiya.app.tiny_parents_android.R;

	public class AboutUsActivity extends Activity {
			private static  final  String TAG=AboutUsActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		Log.d(TAG, "pass AboutUsActivity");
		setContentView(R.layout.activity_aboutus);
		Button num1=(Button)findViewById(R.id.aboutus_service_tel1);
 		Button num2=(Button)findViewById(R.id.aboutus_service_tel2);
 		Button business=(Button)findViewById(R.id.aboutus_business_address);
		Button skill=(Button)findViewById(R.id.aboutus_skill_address);

		//call out
		num1.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {

				Uri uri = Uri.parse(getString(R.string.aboutus_telphonenumber1));
				Intent intent = new Intent(Intent.ACTION_DIAL, uri);
				startActivity(intent);
			}
		});
		num2.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
				Uri uri=Uri.parse(getString(R.string.aboutus_telphonenumber2));
				Intent intent=new Intent(Intent.ACTION_DIAL,uri);
				startActivity(intent);
			}
		});
		//link worknet
		business.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
 				Uri uri=Uri.parse(getString(R.string.aboutus_sale_address));
				Intent it= new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(it);
				startActivity(Intent.createChooser(it, getString(R.string.aboutus_choose_email_to_sale)));
			}
		});


		skill.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View view) {
				Uri uri=Uri.parse(getString(R.string.aboutus_sendemail_to_skill));
				Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
				startActivity(intent);
				startActivity(Intent.createChooser(intent,getString(R.string.aboutus_choose_email_to_support)));
			}
		});
	}
}

