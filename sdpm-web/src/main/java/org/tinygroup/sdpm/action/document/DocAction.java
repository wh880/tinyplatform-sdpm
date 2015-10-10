package org.tinygroup.sdpm.action.document;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoc;
import org.tinygroup.sdpm.document.dao.pojo.DocumentDoclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

@Controller
@RequestMapping(value="/document")
public class DocAction {
	@Autowired
	private DocService docservice;
	
	@RequestMapping("")
	public String docIndex(DocumentDoclib doclib,HttpServletRequest request,Model model)
	{
	//	doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
		//List<Doclib> list = (List<Doclib>)request.getSession().getAttribute("liblist");
		//if(list==null||list.size()==0){
		List<DocumentDoclib> list=docservice.findDoclibList(new DocumentDoclib());
			//request.getSession().setAttribute("liblist",list);
			model.addAttribute("libList",list);
		//}
		//	model.addAttribute("libname", libname);
			if(null==request.getSession().getAttribute("documentLibId")||""==request.getSession().getAttribute("documentLibId")){
				
				request.getSession().setAttribute("documentLibId",list.get(0).getDocLibId());
			}else{
				
				request.getSession().setAttribute("documentLibId",doclib.getDocLibId());
			}
		return "/document/document.page";
		//return "redirect:/document/document?"+(list.size()>0?("lib="+list.get(0).getDocLibName()):"");
	}
	
	@RequestMapping(value="/doc/list")
	public String docList(HttpServletRequest request,Integer page,Integer limit,String order,String ordertype,DocumentDoc doc,Integer libId,Model model)
	{
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
		Pager<DocumentDoc> docpager = docservice.findDocRetPager(limit*(page-1), limit, doc, order, asc);
		model.addAttribute("docpager", docpager);
	//	model.addAttribute("libId", libId);
		return "/data/datalist.pagelet";
	}
	
	@RequestMapping(value="/doc/add")
	public String createDoc(HttpServletRequest request,DocumentDoc doc,DocumentDoclib doclib,Model model,String libname)
	{
		doc.setDocLibId((Integer) request.getSession().getAttribute("documentLibId"));
		model.addAttribute("libname", libname);
		return "/document/add-doc.page";
	}
	
	@RequestMapping(value="/doc/edit")
	public String editDoc(DocumentDoc doc,Model model,String libname,Integer docid)
	{
		doc = docservice.findDocById(docid);
		model.addAttribute("doc", doc);
		model.addAttribute("libname", libname);
		return "/document/doc-edit.page";
	}
	
	@RequestMapping("/doc/view")
	public String docView(DocumentDoc doc,Model model,String libname,Integer docid){
		
		doc = docservice.findDocById(docid);		
		DocumentDoclib docLib = docservice.findDoclibById(doc.getDocLibId());
		model.addAttribute("doc",doc);
		model.addAttribute("docLib",docLib);
		model.addAttribute("libname", libname);
		return "/document/doc-view.page";
	}
	
	@RequestMapping(value="/doc/save",method=RequestMethod.POST)
	public String saveDoc(DocumentDoc doc,Model model)
	{
		if(doc.getDocId()==null||doc.getDocId()==0)
			doc = docservice.createNewDoc(doc);
		else
			docservice.editDoc(doc);
		model.addAttribute("doc", doc);
		return "redirect:"+"/document"/*+"?libname="+doc.getDocLib()*/;
	}
	
	@RequestMapping(value="/doc/delete")
	public String delDoc(Integer id)
	{
		/*int ret = */
		docservice.deleteDocById(id);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document";
	}
	
	@RequestMapping(value="/doclib/add")
	public String addDocLib()
	{
		return "/document/add-doclib.pagelet";
	}
	
	@RequestMapping(value="/doclib/edit")
	public String editDoclib(DocumentDoclib doclib,Model model,String libname)
	{
		//doclib = docservice.findDoclibById(doclibid);
		doclib.setDocLibName(libname);
		//if(doclib.getDocLibName()==null||doclib.getDocLibName()==""){
			List<DocumentDoclib> liblist = docservice.findDoclibList(doclib);
			if(liblist.size()>0)
				doclib=liblist.get(0);
		//}
		model.addAttribute("doclib", doclib);
		return "/document/doclib-edit.pagelet";
	}
	
	@RequestMapping(value="/doclib/save")
	public String saveDoclib(DocumentDoclib doclib)
	{
		if(doclib.getDocLibId()==null||doclib.getDocLibId()==0)
			docservice.createNewDocLib(doclib);
		else
			docservice.editDocLibName(doclib);
		return "redirect:"+"/document";
	}
	
	@RequestMapping(value="/doclib/delete")
	public String delDocLib(Integer id)
	{
		/*int ret = */
		docservice.deleteDoclibById(id);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document";
	}
}
