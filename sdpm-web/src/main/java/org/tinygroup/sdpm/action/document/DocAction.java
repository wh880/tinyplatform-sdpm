package org.tinygroup.sdpm.action.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.DocLib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.tinysqldsl.Pager;

@Controller
@RequestMapping(value="/document")
public class DocAction {
	@Autowired
	private DocService docservice;
	@RequestMapping(value={"","/document"})
	public String docList(Doc doc,Model model,@RequestParam(required = false, defaultValue = "1") Integer page)
	{
		Pager<Doc> docpager = docservice.findDocRetPager(1, page, doc);
		model.addAttribute("docpager", docpager);
		return "/document/document";
	}
	@RequestMapping(value="/add-doc/add",method=RequestMethod.POST)
	public String addDoc(Doc doc,Model model)
	{
		Doc ret = docservice.createNewDoc(doc);
		model.addAttribute("doc", ret);
		return "redirect:"+"/document/document";
	}
	@RequestMapping(value="/add-doclib/add",method=RequestMethod.POST)
	public String addDocLib(DocLib doclib,Model model)
	{
		docservice.createNewDocLib(doclib);
		return "redirect:"+"/document/document";
	}
	@RequestMapping(value="/delete/doc/{docid}",method=RequestMethod.DELETE)
	public String delDoc(@PathVariable Integer docid)
	{
		int ret = docservice.deleteDocById(docid);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document/document.page";
	}
	@RequestMapping(value="/delete/doclib/{doclibid}",method=RequestMethod.DELETE)
	public String delDocLib(@PathVariable Integer doclibid)
	{
		int ret = docservice.deleteDoclibById(doclibid);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document/document";
	}

}
