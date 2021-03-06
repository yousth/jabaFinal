package com.kh.jaba.admin.store.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.jaba.biz.model.domain.Biz;
import com.kh.jaba.biz.model.service.BizService;
import com.kh.jaba.client.custom.model.domain.Custom;
import com.kh.jaba.client.custom.model.service.CustomService;
import com.kh.jaba.client.menu.model.domain.Menu;
import com.kh.jaba.client.menu.model.service.MenuService;

@Controller
public class AdminStoreContoller {
	@Autowired
	private MenuService menuService;

	@Autowired
	private Menu menu;

	@Autowired
	private BizService bizService;

	@Autowired
	private Biz biz;

	@Autowired
	private CustomService customService;

	@Autowired
	private Custom custom;

	@RequestMapping(value = "admin/store/selectAdminStore.do", method = RequestMethod.GET)
	public ModelAndView selectAdminStore(ModelAndView mv, HttpServletRequest request) {
		List<Biz> storeList = bizService.selectAllStore();
		System.out.println(storeList.size() + "개의 Biz 정보 가져옴");

		request.getSession().setAttribute("storeList", storeList);

		mv.setViewName("admin/adminStore");
		return mv;
	}

	@RequestMapping(value = "admin/store/adminStoreDetail.do", method = RequestMethod.GET)
	public ModelAndView adminStoreDetail(ModelAndView mv, HttpServletRequest request) {
		String store_name = null;
		if (request.getParameter("store_name") == null) {
			Biz storeDetailTemp = (Biz) request.getSession().getAttribute("storeDetail");
			store_name = storeDetailTemp.getStore_name();
			System.out.println("세션에 담긴 store_name: " + store_name);
		} else {
			store_name = request.getParameter("store_name");
			System.out.println(store_name);
			System.out.println("parameter에 담긴 store_name: " + store_name);
		}
		// 스토어의 Detail 정보
		Biz storeDetail = bizService.selectStoreByName(store_name);
		request.getSession().setAttribute("storeDetail", storeDetail);
		System.out.println(storeDetail);
		// 메뉴정보 List
		List<Menu> menuList = menuService.selectMenuList(storeDetail.getStore_id());
		request.getSession().setAttribute("menuList", menuList);
		System.out.println(menuList);
		System.out.println(storeDetail.getStore_name() + "의 메뉴정보 " + menuList.size() + "개를 가져왔음");
		mv.setViewName("admin/adminStoreDetail");
		return mv;
	}

	// 매장 Detail 정보 수정 페이지로 이동
	@RequestMapping(value = "/admin/store/updateStoreDetail.do", method = RequestMethod.GET)
	public ModelAndView updateStoreDetail(ModelAndView mv, HttpServletRequest request) {

		mv.setViewName("admin/adminStoreUpdate");
		return mv;
	}

	// 매장 Detail 정보 수정
	@RequestMapping(value = "/admin/store/updateStore.do", method = RequestMethod.POST)
	@ResponseBody
	public void updateStore(HttpServletRequest request) {
		Biz storeDetail = (Biz) request.getSession().getAttribute("storeDetail");
		String store_id = storeDetail.getStore_id();
		String store_time = null;
		String store_description = null;
		String store_img = null;

		// store_time 이 받아온 값이 있으면 받아온 값을 넣어주고 아니면 기존 값을 사용
		if (request.getParameter("store_time") != null) {
			store_time = request.getParameter("store_time");
		} else {
			store_time = storeDetail.getStore_time();
		}

		if (request.getParameter("store_description") != null) {
			store_description = request.getParameter("store_description");
		} else {
			store_description = storeDetail.getStore_description();
		}

		if (request.getParameter("store_img") != null) {
			store_img = request.getParameter("store_img");
		} else {
			store_img = storeDetail.getStore_img();
		}

		biz.setStore_id(store_id);
		biz.setStore_description(store_description);
		biz.setStore_time(store_time);
		biz.setStore_img(store_img);

		bizService.updateStoreDetail(biz);

	}

