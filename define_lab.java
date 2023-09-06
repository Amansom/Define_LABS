-----------------------------
Product     Price Tax
Soap        10     2
Petrol      65     1.2   
Total       75    3.2

Grand Total  : 78.2
-----------------------------

Generated a Data Structure to store the invoice products.
When the program is executed, an output similar to above is generated.

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


//code 2 
Here I have used the percentage value for the calculation of tax. User can give the input of item and price of the product.
    Conditions:
    Rice= NO_TAX
    Petrol=ONLY_VAT
    Other elements = SGST + CGST.

Values considered for TAXES: VAT=26%, SGST= 9%, CGST= 9%.
The grand total is calculated accordingly and is variable according to the product price.


Enter product name (or 'done' to finish): petrol
Enter product price: 65
Enter product name (or 'done' to finish): soap
Enter product price: 10
Enter product name (or 'done' to finish): done
Product	Price	Tax
petrol	65.0	16.900000000000002
soap	10.0	1.7999999999999998
Total	75.0	18.700000000000003
Grand Total:	93.7
-------------------------------------------------------
code:    

import java.util.*;


public class prac {
	
	    public static Map<String, Double> taxRates = new HashMap<>();
	    public static Map<String, List<String>> taxGroups = new HashMap<>();
	    public static List<Product> products = new ArrayList<>();

	    static {
	        // Task 1: Tax Rates
	        taxRates.put("VAT", 0.26);  // 26% VAT
	        taxRates.put("SGST", 0.09); // 9% SGST
	        taxRates.put("CGST", 0.09); // 9% CGST
	        // Add more taxes as needed

	        // Task 2: Tax Groups
	        taxGroups.put("NoTax", new ArrayList<>());
	        taxGroups.put("VATOnly", List.of("VAT"));
	        taxGroups.put("SGST_CGST", List.of("SGST", "CGST"));
	        // Add more tax groups as needed

	       
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
	            List<String> applicableTaxes = taxGroups.get(product.taxGroup);

	            if (applicableTaxes == null) {
	                System.out.println("Invalid tax group for product " + name + ": " + product.taxGroup);
	                continue; // Skip this product and move to the next one
	            }

	            double taxAmount = 0;

	            // Calculate tax amount
	            for (String tax : applicableTaxes) {
	                taxAmount += taxRates.get(tax) * price; // Apply the tax rate as a percentage of the product price
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
	        Scanner scanner = new Scanner(System.in);
	        List<Product> userProducts = new ArrayList<>();

	        // Accept user input for products
	        while (true) {
	            System.out.print("Enter product name (or 'done' to finish): ");
	            String productName = scanner.nextLine();
	            if (productName.equalsIgnoreCase("done")) {
	                break;
	            }

	            System.out.print("Enter product price: ");
	            double productPrice = scanner.nextDouble();
	            scanner.nextLine(); // Consume the newline character

	            // Specify the tax group directly for each product
	            String taxGroup = "SGST_CGST"; // Default to "SGST_CGST" for most products

	            if (productName.equalsIgnoreCase("Petrol")) {
	                taxGroup = "VATOnly"; // VAT only for "Petrol"
	            } else if (productName.equalsIgnoreCase("Rice")) {
	                taxGroup = "NoTax"; // No taxes for "Rice"
	            }

	            userProducts.add(new Product(productName, productPrice, taxGroup));
	        }

	        // Close the scanner
	        scanner.close();

	        // Generate and display the invoice for user input products
	        generateInvoice(userProducts);
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

    


