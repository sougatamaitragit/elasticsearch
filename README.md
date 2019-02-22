# searchmetrics

Search Metrics Assignment

Approach to given assigment .

Project Approach 

At the beginning, first we need to create a plan for developmet and provide a verification or done criteria for each requirements. Plan will contain work break down or stories , story points,  schedule and resourcing  , dependencies , done criteria.
Here, I follow agile principle to design , develop and test given assignment. As per agile process I first create the stories   and assign story points and dependency and done criteria or acceptance criteria for  each story.

![User Stories](https://github.com/sougatamaitratcs/searchmetrics/blob/master/diagrams/Stories.PNG	)
 
                    Story Points

Design and Development Approach 

Once stories are done ,I  started identifying all cross cutting and NFR concerns including transaction /performance / deployment  and started designing common componenets for them . Then I identified high level packages , its responsibilities , interaction among different packages. Once packages are identified them started designing  classes in each packages keeping SOLID principles in mind . 
Once packages and classes are designed then looking for interactions among each classes and designing inetrafces for inetractions . It includes public interfaces and also utilities . Once interfaces or contracts are designed and  then started building behaviour of each methods by writing codes  . 
Finally write unit test cases and mocked all external communications during building unit test cases.  

Pre-Requisite
1. Jdk 1.8 
2. MySQL 5.7 
3. ElasticSarch 6.6
4. Account in bitcoinaverage.com

Basic Layers and Component Interactios 

Project is developed using Spring Boot 2.x version and follows standards layers architecture. A Rest Controller exposes Rest Services and interact with Spring Service layer. Spring Service layer interact with Spring Repository for Releational DB and Elastic Search. A scheduler is scheduled to fetch current exchange rate from [bitcoinaverage.com] and stores current rate in MySQL and store existing one in a Elastic Search repository. It uses spring cache , if rate fetch from the esternal service is same as that of last exchange rate it does not update existing rates and also does not includes data in history. 
Following digram depicts major compoents and its interactions.

![Flow diagram](/diagrams/interactiondiagrams.png	)

              Basic Component Interaction Diagram  diagram 

Setup - Application and database setup steps 

 kindly do the followings - 
a. DB set up - login to mysql prompt using root and run data-localdb.sql and schema-localdb.sql files [ These file are available under sql folder of the repository] . These will create a database and creates table in database and will insert records in CURRENCY master table 

b. Elastic Serach - Make sure elastic search is running - 

c. Modify Properties files - Update following properties of application.properties file
  i. api.key - based on your account in bitcoinaverage.com
  ii. api.secret - based on your account in bitcoinaverage.com
  iii. spring.datasource.url - database connection details and database name as per your environment
  iv. spring.datasource.password - your mysql root password .
  v. cron.schedular.expression - update with cron expression based on your need.
  vi. spring.data.elasticsearch.cluster-nodes - elastic search connetion string based on your environment.
  vii. spring.data.elasticsearch.cluster-name - your elastic serch clustername.
d. Compile and unit test code  - Follow steps below to run compile , unit testing and packaging code

  mvn clean  compile package 
  
e. Run application - Command above create a jar file called in side target folder under application's base directory . Run following command .

java -jar currencyConversion-0.0.1.jar

Testing Approache - Followings are the sample request for testing application 

a. Get Currenct Rate
  i. HTTP Method - GET
  ii . URL Sample - http://localhost:8080/exchangerates/exchangerate?fromcurrency=BTC&tocurrency=USD
  
b. Get Historic Rates 
  i. HTTP Method : - GET
  ii. URL Sample : - http://localhost:8080/exchangerates/exchangeratehistory?fromcurrency=BTC&tocurrency=USD&fromDate=2019-01-01&toDate=2019-02-22&page=1&size=4


API Documentation - API Doumentations are available in following URL - 

Swagger documentation is available in http://localhost:8080/v2/api-docs url ( Change port and ip as necessary ).


Known Issues and Concerns

1. This uses Spring Cache , in poduction it has to be backed by some distributes caching framework otherwise it will not scale.
2. Form packaging aspect , it would be nice to use docker .






