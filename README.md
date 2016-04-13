# CategoryDemo

Category and SubCategory System

We need to pass the file name as argument.

### Prerequisites:

1. Java version greater than or equal 1.7
2. Gradle

### External libraries used : 
1. commons-collections
2. Junit

### Algorithm:

1. Read each line from the file
2. And get the category and subcategory
3. Once you get category, sub category add these to Category object
4. Check for the duplicates if not present then add the category object to category list
5. By using Category list print the categoryCountMap and categories to console

### How to run:

1. Run from the IDE 
     Run as java application CategoryDemo by passing the filename as argument
   
**OR**

2. gradle clean test buildJar  - it will generate the jar file
    
     **java -jar CategoryDemo-1.0.jar 'inputfile'**  <br />
    e.g : java -jar CategoryDemo-1.0.jar C:\ValidCategeroyset.txt
   
####File Input:
- PERSON Bob Jones
- PLACE Washington
- PERSON Mary
- COMPUTER Mac
- PERSON Bob Jones
- OTHER Tree
- ANIMAL Dog
- PLACE Texas
- FOOD Steak
- ANIMAL Cat

####Output:
Category	Count <br />
PERSON	    2 <br />
PLACE	    2 <br />
ANIMAL	    2 <br />
COMPUTER	1 <br />
OTHER	    1  <br />

PERSON Bob Jones <br />
PLACE Washington <br />
PERSON Mary <br />
COMPUTER Mac <br />
OTHER Tree <br />
ANIMAL Dog <br />
PLACE Texas <br />
ANIMAL Cat <br />


###Code Coverage:

Run the below command to get the code coverage report

   **gradle clean test jacocoTestReport**
   
   ![alt text](codecoverage.png "Code Coverage")

###PMD: 
 Verified the code with PMD check and not found any critical violations(except the one which are related to System.out.println(), which is required in this program)


