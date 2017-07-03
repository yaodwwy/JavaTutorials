
import java.util.*;

/**
 * 要求：随机发扑克牌；
 * 1.充分利用循环给数组元素动态赋值
 * 2.给>1个玩家发牌，不能有重复牌，每人等分的牌。
 * 解决思路
 * 先打乱54张牌，再分配。
 */
public class 随机发扑克牌 {
	public static void main(String[] args) {

		// 扫描器获取用户输入
		Scanner input = new Scanner(System.in);

		// 随机数对象
		Random random = new Random();

		// 新建总牌数数组
		String pokers[] = new String[54];

		// 特殊牌单独赋值
		pokers[53] = "小王";
		pokers[0] = "大王";

		/**
		 * 任务一：得到54张牌，分别赋值花色和数字;
		 * 用for遍历，把其余52张牌全赋值；
		 * 用if对其中的JQKA进行重新赋值
		 * 花色分别为，红心、黑桃、方块、草花
		 * 对应的位置是pokers[1-13\ 14-26\ 27-39\ 40-53;
		 */

		for (int i = 1; i <= 4; i++) {
			if (i == 1) {
				String color = "红心";
				for (int j = 1; j < 14; j++) {
					if (j == 11) {
						pokers[j] = color + "J";
					} else if (j == 12) {
						pokers[j] = color + "Q";
					} else if (j == 13) {
						pokers[j] = color + "K";
					} else if (j == 1) {
						pokers[j] = color + "A";
					} else
						pokers[j] = color + j;
				}
			}
			if (i == 2) {
				String huaSe = "黑桃";
				for (int j = 14; j < 27; j++) {
					if (j - 13 == 11) {
						pokers[j] = huaSe + "J";
					} else if (j - 13 == 12) {
						pokers[j] = huaSe + "Q";
					} else if (j - 13 == 13) {
						pokers[j] = huaSe + "K";
					} else if (j - 13 == 1) {
						pokers[j] = huaSe + "A";
					} else
						pokers[j] = huaSe + (j - 13);
				}
			}

			if (i == 3) {
				String huaSe = "方块";
				for (int j = 27; j < 40; j++) {
					if (j - 26 == 11) {
						pokers[j] = huaSe + "J";
					} else if (j - 26 == 12) {
						pokers[j] = huaSe + "Q";
					} else if (j - 26 == 13) {
						pokers[j] = huaSe + "K";
					} else if (j - 26 == 1) {
						pokers[j] = huaSe + "A";
					} else
						pokers[j] = huaSe + (j - 26);
				}
			}
			if (i == 4) {
				String huaSe = "草花";
				for (int j = 40; j < 53; j++) {
					if (j - 39 == 11) {
						pokers[j] = huaSe + "J";
					} else if (j - 39 == 12) {
						pokers[j] = huaSe + "Q";
					} else if (j - 39 == 13) {
						pokers[j] = huaSe + "K";
					} else if (j - 39 == 1) {
						pokers[j] = huaSe + "A";
					} else
						pokers[j] = huaSe + (j - 39);

				}
			}
		}
		/*
		 * 测试poker是否正常储存至数组 
		  for (int i = 0; i < pokers.length; i++) {
		  System.out.print(pokers[i]); }
		 */
		
		/**
		 * 任务二：洗牌，对牌的顺序进行打乱
		 * 给>1个玩家发牌，不能有重复牌，每人等分的牌。 牌的总数为：（牌数－牌数%人数）/人数
		 */
		while (true) {
			System.out.println("请输入您与几个人打牌？");
			int players = input.nextInt() + 1;
			if (players > 54) {
				System.out.println("首先你要再买一副扑克，不然你就是在逗我玩呢");
				continue;
			} else
				System.out.println("开始发牌：");

			// 实际发牌的总数为：（牌数－牌数%人数）/人数
			int pokersSend = 54 - 54 % players;

			// 每个人的牌数：
			int myCardsNum = pokersSend / players;
			do {
				// 对牌的位置进行随机交换
				for (int i = pokers.length - 1; i >= 0; i--) {
					int j = random.nextInt(i + 1);
					String temp = pokers[j];
					pokers[j] = pokers[i];
					pokers[i] = temp;
				}

				// 打印各玩家手中的牌

				System.out.println("您的牌为：");
				for (int i = 0; i < myCardsNum; i++) {
					System.out.print(pokers[i] + "\t");
					// 自动换行
					if (i % players == 0) {
						System.out.println();
					}
				}
				for (int player = 1; player < players; player++) {
					System.out.print("\n\n您的" + player + "号对手的牌为：\n");
					for (int i = myCardsNum * player; i < myCardsNum
							* (player + 1); i++) {
						System.out.print(pokers[i] + "\t");
						// 自动换行
						if (i % players == 0) {
							System.out.println();
						}
					}
				}

				// 未发的底牌，如果有会显示
				System.out.println("\n\n底牌为：");
				if (pokers.length - pokersSend != 0) {
					for (int i = pokers.length - 1; i >= pokersSend; i--) {
						System.out.print(" " + pokers[i]);
					}
				} else
					System.out.println("空");

				System.out.println("\n是否重新洗牌？ y/n");
				String yn = input.next();
				if (!"y".equalsIgnoreCase(yn)) {
					System.out.println("看来你不想玩儿了，再见！");
					System.exit(0);
				} else
					continue;
			} while (true);
		}

	}

}