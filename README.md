# LiftTracker
Run using JavaFX, LiftTracker is a Java GUI desktop application used to track gym workouts. Information is stored and can be graphed. Routines can be created for easy entries.

Structure:
	- All scenes (FXML Files) have a controller
	- All scenes and controllers have the same BorderPane with a top functioning MenuBar

	- Entered workout data is saved in record.txt using the Record class. The record is transfered to a temporary ArrayList of Exercise objects
	- An Exercise object contains the exercise name, weight, reps, sets, and a calculated totalLifted

	- Entered routines are saved in routine.txt using the RoutineRecord class. The record is trensfered to a temparary ArrayList of Routine objects
	- A routine objects contains the routine name, and an array of exercise names

	- Routines are used to easily graph simmilar exercises by routine name. Also, they allow for easy data entry, as the exercise name is certain to be consistent

	- There a several scenes grouped by three menus: View, Edit, and Graph
		- View allows the user to view the Record and Routine Record
		- Edit allows the user to edit both records, create entries, and create routines
		- Graph allows the user to graph all the record data over time or graph by exercise or routine

	- The initial scene is the add entry scene

	- The StringTime class provides static methods for getting the current data or time as a String

Dependencies: 
	- JavaFX and Java Libraries


