# LittleHarppy User Guide

Hello! Welcome to LittleHarppy. This user guide will walk you through how to use the essential features of LittleHarppy to keep track of your tasks with ease. Follow these simple instructions, and you'll be up and running in no time!

## 1. Getting Started
Once you start LittleHarppy, it will greet you with a friendly message:
Hello! I'm LittleHarppy  
  /^_^/  
 ( o.o )  
 \> ~ <

## 2. Adding Tasks
You can add different types of tasks using the following commands:
- Todo Task:  
Adds something you need to do to the list.  
**Format:** `todo t/TASK`  
Examples: 
  - `todo CS2113 ip Level 7`  
  - `todo laundry`  

  After adding a todo task, you will see:
```
Got it. I've added this task:
  [T][ ] laundry
Now you have 2 tasks in the list.
```

- Deadline Task:  
  Adds a task with a specific deadline to your list.  
  **Format:** `deadline t/Task /by d/Date`  
  Examples:
    - `deadline CS2113 assignment /by 06/03/2025 2359`
    - `deadline Clash of Clans War Games /by Friday 1800`

  After adding a deadline task, you will see:
```
Got it. I've added this task:
  [D][ ] Clash of Clans War Games (by: Friday 1800)
Now you have 3 tasks in the list.
```

- Event Task:  
  Adds an event with a start and end time to your list.   
  **Format:** `event t/TASK /from d/START_DATE_TIME /to d/END_DATE_TIME`  
  Examples:
    - `event NUS Open House 2025 /from 08/06/2025 9am /to 08/06/2025 6pm`
    - `event CS2113 Midterm /from 07/06/2025 12pm /to 07/06/2025 2pm`

  After adding a deadline task, you will see:
```
Got it. I've added this task:
  [E][ ] CS2113 Midterm (from: 07/06/2025 12pm to: 07/06/2025 2pm)
Now you have 4 tasks in the list.
```


## 3. Listing all tasks: `list`
Shows a list of all tasks in the saved file.
Format: `list`

## 4. Deleting a task: `delete`
Marks a task as completed in your task list.

**Format:** `delete INDEX`

Example:
- `delete 2` deletes task #2 from the list.

After deleting a task, you will see:
```
Noted. I've removed this task:
  [T][X] laundry
Now you have 3 tasks left.
```  

## 5. Locating tasks by keyword: `find`
Finds tasks with descriptions containing any of the given keywords.

**Format:** `find KEYWORD [MORE_KEYWORDS]`
- The search is case-insensitive. e.g `sleep` will match `SLEEP`
- The order of the keywords matter. e.g `read book` will not match `book read`

Example:
- `find NUS` returns
```
Here are the matching tasks in your list: 
1. [E][ ] NUS Open House 2025 (from: 08/06/2025 9am to: 08/06/2025 6pm)
```

## 6. Marking/Unmarking tasks as completed: `mark` or `unmark`
Marks a task as completed or unmarks a task as incomplete in your task list.

**Format:** `mark INDEX` or `unmark INDEX`

Example:
- `mark 2` marks task #2 as completed.

After marking a task as completed, you will see:
```
Nice! I've marked this task as done:
[T][X] laundry
```  

## 7. Exiting the Chatbot
When you're done, type `bye` to exit LittleHarppy.







