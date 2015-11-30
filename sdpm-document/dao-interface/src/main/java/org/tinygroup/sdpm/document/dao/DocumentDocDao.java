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

package org.tinygroup.sdpm.document.dao;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.tinysqldsl.Pager;

public interface DocumentDocDao extends BaseDao<DocumentDoc,Integer> {
	
	int[] batchUpdateDel(List<DocumentDoc> documentDocs);
	
	Pager<DocumentDoc> complexQuery(int start, int limit, DocumentDoc doc, final String condition,SearchInfos conditions,String groupOperate, final OrderBy... orderBys);

	int editDoc(DocumentDoc documentDoc);
}
