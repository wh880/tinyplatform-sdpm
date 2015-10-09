package org.tinygroup.sdpm.action.document;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.Doclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.weblayer.WebContext;

@Controller
@RequestMapping(value="/document")
public class DocAction {
	@Autowired
	private DocService docservice;
	@RequestMapping("")
	public String docIndex(HttpServletRequest request,Model model,String libname)
	{
		//List<Doclib> list = (List<Doclib>)request.getSession().getAttribute("liblist");
		//if(list==null||list.size()==0){
		List<Doclib> list=docservice.findDoclibList(new Doclib());
			//request.getSession().setAttribute("liblist",list);
			model.addAttribute("liblist",list);
		//}
			model.addAttribute("libname", libname);
		return "/document/document.page";
		//return "redirect:/document/document?"+(list.size()>0?("lib="+list.get(0).getDocLibName()):"");
	}
	@RequestMapping(value="/doc/list")
	public String docList(int limit,Model model,String libname)
	{
		Doc doc = new Doc();
		doc.setDocLib(libname);
		Pager<Doc> docpager = docservice.findDocRetPager(0, limit, doc);
		model.addAttribute("docpager", docpager);
		model.addAttribute("libname", libname);
		return "/data/datalist.pagelet";
	}
	@RequestMapping(value="/doc/add")
	public String createDoc(Doclib doclib,Model model,String libname)
	{
		model.addAttribute("libname", libname);
		return "/document/add-doc.page";
	}
	@RequestMapping(value="/doc/edit")
	public String editDoc(Doc doc,Model model,String libname,Integer docid)
	{
		doc = docservice.findDocById(docid);
		model.addAttribute("doc", doc);
		model.addAttribute("libname", libname);
		return "/document/doc-edit.page";
	}
	@RequestMapping("/doc/view")
	public String docView(Doc doc,Model model,String libname,Integer docid){
		doc = docservice.findDocById(docid);
		model.addAttribute("doc",doc);
		model.addAttribute("libname", libname);
		return "/document/doc-view.page";
	}
	@RequestMapping(value="/doc/save",method=RequestMethod.POST)
	public String saveDoc(Doc doc,Model model)
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
	public String editDoclib(Doclib doclib,Model model,String libname)
	{
		//doclib = docservice.findDoclibById(doclibid);
		doclib.setDocLibName(libname);
		//if(doclib.getDocLibName()==null||doclib.getDocLibName()==""){
			List<Doclib> liblist = docservice.findDoclibList(doclib);
			if(liblist.size()>0)
				doclib=liblist.get(0);
		//}
		model.addAttribute("doclib", doclib);
		return "/document/doclib-edit.pagelet";
	}
	@RequestMapping(value="/doclib/save")
	public String saveDoclib(Doclib doclib)
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
