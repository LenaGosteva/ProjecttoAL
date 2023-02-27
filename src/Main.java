import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static String exit = "exit";
    static Logger logger;
    static int id, id_p;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        logger = new Logger("Logger");
        logger.setMsg("Program is started", "info");
        File personal = new File("C:\\AtomLabAllLessons\\ProjecttoAL\\src\\Personal.txt");
        File products = new File("C:\\AtomLabAllLessons\\ProjecttoAL\\src\\Products.txt");
        FileOperation personalFO = new FileOperation(personal);
        FileOperation productFO = new FileOperation(products);

        ArrayList<Personal> listPersonal = new ArrayList<>();
        listPersonal = personalFO.readFilePersonal();
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct = productFO.readFileProduct();
        id_p = (listProduct.size() == 0) ? 1 : listProduct.stream().mapToInt(Product::getId).max().getAsInt() + 1;

        id = (listPersonal.size() == 0) ? 1 : listPersonal.stream().mapToInt(Personal::getId).max().getAsInt() + 1;
        String myPassword, myLogin;
        String[] authData;

        System.out.println("\nYou are personal member or a customer? Please type it to console.");
        String enterRole = scanner.nextLine();

        switch (enterRole) {
            case "customer":
                System.out.println("\nOkay. You can add some product");

                addProduct(listProduct, productFO);
                break;
            case "personal":
                System.out.print(AllConstants.ANSI_BLUE + "Okay. Please, enter your login: " + AllConstants.ANSI_RESET);
                myLogin = scanner.nextLine();
                System.out.print(AllConstants.ANSI_BLUE + "Okay. Please, enter your password: " + AllConstants.ANSI_RESET);
                myPassword = scanner.nextLine();
                authData = authPerson(listPersonal, myLogin, myPassword);

                if (authData == null) {
                    logger.setMsg("You're not in a base", "err");
                    return;
                } else {
                    System.out.println("\nHello, " + authData[1]);
                    switch (authData[0]) {
                        case "Storekeeper":
                            System.out.println("\nOkay, you're a storekeeper. You can see and add all products. " +
                                    "\nIf you want see products, please type 'see', " +
                                    "\nIf you want add new product, please type 'add', " +
                                    "\nif you want finish program, type anything else. ");
                            while (true) {
                                switch (scanner.nextLine()) {
                                    case "see":
                                        see(listProduct);
                                        break;
                                    case "add":
                                        addProduct(listProduct, productFO);
                                        break;
                                    default:
                                        logger.setMsg("Program is finished", "info");
                                        System.exit(0);
                                        break;

                                }
                            }
                        case "Seller":
                            System.out.println("\nOkay, you're a seller. You can see all products. " +
                                    "\nIf you want see products, please type 'see', " +
                                    "\nif you want finish program, type anything else. ");
                            while (true) {
                                switch (scanner.nextLine()) {
                                    case "see":
                                        see(listProduct);
                                        break;
                                    default:
                                        logger.setMsg("Program is finished", "info");
                                        System.exit(0);
                                        break;
                                }
                            }
                        case "Manager":
                            System.out.println("\nOkay, you're a manager. If you want add another personal, please type 'add personal'," +
                                    "\nif you want add product, please type 'add product', " +
                                    "\nif you want see all products, please type 'see products', " +
                                    "\nif you want see all personal, please type 'see personal', " +
                                    "\nif you want finish program, type anything else. ");
                            while (true) {
                                switch (scanner.nextLine()) {
                                    case "add personal":
                                        addPersonal(listPersonal, personalFO);
                                        break;
                                    case "add product":
                                        addProduct(listProduct, productFO);
                                        break;
                                    case "see personal":
                                        see(listPersonal);
                                        break;
                                    case "see products":
                                        see(listProduct);
                                        break;
                                    default:
                                        logger.setMsg("Program is finished", "info");
                                        System.exit(0);
                                        break;
                                }

                            }
                    }
                    break;
                }
            default:
                logger.setMsg("You can't go in system((( please, try again", "err");
                return;
        }
    }

    private static <T> void see(ArrayList<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }


    public static void checkRole(String role) {
        switch (role) {
            case "Manager":
                System.out.println(AllConstants.ANSI_BLACK + "You will add Manager. So, he can add and see products and new personal" + AllConstants.ANSI_RESET);
                break;
            case "Storekeeper":
                System.out.println(AllConstants.ANSI_BLACK + "You will add  Storekeeper. So, he can add and see products" + AllConstants.ANSI_RESET);
                break;
            case "Seller":
                System.out.println(AllConstants.ANSI_BLACK + "You will add Seller. So, he can also see all products" + AllConstants.ANSI_RESET);
                break;
            default:
                logger.setMsg("You can't add this person to personal", "err");
                System.out.println("\nEnter role again: ");
                String newRole = new Scanner(System.in).nextLine();
                checkRole(newRole);
                break;
        }
    }

    public static void addProduct(ArrayList<Product> listProduct, FileOperation productFO) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter name:");
            String name = scanner.nextLine();
            System.out.println("Enter price:");
            Float price = scanner.nextFloat();
            scanner.nextLine();
            logger.setMsg("If you want to complete the input, write 'exit'", "war");
            listProduct.add(new Product(id_p, name, price));

            String s = scanner.nextLine();
            if (exit.equals(s)) {
                logger.setMsg("Program is finished", "info");
                break;
            }
            id_p += 1;
        }
        if (listProduct.size() != 0) {
            productFO.writeFile(listProduct);
        }
    }

    public static void addPersonal(ArrayList<Personal> listPersonal, FileOperation personalFO) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\nEnter role:");
            String role = scanner.nextLine();
            checkRole(role);
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter login:");
            String login = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            scanner.nextLine();
            logger.setMsg("If you want to complete the input, write 'exit'", "war");
            System.out.println();
            if (exit.equals(scanner.nextLine())) {
                logger.setMsg("Program is finished", "info");
                break;
            }
            listPersonal.add(new Personal(id, role, name, password, login));

            id += 1;
        }
        if (listPersonal.size() != 0) {
            personalFO.writeFile(listPersonal);
        }
    }

    static String[] authPerson(ArrayList<Personal> data, String... authData) {
        String[] result = null;
        for (int i = 0; i < data.size(); i++) {
            if (authData[0].equals(data.get(i).login)
                    && authData[1].equals(data.get(i).password)) {
                result = new String[2];
                result[0] = data.get(i).getRole();
                result[1] = String.valueOf(data.get(i).getName());
                break;
            }
        }
        return result;
    }
}