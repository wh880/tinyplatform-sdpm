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

package sdpm.common.dao.impl.a.b.c;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.common.dao.inter.constant.FileTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import sdpm.common.dao.inter.pojo.File;
import sdpm.common.dao.inter.FileDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class FileDaoImpl extends TinyDslDaoSupport implements FileDao {

	public File add(File file) {
		return getDslTemplate().insertAndReturnKey(file, new InsertGenerateCallback<File>() {
			public Insert generate(File t) {
				Insert insert = insertInto(FILETABLE).values(
					FILETABLE.FILE_ID.value(t.getFileId()),
					FILETABLE.FILE_PATHNAME.value(t.getFilePathname()),
					FILETABLE.FILE_TITLE.value(t.getFileTitle()),
					FILETABLE.FILE_EXTENSION.value(t.getFileExtension()),
					FILETABLE.FILE_SIZE.value(t.getFileSize()),
					FILETABLE.FILE_OBJECTTYPE.value(t.getFileObjectType()),
					FILETABLE.FILE_OBJECTID.value(t.getFileObjectID()),
					FILETABLE.FILE_ADDEDBY.value(t.getFileAddedBy()),
					FILETABLE.FILE_ADDEDDATE.value(t.getFileAddedDate()),
					FILETABLE.FILE_DOWNLOADS.value(t.getFileDownloads()),
					FILETABLE.FILE_EXTRA.value(t.getFileExtra()),
					FILETABLE.FILE_DELETED.value(t.getFileDeleted()));
				return insert;
			}
		});
	}

	public int edit(File file) {
		if(file == null || file.getFileId() == null){
			return 0;
		}
		return getDslTemplate().update(file, new UpdateGenerateCallback<File>() {
			public Update generate(File t) {
				Update update = update(FILETABLE).set(
					FILETABLE.FILE_PATHNAME.value(t.getFilePathname()),
					FILETABLE.FILE_TITLE.value(t.getFileTitle()),
					FILETABLE.FILE_EXTENSION.value(t.getFileExtension()),
					FILETABLE.FILE_SIZE.value(t.getFileSize()),
					FILETABLE.FILE_OBJECTTYPE.value(t.getFileObjectType()),
					FILETABLE.FILE_OBJECTID.value(t.getFileObjectID()),
					FILETABLE.FILE_ADDEDBY.value(t.getFileAddedBy()),
					FILETABLE.FILE_ADDEDDATE.value(t.getFileAddedDate()),
					FILETABLE.FILE_DOWNLOADS.value(t.getFileDownloads()),
					FILETABLE.FILE_EXTRA.value(t.getFileExtra()),
					FILETABLE.FILE_DELETED.value(t.getFileDeleted())).where(
					FILETABLE.FILE_ID.eq(t.getFileId()));
				return update;
			}
		});
	}

	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(FILETABLE).where(FILETABLE.FILE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(FILETABLE).where(FILETABLE.FILE_ID.in(t));
		}
		},pks);
	}

	public File getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, File.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(FILETABLE).where(FILETABLE.FILE_ID.eq(t));
			}
		});
	}

	public List<File> query(File file) {
		if(file==null){
			file=new File();
		}
		return getDslTemplate().query(file, new SelectGenerateCallback<File>() {

			@SuppressWarnings("rawtypes")
			public Select generate(File t) {
				return selectFrom(FILETABLE).where(
				and(
					FILETABLE.FILE_PATHNAME.eq(t.getFilePathname()),
					FILETABLE.FILE_TITLE.eq(t.getFileTitle()),
					FILETABLE.FILE_EXTENSION.eq(t.getFileExtension()),
					FILETABLE.FILE_SIZE.eq(t.getFileSize()),
					FILETABLE.FILE_OBJECTTYPE.eq(t.getFileObjectType()),
					FILETABLE.FILE_OBJECTID.eq(t.getFileObjectID()),
					FILETABLE.FILE_ADDEDBY.eq(t.getFileAddedBy()),
					FILETABLE.FILE_ADDEDDATE.eq(t.getFileAddedDate()),
					FILETABLE.FILE_DOWNLOADS.eq(t.getFileDownloads()),
					FILETABLE.FILE_EXTRA.eq(t.getFileExtra()),
					FILETABLE.FILE_DELETED.eq(t.getFileDeleted())));
			}
		});
	}

	public Pager<File> queryPager(int start,int limit ,File file) {
		if(file==null){
			file=new File();
		}
		return getDslTemplate().queryPager(start, limit, file, false, new SelectGenerateCallback<File>() {

			public Select generate(File t) {
				return MysqlSelect.selectFrom(FILETABLE).where(
				and(
					FILETABLE.FILE_PATHNAME.eq(t.getFilePathname()),
					FILETABLE.FILE_TITLE.eq(t.getFileTitle()),
					FILETABLE.FILE_EXTENSION.eq(t.getFileExtension()),
					FILETABLE.FILE_SIZE.eq(t.getFileSize()),
					FILETABLE.FILE_OBJECTTYPE.eq(t.getFileObjectType()),
					FILETABLE.FILE_OBJECTID.eq(t.getFileObjectID()),
					FILETABLE.FILE_ADDEDBY.eq(t.getFileAddedBy()),
					FILETABLE.FILE_ADDEDDATE.eq(t.getFileAddedDate()),
					FILETABLE.FILE_DOWNLOADS.eq(t.getFileDownloads()),
					FILETABLE.FILE_EXTRA.eq(t.getFileExtra()),
					FILETABLE.FILE_DELETED.eq(t.getFileDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<File> files) {
		if (CollectionUtil.isEmpty(files)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, files, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(FILETABLE).values(
					FILETABLE.FILE_PATHNAME.value(new JdbcNamedParameter("filePathname")),
					FILETABLE.FILE_TITLE.value(new JdbcNamedParameter("fileTitle")),
					FILETABLE.FILE_EXTENSION.value(new JdbcNamedParameter("fileExtension")),
					FILETABLE.FILE_SIZE.value(new JdbcNamedParameter("fileSize")),
					FILETABLE.FILE_OBJECTTYPE.value(new JdbcNamedParameter("fileObjectType")),
					FILETABLE.FILE_OBJECTID.value(new JdbcNamedParameter("fileObjectID")),
					FILETABLE.FILE_ADDEDBY.value(new JdbcNamedParameter("fileAddedBy")),
					FILETABLE.FILE_ADDEDDATE.value(new JdbcNamedParameter("fileAddedDate")),
					FILETABLE.FILE_DOWNLOADS.value(new JdbcNamedParameter("fileDownloads")),
					FILETABLE.FILE_EXTRA.value(new JdbcNamedParameter("fileExtra")),
					FILETABLE.FILE_DELETED.value(new JdbcNamedParameter("fileDeleted")));
			}
		});
	}

	public int[] batchInsert(List<File> files){
			return batchInsert(true ,files);
	}

	public int[] batchUpdate(List<File> files) {
		if (CollectionUtil.isEmpty(files)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(files, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(FILETABLE).set(
					FILETABLE.FILE_PATHNAME.value(new JdbcNamedParameter("filePathname")),
					FILETABLE.FILE_TITLE.value(new JdbcNamedParameter("fileTitle")),
					FILETABLE.FILE_EXTENSION.value(new JdbcNamedParameter("fileExtension")),
					FILETABLE.FILE_SIZE.value(new JdbcNamedParameter("fileSize")),
					FILETABLE.FILE_OBJECTTYPE.value(new JdbcNamedParameter("fileObjectType")),
					FILETABLE.FILE_OBJECTID.value(new JdbcNamedParameter("fileObjectID")),
					FILETABLE.FILE_ADDEDBY.value(new JdbcNamedParameter("fileAddedBy")),
					FILETABLE.FILE_ADDEDDATE.value(new JdbcNamedParameter("fileAddedDate")),
					FILETABLE.FILE_DOWNLOADS.value(new JdbcNamedParameter("fileDownloads")),
					FILETABLE.FILE_EXTRA.value(new JdbcNamedParameter("fileExtra")),
					FILETABLE.FILE_DELETED.value(new JdbcNamedParameter("fileDeleted"))).where(
				FILETABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")));
			}
		});
	}

	public int[] batchDelete(List<File> files) {
		if (CollectionUtil.isEmpty(files)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(files, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(FILETABLE).where(and(
				FILETABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")),
				FILETABLE.FILE_PATHNAME.eq(new JdbcNamedParameter("filePathname")),
				FILETABLE.FILE_TITLE.eq(new JdbcNamedParameter("fileTitle")),
				FILETABLE.FILE_EXTENSION.eq(new JdbcNamedParameter("fileExtension")),
				FILETABLE.FILE_SIZE.eq(new JdbcNamedParameter("fileSize")),
				FILETABLE.FILE_OBJECTTYPE.eq(new JdbcNamedParameter("fileObjectType")),
				FILETABLE.FILE_OBJECTID.eq(new JdbcNamedParameter("fileObjectID")),
				FILETABLE.FILE_ADDEDBY.eq(new JdbcNamedParameter("fileAddedBy")),
				FILETABLE.FILE_ADDEDDATE.eq(new JdbcNamedParameter("fileAddedDate")),
				FILETABLE.FILE_DOWNLOADS.eq(new JdbcNamedParameter("fileDownloads")),
				FILETABLE.FILE_EXTRA.eq(new JdbcNamedParameter("fileExtra")),
				FILETABLE.FILE_DELETED.eq(new JdbcNamedParameter("fileDeleted"))));
			}
		});
	}

}
