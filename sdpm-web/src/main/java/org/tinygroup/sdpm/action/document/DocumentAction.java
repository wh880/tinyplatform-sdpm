package org.tinygroup.sdpm.action.document;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;

/**
 * @date 2015/09/21
 * @author Alu
 *
 */

@Controller
@RequestMapping("/document")
public class DocumentAction extends BaseController{
//	@Autowired
//	private DocService docservice;
//	@RequestMapping("/document")
//	public void getList(Model model)
//	{
//		//
//		Doc doc = new Doc();
//		List<Doc> doclist = docservice.findDocByDocClassOrderBy(doc);
//		model.addAttribute("doclist",doclist);
//	}
//	@RequestMapping(value="/add-doc",method=RequestMethod.POST)
//	public String adddoc(Doc doc)
//	{
//		docservice.createNewDoc(doc);
//		return "redirect:" +"/document/document.page";
//	}
//	@RequestMapping(value="/add-doclib",method=RequestMethod.POST)
//	public String adddoclib(DocLib doclib)
//	{
//		docservice.createNewDocLib(doclib);
//		return "redirect:" +"/document/document.page";
//	}
//	@RequestMapping(value="/doc-view")
//	public void viewdoc(Doc doc,Model model)
//	{
//		doc = docservice.findDocById(doc.getDocId());
//		List<Historydoc> hisdoc = docservice.getEditRecord(doc.getDocId());
//		model.addAttribute("doc", doc);
//		model.addAttribute("docHistory", hisdoc);
//	}
//	@RequestMapping(value="/delete/doc/{docid}")
//	public String delDoc(@PathVariable Integer docid)
//	{
//		int ret = docservice.deleteDocById(docid);
//		return "redirect:"+"/document/document.page";
//	}

}
