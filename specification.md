# Tools for Software Development - Assignment 1

### Version 2.0

### Due date: Specified in the Course Website (Sunday, 6 October 2019, 11:59 PM)

This is a group assignment. 
You can self-nominate your group members before the end of week 3 on Sunday, provided: 

- All members are required to be officially enrolled in the same practical session.
- Minimum of 3 students, maximum of 4 students.
- Use the assignment group allocation wiki on the course website.

Nominations fail to meet the requirement will be discarded. 

The remaining students will be placed into groups at the beginning of week 4. 

### Optional
You may wish to work on extensions of an already-existing, open-source project instead of the given code-base. Seek approval from the course coordinator, discuss, and we'll see what we can organize.

## Overview

1. You'll be cloning an existing repository. The repository is located at: 
          https://gitlab.com/ivanleeunisa/inft2063_assignment_1.git
   This code is broken and buggy. 

2. Your group is welcome to use any of the online Git repository sites: 
	- gitlab
	- github
	- bitbucket
   You don't have to use any of these, but they may be valuable to you. 

It's recommended that you use GitLab.com. 

+ Your group will have to find and use:
	- An issue tracker
	
Note that gitlab/github/bitbucket have inbuilt issue trackers. 

## Instructions

These instructions assume that your group *is* using GitLab. If you choose to use a different tool, you're required to complete these tasks using your chosen tools equivilent components. 

1. Create milestones for your project. 

2. Create issues, and assign them to the relevent milestones. 
	- Issues include bugs and features. 
	- Since programming is not the major focus of this course, there is a supplied list of bugs and some features in the appendix. 

3. Assign issues to team members. 
 	- That team member will then be responsible for:
		+ fixing the bug
		+ leaving a descriptive commit message
		+ pushing their changes
		+ closing this issue. 

4. Once all issues for a particular milestone have been fixed:
	- Create a tag for the milestone
	- Close the milestone
	
	Make sure this step is completed before starting on the issues for the next milestone.

	The first milestone will be related to fixing bugs in the supplied code. 
	The second milestone will be adding features from the supplied list. 
	The third milestone will be adding features you choose as a group.

5. Once milestone 3 has been completed, you will need to conduct a code review to improve and optimise the code.

6. Create two markdown files: 
	- changes.md:: list the changes made during the code review
	- manual.md:: detail how to use the software.

### Submission

The submission is completed in two parts: 
1. The final submission is your git repository. 
	- **EACH** group member must make a submission. 
	- Each member needs to: 
		+ Clone your groups git repository
		+ Submit both your working directory *and* the '.git' folder. 
2. During practicals in week 9, each member is required to demonstrate your issue tracker to your practical instructor. (External students submit screenshots)
	- Each group member must have created issues, and have issues assigned to them. 
	- Do not rely on other members in your group having this stage ticked off for you. 
		+ If you want the mark, you must demonstrate this to your instructor personally. 



# Marking
This is a team-project to practice many of the team-development concepts and tools we covered in this course. 
It is important to note that team members do not have equal marks. 
Individual contributions (such as your stored in the gitlab server) will be considered across all assessment criteria.

## Marking criteria

Programming

	- Fixing Bugs
	- Adding Features
	- New Features
	- These tasks require not just programming, but also git commits on feature branches.

Issue Tracking

	- Creating Issues
	- Closing Issues
	- Git commits that match the issue numbers

Git

	- Commit Log formatting
	- Commits, merges, tags, and branches
	- Signing your commits

Misc

	- Creation of relevant files
	- Code review
	- Documentation Files
    - Individual presentation (3 minutes each. Internal students present in class, external students submit voice recorded slides.)

## Notes

It's important to note that: 

	- Individual marks will be attanined from the git repository. 
	- Clean histories are important!
	- Portions of the marking (`Git`) are primarily _individual_, and the mark that you receive won't always match the rest of the group. 

**The assignment specification may change**. Changes will be discussed in lecture, and added to the gitlab repository. 

# The Scenario
The supplied code is the first alpha of a cash register program. 
Upon running the code the user is prompted for their float (the amount of money in the cash register at the start of the day). 
The user then enters a product name, product price, and the amount of cash tendered by a customer. 
The program then prints to the screen the amount of change required by the customer and the balance of the cash in the cash register. 
As milestones are met the program will gain in functionality.

## Milestone 1: Fixing the Bugs (15%)
1. Clone your repository from gitlab
2. Download the starting code from the homepage
3. Add the starting code to your gitlab repository
4. Each team member will then need to clone the repository
5. Each team member then fixes the bugs assigned to them and uploads the changes 
6. Each team member must then close the issue assigned to them
7. Once all milestone 1 issues are resolved and the code has been checked to make sure then close
milestone 1 and tag the code as version1

## Milestone 2: Adding Features (15%)
1. As before each user must add the features assigned to them, commit their code, and close the issue.
2. The group member tasked with translating the application's text prompts into another language will need to create a new branch, and add the translation on this branch (google translate is fine). Once the translation is complete this branch will be tagged, and the group member will push their changes to the repository then return to the mainline without merging.
3. Once all milestone 2 issues have been closed, test the code, then tag it as version2 and close the milestone.

## Milestone 3: New Features (30%)
1. As a group you will need to choose new features to add functionality to your cash register program.
Try to be original and unique as these features will form much of your softwares unique presentation and feel. Create
these features as ‘issues’ and assign them to team members.
2. Each team member should be responsible on a new feature. All new features should be discussed
among team members and should be interoperable as one project (as oppose to submitting
independent incompatible code.)
3. One feature must be developed on a separate branch before being merged back into the mainline.
4. Once all features have been completed and closed, test the code, tag it as version 3, then close
milestone 3.

## Final Task: Code Review and Wiki Creation (20%)
1. Create a wiki page for the user manual instructing users how to download, install, and use your
software. All team members must contribute as the wiki history will be checked during marking.
2. Review the code of your application, look for improvements and optimisations. Create a second
wiki page that lists each change to the code and why it was done.
3. Create another wiki page to store the meeting minutes and/or major discussion outcome.

# Appendix

## Summary of given bugs in Milestone 1
1. Can't compile using the Makefile
2. Spelling errors in prompts
3. Change value is not correct
4. Ending balance of cash register is not displaying
5. No dollar signs before cash values

## Milestone 2

1. After entering the float present the user with a menu asking if they wish to exit, or process a
transaction. Loop back to this menu after processing a transaction.
2. Remove the display of the cash register balance after each transaction and only display at the end
when the user quits.
3. When processing a transaction allow multiple items to be bought at the same time by asking if all
items have been entered after entering the item name and cost. If the user says no then another
item is added to the transaction. If the user says yes the amount of cash tendered is requested and
change calculated.
4. After processing a transaction present an option to provide a receipt. If the customer says yes print
a receipt to the screen including the names and cost of all items bought, the total, the cash
tendered, and the change.
5. Modify the Makefile to include creation of a jar file
6. Add a nice welcome message once the program is started.
7. Add eloquent error handling in the case of unexpected input from the user
8. Create a branch for an experimental version of the software in another language 
