/**
   Copyright Mob&Me 2013 (@MobAndMe)

   Licensed under the GPL General Public License, Version 3.0 (the "License"),  
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.gnu.org/licenses/gpl.html

   Unless required by applicable law or agreed to in writing, software 
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   Website: http://adaframework.com
   Contact: Txus Ballesteros <txus.ballesteros@mobandme.com>
*/

package com.mobandme.ada.examples.devfest.ui.activities;

import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.devfest.ui.fragments.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public abstract class Activity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstance) {
		try {
			
			super.onCreate(savedInstance);
			
			configureActionBar();
			
			onActivityCreate(savedInstance);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean returnedValue = false;
		
		try {
			
			returnedValue = onActivityOptionsItemSelected(item);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
		
		return returnedValue;
	}
	
	protected void onActivityCreate(Bundle savedInstance) { }
	
	protected boolean onActivityOptionsItemSelected(MenuItem item) { return false; }
	
	protected void showFragment(Fragment oldFragment, Fragment newFragment) {
		android.support.v4.app.Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(newFragment.getFragmentTag());
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		if (currentFragment != null) {
			if (oldFragment != null) 
				transaction.hide(oldFragment);

			if (currentFragment.isDetached())
				transaction.attach(currentFragment);
			
			transaction.show(currentFragment);
		} else {
			if (oldFragment != null)
				transaction.hide(oldFragment);
			
			if (newFragment != null)  {
				if (newFragment.isDetached()) {
					transaction.attach(newFragment);
				} else {
					transaction.add(R.id.MainLayout, newFragment, newFragment.getFragmentTag());
				}
			}
			
		}
		
		if (oldFragment != null)
			transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
	private void configureActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
	}
}
