
package com.example.springboot.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entity.FamilyClaim;
import com.example.springboot.entity.IndividualClaim;
import com.example.springboot.entity.IndividualPlan;
import com.example.springboot.entity.Register;
import com.example.springboot.entity.RelationPrimaryInsured;
import com.example.springboot.entity.TreatmentType;
import com.example.springboot.service.FamilyClaimService;
import com.example.springboot.service.IndividualClaimService;
import com.example.springboot.service.IndividualPlanService;
import com.example.springboot.service.MailService;
import com.example.springboot.service.RegisterService;

import jakarta.servlet.http.HttpSession;
//@CrossOrigin(origins = "http://localhost:3000")
@Controller // This annotation marks this class as a REST controller
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private MailService mailService;
	
    @Autowired
    private RegisterService registerService;
	
	@Autowired
    private IndividualClaimService individualclaimService;
	
	@Autowired
    private FamilyClaimService familyClaimService;
	
	@Autowired
	private IndividualPlanService individualPlanService;
	
	
//	@PostMapping("/submit_form")
//    public ResponseEntity<?> submitForm(@Valid @RequestBody IndividualPlan plan, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//        }
//        IndividualPlan savedPlan = individualPlanService.savePlan(plan);
//        return ResponseEntity.ok(savedPlan); // Return the saved plan object
//    }
	
	
	/*==== Individual Plan=========
	@PostMapping("/PlanSubmit")
    public ResponseEntity<String> submitIndividualPlan(
            @RequestParam("planType") String planType,
            @RequestParam("planPrice") String planPrice,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("age") Integer age,
            @RequestParam("email") String email,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("pinCode") String pinCode,
            BindingResult bindingResult) {

        try {
            IndividualPlan plan = new IndividualPlan();
            plan.setPlanType(planType);
            plan.setPlanPrice(planPrice);
            plan.setFirstName(firstName);
            plan.setLastName(lastName);
            plan.setAge(age);
            plan.setEmail(email);
            plan.setDateOfBirth(dateOfBirth);
            plan.setPhone(phone);
            plan.setAddress(address);
            plan.setCity(city);
            plan.setState(state);
            plan.setPinCode(pinCode);

            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
            }

            IndividualPlan savedPlan = individualPlanService.savePlan(plan);

            System.out.println("Individual Plan submitted successfully! Plan ID: " + savedPlan.getPlanId());

            return ResponseEntity.ok("Individual Plan submitted successfully. Plan ID: " + savedPlan.getPlanId());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing plan: " + e.getMessage());
        }
    }  */
	

	//================individual claim form handling...=====================.
	@PostMapping("/submit")
    public ResponseEntity<String> submitSelfClaim(
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

            individualclaimService.saveClaim(claim);
            
            System.out.println(" Individual Claim submitted successfully! with Claim ID:" + claimId);
            
         // Send email upon successful claim submission
            String recipient = "rajeshtangellamudi329@example.com"; // Replace with your email
            String subject = "Sucessful Claim Submit On Individual Plan";
            String message = "A new claim has been submitted with Claim ID: " + claimId;

            mailService.sendGeneralMessageEmail(recipient, subject, message);

            
            return ResponseEntity.ok("Individual Claim submitted successfully, Check mail for Confirmation..");
            
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing claim: " + e.getMessage());
        }
    }
	
	
	@GetMapping("/claims/{claimId}")
    public ResponseEntity<IndividualClaim> getClaimById(@PathVariable String claimId) {
        IndividualClaim claim = individualclaimService.getClaimById(claimId);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	
//========================================================================
	
//==================familyClaim handling.============================
	
	@PostMapping("/submit1")
    public ResponseEntity<String> submitFamilyClaim(
    		
    		
    		@RequestParam("claimId") String claimId,
    	    @RequestParam("primaryInsuredName") String primaryInsuredName,
    	    @RequestParam("patientName") String patientName,
    	    @RequestParam("relationPrimaryInsured") String relationPrimaryInsured,
    	    @RequestParam("hospitalName") String hospitalName,
    	    @RequestParam("dateOfAdmission") String dateOfAdmission,
    	    @RequestParam("diagnosis") String diagnosis,
    	    @RequestParam("treatmentType") String treatmentType,
    	    @RequestParam("claimAmount") Double claimAmount, // Use Double
    	    @RequestParam("hospitalBills") MultipartFile hospitalBills,
    	    @RequestParam("dischargeSummary") MultipartFile dischargeSummary,
    	    @RequestParam("relationshipProof") MultipartFile relationshipProof, // If this is a file
    	    @RequestParam("patientIdProof") MultipartFile patientIdProof){

        try {
        	//new code for checking missing file
        	System.out.println("Received claimId: " + claimId);
            System.out.println("Received primaryInsuredName: " + primaryInsuredName);
            System.out.println("Received patientIdProof size: " + patientIdProof.getSize());

            if (patientIdProof.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: patientIdProof file is missing!");
            }
        	
        	
            FamilyClaim claim = new FamilyClaim();
            claim.setClaimId(claimId);
            claim.setPrimaryInsuredName(primaryInsuredName);
            claim.setPatientName(patientName);
            claim.setRelationPrimaryInsured(Enum.valueOf(RelationPrimaryInsured.class,relationPrimaryInsured ));
            claim.setHospitalName(hospitalName);
            claim.setDateOfAdmission(LocalDate.parse(dateOfAdmission));
            claim.setDiagnosis(diagnosis);
            claim.setTreatmentType(Enum.valueOf(TreatmentType.class, treatmentType));
            claim.setClaimAmount(claimAmount);
            claim.setHospitalBills(hospitalBills.getBytes());
            claim.setDischargeSummary(dischargeSummary.getBytes());
            claim.setRelationshipProof(relationshipProof.getBytes());
            claim.setPatientIdProof(patientIdProof.getBytes());
            claim.setSubmissionDate(LocalDate.now());

            familyClaimService.saveFamilyClaim(claim);
            
            System.out.println("Family Claim submitted successfully!");
            
         // Send email upon successful claim submission
            String recipient = "rajeshtangellamudi329@example.com"; // Replace with your email
            String subject = "Sucessful Claim Submit On FamilyPlan";
            String message = "A new claim has been submitted with Claim ID: " + claimId;

            mailService.sendGeneralMessageEmail(recipient, subject, message);

            
            return ResponseEntity.ok("Family Claim submitted successfully,Check mail for Confirmation..");
            
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing claim: " + e.getMessage());
        }
    }
	
	@GetMapping("/familyclaims/{claimId}")
    public ResponseEntity<FamilyClaim> getClaimById1(@PathVariable String claimId) {
        FamilyClaim claim = familyClaimService.getClaimById(claimId);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	
	
	//=======================================================================
	

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
   // @PreAuthorize("hasRole('ROLE_USER')")
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
            response.put("message", "Registration successful!, Check email for verfication..");
            
         // Send registration success email
            String recipient = "rajeshtangellamudi329@example.com"; // Replace with your email
            String subject = "Successful Registration on Helath Insurance Platform ";
            String message = "A new user has registered with email: " + register.getEmail(); // Use register.getEmail()
            mailService.sendGeneralMessageEmail(recipient, subject, message);

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
            response.put("message", "Login successful check verification mail");
            
         // Send login success email
            String recipient = "rajeshtangellamudi329@example.com"; // Replace with your email
            String subject = "Successful Login on Health Insurance";
            String message = "User with email " + email + " has logged in.";
            mailService.sendGeneralMessageEmail(recipient, subject, message);
            
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