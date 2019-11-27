# Find-A-Group
<h4>Get together and form groups together. </h4>
<p>Users  can  create  a  profile,  then search  for  interesting  groups based  on  their activities,
  hobbies,  etc.  A group leader can create a group, add meetings, and add tags for users to find this
   group. User shall also be able to view meetings for specific dates/locations.</p>
   

<h5>Adding and Editing Meetings</h5>
<p>The group leader will add a meeting from the group dashboard page. The user will be 
directed to a meeting dialogue to add the meeting <u style="text-underline-color: firebrick;">details</u>. The meeting status
will automatically be set to active and the user will automatically join the meeting's
attendees list. The meeting is then created and posted to the dashboard.</p>
<p>The group leader will edit a meeting from the edit group page. The meeting information 
will be displayed in a table. The user can edit the meeting <u style="text-underline-color: firebrick;">details</u>
 by double-clicking a row from this box. A new window will open to edit the meeting. Once the user clicks finish, the updated meeting will display in the group 
 dashboard page.</p>

<h5>Creating a Group</h5>
<p>The use may create a group from the profile page. The user will then be redirected to the 
page to create the group, where the user will start by inputting the <u>group name</u>
and <u>description</u>. The user will then select up to four tags to attribute the group.
The tags will be provided by the system. When the user is finished with the page, the
new group will be created and the user will become the group leader. The system will
display to the user that the group was created successfully.</p>

<h5>View Meeting Details/Sign Up for Meeting:</h5>
<p> The Close button will exit the program. The 
Attend Meeting button will display a message to the console that says the meeting has been attended.
The Cancel Attendance button will display a unique message if it is clicked when the user hasn't 
attended the meeting, and will display a message confirming their cancellation if they have already
 clicked the Attend Meeting button.</p>
 
<h5>View Profile:</h5>
<p> The user's profile is used to display the user's name and what group they are in. When the user 
joins a group or multiple groups, the program should display to the user the current groups they are in. 
The program also allows the user to have access to the edit profile scene where the user can change their 
password. </p>


<h5>Search/view groups</h5>
<p>The software allows users to search for and view groups based on a set of system provided tags that they can choose from. The user can select up to four tags. When the search button is clicked, the system will query the database for groups have all of the tags specified by the user. Results from the database will be stored as group objects in an ArrayList of group objects. These groups will then have their name and description displayed to a table view for the user to view</p>

<h5>Search/view meetings</h5>
<p>The software allows user to search for and view meetings based on the meeting time, meeting location, and group hosting the meeting. The user can specify date, location, and host group. When the search button is clicked the controller will query the database based and return meetings that fit the parameters specified by the user. The results of the query will be used to create meeting objects, which will then be stored in an ArrayList of meetings. The meetings from this ArrayList will be displayed to the users.</p>
 
 #Versions
 
  <h4>Video of our prototype version 2</h4>
  https://drive.google.com/open?id=1iIhLd-eEtenOl1DvdrbSVVC0pSbXJBFf
  
  <h4>Video of our first prototype</h4>
  https://drive.google.com/open?id=1J9skrwGJoFsHFXFezunjVMN1rDOmV4FK
  
 <h5>Prototype 2 Deltas</h5>
 <ul>
    <li>Added profile tab with most features and functionality</li>
    <li>Edit account now has functionality</li>
    <li>Added feature to sign-up for meeting</li>
    <li>Added features to edit user's profile with functionality</li>
 </ul>

#Author
David Rose, et.al.

#Test Cases
<h3>Normal scenarios</h3>
* Create an account: Click the create account button and provide a username, password, and email. 
When the passwords and emails are confirmed, you are redirected to the login page and must log in
for the first time. On success, you will be on the profile tab.
* Create a group: Click on the create group tab. Enter your group name and select at least one group tag 
from the top row of choice boxes. It is optional to add a description for your group. Click on the
 create group button. You will be taken to the search group tab.
* Add a meeting to your group: Click on the edit group tab. Select the group you want to add the
meeting to in the group selector choice box. The group's details should automatically fill. Navigate
to the add meeting section and enter a location, date and time (non-military). Click add meeting. The
new meeting should be added to the table below.
* Edit a meeting: Click on the edit group tab. Select the group you want to add the
meeting to in the group selector choice box. The meeting table should be populated with the group's 
meetings. Double click a row in the table to open the edit meeting dialogue. Change location, date,
and time for the new meeting and click save changes. Under the edit group tab, you will see your
updated meeting.
* Edit your account: Click on the profile tab. Click edit profile and a new dialogue will open. Enter
your old password, a new password, and a new email. Once these fields are confirmed, press the save 
button and you will be taken back to the profile tab.
* Join a group: Click on the join group tab. Enter some search criteria and click search. You do not
need to choose any group tags to search. Select a row from the table of results and click the join
button. This group is now displayed in the profile tab.
* Search a group: In the join group tab, select up to four tags. Click the search button to search
for groups that contain those tags. To search for groups that contain only those tags, select must
include all and click search.
* Search for a meeting: Click on the view meetings tab. Specify a date, location, and host group in
the search criteria. Click the search button. The results of the query will be shown in the table below.
* View a meeting: After performing a search, select a row from the table of meetings. Click the view
meeting button. A dialogue box will open with the meeting's details and roster. Click the back button
to return to the previous scene.
* Sign-up for meeting: Return to the view meeting dialogue. Click the attend meeting button to add
your name to the roster. Click the cancel attendance button to remove your name from the roster. Click
the back button when you are finished.
* Edit a group: Go to the edit group tab. Select a group you own from the choice box. Change your group
tags or remove some, but the group must have at least one group tag. Change the group description or
remove it. Click the save changes button when you are done. 
