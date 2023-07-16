import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SubstringDivisibilityPandigital {
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string of digits: ");
        String s = scanner.nextLine();
        List<String> permutations = new ArrayList<>();
        generatePermutationsHelper(s.toCharArray(), 0, permutations);
        //Print all permutations
        //System.out.println(permutations);

        List<Integer> validPermutations = new ArrayList<>();
        for (String p : permutations) {
            if (p.length() == s.length() && isSubstringDivisible(p)) {
                validPermutations.add(Integer.parseInt(p));
            }
        }
        Integer[] validPermutationsArray = validPermutations.toArray(new Integer[0]);
        Arrays.sort(validPermutationsArray);
        System.out.println("Valid permutations: ");
        System.out.println(Arrays.toString(validPermutationsArray));
        System.out.println(sum(validPermutationsArray));
    }



        private static void generatePermutationsHelper(char[] chars, int index, List<String> permutations) {
        if (index == chars.length - 1) {
            permutations.add(String.valueOf(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            generatePermutationsHelper(chars, index + 1, permutations);
            swap(chars, index, i); // backtrack
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private static boolean isSubstringDivisible(String s) {
        
        int INDEX_TO_CHECK_TILL = Math.min(s.length() - 4, PRIMES.length);
        System.out.println("INDEX_TO_CHECK_TILL: " + INDEX_TO_CHECK_TILL);  
        System.out.println("permuted string: " + s);  



        for (int i = 0; i <= INDEX_TO_CHECK_TILL; i++) {
            int substring = Integer.parseInt(s.substring(i + 1, i + 4));  // 0 :1,2,3 
            System.out.println("substring: " + substring);
            System.out.println("substring % PRIMES[i]: " + substring % PRIMES[i]);
            if (substring % PRIMES[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static long sum(Integer[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
}
