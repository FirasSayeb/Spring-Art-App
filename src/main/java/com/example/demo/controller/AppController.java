package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Art;
import com.example.demo.domain.Client;

import com.example.demo.service.ArtService;
import com.example.demo.service.ClientService; 



@Controller
public class AppController {
	@Autowired 
	private ClientService service;
	@Autowired
	private ArtService art;
	@GetMapping("/") 
	public String index() { 
		return "home";
	}
	 

	@GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("client", new Client());
        return "login";
    } 
 
 @PostMapping("/signup") 
  public String signup(@ModelAttribute("client")Client c) {
	 service.save(c); 
		return "redirect:/"; 
  }   
    
		    
 @PostMapping("/login")
 public String login(@ModelAttribute("client")Client c, HttpSession session) {
     Client cl=service.findByEmailAndPassword(c.getEmail(), c.getPassword());
     if(cl != null && cl.getEmail().equals(c.getEmail()) && cl.getPassword().equals(c.getPassword())) {
         session.setAttribute("clientId", cl.getId()); // Add client ID to the session
         return "redirect:/listArt";
     }
     else {  
         return "redirect:/login";      
     }      
 }
 
  @GetMapping("/ajouter")
  public String ajouter(Model model) {
	  List<Art> listeart=art.getAll();   
	  List<Client> listclient = service.getAll();
	     model.addAttribute("listeart", listeart); 
	     model.addAttribute("listclient", listclient);
	     model.addAttribute("art", new Art());
	     return "ajouterart";    
  }    
  @RequestMapping(value = "/enregistre", method = RequestMethod.POST)
  public String enregistreart(@ModelAttribute("art") Art ar)
  {   
	  Long clientId = ar.getClient().getId(); 
	    
	    // Get the corresponding Client object from the database
	    Client client = service.getClientById(clientId);
	     
	    // Set the client field of the Art object to the corresponding Client object
	    ar.setClient(client);
      art.save(ar);   
      return "redirect:/listArt";        
  }      
  @GetMapping("/listArt")
  public String listArt(Model model, HttpSession session) {
      Long clientId = (Long) session.getAttribute("clientId"); // Get client ID from the session
      if (clientId == null) {
          return "redirect:/login"; // Redirect to login page if not logged in
      }

      List<Art> listeart = art.getAllByClientId(clientId); // Fetch arts by client ID
      for (Art art : listeart) {
          byte[] imageData = art.getData();
          String base64ImageData = Base64.getEncoder().encodeToString(imageData);
          art.setBase64ImageData(base64ImageData);
      }

      model.addAttribute("listeart", listeart);
      return "index";
  }

   
  @GetMapping("/editArt/{id}")
  public String editArt(@PathVariable(value = "id") Long id, Model model) {
      Art ar = art.get(id);
      List<Client> clients = service.getAll();
      model.addAttribute("art", ar);
      model.addAttribute("clients", clients);
      return "editArt";
  }   
  @PostMapping("/editArt")          
  public String updateArt(@ModelAttribute("art") Art ar) {
      // Update the art in your database using the service layer
      art.updateArt(ar);
      
      // Redirect to the list of arts page
      return "redirect:/listArt";
  }  
    



  @GetMapping("/deleteArt/{id}")
  public String deleteArt(@PathVariable(value = "id") Long id) {
      art.delete(id);
      return "redirect:/listArt";   
  }
  
 
  
}  


