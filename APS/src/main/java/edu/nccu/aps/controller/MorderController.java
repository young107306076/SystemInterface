package edu.nccu.aps.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.nccu.aps.bean.MorderBean;
import edu.nccu.aps.dom.MorderDetail;
import edu.nccu.aps.entity.Morder;
import edu.nccu.aps.service.MorderService;
import edu.nccu.aps.util.CommonUtil;
import edu.nccu.aps.util.DateUtil;

@RestController
public class MorderController {

	//用AutoWired 的標記呼叫 MorderService class 做使用
	
	@Autowired
	protected MorderService morderService;

	@PostMapping("/importMorder")
	public ModelAndView importMorderData(@RequestParam("file") MultipartFile file) throws InvalidFormatException, ParseException {
		try {
			morderService.importMorder(file, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/morder");
	}

	@RequestMapping("/morderImport")
	public ModelAndView jobImport() {
		ModelAndView model = new ModelAndView("morderImport");
		return model;
	}
	
//	@RequestMapping("/error")
//	public ModelAndView error() {
//		ModelAndView model = new ModelAndView("error");
//		return model;
//	}
	
	//當在網址斜線後面輸入 morder 後，便會導入這個 method 進行工作

	@RequestMapping("/morder")
	public ModelAndView morder() {
		Morder tMorder = morderService.queryMorderByStateId("aaa");
		Pageable tPageRequest = PageRequest.of(0, 20, Sort.by("id"));
		List<Morder> tMorder2 = morderService.queryAllMorder();
		System.out.println(tMorder2);
		//PageRequest 的 method 要怎麼做
		List<Morder> tMorderList = morderService.queryMorderWithPageAndSort(tPageRequest);
		List<MorderBean> tMorderBeanList = new ArrayList<MorderBean>();
		for (Morder m : tMorderList) {
			MorderBean tMorderBean = new MorderBean();
			BeanUtils.copyProperties(m, tMorderBean);
			tMorderBean.setPreSDateString(DateUtil.getjQueryStrDate(m.getPreSDate()));
			tMorderBean.setPreEDateString(DateUtil.getjQueryStrDate(m.getPreEDate()));
			tMorderBeanList.add(tMorderBean);
		}
		
//		//在這邊定義 ModelAndView 型態物件 morder，同時為 jsp 檔
//		List<Morder> tMorderList = morderService.queryAllMorder();
//		System.out.println(tMorderList);
//		List<MorderBean> tMorderBeanList = new ArrayList<MorderBean>();
//		for (Morder m : tMorderList) {
//			MorderBean tMorderBean = new MorderBean();
//			BeanUtils.copyProperties(m, tMorderBean);
//			tMorderBean.setPreSDateString(DateUtil.getjQueryStrDate(m.getPreSDate()));
//			tMorderBean.setPreEDateString(DateUtil.getjQueryStrDate(m.getPreEDate()));
//			tMorderBeanList.add(tMorderBean);
//		}
//		System.out.print("Hello"+tMorderBeanList);
		ModelAndView model = new ModelAndView("morder");
		model.addObject("morders", tMorderBeanList);
		model.addObject("morderBean", new MorderBean());
		return model;
	}
	
	
	//這個則是第二題的 url 導向的 method，會回傳一個叫 morderTime 的 jsp 檔案
	@RequestMapping("/morderTime")
	public ModelAndView morderTime() {
		ModelAndView model = new ModelAndView("morderTime");
		model.addObject("morderBean", new MorderBean());
		return model;
	}

	
	//此為動用查詢功能時會呼叫的 method (其實跟下面的一樣)，且會將結果回傳回去給特定的 jsp 頁面
	@RequestMapping(value = "/queryMorder", method = RequestMethod.POST)
	public ModelAndView queryMorder(@ModelAttribute("morderBean") MorderBean morderBean) {
		//為了要查詢，呼叫 morderService 的method去幫我們查詢特定ID的工單資訊 ( 此method若要看更詳細可以去morderServicelmpl裡面看 )
		Morder tMorder = morderService.queryMorderById(morderBean.getId());
		//後續回傳結果，會將結果塞入 Bean 裡面，有利後續我們使用set&get使用
		MorderBean tMorderBean = new MorderBean();
		BeanUtils.copyProperties(tMorder, tMorderBean);
		tMorderBean.setPreSDateString(DateUtil.getjQueryStrDate(tMorder.getPreSDate()));
		tMorderBean.setPreEDateString(DateUtil.getjQueryStrDate(tMorder.getPreEDate()));
		ModelAndView model = new ModelAndView("morder");
		model.addObject("morderBean", tMorderBean);
		return model;
	}
	// 此為動用查詢功能時會呼叫的 method，且會將結果回傳回去給特定的 jsp 頁面
	@RequestMapping(value = "/readMorder", method = RequestMethod.POST)
	public ModelAndView readMorder(@ModelAttribute("morderBean") MorderBean morderBean) {
		//為了要查詢，呼叫 morderService 的method去幫我們查詢特定ID的工單資訊 ( 此method若要看更詳細可以去morderServicelmpl裡面看 )
		Morder tMorder = morderService.queryMorderById(morderBean.getId());
		MorderBean tMorderBean = new MorderBean();
		//後續回傳結果，會將結果塞入 Bean 裡面，有利後續我們使用set&get使用
		BeanUtils.copyProperties(tMorder, tMorderBean);
		List<MorderBean> tMorderBeanList = new ArrayList<MorderBean>();
		//最終還是要將Bean型態的物件塞入 List 裡，才能讓前端自由取出相應的資料
		tMorderBeanList.add(tMorderBean);
		ModelAndView model = new ModelAndView("morder");
		model.addObject("morders", tMorderBeanList);
		return model;
		//後面建立、更新、移除都是差不多的手法
	}
	
	//此為動用查詢功能時會呼叫的 method ( 但這個跟 readMorder 的差別為他有設定特殊的標準 )，且會將結果回傳回去給特定的 jsp 頁面
	@RequestMapping(value = "/queryMorderByStartAndEnd", method = RequestMethod.POST)
	public ModelAndView queryMorderByStartAndEnd(@ModelAttribute("morderBean") MorderBean morderBean) {
		Pageable tPageRequest = PageRequest.of(0, 20, Sort.by("id"));
		String tStart = CommonUtil.checkNotEmptyOrNull(morderBean.getPreSDateString()) ? morderBean.getPreSDateString() : "1970/01/01";
		String tEnd = CommonUtil.checkNotEmptyOrNull(morderBean.getPreEDateString()) ? morderBean.getPreEDateString() : "2050/12/31";

		List<Morder> tMorderList = morderService.queryMorderByStartAndEndWithPageAndSort(DateUtil.getjQueryDate(tStart), DateUtil.getjQueryDate(tEnd), tPageRequest);
		List<MorderBean> tMorderBeanList = new ArrayList<MorderBean>();
		for (Morder m : tMorderList) {
			MorderBean tMorderBean = new MorderBean();
			BeanUtils.copyProperties(m, tMorderBean);
			tMorderBean.setPreSDateString(DateUtil.getjQueryStrDate(m.getPreSDate()));
			tMorderBean.setPreEDateString(DateUtil.getjQueryStrDate(m.getPreEDate()));
			tMorderBeanList.add(tMorderBean);
		}
		ModelAndView model = new ModelAndView("morderTime");
		model.addObject("morders", tMorderBeanList);
		model.addObject("morderBean", new MorderBean());
		return model;
	}
	
	//此為修正會用到的 method
	@RequestMapping(value = "/modifyMorder", method = RequestMethod.POST)
	public ModelAndView modifyMorder(@ModelAttribute("morderBean") MorderBean morderBean) {
		Morder tMorder = morderService.queryMorderById(morderBean.getId());
		tMorder.setPreEDate(DateUtil.getjQueryDate(morderBean.getPreEDateString()));
		tMorder.setQuan(morderBean.getQuan());
		morderService.saveMorder(tMorder);
		return new ModelAndView("redirect:/morder");
	}
	
	//此為移除會用到的 method
	@RequestMapping(value = "/removeMorder", method = RequestMethod.POST)
	public ModelAndView removeMorder(@ModelAttribute("morderBean") MorderBean morderBean) {
		if (morderBean.getRemoveId() != null) {
			for (int i = 0; i < morderBean.getRemoveId().length; i++) {
				Morder tPMorder = morderService.queryMorderById(morderBean.getRemoveId()[i]);
				morderService.removeMorder(tPMorder);
			}
		}

		return new ModelAndView("redirect:/morder");
	}

	//此為創建新資料會用到的 method
	@RequestMapping(value = "/createMorder", method = RequestMethod.POST)
	public ModelAndView createMorder(@ModelAttribute("morderBean") MorderBean morderBean) {
		Morder tMorder = new Morder();
		morderBean.setId(morderBean.getId().replace(",", ""));
		BeanUtils.copyProperties(morderBean, tMorder);
		tMorder.setPreSDate(DateUtil.getjQueryDate(morderBean.getPreSDateString()));
		tMorder.setPreEDate(DateUtil.getjQueryDate(morderBean.getPreEDateString()));
		morderService.saveMorder(tMorder);
		return new ModelAndView("redirect:/morder");
	}

	//此為使用 inner join ( 也就是第二題頁面會用到的 method )
	@RequestMapping("/morderDetail")
	public ModelAndView morderDetail() {
		List<MorderDetail> tMorderDetailList = morderService.queryMorderDetail();
		ModelAndView model = new ModelAndView("morderDetail");
		model.addObject("morderDetails", tMorderDetailList);
		return model;
	}
}