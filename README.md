# University-Project-05
## University project 5 - Programming 2 - Library Management System GUI

(Java) The second project in the Programming 2 course. This Java project uses a lot of advanced programming techniques and builds upon the first Programming 1 project by creating an easy to use GUI.

***NOTE: Some of the code contained within this assignment was written by the Professor, and is labelled as such at the top of the code. Anything else is my own creation.***

## Background information (from the course notes)

This assignment requires you to implement a Swing based graphical user interface for the “Library Management System (LMS)” developed in Assignment 1. The interface should provide a graphical means for the user to ‘build-up’ and view the library collection. 

Specifically, the user should be able to:
- add/remove holdings to/from a library collection
- view the graphical representation of a library collection (all existing holdings).

That is, the user interface is not required to support all the functionality of the LMS system (as per Assignment 1) however, it should allow the user to perform the following functions:

1. **Initialise/Reset the Library Collection.** i.e. the user should be able to enter all parameters required to instantiate the Library with a new (empty) Library Collection. The reset function should remove all holdings, if any, from the library collection, but preserve the originally entered library collection code and title.
2. **Add a holding to a Library Collection.** i.e. the user should be able to enter all parameters required to create a new Holding (the system should cover Books and Videos).
3. **Remove a holding from a Library Collection.**
4. **Display a grid-based view of a library collection showing all the existing holdings.** 
   - The library collection grid should be represented by an equally-sized grid of cells, where each grid cell is sized to take up the maximum amount of available screen area. 
   - The library collection grid can have up to 4 columns and unlimited number of rows, where columns and rows are added/removed on an ‘as needed bases’ (i.e. dynamically, depending on a total number of available holdings). 
   - Cells ‘occupied’ by a holding should have a gray background, and the holding details (code, title, standard loan fee, and loan period) should be displayed inside the cell.
     - Cells containing Books should have a blue border which should be 3 pixels in thickness.
     - Cells containing Videos should have a red border which should also be 3 pixels in thickness.
     - Scrolling functionality should be provided for the individual cells when the holding details cannot be fully displayed inside the cell boundaries.
   - Unoccupied cells should have a white background.
   - There shouldn’t be any ‘gaps’ (unoccupied cells) between the occupied cells. i.e. when removing a holding from the library collection grid, the existing holdings might need to be shifted to cover the resultant ‘gap’.
   - The user should be able to sort the displayed holdings according to the following criteria: NONE (no sorting i.e. original order), CODE (sorted by the holding code number, from lowest to highest), TYPE (sorted by the type of a holding, with all Books displayed first followed by all Videos).

Figures 1-4 show some examples of a library collection grid display. Note how the holding cell size changes to take up the maximum amount of screen area based on the total number of displayed holdings. Also, Figure 5 illustrates a sample UI that satisfies the stated grid display requirements.

![image](https://github.com/Andrew-Sanger/University-Project-05/assets/74388624/e6d882ad-4e57-490e-8c7e-9b7c91ed2992)

![image](https://github.com/Andrew-Sanger/University-Project-05/assets/74388624/26d0f648-e517-4ff6-8443-0b642d19b620)

![image](https://github.com/Andrew-Sanger/University-Project-05/assets/74388624/c568f4ea-cf8f-4fa6-b403-f9454f3e688a)

![image](https://github.com/Andrew-Sanger/University-Project-05/assets/74388624/7e3ed907-5578-4564-958c-c557c6ad0d84)

![image](https://github.com/Andrew-Sanger/University-Project-05/assets/74388624/6ac59a07-522c-45ce-bf8c-d79346b8c8ef)

### 1) Data Requirements
The following Holding data input requirements should be enforced by your GUI implementation:
- code - must be numeric only and have exactly 7 digits.
- title - min length of 3 characters (no character type restrictions).
- standard loan fee – must be $10 for Books, and $4 or $6 for Videos.
  
### 2) GUI Requirements
At a minimum the user interface should provide:
- **Two sets of action controls:** i) series of icon-based buttons; and ii) menu items; with common functions (init/reset library collection, add holding, and remove holding) being present on both control sets. Icons used in the sample GUI above can be found on blackboard (lms-icons.zip), however, feel free to use your own icons as you see fit.
- **A status bar showing the following information:** i) Library collection code; ii) Total number of books in the collection; and iii) Total number of videos in the collection.
- **Dialog boxes to confirm all important operations.** Specifically: exiting the application (you should create a custom WindowListener to achieve this); resetting the library collection; and removing a holding.
- **Dialog boxes to notify the user about any error conditions** (e.g. when invalid data was entered, or when holding addition could not be completed due to an exceptional condition).
- **Two different means for the removal of holdings:**
  - Direct manipulation - the user should be able to left click on any occupied cell on the grid and remove the corresponding holding. Note: the user must confirm the removal first.
  - Indirect manipulation – upon invoking the remove holding button or menu item option, the user should be presented with the list of all currently available holdings. The user should then be able to select any number of holdings from this list and remove them.
- **The main application window can be resized and your application should handle all the program map resizing using multiple containers and LayoutManagers. Do not use fixed x,y sizing and positioning.**

Your graphical user interface should also pay attention to factors that increase its usability. 

For example, some of the issues you should consider are:
- Choose GUI elements (containers and components) wisely, ensuring they are appropriate for the task required and that they minimise user input error where possible. For example, to enter the standard loan fee for a Video you should use radio buttons or checkboxes instead of basic text fields.
- Using Layout Managers appropriately to size and position components.

### 3) Design / Implementation Requirements
- **Your system design must be based on the Model-View-Controller (MVC) design pattern.** That is, you should organise all your code, using classes and packages, according to the Model-View-Controller approach.
- **All interactions with the Model component of MVC must be performed via the LMSModel interface.** This is similar to what we had in Assignment 1 where our TestHarness interfaced with the model (your Ass 1 core system) via this interface. Note that you are not allowed to modify the LMSModel interface.
- **The driver class (i.e. the class containing the main method), should be named LMSDriver and placed in the lms.view.main package.**
- **The system must utilise a tester utility class provided on blackboard (lms.view.util.Tester.java).** The purpose of this class is to pre-populate your model with a number of test Holdings. The tester utility should be automatically invoked after a new library collection has been initialised. The library collection grid should be updated accordingly (with holdings created by the tester utility). Do not modify the tester class.
- A model implementation of the LMS system (Assignment 1) will be made available; you can use this implementation as the Model component in your assignment, but should try using your own Ass 1 solution if possible. Note that your own implementation, if you decide to use it, must still expose the system functionality via the LMSModel interface as was done in Assignment 1.

### Additional Notes
- You are free to refer to textbooks, notes, work in study groups etc. to discover approaches to problems; however, the assignment must be your own individual work.
- Where you do make use of other references, please cite them in your work. Note that you will only be assessed on your own work so the use of third party code is discouraged.
- Code must be well-structured in terms of the cohesion and coupling. NOTE: “Rapid Application Development” user interface toolkits or libraries rarely produce well-structured code so their usage is not recommended.
