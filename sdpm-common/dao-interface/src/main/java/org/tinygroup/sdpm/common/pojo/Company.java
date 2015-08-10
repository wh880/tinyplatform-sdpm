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

package org.tinygroup.sdpm.common.pojo;


public class Company {

	/** 公司ID */
	private Integer companyId;

	/** 公司名称 */
	private String companyName;

	/** 公司电话 */
	private String companyPhone;

	/** 公司传真 */
	private String companyFax;

	/** 公司地址 */
	private String companyAddress;

	/** 公司邮编 */
	private String companyZipcode;

	/** 公司网站 */
	private String companyWebsite;

	/** 公司网络社区 */
	private String companyHome;

	/** 公司微博 */
	private String companyWeibo;

	/** 管理员列表 */
	private String admins;

	/** 删除标记 */
	private Integer deleted;


	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setCompanyName(String companyName){
		this. companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setCompanyPhone(String companyPhone){
		this. companyPhone = companyPhone;
	}

	public String getCompanyPhone(){
		return companyPhone;
	}

	public void setCompanyFax(String companyFax){
		this. companyFax = companyFax;
	}

	public String getCompanyFax(){
		return companyFax;
	}

	public void setCompanyAddress(String companyAddress){
		this. companyAddress = companyAddress;
	}

	public String getCompanyAddress(){
		return companyAddress;
	}

	public void setCompanyZipcode(String companyZipcode){
		this. companyZipcode = companyZipcode;
	}

	public String getCompanyZipcode(){
		return companyZipcode;
	}

	public void setCompanyWebsite(String companyWebsite){
		this. companyWebsite = companyWebsite;
	}

	public String getCompanyWebsite(){
		return companyWebsite;
	}

	public void setCompanyHome(String companyHome){
		this. companyHome = companyHome;
	}

	public String getCompanyHome(){
		return companyHome;
	}

	public void setCompanyWeibo(String companyWeibo){
		this. companyWeibo = companyWeibo;
	}

	public String getCompanyWeibo(){
		return companyWeibo;
	}

	public void setAdmins(String admins){
		this. admins = admins;
	}

	public String getAdmins(){
		return admins;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
