package homeworks.homework3;

import homeworks.homework3.dao.DAOService;
import homeworks.homework3.dao.DAOServiceImpl;
import homeworks.homework3.dao.data_objects.Customer;
import homeworks.homework3.dao.data_objects.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static DAOService service = new DAOServiceImpl();

    public static void main(String[] args) throws IOException {
        //Для инициализации начальных данных в базе
        //initDataAuto();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        //Выводим товары которые купил покупатель
        System.out.println("Введите id покупателя: ");
        System.out.println("Покупатель купил товары: \n" + service.getListProductByCustomerId(Integer.parseInt(bf.readLine())));

        //Выводим покупателей которые купили товар
        System.out.println("Введите id товара: ");
        System.out.println("Данный товар купили покупатели: \n" + service.getListCustomerByProductId(Integer.parseInt(bf.readLine())));


        //Удаление товара у клиента
        System.out.println("Введите id покупателя у которого требуется удалние товара: ");
        int i = Integer.parseInt(bf.readLine());
        System.out.println("Покупатель купил товары: \n" + service.getListProductByCustomerId(i));
        System.out.println("Введите id товара, который необходимо удалить у клиента с id = " + i + " : ");
        service.deleteProductByCustomerId(i,Integer.parseInt(bf.readLine()));
        System.out.println("Покупка у покупателя удалена");
        System.out.println("У покупателя остались товары: \n" + service.getListProductByCustomerId(i));

        //Для отображения содерждимого в базе
        //printAllData();
    }

    private static void printAllData() {
        List<Product> products = service.getListProduct();
        System.out.println(products);

        List<Customer> customers = service.getListCustomer();
        System.out.println(customers);
    }

    //Ручная инициализация
    private static void initData() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            Customer customer = null;
            try {
                customer = new Customer(bf.readLine());
                service.addCustomer(customer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 3; i++) {
            Product product = null;
            try {
                product = new Product(bf.readLine(), Float.parseFloat(bf.readLine()));
                service.addProduct(product);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Автоматическая инициализация
    private static void initDataAuto() {
        for (int i = 0; i < 3; i++) {
            List<Product>  products= new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                Product product = new Product("Computer " + i + " " + j, 10.5f + i + j +6);
                service.addProduct(product);
                products.add(product);
            }

            Customer customer = new Customer("Pavel" + i, products);
            service.addCustomer(customer);
        }


    }
}
