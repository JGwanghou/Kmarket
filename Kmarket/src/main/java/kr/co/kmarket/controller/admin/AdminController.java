package kr.co.kmarket.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
/*
*  관리자 전용 컨트롤러
*  관리자 구분 : 최고관리자, 판매자
*  1. Kmarket에 등록된 모든 상품목록을 DB에서 불러온다.
*  2. 조건 : 관리자는 모든 상품을 볼 수 있다. 단, 판매자는 자신이 판매한 상품만 볼 수 있다.
* */
@Slf4j
@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	// 관리자 페이지 index
	@GetMapping(value = { "admin", "admin/index"})
	public String index() {
		return "admin/index";
	}

	// 관리자 상품 목록 페이지
	@GetMapping("admin/product/list")
	public String list(String pg, Model m, @AuthenticationPrincipal MyUserDetails myUserDetails,
					   @RequestParam(value = "searchType", required = false) String searchType, // 검색을 안할수도 있기때문에 false
					   @RequestParam(value = "keyword", required = false) String keyword) // 위와 동일
			throws Exception {

		UserEntity member =  myUserDetails.getUser(); // 사용자 인증된 객체
		String seller = member.getUid(); // 로그인 한 사람의 uid
		int level = member.getLevel(); // 로그인 한 사람의 level

		/**
		 * 페이징 처리 로직
		 * level: admin.xml로 레벨을 넘겨 조건문 시작하게 함.
		 * seller: 판매자인 경우, 레벨에 따른 조건문 로직 수행으로 uid 필요함. uid 넘겨 자신의 상품만 count
		 * searchType(상품명, 상품코드, 판매자) 타입에 따라 조건문 로직 수행
		 * keyword: 위 타입에 해당되는 검색어 상품만 count
		 **/
		int total = service.selectCountTotal(level, seller, searchType, keyword);
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		int lastPage = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPage);

		List<ProductVO> products = service.selectProducts(start, seller, searchType, keyword, level);

		m.addAttribute("products", products);
		m.addAttribute("currentPage", currentPage);
		m.addAttribute("lastPage", lastPage);
		m.addAttribute("pageStartNum", pageStartNum);
		m.addAttribute("groups", groups);
		m.addAttribute("searchType", searchType);
		m.addAttribute("keyword", keyword);

		return "admin/product/list";
	}

	/** 삭제 버튼이 2가지 존재 **/
	// [삭제]클릭, 상품삭제
	@PostMapping("admin/product/oneDelete")
	@ResponseBody
	public Map<String, Object> productOneDelete(@RequestParam("chkNo") String chkNo){
		int result = 0;

		result = service.deleteProduct(chkNo);

		Map<String, Object> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

	// 체크 후, 체크박스 열 삭제버튼 클릭
	@PostMapping("admin/product/delete")
	@ResponseBody
	public Map<String, Object> productDelete(@RequestParam("valueArr") String[] valueArr){
		log.info("delete Product ...");
		int size = valueArr.length;
		int result = 0;

		// prodNo 받아오기 때문에 647,646,645 이렇게 받아온다.
		for(int i=0; i<size; i++){
			result = service.deleteProduct(valueArr[i]);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

	// 상품정보 수정
	@PostMapping("admin/product/update")
	@ResponseBody
	public Map update(@RequestBody ProductVO vo){
		int result = 0;
		result = service.updateProduct(vo);

		Map map = new HashMap();
		map.put("result", result);

		return map;
	}


	
	// 상품 등록 페이지
	@GetMapping("admin/product/register")
	public String register(Model model) {
		
		// 카테고리 분류
		List<CateVO> cate1 = service.selectCate1();
		model.addAttribute("cate", cate1);
		
		return "admin/product/register";
	}
	
	// 상품 등록
	@PostMapping("admin/product/register")
	public String register(ProductVO vo, HttpServletRequest req) {
		vo.setSeller("admin");
		vo.setIp(req.getRemoteAddr());
		
		service.register(vo);
		
		return "redirect:/admin/product/register";
	}

	// 카테고리 분류
	@ResponseBody
	@GetMapping("admin/register/selectCate2")
	public Map<String, List<CateVO>> selectCate2(@RequestParam String cate1) {
		List<CateVO> cate2 = service.selectCate2(cate1);
		
		Map<String, List<CateVO>> map = new HashMap<>();
		map.put("result", cate2);
		
		return map;
	};




}
