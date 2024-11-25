import java.util.Scanner;


public class CalculadoraDeInventario {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Defino los arrays para los productos, cantidades y precios.
        String[] productos    = new String[100];
        int[] cantidades      = new int[100];
        double[] precios      = new double[100];
        int cantidadProductos = 0; // Cuenta los productos ingresados
        int opcion;

        do {
            // Menú de opciones
            System.out.println("\n ----------- CALCULADORA DE INVENTARIO----------- ");
            System.out.println("1. Ingresar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Calcular valor total del inventario");
            System.out.println("6. Mostrar producto más caro y más barato");
            System.out.println("7. Salir");
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    cantidadProductos = ingresarProducto(scanner, cantidadProductos, productos, cantidades, precios);
                    break;
                case 2:
                    mostrarProductos(cantidadProductos, productos, cantidades, precios);
                    break;
                case 3:
                    buscarProducto(scanner, cantidadProductos, productos, cantidades, precios);
                    break;
                case 4:
                    actualizarProducto(scanner, cantidadProductos, productos, cantidades, precios);
                    break;
                case 5:
                    calcularValorTotal(cantidadProductos, cantidades, precios);
                    break;
                case 6:
                    mostrarProductoMasCaroYBarato(cantidadProductos, productos, precios);
                    break;
                case 7:
                    System.out.println("\n¡Gracias por usar la calculadora de inventario!");
                    break;
                default:
                    System.out.println("\nOpción no válida, intente nuevamente.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    public static int ingresarProducto(Scanner scanner, int cantidadProductos, String[] productos, int[] cantidades, double[] precios) { // Agregar o ingresar un producto

        if (cantidadProductos < 100) {
            System.out.print("\nIngrese el nombre del producto: ");
            productos[cantidadProductos] = scanner.nextLine();

            System.out.print("Ingrese la cantidad: ");
            cantidades[cantidadProductos] = scanner.nextInt();

            System.out.print("Ingrese el precio: ");
            precios[cantidadProductos] = scanner.nextDouble();

            scanner.nextLine();

            cantidadProductos++; // Actualiza la cantidad de productos ingresados
            System.out.println("\nProducto ingresado correctamente.");
        } else {
            System.out.println("\nNo se pueden agregar más productos. El inventario está lleno.");
        }

        return cantidadProductos; // Se retorna la cantidad de productos para actualizarla en main
    }

    public static void mostrarProductos(int cantidadProductos, String[] productos, int[] cantidades, double[] precios) { // Muestra todos los productos
        if (cantidadProductos == 0) {
            System.out.println("\nNo hay productos en el inventario.");
        } else {
            System.out.println("\nInventario:");

            for (int i = 0; i < cantidadProductos; i++) {
                System.out.println("Producto: " + productos[i] + " | Cantidad: " + cantidades[i] + " | Precio: $" + precios[i]);
            }
        }
    }

    public static void buscarProducto(Scanner scanner, int cantidadProductos, String[] productos, int[] cantidades, double[] precios) { // Busca el producto por su nombre
        System.out.print("\nIngrese el nombre del producto a buscar: ");
        String nombreBuscado = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < cantidadProductos; i++) {
            if (productos[i].equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Producto encontrado: " + productos[i] + " | Cantidad: " + cantidades[i] + " | Precio: $" + precios[i]);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nProducto no encontrado.");
        }
    }

    public static void actualizarProducto(Scanner scanner, int cantidadProductos, String[] productos, int[] cantidades, double[] precios) { // Método para actualizar la cantidad y el precio de un producto
        System.out.print("\nIngrese el nombre del producto a actualizar: ");
        String nombreProducto = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < cantidadProductos; i++) {

            if (productos[i].equalsIgnoreCase(nombreProducto)) {
                System.out.print("Ingrese la nueva cantidad: ");
                cantidades[i] = scanner.nextInt();

                System.out.print("Ingrese el nuevo precio: ");
                precios[i] = scanner.nextDouble();
                scanner.nextLine(); 

                System.out.println("Producto actualizado correctamente.");
                encontrado = true;
                break;
            }

        }

        if (!encontrado) {
            System.out.println("\nProducto no encontrado.");
        }
        
    }

    public static void calcularValorTotal(int cantidadProductos, int[] cantidades, double[] precios) { // Calcula el valor total del inventario

        double total = 0;

        for (int i = 0; i < cantidadProductos; i++) {

            total += cantidades[i] * precios[i];

        }

        System.out.println("Valor total del inventario: $" + total);

    }

    public static void mostrarProductoMasCaroYBarato(int cantidadProductos, String[] productos, double[] precios) { // Muestra el producto más caro y más barato

        if (cantidadProductos == 0) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        int indexMasCaro = 0;
        int indexMasBarato = 0;

        for (int i = 1; i < cantidadProductos; i++) {

            if (precios[i] > precios[indexMasCaro]) {
                indexMasCaro = i;
            } else if (precios[i] < precios[indexMasBarato]) {
                indexMasBarato = i;
            }

        }

        System.out.println("Producto más caro: " + productos[indexMasCaro] + " | Precio: $" + precios[indexMasCaro]);
        System.out.println("Producto más barato: " + productos[indexMasBarato] + " | Precio: $" + precios[indexMasBarato]);

    }
}