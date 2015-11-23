package org.sitoolkit.ad.archetype.basic.infra.code;

/**
 * このインターフェースは、コードVOによって実装されます。
 * @author SIToolkit
 */
public interface ICode {

	/**
	 * コードの名称を取得します。
	 * @return コードの名称
	 */
	public String getLabel();

	/**
	 * コードを取得します。
	 * @return コード
	 */
	public String getValue();
}
