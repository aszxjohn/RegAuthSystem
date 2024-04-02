package com.example.RegAuthSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegAuthSystem.service.IAdminAccountService;
import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.RegAuthSystem.service.dto.RegisterUserDto;
import com.example.orm.entity.AdminAccount;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * 新用戶審核管理 Controller
 * 
 * 該 Controller 負責新用戶註冊的審核，並最後確認是否給予使用權限。
 * 
 * 後台的審核系統這邊做簡化，因為目前的規劃中後台帳號的設計並不是後續研究的重點，
 * 所以將後台相關的處理都使用HardCode處理，此Controller只著重在流程上的模組化
 */
@RestController
@RequestMapping("/admin/register")
public class RegistrationReviewController {

	@Autowired
	private IAdminAccountService adminAccountService;
	
	/**
	 * 後台的帳號只能取得屬於自己要審閱的審核單，
	 * 所以當使用者僅來此處後，原先需要透過Token來判斷使用者是誰並擁有什麼權限，
	 * 但這邊簡化成我手動傳入admin 的帳號密碼，
	 * 而不是透過Token判斷
	 * 
	 */
	@GetMapping(value = "/user_list")
	public ResponseEntity<Object> getRegisterUserList(@RequestHeader("Username") String username, @RequestHeader("Password") String password) {
		// 實現註冊功能的程式碼
		Optional<AdminAccount> aOptional = 	adminAccountService.getAccount();
		System.out.print(aOptional.get().getAccount());
		return null;
	}
	
	
	
	
	
	
	
}
