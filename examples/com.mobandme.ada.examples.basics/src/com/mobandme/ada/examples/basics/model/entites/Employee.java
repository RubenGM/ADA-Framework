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

package com.mobandme.ada.examples.basics.model.entites;

import java.util.Date;
import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Databinding;
import com.desandroid.framework.ada.annotations.RequiredFieldValidation;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;
import com.mobandme.ada.examples.basics.R;
import com.mobandme.ada.examples.basics.model.parsers.DateParser;

/**
 * Employee Entity.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Application Model Entity
 * @version  2.3
 */

@Table(name = "tEmployee")
public class Employee extends Entity {

	@TableField(name = "Name", datatype = DATATYPE_STRING, required = true)
	@Databinding(ViewId = R.id.employeeName)
	@RequiredFieldValidation(messageResourceId = R.string.messageEmployeeNameRequired)
	public String Name = null;
	
	@TableField(name = "Surname", datatype = DATATYPE_STRING, required = true)
	@Databinding(ViewId = R.id.employeeSurname)
	@RequiredFieldValidation(messageResourceId = R.string.messageEmployeeSurnameRequired)
	public String Surname = null;

	@TableField(name = "Birthday", datatype = DATATYPE_DATE)
	@Databinding(ViewId = R.id.employeeBirthday, parser = DateParser.class)
	public Date  Birthday = null;
	
	@TableField(name = "Active", datatype = DATATYPE_BOOLEAN)
	@Databinding(ViewId = R.id.employeeActive)
	public Boolean Active = false;

	@TableField(name = "Email", datatype = DATATYPE_STRING, required = true)
	@Databinding(ViewId = R.id.employeeEmail)
	public String Email = "";
	
	@Override
	public String toString() {
		return String.format("%s, %s", Name, Surname);
	}
}
