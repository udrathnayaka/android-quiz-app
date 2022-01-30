# android-quiz-app

A fun, user friendly, minimalistic quiz app where a user is able to answer multiple choice questions and get results at the end.

Brainy quiz is a quiz app that allows the player to play a quiz of 10 random questions within 1 minute and see his/her performance. Technology stack includes Android and Firebase.
Brainy Quiz covers areas such as IQ, Science, History, Geography and Famous people. Each question is given 4 options, and the player can select the correct answer out of them. The countdown timer is displayed for 10 questions at the top of the quiz. At the end of 1 minute a summary will be displayed containing the total number of questions answered, correct and incorrect count.
Once the app is opened the player will see the splash screen which after a few seconds, will be directed to the Home Screen. Here the player will be able to click on the “Instructions” button and read the instructions before playing or click on “Start Quiz” to start playing.
Mentioned below are the main functions of the quiz app;

**1. Start Quiz** <br>
The player will be directed to the Question screen where the player will be shown the reverse timer and the first question out of 10 random questions, along with a chance to choose one out of 4 options. 
If the correct answer is chosen, the selected answer will turn Green color, correct answer count will increase by 1, total questions answered will increase by 1, a message called “correct!” will be displayed and the next random question will be loaded into the Question activity.
If the wrong answer is chosen, the selected answer will turn Red in color and the correct answer will be highlighted in Green, incorrect answer count will increase by 1, total questions answered will increase by 1, a message called “incorrect!” will be displayed and the next random question will be loaded into the Question activity.
The objective of this is to answer 10 random questions under 60 seconds. At the end of 60 seconds the player will be directed to Result activity showing the total questions answered, correct and incorrect answer count respectively.
The player can go back to the home back by clicking the home icon on the top left corner or by pressing the back button.

**2. Admin Authentication** <br>
An admin authentication is performed as only an admin will be able to add, update, delete questions and view the question list.
These functions can be accessed by clicking on “Main Menu” button on the home screen which will direct the user to the Dashboard. Here the admin can click on “Administrator” to go to the login page where the admin will have to provide the admin username and password.
If correct username and password is given, the credentials will be authenticated against the users defined in firebase, which will then be redirected to the question list view  Screen.
If incorrect username and password is given, user will then be redirected to the Dashboard.

**3. Question List** <br> 
Here the admin is able to see the list of all questions stored in the firebase database. Also at the bottom right corner is a compose button which directs admin to “Insert question” screen which allows the user to add a new question.
By clicking on the row of a question, the admin will be directed to “Question View” screen.

**4. Insert Question** <br>
Allows the admin to insert a new question to the database. On the “Insert” screen the admin can give the new question, four options and the correct answer. If all the fields are not filled, “Fill all fields!” message will pop. 
Once all fields are filled and admin decides to insert the question, the admin can click on the “Insert” button. Then the admin will be prompted with a dialog box asking the user to confirm the inserting of the question. If admin clicks “yes” the new question will be added to the database, “Successfully added!” message will be displayed and admin will be directed to an updated question list. But if admin clicks “no”, question insertion will be cancelled and will return to question list view.
However, if admin decides to cancel insert in between filling the fields, the admin can click on the back arrow button on the top left corner, which will call the OnBackPressed() method, and direct the used back to the question list view.

**5. View Question** <br>
Here the admin is able to view the question selected in detail. The question, four options and the answer to the question. 
On the top left corner the back arrow button calls the OnBackPressed() method, which will direct the used back to the question list view.
If admin wishes to update the question, the admin can click on the “Update” button which will open the “Update Question” Screen.
If admin wish to delete the question, the admin can click on the “Delete” button.

**6. Delete Question** <br>
If admin wish to delete the question, the admin can click on the “Delete” button. and a dialog box will be displayed requesting for confirmation of deletion. 
If “yes” is clicked the question will be deleted from the database and will return to a refreshed List view. “Question Deleted Successfully!” message will appear.
If “no” is clicked dialog box will be closed and delete will be canceled. 

**7. Update Question** <br>
Here the question details existing in the database will be filled into the fields, and admin can do changes to the existing question fields. 
Once changes are done, the admin can click on “Update” button which will update the database, and return to a refreshed question list view. But if all the fields are not field update will not happen and “Fill all fields!” message will be shown.
However, if the admin decides to cancel updating the question and go back to the list View then the Question updating will be cancelled and admin will be directed to the question list.

**8. Exit app** <br>
By clicking on the exit button on the dashboard, user will be able to close the app. However, that is if the user answers “yes” in the exit confirmation dialog box. If the user clicks “no” then exiting the app will be cancelled.

In addition to the above-mentioned functions 2 extra screens have been added as below;

**9. Extra Screens** <br>
Instructions Screen: This holds the instructions for the player.
About Us Screen: This holds the developer’s information and the app version. 
