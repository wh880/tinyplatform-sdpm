package org.tinygroup.sdpm.document.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.document.dao.pojo.Doc;
import org.tinygroup.sdpm.document.dao.pojo.DocLib;
import org.tinygroup.sdpm.document.dao.pojo.Historydoc;
import org.tinygroup.sdpm.document.service.inter.DocService;


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
<<<<<<< HEAD:sdpm-web/src/main/java/org/tinygroup/sdpm/action/document/DocumentAction.java
		//Doc doc = new Doc();
		//List<Doc> doclist = docservice.findDocByDocClassOrderBy(doc);
		//model.addAttribute("doclist",doclist);
=======
		List<Doc> doclist = docservice.findDocByDocClass(new Doc());
		model.addAttribute("doclist",doclist);
>>>>>>> 7ab0642d53d5ca91cfcb2ed35b60ae72a14518b1:sdpm-document/web/src/main/java/org/tinygroup/sdpm/document/web/DocumentAction.java
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
