# Homie

/*
A README in general provides general info about the project, with detailed set-up and usage instructions. For the spike exercise, just a detailed guide on how to set up the app and run it is sufficient. Basically, explain how i can run the app on my system to evaluate your submission
*/

**Intro**

Our full stack is as below, so we have to install & setup the related applications.

        F/E                            B/E
Java script/html/css -> java -- JDBC (link by Tomcat) -- MySQL


**Set up**

1.MySQL setup

1.1	install MySQL 8.0.19 (Download MySQL: http://tiny.cc/hg1kuz)

1.2	Download my.ini file and move it to MySQL 8.0.19 directory (Download my.ini: http://tiny.cc/jg1kuz)

1.3	To access MySQL directory: right-click on Finder choose go to file 

and type the path: "/usr/local/mysql-8.0.19-macos10.15-x86_64"

1.4	Open terminal -> input command "echo $SHELL" -> check shell type is bash or zsh

![image](https://user-images.githubusercontent.com/91846668/136494546-321b2f53-67c9-4aca-8a87-16d42e80c129.png)

1.5	Go the user fold find the file (if your shell type is : bash -> .bash_profile or zsh -> .zshrc) 

![image](https://user-images.githubusercontent.com/91846668/136494590-c9cf1724-0214-4974-81c6-be9fa4f5779c.png)

*the file may be a hidden file, use "command + shift + ." to view the file
  
1.6	Open the file and add the line below in the file.

export PATH=/usr/local/mysql-8.0.19-macos10.15-x86_64/bin:$PATH

![image](https://user-images.githubusercontent.com/91846668/136494662-318c5081-80a0-4bfc-a860-aa052d34d2e6.png)
 
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

![image](https://user-images.githubusercontent.com/91846668/136494913-6188ccf9-c057-4a11-a129-b1f2629d05bc.png)

1.13	(Import tables) After connection -> go to scheme tab -> click add SQL button on the up-left side 

-> copy the words from the link below -> paste to the sheet -> click execute button (flash sign) 

-> click refresh button on the up-right side of left box

(Download MySQL script: http://tiny.cc/ng1kuz)

![image](https://user-images.githubusercontent.com/91846668/136495072-74191733-237f-46ae-8a96-1138f1d8d4ea.png)

2.	Eclipse & tomcat setup
	
2.1 install Eclipse EE (Download Eclipse: http://tiny.cc/ug1kuz)

2.2 install java JDK 1.8.0_201 (Download JDK: http://tiny.cc/vg1kuz)

2.3 Create an independent directory for this project -> copy the project folder & tomcat folder to the directory

(project folder: from Github / tomcat folder:download Tomcat v9 from http://tiny.cc/xg1kuz)

2.4 Open Eclipse and choose the directory as workplace

2.5 Go to preference to set up Tomcat connection 

![image](https://user-images.githubusercontent.com/91846668/136495232-ee6aa244-6930-45f9-808b-cb3642a9bac5.png)
 
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

![image](https://user-images.githubusercontent.com/91846668/136495731-a112e988-b099-4435-adad-914cdca4a0ea.png)

2.7 Exceptions: 

There may still have error after doing the procedure above, if it is, please check

*Right clickc HOMI -> Configure Build Path

![image](https://user-images.githubusercontent.com/91846668/136495775-895774a0-fdd0-4065-9073-a9ac1166b2b2.png)
 
*Check if some error in libraries -> if yes, re-add the JARs which show error (we put the JARS in the project folder)

*Check if the JRE Library is JavaSE 1.8 -> if not, edit it to correct version
 
![image](https://user-images.githubusercontent.com/91846668/136495868-f62c9b8f-f265-4c49-96d6-c9d257a7aebc.png)
