package day04;

import java.util.function.IntPredicate;

import static java.util.stream.IntStream.rangeClosed;

public class SecureContainer {

    public long part1() {
        return countValidPasswords(this::isValidPasswordPart1);
    }

    public long part2() {
        return countValidPasswords(this::isValidPasswordPart2);
    }

    private long countValidPasswords(IntPredicate passwordCheck) {
        return rangeClosed(136818, 685979).filter(passwordCheck).count();
    }

    private boolean isValidPasswordPart1(int number) {
        var identicalDigitsFound = false;
        var increasingDigits = true;
        var remaining = number / 10;
        var previous = number % 10;
        while (remaining != 0 && increasingDigits) {
            var current = remaining % 10;
            if (current == previous) {
                identicalDigitsFound = true;
            }
            increasingDigits = current <= previous;
            remaining /= 10;
            previous = current;
        }
        return identicalDigitsFound && increasingDigits;
    }

    private boolean isValidPasswordPart2(int number) {
        var twoIdenticalDigitsFound = false;
        var increasingDigits = true;
        var remaining = number / 10;
        var previous = number % 10;
        var identicalDigitCount = 1;
        while (remaining != 0 && increasingDigits) {
            var current = remaining % 10;
            if (current == previous) {
                identicalDigitCount++;
            } else {
                if (identicalDigitCount == 2) {
                    twoIdenticalDigitsFound = true;
                }
                identicalDigitCount = 1;
            }
            increasingDigits = current <= previous;
            remaining /= 10;
            previous = current;
        }
        twoIdenticalDigitsFound = twoIdenticalDigitsFound || identicalDigitCount == 2;
        return twoIdenticalDigitsFound && increasingDigits;
    }
}
