# TrivagoChallenge
 ## That Automation Testing is Hybrid framework which Uses 
 1- Selenium
 2- Maven Jaba
 3-Cucumber BDD (Gherkin language) via Junit ,
 4-DDT using Excel sheet (for storing locators of the webElements)
 5-using Cucumber 6 Extent report for generating PDF & Spark reports
 6- Using TestNg for assertions
 6-Reading from Properties files for storing environment variables
_________

## Structure of Project:
### 1- src/main/java
 #### A- pages Package includes the designated pages of trivago magazine
 #### B-Utilites Package includes a class of common methods like (scroll to the webelement)
 
2- src/test/java
#### A-Feature Package includes all the features files , every feature file includes a scenario that map to some test cases
#### B-hooks Package includes the class of the common components that need to run before and every feature file
#### C-stepDefinitions Package includes steps class that has the implementations of the feature files
#### D-Runner Package include the test runner that run the step definitionns along with the designated feature file
#### E-test Package include the class of reading from the properity file
 
 
3- src/test/resources
 #### A- config.properties that contains the environment variables (name of the browser, URL to run against, Page title of trivago magazine website)
 #### B- extent-config xml file includes the properties of the display of the extent report over the browser
 #### C- extent.properties includes the location of the generated reports and some system info to be displayed in the report
  

_________

## For running the project 
#### 1- Navigate to the location of the project on your local machine via CMD/Termimal
#### 2- Run (mvn clean test)
#### 3- To check the report :
#### A- (open test output folder) and you will get a generated pdf report contains all the details of the execution (pass/fail)
#### B- (open test-output/SparkReport), then open (index.html) file on any browser and you will get detailed info of the status of every step in every scenario per every feature file
    
    https://user-images.githubusercontent.com/66884373/103463340-508d2e80-4d34-11eb-8ef3-09051f6e7596.png
