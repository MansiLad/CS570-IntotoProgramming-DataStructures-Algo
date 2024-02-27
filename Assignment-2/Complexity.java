public class Complexity {

    //Method1: O(n^2) Complexity
    public static void method1(int n){
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Operation " + cnt);
                cnt++;
            }
        }
    }

    //Method2: O(n^3) Complexity
    public static void method2(int n){
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.println("Operation " + cnt);
                    cnt++;
                }
            }
        }
    }

    //Method3: O(logn) Complexity
    public static void method3(int n){
        int cnt = 0;
        for (int i = 1; i < n; i = i * 3) {
            System.out.println("Operation "+ cnt);
            cnt++;
        }
    }

    //Method4: O(nlogn) Complexity
    public static void method4(int n){
        int cnt = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j < n; j = j * 2) {
                System.out.println("Operation "+ cnt);
                cnt++;
            }
        }
    }

    //Method5: O(log(logn)) Complexity
    public static void method5(int n){
        int cnt = 0;
        for(double i=2; i<n; i = i*i){
            System.out.println("Operation "+ cnt);
            cnt++;
        }
    }

    //Method6: O(2^n) (eg: fibonacci series)
    static int cnt = 0;
    public static int method6(int n){
        if(n<=1)    return  n;
        System.out.println("Operation "+ cnt);
        cnt ++;
        return method6(n - 2) + method6(n - 1);
    }

/*
    public static void main(String[] args){
        method1(4);
        method2(3);
        method3(25);
        method4(5);
        method5(128);
        method6(10);
    }
*/
}
