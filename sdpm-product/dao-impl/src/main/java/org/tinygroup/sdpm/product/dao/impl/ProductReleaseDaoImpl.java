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

package org.tinygroup.sdpm.product.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.product.dao.constant.ProductPlanTable.PRODUCT_PLANTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductReleaseTable.*;
import static org.tinygroup.sdpm.product.dao.constant.ProductTable.*;
import static org.tinygroup.sdpm.product.dao.constant.ProductStorySpecTable.PRODUCT_STORY_SPECTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectBuildTable.PROJECT_BUILDTABLE;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.common.util.update.InsertUtil;
import org.tinygroup.sdpm.common.util.update.UpdateUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.ProductReleaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@Repository
public class ProductReleaseDaoImpl extends TinyDslDaoSupport implements
		ProductReleaseDao {

	public ProductRelease add(final ProductRelease productRelease) {
		return getDslTemplate().insertAndReturnKey(productRelease,
				new InsertGenerateCallback<ProductRelease>() {
					public Insert generate(ProductRelease t) {
						Insert insert = InsertUtil.getInsert(PRODUCT_RELEASETABLE, productRelease);/*insertInto(PRODUCT_RELEASETABLE)
								.values(PRODUCT_RELEASETABLE.RELEASE_ID.value(t
										.getReleaseId()),
										PRODUCT_RELEASETABLE.PRODUCT_ID.value(t
												.getProductId()),
										PRODUCT_RELEASETABLE.BUILD_ID.value(t
												.getBuildId()),
										PRODUCT_RELEASETABLE.RELEASE_NAME
												.value(t.getReleaseName()),
										PRODUCT_RELEASETABLE.RELEASE_DATE
												.value(t.getReleaseDate()),
										PRODUCT_RELEASETABLE.RELEASE_STORIES
												.value(t.getReleaseStories()),
										PRODUCT_RELEASETABLE.RELEASE_BUGS
												.value(t.getReleaseBugs()),
										PRODUCT_RELEASETABLE.RELEASE_DESC
												.value(t.getReleaseDesc()),
										PRODUCT_RELEASETABLE.DELETED.value(t
												.getDeleted()));*/
						return insert;
					}
				});
	}

	public int edit(ProductRelease productRelease) {
		if (productRelease == null || productRelease.getReleaseId() == null) {
			return 0;
		}
		return getDslTemplate().update(productRelease,
				new UpdateGenerateCallback<ProductRelease>() {
					public Update generate(ProductRelease t) {
						Update update = UpdateUtil.getUpdate(
								PRODUCT_RELEASETABLE, t);
						return update;
					}
				});
	}

	public int deleteByKey(Integer pk) {
		if (pk == null) {
			return 0;
		}
		return getDslTemplate().deleteByKey(pk,
				new DeleteGenerateCallback<Serializable>() {
					public Delete generate(Serializable pk) {
						return delete(PRODUCT_RELEASETABLE).where(
								PRODUCT_RELEASETABLE.RELEASE_ID.eq(pk));
					}
				});
	}

	public int deleteByKeys(Integer... pks) {
		if (pks == null || pks.length == 0) {
			return 0;
		}
		return getDslTemplate().deleteByKeys(
				new DeleteGenerateCallback<Serializable[]>() {
					public Delete generate(Serializable[] t) {
						return delete(PRODUCT_RELEASETABLE).where(
								PRODUCT_RELEASETABLE.RELEASE_ID.in(t));
					}
				}, pks);
	}

	public List<ProductRelease> getByKeys(Integer... pk) {

		SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable[] t) {

				return MysqlSelect.select(
						PRODUCT_RELEASETABLE.ALL,PRODUCTTABLE.PRODUCT_NAME.as("productName"),PROJECT_BUILDTABLE.BUILD_NAME.as("buildName")).
						from(PRODUCT_RELEASETABLE).join(Join.leftJoin(PROJECT_BUILDTABLE, PRODUCT_RELEASETABLE.BUILD_ID.eq(PROJECT_BUILDTABLE.BUILD_ID)),
						Join.leftJoin(PRODUCTTABLE,PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_RELEASETABLE.PRODUCT_ID))).where(
						PRODUCT_RELEASETABLE.RELEASE_ID.in(t));
			}

		};
		Select select = callback.generate(pk);
		return getDslSession().fetchList(select, ProductRelease.class);
	}

	public Integer getMaxNo(Integer productId) {
		Select select = select(PRODUCT_RELEASETABLE.NO.max()).from(PRODUCT_RELEASETABLE).where(PRODUCT_RELEASETABLE.PRODUCT_ID.eq(productId));
		return getDslSession().fetchOneResult(select,Integer.class);
	}

	public ProductRelease getByKey(Integer pk) {
		try {
			return getDslTemplate().getByKey(pk, ProductRelease.class,
					new SelectGenerateCallback<Serializable>() {
						@SuppressWarnings("rawtypes")
						public Select generate(Serializable t) {
							return MysqlSelect.select(
									PRODUCT_RELEASETABLE.ALL,PRODUCT_RELEASETABLE.ALL,PRODUCTTABLE.PRODUCT_NAME.as("productName"),PROJECT_BUILDTABLE.BUILD_NAME.as("buildName")).
									from(PRODUCT_RELEASETABLE).join(Join.leftJoin(PROJECT_BUILDTABLE, PRODUCT_RELEASETABLE.BUILD_ID.eq(PROJECT_BUILDTABLE.BUILD_ID)),
									Join.leftJoin(PRODUCTTABLE,PRODUCTTABLE.PRODUCT_ID.eq(PRODUCT_RELEASETABLE.PRODUCT_ID))).where(
									PRODUCT_RELEASETABLE.RELEASE_ID.eq(t));
						}
					});
		} catch (EmptyResultDataAccessException e) {

			return null;
		}

	}

	public List<ProductRelease> query(ProductRelease productRelease,
			final OrderBy... orderArgs) {
		if (productRelease == null) {
			productRelease = new ProductRelease();
		}
		return getDslTemplate().query(productRelease,
				new SelectGenerateCallback<ProductRelease>() {

					@SuppressWarnings("rawtypes")
					public Select generate(ProductRelease t) {
						Select select = selectFrom(PRODUCT_RELEASETABLE).where(
								and(PRODUCT_RELEASETABLE.PRODUCT_ID.eq(t
										.getProductId()),
										PRODUCT_RELEASETABLE.BUILD_ID.eq(t
												.getBuildId()),
										PRODUCT_RELEASETABLE.RELEASE_NAME.eq(t
												.getReleaseName()),
										PRODUCT_RELEASETABLE.RELEASE_DATE.eq(t
												.getReleaseDate()),
										PRODUCT_RELEASETABLE.RELEASE_STORIES
												.eq(t.getReleaseStories()),
										PRODUCT_RELEASETABLE.RELEASE_BUGS.eq(t
												.getReleaseBugs()),
										PRODUCT_RELEASETABLE.RELEASE_DESC.eq(t
												.getReleaseDesc()),
										PRODUCT_RELEASETABLE.DELETED.eq(t
												.getDeleted())));
						return addOrderByElements(select, orderArgs);
					}
				});
	}

	public Pager<ProductRelease> queryPager(int start, int limit,
			ProductRelease productRelease, final OrderBy... orderArgs) {
		if (productRelease == null) {
			productRelease = new ProductRelease();
		}
		return getDslTemplate().queryPager(start>0?start:0, limit, productRelease, false,
				new SelectGenerateCallback<ProductRelease>() {

					public Select generate(ProductRelease t) {
						Select select = MysqlSelect.select(
								PRODUCT_RELEASETABLE.ALL,PROJECT_BUILDTABLE.BUILD_NAME.as("buildName")).
						from(PRODUCT_RELEASETABLE).join(Join.leftJoin(PROJECT_BUILDTABLE,PRODUCT_RELEASETABLE.BUILD_ID.eq(PROJECT_BUILDTABLE.BUILD_ID))).where(
								and(PRODUCT_RELEASETABLE.PRODUCT_ID.eq(t
												.getProductId()),
										PRODUCT_RELEASETABLE.BUILD_ID.eq(t
												.getBuildId()),
										PRODUCT_RELEASETABLE.RELEASE_NAME.eq(t
												.getReleaseName()),
										PRODUCT_RELEASETABLE.RELEASE_DATE.eq(t
												.getReleaseDate()),
										PRODUCT_RELEASETABLE.RELEASE_STORIES
												.eq(t.getReleaseStories()),
										PRODUCT_RELEASETABLE.RELEASE_BUGS.eq(t
												.getReleaseBugs()),
										PRODUCT_RELEASETABLE.RELEASE_DESC.eq(t
												.getReleaseDesc()),
										PRODUCT_RELEASETABLE.DELETED.eq(t
												.getDeleted())));
						return addOrderByElements(select, orderArgs);
					}
				});
	}

	public int[] batchInsert(boolean autoGeneratedKeys,
			List<ProductRelease> productReleases) {
		if (CollectionUtil.isEmpty(productReleases)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, productReleases,
				new NoParamInsertGenerateCallback() {

					public Insert generate() {
						return insertInto(PRODUCT_RELEASETABLE)
								.values(PRODUCT_RELEASETABLE.PRODUCT_ID
										.value(new JdbcNamedParameter(
												"productId")),
										PRODUCT_RELEASETABLE.BUILD_ID
												.value(new JdbcNamedParameter(
														"buildId")),
										PRODUCT_RELEASETABLE.RELEASE_NAME
												.value(new JdbcNamedParameter(
														"releaseName")),
										PRODUCT_RELEASETABLE.RELEASE_DATE
												.value(new JdbcNamedParameter(
														"releaseDate")),
										PRODUCT_RELEASETABLE.RELEASE_STORIES
												.value(new JdbcNamedParameter(
														"releaseStories")),
										PRODUCT_RELEASETABLE.RELEASE_BUGS
												.value(new JdbcNamedParameter(
														"releaseBugs")),
										PRODUCT_RELEASETABLE.RELEASE_DESC
												.value(new JdbcNamedParameter(
														"releaseDesc")),
										PRODUCT_RELEASETABLE.DELETED
												.value(new JdbcNamedParameter(
														"deleted")));
					}
				});
	}

	public int[] batchInsert(List<ProductRelease> productReleases) {
		return batchInsert(true, productReleases);
	}

	public int[] batchUpdate(List<ProductRelease> productReleases) {
		if (CollectionUtil.isEmpty(productReleases)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(productReleases,
				new NoParamUpdateGenerateCallback() {

					public Update generate() {
						return update(PRODUCT_RELEASETABLE)
								.set(PRODUCT_RELEASETABLE.PRODUCT_ID
										.value(new JdbcNamedParameter(
												"productId")),
										PRODUCT_RELEASETABLE.BUILD_ID
												.value(new JdbcNamedParameter(
														"buildId")),
										PRODUCT_RELEASETABLE.RELEASE_NAME
												.value(new JdbcNamedParameter(
														"releaseName")),
										PRODUCT_RELEASETABLE.RELEASE_DATE
												.value(new JdbcNamedParameter(
														"releaseDate")),
										PRODUCT_RELEASETABLE.RELEASE_STORIES
												.value(new JdbcNamedParameter(
														"releaseStories")),
										PRODUCT_RELEASETABLE.RELEASE_BUGS
												.value(new JdbcNamedParameter(
														"releaseBugs")),
										PRODUCT_RELEASETABLE.RELEASE_DESC
												.value(new JdbcNamedParameter(
														"releaseDesc")),
										PRODUCT_RELEASETABLE.DELETED
												.value(new JdbcNamedParameter(
														"deleted"))).where(
										PRODUCT_RELEASETABLE.RELEASE_ID
												.eq(new JdbcNamedParameter(
														"releaseId")));
					}
				});
	}

	public int[] batchDelete(List<ProductRelease> productReleases) {
		if (CollectionUtil.isEmpty(productReleases)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(productReleases,
				new NoParamDeleteGenerateCallback() {

					public Delete generate() {
						return delete(PRODUCT_RELEASETABLE)
								.where(and(
										PRODUCT_RELEASETABLE.RELEASE_ID
												.eq(new JdbcNamedParameter(
														"releaseId")),
										PRODUCT_RELEASETABLE.PRODUCT_ID
												.eq(new JdbcNamedParameter(
														"productId")),
										PRODUCT_RELEASETABLE.BUILD_ID
												.eq(new JdbcNamedParameter(
														"buildId")),
										PRODUCT_RELEASETABLE.RELEASE_NAME
												.eq(new JdbcNamedParameter(
														"releaseName")),
										PRODUCT_RELEASETABLE.RELEASE_DATE
												.eq(new JdbcNamedParameter(
														"releaseDate")),
										PRODUCT_RELEASETABLE.RELEASE_STORIES
												.eq(new JdbcNamedParameter(
														"releaseStories")),
										PRODUCT_RELEASETABLE.RELEASE_BUGS
												.eq(new JdbcNamedParameter(
														"releaseBugs")),
										PRODUCT_RELEASETABLE.RELEASE_DESC
												.eq(new JdbcNamedParameter(
														"releaseDesc")),
										PRODUCT_RELEASETABLE.DELETED
												.eq(new JdbcNamedParameter(
														"deleted"))));
					}
				});
	}

	private Select addOrderByElements(Select select, OrderBy... orderBies) {
		if (orderBies == null || orderBies.length == 0) {
			return select;
		}
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies != null && i < orderBies.length; i++) {
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
		return getDslTemplate().update(id,
				new UpdateGenerateCallback<Integer>() {
					public Update generate(Integer id) {
						Update update = update(PRODUCT_RELEASETABLE).set(
								PRODUCT_RELEASETABLE.DELETED
										.value(FieldUtil.DELETE_YES)).where(
								PRODUCT_RELEASETABLE.RELEASE_ID.eq(id));
						return update;
					}
				});

	}

	public static Condition releasePueryCondition(ProductRelease t) {
		return t == null ? null : and(
				PRODUCT_RELEASETABLE.PRODUCT_ID.eq(t.getProductId()),
				PRODUCT_RELEASETABLE.BUILD_ID.eq(t.getBuildId()),
				PRODUCT_RELEASETABLE.RELEASE_NAME.eq(t.getReleaseName()),
				PRODUCT_RELEASETABLE.RELEASE_DATE.eq(t.getReleaseDate()),
				PRODUCT_RELEASETABLE.RELEASE_STORIES.eq(t.getReleaseStories()),
				PRODUCT_RELEASETABLE.RELEASE_BUGS.eq(t.getReleaseBugs()),
				PRODUCT_RELEASETABLE.RELEASE_DESC.eq(t.getReleaseDesc()),
				PRODUCT_RELEASETABLE.DELETED.eq(t.getDeleted()));
	}
}
