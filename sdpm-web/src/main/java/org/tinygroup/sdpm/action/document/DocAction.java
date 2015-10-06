package org.tinygroup.sdpm.action.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String docIndex(Doclib doclib,Model model)
	{
		//List<Doclib> listDoclib = docservice.findDoclibList(doclib);
		//for(int i=0;i<listDoclib.size();i++)
		//	listDoclib.get(i);
		Pager<Doclib> doclibpager = docservice.findDoclibRetPager(0, 20, doclib);
		model.addAttribute("doclibpager", doclibpager);
		//doclibpager.getRecords()
		return "/document/document.page";
	}
	@RequestMapping(value="/doc/list")
	public String docList(Doc doc,int limit,Model model,String libname)
	{
		if(doc==null)
			doc = new Doc();
		doc.setDocLib(libname);
		Pager<Doc> docpager = docservice.findDocRetPager(0, limit, doc);
		model.addAttribute("docpager", docpager);
		return "/data/datalist.pagelet";
	}
	@RequestMapping(value="/doc/add")
	public String createDoc(Doclib doclib,Model model)
	{
		model.addAttribute("doclib", docservice.findDoclibById(doclib.getDocLibId()));
		return "/document/add-doc.page";
	}
	@RequestMapping(value="/doc/edit")
	public String editDoc(Doc doc,Model model,Integer docid)
	{
		doc = docservice.findDocById(docid);
		model.addAttribute("doc", doc);
		return "/document/doc-edit.page";
	}
	@RequestMapping("/doc/view")
	public String docView(Doc doc,Model model,Integer docid){
		doc = docservice.findDocById(docid);
		model.addAttribute("doc",doc);
		return "/document/doc-view.page";
	}
	@RequestMapping(value="/doc/save",method=RequestMethod.POST)
	public String saveDoc(Doc doc,Model model)
	{
		if(doc.getDocId()!=null||doc.getDocId()!=0)
			docservice.editDoc(doc);
		doc = docservice.createNewDoc(doc);
		model.addAttribute("doc", doc);
		return "redirect:"+"/document/document";
	}
	@RequestMapping(value="/doc/delete")
	public String delDoc(Integer docid)
	{
		/*int ret = */
		docservice.deleteDocById(docid);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document/document";
	}
	@RequestMapping(value="/doclib/add")
	public String addDocLib()
	{
		return "/document/add-doclib.page";
	}
	@RequestMapping(value="/doclib/edit")
	public String editDoclib(Doclib doclib,Model model,Integer doclibid)
	{
		doclib = docservice.findDoclibById(doclibid);
		model.addAttribute("doclib", doclib);
		return "/document/edit-doclib.pagelet";
	}
	@RequestMapping(value="/doclib/save")
	public String saveDoclib(Doclib doclib)
	{
		if(doclib.getDocLibId()!=null||doclib.getDocLibId()!=0)
			docservice.editDocLibName(doclib);
		docservice.createNewDocLib(doclib);
		return "redirect:"+"/document/document";
	}
	@RequestMapping(value="/doclib/delete")
	public String delDocLib(Integer doclibid)
	{
		/*int ret = */
		docservice.deleteDoclibById(doclibid);
		//if(ret==0)return "/document/Error.page";
		return "redirect:"+"/document/document";
	}
}
