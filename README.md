# StartCode_CA3
#### Asignment: [CA3 QuickStart Project](https://github.com/Gold-ish/StartCode_CA3_Backend/blob/master/CA3-QuickStart-project.pdf)

## Frontend
This is the frontend that is supposed to be used with this backend.
https://github.com/Gold-ish/StartCode_CA3_Frontend
  
#### Members:
- Allan Bo Simonses
- Nina Lisakowski
- Caroline Lærke Høeg-Iversen
- Tobias Anker Boldt-Jørgensen
  
## Getting Started with the start code.  
- Make a new repository on GitHub.  
- Clone the repository to your computer.  
- Download this repository as a `.zip` file.  
- Move the project files from the `.zip` to your local repository, that you made in step 2.  
***
- In the pom.xml file edit the artifact id and name to something that makes sense for the project.  
- Create a new schematic on your vagrant db.  
- Find the `config.properties` file in the `Other Sources/src/main/resources/<default package>/config.property` file.  
- Change the `db.database` to the same database name we created on our vagrant db.  
- Find the `context.xml` file in the `webpages/META-INF/context.xml`, and name it to something that makes sense for the project.  
- In the source package -> utils -> SetupTestUsers.java class edit the passwords to something other than "test", and run the file once. 
- Confirm that the roles and users have been added to the schematic.  
- Add the file to the gitignore, before the initial commit and push to GitHub.(Remove the `#`)  
`**/SetupTestUsers.java`  
- Navigate to the root of the project folder with `git bash`, and run `mvn clean test`.
- If there occur any problems then fix them else go to next step.
- In the local repo add, commit and push to GitHub.  
***
## Setup Travis-CI controll (Maven script deploys when travis has full green test)  
- Go to [Travis-ci.com](https://www.travis-ci.com) and activate the new repo.  
- Select the repository from the dashboard menu.  
- Click `More Options` and select `settings`.  
- Create 2 new `Enviorment Variables`;  
`REMOTE_PW` : (Password for script_user)  
`REMOTE_USER` : `script_user`  
- In the pom.xml file edit the; <remote.server>*(You'r deployment serverURL)*/manager/text</remote.server>  
#### Test The pipeline   
- Make a small change in the program, and run "mvn clean test" to make sure it won't break the pipeline.  
- If it is a succes then try pushing the change to github and see travis turn green.  
- Last check is to make sure that it was deployed properly to the remote server.  
- Try in the browser to reach the url.  
- Remember to edit the Travis markdown to your own, for the new repo.
***  
## Deployed server database setup.  
- Create a remote schematic on the deployment server, with the same name as the vagrant db.  
- SSH into the deployment server. `ssh (USER)@(URL)`  
- Add 3 enviorment variables to the setenv.sh folder;  
(sudo nano /opt/tomcat/bin/setenv.sh)  
`export USER_(ProjectName)=(YOUR_DB_USER)`  
`export PW_(ProjectName)=(YOUR_DB_USERS_PASSWORD)`  
`export CONNECTION_STR="jdbc:mysql://localhost:3306/(DATABASE NAME)")`  
- Save the file and run this command to restart tomcat "sudo systemctl restart tomcat"  
***
- If you rename the 3 system.getenv from the "/utils/EMF_Creator.java"(Line 118-122), you also have to remember to rename the 3 variables form the sudo nano /opt/tomcat/bin/setenv.sh  
- Go to vagrant database. press administration, press data export.
- select the correct schema and tables. make sure you select export as self contained file and make one dump file. press export
-go to your online database. press administration. press data import. select the correct dump file. import the whole file. make sure you set the correct schema as target.

## Start making your project.
You can use the `RenameMe` as templates for coding the projects.
Remember to remove the RenameMe table from the Database when you start to use the program.

## Extra:
Force deployment without travis:  
Remember to edit the RemoteServer in the pom.xml file befroe using the mvn command.  
Remember to use the right password.  
mvn clean test -Dremote.user=script_user -Dremote.password=RiGhTpAsSwOrDgOeShErE tomcat7:deploy  
If you have forgotten the password for the script_user, 
you can ssh into the online ubuntu server and directly in the root nano into the my-bootstrap.sh file; `nano my-bootstrap.sh`.

### important!
when creating new test, do NOT make use netbeans junit test class, instead make your test in a normal java class.

  
## Travis
[![Build Status](https://travis-ci.org/Gold-ish/StartCode_CA3_Backend.svg?branch=master)](https://travis-ci.org/Gold-ish/StartCode_CA3_Backend)

## Package overview
This is a overview of the packages and their function

### cors
The cors folder contains the files responsible for setting cors filter. Do not make any changes in this folder unless you want to dissable cors.

### dto
The dto package contains all the dto classes. There is no template class as dto is not very generic and also very easy to make. the current dto classes are all used to hold fetched data and a completeDTO class to merge 3 of the fetch calls together.

### entities
The entity package contains the entity files. Do not touch user or role class as they are used for the login process. the RenameMe.java is a template class for future entity classes.

### errorhandling
this contains exception handling. you can copy notfoundexception and make your own exceptions also the AuthenticationExceptionMapper can also be copied and used as a template

### facades
The userfacade is used for user login and should not be touched. the fetchfacade handles fetch calls and can be used as a template for fetch methods. The facadeExample can be used as a template for future facade classes.

### fether
the fetcher package is used for fetching from other server apis. the interface is used to make a paralel api call to three servers at the same time, the other classes are used to hold the fetched data.

### rest
Do not touch Application config class! the fetchdemoressource is the endpoints for getting the fetch data and cen be used as a template. renameMeRessource is a template with post put delete endpoints, the rolesdemoressource class is a template for protected endpoints with speicifc roles only allowed.

### security
Do not touch the security folder, it as is. this handles the login process and authentication. also has the login endpoint.

### utils
The utils package is used for URLs for connections and building entitymanagerfactory, httputils class has a fetch method used for api fetch calls to other servers. Follow the guide on how to use the setupTestUsers class.



