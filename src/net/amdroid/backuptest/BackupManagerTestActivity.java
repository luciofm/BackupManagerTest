package net.amdroid.backuptest;

import android.app.Activity;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BackupManagerTestActivity extends Activity {

	private SharedPreferences prefs;
	private Editor edit;
	private BackupManager backupManager;
	private EditText text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		backupManager = new BackupManager(this);
		prefs = getSharedPreferences(MyBackupAgent.PREFS_TEST, Context.MODE_PRIVATE);
		edit = prefs.edit();

		text = (EditText) findViewById(R.id.editName);
		String nome = prefs.getString("KEY_NAME", "");
		text.setText(nome);

		Button button = (Button) findViewById(R.id.buttonSave);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				edit.putString("KEY_NAME", text.getText().toString());
				edit.commit();
				Log.d("Test", "Calling backup...");
				backupManager.dataChanged();
			}
		});
	}
}
