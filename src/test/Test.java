package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class Test {

	/*
	 *超便利ラムダの使い方
	 	高階関数 [関数を引数に取る関数」
	 	  関数インターフェースの中に関数インターフェースを渡して
	 	  複雑な処理をスマートな記述をする

目次
★高階関数とラムダ式のすばらしさ編
★StreamAPIを使って様々なオブジェクトでラムダを使ってエレガントでスマートな記述
★StreamをList型に変換！？

	 */

	public static void main(String[] args) {

//////////////////////////////////////////////////////////////////

//		★高階関数とラムダ式のすばらしさ編

//////////////////////////////////////////////////////////////////
		//★配列nameListに[やまだ]がいるかを探したい
		List<String> nameList = new ArrayList<>();
		nameList.add("やまだ");
		nameList.add("たなか");
		nameList.add("いちろう");
		nameList.add("たろう");
		nameList.add("かわしま");

//●以前のおれ
		boolean isYamada = false;
		for(String name:nameList) {
			if(name.equals("やまだ")){
				isYamada = true;
				break;
			}
		}
		System.out.println(isYamada);//true

		boolean isTanaka = nameList.stream().anyMatch(s -> s.equals("たなか"));
		System.out.println(isTanaka);

/*
	●[ラムダ式記述]+[高階関数]（一行でできる！）
		nameList.stream()  ArryyListインスタンスからストリームを作成
							・ストリーム[宣言的に処理を行うためのデータの並び]
							・java.util.stream.Streamインターフェースとして扱い
								ストリームは関数インターフェースであり高階関数を扱える
		.anyMatch(key -> 条件) [anyMatch() : 条件が一つでもあればtrue]{}




 */
//////////////////////////////////////////////////////////////////

//★高階関数とラムダ式のすばらしさ編

//////////////////////////////////////////////////////////////////
/*

 		●ストリーム生成
			①コレクション
			[Stream<T>st = コレクション<T>.stream();]
				java.util.Colectionの子孫(リスト,セット)　MAPは含まれない
			②配列の場合
			[Stream<T>st = Arrays.stream(T型の配列)];


				基本データ型のストリーム使用例
					int[] points = {99,78,67,34,64,56,11};
					int total = Arrays.stream(points).sum();// .sum等の配列を扱う様々なメソッドがある
					System.out.println(total);

 ★Streamの主なデータ処理メソッド
    ●判定 	(渡すデータ : Predicate<T>)(戻り値 :  boolean)
	 	allMatch(関数) [すべての要素でtrueとなるか？]
	 	anyMatch(関数) [少なくとも１つの要素でtrueとなるか？]
	 	noneMatch(関数)[すべての要素でfalseとなるか？]

 	●個別処理 (渡すデータ :Consumer <T>)(戻り値 :  void)
 		forEach(関数)  [各要素に関数を使用]

 	●要素取得 (戻り値 : Optional<T>)
 		findFirst()   [最初の要素を返す]
 		findAny()   [いずれかの要素を返す]

 	●統計
 		count()   (戻り値 : long) [要素数を返す]

 		↓(渡すデータ : Comparator<T>)(戻り値: Optional<T>)
 		max(関数) [大小関係を関数で評価し、最大の要素を返す]
 		min(関数) [大小関係を関数で評価し、最小の要素を返す]

 */

//★使用例
	//forEach()
		nameList.stream().forEach(n->{
			System.out.println(n);
		});
	//findFirst() (Optional型で返すため .get() しないといけない)
		Optional<String> firstName = nameList.stream().findFirst();
		//System.out.println(nameList.stream().findFirst().get());
		if(firstName.isPresent())System.out.println("先頭は"+firstName.get());




//////////////////////////////////////////////////////////////////

//★StreamをList型に変換！？ →ストリーム　→加工　ほしいリストにして返還 

//////////////////////////////////////////////////////////////////

//下List記説用の[配列]
		Hero[] heroArray = new Hero[5];
		for(int i = 0; i < nameList.size(); i++) {
			String heroName = "Hero" + nameList.get(i);
			int hp = new Random().nextInt(1000);
			int mp = new Random().nextInt(100);
			Hero hero = new Hero(heroName,hp,mp);
			heroArray[i] = hero;
		};


/*
	★collect()でリストを取り出せる
		[構文]
			List<T> list = ストリーム.collect(Collectors.toList());
*/
		//●Hero配列[heroArray]　→ストリーム→　ArrayList[heroList]に変換
		List<Hero> heroList = Arrays.stream(heroArray).collect(Collectors.toList());
		//heroList.stream().forEach(s -> System.out.println("名前 : "+s.getName()));

		//●ArrayList[heroList]→ ストリーム →　ArrayList[heroes]
		List<Hero> heroes = heroList.stream().collect(Collectors.toList());

/*
 	★StreamAPIのデータ処理メソッドには戻り値がストリーム型のものがある
 	　データを選定してストリームにしてから加工できる

		●Stream主な中間処理メソッド
			distinct() [引数：なし] [重複を排除したストリームを返す]
			filter() [引数：Predicate<T>] [Predicate<T>を適応した結果が(true)である要素のみを返す]
			limit() [引数：long] [先頭から指定された要素までを返す]
			sorted() [引数：Comparator<T>] [Comparatorを用いて並び替えたストリームを返す]
			map() [引数：Function<T,R>] [Functionを適応した戻り値を要素とするR型の戻り値を返す]
 */
		//●Heroの人数
		//long allHero = heroes.stream().count();

		//●HP 300以下のヒーローの人数
		//long hpUnder300 = heroes.stream().filter(h -> h.getHp() < 300).count();

		List<String> weekHeros = heroes.stream().filter(h -> h.getHp() < 600)
								.limit(2)
								.map(h -> h.getName())
								.collect(Collectors.toList());
		weekHeros.stream().forEach(s -> System.out.println(s));


/*
	★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

 		Streamに様々な処理を続けて行うことができるメソッドを中間処理
 			(ちょっと上にかいてあるメソッドたち)

 	  	ストリームを特定のデータ型に変換する末端の処理を終端処理
 	  	   (collectと[高階関数とラムダ式のすばらしさ編]で扱ったメソッド)


			C言語（手続き型プログラミング）→
			オブジェクト指向プログラミング｛部品として考える｝→
			関数型プログラミング　｛高階関数の集まりとして考える｝

*	★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
*/



	}

}




