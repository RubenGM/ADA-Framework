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

package com.mobandme.ada.examples.advanced.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

/**
 * Shared entity to manage and store postal addresses.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Model Entity
 * @version  2.3
 */

@Table(name = "Address")
public class Address extends Entity {

	@TableField(name = "Street", datatype = DATATYPE_STRING, required = true)
	public String Street;
	
	@TableField(name = "Number", datatype = DATATYPE_INTEGER)
	public int Number;
	
	@TableField(name = "Floor", datatype = DATATYPE_INTEGER)
	public int Floor;
	
	@TableField(name = "Door", datatype = DATATYPE_STRING)
	public String Door;
	
	@TableField(name = "City", datatype = DATATYPE_STRING)
	public String City;
	
	@TableField(name = "Province", datatype = DATATYPE_STRING)
	public String Province;
	
	/**
	 * Configure master relation (N to 1) to Countries Entity.
	 */
	@TableField(name = "Country", datatype = DATATYPE_ENTITY_LINK, required = true)
	public Country Country;
	
	@Override
	public String toString() {
		return String.format("%s, %d, %d%s, %s, %s (%s)", Street, Number, Floor, Door, City, Province, Country);
	}
}
