import java.util.Scanner;
public class LittleHarppy {
    public static void main(String[] args) {
        String chatbotName = "LittleHarppy";
        Scanner scanner = new Scanner(System.in);
        //Hello! I'm little.Harpy
        //What can I do for you?
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("rename")) {
                System.out.print("Enter a new name for me: ");
                chatbotName = scanner.nextLine().trim();
                System.out.println("Great! You can now call me " + chatbotName + ".");
            } else if (userInput.equalsIgnoreCase("greet")) {
                System.out.println(chatbotName + ": Hello! How can I assist you today?");
            }else if (userInput.equalsIgnoreCase("quit")) {
                break;
            } else {
                System.out.println(chatbotName + ": I'm sorry, I don't understand that command.");
            }
        }
        //Bye. Hope to see you again soon!
        System.out.println(chatbotName + ": Goodbye! Have a nice day!");
        System.out.println("____________________________________________________________");
    }
}
