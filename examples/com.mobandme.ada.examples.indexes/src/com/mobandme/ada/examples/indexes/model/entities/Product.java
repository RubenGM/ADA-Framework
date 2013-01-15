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

package com.mobandme.ada.examples.indexes.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;
import com.desandroid.framework.ada.annotations.TableIndex;

/**
 * This class represent our Entity model for this example.
 * @author   Txus Ballesteros (@DesAndrOId)
 * @category Entities
 * @version  2.3
 */

@Table(name = "Products")
public class Product extends Entity {

	private final static String TABLE_INDEX_CATEGORY_NAME  = "INX_Products_Name_Category";
	private final static String TABLE_INDEX_PRICE          = "INX_Products_Price";
	public final static  String DEFAULT_SORT               = "ProductName ASC";
	
	
	@TableField(name = "ProductName", datatype = DATATYPE_STRING, required = true, unique = true)
	@TableIndex(name = TABLE_INDEX_CATEGORY_NAME, direction = INDEX_DIRECTION_ASC) // Configuration for Database Table Index.
	public String Name;
	
	@TableField(name = "ProductCategory", datatype = DATATYPE_STRING, required = true)
	@TableIndex(name = TABLE_INDEX_CATEGORY_NAME, direction = INDEX_DIRECTION_DESC) // Configuration for Database Table Index.
	public String Category;
	
	@TableField(name = "ProductPrice", datatype = DATATYPE_REAL, required = true)
	@TableIndex(name = TABLE_INDEX_PRICE) // Configuration for Database Table Index.
	public float  Price;
	
	public Product() {}
	public Product(String pName, String pCategory, float pPrice) {
		Name = pName;
		Category = pCategory;
		Price = pPrice;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s €", Name, Float.toString(Price));
	}
}
