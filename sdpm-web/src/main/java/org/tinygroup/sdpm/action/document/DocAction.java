package org.tinygroup.sdpm.action.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.Doclib;
import org.tinygroup.sdpm.document.service.inter.DocService;
import org.tinygroup.tinysqldsl.Pager;

@Controller
@RequestMapping(value="/document")
public class DocAction {
	@Autowired
	private DocService docservice;
	@RequestMapping(value={"","/document"})
	public String docIndex(Doc doc,Model model)
	{
		//list Doc
		//Pager<Doc> docpager = docservice.findDocRetPager(0, page, doc);
		//list Doclib name
		//Pager<DocLib> doclibpager = docservice.(1, page, doclib);
		//model.addAttribute("docpager", docpager);
		return "/document/document.page";
	}
	@RequestMapping(value="/data/doclist")
	public String docList(Doc doc, int limit,Model model)
	{
		Pager<Doc> docpager = docservice.findDocRetPager(0, limit, doc);
		model.addAttribute("docpager", docpager);
		return "/data/datalist.pagelet";
	}
	@RequestMapping(value="/doc-edit/{docid}")
	public String editDoc(Doc doc,Model model,@PathVariable Integer docid)
	{
		doc = docservice.findDocById(docid);
		model.addAttribute("doc", doc);
		return "/document/table-edit.page";
	}
	@RequestMapping(value="/edit-doc/save")
	public String saveDoc(Doc doc,Model model)
	{
		docservice.editDoc(doc);
		return "redirect:"+"/document/document.page";
	}
	@RequestMapping(value="/add-doc/add",method=RequestMethod.POST)
	public String addDoc(Doc doc,Model model)
	{
		Doc ret = docservice.createNewDoc(doc);
		model.addAttribute("doc", ret);
		return "/document/document.page";
	}
	@RequestMapping(value="/add-doclib/add",method=RequestMethod.POST)
	public String addDocLib(Doclib doclib,Model model)
	{
		docservice.createNewDocLib(doclib);
		return "/document/document.page";
	}
	@RequestMapping(value="/delete/doc/{docid}",method=RequestMethod.DELETE)
	public String delDoc(@PathVariable Integer docid)
	{
		/*int ret = */
		docservice.deleteDocById(docid);
		//if(ret==0)return "/document/Error.page";
		return "/document/document.page";
	}
	@RequestMapping(value="/delete/doclib/{doclibid}",method=RequestMethod.DELETE)
	public String delDocLib(@PathVariable Integer doclibid)
	{
		/*int ret = */
		docservice.deleteDoclibById(doclibid);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document/document";
	}

}
