package com.example.newcalc;

import android.os.Parcel;
import android.os.Parcelable;

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
    double num;
    public Number(double num) {
        this.num = num;
        this.type = "number";
    }
    public double getNum() {
        return num;
    }
}

// класс для хранения операций
class Operation extends Operands{
    public Operation(String type){
        this.type = type;
    }
}

//основной класс для хранения текущего состояния строки операций и чисел калькулятора и для
// выполнения вычислений с ними
public class Calc {

    ArrayList<Operands> listOperations;// хранятся текущие числа и операции c чередованием
    // пример: "1", "+", "3", "*"

    public Calc() {
        listOperations = new ArrayList<>();
    }

    /**
     * Метод для добавления числа или операции в текущий список. Элементы чередуются. Первый может
     * быть только число
     * @param operand число или операция, допустимы {"+", "-", "*", "/", "%"}
     * @return true если удалось внести, false если допущена какая либо ошибка
     */
    public boolean addOperand(String operand){
        boolean isNum = false;
        boolean isOperation = false;
        String[] possibleOperations = {"+", "-", "*", "/", "%"};

        try {
            double num_tmp = Double.parseDouble(operand);// конвертируем входную строку в число
            isNum = true;
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
                isOperation = true;
                if (listOperations.get(listOperations.size()-1) instanceof Number)//если последний
                    //элемент в списке число
                    listOperations.add(new Operation(operand));//добавляем операцию
                else //последний элемент операция
                    listOperations.set(listOperations.size()-1, new Operation(operand));//заменяем операцию
            }
        }
     return isNum || isOperation;
    }


    @NonNull
    public String toString(){
        // todo сделать возврат текущего списка операция в формате строки
        return "";
    }

    /**
     *Метод выполняющий вычисление итого по текущему списку чисел и операций, если список пуст, то
     * возвращает 0
     * @return результат вычисления
     * @throws ArithmeticException при ошибке в вычислении, например деление на 0
     */
    public double makeCalc() throws ArithmeticException {
        if (listOperations.size() != 0){

            double result = ((Number) listOperations.get(0)).getNum();// в результат записываем
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
class MainCalcScreenString{
    StringBuilder str;// строка для хранения основного экрана калькулятора
    int maxLen;// максимальная длина строки

    public MainCalcScreenString(int maxLen) {
        str = new StringBuilder("");
        this.maxLen = maxLen;
    }

    /** метод для добавления элементов в строку экрана калькулятора
     *
     * @param element цифра или точка
     * @return строку калькулятора, если удалось добавить то новое значение, если нет, то старое
     */
    public String addElement(String element){

        if (str.length() < maxLen){
            if (".".equals(element) && str.indexOf(element)==-1){//если на входе "точка" и ее еще нет в строке
                if (str.length() == 0)
                    str.append("0.");
                else
                    str.append(".");
                return str.toString();
            }
            if (str.indexOf(element)!=-1 && ".".equals(element)) // если на входе "точка"
                // а она уже есть
                return str.toString();

            if (str.length() == 1 && "0".equals(element) && str.toString().equals("0"))// если
                // добавляем нули к нулям
                return str.toString();

            if (str.length() == 1 && !"0".equals(element) && str.toString().equals("0")){//если был
                // 0 в строке, а мы добавляем не 0
                str.replace(0,1, element);
                return str.toString();
            }
            str.append(element);
        }
        return str.toString();
    }


    public double toDouble(){
        return Double.parseDouble(str.toString());
    }

    @NonNull
    public String toString(){
        return str.toString();
    }

    public void clear(){
        str.setLength(0);
    }

    /**
     * Устанавливает значение строки в соответствии с числом на входе метода
     * @param num число
     */
    public void setDouble(double num){
        if (Double.toString(num).length() <= maxLen){
            str = new StringBuilder(Double.toString(num));
        }
        //todo что если количество символов больше чем maxLen
    }

}

//класс для работы с Memory
class Memory{
    private double num;
    private boolean isMemory;

    public Memory() {
        isMemory = false;
        num = 0;
    }

    public void memoryPlus(double num){
        isMemory = true;
        this.num += num;
    }

    public void memoryMinus(double num){
        isMemory = true;
        this.num -= num;
    }

    public boolean isMemory() {
        return isMemory;
    }

    public Double getNum() {
        if (isMemory)
            return num;
        return null;
    }

    public void clear(){
        isMemory = false;
        num = 0;
    }
}
