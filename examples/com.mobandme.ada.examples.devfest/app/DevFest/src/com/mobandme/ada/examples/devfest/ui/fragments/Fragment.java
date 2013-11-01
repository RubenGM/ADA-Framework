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

package com.mobandme.ada.examples.devfest.ui.fragments;

import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public abstract class Fragment extends android.support.v4.app.Fragment {

	public String getFragmentTag() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View returnedValue = null;
		
		try {
			
			returnedValue = onFragmentCreateView(inflater, container, savedInstanceState);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
		
		return returnedValue;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		try {
			
			super.onViewCreated(view, savedInstanceState);
			
			setHasOptionsMenu(true);
			
			onFragmentViewCreated(view, savedInstanceState);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}

	@Override
	public void onResume() {
		try {
			
			super.onResume();
			
			onFragmentResume();
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		try {
			
			super.onCreateOptionsMenu(menu, inflater);
			
			menu.clear();
			onFragmentCreateOptionsMenu(menu, inflater);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean returnedValue = false;
		
		try {
			
			super.onOptionsItemSelected(item);
			
			returnedValue = onFragmentOptionsItemSelected(item);
			
		} catch (Exception e) {
			ExceptionsHelper.manage(this, e);
		}
		
		return returnedValue;
	}
	
	protected View onFragmentCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { return null; }
	
	protected void onFragmentViewCreated(View view, Bundle savedInstanceState) { }

	protected void onFragmentResume() { }
	
	protected void onFragmentCreateOptionsMenu(Menu menu, MenuInflater inflater) { }
	
	protected boolean onFragmentOptionsItemSelected(MenuItem item) { return false; }
}
