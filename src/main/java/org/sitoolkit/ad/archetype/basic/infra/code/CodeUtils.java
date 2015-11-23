package org.sitoolkit.ad.archetype.basic.infra.code;

/**
 * このクラスは、コードVOの操作に関するメソッドを提供するユーティリティです。
 * @author SIToolkit
 */
public class CodeUtils {

	/**
	 * コードの文字列をコードVOにデコードします。
	 * 文字列に対応するコードVOが無い場合はnullを返します。
	 * @param <T> コード定義VOの型
	 * @param codeValue コード
	 * @param codeArr デコード先となるコードVOの集合
	 * @return コードVO
	 */
	public static <T extends ICode> T decode(String codeValue, T[] codeArr) {
		for(T coce : codeArr) {
			if(coce.getValue().equals(codeValue)) {
				return coce;
			}
		}
		return null;
	}
}
