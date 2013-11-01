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

package com.mobandme.ada.examples.devfest.model.parsers;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;

import com.mobandme.ada.DataBinder;
import com.mobandme.ada.DataParser;
import com.mobandme.ada.examples.devfest.helpers.ExceptionsHelper;

@SuppressLint("SimpleDateFormat")
public class DateParser extends DataParser {

	@Override
	public Object parseValue(Object pOriginalValue, int pDirection) {
		Object returnedValue = pOriginalValue;
		
		try {
			
			if (pDirection == DataBinder.BINDING_ENTITY_TO_UI) {
				if (pOriginalValue != null) {
					returnedValue = new SimpleDateFormat("EEE, d MMM yyyy").format((Date)pOriginalValue);
				} else {
					returnedValue = "";
				}
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage((Context)null, e);
		}
		
		return returnedValue;
	}
}
