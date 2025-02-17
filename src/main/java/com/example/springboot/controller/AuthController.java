package com.example.springboot.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entity.IndividualClaim;
import com.example.springboot.entity.Register;
import com.example.springboot.entity.TreatmentType;
import com.example.springboot.repostory.IndividualClaimRepository;
import com.example.springboot.service.IndividualClaimService;
import com.example.springboot.service.RegisterService;

import jakarta.servlet.http.HttpSession;
//@CrossOrigin(origins = "http://localhost:3000")
@Controller // This annotation marks this class as a REST controller
@RequestMapping("/api")
public class AuthController {

	   
    @Autowired
    private RegisterService registerService;
	
	@Autowired
    private IndividualClaimService claimService;
	
	@Autowired
	private IndividualClaimRepository individualClaimRepository;	

	
	
	
	
	@PostMapping("/submit")
    public ResponseEntity<String> submitClaim(
            @RequestParam("claimId") String claimId,
            @RequestParam("hospitalName") String hospitalName,
            @RequestParam("dateOfAdmission") String dateOfAdmission,
            @RequestParam("diagnosis") String diagnosis,
            @RequestParam("treatmentType") String treatmentType,
            @RequestParam("claimAmount") Double claimAmount,
            @RequestParam("hospitalBills") MultipartFile hospitalBills,
            @RequestParam("dischargeSummary") MultipartFile dischargeSummary,
            @RequestParam("medicalReports") MultipartFile medicalReports,
            @RequestParam("idProof") MultipartFile idProof) 
	{

        try {
            IndividualClaim claim = new IndividualClaim();
            claim.setClaimId(claimId);
            claim.setHospitalName(hospitalName);
            claim.setDateOfAdmission(LocalDate.parse(dateOfAdmission));
            claim.setDiagnosis(diagnosis);
            claim.setTreatmentType(Enum.valueOf(TreatmentType.class, treatmentType));
            claim.setClaimAmount(claimAmount);
            claim.setHospitalBills(hospitalBills.getBytes());
            claim.setDischargeSummary(dischargeSummary.getBytes());
            claim.setMedicalReports(medicalReports.getBytes());
            claim.setIdProof(idProof.getBytes());
            claim.setSubmissionDate(LocalDate.now());

            individualClaimRepository.save(claim);
            
            System.out.println("Claim submitted successfully!");
            
            return ResponseEntity.ok("Claim submitted successfully.");
            
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing claim: " + e.getMessage());
        }
    }
	
	
	@GetMapping("/claims/{claimId}")
    public ResponseEntity<IndividualClaim> getClaimById(@PathVariable String claimId) {
        IndividualClaim claim = claimService.getClaimById(claimId);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	

    
    @GetMapping("/policies")  
    public String showPoliciesPage(Model model) {
        model.addAttribute("message", "Welcome to the Policies page!");
        return "policess"; // This should match the Thymeleaf file name (registration.html)
    }
    

    
    @GetMapping("/claims")  
    public String showClaimsPage(Model model) {
        model.addAttribute("message", "Welcome to the claims page!");
        return "Claimss"; // This should match the Thymeleaf file name (registration.html)
    }
    
    @GetMapping("/home")  
    public String showAboutPage(Model model) {
        model.addAttribute("message", "Welcome to the about page!");
        return "aboutt"; // This should match the Thymeleaf file name (registration.html)
    }
    
    @GetMapping("/home1")
	public String showHomepage(Model model)
	{
		model.addAttribute("reg", new Register());
		
		return  "about1";
		
	}
    
    @GetMapping("/login1")
	public String showForm(Model model)
	{
		model.addAttribute("reg", new Register());
		
		return  "looginn";
		
	}
        
    @GetMapping("/individualplan")
    public String individualPage() {
        return "individualplan"; // Returns the family-plan.html template
    }
    
    @GetMapping("/familyPlandetails")
    public String familyPlanPage() {
        return "familyPlandetails"; // Returns the family-plan.html template
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
    public ResponseEntity<Map<String, String>> processLogin(@RequestBody Map<String, String> credentials, Model model, HttpSession session) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Register user = registerService.findByEmail(email); 

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userEmail", email); // Store user info in session
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); 
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/api/home1"; // Redirect to home after logout
    }
    
    


    

    
    
    

    
    
    
    
 
    
    
    
}