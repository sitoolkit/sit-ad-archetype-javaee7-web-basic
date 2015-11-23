package org.sitoolkit.ad.archetype.basic.infra.code;

/**
 * この列挙型は、フラグの値を定義するVoです。
 * @author SIToolkit
 *
 */
public enum FlagVo implements ICode {
		No,
		Yes
	;

	@Override
	public String getLabel() {
		return name();
	}

	@Override
	public String getValue() {
		return Integer.toString(getFlag());
	}

	public short getFlag() {
		return (short) ordinal();
	}

	public static short toFlag(boolean boo) {
		return boo ? Yes.getFlag() : No.getFlag();
	}

	public static boolean toBoolean(int flag) {
		return flag == Yes.getFlag();
	}
}
