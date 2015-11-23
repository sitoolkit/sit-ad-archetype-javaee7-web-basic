package org.sitoolkit.ad.archetype.basic.infra.code;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.sitoolkit.ad.archetype.basic.infra.FrameworkException;

/**
 *
 * @author SIToolkit
 */
@Named
@ApplicationScoped
public class CodeService {

    private static final String DOMAIN_CODE_PKG = CodeService.class.getPackage().getName().replace(".infra.",
            ".domain.");

    private Map<String, ICode[]> cache = new HashMap<String, ICode[]>();

    public ICode[] values(String voName) {
        ICode[] codes = cache.get(voName);
        if (codes == null) {
            try {
                Class<?> type = Class.forName(DOMAIN_CODE_PKG + "." + voName);
                codes = (ICode[]) type.getEnumConstants();
                cache.put(voName.toString(), codes);
            } catch (Exception e) {
                throw new FrameworkException(e);
            }
        }
        return codes;
    }
}
