# Homie

/*
A README in general provides general info about the project, with detailed set-up and usage instructions. For the spike exercise, just a detailed guide on how to set up the app and run it is sufficient. Basically, explain how i can run the app on my system to evaluate your submission
*/

## Intro

Our full stack is as below, so we have to install & setup the related applications.

            F/E                            B/E
    Java script/html/css -> java -- JDBC (link by Tomcat) -- MySQL


## Set up instruction

1.MySQL setup

1.1	install MySQL 8.0.19 (Download MySQL: http://tiny.cc/6f4kuz)

*Set password as 123456, it would be convenitent for link with tomcat later.

1.2	Download my.ini file and move it to MySQL 8.0.19 directory (Download my.ini: http://tiny.cc/jg1kuz)

1.3	To access MySQL directory: right-click on "Finder" -> "go to file"

-> type the path: "/usr/local/mysql-8.0.19-macos10.15-x86_64"

1.4	Open terminal -> input command "echo $SHELL" -> check shell type is bash or zsh

![image](https://user-images.githubusercontent.com/91846668/136494546-321b2f53-67c9-4aca-8a87-16d42e80c129.png)

1.5	Go the user fold find the file (if your shell type is : bash -> .bash_profile or zsh -> .zshrc) 

![image](https://user-images.githubusercontent.com/91846668/136494590-c9cf1724-0214-4974-81c6-be9fa4f5779c.png)

*the file may be a hidden file, use "command + shift + ." to view the file
  
1.6	Open the file and add the line below in the file.

export PATH=/usr/local/mysql-8.0.19-macos10.15-x86_64/bin:$PATH

![image](https://user-images.githubusercontent.com/91846668/136631020-7add756f-25bd-4d76-ad15-ae4591187b52.png)
 
1.7	Go to terminal use command “mysqld --initialize –console” to initialize MySQL

(it would generate a temporary password, but just ignore it. The default password would be set when installing MySQL, remember the password you set.)

![image](https://user-images.githubusercontent.com/91846668/136494749-9ce62a52-7bbb-4059-955c-6ad26aed4d44.png)

1.8	Use command “mysqld --install” to install

![image](https://user-images.githubusercontent.com/91846668/136494781-e497350b-6502-402d-997c-9f66c0b43fbb.png)
 
1.9	Use command “mysqld” to open MySQL service

![image](https://user-images.githubusercontent.com/91846668/136494799-706f6eb4-eb5e-46fc-bbf4-d41438b92a9f.png)

1.10	Install MySQL workbench (Download MySQL workbench http://tiny.cc/7h1kuz)

1.11	Open MySQL workbench -> Edit connection

![image](https://user-images.githubusercontent.com/91846668/136494886-4c57270f-f2f7-4175-be9d-68bf1835e295.png)

1.12	Click “Store in Keychain” -> input the password you set before -> Test connection (it would show successful message if it works)

***If your password is not set 123456, please see 2.13 instruction.**

![image](https://user-images.githubusercontent.com/91846668/136494913-6188ccf9-c057-4a11-a129-b1f2629d05bc.png)

1.13	(Import tables) After connection -> go to scheme tab -> right-click on the left box -> create schema

-> name the schema **HOMII** -> click add SQL button on the up-left side 

-> copy the words from the link below -> paste to the sheet -> click execute button (flash sign) 

-> click refresh button on the up-right side of left box

(Download MySQL script: http://tiny.cc/3n2kuz)

![image](https://user-images.githubusercontent.com/91846668/136495072-74191733-237f-46ae-8a96-1138f1d8d4ea.png)

2.Eclipse & tomcat setup
	
2.1 install Eclipse EE (Download Eclipse: http://tiny.cc/ug1kuz)

2.2 install java JDK 1.8.0_201 (Download JDK: http://tiny.cc/bf4kuz)

2.3 Create an independent directory for this project -> copy the project folder & tomcat folder to the directory

(project folder: from Github / tomcat folder:download Tomcat v9 from http://tiny.cc/wn2kuz)

***If your MySQL root password is not 123456, please go to conf file -> open context.xml -> find password="123456" modify to your password 
 after download tomcat.**

2.4 Open Eclipse and choose the directory as workplace

2.5 Go to preference to set up Tomcat connection 

![image](https://user-images.githubusercontent.com/91846668/136631122-a5b5d9dc-bffe-4b63-a420-36002c1f2642.png)
 
Click add to add server

![image](https://user-images.githubusercontent.com/91846668/136496464-bebeb4be-857c-411e-b588-6687c081ef67.png)

Choose v9.0

![image](https://user-images.githubusercontent.com/91846668/136496416-c431768a-53e7-4ead-9140-a67c1384934b.png)

Choose tomcat directory which would be in your eclipse workplace folder for this project -> Choose Java SE 8[1.8.0_201]

![image](https://user-images.githubusercontent.com/91846668/136495484-1ed16d08-0a0c-4822-b183-9f7d07257a8d.png)

*if there is no Java SE 8 in the option -> click installed JREs to find the version

![image](https://user-images.githubusercontent.com/91846668/136495530-33016fcb-d68a-49ef-9de9-9eb5e0412022.png)

2.6 If there no error message -> run index.jsp -> the website would be opened!
 
![image](https://user-images.githubusercontent.com/91846668/136495726-d87dd890-406f-4f67-8564-32d71dea6bdc.png)


2.7 Exceptions: 

There may still have error after doing the procedure above, if it is, please check

*Right clickc HOMI -> Configure Build Path

![image](https://user-images.githubusercontent.com/91846668/136495775-895774a0-fdd0-4065-9073-a9ac1166b2b2.png)
 
*Check if some error in libraries -> if yes, re-add the JARs which show error

(Download javax.mail.jar: http://tiny.cc/zn2kuz)

(Download jstl.jar: http://tiny.cc/2o2kuz)

*Check if the JRE Library is JavaSE 1.8 -> if not, edit it to correct version
 
![image](https://user-images.githubusercontent.com/91846668/136495868-f62c9b8f-f265-4c49-96d6-c9d257a7aebc.png)


## Usage instruction

There are three required functions for this spike exercise: 

* 1.Entering an application to rent a property. 

  * a. Potential renters who would like to apply can create (or log into) an account, enter their information into a preliminary application, and submit it to rental owners. 

  * b. Rental owners can accept or reject submitted application.

* 2.Paying rent.

  * a. Your team can simulate a payment system by having users enter FAKE payment information and the system displaying a confirmation message.

  * b. Every tenant will be able to view their balance on the system.

  * c. Owners can add late fees or penalties.

  * d. Owners can also enter payment through their admin accounts.

* 3.Entering notices for maintenance.

  * a. Tenants (logged in) can input a maintenance request and whether it is a priority.

  * b. Owners can review and respond to maintenance requests.

Instructions:

- [x] **1.a create an account**

Click Login button on the up-right corner

![image](https://user-images.githubusercontent.com/91846668/136628541-852e0203-a741-4ddf-b1e1-ce8a871023ef.png)

Log in

![image](https://user-images.githubusercontent.com/91846668/136628739-859d2ec1-02e9-475c-ab6c-28c6c5044c53.png)

*if you forget your password, you can click forget password and you’ll receive a random password via your email, then go to member center change your password.

Sign up

Click sign up -> fill up the information -> Click sign up button below

*The member type you register is important, it would be different privilege between two roles.

*It would show error message if the data formation not as expected.

![image](https://user-images.githubusercontent.com/91846668/136628764-062b601c-28f7-4602-9264-8799f887edf2.png)

Send rental application

-> Click “My Rent” to view the rental status -> Click “Register new rent” to apply a new rental

![image](https://user-images.githubusercontent.com/91846668/136628787-e81afaa8-8267-4f10-ac94-29e7f94998e6.png)

-> fill up and send the form and the owner will review the application

![image](https://user-images.githubusercontent.com/91846668/136628798-c61b3926-b464-409e-9930-119c68a3ce42.png)

(Extra function) In “My rent” page, tenant can select opened cases to review the status

![image](https://user-images.githubusercontent.com/91846668/136628810-c7605c44-e247-4b39-a98e-6184eb7cb9fc.png)


- [x] **1.b Owner review the rental application**

Once the tenant sent an application, rental owner can get the application in his/her “My property” page.

-> Click the application you want to review

![image](https://user-images.githubusercontent.com/91846668/136628856-4c1415cb-b5ef-4ccb-85fe-282442bae6ce.png)
 
-> Review the application and select approve or reject 

*End data is must be filled.

![image](https://user-images.githubusercontent.com/91846668/136628867-06021ebf-eb94-4082-8cc8-7a3c60983dcf.png)
 
- [x] **2.a Set payment system**

Click "Member center" -> add the credit card information

(it would show error message if the data format is not as expected)

![image](https://user-images.githubusercontent.com/91846668/136628945-6a119bae-4126-48fc-86e7-440441db8c58.png)

- [x] **2.b View balance**

Click “My rent” to view the current balance

-> Click “make payment” -> Choose a registered credit card can make payment

*You can see the payment log once after you make payment.

![image](https://user-images.githubusercontent.com/91846668/136628968-3cace25f-1ab6-4286-965c-02a0a9f67224.png)

- [x] **2.c owner add late fees or penalties**

(log in owner account, mail: land1@wisc.edu password:1111)

Click “Payment System” -> select the tenant who you want to add penalties to -> select penalty enter the amount

*Once the amount sent, it would be shown in payment log.

![image](https://user-images.githubusercontent.com/91846668/136628981-2a91c110-6b1b-4c01-803f-97fc790b41de.png)

- [x] **2.d owner enter payment (same page as above)**

(log in owner account, mail: land1@wisc.edu password:1111)

Click “Payment System” -> select the tenant who makes the payment -> select payment -> enter the amount

*Once the amount sent, it would be shown in payment log.



- [x] **3.a Open a maintenance request**

Click the create case on the up-right corner 

-> fill up the information needed (description, photo, video, etc)

-> the priority box can define the urgency

![image](https://user-images.githubusercontent.com/91846668/136629015-10b50d36-13e2-4a88-b221-14dc4540133a.png)
 
- [x] **3.b Owner review & response to requests**

Click My property (all the apartment and case below to the landlord would be shown in this page) 

-> click the case you want to read

![image](https://user-images.githubusercontent.com/91846668/136629036-cbd66f19-7463-4cae-bff4-a29fbcb8c088.png)

-> read the details and response to it

![image](https://user-images.githubusercontent.com/91846668/136629062-3e42e3cb-49be-4e80-b405-51426b45322c.png)

(Extra function) Landlord can click “add an apartment” on the up-right corner to register a new apartment

![image](https://user-images.githubusercontent.com/91846668/136629103-d2e38848-c825-4dcc-aa09-25081e391e31.png)

