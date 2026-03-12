import java.util.*;

// Class used for sorting subject with score
// CO1: Used for sorting subjects using Bubble Sort algorithm
class SubjectScore {

    String subject;
    double score;

    SubjectScore(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }
}

// Linked List Node
// CO2: Implementation of Linked List Node for storing subject and readiness
class Subject {

    String name;
    double readiness;
    Subject next;

    Subject(String name, double readiness) {
        this.name = name;
        this.readiness = readiness;
        this.next = null;
    }
}

// Linked List Implementation
// CO2: Custom implementation of Linked List ADT with insertion and traversal
class SubjectLinkedList {

    Subject head;

    // Insert operation in Linked List
    void addSubject(String name, double readiness) {

        Subject newSubject = new Subject(name, readiness);

        if (head == null) {
            head = newSubject;
            return;
        }

        Subject temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newSubject;
    }

    // Traversal operation in Linked List
    void displaySubjects() {

        if (head == null) {
            System.out.println("No subjects added yet.");
            return;
        }

        Subject temp = head;

        while (temp != null) {
            System.out.printf("%s : %.1f%%\n", temp.name, temp.readiness);
            temp = temp.next;
        }
    }
}

// CO3: Custom Stack implementation using Array
class SubjectStack {

    String stack[] = new String[50];
    int top = -1;

    void push(String subject) {

        if (top == stack.length - 1) {
            System.out.println("Stack Overflow");
            return;
        }

        stack[++top] = subject;
    }

    String peek() {

        if (top == -1) {
            return null;
        }

        return stack[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}

// CO4: Custom Hash Table using Division Method
class HashTable {

    static class HashNode {

        String key;
        double value;

        HashNode(String key, double value) {
            this.key = key;
            this.value = value;
        }
    }

    int SIZE = 20;
    HashNode table[] = new HashNode[SIZE];

    // Division Method Hash Function
    int hashFunction(String key) {

        int sum = 0;

        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }

        return sum % SIZE;
    }

    // Insert into hash table
    void put(String key, double value) {

        int index = hashFunction(key);

        while (table[index] != null) {
            index = (index + 1) % SIZE; // Linear Probing
        }

        table[index] = new HashNode(key, value);
    }

    // Search operation
    Double get(String key) {

        int index = hashFunction(key);

        while (table[index] != null) {

            if (table[index].key.equals(key)) {
                return table[index].value;
            }

            index = (index + 1) % SIZE;
        }

        return null;
    }

    boolean containsKey(String key) {
        return get(key) != null;
    }
}

// Node for To-Do tasks
class TaskNode {

    String task;
    TaskNode next;

    TaskNode(String task) {
        this.task = task;
        this.next = null;
    }
}

// Linked List for To-Do tasks
class TaskList {

    TaskNode head;

    void addTask(String task) {

        TaskNode newTask = new TaskNode(task);

        if (head == null) {
            head = newTask;
            return;
        }

        TaskNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newTask;
    }

    void displayTasks() {

        if (head == null) {
            System.out.println("No tasks added.");
            return;
        }

        TaskNode temp = head;

        while (temp != null) {
            System.out.println("- " + temp.task);
            temp = temp.next;
        }
    }
}

public class StudentReadinessIndexSystem {

    // CO1: Algorithmic computation of readiness index using mathematical formula
    static double calculateReadiness(Scanner sc) {

        System.out.print("Total Chapters: ");
        int totalChapters = sc.nextInt();

        System.out.print("Revised Chapters: ");
        int revisedChapters = sc.nextInt();

        System.out.print("Total Topics: ");
        int totalTopics = sc.nextInt();

        System.out.print("Completed Topics: ");
        int completedTopics = sc.nextInt();

        System.out.print("Total Tests: ");
        int totalTests = sc.nextInt();

        System.out.print("Completed Tests: ");
        int completedTests = sc.nextInt();

        System.out.print("Confidence Level (1-10): ");
        int confidence = sc.nextInt();

        double syllabusPercent = (double) completedTopics / totalTopics * 100;
        double revisionPercent = (double) revisedChapters / totalChapters * 100;
        double testPercent = (double) completedTests / totalTests * 100;
        double confidencePercent = (double) confidence / 10 * 100;

        // CO1: Formula-based computation for readiness index
        return (syllabusPercent + revisionPercent + testPercent + confidencePercent) / 4;
    }

