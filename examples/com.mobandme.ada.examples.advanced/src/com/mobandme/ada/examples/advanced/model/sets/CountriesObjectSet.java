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

package com.mobandme.ada.examples.advanced.model.sets;

import java.util.List;
import com.desandroid.framework.ada.ObjectSet;
import com.desandroid.framework.ada.ObjectContext;
import com.mobandme.ada.examples.advanced.model.entities.Country;
import com.mobandme.ada.examples.advanced.helpers.ExceptionsHelper;
import com.desandroid.framework.ada.exceptions.AdaFrameworkException;

/**
 * Custom Countries Object set.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Model ObjectSets
 * @version  2.3
 */

@SuppressWarnings("serial")
public class CountriesObjectSet extends ObjectSet<Country> {

	public CountriesObjectSet(ObjectContext pContext) throws AdaFrameworkException {
		super(Country.class, pContext);
	}

	public Country getByName(String pName) {
		return getByName(pName, false);
	}
	
	/**
	 * This method find the country by name.
	 * @param pName Name of the country. 
	 * @return Country entity.
	 */
	public Country getByName(String pName, boolean pAddIfNotExist) {
		Country returnedValue = null;
		
		try {
			
			if (pName != null && !pName.trim().equals("")) {
				String   wherePattern = String.format("%s = ?", getDataTableFieldName("Name"));
				String[] whereValues  = new String[] { pName };
				
				//Limit the query to 1 result.
				List<Country> result = search(true, wherePattern, whereValues, null, null, null, 0, 1);
				if (result != null && result.size() > 0) {
					returnedValue = result.get(0);
					
				//If the Country does not exist into DataBase, add it and save.
				} else if (pAddIfNotExist) {
					//Create a new Country.
					returnedValue = new Country(pName);

					//Save the element into DataBase.
					save(returnedValue);
					
					//Add the element to ObjectSet list.
					add(returnedValue);
				}
			}
		
		} catch (Exception e) {
			ExceptionsHelper.manage(e);
		}
		
		return returnedValue;
	}
}
