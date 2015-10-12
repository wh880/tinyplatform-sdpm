package org.tinygroup.sdpm.action.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

@Controller
@RequestMapping(value="/document")
public class DocAction {
	@Autowired
	private DocService docservice;
	@Autowired
	private ProductService productService;
	
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
		model.addAttribute("libList",list);		
		return "/document/document.page";
		//return "redirect:/document/document?"+(list.size()>0?("lib="+list.get(0).getDocLibName()):"");
	}
	
	@RequestMapping(value="/doc/list")
	public String docList(HttpServletRequest request,Integer page,Integer limit,String order,String ordertype,DocumentDoc doc,Model model)
	{
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		doc.setDocLibId(Integer.valueOf((Integer)request.getSession().getAttribute("documentLibId")));
		Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, order, asc);
		model.addAttribute("docpager", docpager);
	//	model.addAttribute("libId", libId);
		return "/data/datalist.pagelet";
	}
	
	@RequestMapping(value="/doc/add")
	public String createDoc()
	{		
		return "/document/add-doc.page";
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
		List<Product> product = productService.findProductList(new Product());
		doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
	//	List<DocumentDoclib> list = docservice.findDoclibList(new DocumentDoclib());
		doc = docservice.findDocById(docId);
		model.addAttribute("productList", product);
		model.addAttribute("doc", doc);
	//	model.addAttribute("libList", list);
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
	
	@RequestMapping(value="/doclib/toAdd")
	public String addDocLib()
	{
		return "/document/add-doclib.pagelet";
	}
	
/*	@RequestMapping(value="/doclib/add")
	public String add(HttpServletRequest request,Model model){
		DocumentDoclib doclib = new DocumentDoclib();
		if(doclib.getDocLibId()==null||doclib.getDocLibId()==0){
			docservice.createNewDocLib(doclib);
			request.getSession().setAttribute("documentLibId",doclib.getDocLibId());
		}else
			docservice.editDocLibName(doclib);
		
		model.addAttribute("doclib", doclib);
		return "/document/doclib-edit.pagelet";
	}*/
	
	@RequestMapping(value="/doclib/edit")
	public String editDoclib(DocumentDoclib doclib,Model model,Integer libId)
	{
		doclib = docservice.findDoclibById(libId);
		//if(doclib.getDocLibName()==null||doclib.getDocLibName()==""){
			List<DocumentDoclib> liblist = docservice.findDoclibList(doclib);
			if(liblist.size()>0)
				doclib=liblist.get(0);
		//}
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
		docservice.deleteDoclibById(id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}
	
	//产品文档
	@RequestMapping("/product/doc")
	public String product(HttpServletRequest request){		
		return "";
	}
	
	//项目文档
	@RequestMapping("/product/doc")
	public String prodject(HttpServletRequest request){		
		return "";
	}
	
}
