	package com.sg.facturacion;
	
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	
	
	@Controller
	public class ApplicationController {
	
	
		@GetMapping("/index")
		public String goHome(Model model) {
			
			return "index";
		}
	
		@GetMapping("/login")
		public String login() {
			return "login";
		}
	
		@GetMapping("/logout")
		public String logout() {
			return "login";
		}
	
	}
