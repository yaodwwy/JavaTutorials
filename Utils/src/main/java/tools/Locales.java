package tools;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

public class Locales {
	public Map<String, Locale> getLocales() {
        Map<String, Locale> locales =new Hashtable<String, Locale>(2);
        locales.put("简体中文", Locale.CHINA);
        locales.put("English", Locale.US);
        return locales;
    }

}
