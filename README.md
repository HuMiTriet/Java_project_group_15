# Java_project_group_21

# Maven basics

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

## Installing maven dependencies: https://mvnrepository.com/
- put it between the two <dependencies> tags

## Plugins are user modified version of the maven phases
- they often have goals next to them to specify their actions, e.g.
	surefire:test where surefire is the plugin and test is the goal.

## other things to know

- mvn clean test vs mvn surefire:test the first command on the left will compile the
	current project and run test on it. The second on the right will only run
	the test on the most recent build
# Javadoc

- To generate javadoc type mvn javadoc:javadoc. Then find the file:
time-scheduler/target/site/apidocs/index.html

Run it with your browser, that is your javadoc. Every comments you have above
each method or classes will also be included in this javadoc but they have to be
written in a certain format.

## format of javadoc

	/**
	 * @author PJ
	 * @version 1.0
	 * @param a variable
	 * @param b variable
	 * @return int
	 * @see App.java
	 */
- Javadoc will be included in the final website also hovering over the class or method with your IDE will show these information, very helpful when working in team.  

# Testing
- Currently using Junit 5 framework. If you google how to use it type Junit 5
	instead of Junit 4 because there are some differences.

## Annotation 
- If you want to find out more about annotation especially @Override:
- https://www.geeksforgeeks.org/annotations-in-java/


