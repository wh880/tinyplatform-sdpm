package org.tinygroup.sdpm.action.document;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/a/document")
public class DocAction extends BaseController{
	@Autowired
	private DocService docservice;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ProjectProductService projectProductService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private UserService userService;
	
	/*
	 * 主页action。session中存放了文档库id（documentLibId）
	 */
	@RequestMapping("")
	public String docIndex(DocumentDoclib doclib,HttpServletRequest request,Model model,String change,String docChange,String tree, String moduleId)
	{	
		
		List<DocumentDoclib> list=docservice.findDoclibList(new DocumentDoclib());		

		
		if(list.size()>0&&!("true".equals(change))&&!("true".equals(docChange))&&!("true".equals(tree))){
			if(null==request.getSession().getAttribute("documentLibId")||doclib.getDocLibId()==null){
				request.getSession().setAttribute("documentLibId",list.get(0).getDocLibId());
			}else {
				request.getSession().setAttribute("documentLibId",doclib.getDocLibId());
			}
		}	
		
		OrgUser user = new OrgUser();		
		List<OrgUser> userList = userService.findUserList(user);
		
		Product product = new Product();
		product.setDeleted(0);
		List<Product> productList = productService.findProductList(product);
		
		Project project = new Project();
		project.setProjectDeleted("0");
		List<Project> projectList = projectService.findProjectList(project, null, null);
		
        List<SystemModule> moduleList = moduleService.findModuleList(new SystemModule());
        
        DocumentDoclib lib = new DocumentDoclib();
        lib.setDocLibDeleted("0");
        List<DocumentDoclib> libList = docservice.findDoclibList(lib);
        
        model.addAttribute("userList", userList);
        model.addAttribute("productList", productList);
        model.addAttribute("projectList", projectList);
        model.addAttribute("moduleList", moduleList);
        model.addAttribute("libList", libList);
		request.getSession().setAttribute("libList",list);
		request.getSession().setAttribute("moduleId", moduleId);
		return "/document/document.page";
	}
	
