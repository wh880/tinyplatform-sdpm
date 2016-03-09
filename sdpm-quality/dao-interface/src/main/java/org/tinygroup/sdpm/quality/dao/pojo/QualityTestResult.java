/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.quality.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试结果表
 */
public class QualityTestResult implements Serializable {

    /**
     * 测试结果
     */
    private Integer testResultId;

    /**
     * TESTRESULT_RUN
     */
    private Integer testresultRun;

    /**
     * 相关用例
     */
    private Integer linkCase;

    /**
     * 关联用例版本
     */
    private Integer caseVersion;

    /**
     * 测试结果
     */
    private String caseResult;

    /**
     * 用例步骤结果
     */
    private String caseStepresults;

    /**
     * 最后执行人
     */
    private String testResultLastRunner;

    /**
     * 最后执行日期
     */
    private Date testResultDate;

    public Integer getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(Integer testResultId) {
        this.testResultId = testResultId;
    }

    public Integer getTestresultRun() {
        return testresultRun;
    }

    public void setTestresultRun(Integer testresultRun) {
        this.testresultRun = testresultRun;
    }

    public Integer getLinkCase() {
        return linkCase;
    }

    public void setLinkCase(Integer linkCase) {
        this.linkCase = linkCase;
    }

    public Integer getCaseVersion() {
        return caseVersion;
    }

    public void setCaseVersion(Integer caseVersion) {
        this.caseVersion = caseVersion;
    }

    public String getCaseResult() {
        return caseResult;
    }

    public void setCaseResult(String caseResult) {
        this.caseResult = caseResult;
    }

    public String getCaseStepresults() {
        return caseStepresults;
    }

    public void setCaseStepresults(String caseStepresults) {
        this.caseStepresults = caseStepresults;
    }

    public String getTestResultLastRunner() {
        return testResultLastRunner;
    }

    public void setTestResultLastRunner(String testResultLastRunner) {
        this.testResultLastRunner = testResultLastRunner;
    }

    public Date getTestResultDate() {
        return testResultDate;
    }

    public void setTestResultDate(Date testResultDate) {
        this.testResultDate = testResultDate;
    }

}
