# TEAM cRAFTers
# CSCC01 2017 Fall team project
This project is to build an encouraging assignment system for statistic students. 


## Deliverables 

This [file](https://github.com/CSCC01F17/L01_01/blob/master/project_documentation/ProjectTeamAgreement.pdf) is an introduction to cRAFTers (our team for this project).

We have identified the key personas and developed user stories from the personas, which can be found [here](https://github.com/CSCC01F17/L01_01/blob/master/product_backlog/userpersonas+stories_v3.pdf) (Updated 11-20-2017).

You may also view our report on how we describe our strategies in verifying, validating, and reviewing our code [here](https://github.com/CSCC01F17/L01_01/blob/master/project_documentation/verify-validate-review.pdf).

Our Sprints can be found here:

[Sprint 0](https://github.com/CSCC01F17/L01_01/blob/master/product_backlog/product_backlog_sprint0.pdf)  
[Sprint 1](https://github.com/CSCC01F17/L01_01/blob/master/product_backlog/product_backlog_sprint1.pdf)  
[Sprint 2](https://github.com/CSCC01F17/L01_01/blob/master/product_backlog/product_backlog_sprint2.pdf)  
[Sprint 3](https://github.com/CSCC01F17/L01_01/blob/master/product_backlog/product_backlog_sprint3.pdf) 
[Sprint 4](https://github.com/CSCC01F17/L01_01/blob/master/product_backlog/product_backlog_sprint4.pdf) 

## Team members 

* **Adrian Ensan** 
* **Felicia Tung** 
* **Rene Piperi** 
* **Talha Khatri** 


## Set up local database
1. Download and install [MySQL Server and Workbench](https://dev.mysql.com/downloads/installer/)
2. Start Server and Workbench
3. In MySQL, open and execute the following database scripts:
  
  * [initializeDB.sql](https://github.com/CSCC01F17/L01_01/blob/master/initializeDB.sql): this will drop the existing database and create a new database
  
  * [mockData.sql](https://github.com/CSCC01F17/L01_01/blob/master/mockData.sql): this will create mock data in the database (not including the user related data)
4. Open DatabaseManager.java from \App\src\backend\
5. Update the following line with your local database server user(first "root") and password (second "root")
```
conn = DriverManager.getConnection("jdbc:mysql://localhost/C01ProjectDB", "root", "root")
```
6. Update your IDE to add the SQL jar dependency, you can find the jar in \App\dependencies\mysql-connector-java-5.0.8\mysql-connector-java-5.0.8-bin.jar

## Run the project
1. Run the [Main.java](https://github.com/CSCC01F17/L01_01/blob/master/App/src/Main.java) after you have the local database set up 
* By default, it loads the add question page 
* To see the add assignment page, go to [UIManager.java](https://github.com/CSCC01F17/L01_01/blob/master/App/src/ui/UIManager.java), 
  1. comment the line below 
  ```
  switchView(new AddQuestionPage());
  ```
  2. uncomment the line below 
  ```
  switchView(new AddAssignmentPage(qlist));
  ```
  3. rerun Main.java 
