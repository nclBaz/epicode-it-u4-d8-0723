package functional_interfaces;

public class StringModifierImpl implements StringModifier{
	@Override
	public String modify(String str) {
		return "*" + str + "*";
	}
}
