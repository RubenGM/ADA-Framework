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

package com.mobandme.ada.examples.advanced.model.helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.Context;
import com.mobandme.ada.examples.advanced.helpers.ExceptionsHelper;
import com.mobandme.ada.examples.advanced.model.entities.Country;

/**
 * Initial Countries loader helper.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Data Loaders Helper
 * @version  2.3
 */

public class CountriesLoaderHelper {

	/**
	 * This method read a countries.json file from application Assets folder and return a list of Countries.
	 * @param pContext
	 * @return List with filled Countries.
	 */
	public static List<Country> getList(Context pContext) {
		List<Country> returnedValue = new ArrayList<Country>();

		try {
			String data = null;
			
			if (pContext != null) {
				
				InputStream    input = pContext.getAssets().open("countries.json");
				if (input != null) {
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(input));
					if (reader != null) {
						data =  reader.readLine();
						reader.close();
					}
					
					input.close();
				}
				
				if (data != null && !data.trim().equals("")) {
					JSONArray dataParser = new JSONArray(data);
					if (dataParser != null && dataParser.length() > 0) {
						for (int index = 0; index < dataParser.length(); index++) {
							returnedValue.add(new Country(dataParser.getString(index)));
						}
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionsHelper.manage(e);
		}
		
		return returnedValue;
	}
}
