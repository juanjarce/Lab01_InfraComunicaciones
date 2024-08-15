//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Lab01 {
    public static void main(String[] args) {

        //------------------------------------------------------------------------------------------------------------------------
        // 1. Conversiones entre entero decimal, binario y hexadecimal.

        // a. Convertir un entero decimal a binario, especificando el ancho en bits.
        String res1 = convertDecToBin(5, 6);
        System.out.println(res1);

        // b. Convertir un número binario a entero decimal.
        int res2 = convertBinToDec("00101");
        System.out.println(res2);

        // c. Convertir un número de entero decimal a hexadecimal, especificando el ancho en dígitos
        //hexadecimales.
        String res3 = convertDecToHex(1523, 8);
        System.out.println(res3);

        // d. Convertir un número hexadecimal a entero decimal.
        int res4 = convertHexToDec("5F3");
        System.out.println(res4);

        // e. Convertir un número de binario a hexadecimal.
        String res5 = convertBinToHex("100001110");
        System.out.println(res5);

        // f. Convertir un número de hexadecimal a binario.
        String res6 = convertHexToBin("10E");
        System.out.println(res6);

    }

    //------------------------------------------------------------------------------------------------------------------------
    // 1. Conversiones entre entero decimal, binario y hexadecimal.

    // a. Convertir un entero decimal a binario, especificando el ancho en bits.
    public static String convertDecToBin(int dec, int anchoBits) {
        StringBuilder res = new StringBuilder();

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
    public static int convertBinToDec(String binary) {
        int decimal = 0;
        int length = binary.length();

        for (int i = 0; i < length; i++) {
            char digit = binary.charAt(length - 1 - i);
            decimal += Integer.parseInt(""+digit)*Math.pow(2, i);
        }

        return decimal;
    }

    // c. Convertir un número de entero decimal a hexadecimal, especificando el ancho en dígitos
    //hexadecimales.
    public static String convertDecToHex(int dec, int anchoBits) {
        StringBuilder res = new StringBuilder();

        while(dec > 0) {
            int n = dec % 16;
            dec = dec / 16;
            if(n == 10){
                res.append("A");
            } else if(n == 11){
                res.append("B");
            } else if (n == 12) {
                res.append("C");
            } else if (n == 13) {
                res.append("D");
            } else if (n == 14) {
                res.append("E");
            } else if (n == 15) {
                res.append("F");
            } else{
                res.append(n);
            }
        }

        // Añadir ceros al inicio si es necesario
        while(res.length() < anchoBits) {
            res.append("0");
        }

        return res.reverse().toString();
    }

    // d. Convertir un número de hexadecimal a entero decimal.
    public static int convertHexToDec(String hexa) {
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

        return decimal;
    }

    // e. Convertir un número de binario a hexadecimal.
    public static String convertBinToHex(String bin) {
        StringBuilder res = new StringBuilder();
        StringBuilder nibble = new StringBuilder();
        for(int i=bin.length()-1; i>=0; i--){
            nibble.insert(0, bin.charAt(i));
            if(nibble.length()==4){
                res.insert(0, hexValueOf(nibble));
                nibble.delete(0, 4);
            } else if (i == 0) {
                while (nibble.length()<4){
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
    //------------------------------------------------------------------------------------------------------------------
}