import java.util.Arrays;
import java.util.Scanner;

public class SmartBanking {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = " \u1F477Welcome to Smart Banking System";
        final String NEW_ACC = "\u2795  Open New Account";
        final String DEPOSIT_MONEY = "\u1F91D Deposit Money";
        final String WITHDRAW_MONEY = "\u1F3E7 Withdraw Money";
        final String TRANSFER = "\u1F91D Transfer Money";
        final String CHECK_BALANCE = "🖨 Check Balance";
        final String DELETE_AC = "🖨 Delete Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        // Arrays
        String[] accId = new String[0];
        String[] names = new String[0];
        Double[] deposits = new Double[0];

        String screen = DASHBOARD;

        do {
            System.out.println(CLEAR);
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen) {
                case DASHBOARD:

                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer");
                    System.out.println("\t[5]. Check A/C Balance");
                    System.out.println("\t[6]. Delete Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = NEW_ACC;
                            break;
                        case 2:
                            screen = DEPOSIT_MONEY;
                            break;
                        case 3:
                            screen = WITHDRAW_MONEY;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = CHECK_BALANCE;
                            break;
                        case 6:
                            screen = DELETE_AC;
                            break;
                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:

                            continue;

                    }
                    break;
                // Create New Account
                case NEW_ACC:
                    System.out.printf("\tNew Student ID: SDB-%05d \n", (accId.length + 1));
                    String id = String.format("SDB-%05d", accId.length + 1);

                    boolean valid;
                    String name;
                    do {
                        valid = true;
                        System.out.print("\tName: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()) {
                            System.out.printf("\t%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) ||
                                    Character.isSpaceChar(name.charAt(i)))) {
                                System.out.printf("\t%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);

                    String[] newaccIds = new String[accId.length + 1];
                    String[] newCustomerNames = new String[names.length + 1];
                    for (int i = 0; i < accId.length; i++) {
                        newaccIds[i] = accId[i];
                        newCustomerNames[i] = names[i];
                    }

                    newaccIds[newaccIds.length - 1] = id;
                    newCustomerNames[newCustomerNames.length - 1] = name;
                    accId = newaccIds;
                    names = newCustomerNames;

                    do {
                        valid = true;
                        System.out.print("\tEnter Initial Deposit: ");
                        double deposit = SCANNER.nextDouble();
                        SCANNER.nextLine();

                        if (deposit < 5000) {
                            System.out.printf("\t%sInitial deposit should be more than 5000%s\n", COLOR_RED_BOLD,
                                    RESET);
                            valid = false;
                        }

                        Double[] newDeposits = new Double[deposits.length + 1];
                        for (int i = 0; i < deposits.length; i++) {
                            newDeposits[i] = deposits[i];

                        }
                        newDeposits[newDeposits.length - 1] = deposit;
                        deposits = newDeposits;

                    } while (!valid);

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,
                            String.format("%s:%s has been saved successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                        continue;
                    screen = DASHBOARD;
                    break;

                // Deposit Money
                case DEPOSIT_MONEY:

                    do {
                        double amount;
                        valid = true;
                        System.out.print("\tEnter A/C Number: ");
                        id = SCANNER.nextLine().toUpperCase().strip();
                        if (id.isBlank()) {
                            System.out.printf(ERROR_MSG, "ID can't be empty");
                            valid = false;
                        } else if (!id.startsWith("SDB-") || id.length() < 5) {
                            System.out.printf(ERROR_MSG, "Invalid ID format");
                            valid = false;
                        } else {
                            String number = id.substring(4);
                            for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))) {
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    valid = false;
                                    break;
                                }
                            }

                            int index = 0;
                            for (int i = 0; i < accId.length; i++) {
                                if (accId[i].equals(id)) {

                                    index = i;
                                }
                            }
                            System.out.printf("\tCurrent Balance: Rs:%,.2f \n", deposits[index]);

                            do {
                                valid = true;
                                System.out.print("\tEnter Deposit Amount: ");

                                amount = SCANNER.nextDouble();
                                SCANNER.nextLine();

                                if (amount < 500) {
                                    System.out.printf("\t%sInsufficient Amount.Amount should be more 500/=%s\n",
                                            COLOR_RED_BOLD,
                                            RESET);
                                    valid = false;
                                }
                            } while (!valid);
                            deposits[index] += amount;
                            System.out.printf("\tCurrent Balance: Rs:%,.2f \n", deposits[index]);

                            System.out.print("\tDo you want to continue adding (Y/n)? ");
                            if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                                continue;
                            screen = DASHBOARD;
                            break;
                        }
                    } while (!valid);

                    // Withdraw Money
                case WITHDRAW_MONEY:

                    do {
                        valid = true;
                        double amount;
                        // check A/C Number
                        System.out.print("\tEnter A/C Number: ");
                        id = SCANNER.nextLine().toUpperCase().strip();
                        if (id.isBlank()) {
                            System.out.printf(ERROR_MSG, "ID can't be empty");
                            valid = false;
                        } else if (!id.startsWith("SDB-") || id.length() < 5) {
                            System.out.printf(ERROR_MSG, "Invalid ID format");
                            valid = false;
                        } else {
                            String number = id.substring(4);
                            for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))) {
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    valid = false;
                                    break;
                                }
                            }
                        }

                        // Current Balance

                        int index = 0;

                        for (int i = 0; i < accId.length; i++) {
                            if (accId[i].equals(id)) {

                                index = i;
                                break;
                            }
                        }
                        System.out.printf("\tCurrent Balance: Rs:%,.2f \n", deposits[index]);

    // Withdraw Money
                        do {
                            valid = true;
                            System.out.print("\tEnter Withdraw Amount: ");

                            amount = SCANNER.nextDouble();
                            SCANNER.nextLine();

                            if (amount < 100 || deposits[index] < 500) {
                                System.out.printf("\t%sInsufficient A/C Balance %s\n", COLOR_RED_BOLD,
                                        RESET);
                                valid = false;
                            }
                        } while (!valid);
                        deposits[index] -= amount;
                        System.out.printf("\tCurrent Balance: Rs:%,.2f \n", deposits[index]);

                    } while (!valid);
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                        continue;
                    screen = DASHBOARD;
                    break;
