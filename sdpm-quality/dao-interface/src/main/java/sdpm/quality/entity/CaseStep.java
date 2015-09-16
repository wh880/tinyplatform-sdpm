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

package sdpm.quality.entity;

public class CaseStep {

	/** 用例步骤编号 */
	private Integer caseStepID;

	/** 相关用例 */
	private String caseLink;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 描述 */
	private String caseStepDesc;

	/** EXPECT */
	private String caseStepExpect;


	public void setCaseStepID(Integer caseStepID){
		this. caseStepID = caseStepID;
	}

	public Integer getCaseStepID(){
		return caseStepID;
	}

	public void setCaseLink(String caseLink){
		this. caseLink = caseLink;
	}

	public String getCaseLink(){
		return caseLink;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setCaseStepDesc(String caseStepDesc){
		this. caseStepDesc = caseStepDesc;
	}

	public String getCaseStepDesc(){
		return caseStepDesc;
	}

	public void setCaseStepExpect(String caseStepExpect){
		this. caseStepExpect = caseStepExpect;
	}

	public String getCaseStepExpect(){
		return caseStepExpect;
	}

}
