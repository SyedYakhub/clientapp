package com.twg.springboot.webappclient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.twg.springboot.webappclient.models.Entry;
import com.twg.springboot.webappclient.models.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController 
{
    @Autowired
	HttpSession session;
    
    
    @GetMapping("/login")
    public String loginpage()
    {
    	String viewname="login";
    	
    	return viewname;
    }
    
    
    @PostMapping("/authenticate")
    public String authenticateuser(@ModelAttribute User user, Model model)
    {
    	String viewname="login";
    	
    	RestTemplate template=new RestTemplate();
    	
    	User u=template.getForObject("http://localhost:8080/webapprestapi/users/username/"+user.getUsername(), User.class);
    	
    	if(u!=null && user.getPassword().equals(u.getPassword()))
    	{
    		viewname="userhomepage";
    		
    		model.addAttribute("user", u);
    		
    		session.setAttribute("user", u);
    		
    		List<Entry> entries=null;
    		
    		try
    		{
    			ResponseEntity<List<Entry>> response=template.exchange("http://localhost:8080/webapprestapi/entries/userid/"+u.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Entry>>() {});
    			
    			entries=response.getBody();
    		}
    		
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		
    		model.addAttribute("entrieslist", entries);
    	}
    	
    	return viewname;
    }
    
    
    
    @GetMapping("/register")
    public String registeruser()
    {
    	String viewname="register";
    	
    	return viewname;
    }
    
    
    
    @PostMapping("/registersuccess")
    public String registersuccess(@ModelAttribute User user)
    {
    	String viewname="registersuccess";
    	
    	RestTemplate template=new RestTemplate();
    	
    	template.postForObject("http://localhost:8080/webapprestapi/users/", user, User.class);
    	
    	return viewname;
    }
    
    
    
    @GetMapping("/displayentry")
    public String displayentry(@RequestParam("id") long id, Model model)
    {
    	String viewname="displayentry";
    	
    	RestTemplate template=new RestTemplate();
    	
    	Entry entry=template.getForObject("http://localhost:8080/webapprestapi/entries/id/"+id, Entry.class);
    	
    	model.addAttribute("entry", entry);
    	
    	return viewname;
    	
    }
    
    
    
    @PostMapping("/userhome")
    public String userhome(Model model)
    {
    	String viewname="userhomepage";
    	
    	User user1=(User) session.getAttribute("user");
    	
    	if (user1 == null) 
    	{
            // Redirect to login page or show error
            return "login";
        }
    	
    	List<Entry> entries=null;
    	
    	RestTemplate template=new RestTemplate();
    	
    	try
    	{
    		ResponseEntity<List<Entry>> response=template.exchange("http://localhost:8080/webapprestapi/entries/userid/"+user1.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Entry>>() {});
    		
    		entries=response.getBody();
    	}
    	
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	model.addAttribute("entrieslist", entries);
    	
    	return viewname;
    }
    
    
    
    @PostMapping("/addentry")
    public String addentry()
    {
    	String viewname="addentryform";
    	
    	return viewname;
    }
    
    
    
    @PostMapping("/saveentry")
    public String saveentry(@ModelAttribute("entry") Entry entry, Model model)
    {
    	String viewname="userhomepage";
    	
    	RestTemplate template=new RestTemplate();
    	
    	try
    	{
    		HttpEntity<Entry> request=new HttpEntity<>(entry);
    		
    		ResponseEntity<Entry> response=template.exchange("http://localhost:8080/webapprestapi/entries/", HttpMethod.POST, request, new ParameterizedTypeReference<Entry>() {});
    		
    		Entry createdentry=response.getBody();
    		
    		model.addAttribute("newentry", createdentry);
    		
    		
    		
    		User user1=(User) session.getAttribute("user");
    		
    		ResponseEntity<List<Entry>> entriesresponse=template.exchange("http://localhost:8080/webapprestapi/entries/userid/"+user1.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Entry>>() {});
    		
    		List<Entry> entries=entriesresponse.getBody();
    		
    		model.addAttribute("entrieslist", entries);
    		
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return viewname;
    }
    
    
    
    @GetMapping("/updateentry")
    public String updateentry(@RequestParam("id") long id, Model model)
    {
    	String viewname="displayupdateentry";
    	
    	RestTemplate template=new RestTemplate();
    	
    	Entry entry=template.getForObject("http://localhost:8080/webapprestapi/entries/id/"+id, Entry.class);
    	
    	model.addAttribute("entry", entry);
    	
    	User user1=(User) session.getAttribute("user");
    	
    	if(user1==null)
    	{
    		viewname="login";
    	}
    	
    	return viewname;
    }
    
    
    
    
    @PostMapping("/processentryupdate")
	public String processentryupdate(@ModelAttribute("entry") Entry entry, Model model)
	{
	    String viewname="userhomepage";
	    
	    RestTemplate template=new RestTemplate();
	    
	    try
	    {
	        // Create an HttpEntity object that includes the entry object as the request body
	        HttpEntity<Entry> request = new HttpEntity<>(entry);
	        
	        // Send a PUT request to the server to update the entry
	        template.exchange("http://localhost:8080/webapprestapi/entries/"+entry.getId(), HttpMethod.PUT, request, Entry.class);
	        
	        // Get the user from the session
	        User user1=(User) session.getAttribute("user");
	        
	        
	        
	        
	        // Send a GET request to the server to get the list of entries for the user
	        ResponseEntity<List<Entry>> entriesResponse = template.exchange("http://localhost:8080/webapprestapi/entries/userid/"+user1.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Entry>>() {});
	        
	        // Get the list of entries from the response
	        List<Entry> entries = entriesResponse.getBody();
	        
	        // Add the list of entries to the model
	        model.addAttribute("entrieslist", entries);
	    }
	    
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return viewname;
	}
	
	
	
	@GetMapping("/deleteentry")
	public String deleteentry(@RequestParam("id") long id, Model model)
	{
	    String viewname="userhomepage";
	    
	    RestTemplate template=new RestTemplate();
	    
	    try
	    {
	        // Send a DELETE request to the server to delete the entry
	        template.exchange("http://localhost:8080/webapprestapi/entries/"+id, HttpMethod.DELETE, null, Entry.class);
	        
	        
	        
	        
	        // Get the user from the session
	        User user1=(User) session.getAttribute("user");
	        
	        if(user1==null)
	        {
	            viewname="login";
	        }
	        else
	        {
	            // Send a GET request to the server to get the list of entries for the user
	            ResponseEntity<List<Entry>> entriesResponse = template.exchange("http://localhost:8080/webapprestapi/entries/userid/"+user1.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Entry>>() {});
	            
	            // Get the list of entries from the response
	            List<Entry> entries = entriesResponse.getBody();
	            
	            // Add the list of entries to the model
	            model.addAttribute("entrieslist", entries);
	        }
	    }
	    
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return viewname;
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) 
	{
	    session.invalidate();
	    
	    return "redirect:/login";
	}
    
}
