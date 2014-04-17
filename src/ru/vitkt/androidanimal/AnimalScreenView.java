package ru.vitkt.androidanimal;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class AnimalScreenView extends TextView{

	public AnimalScreenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void setColor(int a,int r, int g, int b)
	{
		setBackgroundColor(Color.argb(255, r, g, b));
		//setText(a+";"+r+";"+g+";"+b+";"); for Debug
	}

}
