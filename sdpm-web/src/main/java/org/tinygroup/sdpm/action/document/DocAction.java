package org.tinygroup.sdpm.action.document;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

@Controller
@RequestMapping(value="/document")
public class DocAction {
	@Autowired
	private DocService docservice;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("")
	public String docIndex(DocumentDoclib doclib,HttpServletRequest request,Model model,String change,String docChange)
	{	
		List<DocumentDoclib> list=docservice.findDoclibList(new DocumentDoclib());				
		if(list.size()>0&&!("true".equals(change))&&!("true".equals(docChange))){
			if(null==request.getSession().getAttribute("documentLibId")||doclib.getDocLibId()==null){
				request.getSession().setAttribute("documentLibId",list.get(0).getDocLibId());
			}else {
				request.getSession().setAttribute("documentLibId",doclib.getDocLibId());
			}
		}
		request.getSession().setAttribute("libList",list);	
		return "/document/document.page";
		//return "redirect:/document/document?"+(list.size()>0?("lib="+list.get(0).getDocLibName()):"");
	}
	
	@RequestMapping(value="/doc/list")
	public String docList(HttpServletRequest request,Integer page,Integer limit,String order,String ordertype,DocumentDoc doc,Model model ,String groupOperate, SearchInfos searchInfos)
	{
		doc.setDocDeleted("0");
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));
		Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, searchInfos,groupOperate, order, asc);
		model.addAttribute("docpager", docpager);
		return "/data/datalist.pagelet";
	}
	
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
		return "/document/add-doc.page";	
		}
	}
	
	@RequestMapping(value="/doc/addSave",method=RequestMethod.POST)
	public String addSave(HttpServletRequest request,DocumentDoc doc,Model model){	
		List<Product> product = productService.findProductList(new Product());
		doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));
		docservice.createNewDoc(doc);
		model.addAttribute("productList", product);
		return "redirect:"+"/document?docChange=true";
	}
	
	@RequestMapping(value="/doc/edit")
	public String editDoc(HttpServletRequest request,Model model,Integer docId)
	{	
		DocumentDoc doc = new DocumentDoc();
		doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
		doc = docservice.findDocById(docId);
		model.addAttribute("doc", doc);
		return "/document/doc-edit.page";
	}
	
	@RequestMapping(value="/doc/editSave",method=RequestMethod.POST)
	public String editSave(DocumentDoc doc,Model model){			
		docservice.editDoc(doc);
		return "redirect:"+"/document?docChange=true";
	}
	
	@RequestMapping("/doc/view")
	public String docView(HttpServletRequest request,DocumentDoc doc,Model model,Integer docid){
		
		doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));
		doc = docservice.findDocById(docid);		
		DocumentDoclib docLib = docservice.findDoclibById(doc.getDocLibId());
		model.addAttribute("doc",doc);
		model.addAttribute("docLib",docLib);
		return "/document/doc-view.page";
	}
	
	@RequestMapping("/doc/viewInfo")
	public String viewInfo(HttpServletRequest request, Integer docId, Model model){
		DocumentDoc doc = new DocumentDoc();
		DocumentDoclib docLib = new DocumentDoclib();
		doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));
		doc = docservice.findDocById(docId);
		docLib = docservice.findDoclibById(doc.getDocLibId());
		model.addAttribute("doc",doc);
		model.addAttribute("docLib",docLib);
		return "/document/basic-info.pagelet";
	}
	
	@RequestMapping(value="/doc/save",method=RequestMethod.POST)
	public String saveDoc(HttpServletRequest request,DocumentDoc doc,Model model)
	{
		if(doc.getDocId()==null||doc.getDocId()==0){
			doc = docservice.createNewDoc(doc);			
		}
		else{
			docservice.editDoc(doc);
		}
		return "redirect:"+"/document";
	}
	
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
	
	@RequestMapping(value="/doclib/toAdd")
	public String addDocLib()
	{
		return "/document/add-doclib.pagelet";
	}
	
	@RequestMapping(value="/doclib/edit")
	public String editDoclib(HttpServletRequest request,DocumentDoclib doclib,Model model)
	{	
		//List<DocumentDoclib> liblist = docservice.findDoclibList(doclib);
		doclib.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));		
	//	if(liblist.size()>0)
	//		doclib = liblist.get(0);
		model.addAttribute("doclib", doclib);
		return "/document/doclib-edit.pagelet";
	}
	
	@RequestMapping(value="/doclib/save")
	public String saveDoclib(HttpServletRequest request,DocumentDoclib doclib)
	{
		if(doclib.getDocLibId()==null||doclib.getDocLibId()==0){
			doclib = docservice.createNewDocLib(doclib);
			request.getSession().setAttribute("documentLibId", doclib.getDocLibId());
		}else {
			docservice.editDocLibName(doclib);
		}	
		return "redirect:"+"/document?change=true";
	}
	
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
	
	//产品文档
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
		Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc,searchInfos,groupOperate, NameUtil.resolveNameDesc(order), asc);
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
	public String saveDocument(DocumentDoc doc,@PathVariable(value="type")String type){
		if("save".equals(type)){
			docservice.createNewDoc(doc);
			return "redirect:"+"/product/page/project/archive-list.page";
		}else if ("update".equals(type)) {

			docservice.editDoc(doc);
			return "redirect:"+"/document/product/doc";
		}
		return "";
	}
	
	//项目文档
	@RequestMapping("/project/doc")
	public String prodject(HttpServletRequest request){		
		return "";
	}
	
}
