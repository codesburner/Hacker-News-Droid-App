package com.glebpopov.hackernews;

import com.glebpopov.hackernews.fragments.NewsFragment;
import com.glebpopov.hackernews.ui.BaseSinglePaneActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class NewestActivity extends BaseSinglePaneActivity 
{
	private static final String TAG = "NewestActivity";
	private NewsFragment newsFragment = null;
	
	@Override
    protected Fragment onCreatePane() {
		newsFragment = new NewsFragment(this, R.string.hn_url_newest);
		return newsFragment;
    }
	
	public void refreshData()
	{
		Log.d(TAG, "Refreshing Data...");
		String startUpUrl = getResources().getString(R.string.hn_url_newest);
		if (newsFragment != null)
		{
			newsFragment.refreshData(startUpUrl);
		} else
		{
			try
			{
				newsFragment = new NewsFragment(this, R.string.hn_url_newest);
				newsFragment.refreshData(startUpUrl);
				
			} catch (Exception ex)
			{
				Log.e(TAG, "Unable to instantiate NewsFragment or refresh data");
			}
		}
	}
	
	public void displayPreview(String url) {
   		Intent intent = new Intent(this, PreviewActivity.class);
   		intent.putExtra("url", url);
        startActivity(intent);
	}

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        
    }
}