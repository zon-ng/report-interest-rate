# Report Interest Rate

This program queries the bank and financial company interest rate from MAS and prints out as a report for comparison.
It basically takes in date(s) as parameter and generate the report for the list of date(s).


## Design Patterns used
### Abstract Factory Pattern
Abstract factory pattern has been used to create a loose coupling of the implemention from the client. 
Client will only make use of the factory to create the objects which were also stored with interfaces to interact with the system.
Also if we have more kind of reports in future, we can keep make use of the abstract factory for holding the families of reports together.
### Facade Pattern
The abstract factory then creates the facade object which is stored in a interface and given to the client for interaction, hence encapsulating the system from the client.
The client only needs to know the report has been generated but need not know how it is being done.
### Bridge Pattern
The result from calling the facade returns a object(model) holding the data result. It is then sent to the view(Presentation) object for performing the printing to display the result to the customer.
I have applied the bridge pattern between the result object and the view object as I believe there can be and likely to be more types of view in future, i.e. it can be exported as a XLS document.
Also there can be more kind of interest rate report to be pulled, and this bridge will allow more kind of results to be presented in different ways to be achieved yet maintaining simplicity in codes.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Software installations required for building and running the program:
* Git: https://git-scm.com/downloads
* Java (Version 1.8.0_144 and above): https://java.com/en/download/
* Maven:  https://maven.apache.org/install.html

## Building and running the tests

Clone the repository from GitHub:
> $ git clone https://github.com/zon-ng/report-interest-rate.git

Navigate into the cloned repository directory:
> $ cd report-interest-rate

The project uses Maven to build:
> $ mvn clean install
 
To run the unit test in the application:
> $ mvn test
 
To build the application:
> $ mvn compile
 
Let's create the jar for the application:
> $ mvn clean package
 
Now you can run the application:
> $ mvn -X exec:java -Dexec.mainClass="sg.gov.mom.report.GenerateRateReport" -Dexec.args="JAN-2017,FEB-2017" 
 
The application require 1 parameter, a date(s) in the format of MMM-YYYY delimited by comma (e.g ""JAN-2017,FEB-2017") and up to a maximum of 12 dates allowed to be specified.
Below are 2 sample commands to run the application:
> $ mvn -X exec:java -Dexec.mainClass="sg.gov.mom.report.GenerateRateReport" -Dexec.args="JAN-2017"

> $ mvn -X exec:java -Dexec.mainClass="sg.gov.mom.report.GenerateRateReport" -Dexec.args="JAN-2017,FEB-2017,MAR-2017"

### Tests Explained

#### The following tests the JSON to Object Conversion:
1. testJsonToObject(); - Test Codes on conversion of JSON string to java object for usage
#### The following tests the date regex used for validation of the date specified by user (Allow max 12 dates delimited with comma)
2. testSingleDate(); - Test on a valid date, e.g "JAN-2017"
3. testSingleDateWithSpecialCharacter() ; - Test on a invalid date, e.g "JAN-2017*"
4. testSingleDateWithTrailingComma(); - Test date with trailing comma, e.g "JAN-2017,"
5. testSingleDateWithStartingComma(); - Test date starting with comma, e.g ",JAN-2017"
6. testTwelveDate(); - Test 12 dates, e.g "JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017"
7. testThirteenDate(); - Test 13 dates (Max allowed up to 12 dates), e.g "JAN-2017,FEB-2017,MAR-2017,APR-2017,MAY-2017,JUN-2017,JUL-2017,AUG-2017,SEP-2017,OCT-2017,NOV-2017,DEC-2017,JAN-2018"
#### The following test the generation of report to form the model which is later sent to the view/presentation for display.
8. testInvalidDateGenerateReport(); - Test creation of report with invalid date, expect no result
9. testSingleDateGenerateReport(); - Test creation of single date report
10. testSingleDateWithSpaceGenerateReport(); - Test creation of single date report with space
11. testTwelveDateGenerateReport(); - Test creation of twelve date report
12. testTwelveDateGenerateReportCheckOverallAverage(); - Test creation of twelve date report, check on the overall average rate is it computed correctly
13. testThirteenDateGenerateReport(); - Test creation of thirteen date report, expected no result as maximum allowed 12 dates


## Built With

* Eclipse Jee Oxygen - IDE for development
* [Maven](https://maven.apache.org/) - Dependency Management
* Git - For versioning

## Authors

* **Zon Ng**
