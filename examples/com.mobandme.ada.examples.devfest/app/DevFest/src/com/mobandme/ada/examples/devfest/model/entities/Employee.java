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

package com.mobandme.ada.examples.devfest.model.entities;

import java.util.Date;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Databinding;
import com.mobandme.ada.annotations.RequiredFieldValidation;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;
import com.mobandme.ada.examples.devfest.R;
import com.mobandme.ada.examples.devfest.model.binders.DateDataBinder;
import com.mobandme.ada.examples.devfest.model.binders.ImageDataBinder;
import com.mobandme.ada.examples.devfest.model.parsers.DateParser;

@Table(name = "Employees")
public class Employee extends Entity {

	public static final String DEFAULT_SORT = "Name ASC";
	private final String DEFAULT_BIO = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
	
	@TableField(name = "Name", datatype = DATATYPE_STRING, required = true, maxLength = 200)
	@Databinding(ViewId = R.id.Employee_Name)
	@RequiredFieldValidation(messageResourceId = R.string.requires_field_name)
	private String name = "";
	
	@TableField(name = "Biography", datatype = DATATYPE_STRING, required = true, maxLength = 2000)
	@Databinding(ViewId = R.id.Employee_Biography)
	private String biography = DEFAULT_BIO;
	
	@TableField(name = "DateOfBirth", datatype = DATATYPE_DATE_BINARY)
	@Databinding(ViewId = R.id.Employee_Birthday, parser = DateParser.class, binder = DateDataBinder.class)
	private Date   dateOfBirth;
	
	@TableField(name = "Image", datatype = DATATYPE_INTEGER, required = true)
	@Databinding(ViewId = R.id.Employee_Image, binder = ImageDataBinder.class)
	private int imageId = R.drawable.face00;
	
	
	public String getName() { return name; }

	public Employee setName(String pName) { this.name = pName; return this;}
	
	public String getBiography() { return biography; }

	public Employee setBiography(String pBiography) { this.biography = pBiography; return this;}
	
	public Date getDateOfBirth() { return dateOfBirth; }

	public Employee setDateOfBirth(Date pDateOfBirth) { this.dateOfBirth = pDateOfBirth; return this; }

	public int getImageId() { return imageId; }

	public Employee setImageId(int imageId) { this.imageId = imageId; return this; }
	
	@Override
	public String toString() {
		return name;
	}
	
	//@RegularExpressionValidation(expression = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})", messageResourceId = R.string.message_invalid_email)
}