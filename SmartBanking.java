import java.util.Scanner;

public class SmartBanking{
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
        String screen = DASHBOARD;


        do{
            System.out.println(CLEAR);
        final String APP_TITLE = String.format("%s%s%s",
            COLOR_BLUE_BOLD, screen, RESET);


            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen)
            {
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


                switch (option){
                    case 1: screen = NEW_ACC; break;
                    case 2: screen = DEPOSIT_MONEY; break;
                    case 3: screen = WITHDRAW_MONEY; break;
                    case 4: screen = TRANSFER; break;
                    case 5: screen = CHECK_BALANCE; break;
                    case 6: screen = DELETE_AC; break;
                    case 7: System.out.println(CLEAR); System.exit(0);
                    default: continue;
                }
                break;

            case NEW_ACC:
                
            }
        }while(true);
    }
            

}