	// =====================================================MENU=====================================================
	// 메뉴 Detail 페이지 이동
	@RequestMapping(value = "/admin/store/adminMenuDetail.do", method = RequestMethod.GET)
	public ModelAndView adminMenuDetail(ModelAndView mv, HttpServletRequest request) {
		// 메뉴 디테일 정보를 얻어옴
		String menu_id = null;
		if (request.getParameter("menu_id") != null) {
			menu_id = request.getParameter("menu_id");
			// 메뉴 디테일 정보를 세션에 담기
			Menu menuDetail = menuService.selectOneMenuByMenuId(menu_id);
			request.getSession().setAttribute("menuDetail", menuDetail);

			// 해당 메뉴의 커스텀 리스트들을 세션에 담기
			List<Custom> customList = customService.selectListByMenuId(menu_id);
			System.out.println(customList.size() + " 개의 커스텀 목록을 가져왔습니다.");

			request.getSession().setAttribute("customList", customList);
		} else {
			System.out.println("메뉴 정보를 얻어올 수 없습니다.");
		}

		mv.setViewName("admin/adminMenuDetail");
		return mv;
	}

	// 메뉴 추가 페이지로 이동
	@RequestMapping(value = "/admin/store/menuWrite.do", method = RequestMethod.GET)
	public ModelAndView menuWrite(ModelAndView mv, HttpServletRequest request) {

		mv.setViewName("admin/adminMenuWrite");
		return mv;
	}

	// 메뉴 Detail 정보 수정 페이지로 이동
	@RequestMapping(value = "/admin/store/menuUpdateDetail.do", method = RequestMethod.GET)
	public ModelAndView updateMenuDetail(ModelAndView mv, HttpServletRequest request) {

		mv.setViewName("admin/adminMenuUpdate");
		return mv;
	}

	// 메뉴 update
	@RequestMapping(value = "/admin/store/updateMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public void updateMenu(HttpServletRequest request) {
		Menu menuDetail = (Menu) request.getSession().getAttribute("menuDetail");
		String menu_id = menuDetail.getMenu_id();
		String menu_name = null;
		String menu_img = null;
		String menu_description = null;
		String menu_category = null;
		int menu_price = 0;
		int result = 0;

		// null 값이면 기존에 있던 정보로 변경
		if (request.getParameter("menu_name") != null) {
			menu_name = request.getParameter("menu_name");
		} else {
			menu_name = menuDetail.getMenu_name();
		}
		if (request.getParameter("menu_img") != null) {
			menu_img = request.getParameter("menu_img");
		} else {
			menu_img = menuDetail.getMenu_img();
		}
		if (request.getParameter("menu_description") != null) {
			menu_description = request.getParameter("menu_description");
		} else {
			menu_description = menuDetail.getMenu_description();
		}
		if (request.getParameter("menu_category") != null) {
			menu_category = request.getParameter("menu_category");
		} else {
			menu_category = menuDetail.getMenu_category();
		}
		if (Integer.parseInt(request.getParameter("menu_price")) != 0) {
			menu_price = Integer.parseInt(request.getParameter("menu_price"));
		} else {
			menu_price = menuDetail.getMenu_price();
		}

		menu.setMenu_id(menu_id);
		menu.setMenu_name(menu_name);
		menu.setMenu_img(menu_img);
		menu.setMenu_category(menu_category);
		menu.setMenu_description(menu_description);
		menu.setMenu_price(menu_price);

		result = menuService.updateMenu(menu);
		if (result == 1) {
			System.out.println(menu.getMenu_id() + "의 메뉴정보 업데이트 완료");
		} else {
			System.out.println("실패");
		}
	}

