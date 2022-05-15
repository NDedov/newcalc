package com.example.newcalc;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;

//базовый класс для хранения чисел и операций
class Operands{
    String type;
    public String getType() {
        return type;
    }

}

// класс для хранения чисел
class Number extends Operands{
    float num;

    public Number(float num) {
        this.num = num;
        this.type = "number";
    }
    public float getNum() {
        return num;
    }

    @NonNull
    @Override
    public String toString() {
        if (num == (int) num)
            return Integer.toString((int) num);
        return Float.toString(num);
    }
}

// класс для хранения операций
class Operation extends Operands{

    public Operation(String type){
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return type;
    }
}

//основной класс для хранения текущего состояния строки операций и чисел калькулятора и для
// выполнения вычислений с ними
public class Calc{

    ArrayList<Operands> listOperations;// хранятся текущие числа и операции c чередованием
    // пример: "1", "+", "3", "*"

    public Calc() {
        listOperations = new ArrayList<>();
    }

    /**
     * Метод для добавления числа или операции в текущий список. Элементы чередуются. Первый может
     * быть только число. Допустимые операции, допустимы {"+", "-", "*", "/", "%"}
     */
    public void addOperand(String operand){
        String[] possibleOperations = {"+", "-", "*", "/", "%"};
        try {
            float num_tmp = Float.parseFloat(operand);// конвертируем входную строку в число
            if (listOperations.isEmpty())//если список пустой
                listOperations.add(new Number(num_tmp));// добавляем новый объект типа число
            else { //если список не пустой
                if (listOperations.get(listOperations.size()-1) instanceof Number)//если текущий
                    // элемент в списке операндов число
                    listOperations.set(listOperations.size()-1, new Number(num_tmp)); // заменяем его
                    else// если не число
                    listOperations.add(new Number(num_tmp));// добавляем новый объект число
            }
        }
        catch (NumberFormatException e){ //на входе не число
            if (Arrays.asList(possibleOperations).contains(operand) && !listOperations.isEmpty()){
                // если на входе допустимая операция и список не пустой
                if (listOperations.get(listOperations.size()-1) instanceof Number)//если последний
                    //элемент в списке число
                    listOperations.add(new Operation(operand));//добавляем операцию
                else //последний элемент операция
                    listOperations.set(listOperations.size()-1, new Operation(operand));//заменяем операцию
            }
        }
    }

    public void clear(){
        listOperations.clear();
    }

    @NonNull
    public String toString(){
        StringBuilder tmp = new StringBuilder();
        if (listOperations.size() > 0){
            for (Operands item: listOperations)
                tmp.append(item.toString());
            return tmp.toString();
        }
        return "";
    }

    /**
     *Метод выполняющий вычисление итого по текущему списку чисел и операций, если список пуст, то
     * возвращает 0
     * @return результат вычисления
     * @throws ArithmeticException при ошибке в вычислении, например деление на 0
     */
    public float makeCalc() throws ArithmeticException {
        if (listOperations.size() != 0){
            float result = ((Number) listOperations.get(0)).getNum();// в результат записываем
            // первое число
            for (int i = 1; i < listOperations.size(); i++) {// проходим по списку, выполняем операции
                if (listOperations.get(i) instanceof Operation && i != listOperations.size() - 1){
                    if (listOperations.get(i).getType().equals("+"))
                        result += ((Number) listOperations.get(i + 1)).getNum();
                    if (listOperations.get(i).getType().equals("-"))
                        result -= ((Number) listOperations.get(i + 1)).getNum();
                    if (listOperations.get(i).getType().equals("*"))
                        result *= ((Number) listOperations.get(i + 1)).getNum();
                    if (listOperations.get(i).getType().equals("/")) {
                        if (((Number) listOperations.get(i + 1)).getNum() == 0)
                            throw new ArithmeticException("Division by Zero");
                        result /= ((Number) listOperations.get(i + 1)).getNum();
                    }
                    if (listOperations.get(i).getType().equals("%")) {
                        result *= ((Number) listOperations.get(i + 1)).getNum();
                        result /= 100;
                    }
                }
            }
            return result;
        }
    return 0;
    }
}

// класс для хранения и обработки значений выводимых на основной экран калькулятора
class MainCalcScreenString {

    StringBuilder str;// строка для хранения основного экрана калькулятора
    int maxLen;// максимальная длина строки
    boolean isError;

    public boolean isError() {
        return isError;
    }

    public MainCalcScreenString(int maxLen) {
        str = new StringBuilder();
        this.maxLen = maxLen;
        isError = false;
    }

    /**
     * удаляет последний сивол в строке, если символ один, то заменяет его на 0
     */
    public void delLast(){
        if (isError){
            str = new StringBuilder();
            isError = false;
            return;
        }
        if (!str.toString().equals("")) {
            if (str.length() > 1)
                str.deleteCharAt(str.length() - 1);
            else
                str = new StringBuilder();
        }
    }

    /** метод для добавления элементов в строку экрана калькулятора
     *
     * @param element цифра или точка
     *
     */
    public void addElement(String element){

        if (isError){
            str = new StringBuilder();
            isError = false;
        }

        if (str.length() < maxLen){
            if (".".equals(element) && str.indexOf(element)==-1){//если на входе "точка" и ее
                // еще нет в строке
                if (str.length() == 0)
                    str.append("0.");
                else
                    str.append(".");
                return;
            }
            if (str.indexOf(element)!=-1 && ".".equals(element)) // если на входе "точка"
                // а она уже есть
                return;

            if (str.length() == 1 && "0".equals(element) && str.toString().equals("0"))// если
                // добавляем нули к нулям
                return;

            if (str.length() == 1 && !"0".equals(element) && str.toString().equals("0")){//если был
                // 0 в строке, а мы добавляем не 0
                str.replace(0,1, element);
                return;
            }
            str.append(element);
        }
    }


    public float toFloat(){
        if (str.length() == 0)
            return 0;
        return Float.parseFloat(str.toString());
    }

    @NonNull
    public String toString(){
        if (str.length() == 0)
            return "";
        return str.toString();
    }

    public void clear(){
        str = new StringBuilder();
    }

    /**
     * Устанавливает значение строки в соответствии с числом на входе метода
     * @param num число
     */
    public void setFloat(float num){
        //DecimalFormat df = new DecimalFormat("#.######");
        if ((int)num == num){
            str = new StringBuilder(Integer.toString((int) num));
        }
        else {
            str = new StringBuilder(Float.toString(num));
        }
    }

    public void setError(){
        str = new StringBuilder("ERROR");
        isError = true;
    }
}

//класс для работы с Memory
class Memory {
    private float num;
    private boolean isMemory;

    public Memory() {
        isMemory = false;
        num = 0;
    }

    public void memoryPlus(float num){
        isMemory = true;
        this.num += num;
    }

    @NonNull
    @Override
    public String toString() {
        if (isMemory)
            return "M";
        else
            return "";
    }

    public void memoryMinus(float num){
        isMemory = true;
        this.num -= num;
    }

    public boolean isMemory() {
        return isMemory;
    }

    public Float getNum() {
        if (isMemory)
            return num;
        return null;
    }

    public void clear(){
        isMemory = false;
        num = 0;
    }
}
