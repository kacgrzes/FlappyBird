package com.grzeszczyk.weronika;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.grzeszczyk.weronika.FlappyBird;

public class AndroidLauncher extends AndroidApplication {
	DisplayMetrics displayMetrics;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyBird(), config);

		Context context = getContext();
		displayMetrics = context.getResources().getDisplayMetrics();
		FlappyBird.WIDTH = displayMetrics.widthPixels;
		FlappyBird.HEIGHT = displayMetrics.heightPixels;
	}
}