	// 메뉴 insert
	@RequestMapping(value = "/admin/store/insertMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView insertMenuDo(HttpServletRequest request, @RequestParam(name = "upfile") MultipartFile report,
			ModelAndView mv) {
		Biz storeDetail = (Biz) request.getSession().getAttribute("storeDetail");
		String store_id = storeDetail.getStore_id();
		System.out.println(store_id);
		String menu_name = request.getParameter("menu_name");
		int menu_price = Integer.parseInt(request.getParameter("menu_price"));

		String menu_description = request.getParameter("menu_description");
		String menu_category = request.getParameter("menu_category");
		String store_name = storeDetail.getStore_name();
		System.out.println(store_name);
		int result = 0;
		// menu_name 중복 확인 해야함
		try {
			// 첨부파일 저장
			if (report != null && !report.equals("")) {
				saveFile(report, request);
			}
			System.out.println(report.getOriginalFilename());
			menu.setMenu_img(report.getOriginalFilename()); // 저장된 파일명을 vo에 set
		} catch (Exception e) {
			e.printStackTrace();
		}

		menu.setStore_id(store_id);
		menu.setMenu_name(menu_name);
		menu.setMenu_price(menu_price);

		menu.setMenu_description(menu_description);
		menu.setMenu_category(menu_category);
		result = menuService.insertMenu(menu);
		if (result == 1) {
			System.out.println(store_id + "의 메뉴 " + menu_name + " 추가 완료");
		} else {
			System.out.println(store_id + "의 메뉴 " + menu_name + " 추가 실패");
		}
		// session에 store_name이 있으면 채워질지 안채워질지 고민
		request.getSession().setAttribute("storeDetail", storeDetail);
		mv.setViewName("redirect:adminStoreDetail.do");
		return mv;

	}

	// 메뉴 삭제 메소드
	@RequestMapping(value = "admin/store/menuDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public void deleteMenu(HttpServletRequest request) {
		Menu menuDetail = (Menu) request.getSession().getAttribute("menuDetail");
		String menu_id = menuDetail.getMenu_id();
		int result = 0;
		int customUpdateResult = 0;

		System.out.println("삭제할 현재 menu_id : " + menu_id);

		result = menuService.deleteMenu(menu_id);
		if (result == 1) {
			System.out.println(menu_id + " 메뉴 삭제 완료");
		} else {
			System.out.println(menu_id + " 메뉴 삭제 실패");
		}

		// 삭제한 메뉴의 커스텀 정보들도 전부 바꿔줘야함
		customUpdateResult = customService.updateCustomStatusByMenuId(menu_id);
		if (customUpdateResult != 0) {
			System.out.println(customUpdateResult + " 개의 커스텀을 삭제했습니다.");
		} else {
			System.out.println("메뉴 삭제시 연관된 커스텀을 삭제하지 못했습니다.");
		}
	}

	// =====================================================CUSTOM=====================================================
	// custom detail
	@RequestMapping(value = "/admin/store/customDetail.do", method = RequestMethod.GET)
	public ModelAndView customDetail(ModelAndView mv, HttpServletRequest request) {
		String custom_id = request.getParameter("custom_id");
		Custom customDetail = customService.selectOneCustomByCustomId(custom_id);
		if (customDetail == null) {
			System.out.println(custom_id + "의 커스텀 정보를 가져오지 못했습니다.");
		} else {
			request.getSession().setAttribute("customDetail", customDetail);
		}
		mv.setViewName("admin/adminCustomDetail");
		return mv;
	}

	// custom 진입
	@RequestMapping(value = "/admin/store/customUpdateDetail.do", method = RequestMethod.GET)
	public ModelAndView updateCustomDetail(ModelAndView mv, HttpServletRequest request) {
		String custom_id = request.getParameter("custom_id");
		Custom customDetail = customService.selectOneCustomByCustomId(custom_id);
		if (customDetail == null) {
			System.out.println(custom_id + "의 커스텀 정보를 가져오지 못했습니다.");
		} else {
			request.getSession().setAttribute("customDetail", customDetail);
		}
		mv.setViewName("admin/adminCustomUpdate");
		return mv;
	}

