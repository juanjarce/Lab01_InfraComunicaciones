//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Lab01 {
    public static void main(String[] args) {

        //------------------------------------------------------------------------------------------------------------------------
        // 1. Conversiones entre entero decimal, binario y hexadecimal.

        // a. Convertir un entero decimal a binario, especificando el ancho en bits.
        String decToBin = convertDecToBin(5, 6);
        System.out.println(decToBin);

        // b. Convertir un número binario a entero decimal.
        String binToDec = convertBinToDec("0010000");
        System.out.println(binToDec);

        // c. Convertir un número de entero decimal a hexadecimal, especificando el ancho en dígitos
        //hexadecimales.
        String decToHex = convertDecToHex(1523, 8);
        System.out.println(decToHex);

        // d. Convertir un número hexadecimal a entero decimal.
        String hexToDec = convertHexToDec("5F3");
        System.out.println(hexToDec);

        // e. Convertir un número de binario a hexadecimal.
        String res5 = convertBinToHex("100001110");
        System.out.println(res5);

        // f. Convertir un número de hexadecimal a binario.
        String res6 = convertHexToBin("10E");
        System.out.println(res6);

        //------------------------------------------------------------------------------------------------------------------------
        // 2. Segmentación de una cadena de caracteres en partes de tamaño fijo.

        String cadenaGenerada = generarCadena(30);
        System.out.println(cadenaGenerada);

        String[] cadenasPorPartesIguales = dividirCadenaEnParteIguales(generarCadena(4), 2);
        System.out.println(Arrays.toString(cadenasPorPartesIguales));

        //------------------------------------------------------------------------------------------------------------------------
        // 3. Segmentación de una cadena de caracteres en partes de tamaño variable.

        String [] cadenasSeparadasEnDos = dividirCadenaEnDos(20, 5);
        System.out.println(Arrays.toString(cadenasSeparadasEnDos));

        String [] cadenasSeparadasEnTres = dividirCadenaEnTres(22, 6, 8);
        System.out.println(Arrays.toString(cadenasSeparadasEnTres));

        int[] volumenes = {4, 5, 2, 3, 8};
        String[] cadenasPorPartesIndicadas = dividirCadenaPorVolumenesIndicados(generarCadena(32), volumenes);
        System.out.println(Arrays.toString(cadenasPorPartesIndicadas));

        //------------------------------------------------------------------------------------------------------------------------
        // 4. Unión de partes de una cadena.
        String[] partesCadena = {"H", "O", "L", "A"};
        String cadenaCompleta = construirCadena(partesCadena);
        System.out.println(cadenaCompleta);
        

    }

    //----------------------- PUNTO 1 -------------------------------------------------------------------------------------------
    // 1. Conversiones entre entero decimal, binario y hexadecimal.

    // a. Convertir un entero decimal a binario, especificando el ancho en bits.
    public static String convertDecToBin(int dec, int anchoBits) {
        StringBuilder res = new StringBuilder();

        int maxNumber = (int) Math.pow(2, anchoBits-1);
        if(dec > maxNumber) {
            return "El decimal no se puede representar correctamente con el tamaño de bits indicado.";
        }

        while(dec > 0) {
            int n = dec % 2;
            dec = dec / 2;
            res.append(n);
        }

        // Añadir ceros al inicio si es necesario
        while(res.length() < anchoBits) {
            res.append("0");
        }

        return res.reverse().toString();
    }

    // b. Convertir un número binario a entero decimal.
    public static String convertBinToDec(String cadenaBinaria) {

        // Casos de error base.
        if (cadenaBinaria.length() == 0) return "La cadena parámetro está vacía";

        boolean esBi = cadenaBinaria.chars().allMatch(c -> c == '0' || c == '1');
        if(!esBi) return "La cadena no es binaria!";


        int decimal = 0;
        int length = cadenaBinaria.length() - 1;

        for (int i = 0; i < length + 1; i++) {
            char digit = cadenaBinaria.charAt(length - i);
            decimal += Integer.parseInt(""+digit) * Math.pow(2, i); // Número * 2^n
        }

        return String.valueOf(decimal);
    }

    // c. Convertir un número de entero decimal a hexadecimal, especificando el ancho en dígitos
    //hexadecimales.
    public static String convertDecToHex(int dec, int anchoBits) {
        StringBuilder res = new StringBuilder();
        char[] hexChars = "0123456789ABCDEF".toCharArray();

        while (dec > 0) {
            int n = dec % 16;
            res.append(hexChars[n]);
            dec /= 16;
        }

        // Añadir ceros al inicio si es necesario. ¡Qué buen while dios santo!
        while(res.length() < anchoBits) {
            res.append("0");
        }

        return res.reverse().toString();
    }

    // d. Convertir un número de hexadecimal a entero decimal. También se podría hacer con hexa.toCharArray.
    public static String convertHexToDec(String hexa) {
        int decimal = 0;
        int length = hexa.length();

        for (int i = 0; i < length; i++) {
            String n = String.valueOf(hexa.charAt(length - 1 - i));
            // Verificamos el digito hexa que se va a añadir
            // --------------------------------------------
            if(n.equals("A")){
                decimal += 10*Math.pow(16, i);
            } else if(n.equals("B")){
                decimal += 11*Math.pow(16, i);
            } else if (n.equals("C")) {
                decimal += 12*Math.pow(16, i);
            } else if (n.equals("D")) {
                decimal += 13*Math.pow(16, i);
            } else if (n.equals("E")) {
                decimal += 14*Math.pow(16, i);
            } else if (n.equals("F")) {
                decimal += 15*Math.pow(16, i);
            } else{
                decimal += Integer.parseInt(n)*Math.pow(16, i);
            }
            // --------------------------------------------
        }

        return String.valueOf(decimal);
    }

    // e. Convertir un número de binario a hexadecimal. Se agrupan de a 4 digitos en la variable nibble, luego a res se inserta el valor hex del nibble con hexValueOf.
    public static String convertBinToHex(String binary) {
        StringBuilder res = new StringBuilder();
        StringBuilder nibble = new StringBuilder();
        for(int i=binary.length()-1; i>=0; i--){
            nibble.insert(0, binary.charAt(i));
            if(nibble.length()==4){
                res.insert(0, hexValueOf(nibble));
                nibble.delete(0, 4);
            } else if (i == 0) {
                while (nibble.length() < 4){
                    nibble.insert(0, "0");
                }
                res.insert(0, hexValueOf(nibble));
                nibble.delete(0, 4);
            }
        }
        return res.toString();
    }

    // Metodo para convertir un nibble (cadena de 4 bits) a su respectivo digito hexadecimal
    private static char hexValueOf(StringBuilder nibble) {
        switch (nibble.toString()) {
            case "0000":
                return '0';
            case "0001":
                return '1';
            case "0010":
                return '2';
            case "0011":
                return '3';
            case "0100":
                return '4';
            case "0101":
                return '5';
            case "0110":
                return '6';
            case "0111":
                return '7';
            case "1000":
                return '8';
            case "1001":
                return '9';
            case "1010":
                return 'A';
            case "1011":
                return 'B';
            case "1100":
                return 'C';
            case "1101":
                return 'D';
            case "1110":
                return 'E';
            case "1111":
                return 'F';
            default:
                throw new IllegalArgumentException("Entrada de nibble no válida: " + nibble);
        }
    }

    // f. Convertir un número hexadecimal a binario.
    public static String convertHexToBin(String hex) {
        StringBuilder res = new StringBuilder();
        for(int i=0; i<hex.length(); i++){
            res.append(BinValueOf(hex.charAt(i)));
        }
        return res.toString();
    }

    // Metodo para convertir un digito hexadecimal a su respectivo nibble (cadena de 4 bits)
    private static String BinValueOf(char c) {
        switch (c) {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'A':
                return "1010";
            case 'B':
                return "1011";
            case 'C':
                return "1100";
            case 'D':
                return "1101";
            case 'E':
                return "1110";
            case 'F':
                return "1111";
            default:
                throw new IllegalArgumentException("Dígito hexadecimal no válido: " + c);
        }
    }
    
    
    //------------------------ PUNTO 2 ------------------------------------------------------------------------------------------

    /**
     * Crear un String de un tamaño parametrizado. 
     * El contenido final de la cadena será el abecedario en letras minúsculas tantas veces como sea necesario hasta completar el tamaño indicado de la cadena.
     * @param cantidadCaracteres
     * @return cadenaFinal.toString()
     */
    public static String generarCadena(Integer cantidadCaracteres) {

        StringBuilder cadenaFinal =  new StringBuilder();
        
        char letra = 'a';

        for (int i = 0; i < cantidadCaracteres; i++) {
            cadenaFinal.append(letra);
            letra++;
    
            // Reinicia la letra a 'a' cuando llega a '{'
            if (letra == '{') {
                letra = 'a';
            }
        }

        return cadenaFinal.toString();
    }

    /**
     * Divide una cadena dada en partes iguales, tiene en cuenta el residuo.
     * @param cadena
     * @param longitudEncabezado
     * @return
     */
    public static String[] dividirCadenaEnParteIguales(String cadena, int longitudParticiones) {

        // Calculo el número de divisiones para saber de que tamaño va a ser el arreglo de cadenas. Si hay residuo, aumento en 1 el # de divisiones.
        int numeroDivisiones = cadena.length() / longitudParticiones;
        int residuo = cadena.length() % longitudParticiones;
        if (residuo != 0) numeroDivisiones++;
        
        // Inicializo el arreglo de cadenas con el número de divisiones que sé que tendré que usar.
        String[] cadenas = new String[numeroDivisiones];
        // Convierto la cadena dada como parámetro en un arreglo de carácteres para ir iterando sobre sus índices que serán las letras que se necesiten en cada cadena.
        char[] cadenaCharArr = cadena.toCharArray();
    
        // Declaro dos índices, uno para iterar sobre el arreglo de carácteres, y otro para iterar sobre el arreglo de cadenas. El índice de arreglo de cadenas solo aumenta si la cadena que se está armando actualmente ya llegó a su límite.
        int indiceStringArr = 0;
        int indiceCharArr = 0;
    
        // Itero mientras no haya recorrido todos los índices del arreglo de cadenas.
        while (indiceStringArr < numeroDivisiones) {
    
            StringBuilder cadenaActual = new StringBuilder();
    
            // Calculo la longitud de la partición actual, "número de divisiones-1" indica que ya estamos en el último indice del arreglo de cadenas, si es así, la longitud de la partición deberá aumentar tomando el cuenta el valor del residuo (mientras el residuo sea distinto de cero).
            int longitudActual = (indiceStringArr == numeroDivisiones - 1 && residuo != 0) ? residuo : longitudParticiones;
    
            // Construyo la cadena del índice en el que nos encontremos. Solo itera hasta que se cumpla la longitud actual, indiceCharArr crece dentro de todo el while.
            for (int i = 0; i < longitudActual; i++) {
                if (indiceCharArr < cadenaCharArr.length) {
                    cadenaActual.append(cadenaCharArr[indiceCharArr]);
                    indiceCharArr++;
                }
            }
    
            // Añado la cadena actual a el índice que le corresponde al arreglo de cadenas.
            cadenas[indiceStringArr] = cadenaActual.toString();
            indiceStringArr++; // Subo al otro índice.
        }
    
        return cadenas;
    }

    //------------------------ PUNTO 3 -----------------------------------------------------------------------------------

    public static String[] dividirCadenaEnDos(int longitudCadena, int longitudEncabezado) {

        String cadena = generarCadena(longitudCadena);
        String[] cadenas = new String[2];

        cadenas[0] = cadena.substring(0, longitudEncabezado);
        cadenas[1] = cadena.substring(longitudEncabezado + 1, longitudCadena);

        return cadenas;
    }

    public static String[] dividirCadenaEnTres(int longitudCadena, int longitudPrimerEncabezado, int longitudSegundoEncabezado) {

        String cadena = generarCadena(longitudCadena);
        String[] cadenas = new String[3];

        cadenas[0] = cadena.substring(0, longitudPrimerEncabezado);
        cadenas[1] = cadena.substring(longitudPrimerEncabezado + 1, longitudPrimerEncabezado + longitudSegundoEncabezado);
        cadenas[2] = cadena.substring(longitudPrimerEncabezado + longitudSegundoEncabezado + 1, longitudCadena);

        return cadenas;

    }
    
    public static String[] dividirCadenaPorVolumenesIndicados(String cadena, int[] volumenes) {

        int[] volumenesFinales;

        int sumaVolumenes = Arrays.stream(volumenes).sum();
        if (sumaVolumenes > cadena.length()) return null;

        int numeroIndicesArreglo = volumenes.length;
        if (sumaVolumenes < cadena.length())  {
            numeroIndicesArreglo++; 
            int nuevoElemento = cadena.length() - sumaVolumenes;

            // Coloco el nuevo arreglo con un espacio extra.
            volumenesFinales = Arrays.copyOf(volumenes, volumenes.length + 1);
    
            // Agrego el nuevo elemento a volumenes finales.
            volumenesFinales[volumenes.length] = nuevoElemento;
        } else {
            volumenesFinales = volumenes; // Redundate, lo sé. No se me ocurrió algo mejor.
        }
        String[] cadenas = new String[numeroIndicesArreglo];

        char[] arregloDeLaCadena = cadena.toCharArray();

        int indiceArregloCadenas = 0;
        int indiceArregloVolumenes = 0;
        int indiceRecorreCadena = 0;
        while (indiceArregloCadenas < numeroIndicesArreglo) {

            StringBuilder cadenaActual = new StringBuilder();

            int longitudIndiceActual = volumenesFinales[indiceArregloVolumenes];
            for (int i = 0; i < longitudIndiceActual; i++) {
                if (indiceRecorreCadena < arregloDeLaCadena.length) {
                    cadenaActual.append(arregloDeLaCadena[indiceRecorreCadena]);
                    indiceRecorreCadena++;
                }
            }

            cadenas[indiceArregloCadenas] = cadenaActual.toString();
            indiceArregloCadenas++;
            indiceArregloVolumenes++;

        }
        return cadenas;
    }

    //------------------------ PUNTO 4 -----------------------------------------------------------------------------------

    public static String construirCadena (String[] partesCadena) {

        String cadenaCompleta = Arrays.stream(partesCadena).collect(Collectors.joining()); // Gracias a dios por los flujos! (Sobre todo los de las... )
        return cadenaCompleta;
    }


}