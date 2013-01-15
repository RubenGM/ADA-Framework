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

package com.mobandme.ada.examples.indexes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobandme.ada.examples.indexes.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.indexes.model.entities.Product;

/**
 * Products data adapter for ListViews.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Adapters
 * @version  2.3
 */
public class ProductAdapter extends ArrayAdapter<Product> {

	public ProductAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_1);
	}

	@Override
	public View getView(int pPosition, View pConvertView, ViewGroup pParent) {
		
		View returnedValue = pConvertView;
		try {
			
			if (returnedValue == null) {
				returnedValue = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(android.R.layout.simple_list_item_1, null);
			}
			
			((TextView)returnedValue).setText(getItem(pPosition).toString());
			
		} catch (Exception e) {
			ExceptionsHelper.manage(getContext(), e);
		}
		
		return returnedValue;
	}
}
