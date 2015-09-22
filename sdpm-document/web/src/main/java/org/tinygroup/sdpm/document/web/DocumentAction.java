package org.tinygroup.sdpm.document.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.docment.service.inter.DocService;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.DocLib;

/**
 * @date 2015/09/21
 * @author Alu
 *
 */

@Controller
@RequestMapping("/document")
public class DocumentAction{
	@Autowired
	private DocService docservice;
	@ModelAttribute
	public void getList(Model model)
	{
		//
		List<Doc> doclist = docservice.findDocByDocClass(new Doc());
		model.addAttribute("doclist",doclist);
	}
	@RequestMapping(value="/add-doc",method=RequestMethod.POST)
	public String adddoc(Doc doc)
	{
		docservice.createNewDoc(doc);
		return "redirect:" +"/document/document";
	}
	@RequestMapping(value="/add-doclib",method=RequestMethod.POST)
	public String adddoclib(DocLib doclib)
	{
		docservice.createNewDocLib(doclib);
		return "redirect:" +"/document/document";
	}

}
