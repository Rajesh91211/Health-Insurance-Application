package com.example.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot.entity.Register;
import com.example.springboot.service.RegisterService;
//@CrossOrigin(origins = "http://localhost:3000")
@Controller // This annotation marks this class as a REST controller
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private RegisterService registerService;
    
    
    @GetMapping("/home")  
    public String showHomePage(Model model) {
        model.addAttribute("message", "Welcome to the Home page!");
        return "home"; // This should match the Thymeleaf file name (registration.html)
    }
    
    @GetMapping("/policies")  
    public String showPoliciesPage(Model model) {
        model.addAttribute("message", "Welcome to the Policies page!");
        return "policcciesss"; // This should match the Thymeleaf file name (registration.html)
    }
    
    @GetMapping("/claims")  
    public String showClaimsPage(Model model) {
        model.addAttribute("message", "Welcome to the claims page!");
        return "claims"; // This should match the Thymeleaf file name (registration.html)
    }
    
    @GetMapping("/about")  
    public String showAboutPage(Model model) {
        model.addAttribute("message", "Welcome to the about page!");
        return "about"; // This should match the Thymeleaf file name (registration.html)
    }
    
    @GetMapping("/contact")  
    public String showContactPage(Model model) {
        model.addAttribute("message", "Welcome to the contact page!");
        return "contact"; // This should match the Thymeleaf file name (registration.html)
   }
    
    @GetMapping("/login1")
	public String showForm(Model model)
	{
		model.addAttribute("reg", new Register());
		
		return  "looginn";
		
	}
    
    @GetMapping("/silver-plan")
    public String silverPlanPage() {
        return "silverPlan"; // Returns the silver-plan.html template
    }
      
    @GetMapping("/selfPlandetails")
    public String selfPlanPage() {
        return "selfPlandetails"; // Returns the silver-plan.html template
    }
      
    
    
    @PostMapping("/custReg")
    public ResponseEntity<Map<String, String>> processRegister(@RequestBody Register register, Model model) {
        try {
            registerService.saveDetails(register);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration successful! Please log in.");
            return ResponseEntity.ok(response); // 200 OK with JSON response
        } catch (Exception e) { // Catch any exception
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Registration failed. Please check your input.");
            return ResponseEntity.badRequest().body(errorResponse); // 400 Bad Request with JSON error
        }
    }
    
    
    @PostMapping("/custLogin")
    public ResponseEntity<Map<String, String>> processLogin(@RequestBody Map<String, String> credentials, Model model) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Fetch user from database (assuming you have a findByEmail method in your repository)
        Register user = registerService.findByEmail(email); 

        if (user!= null && user.getPassword().equals(password)) { // Check password match
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); 
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    

    

    
    
    

    
    
    
    
 
    
    
    
}