# TestRailDiploma
This is a project for testing the TestRail test management platform.

## Checklist
### API HTTP Client suite
1)LoginHTTPClientTest

### UI suite
2)CreateANewProjectTest

    Steps: 1.log in to TestRail
           2.click "Add project" button
           3.set a new project name
           4.click "Add project" button
           5.delete project via API call 
           
3)CreateANewTestCaseTest

     Steps: 1.create a new project
            2.log in to TestRail
            3.open created project
            4.switch to "Test Cases" tab
            5.click "Add Test Case" button
            6.set Title
            7.set Precondition
            8.set Steps
            9.click "Add Test Case" button
            10.see pop-up message "Successfully added the new test case. "
            11.delete project via API call
            
4)CreateANewTestRunTest

     Steps: 1.create a new project
            2.log in to TestRail
            3.open created project
            4.switch to "Test Run & results" tab
            5.click "Add Test run" button
            6.see new defaul Test Run name
            7.set Reference
            8.set Description
            9.click "Add Test run" button
            10.see message as "Successfully added the new test run."
            11.delete created Test Run via API call
            
5)CreateANewMilestoneTest

     Steps: 1.create a new project
            2.log in to TestRail
            3.open created project
            4.switch to "Add Milestone" tab
            5.click "Add milestone" button
            6.set Name
            7.set Reference
            8.set Description
            9.set Parent
            10.set Description
            11.set Start Date
            12.set End Date
            13.click "Add Milestone" button
            14.see message as "Successfully added the new milestone."
            15.delete created Test Run via API call
            
### API Rest assured suite
6)DataTypeOfAllProjectsTest

7)UpdateAProjectTest

8)UpdateATestCaseTest

9)UpdateTestRunTest

10)UpdateMilestoneTest
