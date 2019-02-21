# searchmetrics

Search Metrics Assignment

Approach to given assigment .

Project Approach 

At the beginning, first we need to create a plan to crate to each requirements and provide a verification or done criteria for each requirement. Here, I follow agile principle to design and develop and test given assignment. As per agile process I first create the stories   and assign story points and dependency on each story.

Please see story-v1.doc file for detail stories .

Design and Development Approach 

Once stories are done , started identifying all cross cutting and NFR concerns including logging / transaction /performance / deployment  and developing componenets for them .Then started identifying packages and low level classes and its responsibilities.
Once packages and classes are designed then looking for interactions among each classes and designing inetrafces for inetractions . It includes public interfaces as well in class to class inetraction interfaces. Once interfaces or contracts are designed and developed then started building behaviour of each methods . Finally write unit test cases and mocked all external communications during building unit test cases.  Once unit testing is done then buiilding docker file and create docker images out of it. 
Once docker images are produced then run these images and perform functional testing. 

Pre-Requisite
1. Jdk 1.8 
2. MySQL 5.7 
3. ElasticSarch 6.6
4. Account in bitcoinaverage.com

Basic Layers and Component Interactios 

Project is developed using Spring Boot 2.x version and follow standards layers architecture. A Rest Controller exposes Rest Services and interact with Spring Service layer. Spring Service layer interact with Spring Repository for Releational DB and Elastic Search. A scheduler is scheduled to fetch current exchange rate from [bitcoinaverage.com] and stores current rate in MySQL and store existing one in a Elastic Search repository. It uses spring cache , if rate fetch from the esternal service is same as that of last exchange rate it does not update existing rates and also does not includes data in history. 
Following digram depicts major compoents and its interactions.

![Flow diagram](/3DTest.png)/diagrams/interactiondiagrams.png	

Basic flow diagram 

Setup 
For setup , kindly do the followings - 
a. DB set up - use 
b. Elastic Serach - Make sure elastic search is running - 
c. Modify Properties files - Update following properties of application.properties file
  i. api.key - based on your account in bitcoinaverage.com
  ii. api.secret - based on your account in bitcoinaverage.com
  iii. spring.datasource.url - database connection details and database name as per your environment
  iv. spring.datasource.password - your mysql root password .
  v. cron.schedular.expression - update with cron expression based on your need.
  vi. spring.data.elasticsearch.cluster-nodes - elastic search connetion string based on your environment.
  vii. spring.data.elasticsearch.cluster-name - your elastic serch clustername.
c. Compile and unit test code - 



API Documentation - API Doumentations are available in following URL - 

Known Issues and Concerns
1. This uses Spring Cache , in poduction it has to be backed by some distributes caching framework otherwise it will not scale.
2. Form packaging aspect , it would be nice to use docker .
3. 





