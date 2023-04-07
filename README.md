# Love-Log App

## The app that makes your dating experience whole!

About Love-Log:
- ***What** will the application do?*
  - In the age of online dating and social media, finding your love-match among the many options you have, can get overwhelming. This app allows you to keep track of the dozen dates you have been on, make logs, rate them, create personalized notes and reminders, as well as journals.
- ***Who** will use it?*
  - The app is appropriate for anyone from teenagers finding their *first love* to adults looking for their *true love*.This app makes the process of narrowing down the candidates to the most compatible one easy and fun. 
- ***Why** is this project of interest to you?*
  - I personally find dating exhausting as you have to try to remember all the details and later recollect those to make the decision of whether you want to following through with the person or not. This becomes specially burdensome when there are multiple "suitors" 😄. Such an app creates room for more organized thoughts and clearer evaluation of the dates.

## User Stories:
* As a user, I want to be able to create a new Journal.
* As a user, I want to be able to create a new Person and add to my Journal.
* As a user, I want to be able to maintain and view a List of People am currently dating.
* As a user, I want to be able to remove a Person from my list.
* As a user, I want to be able to create a new Date Entry and add it to the person.
* As a user, I want to be able to view the list of Dates and descriptions if I choose.
* As a user, I want to be able to add Qualities to the Person.
* As a user, I want to be able to remove Qualities and/or add Red Flags.
* As a user, I want to be able to modify persons on the list.
* As a user, I want to be able to save my journal to file (if I so choose)
* As a user, I want to be able to be able to load my journal from file (if I so choose)




*P.S. reference to JsonSerializationDemo Project in persistence package*

# Instructions for Grader:
- You can generate the first required action related to adding Xs to a Y by clicking on the 'Start a New Journal' button
and subsequently 'Create a New Person'. You will be prompted to fill out information about the person
such as Name, Age, Location, and Job. Then you can 'Add Person' to you Journal.
- You can generate the second/third/fourth required action related to adding Xs to a Y by going to 'View the List of People' and 
selecting a person from the left panel on the split pane. You will have options of adding to 'Dates'
with this person, 'See Dates' you have been on so far, or 'Remove Person'. 
- You can locate my visual component by running the application. It appears on the Main Journal GUI
- You can save the state of my application by navigating to the 'InJournal Gui' from whichever GUI you are in and choosing 
'Save' button.
- You can reload the state of my application by choosing the 'Load from File' button upon running the application (Main GUI)

# Phase 4: Task 2
Upon running the journal you can start a new journal, add persons (3 in this instance), add dates to each person, remove
a person if you wish and once user quits the program (closes application) the events will be printed to console. Sample:

    "Tue Apr 04 07:22:14 PDT 2023
    New Journal created.

    Tue Apr 04 07:22:32 PDT 2023
    Person added: Jack

    Tue Apr 04 07:22:47 PDT 2023
    Person added: Joe

    Tue Apr 04 07:23:07 PDT 2023
    Person added: Peter

    Tue Apr 04 07:23:26 PDT 2023
    New date added for: Jack

    Tue Apr 04 07:23:59 PDT 2023
    New date added for: Jack

    Tue Apr 04 07:24:33 PDT 2023
    New date added for: Jack

    Tue Apr 04 07:24:47 PDT 2023
    Candidate 3 was removed"

# Phase 4: Task 3"
After reviewing my code and reflecting on the UML diagram I realized there are segments of the code duplicated across 
different parts of my app this can make the codebase harder to maintain and lead to inconsistencies in the future. 
My UML diagram made me realize that there could be more efficient ways for my GUI classes to maintain associations with 
my model classes (i.e. MyJournal, Person, DateEntry). This level of coupling might be problematic. We could go further 
and implement designs such that the GUI classes only have associations with MyJournal class and access Person and DateEntry
through that.Implementing design patterns such as the Singleton Pattern for my persistence package classes (as JSON reader and writer are only 
been instantiated once) as well as refactoring to remove duplication can also improve code quality and cohesion and make it easier to modify 
and extend the application.


While working on Phase 3 I improvised some light refactoring by extracting the lines of code setting up GUI frame into 
its own method, similarly extracting panel set-up code into another method, and JButtons set-up and addition as another. 
However, this was done within each GUI class. Having noticed the similarity between each of these methods across the GUI 
classes, I could have created an abstract superclass with the set-up methods declared and implemented once. This way my GUI
classes could extend and inherit the general methods and override them whenever necessary (*I am aware that this change entails other 
modifications to the code specially with extending LogPrinter by GUI classes, as only one superclass can be extended*). 
Moreover, I could refactor similar methods such as *doAddToCons* and *doAddToPros* in my ui.MyJournalApp (console App) 
into one method, with different parameters (cons vs. pros) as they share much similarity. Overall, refactoring is an 
important part of the software development process. It can help improve the quality and maintainability of code, making 
it easier to work with in the future.

.