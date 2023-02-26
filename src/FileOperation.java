import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation {
    /*Поля*/
    private File file;
    private String filePath;
    Logger log = new Logger("C:\\Supermarket\\log.txt");

    /*Конструктор*/
    FileOperation(File file) {
        this.file = file;
        this.filePath = file.getPath();
        /*Методы*/
    }

    public ArrayList<Personal> readFilePersonal() {
        ArrayList<Personal> personals = new ArrayList<Personal>();

        try (FileReader read = new FileReader(file)) {
            Scanner in = new Scanner(read);
            String[] strArr;
            String str;
            while (in.hasNextLine()) {
                str = in.nextLine();
                strArr = str.split(" -> ");
                switch (strArr[1]){
                    case "Manager":
                        personals.add(new Manager(Integer.parseInt(strArr[0]), strArr[2], strArr[3], strArr[4]));
                        break;
                    case "Storekeeper":
                        personals.add(new Storekeeper(Integer.parseInt(strArr[0]), strArr[2], strArr[3], strArr[4]));
                        break;
                    case "Seller":
                        personals.add(new Seller(Integer.parseInt(strArr[0]), strArr[2], strArr[3], strArr[4]));
                        break;
                }

            }
        } catch (IOException e) {

        }
        return personals;
    }


    public ArrayList<Product> readFileProduct() {
        ArrayList<Product> products = new ArrayList<Product>();

        try (FileReader read = new FileReader(file)) {
            Scanner in = new Scanner(read);
            String[] strArr;
            String str;
            while (in.hasNextLine()) {
                str = in.nextLine();
                strArr = str.split(" -> ");
                products.add(new Product( Integer.parseInt(strArr[0]), strArr[1], Float.parseFloat(strArr[2])));

            }
        } catch (IOException e) {

        }
        return products;
    }
    public <T> void  writeFile(ArrayList<T> data) {

        try (FileWriter writer = new FileWriter(file)) {
            for (int j = 0; j < data.size(); j++) {
                if (data.get(j) instanceof T) {
                    T element = data.get(j);
                    writer.write(element.toString());
                    writer.append("\n");
                } else {
                    log.setMsg("Data of this class is not supported", "err");
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> void writeFile(T data) {

        try (FileWriter writer = new FileWriter(file)) {
                    writer.write(data.toString());
                    writer.append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}