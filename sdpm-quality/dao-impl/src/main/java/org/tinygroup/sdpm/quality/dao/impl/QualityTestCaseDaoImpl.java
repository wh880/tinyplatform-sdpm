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

package org.tinygroup.sdpm.quality.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.org.dao.constant.OrgUserTable;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.sdpm.quality.dao.constant.QualityTestCaseTable;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.Table;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityBugTable.QUALITY_BUGTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestCaseTable.QUALITY_TEST_CASETABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemModuleTable.SYSTEM_MODULETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class QualityTestCaseDaoImpl extends TinyDslDaoSupport implements QualityTestCaseDao {

    public QualityTestCase add(QualityTestCase qualityTestCase) {
        return getDslTemplate().insertAndReturnKey(qualityTestCase, new InsertGenerateCallback<QualityTestCase>() {
            public Insert generate(QualityTestCase t) {
                Insert insert = insertInto(QUALITY_TEST_CASETABLE).values(
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.value(t.getProductId()),
                        QUALITY_TEST_CASETABLE.MODULE_ID.value(t.getModuleId()),
                        QUALITY_TEST_CASETABLE.CASE_PATH.value(t.getCasePath()),
                        QUALITY_TEST_CASETABLE.STORY_ID.value(t.getStoryId()),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.value(t.getStoryVersion()),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.value(t.getCaseTitle()),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.value(t.getCasePrecondition()),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.value(t.getCaseKeywords()),
                        QUALITY_TEST_CASETABLE.PRIORITY.value(t.getPriority()),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.value(t.getCaseType()),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.value(t.getCaseStage()),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.value(t.getCaseRunway()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.value(t.getCaseScriptedBy()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.value(t.getCaseScriptedDate()),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.value(t.getScriptStatus()),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.value(t.getScriptLocation()),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.value(t.getCaseStatus()),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.value(t.getCaseFrequency()),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.value(t.getCaseOrder()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.value(t.getCaseOpenedBy()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.value(t.getCaseOpenedDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.value(t.getCaseLastEditedBy()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.value(t.getCaseLastEditedDate()),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.value(t.getCaseVersion()),
                        QUALITY_TEST_CASETABLE.LINK_CASE.value(t.getLinkCase()),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.value(t.getCaseFromBug()),
                        QUALITY_TEST_CASETABLE.DELETED.value(t.getDeleted()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.value(t.getCaseLastRunner()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.value(t.getCaseLastRunDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.value(t.getCaseLastRunResult()),
                        QUALITY_TEST_CASETABLE.NO.value(t.getNo()));
                return insert;
            }
        });
    }

    public int edit(final QualityTestCase qualityTestCase) {
        if (qualityTestCase == null || qualityTestCase.getCaseId() == null) {
            return 0;
        }
        return getDslTemplate().update(qualityTestCase, new UpdateGenerateCallback<QualityTestCase>() {
            public Update generate(QualityTestCase t) {
                Update update = update(QUALITY_TEST_CASETABLE).set(
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.value(t.getProductId()),
                        QUALITY_TEST_CASETABLE.MODULE_ID.value(t.getModuleId()),
                        QUALITY_TEST_CASETABLE.CASE_PATH.value(t.getCasePath()),
                        QUALITY_TEST_CASETABLE.STORY_ID.value(t.getStoryId()),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.value(t.getStoryVersion()),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.value(t.getCaseTitle()),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.value(t.getCasePrecondition()),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.value(t.getCaseKeywords()),
                        QUALITY_TEST_CASETABLE.PRIORITY.value(t.getPriority()),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.value(t.getCaseType()),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.value(t.getCaseStage()),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.value(t.getCaseRunway()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.value(t.getCaseScriptedBy()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.value(t.getCaseScriptedDate()),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.value(t.getScriptStatus()),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.value(t.getScriptLocation()),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.value(t.getCaseStatus()),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.value(t.getCaseFrequency()),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.value(t.getCaseOrder()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.value(t.getCaseOpenedBy()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.value(t.getCaseOpenedDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.value(t.getCaseLastEditedBy()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.value(t.getCaseLastEditedDate()),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.value(t.getCaseVersion()),
                        QUALITY_TEST_CASETABLE.LINK_CASE.value(t.getLinkCase()),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.value(t.getCaseFromBug()),
                        QUALITY_TEST_CASETABLE.DELETED.value(t.getDeleted()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.value(t.getCaseLastRunner()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.value(t.getCaseLastRunDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.value(t.getCaseLastRunResult()),
                        QUALITY_TEST_CASETABLE.NO.value(t.getNo())).where(QUALITY_TEST_CASETABLE.CASE_ID.eq(t.getCaseId()));
                return update;
            }
        });
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(QUALITY_TEST_CASETABLE).where(QUALITY_TEST_CASETABLE.CASE_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(QUALITY_TEST_CASETABLE).where(QUALITY_TEST_CASETABLE.CASE_ID.in(t));
            }
        }, pks);
    }

    public QualityTestCase getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, QualityTestCase.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return select(QUALITY_TEST_CASETABLE.ALL,
                        SYSTEM_MODULETABLE.MODULE_NAME.as("moduleName"),
                        PRODUCT_STORYTABLE.STORY_TITLE.as("storyTitle"),
                        QUALITY_BUGTABLE.BUG_TITLE.as("caseFromBugTitle"),
                        new Column(new Table("linkCaseTable"), "case_title").as("linkCaseTile")
                ).from(QUALITY_TEST_CASETABLE).join(
                        Join.leftJoin(SYSTEM_MODULETABLE, QUALITY_TEST_CASETABLE.MODULE_ID.eq(SYSTEM_MODULETABLE.MODULE_ID)),
                        Join.leftJoin(PRODUCT_STORYTABLE, QUALITY_TEST_CASETABLE.STORY_ID.eq(PRODUCT_STORYTABLE.STORY_ID)),
                        Join.leftJoin(QUALITY_BUGTABLE, QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(QUALITY_BUGTABLE.BUG_ID)),
                        Join.leftJoin(QUALITY_TEST_CASETABLE.as("linkCaseTable"), QUALITY_TEST_CASETABLE.LINK_CASE.eq(((QualityTestCaseTable) QUALITY_TEST_CASETABLE.as("linkCaseTable")).CASE_ID))
                ).where(QUALITY_TEST_CASETABLE.CASE_ID.eq(t));
            }
        });
    }

    public List<QualityTestCase> query(QualityTestCase qualityTestCase, final OrderBy... orderArgs) {
        if (qualityTestCase == null) {
            qualityTestCase = new QualityTestCase();
        }
        return getDslTemplate().query(qualityTestCase, new SelectGenerateCallback<QualityTestCase>() {

            @SuppressWarnings("rawtypes")
            public Select generate(QualityTestCase t) {
                Select select = selectFrom(QUALITY_TEST_CASETABLE).where(
                        and(
                                QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(t.getProductId()),
                                QUALITY_TEST_CASETABLE.MODULE_ID.eq(t.getModuleId()),
                                QUALITY_TEST_CASETABLE.CASE_PATH.eq(t.getCasePath()),
                                QUALITY_TEST_CASETABLE.STORY_ID.eq(t.getStoryId()),
                                QUALITY_TEST_CASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                QUALITY_TEST_CASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
                                QUALITY_TEST_CASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
                                QUALITY_TEST_CASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
                                QUALITY_TEST_CASETABLE.PRIORITY.eq(t.getPriority()),
                                QUALITY_TEST_CASETABLE.CASE_TYPE.eq(t.getCaseType()),
                                QUALITY_TEST_CASETABLE.CASE_STAGE.eq(t.getCaseStage()),
                                QUALITY_TEST_CASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
                                QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.eq(t.getCaseScriptedBy()),
                                QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.eq(t.getCaseScriptedDate()),
                                QUALITY_TEST_CASETABLE.SCRIPT_STATUS.eq(t.getScriptStatus()),
                                QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.eq(t.getScriptLocation()),
                                QUALITY_TEST_CASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
                                QUALITY_TEST_CASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
                                QUALITY_TEST_CASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
                                QUALITY_TEST_CASETABLE.CASE_OPENED_BY.eq(t.getCaseOpenedBy()),
                                QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.eq(t.getCaseOpenedDate()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.eq(t.getCaseLastEditedBy()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.eq(t.getCaseLastEditedDate()),
                                QUALITY_TEST_CASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
                                QUALITY_TEST_CASETABLE.LINK_CASE.eq(t.getLinkCase()),
                                QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(t.getCaseFromBug()),
                                QUALITY_TEST_CASETABLE.DELETED.eq(t.getDeleted()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.eq(t.getCaseLastRunner()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.eq(t.getCaseLastRunDate()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.eq(t.getCaseLastRunResult()),
                                QUALITY_TEST_CASETABLE.NO.eq(t.getNo())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<QualityTestCase> queryPager(int start, int limit, QualityTestCase qualityTestCase, final OrderBy... orderArgs) {
        if (qualityTestCase == null) {
            qualityTestCase = new QualityTestCase();
        }
        return getDslTemplate().queryPager(start, limit, qualityTestCase, false, new SelectGenerateCallback<QualityTestCase>() {

            public Select generate(QualityTestCase t) {
                Select select = MysqlSelect.select(QUALITY_TEST_CASETABLE.ALL,
                        ((OrgUserTable) ORG_USERTABLE.as("open")).ORG_USER_REAL_NAME.as("caseOpenedName"),
                        ((OrgUserTable) ORG_USERTABLE.as("runner")).ORG_USER_REAL_NAME.as("caseLastRunnerName")).
                        from(QUALITY_TEST_CASETABLE).join(
                        Join.leftJoin(ORG_USERTABLE.as("open"), ((OrgUserTable) ORG_USERTABLE.as("open")).ORG_USER_ID.eq(QUALITY_TEST_CASETABLE.CASE_OPENED_BY)),
                        Join.leftJoin(ORG_USERTABLE.as("runner"), ((OrgUserTable) ORG_USERTABLE.as("runner")).ORG_USER_ID.eq(QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER))
                ).where(and(

                        QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(t.getProductId()),
                        QUALITY_TEST_CASETABLE.MODULE_ID.eq(t.getModuleId()),
                        QUALITY_TEST_CASETABLE.CASE_PATH.eq(t.getCasePath()),
                        QUALITY_TEST_CASETABLE.STORY_ID.eq(t.getStoryId()),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
                        QUALITY_TEST_CASETABLE.PRIORITY.eq(t.getPriority()),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.eq(t.getCaseType()),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.eq(t.getCaseStage()),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.eq(t.getCaseScriptedBy()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.eq(t.getCaseScriptedDate()),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.eq(t.getScriptStatus()),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.eq(t.getScriptLocation()),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.eq(t.getCaseOpenedBy()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.eq(t.getCaseOpenedDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.eq(t.getCaseLastEditedBy()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.eq(t.getCaseLastEditedDate()),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
                        QUALITY_TEST_CASETABLE.LINK_CASE.eq(t.getLinkCase()),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(t.getCaseFromBug()),
                        QUALITY_TEST_CASETABLE.DELETED.eq(t.getDeleted()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.eq(t.getCaseLastRunner()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.eq(t.getCaseLastRunDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.eq(t.getCaseLastRunResult())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<QualityTestCase> queryPagerRel(int start, int limit, QualityTestCase qualityTestCase, final Condition condition, final OrderBy... orderArgs) {
        if (qualityTestCase == null) {
            qualityTestCase = new QualityTestCase();
        }
        return getDslTemplate().queryPager(start, limit, qualityTestCase, false, new SelectGenerateCallback<QualityTestCase>() {

            public Select generate(QualityTestCase t) {
                Select select = MysqlSelect.select(FragmentSelectItemSql.fragmentSelect("quality_test_case.*,o.org_user_account as caseOpenedName,o2.org_user_account as caseLastRunnerName")).
                        from(FragmentSelectItemSql.fragmentFrom("quality_test_case left join org_user o on o.org_user_id = quality_test_case.case_opened_by left join org_user o2 on o2.org_user_id = quality_test_case.case_last_runner"))
                        .where(and(

                                condition,
                                QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(t.getProductId()),
                                QUALITY_TEST_CASETABLE.MODULE_ID.eq(t.getModuleId()),
                                QUALITY_TEST_CASETABLE.CASE_PATH.eq(t.getCasePath()),
                                QUALITY_TEST_CASETABLE.STORY_ID.eq(t.getStoryId()),
                                QUALITY_TEST_CASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
                                QUALITY_TEST_CASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
                                QUALITY_TEST_CASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
                                QUALITY_TEST_CASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
                                QUALITY_TEST_CASETABLE.PRIORITY.eq(t.getPriority()),
                                QUALITY_TEST_CASETABLE.CASE_TYPE.eq(t.getCaseType()),
                                QUALITY_TEST_CASETABLE.CASE_STAGE.eq(t.getCaseStage()),
                                QUALITY_TEST_CASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
                                QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.eq(t.getCaseScriptedBy()),
                                QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.eq(t.getCaseScriptedDate()),
                                QUALITY_TEST_CASETABLE.SCRIPT_STATUS.eq(t.getScriptStatus()),
                                QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.eq(t.getScriptLocation()),
                                QUALITY_TEST_CASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
                                QUALITY_TEST_CASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
                                QUALITY_TEST_CASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
                                QUALITY_TEST_CASETABLE.CASE_OPENED_BY.eq(t.getCaseOpenedBy()),
                                QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.eq(t.getCaseOpenedDate()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.eq(t.getCaseLastEditedBy()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.eq(t.getCaseLastEditedDate()),
                                QUALITY_TEST_CASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
                                QUALITY_TEST_CASETABLE.LINK_CASE.eq(t.getLinkCase()),
                                QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(t.getCaseFromBug()),
                                QUALITY_TEST_CASETABLE.DELETED.eq(t.getDeleted()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.eq(t.getCaseLastRunner()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.eq(t.getCaseLastRunDate()),
                                QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.eq(t.getCaseLastRunResult())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<QualityTestCase> queryStoryChangedCase(int start, int limit, QualityTestCase qualityTestCase, Condition condition, OrderBy... orderArgs) {
        Select select = MysqlSelect.select(QUALITY_TEST_CASETABLE.ALL).
                from(QUALITY_TEST_CASETABLE).join(
                Join.leftJoin(
                        PRODUCT_STORYTABLE,
                        QUALITY_TEST_CASETABLE.STORY_ID.eq(PRODUCT_STORYTABLE.STORY_ID)))
                .where(and(
                        QUALITY_TEST_CASETABLE.STORY_VERSION.neq(PRODUCT_STORYTABLE.STORY_VERSION),
                        condition,
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(qualityTestCase.getProductId()),
                        QUALITY_TEST_CASETABLE.MODULE_ID.eq(qualityTestCase.getModuleId()),
                        QUALITY_TEST_CASETABLE.CASE_PATH.eq(qualityTestCase.getCasePath()),
                        QUALITY_TEST_CASETABLE.STORY_ID.eq(qualityTestCase.getStoryId()),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.eq(qualityTestCase.getStoryVersion()),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.eq(qualityTestCase.getCaseTitle()),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.eq(qualityTestCase.getCasePrecondition()),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.eq(qualityTestCase.getCaseKeywords()),
                        QUALITY_TEST_CASETABLE.PRIORITY.eq(qualityTestCase.getPriority()),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.eq(qualityTestCase.getCaseType()),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.eq(qualityTestCase.getCaseStage()),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.eq(qualityTestCase.getCaseRunway()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.eq(qualityTestCase.getCaseScriptedBy()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.eq(qualityTestCase.getCaseScriptedDate()),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.eq(qualityTestCase.getScriptStatus()),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.eq(qualityTestCase.getScriptLocation()),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.eq(qualityTestCase.getCaseStatus()),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.eq(qualityTestCase.getCaseFrequency()),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.eq(qualityTestCase.getCaseOrder()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.eq(qualityTestCase.getCaseOpenedBy()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.eq(qualityTestCase.getCaseOpenedDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.eq(qualityTestCase.getCaseLastEditedBy()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.eq(qualityTestCase.getCaseLastEditedDate()),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.eq(qualityTestCase.getCaseVersion()),
                        QUALITY_TEST_CASETABLE.LINK_CASE.eq(qualityTestCase.getLinkCase()),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(qualityTestCase.getCaseFromBug()),
                        QUALITY_TEST_CASETABLE.DELETED.eq(qualityTestCase.getDeleted()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.eq(qualityTestCase.getCaseLastRunner()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.eq(qualityTestCase.getCaseLastRunDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.eq(qualityTestCase.getCaseLastRunResult())));
        return getDslSession().fetchPage(select, start, limit, false, QualityTestCase.class);
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<QualityTestCase> qualityTestCases) {
        if (CollectionUtil.isEmpty(qualityTestCases)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestCases, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(QUALITY_TEST_CASETABLE).values(
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        QUALITY_TEST_CASETABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
                        QUALITY_TEST_CASETABLE.CASE_PATH.value(new JdbcNamedParameter("casePath")),
                        QUALITY_TEST_CASETABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.value(new JdbcNamedParameter("caseTitle")),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.value(new JdbcNamedParameter("casePrecondition")),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.value(new JdbcNamedParameter("caseKeywords")),
                        QUALITY_TEST_CASETABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.value(new JdbcNamedParameter("caseType")),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.value(new JdbcNamedParameter("caseStage")),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.value(new JdbcNamedParameter("caseRunway")),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.value(new JdbcNamedParameter("caseScriptedBy")),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.value(new JdbcNamedParameter("caseScriptedDate")),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.value(new JdbcNamedParameter("scriptStatus")),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.value(new JdbcNamedParameter("scriptLocation")),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.value(new JdbcNamedParameter("caseStatus")),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.value(new JdbcNamedParameter("caseFrequency")),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.value(new JdbcNamedParameter("caseOrder")),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.value(new JdbcNamedParameter("caseOpenedBy")),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.value(new JdbcNamedParameter("caseOpenedDate")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.value(new JdbcNamedParameter("caseLastEditedBy")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.value(new JdbcNamedParameter("caseLastEditedDate")),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
                        QUALITY_TEST_CASETABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.value(new JdbcNamedParameter("caseFromBug")),
                        QUALITY_TEST_CASETABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.value(new JdbcNamedParameter("caseLastRunner")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.value(new JdbcNamedParameter("caseLastRunDate")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.value(new JdbcNamedParameter("caseLastRunResult")));
            }
        });
    }

    public int[] batchInsert(List<QualityTestCase> qualityTestCases) {
        return batchInsert(true, qualityTestCases);
    }

    public int[] batchUpdate(List<QualityTestCase> qualityTestCases) {
        if (CollectionUtil.isEmpty(qualityTestCases)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(qualityTestCases, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(QUALITY_TEST_CASETABLE).set(
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        QUALITY_TEST_CASETABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
                        QUALITY_TEST_CASETABLE.CASE_PATH.value(new JdbcNamedParameter("casePath")),
                        QUALITY_TEST_CASETABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.value(new JdbcNamedParameter("caseTitle")),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.value(new JdbcNamedParameter("casePrecondition")),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.value(new JdbcNamedParameter("caseKeywords")),
                        QUALITY_TEST_CASETABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.value(new JdbcNamedParameter("caseType")),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.value(new JdbcNamedParameter("caseStage")),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.value(new JdbcNamedParameter("caseRunway")),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.value(new JdbcNamedParameter("caseScriptedBy")),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.value(new JdbcNamedParameter("caseScriptedDate")),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.value(new JdbcNamedParameter("scriptStatus")),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.value(new JdbcNamedParameter("scriptLocation")),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.value(new JdbcNamedParameter("caseStatus")),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.value(new JdbcNamedParameter("caseFrequency")),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.value(new JdbcNamedParameter("caseOrder")),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.value(new JdbcNamedParameter("caseOpenedBy")),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.value(new JdbcNamedParameter("caseOpenedDate")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.value(new JdbcNamedParameter("caseLastEditedBy")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.value(new JdbcNamedParameter("caseLastEditedDate")),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
                        QUALITY_TEST_CASETABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.value(new JdbcNamedParameter("caseFromBug")),
                        QUALITY_TEST_CASETABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.value(new JdbcNamedParameter("caseLastRunner")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.value(new JdbcNamedParameter("caseLastRunDate")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.value(new JdbcNamedParameter("caseLastRunResult"))).where(
                        QUALITY_TEST_CASETABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")));
            }
        });
    }

    public int[] batchDelete(List<QualityTestCase> qualityTestCases) {
        if (CollectionUtil.isEmpty(qualityTestCases)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(qualityTestCases, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(QUALITY_TEST_CASETABLE).where(and(
                        QUALITY_TEST_CASETABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        QUALITY_TEST_CASETABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
                        QUALITY_TEST_CASETABLE.CASE_PATH.eq(new JdbcNamedParameter("casePath")),
                        QUALITY_TEST_CASETABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.eq(new JdbcNamedParameter("caseTitle")),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.eq(new JdbcNamedParameter("casePrecondition")),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.eq(new JdbcNamedParameter("caseKeywords")),
                        QUALITY_TEST_CASETABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.eq(new JdbcNamedParameter("caseType")),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.eq(new JdbcNamedParameter("caseStage")),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.eq(new JdbcNamedParameter("caseRunway")),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.eq(new JdbcNamedParameter("caseScriptedBy")),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.eq(new JdbcNamedParameter("caseScriptedDate")),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.eq(new JdbcNamedParameter("scriptStatus")),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.eq(new JdbcNamedParameter("scriptLocation")),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.eq(new JdbcNamedParameter("caseStatus")),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.eq(new JdbcNamedParameter("caseFrequency")),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.eq(new JdbcNamedParameter("caseOrder")),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.eq(new JdbcNamedParameter("caseOpenedBy")),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.eq(new JdbcNamedParameter("caseOpenedDate")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.eq(new JdbcNamedParameter("caseLastEditedBy")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.eq(new JdbcNamedParameter("caseLastEditedDate")),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
                        QUALITY_TEST_CASETABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(new JdbcNamedParameter("caseFromBug")),
                        QUALITY_TEST_CASETABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.eq(new JdbcNamedParameter("caseLastRunner")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.eq(new JdbcNamedParameter("caseLastRunDate")),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.eq(new JdbcNamedParameter("caseLastRunResult"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        if (orderBies == null || orderBies.length == 0) {
            return select;
        }
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; i < orderBies.length; i++) {
            OrderByElement tempElement = null;
            if (orderBies[i] != null) {
                tempElement = orderBies[i].getOrderByElement();
            }
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }


    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(QUALITY_TEST_CASETABLE).set(
                        QUALITY_TEST_CASETABLE.DELETED.value(FieldUtil.DELETE_YES)).where(
                        QUALITY_TEST_CASETABLE.CASE_ID.eq(id));
                return update;
            }
        });

    }

    public List<Integer> getStoryIds(QualityTestCase t) {
        Select select = Select.select(QUALITY_TEST_CASETABLE.STORY_ID).from(QUALITY_TEST_CASETABLE).where(
                and(
                        QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(t.getProductId()),
                        QUALITY_TEST_CASETABLE.MODULE_ID.eq(t.getModuleId()),
                        QUALITY_TEST_CASETABLE.CASE_PATH.eq(t.getCasePath()),
                        QUALITY_TEST_CASETABLE.STORY_ID.eq(t.getStoryId()),
                        QUALITY_TEST_CASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
                        QUALITY_TEST_CASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
                        QUALITY_TEST_CASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
                        QUALITY_TEST_CASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
                        QUALITY_TEST_CASETABLE.PRIORITY.eq(t.getPriority()),
                        QUALITY_TEST_CASETABLE.CASE_TYPE.eq(t.getCaseType()),
                        QUALITY_TEST_CASETABLE.CASE_STAGE.eq(t.getCaseStage()),
                        QUALITY_TEST_CASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_BY.eq(t.getCaseScriptedBy()),
                        QUALITY_TEST_CASETABLE.CASE_SCRIPTED_DATE.eq(t.getCaseScriptedDate()),
                        QUALITY_TEST_CASETABLE.SCRIPT_STATUS.eq(t.getScriptStatus()),
                        QUALITY_TEST_CASETABLE.SCRIPT_LOCATION.eq(t.getScriptLocation()),
                        QUALITY_TEST_CASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
                        QUALITY_TEST_CASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
                        QUALITY_TEST_CASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_BY.eq(t.getCaseOpenedBy()),
                        QUALITY_TEST_CASETABLE.CASE_OPENED_DATE.eq(t.getCaseOpenedDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_BY.eq(t.getCaseLastEditedBy()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_EDITED_DATE.eq(t.getCaseLastEditedDate()),
                        QUALITY_TEST_CASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
                        QUALITY_TEST_CASETABLE.LINK_CASE.eq(t.getLinkCase()),
                        QUALITY_TEST_CASETABLE.CASE_FROM_BUG.eq(t.getCaseFromBug()),
                        QUALITY_TEST_CASETABLE.DELETED.eq(t.getDeleted()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUNNER.eq(t.getCaseLastRunner()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_DATE.eq(t.getCaseLastRunDate()),
                        QUALITY_TEST_CASETABLE.CASE_LAST_RUN_RESULT.eq(t.getCaseLastRunResult())));

        List<Integer> list = getDslSession().fetchList(select, Integer.class);
        return list;
    }

    public Integer getMaxNo(Integer productId) {
        Select select = select(QUALITY_TEST_CASETABLE.NO.max()).from(QUALITY_TEST_CASETABLE).where(QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(productId));
        return getDslSession().fetchOneResult(select, Integer.class);
    }

    public Integer deleteCaseByProduct(Integer productId) {
        Update update = update(QUALITY_TEST_CASETABLE).set(QUALITY_TEST_CASETABLE.DELETED.value(1)).where(and(QUALITY_TEST_CASETABLE.PRODUCT_ID.eq(productId), QUALITY_TEST_CASETABLE.DELETED.eq(0)));
        return getDslSession().execute(update);
    }

    public Integer deleteCaseByStory(Integer storyId) {
        Update update = update(QUALITY_TEST_CASETABLE).set(QUALITY_TEST_CASETABLE.DELETED.value(1)).where(and(QUALITY_TEST_CASETABLE.STORY_ID.eq(storyId), QUALITY_TEST_CASETABLE.DELETED.eq(0)));
        return getDslSession().execute(update);
    }
}