	// 커스텀 update
	@RequestMapping(value = "/admin/store/customUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public void updateCustom(HttpServletRequest request) {
		Custom customDetail = (Custom) request.getSession().getAttribute("customDetail");
		String custom_id = customDetail.getCustom_id();
		String custom_name = null;
		String custom_category = null;
		int custom_price = 0;
		int result = 0;

		// null 값이면 기존에 있던 정보로 변경
		if (request.getParameter("custom_name") != null) {
			custom_name = request.getParameter("custom_name");
		} else {
			custom_name = customDetail.getCustom_name();
		}
		if (request.getParameter("custom_category") != null) {
			custom_category = request.getParameter("custom_category");
		} else {
			custom_category = customDetail.getCustom_category();
		}
		if (request.getParameter("custom_price") != null) {
			custom_price = Integer.parseInt(request.getParameter("custom_price"));
		} else {
			custom_price = customDetail.getCustom_price();
		}

		custom.setCustom_id(custom_id);
		custom.setCustom_name(custom_name);
		custom.setCustom_category(custom_category);
		custom.setCustom_price(custom_price);

		result = customService.updateCustom(custom);

		if (result == 1) {
			System.out.println(custom.getCustom_id() + "의 커스텀정보 업데이트 완료");
		} else {
			System.out.println("커스텀정보 업데이트 실패");
		}
	}

	// custom write page 진입
	// 메뉴 Detail 정보 수정 페이지로 이동
	@RequestMapping(value = "/admin/store/customWrite.do", method = RequestMethod.GET)
	public ModelAndView customWrite(ModelAndView mv, HttpServletRequest request) {

		mv.setViewName("admin/adminCustomWrite");
		return mv;
	}

	// insert custom
	@RequestMapping(value = "/admin/store/customAdd.do", method = RequestMethod.POST)
	@ResponseBody
	public void customAdd(HttpServletRequest request) {
		Menu menuDetail = (Menu) request.getSession().getAttribute("menuDetail");
		String menu_id = menuDetail.getMenu_id();
		String custom_name = request.getParameter("custom_name");
		int custom_price = Integer.parseInt(request.getParameter("custom_price"));
		String custom_category = request.getParameter("custom_category");

		custom.setCustom_name(custom_name);
		custom.setCustom_category(custom_category);
		custom.setCustom_price(custom_price);
		custom.setMenu_id(menu_id);

		int result = customService.insertCustom(custom);

		if (result == 1) {
			System.out.println(custom.getCustom_name() + "의 커스텀 추가 완료");
		} else {
			System.out.println("커스텀 추가 실패");
		}
	}

	// 커스텀 삭제 메소드
	@RequestMapping(value = "admin/store/customDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public void deleteCustom(HttpServletRequest request) {
		Custom customDetail = (Custom) request.getSession().getAttribute("customDetail");
		String custom_id = customDetail.getCustom_id();
		int result = 0;

		System.out.println("삭제할 현재 custom_id : " + custom_id);

		result = customService.updateCustomStatus(custom_id);
		if (result == 1) {
			System.out.println(custom_id + " 커스텀 삭제 완료");
		} else {
			System.out.println(custom_id + " 커스텀 삭제 실패");
		}
	}

// ===================================================== 파일 관련 메소드 =====================================================
	private void saveFile(MultipartFile report, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		Biz storeDetail = (Biz) request.getSession().getAttribute("storeDetail");
		String savePath = root + "\\" + storeDetail.getStore_name();
		File folder = new File(savePath);
		if (!folder.exists()) {
			folder.mkdirs(); // 폴더가 없다면 생성한다.
		}
		String filePath = null;
		try {
			// 파일 저장
			System.out.println(report.getOriginalFilename() + "을 저장합니다.");
			System.out.println("저장 경로 : " + savePath);

			filePath = folder + "\\" + report.getOriginalFilename();
			report.transferTo(new File(filePath)); // 파일을 저장한다
			System.out.println("파일 명 : " + report.getOriginalFilename());
			System.out.println("파일 경로 : " + filePath);
			System.out.println("파일 전송이 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}
	}
}