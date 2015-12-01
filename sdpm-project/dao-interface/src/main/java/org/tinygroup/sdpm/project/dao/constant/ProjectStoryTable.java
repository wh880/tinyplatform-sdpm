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

package org.tinygroup.sdpm.project.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 项目需求
 *
 */
public class ProjectStoryTable extends Table {

    public static final ProjectStoryTable PROJECT_STORYTABLE = new ProjectStoryTable();

    /**
     * 逻辑ID
     */
    public final Column ID = new Column(this, "id");

    /**
     * 项目id
     */
    public final Column PROJECT_ID = new Column(this, "project_id");

    /**
     * 产品ID
     */
    public final Column PRODUCT_ID = new Column(this, "product_id");

    /**
     * 需求ID
     */
    public final Column STORY_ID = new Column(this, "story_id");

    /**
     * 需求版本
     */
    public final Column STORY_VERSION = new Column(this, "story_version");


    private ProjectStoryTable() {
        super("project_story");
    }

}