//Transfer Money
                case TRANSFER:

                    // do {
                    //     double amount;
                    //     valid = true;
                    //     System.out.print("\tEnter From A/C Number: ");
                    //     id = SCANNER.nextLine().toUpperCase().strip();
                    //     if (id.isBlank()) {
                    //         System.out.printf(ERROR_MSG, "ID can't be empty");
                    //         valid = false;
                    //     } else if (!id.startsWith("SDB-") || id.length() < 5) {
                    //         System.out.printf(ERROR_MSG, "Invalid ID format");
                    //         valid = false;
                    //     } else {
                    //         String number = id.substring(4);
                    //         for (int i = 0; i < number.length(); i++) {
                    //             if (!Character.isDigit(number.charAt(i))) {
                    //                 System.out.printf(ERROR_MSG, "Invalid ID format");
                    //                 valid = false;
                    //                 break;
                    //             }
                    //         }

                    //         int index = 0;
                    //         for (int i = 0; i < accId.length; i++) {
                    //             if (accId[i].equals(id)) {

                    //                 index = i;
                    //             }
                    //         }
                    //         System.out.printf("\tFrom A/C Name: %s \n", names[index]);

                    //         do {
                    //             valid = true;
                    //             System.out.print("\tEnter Transfer Amount: ");

                    //             amount = SCANNER.nextDouble();
                    //             SCANNER.nextLine();

                    //             if (amount < 100 || deposits[index]<500) {
                    //                 System.out.printf("\t%sInsufficient Amount %s\n",
                    //                         COLOR_RED_BOLD,
                    //                         RESET);
                    //                 valid = false;
                    //             }
                    //         }
                    //         while (!valid);
                 
                        
                    // System.out.print("\tDo you want to continue adding (Y/n)? ");
                    // if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                    //     continue;
                    // screen = DASHBOARD;
                    break;

                case CHECK_BALANCE:

                 do {
                        double amount;
                        valid = true;
                        System.out.print("\tEnter A/C Number: ");
                        id = SCANNER.nextLine().toUpperCase().strip();
                        if (id.isBlank()) {
                            System.out.printf(ERROR_MSG, "ID can't be empty");
                            valid = false;
                        } else if (!id.startsWith("SDB-") || id.length() < 5) {
                            System.out.printf(ERROR_MSG, "Invalid ID format");
                            valid = false;
                        } else {
                            String number = id.substring(4);
                            for (int i = 0; i < number.length(); i++) {
                                if (!Character.isDigit(number.charAt(i))) {
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    valid = false;
                                    break;
                                }
                            }

                            int index = 0;
                            for (int i = 0; i < accId.length; i++) {
                                if (accId[i].equals(id)) {

                                    index = i;
                                }
                            }
                            System.out.printf("\tName:%s \n",names[index]);
                            System.out.printf("\tCurrent Balance: Rs:%,.2f \n", deposits[index]);
                            System.out.printf("\tAvialable Balance: Rs:%,.2f \n", deposits[index]-500);

                           
                    
                            System.out.print("\tDo you want to continue adding (Y/n)? ");
                            if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                                continue;
                            screen = DASHBOARD;
                            break;
                        }
                    } while (!valid);
                }
            

        } while (true);
    }

}
    
