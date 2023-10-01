package free01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HitAndBlow20200802 {

    /*
     * specification 仕様
     * 1.create non-overlapping random number.
     * 2.User input anumber.
     * 3.Judge hit and blow
     */
    public static void main(String[] args) {

	// 1.乱数を作成して
	int[] rand = makeRandNumber();
	System.out.println("4桁の乱数は" + rand[0] + rand[1] + rand[2] + rand[3]);

	// 正解するまで入力する
	while (true) {
	    // 2.入力して
	    int[] input = inputNumber();
	    // 3 判定する。正解したら終了。
	    if (judgement(input, rand)) {
		break;
	    }
	}
    }


    /*
     * random number creation method 4桁の乱数生成メソッド （乱数は先頭が0ではない、重複しない）
     * @return int[] rand 生成した乱数を配列に格納したもの
     */
    // TODO:乱数の作成を他のやり方で
    public static int[] makeRandNumber() {

	int[] rand = new int[4];

	while (true) {

	    for (int i = 0; i < 4; i++) {
		rand[i] = (int) (Math.random() * 10);
		System.out.println(rand[i]);
	    }

	    if (rand[0] != 0) {

		if (duplicationCheck(rand)) {
		    break;
		}
	    }
	}
	//System.out.println(Arrays.toString(rand));
	return rand;
    }

    /*
     * Check for duplication method 生成した乱数が重複しているか判定します。
     * @param int[] intArray 生成した乱数
     * @retrun boolean duplicatedFlag 乱数が重複してたらfalse,そうでなかったらtrue.
     */
    public static boolean duplicationCheck(int[] intArray) {

	boolean isDupulicated = true;

	// if randNumber is not dupulicated, break.
	for (int i = 0; i < intArray.length; i++) {
	    for (int j = 0; j < i; j++) {
		if (intArray[i] == intArray[j]) {
		    isDupulicated = false;
		}
	    }
	}
	return isDupulicated;
    }

    /*
     * Number input method 整数入力メソッド
     * @param なし
     * @return int[] inputNumber 入力された整数
     */
    public static int[] inputNumber() {
	Scanner sc = new Scanner(System.in);
	int[] inputNumber = new int[4];

	int number = 0;
	// TODO 他の例外処理
	try {

	    number = sc.nextInt();

	} catch(InputMismatchException e) {

	   System.out.println("整数を入力してください");
	}


	// 一桁ずつ分解する
	for (int i = 3; i >= 0; i--) {
	    inputNumber[i] = number % 10;
	    number = number / 10;
	    System.out.println("inputNumber : " + i + " " + inputNumber[i]);
	}
	return inputNumber;

    }

    /*
     * Judgement method 判定メソッド 入力された整数と生成された乱数を比較して、判定します。
     * @param int[] inputNum 入力された整数,
     * @param int[] randNum 生成した乱数
     * @return boolean correctFlag 正解したらtrue,そうでなかったらfalse
     */
    public static boolean judgement(int[] inputNum, int[] randNum) {
	int BlowCnt = 0, HitCnt = 0;
	boolean correctFlag;

	for (int i = 0; i < inputNum.length; i++) {
	    for (int j = 0; j < inputNum.length; j++) {
		if (inputNum[i] == randNum[j]) {
		    BlowCnt++;
		}
	    }
	}
	for (int i = 0; i < inputNum.length; i++) {
	    if (inputNum[i] == randNum[i]) {
		HitCnt++;
	    }
	}
	System.out.println(HitCnt + "Hit " + (BlowCnt - HitCnt) + "Blow");
	if (HitCnt == 4) {
	    System.out.println("正解です。");
	    correctFlag = true;
	} else {
	    correctFlag = false;
	}
	return correctFlag;
    }
}