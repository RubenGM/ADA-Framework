ADAFramework
============

Android Data Abstraction Framework, Simplify your code, Simplify your life

ADA Framework is the first library designed to simplify the source code of your native Android applications.

**See http://adaframework.com/ for more information.**


Support for Android, version 2.2 or later..


Support for Data Model Autogeneration, synchronize and generate automatically from the entities data model.


Data Model Abstraction, separate your model from your code.


Data Model Update, supports automatic data model merging between versions.


Support for Dependencies, between entities.


Support for Inheritances, between entities.


Support for Relationships, between entities.


Support for Encryption, encrypt the value of your selected database fields, based on a masterkey passphrase.


Support for Backups, and restores of your application database.


Support for Databinding, with bidirectional databinding between UI and Entities.


Support for Data Validation, automatic and customizable data validations.


Support for Database Field Indexing for advanced high performance tunning.


Support for Lazy Loading.


Changelog
=========

v2.4

	Added two new fill methods with extra parameters.
	Added a new data type named DATATYPE_DATE_BINARY, this new type, save the Date values into a numeric field into the Database.
	Deprecated the old type named DATATYPE_DATE, because now, you can use the new date type named DATATYPE_DATE_BINARY.
	Improved performance when using DATATYPE_ENTITY_LINK.
	Solved the problem with the concurrents save processes.
	Solved the problem between Entity classes and Gson library serialization.
	
v2.3.1

	Fixed model generation issue.
   
v2.3

	Added new annotations to configure a custom database table indexes.

v 2.2 	

	Added support to use Lazy Loading techniques.
 	Added new DataType named DATA_TYPE_ENTITY_LINK, this type suport relations 1 to N between Entities and Master Entities.
 	Fixed some issues.

v 2.1.1	

	Fixed issue with deleting process and getter methods.

v 2.1.0	

	Added a new method to ObjectContext class, named restoreFromAssets for restore database file from application assets.

v 2.0.0	

	New feature, auto model merging, Now the library is able to automatically update the data model between versions.
 	Added a new method to ObjectSet class, named removeAll for remove all registries of a table.
 	Added a new property to Entity base class, named getParent, to retrieve the entity parent from the current entity.
 	Added a new DataType to the Entities to manage Geolocations.

v 1.4.5	

	Fixed issue with entities properties of type DATATYPE_ENTITY and the getters and setters.

v 1.4.4	

	Added ObjectSetConfiguration annotation.

v 1.4.3	

	Fixed issue with the onPopulate method into the ObjectContext.

v 1.4.2	

	Fixed issue with getters and setters into ObjectContext and ObjectSet definitions.
 	Added 'parser' parameter into @Databinding annotation.

v 1.4.1	

	Fixed issues with getters and setters.
 	Added 'virtual' parameter into @TableField annotation.

v 1.4	Added validation capabilities.

v 1.3	Added databinding capabilities.



Developed By
============

* Txus Ballesteros - @DesAndrOId, <txus.ballesteros@mobandme.com>



License (LGPL Lesser General Public License)
=======

   Copyright Mob&Me 2012 (@MobAndMe)

   Licensed under the LGPL Lesser General Public License, Version 3.0 (the "License"),  
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.gnu.org/licenses/lgpl.html

   Unless required by applicable law or agreed to in writing, software 
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
