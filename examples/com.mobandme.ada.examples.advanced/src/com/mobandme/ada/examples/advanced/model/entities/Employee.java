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

import java.util.ArrayList;
import java.util.List;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.TableField;

/**
 * Employee Entity.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Model Entity
 * @version  2.3
 */

public abstract class Employee extends Entity {

	public static final String DEFAULT_SORT = "Name ASC";
	
	@TableField(name = "Company", datatype = DATATYPE_ENTITY_LINK)
	public Company Company;
	
	@TableField(name = "Address", datatype = DATATYPE_ENTITY)
	public List<Address> Address = new ArrayList<Address>();
	
	@TableField(name = "Name", datatype = DATATYPE_STRING)
	public String Name;
	
	@TableField(name = "Surame", datatype = DATATYPE_STRING)
	public String Surname;
	
	@TableField(name = "UserName", datatype = DATATYPE_STRING, encripted = true, unique = true)
	public String UserName;
	
	@TableField(name = "Password", datatype = DATATYPE_STRING, encripted = true)
	public String Password;

	@TableField(name = "Active", datatype = DATATYPE_BOOLEAN)
	public Boolean Active;
	
	
	@Override
	public String toString() {
		return String.format("%s, %s", Surname, Name);
	}
}
