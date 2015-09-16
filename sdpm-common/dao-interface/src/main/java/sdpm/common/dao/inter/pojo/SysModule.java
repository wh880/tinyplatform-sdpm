/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package sdpm.common.dao.inter.pojo;


public class SysModule {

	/** 模块ID */
	private Integer sysModuleId;

	/** 模块根节点 */
	private Integer sysModuleRoot;

	/** 模块名称 */
	private String sysModuleName;

	/** 模块地址 */
	private String sysModulePath;

	/** 模块父节点 */
	private Integer sysModuleParent;

	/** 模块等级 */
	private Integer sysModuleGrade;

	/** 模块次序 */
	private Integer sysModuleOrder;

	/** 模块类型 */
	private String sysModuleType;

	/** 模块归属 */
	private String sysModuleOwner;


	public void setSysModuleId(Integer sysModuleId){
		this. sysModuleId = sysModuleId;
	}

	public Integer getSysModuleId(){
		return sysModuleId;
	}

	public void setSysModuleRoot(Integer sysModuleRoot){
		this. sysModuleRoot = sysModuleRoot;
	}

	public Integer getSysModuleRoot(){
		return sysModuleRoot;
	}

	public void setSysModuleName(String sysModuleName){
		this. sysModuleName = sysModuleName;
	}

	public String getSysModuleName(){
		return sysModuleName;
	}

	public void setSysModulePath(String sysModulePath){
		this. sysModulePath = sysModulePath;
	}

	public String getSysModulePath(){
		return sysModulePath;
	}

	public void setSysModuleParent(Integer sysModuleParent){
		this. sysModuleParent = sysModuleParent;
	}

	public Integer getSysModuleParent(){
		return sysModuleParent;
	}

	public void setSysModuleGrade(Integer sysModuleGrade){
		this. sysModuleGrade = sysModuleGrade;
	}

	public Integer getSysModuleGrade(){
		return sysModuleGrade;
	}

	public void setSysModuleOrder(Integer sysModuleOrder){
		this. sysModuleOrder = sysModuleOrder;
	}

	public Integer getSysModuleOrder(){
		return sysModuleOrder;
	}

	public void setSysModuleType(String sysModuleType){
		this. sysModuleType = sysModuleType;
	}

	public String getSysModuleType(){
		return sysModuleType;
	}

	public void setSysModuleOwner(String sysModuleOwner){
		this. sysModuleOwner = sysModuleOwner;
	}

	public String getSysModuleOwner(){
		return sysModuleOwner;
	}

}
