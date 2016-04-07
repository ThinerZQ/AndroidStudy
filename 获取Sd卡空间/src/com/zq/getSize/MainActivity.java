package com.zq.getSize;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.app.Activity;
import android.text.format.Formatter;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv = (TextView)findViewById(R.id.tv);
		
		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		long availableBlocks = stat.getAvailableBlocks();
		
		long totalSize = blockSize*totalBlocks;
		long availableSize = blockSize*availableBlocks;
		
		String totalStr = Formatter.formatFileSize(this, totalSize);
		String availableStr = Formatter.formatFileSize(this, availableSize);
		
		tv.setText("×ÜÄÚ´æ : "+totalStr+"\n"+"¿×ÓÂÄÚ´æ£º"+availableStr);
		

		
		
	}

	

}
