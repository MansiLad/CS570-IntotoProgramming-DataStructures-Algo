//importing required java libraries
import java.util.Arrays;
import java.lang.Math;
import java.lang.*;

public class BinaryNumber {

    //declaring required variables
    private int[] data;
    private boolean overflow;

    //creating a Binary number with zeros
    public int[] BinaryNumber(int length){

        if (length <= 0){
            throw new IndexOutOfBoundsException();
        }

        data = new int[length];
        Arrays.fill(data, 0, length-1, 0);
        return data;
    }

    //creating a binary number from the given string
    public int[] BinaryNumber(String str){

        if(str.length() == 0){
            throw new IllegalArgumentException();
        }

        int cnt=0;
        char[] ch = str.toCharArray();
        for(char c: ch){
            data[cnt] = Character.getNumericValue(c);
            cnt++;
        }
        return data;
    }

    //returns length of data (length of the binary number
    public int getLength(){
        return data.length;
    }

    //returns the binary digit at a given position
    public int getDigit(int index){

        if(index > getLength()){
            throw new IndexOutOfBoundsException();
        }

        return data[index];
    }

    //Right shifts the binary number by the given number of shifts
    public void shiftR(int amount){

        if(amount > getLength()){
            throw new IndexOutOfBoundsException();
        }

        int newLength = getLength()+amount;
        int[] ans = new int[newLength];
        for(int i=0; i<amount; i++){
            ans[i] = 0;
        }
        for(int j=amount, i=0; j<data.length+amount; j++, i++){
            ans[j] = data[i];
        }
        data = BinaryNumber(newLength);
        for(int i=0;i<getLength();i++){
            data[i] = ans[i];
        }
    }

    //Addition of 2 binary numbers
    public void add(BinaryNumber aBinaryNumber){    //instance of binary number
        if(getLength() != aBinaryNumber.getLength()){
            throw new IllegalArgumentException();
        }

        int carry = 0;
        int i=0;
        int[] addition = new int[getLength()];
        int len = getLength()+1;
        //System.out.println(sum.length);

        for(i=0; i<getLength(); i++){
            addition[i] = (data[i] + aBinaryNumber.data[i] + carry) % 2;
            carry = (data[i] + aBinaryNumber.data[i] + carry) / 2;
            System.out.println(addition[i]);
            //System.out.println(carry);
        }

        if (carry == 1){
            //System.out.println(carry);
            data = BinaryNumber(len);
            data[getLength()-1] = carry;
        }
        //System.out.println(sum[3]);
        //System.out.println(sum.length);
        //System.out.println(addition[4]);

        //data = BinaryNumber(len);
        //System.out.println(getLength());


        for(int j=0;j< addition.length;j++){
            data[j] = addition[j];
            //System.out.print(addition[j]);
        }

        for (int ele: data) {
            System.out.print(ele);
        }
        System.out.println(" ");
    }

    //convert the array into string
    public String toString(){
        return Arrays.toString(data);
    }

    //returns the decimal equivalent of the given binary number
    public int toDecimal(){

        int sum=0;
        for (int i=0; i<data.length; i++){
            sum += data[i] * Math.pow(2,i);
        }
        return sum;
    }

    //clears overflow
    public void clearOverflow(){
        overflow = false;
    }

}
