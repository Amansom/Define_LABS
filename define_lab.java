import java.util.*;

public class prac {
    public static Map<String, Double> taxRates = new HashMap<>();
    public static Map<String, List<String>> taxGroups = new HashMap<>();
    public static List<Product> products = new ArrayList<>();

    static {
        // Task 1: Tax Rates
        taxRates.put("VAT", 1.2);
        taxRates.put("SGST", 1.0);
        taxRates.put("CGST", 1.0);
        // Add more taxes as needed

        // Task 2: Tax Groups
        taxGroups.put("NoTax", new ArrayList<>());
        taxGroups.put("VATOnly", List.of("VAT"));
        taxGroups.put("SGST_CGST", List.of("SGST", "CGST"));
        // Add more tax groups as needed

        // Task 3: Products
        products.add(new Product("Soap", 10, "SGST_CGST"));
        products.add(new Product("Petrol", 65, "VATOnly"));
        // Add more products as needed
    }

    public static void generateInvoice(List<Product> productList) {
        double totalPrice = 0;
        double totalTax = 0;

        // Print the header
        System.out.println("Product\tPrice\tTax");

        // Calculate and print details for each product
        for (Product product : productList) {
            String name = product.name;
            double price = product.price;
            String taxGroup = product.taxGroup;
            List<String> applicableTaxes = taxGroups.get(taxGroup);
            double taxAmount = 0;

            // Calculate tax amount
            for (String tax : applicableTaxes) {
                taxAmount += taxRates.get(tax);
            }

            totalPrice += price;
            totalTax += taxAmount;

            // Print product details
            System.out.println(name + "\t" + price + "\t" + taxAmount);
        }

        // Calculate and print the total and grand total
        double grandTotal = totalPrice + totalTax;
        System.out.println("Total\t" + totalPrice + "\t" + totalTax);
        System.out.println("Grand Total:\t" + grandTotal);
    }

    public static void main(String[] args) {
        generateInvoice(products);
    }
}

class Product {
    String name;
    double price;
    String taxGroup;

    public Product(String name, double price, String taxGroup) {
        this.name = name;
        this.price = price;
        this.taxGroup = taxGroup;
    }
}