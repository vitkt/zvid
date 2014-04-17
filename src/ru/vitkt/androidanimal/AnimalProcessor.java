package ru.vitkt.androidanimal;

import android.app.Activity;
import android.os.Vibrator;
import android.util.Log;

public class AnimalProcessor {

	Activity activity;

	private AnimalOutput animalOutput;
	private AnimalInput animalInput;

	public AnimalProcessor(Activity _activity) {
		activity = _activity;
		animalOutput = new AnimalOutput(_activity);
		animalInput = new AnimalInput(_activity);

	}

	float lightEmotion = 0f;
	float soundEmotion = 100f;
	float stabilityEmotion = 100f;
	float safetyEmotion = 100f;

	public void process() {
		int proximity = animalInput.getProximity();

		lightEmotion = 0f;
		if (proximity != 0)
			lightEmotion += 50f;

		int light = animalInput.getLight();

		lightEmotion += ((float) light / 300.0f) * 50f;
		stabilityEmotion = animalInput.getStability() * 100f;

		float unstable = animalInput.getUnstableValue();
		if (unstable >= 10f) {
			if (unstable > 30f)
				unstable = 30f;
			float unstableFactor = (unstable - 10f) / 20f;
			safetyEmotion = 100f - (unstableFactor * 100f);
		} else
			safetyEmotion = 100f;

		showEmotions();
	}

	private void showEmotions() {
		if (lightEmotion > 100)
			lightEmotion = 100;
		if (soundEmotion > 100)
			soundEmotion = 100;
		if (stabilityEmotion > 100)
			stabilityEmotion = 100;
		int a, r, g, b;
		if (safetyEmotion < 50f)
			animalOutput.vibrate();
		
		a = (int) ((safetyEmotion / 100f) * 255);
		r = (int) ((soundEmotion / 100f) * 255);
		g = (int) ((lightEmotion / 100f) * 255);
		b = (int) ((stabilityEmotion / 100f) * 255);

		if (safetyEmotion < 50f){
			animalOutput.vibrate();
			r*=(safetyEmotion / 100f);
			g*=(safetyEmotion / 100f);
			b*=(safetyEmotion / 100f);
		}
		animalOutput.setColorToScreen(a, r, g, b);

	}

	public void onResume() {
		animalInput.onResume();
	}

	public void onPause() {
		animalInput.onPause();
	}

}