    // CO1: Bubble Sort algorithm used to sort subjects by readiness score
    static void bubbleSort(SubjectScore arr[], int n) {

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j].score > arr[j + 1].score) {

                    SubjectScore temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // CO2: Linked List used to store subjects dynamically
        SubjectLinkedList subjectList = new SubjectLinkedList();

        // CO4: Custom Hash Table used for fast lookup
        HashTable subjectScores = new HashTable();

        // CO3: Stack used to track recently added subjects
        SubjectStack recentSubjects = new SubjectStack();

        // CO1: Array used for sorting subjects
        SubjectScore scoreArray[] = new SubjectScore[50];
        int count = 0;

        // To-Do list for subjects
        HashMap<String, TaskList> todoList = new HashMap<>();

        int choice;

        do {

            System.out.println("\n===== Student Exam Preparation Readiness Index =====");
            System.out.println("1. Add Subject & Calculate Readiness");
            System.out.println("2. View All Subjects");
            System.out.println("3. Sort Subjects by Readiness");
            System.out.println("4. Get Readiness of a Subject");
            System.out.println("5. Show Recently Added Subject");
            System.out.println("6. Add To-Do Task for Subject");
            System.out.println("7. View To-Do List for Subject");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Subject Name: ");
                    String subject = sc.nextLine();

                    double readiness = calculateReadiness(sc);

                    // CO2: Insert subject into Linked List
                    subjectList.addSubject(subject, readiness);

                    // CO4: Insert into Hash Table
                    subjectScores.put(subject, readiness);

                    // CO3: Push into Stack
                    recentSubjects.push(subject);

                    // CO1: Add to array for sorting
                    scoreArray[count++] = new SubjectScore(subject, readiness);

                    System.out.printf("Readiness Score for %s = %.1f%%\n", subject, readiness);

                    break;

                case 2:

                    System.out.println("\nSubjects and Readiness:");
                    subjectList.displaySubjects();

                    break;

                case 3:

                    if (count == 0) {
                        System.out.println("No subjects added.");
                        break;
                    }

                    bubbleSort(scoreArray, count);

                    System.out.println("\nSubjects Sorted by Readiness:");

                    for (int i = 0; i < count; i++) {
                        System.out.printf("%.1f%% : %s\n", scoreArray[i].score, scoreArray[i].subject);
                    }

                    break;

                case 4:

                    System.out.print("Enter subject name: ");
                    String searchSubject = sc.nextLine();

                    if (subjectScores.containsKey(searchSubject)) {

                        double score = subjectScores.get(searchSubject);

                        System.out.printf("Readiness Score for %s = %.1f%%\n", searchSubject, score);

                    } else {

                        System.out.println("Subject not found.");
                    }

                    break;

                case 5:

                    if (!recentSubjects.isEmpty()) {

                        System.out.println("Most Recently Added Subject: " + recentSubjects.peek());

                    } else {

                        System.out.println("No subjects added yet.");
                    }

                    break;

                case 6:

                    System.out.print("Enter subject: ");
                    String sub = sc.nextLine();

                    if (!todoList.containsKey(sub)) {
                        todoList.put(sub, new TaskList());
                    }

                    System.out.print("Enter task: ");
                    String task = sc.nextLine();

                    todoList.get(sub).addTask(task);

                    System.out.println("Task added successfully.");

                    break;

                case 7:

                    System.out.print("Enter subject: ");
                    String subjectTodo = sc.nextLine();

                    if (todoList.containsKey(subjectTodo)) {

                        System.out.println("To-Do List for " + subjectTodo + ":");

                        todoList.get(subjectTodo).displayTasks();

                    } else {

                        System.out.println("No tasks found for this subject.");
                    }

                    break;

                case 8:

                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 8);

        sc.close();
    }
}