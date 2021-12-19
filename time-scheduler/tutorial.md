# Maven basics

## Folder/Directory structure
- Always have the two folders of src and test. 
	+ Source is where we store all of 
	 	the java files and test files (more on test files and Junit in second part).
	+ target store all of our jar files when it finished compiling
		and is rarely accessed, only when we need the javadoc or the final build.

## Executing the main class
- All only the App.java will have the main class (driver code), the rest of the
	files and folder will be used for packages and methods.

- Typing mvn exec:java will run this only the App.java file. 

## Dependencies, artifactID, and groupID
- Dependencies are the libraries that we wanted to use. 

- ArtifactIDs are the unique name that identify it. Written in the format of:
	this-is-an-artifact-name. In our project: time-scheduler

- GroupID are the unique name to identify the group (usually a company) or a
	team that work on the project. Usually written by using the website of that
	group but it is written backwards, e.g. mavenplugin.org will become
	org.maven.plugin. In our project this groupID is: com.twenty.one

- Every files are stored inside sub-folders named after the groupID, the
	artifactID will be included in the final build. In addition every file must
	be in the groupID-named package !!!, e.g. package com.twenty.one; this
	command has to be at the top of every file.
 

## Installing maven dependencies: https://mvnrepository.com/
- put it between the two <dependencies> tags

## Maven is consist of lifecycle, of which there are: default, clean and site.
- Of the three default is the most important, and if not specify default will be
	run

## Phases in default lifecycle
1.validate: check if all information necessary for the build is available\
2.compile: compile the source code\
3.test-compile: compile the test source code\
4.test: run unit tests\
5.package: package compiled source code into the distributable format (jar, war, …)\
6.integration-test: process and deploy the package if needed to run integration tests\
7.install: install the package to a local repository\
8.deploy: copy the package to the remote repository\

Phases when run includes all phases before it, e.g. if you run phase 4 that
means it also run phases 1,2 and 3.

## When using maven command remember to be in the base directory
- The base directory is the folder with the pom.xml file in this project that
	would be time-scheduler


## Plugins are user modified version of the maven phases
- they often have goals next to them to specify their actions, e.g.
	surefire:test where surefire is the plugin and test is the goal.

# Javadoc

- To generate javadoc type mvn javadoc:javadoc. Then find the file:
time-scheduler/target/site/apidocs/index.html

Run it with your browser, that is your javadoc. Every comments you have above
each method or classes will also be included in this javadoc but they have to be
written in a certain format.

## format of javadoc

	/**
	 * @author authorName
	 * @version 1.0
	 * @param a variable
	 * @param b variable
	 * @return int
	 * @see App.java
	 */
- Javadoc will be included in the final website also hovering over the class or
- method with your IDE will show these information, very helpful when working in
- team.  

# Testing (here annotation is used again)

- Though Maven is a build tool and different project uses different build tools,
	Junit is a very popular framework that work across all of these build tools.

- Currently using Junit 5 framework. If you google how to use it type Junit 5
	instead of Junit 4 or 3 because there are some differences.

- Test files must be in the test folder and named in the convention of
 file_nameTest.java in order for the build tool Maven to find it.

## Annotation 
- If you want to find out more about annotation especially @Override:
	https://www.geeksforgeeks.org/annotations-in-java/

- Important annotations: @Test, @BeforeEach @BeforeAll, @RepeatedTest(),
	@AfterAll, @AfterEach
	+ The @Test indicate that the following block of code is to be run in a
		test.
	+ @BeforeEach is usually use the set up the test and this block of code will
		be run before each of the tests.
	+ @BeforeALl also used to set up but is only run once before all test.
	+ @RepeatedTest() repeats a test to the number of time of its argument,
		e.g. @RepeatedTest(5) repeats 5 times.
	+ @AfterAll, @AfterEach is used to clean up tests, reset the enviroment.

## Methods for testing

- Import org.junit.jupiter.api.*; Loads the Junit and all of its modules into
	our code.

- import static org.junit.jupiter.api.Assertions.assertEquals; Import assertion
	methods. For this simple example we will only be used the asserEquals()
	method.

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 * @see https://www.vogella.com/tutorials/JUnit/article.html
 */
public class CalculatorTest 
{
	Calculator cal;

	@BeforeEach
	public void setUp()
	{
		cal = new Calculator();
	}

    @Test
    @DisplayName("Test multipying 2*3")
    void shouldAnswerWithTrue()
    {
        assertEquals( 6 , cal.multi(2, 3)); 
    }

    @RepeatedTest(5)                                    
    @Displ
	ayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, cal.multi(0, 5), "Multiple with zero should be zero");
        assertEquals(0, cal.multi(5, 0), "Multiple with zero should be zero");
    }
}

- assertEquals(expected, actual) takes in the two parameter expected and actual
	checks if they are equal if yes return true else false.

## Running tests with maven
- mvn test will run all of the Test files, meaning all of the tests.

### running the test 

#### Use your IDE plugins to do this or the command line:

- Run all the unit test classes.\
$ mvn test

- Run a single test class.\
$ mvn -Dtest=TestApp1 test

- Run multiple test classes.\
$ mvn -Dtest=TestApp1,TestApp2 test

- Run a single test method from a test class.\
$ mvn -Dtest=TestApp1#methodname test

- Run all test methods that match pattern 'testHello*' from a test class.\
$ mvn -Dtest=TestApp1#testHello* test

- Run all test methods match pattern 'testHello*' and 'testMagic*' from a test class.\
$ mvn -Dtest=TestApp1#testHello*+testMagic* test
 
## other things to know

- SNAPSHOT is the work in progress of a program. Without this word the program
	is assumed to be finished -> stable release

- mvn clean test vs mvn surefire:test the first command on the left will compile the
	current project and run test on it. The second on the right will only run
	the test on the most recent build

- In depth discussion on Junit 5: (26 minutes)
	https://www.youtube.com/watch?v=flpmSXVTqBI
