## `Photo Gallery`
>Spring Boot, Hibernate, MySQL

Application imitating action of *On-line Photo Gallery*

====== NOTE! =====

This application is complete Maven-Project. Contains all necessary files.
  
  At first application launch automatically `Admin` is created.
  To Log-in to `Photo Gallery` use:
  
  	- username: admin
  
  	- password: password
  
Steps needed to launch application:
* create mySQL database `stock_exchange`
* import project to Your IDE
* in `application.properties` define:

	-spring.datasource.username=YOUR mySQL USERNAME
	
    - spring.datasource.password= YOUR mySQL PASSWORD
 
 
    
=================

(Minimum Viable Product)
`Admin` can:
* log-in 
* scroll `Users`list
* add/edit/delete `Users`
* add `Photos` for `Users`

`User` can:
* log-in to his `Gallery`
* scroll his `Gallery`

Technologies:
* Spring Boot
* Hibernate 
* Thymeleaf

===== TO DO: ====
* Develop data validation while uploading images
* Solve photos display problem
* `User` personal password edition + encoding password before putting into database (jdbcrypt?)
* Filters
* Test
* Comments
*JavaScript for User gallery (dynamic show) and for all input forms
* Clean the code!