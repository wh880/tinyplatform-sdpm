
package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;

import java.util.List;

public interface CompanyService {
    /**
     * 根据主键id查找公司
     *
     * @param id 主键
     * @return
     */
    OrgCompany findCompany(Integer id);

    /**
     * 新增一个公司
     *
     * @param orgCompany 新增实体类
     * @return
     */
    OrgCompany addCompany(OrgCompany orgCompany);

    /**
     * 更新公司
     *
     * @param orgCompany 需要更新的实体类
     * @return
     */
    OrgCompany updateCompany(OrgCompany orgCompany);

    /**
     * 查找表中的公司记录
     *
     * @return
     */
    List<OrgCompany> findCompanyList();

}
