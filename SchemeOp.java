import java.util.Random;

// Daniel
public class SchemeOp {

    public int ID;
    public int cont = 0;
    public int min;
    public int max;
    public String op;
    public double res;
    static Random rand = new Random();

    public SchemeOp(int min, int max) {
        this.ID = 0;
        this.op = null;
        this.res = 0;
        this.min = min;
        this.max = max;
    }

    // Compute/Resolve Operation
    public static SchemeOp compute(SchemeOp op) {
        double result;
        char[] operations = stringToChar(op.getOperation());

        double n1 = Integer.parseInt(String.valueOf(operations[3]));
        double n2 = Integer.parseInt(String.valueOf(operations[5]));

        char operator = operations[1];

        // se valida si es entre 0
        if (n2 == 0 && operator == '/') {
            op.setResult(Double.NaN);
            return op;
        }

        // Resuelve la operacion
        switch (operator) {
            case '+':
                result = n1 + n2;
                op.setResult(result);
                break;
            case '-':
                result = n1 - n2;
                op.setResult(result);
                break;
            case '*':
                result = n1 * n2;
                op.setResult(result);
                break;
            case '/':
                result = n1 / n2;
                op.setResult(result);
                break;
            default:
                break;
        }
        return op;
    }

    public int[] createRandomNumbers() {
        int[] arr = new int[2];
        arr[0] = rand.nextInt(this.max - this.min) + this.min;
        arr[1] = rand.nextInt(this.max - this.min) + this.min;

        return arr;
    }

    // Genera la operacion (+,-,*,/)
    public String createRandomOperation() {
        int ran = rand.nextInt(4);
        String op = null;
        switch (ran) {
            case 0:
                op = "+";
                break;
            case 1:
                op = "-";
                break;
            case 2:
                op = "*";
                break;
            case 3:
                op = "/";
                break;
        }
        return op;
    }

    // Asigna el formato como en Scheme (+ a b)
    public String toSchemeFormat(int[] arreglo, String operacionRandom) {
        String format = "(" + operacionRandom + " " + arreglo[0] + " " + arreglo[1] + ")";
        return format;
    }

    public static char[] stringToChar(String str) {

        char[] ch = new char[str.length()];

        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }

        return ch;
    }

    /* GETTERS & SETTERS */

    private int getRandomNumber(int rangoMinimo, int rangoMayor) {
        return rand.nextInt(rangoMayor - rangoMinimo) + rangoMinimo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public double getResult() {
        return res;
    }

    public void setResult(double result) {
        this.res = result;
    }

    public int getCounter() {
        return cont;
    }

    public void setCounter(int counter) {
        this.cont = counter;
    }

    public String getOperation() {
        return op;
    }

    public void setOperation(String operation) {
        this.op = operation;
    }
}