	/*
	 * 首页查出数据的action
	 */
	@RequestMapping(value="/doc/list")
	public String docList(String moduleId, HttpServletRequest request,Integer page,Integer limit,String order,String ordertype,DocumentDoc doc,Model model ,String groupOperate, SearchInfos searchInfos)
	{	
		doc.setDocDeleted("0");		
		boolean asc = true;
		if("desc".equals(ordertype)){
			asc = false;
		}
		String condition = null;
		if(!StringUtil.isBlank(moduleId)){
			if(moduleId.contains("p")&&((Integer)request.getSession().getAttribute("documentLibId"))==1){
				doc.setDocProduct(Integer.parseInt(moduleId.substring(1)));
				Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, condition,searchInfos,groupOperate, order, asc);
				model.addAttribute("docpager", docpager);
			}else if(moduleId.contains("p")&&((Integer)request.getSession().getAttribute("documentLibId"))==2) {
				doc.setDocProject(Integer.parseInt(moduleId.substring(1)));
				Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, condition,searchInfos,groupOperate, order, asc);
				model.addAttribute("docpager", docpager);
			}else if("productDoc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())){			
				Integer root = moduleService.findById(Integer.valueOf(moduleId)).getModuleRoot();
				doc.setDocProduct(Integer.valueOf(root));
				condition = NameUtil.resolveNameDesc("docModule") + " "+ ModuleUtil.getCondition(Integer.valueOf(moduleId), moduleService);
				Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, condition,searchInfos,groupOperate, order, asc);
				model.addAttribute("docpager", docpager);						
			}else if("projectDoc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())){
				Integer root = moduleService.findById(Integer.valueOf(moduleId)).getModuleRoot();
				doc.setDocProject(Integer.valueOf(root));
				condition = NameUtil.resolveNameDesc("docModule") + " "+ ModuleUtil.getCondition(Integer.valueOf(moduleId), moduleService);
				Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, condition,searchInfos,groupOperate, order, asc);
				model.addAttribute("docpager", docpager);						
			}else if("doc".equals(moduleService.findById(Integer.valueOf(moduleId)).getModuleType())){
				condition = NameUtil.resolveNameDesc("docModule") + " "+ ModuleUtil.getCondition(Integer.valueOf(moduleId), moduleService);
				Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, condition,searchInfos,groupOperate, order, asc);
				model.addAttribute("docpager", docpager);
			}
		}else{
			doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
			Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, null,searchInfos,groupOperate, order, asc);
			model.addAttribute("docpager", docpager);
		}
		return "/data/datalist.pagelet";
	}
	
	/*
	 * 添加文档的跳转的方法
	 */
	@RequiresPermissions(value={"add-doc"})
	@RequestMapping(value="/doc/add")
	public String createDoc(HttpServletRequest request ,Model model)
	{		
		Integer libid = (Integer) request.getSession().getAttribute("documentLibId");
		if(libid == 1){
			Product product = new Product();
			List<Product> list = productService.findProductList(product);
			model.addAttribute("productList", list);
			return "/document/add-doc-product.page";
		}else if(libid == 2){	
			List<Project> list = projectService.findList();
			Product product = new Product();
			List<Product> list1 = productService.findProductList(product);
			model.addAttribute("productList", list1);
			model.addAttribute("projectList", list);
			return "/document/add-doc-project.page";
		}else{
			SystemModule module = new SystemModule();
			module.setModuleType("doc");
			module.setModuleRoot(libid);
			List<SystemModule> moduleList = moduleService.findModules(module);
			model.addAttribute("moduleList", moduleList);
			return "/document/add-doc.page";	
		}
	}
	
	/*
	 * 添加文档的保存方法
	 */
	@RequestMapping(value="/doc/addSave",method=RequestMethod.POST)
	public String addSave(HttpServletRequest request,SystemAction systemAction,DocumentDoc doc,@RequestParam(value = "file", required = false)MultipartFile[] file,String[] title,Model model) throws IOException{	
		List<Product> product = productService.findProductList(new Product());
		doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));
		doc.setDocDeleted("0");	
		doc.setDocAddedBy(UserUtils.getUser().getOrgUserId());
		DocumentDoc document = docservice.createNewDoc(doc);
		uploads(file,document.getDocId(), ProfileType.DOCUMENT,title);
        
		model.addAttribute("productList", product);
		
		LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
				LogUtil.LogAction.OPENED,
				String.valueOf(document.getDocId()),
				UserUtils.getUserId(),
				String.valueOf(doc.getDocProduct()),
				String.valueOf(doc.getDocProject()),
				null,
				null,
				systemAction.getActionComment());
		
		if(request.getSession().getAttribute("moduleId") == null){
			return "redirect:"+"/a/document?docChange=true";
		}
		return "redirect:"+"/a/document?docChange=true&moduleId="+request.getSession().getAttribute("moduleId");
	}
	
	/*
	 * 编辑文档的跳转的方法
	 */
	@RequiresPermissions(value={"docedit"})
	@RequestMapping(value="/doc/edit")
	public String editDoc(HttpServletRequest request,Model model,Integer docId)
	{	
		SystemModule module = new SystemModule();
		DocumentDoc doc = new DocumentDoc();
		module.setModuleType("doc");
		doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
		doc = docservice.findDocById(docId);
		List<Product> list1 = productService.findProductList(new Product());
		List<Project> list2 = projectService.findList();
		List<SystemModule> listModule = moduleService.findModuleList(module);
		model.addAttribute("doc", doc);
		model.addAttribute("productList", list1);
		model.addAttribute("projectList", list2);
		model.addAttribute("listModule", listModule);
		return "/document/doc-edit.page";
	}
	
	/*
	 * 编辑文档的保存的方法
	 */
	@RequestMapping(value="/doc/editSave",method=RequestMethod.POST)
	public String editSave(DocumentDoc doc,SystemAction systemAction,Model model){
		DocumentDoc documentDoc = docservice.findDocById(doc.getDocId());
		doc.setDocEditedBy(UserUtils.getUser().getOrgUserId());
		docservice.editDoc(doc);
		
		LogUtil.logWithComment(LogUtil.LogOperateObject.DOC,
				LogUtil.LogAction.EDITED, 
				String.valueOf(doc.getDocId()), 
				UserUtils.getUserId(), 
				String.valueOf(doc.getDocProduct()),
				String.valueOf(doc.getDocProject()), 
				documentDoc, 
				doc, 
				systemAction.getActionComment());
		return "redirect:"+"/a/document?docChange=true";
	}
	
	/*
	 *查看文档基本信息
	 */
	@RequestMapping("/doc/view")
	public String docView(HttpServletRequest request, SystemAction systemAction,DocumentDoc doc,SystemProfile systemProfile,Model model,Integer docid){
		doc = docservice.findDocById(docid);	
		DocumentDoclib docLib = docservice.findDoclibById(doc.getDocLibId());
		systemProfile.setFileObjectType("document");
		systemProfile.setFileObjectId(docid);
		systemProfile.setFileDeleted("0");
		List<SystemProfile> list = profileService.find(systemProfile);
		model.addAttribute("file",list);
		model.addAttribute("doc",doc);
		model.addAttribute("docLib",docLib);
		return "/document/doc-view.page";
	}
	
	/*
	 * 文档基本信息右边的页面
	 */
	@RequestMapping("/doc/viewInfo")
	public String viewInfo(HttpServletRequest request, Integer docId, Model model){	
		DocumentDoc doc = docservice.findDocById(docId);
		//doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));		
		DocumentDoclib docLib = docservice.findDoclibById(doc.getDocLibId());
		System.out.println(doc.getDocModule()!=0&&doc.getDocModule()!=null);
		if(doc.getDocModule()!=0&&doc.getDocModule()!=null){
			SystemModule module = moduleService.findById(doc.getDocModule());
			model.addAttribute("module",module);
		}else{
			model.addAttribute("module",null);
		}
		model.addAttribute("doc",doc);
		model.addAttribute("docLib",docLib);		
		return "/document/basic-info.pagelet";
	}
	
	/*
	 * 添加文档和编辑文档的保存方法
	 */
	@RequestMapping(value="/doc/save",method=RequestMethod.POST)
	public String saveDoc(HttpServletRequest request,DocumentDoc doc,Model model)
	{
		if(doc.getDocId()==null||doc.getDocId()==0){
			doc = docservice.createNewDoc(doc);			
		}
		else{
			docservice.editDoc(doc);
		}
		return "redirect:"+"/a/document";
	}
	
	/*
	 * 单条删除文档的action
	 */
	@RequiresPermissions(value={"docdelete","doc-view-delete"},logical=Logical.AND)
	@ResponseBody
	@RequestMapping(value="/doc/delete")
	public Map delDoc(Integer id)
	{
		docservice.deleteDocById(id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}
	
	/*
	 * 批量删除文档的action
	 */
	@RequiresPermissions(value={"batch-delete"})
	@ResponseBody
	@RequestMapping(value="/doc/batchDelete")
	public Map bctchDelDoc(String ids)
	{		
		Map<String,String> map = new HashMap<String,String>();
		if(ids == null || ids == ""){
			map.put("status", "fail");
		    map.put("info", "请至少选择一条数据");
			return map;
		}
		 List<DocumentDoc> list = new ArrayList<DocumentDoc>();
		for(String s : ids.split(",")){			
			DocumentDoc doc= new DocumentDoc();
			doc.setDocId(Integer.valueOf(s));
			doc.setDocDeleted("1");
			list.add(doc);
		}	
		docservice.deleteDocByIds(list);
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}
	
	/*
	 * 添加文档库的action
	 */
	@RequiresPermissions(value={"doclib-add"})
	@RequestMapping(value="/doclib/toAdd")
	public String addDocLib()
	{
		return "/document/add-doclib.pagelet";
	}
	
	/*
	 * 编辑文档库 的action
	 */
	@RequiresPermissions("doclib-edit")
	@RequestMapping(value="/doclib/edit")
	public String editDoclib(HttpServletRequest request,DocumentDoclib doclib,Model model)
	{		
		doclib = docservice.findDoclibById((Integer) request.getSession().getAttribute("documentLibId"));
		model.addAttribute("doclib", doclib);
		if((Integer) request.getSession().getAttribute("documentLibId") == 1 || (Integer) request.getSession().getAttribute("documentLibId") == 2){
			return "/document/doclib-no-edit.pagelet";
		}
		return "/document/doclib-edit.pagelet";
	}
	
	/*
	 * 文档库编辑和保存的保存的action
	 */
	@RequestMapping(value="/doclib/save")
	public String saveDoclib(HttpServletRequest request,DocumentDoclib doclib)
	{
		if(doclib.getDocLibId()==null||doclib.getDocLibId()==0){		
			doclib = docservice.createNewDocLib(doclib);
			request.getSession().setAttribute("documentLibId", doclib.getDocLibId());
		}else {
			docservice.editDocLibName(doclib);
		}	
		return "redirect:"+"/a/document?change=true";
	}
	/*
	 * 删除文档库的action
	 */
	@RequiresPermissions(value={"doclib-delete"})
	@ResponseBody
	@RequestMapping(value="/doclib/delete")
	public Map delDocLib(Integer id)
	{
		DocumentDoclib doclib = new DocumentDoclib();
		List<DocumentDoclib> list = docservice.findDoclibList(doclib);
		if(id != list.get(0).getDocLibId() && id != list.get(1).getDocLibId()){
			docservice.deleteDoclibById(id);
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "success");
		    map.put("info", "删除成功");
		    return map;
		}
		return null;
	}
	
	/*
	 * 添加项目文档中项目和分类的二级联动
	 */
	@ResponseBody
	@RequestMapping("/ajax/module")
	public List<SystemModule> getModule(SystemModule systemModule,Integer projectId){
		if(projectId == 0){	
			return moduleService.findModules(systemModule);
		}
		systemModule.setModuleRoot(projectId);
		systemModule.setModuleType("projectDoc");
		return moduleService.findModules(systemModule);
	}
	
	/*
	 * 添加产品文档中产品和分类的二级联动
	 */
	@ResponseBody
	@RequestMapping("/ajax/module1")
	public List<SystemModule> getModule1(SystemModule systemModule,Integer productId){
		if(productId ==0){
			return moduleService.findModules(systemModule);
		}
		systemModule.setModuleRoot(productId);
		systemModule.setModuleType("productDoc");
		return moduleService.findModules(systemModule);
	}
	
	/*
	 * 添加项目文档中项目和产品的二级联动
	 */
	@ResponseBody
	@RequestMapping("/ajax/product")
	public List<Product> getproduct(Product product,Integer projectId){
		
		if(projectId == 0){			
			return projectProductService.findLinkProduct();
		}
		List<ProjectProduct> list = projectProductService.findProducts(projectId);
		List<Product> list2 = productService.findProductList(product);
		Integer[] i = new Integer[list.size()];
		List<Integer> list1 = new ArrayList<Integer>();
		for(int j=0;j<list.size();j++){
			if(list2.get(j).getDeleted()==0){
				list1.add(list.get(j).getProductId());
			}
		}
		List<Product> productList = productService.findProductList(list1.toArray(i));
		return productList;
	}
	
	/*
	 * 编辑文档中文档库和分类的二级联动
	 */
	@ResponseBody
	@RequestMapping("/ajax/moduleByDoclib")
	public List<SystemModule> getModuleByDoclib(Integer libId){
		SystemModule module = new SystemModule();
		module.setModuleRoot(libId);
		module.setModuleType("doc");
		List<SystemModule> list = moduleService.findModuleList(module);
		return list;
	}
	
	/*
	 * 编辑文档中项目和分类的二级联动
	 */
	@ResponseBody
	@RequestMapping("/ajax/moduleByProject")
	public List<SystemModule> getModuleByProject(Integer projectId){
		SystemModule module = new SystemModule();
		module.setModuleType("projectDoc");
		module.setModuleRoot(projectId);
		List<SystemModule> list = moduleService.findModuleList(module);
		return list;
	}
	
	/*
	 * 编辑文档中产品和分类的二级联动
	 */
	@ResponseBody
	@RequestMapping("/ajax/moduleByProduct")
	public List<SystemModule> getModuleByProduct(Integer productId){
		SystemModule module = new SystemModule();
		module.setModuleType("productDoc");
		module.setModuleRoot(productId);
		List<SystemModule> list = moduleService.findModuleList(module);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/ajax/productByDocLib")
	public List<Product> getProductByDocLib(){
		Product product = new Product();
		List<Product> list = productService.findProductList(product);
		return list;
	}
	
	//产品文档相关
	@RequestMapping("/product/doc")
	public String product(HttpServletRequest request,Model model){
		Product product = new Product();
		List<Product> list = productService.findProductList(new Product());
		if(list.size() > 0){
			if(null==request.getSession().getAttribute("sessionProductId")||product.getProductId()==null){
				request.getSession().setAttribute("sessionProductId",list.get(0).getProductId());
			}else {
				request.getSession().setAttribute("sessionProductId",product.getProductId());
			}
		}
		model.addAttribute("productList", list);
		return "/product/page/project/archive-list.page";
	}
	
	@RequestMapping("/product/doc/list")
	public String productList(DocumentDoc doc,HttpServletRequest request,Integer page,Integer limit,String order,String ordertype,String groupOperate, SearchInfos searchInfos, Model model){
		
		doc.setDocProduct((Integer) request.getSession().getAttribute("sessionProductId"));
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, null, searchInfos,groupOperate, NameUtil.resolveNameDesc(order), asc);
		model.addAttribute("pager", docpager);
		return "/product/data/archivedata.pagelet";
	}
	
	@RequestMapping("/product/findDoc")
	public String findDocument(Integer docId,Model model){
		
		DocumentDoc doc = docservice.findDocById(docId);
		model.addAttribute("doc", doc);
		return "/document/doc-edit.page";
	}
	
	@RequestMapping("/product/{type}/updateDoc")
	public String saveDocument(DocumentDoc doc,@PathVariable(value="type")String type,@RequestParam(value = "file", required = false)MultipartFile[] file,String[] title) throws IOException {
		if("save".equals(type)){
			DocumentDoc document = docservice.createNewDoc(doc);

	        uploads(file,document.getDocId(),ProfileType.DOCUMENT,title);
			return "redirect:"+"/product/page/project/archive-list.page";
		}else if ("update".equals(type)) {

			docservice.editDoc(doc);
			return "redirect:"+"/a/document/product/doc";
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/docList")
	public List<DocumentDoc> findDocumentDoc(DocumentDoc doc) {

		List<DocumentDoc> list = docservice.findDocList(doc);

		return list;
	}
}
