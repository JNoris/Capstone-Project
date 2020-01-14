# Final-Capstone-Project
Final Capstone Project meant to liberate all people from their diminutive lives

Installing Source Control:
First of all, you must have the github extension/setting/whatever installed on whichever IDE you are currently using. 

Vscode: Look in the extension marketplace and search up git.

Git: you could also download git and link github to it and use the git terminal to upload to and fro your desktop to the source control version (SCV).
OR
Github desktop: This is the GUI version of git, also extremely useful. Download from github.

Visual Studio: Should automatically have git support installed, if not, check the visual studio installer (if you have visual studio, you definitely have visual studio installer as well).

IntelliJ, Eclipse, netbeans, etc: I have no idea, search it up.

****************

Branch Guide: There are several branches in this github due to the scope of the project, each branch is to serve a specific purpose, do not defile their purpose. Or else.

DO NOT UNDER ANY CIRCUMSTANCE UPLOAD TO MASTER BRANCH, we will do that when the entire group is around and it's under explicit consensus from everyone to upload, until then, keep your code local.

Master: Default development branch, in most cases, this contains the local development, though that is purely by convention and is not required. In our case, it's our most active branch since it contains our recent changes to the application.

Init: When in doubt, upload here, this is the initialization of all files, in a perfect world this would not be used, but we're not perfect.

Stable: A stable branch is a development or feature branch that is guaranteed to build and pass integrated tests.
 - Must branch from: Master.

Feature: A feature branch is where new development is done. However, master should be merged periodically into a feature branch.
 - Must branch from: Master.
 - Must merge to: Master.

****************
Common Terms:

Branch: is an active line of developement, the most recent commits to a branch is referred to as the tip of that branch.

Pull or pull request: Where you 'fetch' code from github and 'merge' to your desktop's local repository/branch in order to work on that code locally. 

Push: Upload code to github, you must specify the branch in order to push successfully. Yes, it does matter which branch you upload to.

Fetch: Fetching = download basically, fetching a branch downloads all the files of that branch in a zip file.

Blame: View line-by-line history of a file and see who worked on it and etc.

Merge: Merging is performed by an automatic process that identifies changes made since the branches diverged, and then applies all those changes together. In cases where changes conflict, manual intervention may be required to complete the merge.




